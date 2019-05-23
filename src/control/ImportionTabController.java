package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.RadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.ImportionVO;

public class ImportionTabController implements Initializable {

	// 거래처 등록 탭
	@FXML
	private TextField txt_I_businessNumber;
	@FXML
	private TextField txt_I_name;
	@FXML
	private TextField txt_I_represent;
	@FXML
	private TextField txt_I_representPhone;
	@FXML
	private TextField txt_I_charge;
	@FXML
	private TextField txt_I_chargePhone;
	@FXML
	private TextField txt_I_address;
	@FXML
	private TextField txt_I_email;
	@FXML
	private TextField txt_I_business;
	@FXML
	private TableView<ImportionVO> ImportionTableView = new TableView<>();
	@FXML
	private Button btn_I_register; // 거래처 등록
	@FXML
	private Button btn_I_update; // 거래처 수정
	@FXML
	private Button btn_I_delete; // 거래처 삭제
	@FXML
	private Button btn_I_overlapBN;// 사업자번호 중복 검사
	@FXML
	private Button btn_I_payment;// 입금 버튼
	@FXML
	private TextField txt_I_payment;// 입금 텍스트
	@FXML
	private Button btn_I_clear;// 초가화버튼

	public static ObservableList<ImportionVO> ImportionDataList = FXCollections.observableArrayList();
	ObservableList<ImportionVO> selectImportion = null; // 매입거래처 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 거래처 정보 인덱스 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 거래처등록 초기화
			btn_I_register.setDisable(true);
			btn_I_update.setDisable(true);
			btn_I_delete.setDisable(true);
			btn_I_payment.setDisable(true);
			ImportionTableView.setEditable(false);

			txt_I_name.setEditable(false);
			txt_I_represent.setEditable(false);
			txt_I_representPhone.setEditable(false);
			txt_I_charge.setEditable(false);
			txt_I_chargePhone.setEditable(false);
			txt_I_address.setEditable(false);
			txt_I_email.setEditable(false);
			txt_I_business.setEditable(false);

			// 거래처 테이블 뷰 컬럼이름 설정
			TableColumn colINo = new TableColumn("거래처 번호");
			colINo.setPrefWidth(90);
			colINo.setStyle("-fx-allignment: CENTER");
			colINo.setCellValueFactory(new PropertyValueFactory<>("i_no"));

			TableColumn colIName = new TableColumn("상 호 명");
			colIName.setPrefWidth(90);
			colIName.setStyle("-fx-allignment: CENTER");
			colIName.setCellValueFactory(new PropertyValueFactory<>("i_name"));

			TableColumn colIBusinessNumber = new TableColumn("사업자 번호");
			colIBusinessNumber.setPrefWidth(90);
			colIBusinessNumber.setStyle("-fx-allignment: CENTER");
			colIBusinessNumber.setCellValueFactory(new PropertyValueFactory<>("i_businessNumber"));

			TableColumn colIRepredent = new TableColumn("대 표 명");
			colIRepredent.setPrefWidth(90);
			colIRepredent.setStyle("-fx-allignment: CENTER");
			colIRepredent.setCellValueFactory(new PropertyValueFactory<>("i_represent"));

			TableColumn colIRepredentPhone = new TableColumn("대표자 번호");
			colIRepredentPhone.setPrefWidth(90);
			colIRepredentPhone.setStyle("-fx-allignment: CENTER");
			colIRepredentPhone.setCellValueFactory(new PropertyValueFactory<>("i_representPhone"));

			TableColumn colICharge = new TableColumn("담 당 자");
			colICharge.setPrefWidth(90);
			colICharge.setStyle("-fx-allignment: CENTER");
			colICharge.setCellValueFactory(new PropertyValueFactory<>("i_charge"));

			TableColumn colIChargePhone = new TableColumn("담당자 번호");
			colIChargePhone.setPrefWidth(90);
			colIChargePhone.setStyle("-fx-allignment: CENTER");
			colIChargePhone.setCellValueFactory(new PropertyValueFactory<>("i_chargePhone"));

			TableColumn colIAddress = new TableColumn("주	소");
			colIAddress.setPrefWidth(90);
			colIAddress.setStyle("-fx-allignment: CENTER");
			colIAddress.setCellValueFactory(new PropertyValueFactory<>("i_address"));

