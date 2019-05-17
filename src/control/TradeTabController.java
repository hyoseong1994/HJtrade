package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.ImportionVO;

public class TradeTabController implements Initializable {

	// 거래처 등록 탭
	@FXML
	private TextField txt_name;
	@FXML
	private TextField txt_businessNumber;
	@FXML
	private TextField txt_represent;
	@FXML
	private TextField txt_representPhone;
	@FXML
	private TextField txt_charge;
	@FXML
	private TextField txt_chargePhone;
	@FXML
	private TextField txt_address;
	@FXML
	private TextField txt_email;
	@FXML
	private TextField txt_business;
	@FXML
	private TableView<ImportionVO> ImportionTableView = new TableView<>();
	@FXML
	private Button btn_register; // 거래처 등록
	@FXML
	private Button btn_update; // 거래처 수정
	@FXML
	private Button btn_i_delete; // 거래처 삭제
	@FXML
	private Button btnRead;

	public static ObservableList<ImportionVO> ImportionDataList = FXCollections.observableArrayList();
	ObservableList<ImportionVO> selectImportion = null; // 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 거래처 정보 인덱스 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			// 거래처등록 초기화
			btn_update.setDisable(true);
			btn_i_delete.setDisable(true);
			ImportionTableView.setEditable(false);

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
			colIRepredent.setCellValueFactory(new PropertyValueFactory<>("i_repredent"));

			TableColumn colIRepredentPhone = new TableColumn("대표자 번호");
			colIRepredentPhone.setPrefWidth(90);
			colIRepredentPhone.setStyle("-fx-allignment: CENTER");
			colIRepredentPhone.setCellValueFactory(new PropertyValueFactory<>("i_repredentPhone"));

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
			ImportionTableView.getColumns().addAll(colINo, colIName, colIBusinessNumber, colIRepredent, colIRepredentPhone,
					colICharge, colIChargePhone, colIAddress, colIEmail, colIBusiness, colIPayment);

			// 거래처 전체 목록
			TradeTotalList();

			// 거래처 키 이벤트 등록
			txt_name.setOnKeyPressed(event -> handlerTxtNameKeyPressed(event));
			txt_businessNumber.setOnKeyPressed(event -> handlerTxtBusinessNumberKeyPressed(event));
			txt_represent.setOnKeyPressed(event -> handlerTxtRepresentKeyPressed(event));
			txt_representPhone.setOnKeyPressed(event -> handlerTxtRepresentNumberKeyPressed(event));
			txt_charge.setOnKeyPressed(event -> handlerTxtChargeKeyPressed(event));
			txt_chargePhone.setOnKeyPressed(event -> handlerTxtChargeNumberKeyPressed(event));
			txt_address.setOnKeyPressed(event -> handlerTxtAddressKeyPressed(event));
			txt_email.setOnKeyPressed(event -> handlerTxtEmailKeyPressed(event));

			// 거래처 등록, 수정, 삭제 이벤트 등록
			btn_register.setOnAction(event -> handlerbtn_registerActoion(event));
			btn_i_delete.setOnAction(event -> handlerbtn_i_deleteActoion(event));
			btn_update.setOnAction(event -> handlerbtn_updateActoion(event));
			ImportionTableView.setOnMouseClicked(event -> handlerImportionTableViewActoion(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래처 등록 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtNameKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txt_businessNumber.requestFocus();
		}
	}

