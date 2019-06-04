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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.AccountVO;
import model.BuyVO;
import model.DealVO;
import model.ImportionVO;

public class BuyTabController implements Initializable {
	@FXML
	private ComboBox<String> cbx_b_importion; // 매입 거래처 콤보박스
	@FXML
	private String b_type; // 소 돼지 분류
	@FXML
	private ComboBox<String> cbx_b_origin; // 원산지 콤보박스
	@FXML
	private ComboBox<String> cbx_b_brand; // 브랜드 콤보박스
	@FXML
	private ComboBox<String> cbx_b_part; // 부위 콤보박스
	@FXML
	private TextField txt_b_buydate; // 주문날짜 텍스트필드
	@FXML
	private TextField txt_b_code; // 상품코드 텍스트필드
	@FXML
	private TextField txt_b_number; // 상품 수량 텍스트필드
	@FXML
	private TextField txt_b_kg; // 상품 중량 텍스트필드
	@FXML
	private TextField txt_b_cost; // 상품 단가 텍스트필드
	@FXML
	private TextField txt_b_totalMoney; // 상품 총 가격 텍스트필드
	@FXML
	private ComboBox<String> cbx_b_importion2; // 거래처 콤보박스
	@FXML
	private ToggleGroup productType; // 분류의 소 돼지 버튼
	@FXML
	private RadioButton typeCow; // 소 버튼
	@FXML
	private RadioButton typePig; // 돼지 버튼
	@FXML
	private Button btn_b_order; // 입고 버튼
	@FXML
	private Label txt_i_address; // 매입처 주소 라벨
	@FXML
	private Label txt_i_business; // 매입처 업태 라벨
	@FXML
	private Label txt_i_representPhone; // 매입처 대표자 번호 라벨
	@FXML
	private TableView<BuyVO> BuyTableView = new TableView<>(); // 구매 전표 테이블
	@FXML
	private Label txt_a_address; // 판매처 주소 라벨
	@FXML
	private Label txt_a_business; // 판매처 업태 라벨
	@FXML
	private Label txt_a_representPhone; // 판매처 대표자 번호 라벨
	@FXML
	private ComboBox<String> cbx_d_account; // 판매 거래처 콤보박스
	@FXML
	private int i_no; // 매입거래처일련번호
	@FXML
	private int p_no; // 상품일련번호
	@FXML
	private int s_no; // 재고 일련번호
	@FXML
	private int b_no; // 입고 일련번호
	@FXML
	private String p_origin; // 상품 원산지
	@FXML
	private String p_brand; // 상품 브랜드
	@FXML
	private String p_part; // 상품 부위
	@FXML
	private String s_state; // 상태
	@FXML
	private TableView<DealVO> DealTableView = new TableView<>(); // 판매 테이블 뷰

	static ObservableList<BuyVO> buyDataList = FXCollections.observableArrayList();
	ObservableList<BuyVO> selectBuy = null; // 매입거래처 테이블에서 선택한 정보 저장

	static ObservableList<DealVO> dealDataList = FXCollections.observableArrayList();
	ObservableList<DealVO> selectdeal = null; // 재고 테이블에서 선택한 정보 저장

	int selectedBuyIndex; // 테이블에 선택한 상품 정보 인덱스저장

	String selectedOrigin = ""; // 콤보박스 원산지에서 선택한 글자 담을 공간
	String selectedBrand = ""; // 콤보박스 브랜드에서 선택한 글자 담을 공간
	String selectedPart = ""; // 콤보박스 부위에서 선택한 글자 담을 공간