			TableColumn colIEmail = new TableColumn("이 메 일");
			colIEmail.setPrefWidth(90);
			colIEmail.setStyle("-fx-allignment: CENTER");
			colIEmail.setCellValueFactory(new PropertyValueFactory<>("i_email"));

			TableColumn colIBusiness = new TableColumn("업	태");
			colIBusiness.setPrefWidth(90);
			colIBusiness.setStyle("-fx-allignment: CENTER");
			colIBusiness.setCellValueFactory(new PropertyValueFactory<>("i_business"));

			TableColumn colIPayment = new TableColumn("매입 미지급액");
			colIPayment.setPrefWidth(90);
			colIPayment.setStyle("-fx-allignment: CENTER");
			colIPayment.setCellValueFactory(new PropertyValueFactory<>("i_payment"));

			ImportionTableView.setItems(ImportionDataList);
			ImportionTableView.getColumns().addAll(colINo, colIName, colIBusinessNumber, colIRepredent,
					colIRepredentPhone, colICharge, colIChargePhone, colIAddress, colIEmail, colIBusiness, colIPayment);

			// 거래처 전체 목록
			ImportionTotalList();

			// 거래처 키 이벤트 등록
			txt_I_name.setOnKeyPressed(event -> handlerTxtNameKeyPressed(event));
			txt_I_represent.setOnKeyPressed(event -> handlerTxtRepresentKeyPressed(event));
			txt_I_representPhone.setOnKeyPressed(event -> handlerTxtRepresentNumberKeyPressed(event));
			txt_I_charge.setOnKeyPressed(event -> handlerTxtChargeKeyPressed(event));
			txt_I_chargePhone.setOnKeyPressed(event -> handlerTxtChargeNumberKeyPressed(event));
			txt_I_address.setOnKeyPressed(event -> handlerTxtAddressKeyPressed(event));
			txt_I_email.setOnKeyPressed(event -> handlerTxtEmailKeyPressed(event));

