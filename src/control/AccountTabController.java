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
import model.AccountVO;

public class AccountTabController implements Initializable {

	// 거래처 등록 탭
	@FXML
	private TextField txt_A_businessNumber;
	@FXML
	private TextField txt_A_name;
	@FXML
	private TextField txt_A_represent;
	@FXML
	private TextField txt_A_representPhone;
	@FXML
	private TextField txt_A_charge;
	@FXML
	private TextField txt_A_chargePhone;
	@FXML
	private TextField txt_A_address;
	@FXML
	private TextField txt_A_email;
	@FXML
	private TextField txt_A_business;
	@FXML
	private TableView<AccountVO> AccountTableView = new TableView<>();
	@FXML
	private Button btn_A_register; // 거래처 등록
	@FXML
	private Button btn_A_update; // 거래처 수정
	@FXML
	private Button btn_A_delete; // 거래처 삭제
	@FXML
	private Button btn_A_overlapBN;// 사업자번호 중복 검사
	@FXML
	private Button btn_A_collect;// 수금 버튼
	@FXML
	private TextField txt_A_collect;// 수금 텍스트
	@FXML
	private Button btn_A_clear;// 초가화버튼

	public static ObservableList<AccountVO> accountDataList = FXCollections.observableArrayList();
	ObservableList<AccountVO> selectAccount = null; // 매입거래처 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 거래처 정보 인덱스 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 거래처등록 초기화
			btn_A_register.setDisable(true);
			btn_A_update.setDisable(true);
			btn_A_delete.setDisable(true);
			AccountTableView.setEditable(false);

			txt_A_name.setEditable(false);
			txt_A_represent.setEditable(false);
			txt_A_representPhone.setEditable(false);
			txt_A_charge.setEditable(false);
			txt_A_chargePhone.setEditable(false);
			txt_A_address.setEditable(false);
			txt_A_email.setEditable(false);
			txt_A_business.setEditable(false);

			// 거래처 테이블 뷰 컬럼이름 설정
			TableColumn colANo = new TableColumn("거래처 번호");
			colANo.setPrefWidth(90);
			colANo.setStyle("-fx-allignment: CENTER");
			colANo.setCellValueFactory(new PropertyValueFactory<>("A_no"));

			TableColumn colAName = new TableColumn("상 호 명");
			colAName.setPrefWidth(90);
			colAName.setStyle("-fx-allignment: CENTER");
			colAName.setCellValueFactory(new PropertyValueFactory<>("A_name"));

			TableColumn colABusinessNumber = new TableColumn("사업자 번호");
			colABusinessNumber.setPrefWidth(90);
			colABusinessNumber.setStyle("-fx-allignment: CENTER");
			colABusinessNumber.setCellValueFactory(new PropertyValueFactory<>("A_businessNumber"));

			TableColumn colARepredent = new TableColumn("대 표 명");
			colARepredent.setPrefWidth(90);
			colARepredent.setStyle("-fx-allignment: CENTER");
			colARepredent.setCellValueFactory(new PropertyValueFactory<>("A_represent"));

			TableColumn colARepredentPhone = new TableColumn("대표자 번호");
			colARepredentPhone.setPrefWidth(90);
			colARepredentPhone.setStyle("-fx-allignment: CENTER");
			colARepredentPhone.setCellValueFactory(new PropertyValueFactory<>("A_representPhone"));

			TableColumn colACharge = new TableColumn("담 당 자");
			colACharge.setPrefWidth(90);
			colACharge.setStyle("-fx-allignment: CENTER");
			colACharge.setCellValueFactory(new PropertyValueFactory<>("A_charge"));

			TableColumn colAChargePhone = new TableColumn("담당자 번호");
			colAChargePhone.setPrefWidth(90);
			colAChargePhone.setStyle("-fx-allignment: CENTER");
			colAChargePhone.setCellValueFactory(new PropertyValueFactory<>("A_chargePhone"));

			TableColumn colAAddress = new TableColumn("주	소");
			colAAddress.setPrefWidth(90);
			colAAddress.setStyle("-fx-allignment: CENTER");
			colAAddress.setCellValueFactory(new PropertyValueFactory<>("A_address"));

			TableColumn colAEmail = new TableColumn("이 메 일");
			colAEmail.setPrefWidth(90);
			colAEmail.setStyle("-fx-allignment: CENTER");
			colAEmail.setCellValueFactory(new PropertyValueFactory<>("A_email"));

