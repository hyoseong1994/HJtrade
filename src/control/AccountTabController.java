package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.RadioButton;
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
	private TextField txt_A_businessNumber;// 사업자번호 텍스트필드
	@FXML
	private TextField txt_A_name;// 상호명 텍스트필드
	@FXML
	private TextField txt_A_represent;// 대표자 텍스트필드
	@FXML
	private TextField txt_A_representPhone;// 대표자번호 텍스트필드
	@FXML
	private TextField txt_A_charge;// 담당자 텍스트필드
	@FXML
	private TextField txt_A_chargePhone;// 담당자번호 텍스트필드
	@FXML
	private TextField txt_A_address;// 주소 텍스트필드
	@FXML
	private TextField txt_A_email;// 이메일 텍스트필드
	@FXML
	private TextField txt_A_business;// 업태 텍스트필드
	@FXML
	private TableView<AccountVO> AccountTableView = new TableView<>();// 판매 거래처 테이블
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
	ObservableList<AccountVO> selectAccount = null; // 판매거래처 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 거래처 정보 인덱스 저장

	// 초기설정
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			// 사업자 번호 숫자만 입력가능
			txt_A_businessNumber.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_A_businessNumber.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 대표자 번호 숫자만 입력가능
			txt_A_representPhone.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_A_representPhone.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 담당자 번호 숫자만 입력가능
			txt_A_chargePhone.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_A_chargePhone.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			//수금액 숫자만 입력가능
			txt_A_collect.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_A_collect.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			
			// 판매 거래처 버튼 초기화
			btn_A_collect.setDisable(true);// 수금버튼 비활성화
			btn_A_register.setDisable(true);// 등록버튼 비활성화
			btn_A_update.setDisable(true);// 수정버튼 비활성화
			btn_A_delete.setDisable(true);// 삭제버튼 비활성화

			// 판매거래처 테이블 수정금지
			AccountTableView.setEditable(false);

			// 텍스트 필드 초기 설정
			txt_A_name.setEditable(false);// 거래처 이름 수정불가
			txt_A_represent.setEditable(false);// 대표자 수정불가
			txt_A_representPhone.setEditable(false);// 대표자번호 수정불가
			txt_A_charge.setEditable(false);// 담당자 수정불가
			txt_A_chargePhone.setEditable(false);// 담당자 번호 수정불가
			txt_A_address.setEditable(false);// 주소 수정불가
			txt_A_email.setEditable(false);// 이메일 수정불가
			txt_A_business.setEditable(false);// 업태 수정불가

			// 거래처 테이블 뷰 컬럼이름 설정
			TableColumn colANo = new TableColumn("거래처 번호");// 컬럼명 설정
			colANo.setPrefWidth(90);// 컬럼 길이 설정
			colANo.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colANo.setCellValueFactory(new PropertyValueFactory<>("A_no"));// 걸럼값 불러오기

			TableColumn colAName = new TableColumn("상 호 명");// 컬럼명 설정
			colAName.setPrefWidth(90);// 컬럼 길이 설정
			colAName.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colAName.setCellValueFactory(new PropertyValueFactory<>("A_name"));// 걸럼값 불러오기

			TableColumn colABusinessNumber = new TableColumn("사업자 번호");// 컬럼명 설정
			colABusinessNumber.setPrefWidth(90);// 컬럼 길이 설정
			colABusinessNumber.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colABusinessNumber.setCellValueFactory(new PropertyValueFactory<>("A_businessNumber"));// 걸럼값 불러오기

			TableColumn colARepredent = new TableColumn("대 표 명");// 컬럼명 설정
			colARepredent.setPrefWidth(90);// 컬럼 길이 설정
			colARepredent.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colARepredent.setCellValueFactory(new PropertyValueFactory<>("A_represent"));// 걸럼값 불러오기

			TableColumn colARepredentPhone = new TableColumn("대표자 번호");// 컬럼명 설정
			colARepredentPhone.setPrefWidth(90);// 컬럼 길이 설정
			colARepredentPhone.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colARepredentPhone.setCellValueFactory(new PropertyValueFactory<>("A_representPhone"));// 걸럼값 불러오기

			TableColumn colACharge = new TableColumn("담 당 자");// 컬럼명 설정
			colACharge.setPrefWidth(90);// 컬럼 길이 설정
			colACharge.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colACharge.setCellValueFactory(new PropertyValueFactory<>("A_charge"));// 걸럼값 불러오기

			TableColumn colAChargePhone = new TableColumn("담당자 번호");// 컬럼명 설정
			colAChargePhone.setPrefWidth(90);// 컬럼 길이 설정
			colAChargePhone.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colAChargePhone.setCellValueFactory(new PropertyValueFactory<>("A_chargePhone"));// 걸럼값 불러오기

			TableColumn colAAddress = new TableColumn("주	소");// 컬럼명 설정
			colAAddress.setPrefWidth(90);// 컬럼 길이 설정
			colAAddress.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colAAddress.setCellValueFactory(new PropertyValueFactory<>("A_address"));// 걸럼값 불러오기

			TableColumn colAEmail = new TableColumn("이 메 일");// 컬럼명 설정
			colAEmail.setPrefWidth(90);// 컬럼 길이 설정
			colAEmail.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colAEmail.setCellValueFactory(new PropertyValueFactory<>("A_email"));// 걸럼값 불러오기

			TableColumn colABusiness = new TableColumn("업	태");// 컬럼명 설정
			colABusiness.setPrefWidth(90);// 컬럼 길이 설정
			colABusiness.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colABusiness.setCellValueFactory(new PropertyValueFactory<>("A_business"));// 걸럼값 불러오기

			TableColumn colACollect = new TableColumn("매입 미지급액");// 컬럼명 설정
			colACollect.setPrefWidth(90);// 컬럼 길이 설정
			colACollect.setStyle("-fx-allignment: CENTER");// 컬럼명 위치 설정
			colACollect.setCellValueFactory(new PropertyValueFactory<>("A_collect"));// 걸럼값 불러오기

			AccountTableView.setItems(accountDataList);// db에서 불러온값 저장

			// 설정한 컬럼 테이블에 입력
			AccountTableView.getColumns().addAll(colANo, colAName, colABusinessNumber, colARepredent,
					colARepredentPhone, colACharge, colAChargePhone, colAAddress, colAEmail, colABusiness, colACollect);

			// 거래처 전체 목록
			AccountTotalList();

			// 거래처 키 이벤트 등록
			txt_A_name.setOnKeyPressed(event -> handlerTxtNameKeyPressed(event));// 상호명
			txt_A_represent.setOnKeyPressed(event -> handlerTxtRepresentKeyPressed(event));// 대표자
			txt_A_representPhone.setOnKeyPressed(event -> handlerTxtRepresentNumberKeyPressed(event));// 대표자번호
			txt_A_charge.setOnKeyPressed(event -> handlerTxtChargeKeyPressed(event));// 담당자
			txt_A_chargePhone.setOnKeyPressed(event -> handlerTxtChargeNumberKeyPressed(event));// 담당자 번호
			txt_A_address.setOnKeyPressed(event -> handlerTxtAddressKeyPressed(event));// 주소
			txt_A_email.setOnKeyPressed(event -> handlerTxtEmailKeyPressed(event));// 이메일

			// 버튼 이벤트 등록
			btn_A_register.setOnAction(event -> handlerbtn_A_registerAction(event));// 등록 버튼
			btn_A_delete.setOnAction(event -> handlerbtn_A_deleteAction(event));// 삭제 버튼
			btn_A_update.setOnAction(event -> handlerbtn_A_updateAction(event));// 수정 버튼
			btn_A_overlapBN.setOnAction(event -> handlerBtnOverlapBNAction(event));// 사업자번호 중복확인 버튼
			btn_A_collect.setOnAction(event -> handlerbtn_A_collectAction(event));// 수금 버튼
			btn_A_clear.setOnAction(event -> handlerbtn_A_clearAction(event));// 초기화 버튼

			// 테이블 더블클릭 이벤트
			AccountTableView.setOnMouseClicked(event -> handlerAccountTableViewAction(event));

		} catch (Exception e) {
			e.printStackTrace();// 초기설정에서 문제 발생시 오류창 출력
		}
	}

	// 거래처 이름 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtNameKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txt_A_represent.requestFocus();
		}
	}

	// 거래처 대표자 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtRepresentKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_representPhone.requestFocus();
		}
	}

	// 거래처 대표자번호 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtRepresentNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_charge.requestFocus();
		}
	}

	// 거래처 담당자 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtChargeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_chargePhone.requestFocus();
		}
	}

	// 거래처 담당자 번호 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtChargeNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_address.requestFocus();
		}
	}

	// 거래처 주소 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtAddressKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_email.requestFocus();
		}
	}

	// 거래처 이메일 텍스트 필드 키 이벤트 핸들러
	public void handlerTxtEmailKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_A_business.requestFocus();
		}
	}

	// 사업자등록번호 중복 검사
	public void handlerBtnOverlapBNAction(ActionEvent event) {
		AccountTabDAO aDao = null;// 판매거래처 DAO 객체 null값 초기화

		String searchBN = "";// 사업자번호을 넣을 변수 선언
		boolean searchResult = true;// 성공 실패 여부 확인을위한 변수 선언

		try {
			// 사업자번호 텍스트필드에 값을 선언된 변수에 넣는다
			searchBN = txt_A_businessNumber.getText().trim();
			// 판매거래처 DAO을 선언된 인스턴스에 넣는다
			aDao = new AccountTabDAO();
			// DAO에서 호출한 메소드에 입력된 사업자번호를 넣고 결과값 가져오기
			searchResult = (boolean) aDao.getOverlapBN(searchBN);

			// 결과가 ture 이고 공란이 아닐시 실행
			if (!searchResult && !searchBN.equals("")) {
				txt_A_businessNumber.setDisable(true);// 사업자번호 텍스트필드 수정불가

				// 사업자번호 사용가능하다는 정보창 출력
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 있습니다.");
				alert.setContentText("상호명를 입력하세요.");
				alert.showAndWait();

				// 필드 설정
				txt_A_name.setEditable(true);// 상호명 텍스트필드 활성화
				txt_A_represent.setEditable(true);// 대표자 텍스트필드 활성화
				txt_A_representPhone.setEditable(true);// 대표자번호 텍스트필드 활성화
				txt_A_charge.setEditable(true);// 담당자 텍스트필드 활성화
				txt_A_chargePhone.setEditable(true);// 담당자번호 텍스트필드 활성화
				txt_A_address.setEditable(true);// 주소 텍스트필드 활성화
				txt_A_email.setEditable(true);// 이메일 텍스트필드 활성화
				txt_A_business.setEditable(true);// 업테 텍스트필드 활성화
				txt_A_name.requestFocus();// 상호명 텍스트 필드로 커서 이동

				// 버튼 설정
				btn_A_register.setDisable(false);// 등록 버튼 활성화
				btn_A_overlapBN.setDisable(true);// 사업자번호 중복검사 버튼 비활성화

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
				txt_A_businessNumber.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사업자등록번호 중복 검사");
				alert.setHeaderText(searchBN + "를 사용할 수 없습니다.");
				alert.setContentText("사업자등록번호를 다른것으로 입력하세요.");
				alert.showAndWait();

				// 사업자 번호 텍스트 필드로 커서 이동
				txt_A_businessNumber.requestFocus();
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

	// 판매 거래처 테이블뷰 더블클릭 선택 이벤트 핸들러
	public void handlerAccountTableViewAction(MouseEvent event) {

		// 더블클릭시 실행
		if (event.getClickCount() == 2) {
			try {
				// 테이블에서 선택된값 인스턴스에 저장
				selectAccount = AccountTableView.getSelectionModel().getSelectedItems();
				// 테이블에서 선택된번호 인스턴스에 저장
				selectedIndex = selectAccount.get(0).getA_no();

				// 인스턴스에 저장된값 변수에 저장
				String selectedA_name = selectAccount.get(0).getA_name();// 상호명
				String selectedA_businessNumber = selectAccount.get(0).getA_businessNumber();// 사업자번호
				String selectedA_represent = selectAccount.get(0).getA_represent();// 대표자
				String selectedA_representPhone = selectAccount.get(0).getA_representPhone();// 대표자명
				String selectedA_charge = selectAccount.get(0).getA_charge();// 담당자
				String selectedA_chargePhone = selectAccount.get(0).getA_chargePhone();// 담당자번호
				String selectedA_address = selectAccount.get(0).getA_address();// 주소
				String selectedA_email = selectAccount.get(0).getA_email();// 이메일
				String selectedA_business = selectAccount.get(0).getA_business();// 업태

				// 텍스트 필드에 변수값 넣기
				txt_A_name.setText(selectedA_name);// 상호명
				txt_A_businessNumber.setText(selectedA_businessNumber);// 사업자번호
				txt_A_represent.setText(selectedA_represent);// 대표자
				txt_A_representPhone.setText(selectedA_representPhone);// 대표자명
				txt_A_charge.setText(selectedA_charge);// 담당자
				txt_A_chargePhone.setText(selectedA_chargePhone);// 담당자번호
				txt_A_address.setText(selectedA_address);// 주소
				txt_A_email.setText(selectedA_email);// 이메일
				txt_A_business.setText(selectedA_business);// 업태

				// 버튼 설정
				btn_A_update.setDisable(false);// 수정 버튼 활성화
				btn_A_delete.setDisable(false);// 삭제 버튼 활성화
				btn_A_register.setDisable(true);// 등록 버튼 비활성화
				btn_A_overlapBN.setDisable(true);// 중복 버튼 비활성화
				btn_A_collect.setDisable(false);// 수금 버튼 활성화

				// 텍스트 필드 설정
				txt_A_collect.setDisable(false);// 수금 필드 활성화
				txt_A_businessNumber.setEditable(false);// 사업자번호 수정불가
				txt_A_name.setEditable(true);// 상호명 수정가능
				txt_A_represent.setEditable(true);// 대표자 수정가능
				txt_A_representPhone.setEditable(true);// 대표자번호 수정가능
				txt_A_charge.setEditable(true);// 담당자 수정가능
				txt_A_chargePhone.setEditable(true);// 담당자번호 수정가능
				txt_A_address.setEditable(true);// 주소 수정가능
				txt_A_email.setEditable(true);// 이메일 수정가능
				txt_A_business.setEditable(true);// 업태 수정가능

				// 더블클릭 이벤트 실행중 오류시 오류 내용 출력
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 거래처 전체 리스트
	public void AccountTotalList() throws Exception {
		// 데이터리스트 값 초기화
		accountDataList.removeAll(accountDataList);

		// 인스턴스 선언
		AccountTabDAO aDao = new AccountTabDAO();

		// VO객체 null값 초기화
		AccountVO aVo = null;

		// ARrayList 에 VO입력
		ArrayList<AccountVO> list;

		// list 에 DB에서 가져온 데이터 저장
		list = aDao.getaccountVOTotalList();
		// DB에서 가져온 정보사이즈를 변수에 저장
		int rowCount = list.size();

		// 데이터리스트에 데이터를 저장하기 위한 for문
		for (int index = 0; index < rowCount; index++) {
			aVo = list.get(index);
			accountDataList.add(aVo);
		}
	}

	// 판매 거래처 등록 이벤트 핸들러
	public void handlerbtn_A_registerAction(ActionEvent event) {
		Alert alert = null; // Alert 객체 null 초기화

		// 텍스트 필드값이 null일때 경고창 출력
		// 상호명
		if (txt_A_name.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("상호명를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 대표자
		} else if (txt_A_represent.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 대표자번호
		} else if (txt_A_representPhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자번호를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 주소
		} else if (txt_A_address.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("주소를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 업태
		} else if (txt_A_business.getText().trim().equals("")) {
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
			accountDataList.removeAll(accountDataList);

			// 객체 null값 초가화
			AccountVO aVo = null;
			AccountTabDAO aDao = null;

			// 객체에 텍스트필드값 저장
			aVo = new AccountVO(selectedIndex, txt_A_name.getText().trim(), txt_A_businessNumber.getText().trim(),
					txt_A_represent.getText().trim(), txt_A_representPhone.getText().trim(),
					txt_A_charge.getText().trim(), txt_A_chargePhone.getText().trim(), txt_A_address.getText().trim(),
					txt_A_email.getText().trim(), txt_A_business.getText().trim());
			// 객체에 AccountTabDAO 저장
			aDao = new AccountTabDAO();
			// getAccountRegiste 에 텍스트필드값이 저장된 객체 입력
			aDao.getAccountRegiste(aVo);

			// 반화된값이 null이 아닐경우
			if (aDao != null) {

				// 전체리스트 메소드 호출
				AccountTotalList();

				// 거래처등록 성공 정보창 출력
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 입력");
				alert.setHeaderText(txt_A_name.getText() + " 거래처가 성공적으로 추가되었습니다..");
				alert.setContentText("다음 거래처를 입력하세요");
				alert.showAndWait();

				// 초기화 핸들러 호출
				handlerbtn_A_clearAction(event);
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
	public void handlerbtn_A_updateAction(ActionEvent event) {
		Alert alert = null; // Alert 객체 null 초기화

		// 텍스트 필드값이 null일때 경고창 출력
		// 상호명
		if (txt_A_name.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("상호명를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 대표자
		} else if (txt_A_represent.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 대표자번호
		} else if (txt_A_representPhone.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("대표자번호를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 주소
		} else if (txt_A_address.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처등록");
			alert.setHeaderText("주소를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
			// 업태
		} else if (txt_A_business.getText().trim().equals("")) {
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
			AccountTabDAO aDao = new AccountTabDAO();
			// 객체에 텍스트필드값 저장
			sucess = aDao.getaccountUpdate(selectedIndex, txt_A_name.getText().trim(),
					txt_A_businessNumber.getText().trim(), txt_A_represent.getText().trim(),
					txt_A_representPhone.getText().trim(), txt_A_charge.getText().trim(),
					txt_A_chargePhone.getText().trim(), txt_A_address.getText().trim(), txt_A_email.getText().trim(),
					txt_A_business.getText().trim());
			// 반환된 값이 true일 경우
			if (sucess) {
				// 데이터리스트 값 초기화
				accountDataList.removeAll(accountDataList);
				// 전체리스트 메소드 호출
				AccountTotalList();

				// 초기화 이벤트 핸들러 호출
				handlerbtn_A_clearAction(event);
			}
			// 수정 중 오류시 오류 내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 초기화 이벤트핸들러
	public void handlerbtn_A_clearAction(ActionEvent event) {

		// 텍스트 필드 클리어
		txt_A_businessNumber.clear();// 사업자번호
		txt_A_name.clear();// 상호명
		txt_A_represent.clear();// 대표자
		txt_A_representPhone.clear();// 대표자명
		txt_A_charge.clear();// 담당자
		txt_A_chargePhone.clear();// 담당자명
		txt_A_address.clear();// 주소
		txt_A_email.clear();// 이메일
		txt_A_business.clear();// 업태
		txt_A_collect.clear();// 수금액

		// 사업자번호텍스트필드에 커서 이동
		txt_A_businessNumber.requestFocus();

		// 버튼 설정
		btn_A_collect.setDisable(true);// 수금버튼 비활성화
		btn_A_register.setDisable(true);// 등록버튼 비활성화
		btn_A_update.setDisable(true);// 수정버튼 비활성화
		btn_A_delete.setDisable(true);// 삭제버튼 비활성화
		btn_A_overlapBN.setDisable(false);// 사업자번호 중복검사 버튼 활성화

		// 텍스트 필드 초기화 설정
		txt_A_businessNumber.setDisable(false);// 사업자번호 활성화
		txt_A_businessNumber.setEditable(true);// 사업자 번호 수정 가능
		txt_A_name.setEditable(false);// 거래처 이름 수정불가
		txt_A_represent.setEditable(false);// 대표자 수정불가
		txt_A_representPhone.setEditable(false);// 대표자번호 수정불가
		txt_A_charge.setEditable(false);// 담당자 수정불가
		txt_A_chargePhone.setEditable(false);// 담당자 번호 수정불가
		txt_A_address.setEditable(false);// 주소 수정불가
		txt_A_email.setEditable(false);// 이메일 수정불가
		txt_A_business.setEditable(false);// 업태 수정불가

	}

	// 거래처 삭제 이벤트 핸들러
	public void handlerbtn_A_deleteAction(ActionEvent event) {
		try {
			// 결과 값 반환을 저장하기위한 변수 선언
			boolean sucess;

			// 인스턴스 선언
			AccountTabDAO aDao = new AccountTabDAO();
			// 결과값 변수에 저장
			sucess = aDao.getaccountDelete(selectedIndex);
			// 결과가 true일 경우 실행
			if (sucess) {
				// 데이터리스트 값 초기화
				accountDataList.removeAll(accountDataList);
				// 전체리스트 호출
				AccountTotalList();

				// 버튼 설정
				btn_A_register.setDisable(false);
				btn_A_update.setDisable(true);
				btn_A_delete.setDisable(true);
			}
			// 거래처 삭제중 오류시 오류내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수금 버튼 이벤트 핸들러
	public void handlerbtn_A_collectAction(ActionEvent event) {
		try {
			// 결과 값 반환을 저장하기위한 변수 선언
			boolean sucess;

			// 인스턴스 선언
			AccountTabDAO aDao = new AccountTabDAO();
			// 결과값 변수에 저장
			sucess = aDao.getCollect(txt_A_name.getText().trim(), txt_A_business.getText().trim(),
					txt_A_collect.getText().trim(), selectedIndex);
			// 결과가 true일 경우 실행
			if (sucess) {

				// 초기화 이벤트 핸들러 호출
				handlerbtn_A_clearAction(event);
			}
			// 거래처 수금중 오류시 오류내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 거래처 미수금액 변경
		try {

			boolean sucess;// 거래처 삭제 성공여부 변수선언

			// 인스턴스 선언
			AccountTabDAO aDao = new AccountTabDAO();
			// 객체에 텍스트필드값 저장
			sucess = aDao.getaccountUpdateCollect(selectedIndex, txt_A_collect.getText().trim());
			// 반환된 값이 true일 경우
			if (sucess) {
				// 데이터리스트 값 초기화
				accountDataList.removeAll(accountDataList);
				// 전체리스트 메소드 호출
				AccountTotalList();

			}
			// 미수금액 변경 중 오류시 오류 내용 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