			// 거래처 등록, 수정, 삭제 이벤트 등록
			btn_I_register.setOnAction(event -> handlerbtn_I_registerActoion(event));
			btn_I_delete.setOnAction(event -> handlerbtn_I_deleteActoion(event));
			btn_I_update.setOnAction(event -> handlerbtn_I_updateActoion(event));
			ImportionTableView.setOnMouseClicked(event -> handlerImportionTableViewActoion(event));
			btn_I_overlapBN.setOnAction(event -> handlerBtnOverlapBNActoion(event));
			btn_I_payment.setOnAction(event -> handlerbtn_I_paymentAction(event));
			btn_I_clear.setOnAction(event -> handlerbtn_I_clearAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래처 등록 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtNameKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txt_I_represent.requestFocus();
		}
	}

	public void handlerTxtRepresentKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_representPhone.requestFocus();
		}
	}

	public void handlerTxtRepresentNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_charge.requestFocus();
		}
	}

	public void handlerTxtChargeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_chargePhone.requestFocus();
		}
	}

	public void handlerTxtChargeNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_address.requestFocus();
		}
	}

	public void handlerTxtAddressKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_email.requestFocus();
		}
	}

	public void handlerTxtEmailKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_business.requestFocus();
		}
	}

	// 사업자등록번호 중복 검사
	public void handlerBtnOverlapBNActoion(ActionEvent event) {
		btn_I_register.setDisable(false);
		btn_I_overlapBN.setDisable(true);

		ImportionTabDAO tDao = null;

		String searchBN = "";
		boolean searchResult = true;

		try {
			searchBN = txt_I_businessNumber.getText().trim();
			tDao = new ImportionTabDAO();
			searchResult = (boolean) tDao.getOverlapBN(searchBN);

			if (!searchResult && !searchBN.equals("")) {
				txt_I_businessNumber.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 있습니다.");
				alert.setContentText("상호명를 입력하세요.");
				alert.showAndWait();

				btn_I_register.setDisable(false);
				btn_I_overlapBN.setDisable(true);

				txt_I_name.setEditable(true);
				txt_I_represent.setEditable(true);
				txt_I_representPhone.setEditable(true);
				txt_I_charge.setEditable(true);
				txt_I_chargePhone.setEditable(true);
				txt_I_address.setEditable(true);
				txt_I_email.setEditable(true);
				txt_I_business.setEditable(true);
				txt_I_name.requestFocus();

			} else if (searchBN.equals("")) {
				btn_I_register.setDisable(true);
				btn_I_overlapBN.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("사업자등록번호 중복 검색");
				alert.setHeaderText("사업자등록번호를 입력하시오.");
				alert.setContentText("등록할 사업자등록번호를 입력하세요!");
				alert.showAndWait();
			} else {
				btn_I_register.setDisable(true);
				btn_I_overlapBN.setDisable(false);
				txt_I_businessNumber.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 없습니다.");
				alert.setContentText("사업자등록번호를 다른것으로 입력하세요.");
				alert.showAndWait();

				txt_I_businessNumber.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("사업자등록번호 중복 검사 오류");
			alert.setHeaderText("사업자등록번호 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 매입 거래처 테이블뷰 더블클릭 선택 이벤트 핸들러
	public void handlerImportionTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectImportion = ImportionTableView.getSelectionModel().getSelectedItems();
				selectedIndex = selectImportion.get(0).getI_no();
				String selectedI_name = selectImportion.get(0).getI_name();
				String selectedI_businessNumber = selectImportion.get(0).getI_businessNumber();
				String selectedI_represent = selectImportion.get(0).getI_represent();
				String selectedI_representPhone = selectImportion.get(0).getI_representPhone();
				String selectedI_charge = selectImportion.get(0).getI_charge();
				String selectedI_chargePhone = selectImportion.get(0).getI_chargePhone();
				String selectedI_address = selectImportion.get(0).getI_address();
				String selectedI_email = selectImportion.get(0).getI_email();
				String selectedI_business = selectImportion.get(0).getI_business();

				txt_I_name.setText(selectedI_name);
				txt_I_businessNumber.setText(selectedI_businessNumber);
				txt_I_represent.setText(selectedI_represent);
				txt_I_representPhone.setText(selectedI_representPhone);
				txt_I_charge.setText(selectedI_charge);
				txt_I_chargePhone.setText(selectedI_chargePhone);
				txt_I_address.setText(selectedI_address);
				txt_I_email.setText(selectedI_email);
				txt_I_business.setText(selectedI_business);

				btn_I_update.setDisable(false);
				btn_I_delete.setDisable(false);
				btn_I_register.setDisable(true);
				btn_I_overlapBN.setDisable(true);
				btn_I_payment.setDisable(false);

				txt_I_businessNumber.setEditable(false);
				txt_I_name.setEditable(true);
				txt_I_represent.setEditable(true);
				txt_I_representPhone.setEditable(true);
				txt_I_charge.setEditable(true);
				txt_I_chargePhone.setEditable(true);
				txt_I_address.setEditable(true);
				txt_I_email.setEditable(true);
				txt_I_business.setEditable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 거래처 전체 리스트
	public void ImportionTotalList() throws Exception {
		ImportionDataList.removeAll(ImportionDataList);

		ImportionTabDAO tDao = new ImportionTabDAO();
		ImportionVO iVo = null;
		ArrayList<ImportionVO> list;

		list = tDao.getImportionVOTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			iVo = list.get(index);
			ImportionDataList.add(iVo);
		}
	}

	// 매입거래처 등록 이벤트 핸들러
	public void handlerbtn_I_registerActoion(ActionEvent event) {
		Alert alert = null; // 알럿 객체 null 초기화

		if (txt_I_name.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("상호명를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_represent.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_representPhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자번호를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_address.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("주소를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_business.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("업태를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
		if (alert != null) {
			return;
		}
		try {
			selectImportion = ImportionTableView.getSelectionModel().getSelectedItems();

			ImportionDataList.removeAll(ImportionDataList);

			ImportionVO iVo = null;
			ImportionTabDAO tDao = null;

			iVo = new ImportionVO(selectedIndex, txt_I_name.getText().trim(), txt_I_businessNumber.getText().trim(),
					txt_I_represent.getText().trim(), txt_I_representPhone.getText().trim(),
					txt_I_charge.getText().trim(), txt_I_chargePhone.getText().trim(), txt_I_address.getText().trim(),
					txt_I_email.getText().trim(), txt_I_business.getText().trim());
			tDao = new ImportionTabDAO();
			tDao.getImportionRegiste(iVo);

			if (tDao != null) {

				ImportionTotalList();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 입력");
				alert.setHeaderText(txt_I_name.getText() + " 거래처가 성공적으로 추가되었습니다..");
				alert.setContentText("다음 거래처를 입력하세요");
				alert.showAndWait();

				handlerbtn_I_clearAction(event);
			}

		} catch (Exception e) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 입력");
			alert.setHeaderText("거래처 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 거래처 수정 이벤트 핸들러
	public void handlerbtn_I_updateActoion(ActionEvent event) {
		Alert alert = null; // 알럿 객체 null 초기화

		if (txt_I_name.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("상호명를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_represent.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_representPhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자번호를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_address.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("주소를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_I_business.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("업태를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
		if (alert != null) {
			return;
		}
		try {

			boolean sucess;

			ImportionTabDAO tDao = new ImportionTabDAO();
			sucess = tDao.getImportionUpdate(selectedIndex, txt_I_name.getText().trim(),
					txt_I_businessNumber.getText().trim(), txt_I_represent.getText().trim(),
					txt_I_representPhone.getText().trim(), txt_I_charge.getText().trim(),
					txt_I_chargePhone.getText().trim(), txt_I_address.getText().trim(), txt_I_email.getText().trim(),
					txt_I_business.getText().trim());
			if (sucess) {
				ImportionDataList.removeAll(ImportionDataList);
				ImportionTotalList();

				handlerbtn_I_clearAction(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//초기화 이벤트 핸들러
	public void handlerbtn_I_clearAction(ActionEvent event) {

		txt_I_businessNumber.clear();
		txt_I_name.clear();
		txt_I_represent.clear();
		txt_I_representPhone.clear();
		txt_I_charge.clear();
		txt_I_chargePhone.clear();
		txt_I_address.clear();
		txt_I_email.clear();
		txt_I_business.clear();
		txt_I_payment.clear();

		txt_I_businessNumber.requestFocus();

		btn_I_register.setDisable(true);
		btn_I_update.setDisable(true);
		btn_I_delete.setDisable(true);
		btn_I_overlapBN.setDisable(false);
		btn_I_payment.setDisable(true);

		txt_I_businessNumber.setDisable(false);
		txt_I_businessNumber.setEditable(true);
		txt_I_name.setEditable(false);
		txt_I_represent.setEditable(false);
		txt_I_representPhone.setEditable(false);
		txt_I_charge.setEditable(false);
		txt_I_chargePhone.setEditable(false);
		txt_I_address.setEditable(false);
		txt_I_email.setEditable(false);
		txt_I_business.setEditable(false);

	}

	// 거래처 삭제 이벤트 핸들러
	public void handlerbtn_I_deleteActoion(ActionEvent event) {
		try {
			boolean sucess;

			ImportionTabDAO tDao = new ImportionTabDAO();
			sucess = tDao.getImportionDelete(selectedIndex);
			if (sucess) {
				ImportionDataList.removeAll(ImportionDataList);
				ImportionTotalList();

				btn_I_register.setDisable(false);
				btn_I_update.setDisable(true);
				btn_I_delete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수금 버튼 이벤트 핸들러
	public void handlerbtn_I_paymentAction(ActionEvent event) {
		try {
			boolean sucess;

			ImportionTabDAO iDao = new ImportionTabDAO();
			sucess = iDao.getPayment(txt_I_name.getText().trim(), txt_I_businessNumber.getText().trim(),
					txt_I_business.getText().trim(), txt_I_payment.getText().trim(), selectedIndex);
			if (sucess) {

				handlerbtn_I_clearAction(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 거래처 미수금액 변경
		try {

			boolean sucess;

			ImportionTabDAO iDao = new ImportionTabDAO();
			sucess = iDao.getaccountUpdateCollect(selectedIndex, txt_I_payment.getText().trim());
			if (sucess) {
				ImportionDataList.removeAll(ImportionDataList);
				ImportionTotalList();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
