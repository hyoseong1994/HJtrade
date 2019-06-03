package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class ImportionTabController implements Initializable {

	// 거래처 등록 탭
	@FXML
	private TextField txt_I_businessNumber;// 사업자번호 텍스트필드
	@FXML
	private TextField txt_I_name;// 상호명 텍스트필드
	@FXML
	private TextField txt_I_represent;// 대표자 텍스트필드
	@FXML
	private TextField txt_I_representPhone;// 대표자번호 텍스트필드
	@FXML
	private TextField txt_I_charge;// 담당자 텍스트필드
	@FXML
	private TextField txt_I_chargePhone;// 담당자번호 텍스트필드
	@FXML
	private TextField txt_I_address;// 주소 텍스트필드
	@FXML
	private TextField txt_I_email;// 이메일 텍스트필드
	@FXML
	private TextField txt_I_business;// 업태 텍스트필드
	@FXML
	private TableView<ImportionVO> ImportionTableView = new TableView<>();// 매입거래처 테이블
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

	// 초기설정
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			// 사업자 번호 숫자만 입력가능
			txt_I_businessNumber.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_I_businessNumber.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 대표자 번호 숫자만 입력가능
			txt_I_representPhone.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_I_representPhone.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 담당자 번호 숫자만 입력가능
			txt_I_chargePhone.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_I_chargePhone.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 매입금 숫자만 입력가능
			txt_I_payment.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_I_payment.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});

			// 매입 거래처 버튼 초기화
			btn_I_register.setDisable(true);// 등록버튼 비활성화
			btn_I_update.setDisable(true);// 수정버튼 비활성화
			btn_I_delete.setDisable(true);// 삭제버튼 비활성화
			btn_I_payment.setDisable(true);// 입금버튼 비활성화

			// 판매거래처 테이블 수정금지
			ImportionTableView.setEditable(false);

			// 텍스트 필드 초기 설정
			txt_I_name.setEditable(false);// 거래처 이름 수정불가
			txt_I_represent.setEditable(false);// 대표자 수정불가
			txt_I_representPhone.setEditable(false);// 대표자번호 수정불가
			txt_I_charge.setEditable(false);// 담당자 수정불가
			txt_I_chargePhone.setEditable(false);// 담당자 번호 수정불가
			txt_I_address.setEditable(false);// 주소 수정불가
			txt_I_email.setEditable(false);
			;// 이메일 수정불가
			txt_I_business.setEditable(false);// 업태 수정불가

			// 거래처 테이블 뷰 컬럼이름 설정
			TableColumn colINo = new TableColumn("거래처 번호");// 컬럼명 설정
			colINo.setPrefWidth(90);// 컬럼 길이 설정
			colINo.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colINo.setCellValueFactory(new PropertyValueFactory<>("i_no"));// 걸럼값 불러오기

			TableColumn colIName = new TableColumn("상 호 명");// 컬럼명 설정
			colIName.setPrefWidth(90);// 컬럼 길이 설정
			colIName.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIName.setCellValueFactory(new PropertyValueFactory<>("i_name"));// 걸럼값 불러오기

			TableColumn colIBusinessNumber = new TableColumn("사업자 번호");// 컬럼명 설정
			colIBusinessNumber.setPrefWidth(90);// 컬럼 길이 설정
			colIBusinessNumber.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIBusinessNumber.setCellValueFactory(new PropertyValueFactory<>("i_businessNumber"));// 걸럼값 불러오기

			TableColumn colIRepredent = new TableColumn("대 표 명");// 컬럼명 설정
			colIRepredent.setPrefWidth(90);// 컬럼 길이 설정
			colIRepredent.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIRepredent.setCellValueFactory(new PropertyValueFactory<>("i_represent"));// 걸럼값 불러오기

			TableColumn colIRepredentPhone = new TableColumn("대표자 번호");// 컬럼명 설정
			colIRepredentPhone.setPrefWidth(90);// 컬럼 길이 설정
			colIRepredentPhone.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIRepredentPhone.setCellValueFactory(new PropertyValueFactory<>("i_representPhone"));// 걸럼값 불러오기

			TableColumn colICharge = new TableColumn("담 당 자");// 컬럼명 설정
			colICharge.setPrefWidth(90);// 컬럼 길이 설정
			colICharge.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colICharge.setCellValueFactory(new PropertyValueFactory<>("i_charge"));// 걸럼값 불러오기

			TableColumn colIChargePhone = new TableColumn("담당자 번호");// 컬럼명 설정
			colIChargePhone.setPrefWidth(90);// 컬럼 길이 설정
			colIChargePhone.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIChargePhone.setCellValueFactory(new PropertyValueFactory<>("i_chargePhone"));// 걸럼값 불러오기

			TableColumn colIAddress = new TableColumn("주	소");// 컬럼명 설정
			colIAddress.setPrefWidth(90);// 컬럼 길이 설정
			colIAddress.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIAddress.setCellValueFactory(new PropertyValueFactory<>("i_address"));// 걸럼값 불러오기

			TableColumn colIEmail = new TableColumn("이 메 일");// 컬럼명 설정
			colIEmail.setPrefWidth(90);// 컬럼 길이 설정
			colIEmail.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIEmail.setCellValueFactory(new PropertyValueFactory<>("i_email"));// 걸럼값 불러오기

			TableColumn colIBusiness = new TableColumn("업	태");// 컬럼명 설정
			colIBusiness.setPrefWidth(90);// 컬럼 길이 설정
			colIBusiness.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIBusiness.setCellValueFactory(new PropertyValueFactory<>("i_business"));// 걸럼값 불러오기

			TableColumn colIPayment = new TableColumn("매입 미지급액");// 컬럼명 설정
			colIPayment.setPrefWidth(90);// 컬럼 길이 설정
			colIPayment.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colIPayment.setCellValueFactory(new PropertyValueFactory<>("i_payment"));// 걸럼값 불러오기

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

			// 버튼 이벤트 등록
			btn_I_register.setOnAction(event -> handlerbtn_I_registerActoion(event));
			btn_I_delete.setOnAction(event -> handlerbtn_I_deleteActoion(event));
			btn_I_update.setOnAction(event -> handlerbtn_I_updateActoion(event));
			btn_I_overlapBN.setOnAction(event -> handlerBtnOverlapBNActoion(event));
			btn_I_payment.setOnAction(event -> handlerbtn_I_paymentAction(event));
			btn_I_clear.setOnAction(event -> handlerbtn_I_clearAction(event));

			// 테이블 더블클릭 이벤트
			ImportionTableView.setOnMouseClicked(event -> handlerImportionTableViewActoion(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래처 이름 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtNameKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txt_I_represent.requestFocus();
		}
	}

	// 거래처 대표자 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtRepresentKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_representPhone.requestFocus();
		}
	}

	// 거래처 대표자번호 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtRepresentNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_charge.requestFocus();
		}
	}

	// 거래처 담당자 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtChargeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_chargePhone.requestFocus();
		}
	}

	// 거래처 담당자 번호 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtChargeNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_address.requestFocus();
		}
	}

	// 거래처 주소 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtAddressKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_email.requestFocus();
		}
	}

	// 거래처 이메일 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtEmailKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_I_business.requestFocus();
		}
	}

	// 사업자등록번호 중복 검사
	public void handlerBtnOverlapBNActoion(ActionEvent event) {
		ImportionTabDAO tDao = null;// 판매거래처 DAO 객체 null값 초기화

		String searchBN = "";// 사업자번호을 넣을 변수 선언
		boolean searchResult = true;// 성공 실패 여부 확인을위한 변수 선언

		try {
			// 사업자번호 텍스트필드에 값을 선언된 변수에 넣는다
			searchBN = txt_I_businessNumber.getText().trim();
			// 판매거래처 DAO을 선언된 인스턴스에 넣는다
			tDao = new ImportionTabDAO();
			// DAO에서 호출한 메소드에 입력된 사업자번호를 넣고 결과값 가져오기
			searchResult = (boolean) tDao.getOverlapBN(searchBN);

			// 결과가 ture 이고 공란이 아닐시 실행
			if (!searchResult && !searchBN.equals("")) {
				txt_I_businessNumber.setDisable(true);

				// 사업자번호 사용가능하다는 정보창 출력
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 있습니다.");
				alert.setContentText("상호명를 입력하세요.");
				alert.showAndWait();

				// 필드 설정
				txt_I_name.setEditable(true);
				txt_I_represent.setEditable(true);
				txt_I_representPhone.setEditable(true);
				txt_I_charge.setEditable(true);
				txt_I_chargePhone.setEditable(true);
				txt_I_address.setEditable(true);
				txt_I_email.setEditable(true);
				txt_I_business.setEditable(true);
				txt_I_name.requestFocus();

				// 버튼 설정
				btn_I_register.setDisable(false);
				btn_I_overlapBN.setDisable(true);

				// 공란일 시 실행
			} else if (searchBN.equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("사업자등록번호 중복 검색");
				alert.setHeaderText("사업자등록번호를 입력하시오.");
				alert.setContentText("등록할 사업자등록번호를 입력하세요!");
				alert.showAndWait();

				// 결과가 false일 시 실행
			} else {

				// 사업자 텍스트 필드 초기화
				txt_I_businessNumber.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 없습니다.");
				alert.setContentText("사업자등록번호를 다른것으로 입력하세요.");
				alert.showAndWait();

				// 사업자 번호 텍스트 필드로 커서 이동
				txt_I_businessNumber.requestFocus();
			}
			// 사업자번호 중복검사중 오류일시 에러창 출력
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

		// 더블클릭시 실행
		if (event.getClickCount() == 2) {
			try {
				// 테이블에서 선택된값 인스턴스에 저장
				selectImportion = ImportionTableView.getSelectionModel().getSelectedItems();
				// 테이블에서 선택된번호 인스턴스에 저장
				selectedIndex = selectImportion.get(0).getI_no();

				// 인스턴스에 저장된값 변수에 저장
				String selectedI_name = selectImportion.get(0).getI_name();
				String selectedI_businessNumber = selectImportion.get(0).getI_businessNumber();
				String selectedI_represent = selectImportion.get(0).getI_represent();
				String selectedI_representPhone = selectImportion.get(0).getI_representPhone();
				String selectedI_charge = selectImportion.get(0).getI_charge();
				String selectedI_chargePhone = selectImportion.get(0).getI_chargePhone();
				String selectedI_address = selectImportion.get(0).getI_address();
				String selectedI_email = selectImportion.get(0).getI_email();
				String selectedI_business = selectImportion.get(0).getI_business();

				// 텍스트 필드에 변수값 넣기
				txt_I_name.setText(selectedI_name);
				txt_I_businessNumber.setText(selectedI_businessNumber);
				txt_I_represent.setText(selectedI_represent);
				txt_I_representPhone.setText(selectedI_representPhone);
				txt_I_charge.setText(selectedI_charge);
				txt_I_chargePhone.setText(selectedI_chargePhone);
				txt_I_address.setText(selectedI_address);
				txt_I_email.setText(selectedI_email);
				txt_I_business.setText(selectedI_business);

				// 버튼 설정
				btn_I_update.setDisable(false);
				btn_I_delete.setDisable(false);
				btn_I_register.setDisable(true);
				btn_I_overlapBN.setDisable(true);
				btn_I_payment.setDisable(false);

				// 텍스트 필드 설정
				txt_I_businessNumber.setEditable(false);
				txt_I_name.setEditable(true);
				txt_I_represent.setEditable(true);
				txt_I_representPhone.setEditable(true);
				txt_I_charge.setEditable(true);
				txt_I_chargePhone.setEditable(true);
				txt_I_address.setEditable(true);
				txt_I_email.setEditable(true);
				txt_I_business.setEditable(true);

				// 더블클릭 이벤트 실행중 오류시 오류 내용 출력
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 거래처 전체 리스트
	public void ImportionTotalList() throws Exception {
		// 데이터리스트 값 초기화
		ImportionDataList.removeAll(ImportionDataList);

		// 인스턴스 선언
		ImportionTabDAO tDao = new ImportionTabDAO();

		// VO객체 null값 초기화
		ImportionVO iVo = null;

		// ARrayList 에 VO입력
		ArrayList<ImportionVO> list;

		// list 에 DB에서 가져온 데이터 저장
		list = tDao.getImportionVOTotalList();
		// DB에서 가져온 정보사이즈를 변수에 저장
		int rowCount = list.size();

		// 데이터리스트에 데이터를 저장하기 위한 for문
		for (int index = 0; index < rowCount; index++) {
			iVo = list.get(index);
			ImportionDataList.add(iVo);
		}
	}

	// 매입거래처 등록 이벤트 핸들러
	public void handlerbtn_I_registerActoion(ActionEvent event) {
		Alert alert = null; // 알럿 객체 null 초기화

		// 텍스트 필드값이 null일때 경고창 출력
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
		// alert 이 null 값이 아닐시 리턴
		if (alert != null) {
			return;
		}
		try {
			// 데이터리스트 값 초기화
			ImportionDataList.removeAll(ImportionDataList);

			// 객체 null값 초가화
			ImportionVO iVo = null;
			ImportionTabDAO tDao = null;

			// 객체에 텍스트필드값 저장
			iVo = new ImportionVO(selectedIndex, txt_I_name.getText().trim(), txt_I_businessNumber.getText().trim(),
					txt_I_represent.getText().trim(), txt_I_representPhone.getText().trim(),
					txt_I_charge.getText().trim(), txt_I_chargePhone.getText().trim(), txt_I_address.getText().trim(),
					txt_I_email.getText().trim(), txt_I_business.getText().trim());
			// 객체에 AccountTabDAO 저장
			tDao = new ImportionTabDAO();
			// getAccountRegiste 에 텍스트필드값이 저장된 객체 입력
			tDao.getImportionRegiste(iVo);

			// 반화된값이 null이 아닐경우
			if (tDao != null) {

				// 전체리스트 메소드 호출
				ImportionTotalList();

				// 거래처등록 성공 정보창 출력
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 입력");
				alert.setHeaderText(txt_I_name.getText() + " 거래처가 성공적으로 추가되었습니다..");
				alert.setContentText("다음 거래처를 입력하세요");
				alert.showAndWait();

				// 초기화 핸들러 호출
				handlerbtn_I_clearAction(event);
			}

			// 거래처 등록중 오류시 경고창 출력
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

		// 텍스트 필드값이 null일때 경고창 출력
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
		// alert 이 null 값이 아닐시 리턴
		if (alert != null) {
			return;
		}
		try {

			boolean sucess;// 거래처 수정 성공여부 변수선언

			// 인스턴스 선언
			ImportionTabDAO tDao = new ImportionTabDAO();
			// 객체에 텍스트필드값 저장
			sucess = tDao.getImportionUpdate(selectedIndex, txt_I_name.getText().trim(),
					txt_I_businessNumber.getText().trim(), txt_I_represent.getText().trim(),
					txt_I_representPhone.getText().trim(), txt_I_charge.getText().trim(),
					txt_I_chargePhone.getText().trim(), txt_I_address.getText().trim(), txt_I_email.getText().trim(),
					txt_I_business.getText().trim());
			// 반환된 값이 true일 경우
			if (sucess) {
				// 데이터리스트 값 초기화
				ImportionDataList.removeAll(ImportionDataList);
				// 전체리스트 메소드 호출
				ImportionTotalList();

				// 초기화 이벤트 핸들러 호출
				handlerbtn_I_clearAction(event);
			}
			// 수정 중 오류시 오류 내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 초기화 이벤트 핸들러
	public void handlerbtn_I_clearAction(ActionEvent event) {

		// 텍스트 필드 클리어
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

		// 사업자번호텍스트필드에 커서 이동
		txt_I_businessNumber.requestFocus();

		// 버튼 설정
		btn_I_register.setDisable(true);
		btn_I_update.setDisable(true);
		btn_I_delete.setDisable(true);
		btn_I_overlapBN.setDisable(false);
		btn_I_payment.setDisable(true);

		// 텍스트 필드 초기화 설정
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
			// 결과 값 반환을 저장하기위한 변수 선언
			boolean sucess;

			// 인스턴스 선언
			ImportionTabDAO tDao = new ImportionTabDAO();
			// 결과값 변수에 저장
			sucess = tDao.getImportionDelete(selectedIndex);
			// 결과가 true일 경우 실행
			if (sucess) {
				// 데이터리스트 값 초기화
				ImportionDataList.removeAll(ImportionDataList);
				// 전체리스트 호출
				ImportionTotalList();

				// 버튼 설정
				btn_I_register.setDisable(false);
				btn_I_update.setDisable(true);
				btn_I_delete.setDisable(true);
			}
			// 거래처 삭제중 오류시 오류내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 입금 버튼 이벤트 핸들러
	public void handlerbtn_I_paymentAction(ActionEvent event) {
		try {
			// 결과 값 반환을 저장하기위한 변수 선언
			boolean sucess;

			// 인스턴스 선언
			ImportionTabDAO iDao = new ImportionTabDAO();
			// 결과값 변수에 저장
			sucess = iDao.getPayment(txt_I_name.getText().trim(), txt_I_businessNumber.getText().trim(),
					txt_I_business.getText().trim(), txt_I_payment.getText().trim(), selectedIndex);
			// 결과가 true일 경우 실행
			if (sucess) {

				// 초기화 이벤트 핸들러 호출
				handlerbtn_I_clearAction(event);
			}
			// 거래처 입금중 오류시 오류내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 거래처 미입금금액 변경
		try {

			boolean sucess;// 거래처 삭제 성공여부 변수선언

			// 인스턴스 선언
			ImportionTabDAO iDao = new ImportionTabDAO();
			// 객체에 텍스트필드값 저장
			sucess = iDao.getaccountUpdateCollect(selectedIndex, txt_I_payment.getText().trim());
			// 반환된 값이 true일 경우
			if (sucess) {
				// 데이터리스트 값 초기화
				ImportionDataList.removeAll(ImportionDataList);
				// 전체리스트 메소드 호출
				ImportionTotalList();

			}
			// 미수금액 변경 중 오류시 오류 내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