			TableColumn colABusiness = new TableColumn("업	태");
			colABusiness.setPrefWidth(90);
			colABusiness.setStyle("-fx-allignment: CENTER");
			colABusiness.setCellValueFactory(new PropertyValueFactory<>("A_business"));

			TableColumn colACollect = new TableColumn("매입 미지급액");
			colACollect.setPrefWidth(90);
			colACollect.setStyle("-fx-allignment: CENTER");
			colACollect.setCellValueFactory(new PropertyValueFactory<>("A_collect"));

			AccountTableView.setItems(accountDataList);
			AccountTableView.getColumns().addAll(colANo, colAName, colABusinessNumber, colARepredent,
					colARepredentPhone, colACharge, colAChargePhone, colAAddress, colAEmail, colABusiness, colACollect);

			// 거래처 전체 목록
			AccountTotalList();

			// 거래처 키 이벤트 등록
			txt_A_name.setOnKeyPressed(event -> handlerTxtNameKeyPressed(event));
			txt_A_represent.setOnKeyPressed(event -> handlerTxtRepresentKeyPressed(event));
			txt_A_representPhone.setOnKeyPressed(event -> handlerTxtRepresentNumberKeyPressed(event));
			txt_A_charge.setOnKeyPressed(event -> handlerTxtChargeKeyPressed(event));
			txt_A_chargePhone.setOnKeyPressed(event -> handlerTxtChargeNumberKeyPressed(event));
			txt_A_address.setOnKeyPressed(event -> handlerTxtAddressKeyPressed(event));
			txt_A_email.setOnKeyPressed(event -> handlerTxtEmailKeyPressed(event));