	public void handlerTxtBusinessNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_represent.requestFocus();
		}
	}

	public void handlerTxtRepresentKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_representPhone.requestFocus();
		}
	}

	public void handlerTxtRepresentNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_charge.requestFocus();
		}
	}

	public void handlerTxtChargeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_chargePhone.requestFocus();
		}
	}

	public void handlerTxtChargeNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_address.requestFocus();
		}
	}

	public void handlerTxtAddressKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_email.requestFocus();
		}
	}

	public void handlerTxtEmailKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_business.requestFocus();
		}
	}

	// 거래처 테이블뷰 더블클릭 선택 이벤트 핸들러
	public void handlerImportionTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectImportion = ImportionTableView.getSelectionModel().getSelectedItems();
				selectedIndex = selectImportion.get(0).getI_no();
				String selectedi_name = selectImportion.get(0).getI_name();
				String selectedi_businessNumber = selectImportion.get(0).getI_businessNumber();
				String selectedi_represent = selectImportion.get(0).getI_represent();
				String selectedi_representPhone = selectImportion.get(0).getI_representPhone();
				String selectedi_charge = selectImportion.get(0).getI_charge();
				String selectedi_chargePhone = selectImportion.get(0).getI_chargePhone();
				String selectedi_address = selectImportion.get(0).getI_address();
				String selectedi_email = selectImportion.get(0).getI_email();
				String selectedi_business = selectImportion.get(0).getI_business();

				txt_name.setText(selectedi_name);
				txt_businessNumber.setText(selectedi_businessNumber);
				txt_represent.setText(selectedi_represent);
				txt_representPhone.setText(selectedi_representPhone);
				txt_charge.setText(selectedi_charge);
				txt_chargePhone.setText(selectedi_chargePhone);
				txt_address.setText(selectedi_address);
				txt_email.setText(selectedi_email);
				txt_business.setText(selectedi_business);

				btn_update.setDisable(false);
				btn_i_delete.setDisable(false);
				btn_register.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 거래처 전체 리스트
	public void TradeTotalList() throws Exception {
		ImportionDataList.removeAll(ImportionDataList);

		TradeTabDAO tDao = new TradeTabDAO();
		ImportionVO iVo = null;
		ArrayList<String> title;
		ArrayList<ImportionVO> list;

		title = tDao.getImportionColumnName();
		int columnCount = title.size();
		
		list = tDao.getImportionVOTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			iVo = list.get(index);
			ImportionDataList.add(iVo);
		}
	}

	// 거래처 등록 이벤트 핸들러
	public void handlerbtn_registerActoion(ActionEvent event) {
		try {
			ImportionDataList.removeAll(ImportionDataList);

			ImportionVO iVo = null;
			TradeTabDAO tDao = null;

			iVo = new ImportionVO(selectedIndex, txt_name.getText().trim(), txt_businessNumber.getText().trim(),
					txt_represent.getText().trim(), txt_representPhone.getText().trim(), txt_charge.getText().trim(),
					txt_chargePhone.getText().trim(), txt_address.getText().trim(), txt_email.getText().trim(),
					txt_business.getText().trim());
			tDao = new TradeTabDAO();
			tDao.getImportionRegiste(iVo);

			if (tDao != null) {

				TradeTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 입력");
				alert.setHeaderText(txt_name.getText() + " 거래처가 성공적으로 추가되었습니다..");
				alert.setContentText("다음 거래처를 입력하세요");
				alert.showAndWait();

				txt_name.clear();
				txt_businessNumber.clear();
				txt_represent.clear();
				txt_representPhone.clear();
				txt_charge.clear();
				txt_chargePhone.clear();
				txt_address.clear();
				txt_email.clear();
				txt_business.clear();
				txt_name.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 입력");
			alert.setHeaderText("거래처 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 거래처 수정 이벤트 핸들러
	public void handlerbtn_updateActoion(ActionEvent event) {
		try {
			boolean sucess;

			TradeTabDAO tDao = new TradeTabDAO();
			sucess = tDao.getImportionUpdate(selectedIndex, txt_name.getText().trim(),
					txt_businessNumber.getText().trim(), txt_represent.getText().trim(),
					txt_representPhone.getText().trim(), txt_charge.getText().trim(), txt_chargePhone.getText().trim(),
					txt_address.getText().trim(), txt_email.getText().trim(), txt_business.getText().trim());
			if (sucess) {
				ImportionDataList.removeAll(ImportionDataList);
				TradeTotalList();

				txt_name.clear();
				txt_businessNumber.clear();
				txt_represent.clear();
				txt_representPhone.clear();
				txt_charge.clear();
				txt_chargePhone.clear();
				txt_address.clear();
				txt_email.clear();
				txt_business.clear();
				txt_name.requestFocus();

				btn_register.setDisable(false);
				btn_update.setDisable(true);
				btn_i_delete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래처 삭제 이벤트 핸들러
	public void handlerbtn_i_deleteActoion(ActionEvent event) {
		try {
			boolean sucess;

			TradeTabDAO tDao = new TradeTabDAO();
			sucess = tDao.getImportionDelete(selectedIndex);
			if (sucess) {
				ImportionDataList.removeAll(ImportionDataList);
				TradeTotalList();

				btn_register.setDisable(false);
				btn_update.setDisable(true);
				btn_i_delete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