	// 초기설정
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){

		try {

			// 수량 숫자만입력가능
			txt_b_number.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_b_number.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 중량 숫자만 입력가능
			txt_b_kg.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_b_kg.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 단가 숫자만 입력가능
			txt_b_cost.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						txt_b_cost.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			// 콤보박스 브랜드 및 부위 비활성화
			cbx_b_brand.setDisable(true);
			cbx_b_part.setDisable(true);

			// 입고 테이블 뷰 컬럼이름 설정
			TableColumn col_B_No = new TableColumn("입고번호"); // 컬럼명설정
			col_B_No.setPrefWidth(70); // 컬럼 크기 설정
			col_B_No.setStyle("-fx-allignment:CENTER"); // 컬럼명 위치 설정
			col_B_No.setCellValueFactory(new PropertyValueFactory<>("b_no"));// 컬럼값불러오기
			TableColumn col_B_buyDate = new TableColumn("주문일자");// 컬럼명설정
			col_B_buyDate.setPrefWidth(120);// 컬럼 크기 설정
			col_B_buyDate.setStyle("-fx-allignment:CENTER");// 컬럼값불러오기
			col_B_buyDate.setCellValueFactory(new PropertyValueFactory<>("b_buyDate"));// 컬럼값불러오기
			TableColumn col_B_date = new TableColumn("거래일자"); // 컬럼명설정
			col_B_date.setPrefWidth(130); // 컬럼 크기 설정
			col_B_date.setStyle("-fx-allignment:CENTER"); // 컬럼명 위치 설정
			col_B_date.setCellValueFactory(new PropertyValueFactory<>("b_date")); // 컬럼값불러오기

			TableColumn col_I_name = new TableColumn("상호명");
			col_I_name.setPrefWidth(100);
			col_I_name.setStyle("-fx-allignment:CENTER");
			col_I_name.setCellValueFactory(new PropertyValueFactory<>("i_name"));

			TableColumn col_B_code = new TableColumn("식별번호");
			col_B_code.setPrefWidth(130);
			col_B_code.setStyle("-fx-allignment:CENTER");
			col_B_code.setCellValueFactory(new PropertyValueFactory<>("b_code"));

			TableColumn col_P_type = new TableColumn("분류");
			col_P_type.setPrefWidth(50);
			col_P_type.setStyle("-fx-allignment:CENTER");
			col_P_type.setCellValueFactory(new PropertyValueFactory<>("p_type"));

			TableColumn col_P_origin = new TableColumn("원산지");
			col_P_origin.setPrefWidth(60);
			col_P_origin.setStyle("-fx-allignment:CENTER");
			col_P_origin.setCellValueFactory(new PropertyValueFactory<>("p_origin"));

			TableColumn col_P_brand = new TableColumn("브랜드");
			col_P_brand.setPrefWidth(80);
			col_P_brand.setStyle("-fx-allignment:CENTER");
			col_P_brand.setCellValueFactory(new PropertyValueFactory<>("p_brand"));

			TableColumn col_P_part = new TableColumn("부위");
			col_P_part.setPrefWidth(90);
			col_P_part.setStyle("-fx-allignment:CENTER");
			col_P_part.setCellValueFactory(new PropertyValueFactory<>("p_part"));

			TableColumn col_B_number = new TableColumn("수량");
			col_B_number.setPrefWidth(70);
			col_B_number.setStyle("-fx-allignment:CENTER");
			col_B_number.setCellValueFactory(new PropertyValueFactory<>("b_number"));

			TableColumn col_B_kg = new TableColumn("중량");
			col_B_kg.setPrefWidth(70);
			col_B_kg.setStyle("-fx-allignment:CENTER");
			col_B_kg.setCellValueFactory(new PropertyValueFactory<>("b_kg"));

			TableColumn col_B_cost = new TableColumn("단가");
			col_B_cost.setPrefWidth(70);
			col_B_cost.setStyle("-fx-allignment:CENTER");
			col_B_cost.setCellValueFactory(new PropertyValueFactory<>("b_cost"));

			TableColumn col_B_totalMoney = new TableColumn("총 금액");
			col_B_totalMoney.setPrefWidth(110);
			col_B_totalMoney.setStyle("-fx-allignment:CENTER");
			col_B_totalMoney.setCellValueFactory(new PropertyValueFactory<>("b_totalMoney"));

			BuyTableView.setItems(buyDataList); // db에서 불러온값 저장
			// 설정한 컬럼 테이블에 입력
			BuyTableView.getColumns().addAll(col_B_No, col_B_buyDate, col_B_date, col_I_name, col_B_code, col_P_type,
					col_P_origin, col_P_brand, col_P_part, col_B_number, col_B_kg, col_B_cost, col_B_totalMoney);

			// 매입 전체 목록
			BuyTotalList();

			// 출고 테이블 뷰 컬럼이름 설정
			TableColumn col_D_No = new TableColumn("입고번호");// 컬럼명 설정
			col_D_No.setPrefWidth(70);// 컬럼 길이 설정
			col_D_No.setStyle("-fx-allignment:CENTER");// 컬럼명 위치 설정
			col_D_No.setCellValueFactory(new PropertyValueFactory<>("d_no"));// 걸럼값 불러오기
			TableColumn col_D_dealDate = new TableColumn("주문일자"); // 컬럼명 설정
			col_D_dealDate.setPrefWidth(120); // 컬럼 길이 설정
			col_D_dealDate.setStyle("-fx-allignment:CENTER"); // 컬럼명 위치 설정
			col_D_dealDate.setCellValueFactory(new PropertyValueFactory<>("d_dealDate")); // 걸럼값 불러오기

			TableColumn col_D_date = new TableColumn("거래일자");
			col_D_date.setPrefWidth(130);
			col_D_date.setStyle("-fx-allignment:CENTER");
			col_D_date.setCellValueFactory(new PropertyValueFactory<>("d_date"));

			TableColumn col_A_name = new TableColumn("상호명");
			col_A_name.setPrefWidth(100);
			col_A_name.setStyle("-fx-allignment:CENTER");
			col_A_name.setCellValueFactory(new PropertyValueFactory<>("a_name"));

			TableColumn col_B_code1 = new TableColumn("식별번호");
			col_B_code1.setPrefWidth(130);
			col_B_code1.setStyle("-fx-allignment:CENTER");
			col_B_code1.setCellValueFactory(new PropertyValueFactory<>("b_code"));

			TableColumn col_P_type1 = new TableColumn("분류");
			col_P_type1.setPrefWidth(50);
			col_P_type1.setStyle("-fx-allignment:CENTER");
			col_P_type1.setCellValueFactory(new PropertyValueFactory<>("p_type"));

			TableColumn col_P_origin1 = new TableColumn("원산지");
			col_P_origin1.setPrefWidth(60);
			col_P_origin1.setStyle("-fx-allignment:CENTER");
			col_P_origin1.setCellValueFactory(new PropertyValueFactory<>("p_origin"));

			TableColumn col_P_brand1 = new TableColumn("브랜드");
			col_P_brand1.setPrefWidth(80);
			col_P_brand1.setStyle("-fx-allignment:CENTER");
			col_P_brand1.setCellValueFactory(new PropertyValueFactory<>("p_brand"));

			TableColumn col_P_part1 = new TableColumn("부위");
			col_P_part1.setPrefWidth(90);
			col_P_part1.setStyle("-fx-allignment:CENTER");
			col_P_part1.setCellValueFactory(new PropertyValueFactory<>("p_part"));

			TableColumn col_D_number = new TableColumn("수량");
			col_D_number.setPrefWidth(70);
			col_D_number.setStyle("-fx-allignment:CENTER");
			col_D_number.setCellValueFactory(new PropertyValueFactory<>("d_number"));

			TableColumn col_D_kg = new TableColumn("중량");
			col_D_kg.setPrefWidth(70);
			col_D_kg.setStyle("-fx-allignment:CENTER");
			col_D_kg.setCellValueFactory(new PropertyValueFactory<>("d_kg"));

			TableColumn col_D_cost = new TableColumn("단가");
			col_D_cost.setPrefWidth(70);
			col_D_cost.setStyle("-fx-allignment:CENTER");
			col_D_cost.setCellValueFactory(new PropertyValueFactory<>("d_cost"));

			TableColumn col_D_totalMoney1 = new TableColumn("총 금액");
			col_D_totalMoney1.setPrefWidth(120);
			col_D_totalMoney1.setStyle("-fx-allignment:CENTER");
			col_D_totalMoney1.setCellValueFactory(new PropertyValueFactory<>("d_totalMoney"));

			DealTableView.setItems(dealDataList); // db에불러온값저장
			// 설정한 컬럼 테이블에 입력
			DealTableView.getColumns().addAll(col_D_No, col_D_dealDate, col_D_date, col_A_name, col_B_code1,
					col_P_type1, col_P_origin1, col_P_brand1, col_P_part1, col_D_number, col_D_kg, col_D_cost,
					col_D_totalMoney1);

			// 출고전체목록
			DealTotalList();

			// 입고 텍스트필드 이벤트 등록
			txt_b_buydate.setOnKeyPressed(event -> handlerTxt_b_buydateKeyPressed(event)); // 주문날짜
			txt_b_code.setOnKeyPressed(event -> handlerTxt_b_codeKeyPressed(event)); // 상품식별번호
			txt_b_number.setOnKeyPressed(event -> handlerTxt_b_numberKeyPressed(event)); // 입고 수량
			txt_b_kg.setOnKeyPressed(event -> handlerTxt_b_kgKeyPressed(event)); // 입고중량
			txt_b_cost.setOnKeyPressed(event -> handlerTxt_b_costKeyPressed(event)); // 입고단가

			// 라디오버튼 소 또는 돼지 선택 이벤트
			typeCow.setOnAction(event -> handlerTypeCowAction(event)); // 소 버튼
			typePig.setOnAction(event -> handlerTypePigAction(event)); // 돼지 버튼

			// 콤보박스 이벤트
			cbx_b_origin.setOnAction(event -> handlerCbx_b_originAction(event)); // 상품 원산지 콤보박스
			cbx_b_brand.setOnAction(event -> handlerCbx_b_brandAction(event));// 상품 브랜드 콤보박스
			cbx_b_part.setOnAction(event -> handlerCbx_b_partAction(event)); // 상품 부위 콤보박스
			cbx_b_importion.setOnAction(event -> handlerCbx_b_importionAction(event)); // 좌측에 있는 매입 거래처 콤보박스
			cbx_b_importion2.setOnAction(event -> handlerCbx_b_importion2Action(event));// 우측에 있는 매입 거래처 콤보박스
			cbx_d_account.setOnAction(event -> handlerCbx_d_accountAction(event));// 판매 거래처 콤보박스

			// 상픔 입고 등록 버튼
			btn_b_order.setOnAction(event -> handlerBtn_b_orderAction(event));

		} catch (Exception e) {
			e.printStackTrace(); // 초기설정에서 문제 발생시 오류창 출력
		}

	}

	// 주문날짜 텍스트 필드 키 이벤트
	public void handlerTxt_b_buydateKeyPressed(KeyEvent event) {
		// 엔터시 다음 택스트 필드 이동
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_code.requestFocus();
		}
	}

	// 상품 식별코드 텍스트 필드 키 이벤트
	public void handlerTxt_b_codeKeyPressed(KeyEvent event) {
		// 엔터시 다음 택스트 필드 이동
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_number.requestFocus();
		}
	}

	// 입고 수량 텍스트 필드 키 이벤트
	public void handlerTxt_b_numberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_kg.requestFocus();
		}
	}

	// 입고중량 텍스트 필드 키 이벤트
	public void handlerTxt_b_kgKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_cost.requestFocus();
		}
	}

	// 입고 단가 텍스트 필드 키 이벤트
	public void handlerTxt_b_costKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btn_b_order.requestFocus();
		}
	}

	// 판매 거래처 콤보박스
	public void handlerCbx_d_accountAction(ActionEvent event) {

		try {
			// 콤보박스에서 선택한 판매거래처를 selectedNameIndex에 글자로 저장
			String selectedNameIndex = cbx_d_account.getSelectionModel().getSelectedItem().toString();

			AccountTabDAO adao = new AccountTabDAO();
			// 상호명을 가져오는 메소드를 list에 저장
			ArrayList<AccountVO> list = new ArrayList<>();
			// 상호명을 가져오는 메소드를 list에 저장
			ArrayList<DealVO> list2 = new ArrayList<>();
			DealVO dvo = new DealVO();

			AccountVO avo = new AccountVO();
			// selectedNameIndex에 저장된 상호명 getAcccountInfo에 넣어 만든 쿼리문에 해당하는 정보 가져와서 설정
			list = adao.getAcccountInfo(selectedNameIndex);

			txt_a_address.setText(list.get(0).getA_address());
			txt_a_business.setText(list.get(0).getA_business());
			txt_a_representPhone.setText(list.get(0).getA_representPhone());

			list2 = adao.getSelectTotalList(selectedNameIndex);
			// 테이블 초기화를 시켰다가 저장된 테이터 다 넣어주기
			dealDataList.removeAll(dealDataList);
			for (int index = 0; index < list2.size(); index++) {
				dvo = list2.get(index);
				dealDataList.add(dvo);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	// 판매처 이름 가져오기
	public void accountName() {
		// ArrayList 에 VO 넣기
		ArrayList<AccountVO> list = new ArrayList();

		// observablelist에 string으로 AccountDataList에 담겠다
		ObservableList<String> AccountDataList = FXCollections.observableArrayList();
		AccountVO aVo = null;

		try {

			AccountTabDAO adao = new AccountTabDAO();
			// adao 의 getaccountName에 해당되는 것을 list에 담는다
			list = adao.getaccountName();

			for (int i = 0; i < list.size(); i++) {
				aVo = list.get(i);
				AccountDataList.add(aVo.getA_name());
				cbx_d_account.setItems(AccountDataList); // 판매거래처의 콤보박스에 저장된 상호명들을 담는다
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 원산지 선택 콤보박스
	public void handlerCbx_b_originAction(ActionEvent event) {
		selectedOrigin = cbx_b_origin.getValue().toString(); // 콤보박스의 선택된 값selectedOrigin에 저장

		if (!(selectedOrigin.equals(""))) { // 저장값이 공백이 아니라면
			cbx_b_brand.setDisable(false); // 상품 브랜드 콤보박스 활성화
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록 실패");
			alert.setHeaderText("원산지를 체크하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
	}

	// 브랜드 콤보박스 선택 이벤트
	public void handlerCbx_b_brandAction(ActionEvent event) {
		selectedBrand = cbx_b_brand.getValue().toString(); // 콤보박스 선택된 값 selectedBrand에 저장
		if (!(cbx_b_brand.equals(""))) { // 공백이 아니라면
			cbx_b_part.setDisable(false); // 상품 부위 콤보박스 활성화
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록 실패");
			alert.setHeaderText("브랜드를를 체크하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
	}

	// 부위 콤보박스 선택 이벤트
	public void handlerCbx_b_partAction(ActionEvent event) {
		// 부위콤보박스에 선택된 값 selectedPart저장
		selectedPart = cbx_b_part.getValue().toString();
		if (!(cbx_b_part.equals(""))) {
			BuyDAO bdao = new BuyDAO();
			try {
				// 선택된 , 원산지 , 브랜드 , 부위로 상품번호 알아오기
				selectedBuyIndex = bdao.getProductNumber(selectedOrigin, selectedBrand, selectedPart);
				p_no = selectedBuyIndex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록 실패");
			alert.setHeaderText("브랜드를를 체크하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
	}

	// 테이블뷰 위에있는 거래처선택 콤보박스
	public void handlerCbx_b_importion2Action(ActionEvent event) {
		try {
			// 콤보박스에 선택된 값 selectedNameIndex에 저장
			String selectedNameIndex = cbx_b_importion2.getSelectionModel().getSelectedItem().toString();
			// 인스턴스 선언
			ImportionTabDAO idao = new ImportionTabDAO();

			ArrayList<ImportionVO> list = new ArrayList<>();

			ArrayList<BuyVO> list2 = new ArrayList<>();
			BuyVO bvo = new BuyVO();

			ImportionVO ivo = new ImportionVO();
			list = idao.getImportionInfo(selectedNameIndex);

			txt_i_address.setText(list.get(0).getI_address());
			txt_i_business.setText(list.get(0).getI_business());
			txt_i_representPhone.setText(list.get(0).getI_representPhone());

			list2 = idao.getselectTotalList(selectedNameIndex);
			// 데이터리스트 값 초기화
			buyDataList.removeAll(buyDataList);
			// 데이터리스트에 데이터를 저장하기 위한 for문
			for (int index = 0; index < list2.size(); index++) {
				bvo = list2.get(index);
				buyDataList.add(bvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래처 상호명 가져오기
	public void importionName() {

		ArrayList<ImportionVO> list = new ArrayList();
		ObservableList<String> ImportionDataList = FXCollections.observableArrayList();
		ImportionVO iVo = null;

		try {
			ImportionTabDAO idao = new ImportionTabDAO();

			// 직원명을 가져오는 메소드를 list에 저장
			list = idao.getimportionName();

			for (int i = 0; i < list.size(); i++) {
				iVo = list.get(i);
				ImportionDataList.add(iVo.getI_name());
				cbx_b_importion.setItems(ImportionDataList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerCbx_b_importionAction(ActionEvent event) {
		// 선택한 상호명 인덱스값

		String selectedNameIndex = cbx_b_importion.getSelectionModel().getSelectedItem();

		ImportionTabDAO importion = new ImportionTabDAO();

		try {

			i_no = importion.getImportionNum(selectedNameIndex);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 우측 콤보 박스 거래처 상호명 가져오기
	public void importionName2() {

		ArrayList<ImportionVO> list = new ArrayList();
		ObservableList<String> ImportionDataList = FXCollections.observableArrayList();
		ImportionVO iVo = null;

		try {
			ImportionTabDAO idao = new ImportionTabDAO();

			// 상호명을 가져오는 메소드를 list에 저장
			list = idao.getimportionName();

			for (int i = 0; i < list.size(); i++) {
				iVo = list.get(i);
				ImportionDataList.add(iVo.getI_name());
				cbx_b_importion2.setItems(ImportionDataList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 브랜드 콤보박스의 상품 브랜드명 가져오기
	public void productBrand() {

		BuyDAO bdao = new BuyDAO();
		ArrayList productbrand = new ArrayList<>();

		try {
			productbrand = bdao.getProductInfo();
			cbx_b_brand.setItems(FXCollections.observableArrayList(productbrand));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 콤보박스 분류 소 클릭시 소 부위 가져오기
	public void productcowPart() {

		BuyDAO bdao = new BuyDAO();
		ArrayList productPart = new ArrayList<>();

		try {
			productPart = bdao.getcowPart();
			cbx_b_part.setItems(FXCollections.observableArrayList(productPart));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 상품 원산지 가져오기
	public void productOrigin() {

		BuyDAO bdao = new BuyDAO();
		ArrayList productOrigin = new ArrayList<>();

		try {
			productOrigin = bdao.getOrigin();
			cbx_b_origin.setItems(FXCollections.observableArrayList(productOrigin));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 콤보박스 분류 돼지 클릭시 돼지 부위 가져오기
	public void productpigPart() {

		BuyDAO bdao = new BuyDAO();
		ArrayList productPart = new ArrayList<>();

		try {
			productPart = bdao.getpigPart();
			cbx_b_part.setItems(FXCollections.observableArrayList(productPart));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 라디오 버튼 돼지 클릭시 '돼지'텍스트 가져오고 부위 호출
	public void handlerTypePigAction(ActionEvent event) {
		productpigPart();
		b_type = typePig.getText();
	}

	// 라디오 버튼 소 클릭시 '소'텍스트가져오고 부위 호출
	public void handlerTypeCowAction(ActionEvent event) {
		productcowPart();
		b_type = typeCow.getText();
	}

	// 입고 전체 리스트
	public void BuyTotalList() throws Exception {

		buyDataList.removeAll(buyDataList);
		BuyDAO bDao = new BuyDAO();
		BuyVO bVo = null;
		ArrayList<String> title;
		ArrayList<BuyVO> list;

		list = bDao.getbuyTotalList();

		int rowCount = list.size();
		for (int index = 0; index < rowCount; index++) {
			bVo = list.get(index);
			buyDataList.add(bVo);
		}

	}

	public void stock() {
		try {

			boolean sucess;

			BuyDAO bDao = new BuyDAO();
			sucess = bDao.getStock(txt_b_number.getText().trim(), txt_b_kg.getText().trim(),
					txt_b_cost.getText().trim(), p_no, b_no);
			if (sucess) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 출고전체리스트
	public void DealTotalList() {

		dealDataList.removeAll(dealDataList);
		DealDAO dDao = new DealDAO();
		DealVO dVo = null;
		ArrayList<String> title;
		ArrayList<DealVO> list;

		list = dDao.getDealVOTotalList();

		int rowCount = list.size();
		for (int index = 0; index < rowCount; index++) {
			dVo = list.get(index);
			dealDataList.add(dVo);
		}
	}

	// 주문버튼 클릭시 저장
	public void handlerBtn_b_orderAction(ActionEvent event) {

		// 빈칸시 경고창 실행
		Alert alert = null; // 알럿 객체 null 초기화

		if (txt_b_buydate.getText().trim().equals("")) {
			txt_b_buydate.clear();
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("매입 내역 등록");
			alert.setHeaderText("주문일자를 입력해주세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_b_code.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("매입 내역 등록");
			alert.setHeaderText("식별 번호를 입력해주세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_b_number.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("매입 내역 등록");
			alert.setHeaderText("수량을 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_b_kg.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("매입 내역 등록");
			alert.setHeaderText("중량을 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_b_cost.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록");
			alert.setHeaderText("단가를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
		if (alert != null) {
			return;
		}
		// 주문 버튼 클릭시 재고 테이블에도 저장되게 메소드 호출
		stock();
		try {

			BuyVO bvo = null;
			BuyDAO bdao = null;

			bvo = new BuyVO(txt_b_buydate.getText().trim(), i_no, cbx_b_importion.getValue().toString(),
					txt_b_code.getText().trim(), p_no, b_type, selectedOrigin, selectedBrand, selectedPart,
					Integer.parseInt(txt_b_number.getText().trim()), Double.parseDouble(txt_b_kg.getText().trim()),
					Integer.parseInt(txt_b_cost.getText().trim()));

			bdao = new BuyDAO();
			bdao.getBuyRegiste(bvo);

			if (bdao != null) {
				BuyTotalList();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상품 등록");
				alert.setHeaderText("상품이 추가되었습니다");
				alert.setContentText("다음 상품을 입력하세요");
				alert.showAndWait();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 미입금액 추가
		try {
			// 결과값 저장을 위한 변수선언
			boolean sucess;
			// 인스턴스 선언
			ImportionTabDAO iDAO = new ImportionTabDAO();

			// 결과 값 저장
			sucess = iDAO.Updatepayment(i_no, txt_b_cost.getText().trim(), txt_b_kg.getText().trim());

			// 결과값이 true 일경우 실행
			if (sucess) {

				// 등록후 초기화

				txt_b_buydate.clear();
				txt_b_code.clear();
				txt_b_number.clear();
				txt_b_kg.clear();
				txt_b_cost.clear();
				cbx_b_importion.getSelectionModel().clearSelection();
				cbx_b_origin.getSelectionModel().clearSelection();
				cbx_b_brand.getSelectionModel().clearSelection();
				cbx_b_part.getSelectionModel().clearSelection();
			}
			// 미입금금액 수정중 오류 발생시 오류 출력
		} catch (Exception e) {
			System.out.println("111111111111");
			// TODO: handle exception
		}

	}

}