			// 거래처 등록, 수정, 삭제 이벤트 등록
			btn_A_register.setOnAction(event -> handlerbtn_A_registerAction(event));
			btn_A_delete.setOnAction(event -> handlerbtn_A_deleteAction(event));
			btn_A_update.setOnAction(event -> handlerbtn_A_updateAction(event));
			AccountTableView.setOnMouseClicked(event -> handlerAccountTableViewAction(event));
			btn_A_overlapBN.setOnAction(event -> handlerBtnOverlapBNAction(event));
			btn_A_collect.setOnAction(event -> handlerbtn_A_collectAction(event));
			btn_A_clear.setOnAction(event -> handlerbtn_A_clearAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래처 등록 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtNameKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txt_A_represent.requestFocus();
		}
	}

	public void handlerTxtRepresentKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_representPhone.requestFocus();
		}
	}

	public void handlerTxtRepresentNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_charge.requestFocus();
		}
	}

	public void handlerTxtChargeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_chargePhone.requestFocus();
		}
	}

	public void handlerTxtChargeNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_address.requestFocus();
		}
	}

	public void handlerTxtAddressKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_email.requestFocus();
		}
	}

	public void handlerTxtEmailKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_business.requestFocus();
		}
	}

	// 사업자등록번호 중복 검사
	public void handlerBtnOverlapBNAction(ActionEvent event) {
		btn_A_register.setDisable(false);
		btn_A_overlapBN.setDisable(true);

		AccountTabDAO aDao = null;

		String searchBN = "";
		boolean searchResult = true;

		try {
			searchBN = txt_A_businessNumber.getText().trim();
			aDao = new AccountTabDAO();
			searchResult = (boolean) aDao.getOverlapBN(searchBN);

			if (!searchResult && !searchBN.equals("")) {
				txt_A_businessNumber.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 있습니다.");
				alert.setContentText("상호명를 입력하세요.");
				alert.showAndWait();

				btn_A_register.setDisable(false);
				btn_A_overlapBN.setDisable(true);
				txt_A_name.setEditable(true);
				txt_A_represent.setEditable(true);
				txt_A_representPhone.setEditable(true);
				txt_A_charge.setEditable(true);
				txt_A_chargePhone.setEditable(true);
				txt_A_address.setEditable(true);
				txt_A_email.setEditable(true);
				txt_A_business.setEditable(true);
				txt_A_name.requestFocus();

				btn_A_register.setDisable(false);

			} else if (searchBN.equals("")) {
				btn_A_register.setDisable(true);
				btn_A_overlapBN.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("사업자등록번호 중복 검색");
				alert.setHeaderText("사업자등록번호를 입력하시오.");
				alert.setContentText("등록할 사업자등록번호를 입력하세요!");
				alert.showAndWait();
			} else {
				btn_A_register.setDisable(true);
				btn_A_overlapBN.setDisable(false);
				txt_A_businessNumber.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 없습니다.");
				alert.setContentText("사업자등록번호를 다른것으로 입력하세요.");
				alert.showAndWait();

				txt_A_businessNumber.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("사업자등록번호 중복 검사 오류");
			alert.setHeaderText("사업자등록번호 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 판매 거래처 테이블뷰 더블클릭 선택 이벤트 핸들러
	public void handlerAccountTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectAccount = AccountTableView.getSelectionModel().getSelectedItems();
				selectedIndex = selectAccount.get(0).getA_no();
				String selectedA_name = selectAccount.get(0).getA_name();
				String selectedA_businessNumber = selectAccount.get(0).getA_businessNumber();
				String selectedA_represent = selectAccount.get(0).getA_represent();
				String selectedA_representPhone = selectAccount.get(0).getA_representPhone();
				String selectedA_charge = selectAccount.get(0).getA_charge();
				String selectedA_chargePhone = selectAccount.get(0).getA_chargePhone();
				String selectedA_address = selectAccount.get(0).getA_address();
				String selectedA_email = selectAccount.get(0).getA_email();
				String selectedA_business = selectAccount.get(0).getA_business();

				txt_A_name.setText(selectedA_name);
				txt_A_businessNumber.setText(selectedA_businessNumber);
				txt_A_represent.setText(selectedA_represent);
				txt_A_representPhone.setText(selectedA_representPhone);
				txt_A_charge.setText(selectedA_charge);
				txt_A_chargePhone.setText(selectedA_chargePhone);
				txt_A_address.setText(selectedA_address);
				txt_A_email.setText(selectedA_email);
				txt_A_business.setText(selectedA_business);

				btn_A_update.setDisable(false);
				btn_A_delete.setDisable(false);
				btn_A_register.setDisable(true);
				btn_A_overlapBN.setDisable(true);

				txt_A_businessNumber.setEditable(false);
				txt_A_name.setEditable(true);
				txt_A_represent.setEditable(true);
				txt_A_representPhone.setEditable(true);
				txt_A_charge.setEditable(true);
				txt_A_chargePhone.setEditable(true);
				txt_A_address.setEditable(true);
				txt_A_email.setEditable(true);
				txt_A_business.setEditable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 거래처 전체 리스트
	public void AccountTotalList() throws Exception {
		accountDataList.removeAll(accountDataList);

		AccountTabDAO aDao = new AccountTabDAO();
		AccountVO aVo = null;
		ArrayList<AccountVO> list;

		list = aDao.getaccountVOTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			aVo = list.get(index);
			accountDataList.add(aVo);
		}
	}

	// 판매 거래처 등록 이벤트 핸들러
	public void handlerbtn_A_registerAction(ActionEvent event) {
		Alert alert = null; // 알럿 객체 null 초기화

		if (txt_A_name.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("상호명를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_represent.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_representPhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자번호를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_charge.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("담당자를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_chargePhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("담당자번호를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_address.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("주소를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_email.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("이메일를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_business.getText().trim().equals("")) {
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
			selectAccount = AccountTableView.getSelectionModel().getSelectedItems();

			accountDataList.removeAll(accountDataList);

			AccountVO aVo = null;
			AccountTabDAO aDao = null;

			aVo = new AccountVO(selectedIndex, txt_A_name.getText().trim(), txt_A_businessNumber.getText().trim(),
					txt_A_represent.getText().trim(), txt_A_representPhone.getText().trim(),
					txt_A_charge.getText().trim(), txt_A_chargePhone.getText().trim(), txt_A_address.getText().trim(),
					txt_A_email.getText().trim(), txt_A_business.getText().trim());
			aDao = new AccountTabDAO();
			aDao.getAccountRegiste(aVo);

			if (aDao != null) {

				AccountTotalList();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 입력");
				alert.setHeaderText(txt_A_name.getText() + " 거래처가 성공적으로 추가되었습니다..");
				alert.setContentText("다음 거래처를 입력하세요");
				alert.showAndWait();

				txt_A_name.clear();
				txt_A_businessNumber.clear();
				txt_A_represent.clear();
				txt_A_representPhone.clear();
				txt_A_charge.clear();
				txt_A_chargePhone.clear();
				txt_A_address.clear();
				txt_A_email.clear();
				txt_A_business.clear();
				txt_A_businessNumber.requestFocus();

				txt_A_businessNumber.setDisable(false);
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
	public void handlerbtn_A_updateAction(ActionEvent event) {
		Alert alert = null; // 알럿 객체 null 초기화

		if (txt_A_name.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("상호명를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_represent.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_representPhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자번호를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_charge.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("담당자를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_chargePhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("담당자번호를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_address.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("주소를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_email.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("이메일를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_A_business.getText().trim().equals("")) {
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

			AccountTabDAO aDao = new AccountTabDAO();
			sucess = aDao.getaccountUpdate(selectedIndex, txt_A_name.getText().trim(),
					txt_A_businessNumber.getText().trim(), txt_A_represent.getText().trim(),
					txt_A_representPhone.getText().trim(), txt_A_charge.getText().trim(),
					txt_A_chargePhone.getText().trim(), txt_A_address.getText().trim(), txt_A_email.getText().trim(),
					txt_A_business.getText().trim());
			if (sucess) {
				accountDataList.removeAll(accountDataList);
				AccountTotalList();

				txt_A_name.clear();
				txt_A_businessNumber.clear();
				txt_A_represent.clear();
				txt_A_representPhone.clear();
				txt_A_charge.clear();
				txt_A_chargePhone.clear();
				txt_A_address.clear();
				txt_A_email.clear();
				txt_A_business.clear();
				txt_A_name.requestFocus();

				btn_A_register.setDisable(false);
				btn_A_update.setDisable(true);
				btn_A_delete.setDisable(true);
				txt_A_businessNumber.setEditable(true);
				txt_A_businessNumber.requestFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerbtn_A_clearAction(ActionEvent event) {

		txt_A_businessNumber.clear();
		txt_A_name.clear();
		txt_A_represent.clear();
		txt_A_representPhone.clear();
		txt_A_charge.clear();
		txt_A_chargePhone.clear();
		txt_A_address.clear();
		txt_A_email.clear();
		txt_A_business.clear();

		txt_A_businessNumber.requestFocus();

		btn_A_register.setDisable(true);
		btn_A_update.setDisable(true);
		btn_A_delete.setDisable(true);
		btn_A_overlapBN.setDisable(false);

		txt_A_businessNumber.setDisable(false);
		txt_A_businessNumber.setEditable(true);
		txt_A_name.setEditable(false);
		txt_A_represent.setEditable(false);
		txt_A_representPhone.setEditable(false);
		txt_A_charge.setEditable(false);
		txt_A_chargePhone.setEditable(false);
		txt_A_address.setEditable(false);
		txt_A_email.setEditable(false);
		txt_A_business.setEditable(false);
		
		

	}

	// 거래처 삭제 이벤트 핸들러
	public void handlerbtn_A_deleteAction(ActionEvent event) {
		try {
			boolean sucess;

			AccountTabDAO aDao = new AccountTabDAO();
			sucess = aDao.getaccountDelete(selectedIndex);
			if (sucess) {
				accountDataList.removeAll(accountDataList);
				AccountTotalList();

				btn_A_register.setDisable(false);
				btn_A_update.setDisable(true);
				btn_A_delete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수금 버튼 이벤트 핸들러
	public void handlerbtn_A_collectAction(ActionEvent event) {
		try {

			boolean sucess;

			AccountTabDAO aDao = new AccountTabDAO();
			sucess = aDao.getCollect(selectedIndex, txt_A_name.getText().trim(), txt_A_businessNumber.getText().trim(),
					txt_A_business.getText().trim(), txt_A_collect.getText().trim());
			if (sucess) {
				accountDataList.removeAll(accountDataList);
				AccountTotalList();

				txt_A_name.clear();
				txt_A_businessNumber.clear();
				txt_A_represent.clear();
				txt_A_representPhone.clear();
				txt_A_charge.clear();
				txt_A_chargePhone.clear();
				txt_A_address.clear();
				txt_A_email.clear();
				txt_A_business.clear();
				txt_A_name.requestFocus();

				btn_A_register.setDisable(false);
				btn_A_update.setDisable(true);
				btn_A_delete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 거래처 미수금액 변경
		try {

			boolean sucess;

			AccountTabDAO aDao = new AccountTabDAO();
			sucess = aDao.getaccountUpdateCollect(selectedIndex, txt_A_collect.getText().trim());
			if (sucess) {
				accountDataList.removeAll(accountDataList);
				AccountTotalList();

				txt_A_name.clear();
				txt_A_businessNumber.clear();
				txt_A_represent.clear();
				txt_A_representPhone.clear();
				txt_A_charge.clear();
				txt_A_chargePhone.clear();
				txt_A_address.clear();
				txt_A_email.clear();
				txt_A_business.clear();
				txt_A_name.requestFocus();

				btn_A_register.setDisable(false);
				btn_A_update.setDisable(true);
				btn_A_delete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
