package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.AccountVO;
import model.ProductVO;
import model.StockVO;

public class ProductTotalTabController implements Initializable {
	@FXML
	private TextField txt_p_type; // 분류
	@FXML
	private TextField txt_p_origin; // 원산지
	@FXML
	private TextField txt_p_brand; // 브랜드
	@FXML
	private TextField txt_p_part; // 부위
	@FXML
	private Button btn_p_register; // 등록버튼
	@FXML
	private TableView<ProductVO> productTableView = new TableView<>();
	@FXML
	private TextField txt_S_dealDate;
	@FXML
	private TextField txt_S_number;
	@FXML
	private TextField txt_S_kg;
	@FXML
	private TextField txt_S_cost;
	@FXML
	private TableView<StockVO> stockTableView = new TableView<>();
	@FXML
	private Button btn_s_deal;
	@FXML
	private String S_state;
	@FXML
	private String p_no;
	@FXML
	private String a_no;
	@FXML
	private ComboBox<String> cbx_ccountChoice;
	@FXML
	private String origin;
	@FXML
	private String brand;
	@FXML
	private String part;
	private String b_no;
	private String b_code;

	public static ObservableList<ProductVO> productDataList = FXCollections.observableArrayList();
	ObservableList<ProductVO> selectProduct = null; // 테이블에서 선택한 정보저장
	int selectedProductIndex;// 테이블에 선택한 상품 정보 인덱스저장

	public static ObservableList<StockVO> stockDataList = FXCollections.observableArrayList();
	ObservableList<StockVO> selectStock = null; // 테이블에서 선택한 정보저장
	int selectedStockIndex;// 테이블에 선택한 상품 정보 인덱스저장

	// 초기 설정
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			accountName();

			btn_p_register.setDisable(false);
			productTableView.setEditable(false);
			btn_s_deal.setDisable(true);
			stockTableView.setEditable(false);

			// 상품 테이블 뷰 컬림 이름 설정
			TableColumn col_P_No = new TableColumn("상품번호");
			col_P_No.setPrefWidth(90);
			col_P_No.setStyle("-fx-allignment:CENTER");
			col_P_No.setCellValueFactory(new PropertyValueFactory<>("p_no"));

			TableColumn col_P_type = new TableColumn("분류");
			col_P_type.setPrefWidth(90);
			col_P_type.setStyle("-fx-allignment:CENTER");
			col_P_type.setCellValueFactory(new PropertyValueFactory<>("p_type"));

			TableColumn col_P_origin = new TableColumn("원산지");
			col_P_origin.setPrefWidth(90);
			col_P_origin.setStyle("-fx-allignment:CENTER");
			col_P_origin.setCellValueFactory(new PropertyValueFactory<>("p_origin"));

			TableColumn col_P_brand = new TableColumn("브랜드");
			col_P_brand.setPrefWidth(90);
			col_P_brand.setStyle("-fx-allignment:CENTER");
			col_P_brand.setCellValueFactory(new PropertyValueFactory<>("p_brand"));

			TableColumn col_P_part = new TableColumn("부위");
			col_P_part.setPrefWidth(90);
			col_P_part.setStyle("-fx-allignment:CENTER");
			col_P_part.setCellValueFactory(new PropertyValueFactory<>("p_part"));

			productTableView.setItems(productDataList);
			productTableView.getColumns().addAll(col_P_No, col_P_type, col_P_origin, col_P_brand, col_P_part);

			// 상품 전체 목록
			productTotalList();

			// 재고 테이블 뷰 컬럼이름 설정
			TableColumn col_S_No = new TableColumn("재고번호");
			col_S_No.setPrefWidth(70);
			col_S_No.setStyle("-fx-allignment:CENTER");
			col_S_No.setCellValueFactory(new PropertyValueFactory<>("s_no"));

			TableColumn col_B_date = new TableColumn("거래일자");
			col_B_date.setPrefWidth(70);
			col_B_date.setStyle("-fx-allignment:CENTER");
			col_B_date.setCellValueFactory(new PropertyValueFactory<>("b_date"));

			TableColumn col_S_code = new TableColumn("식별번호");
			col_S_code.setPrefWidth(70);
			col_S_code.setStyle("-fx-allignment:CENTER");
			col_S_code.setCellValueFactory(new PropertyValueFactory<>("b_code"));

			TableColumn col_P_type1 = new TableColumn("분류");
			col_P_type1.setPrefWidth(70);
			col_P_type1.setStyle("-fx-allignment:CENTER");
			col_P_type1.setCellValueFactory(new PropertyValueFactory<>("p_type"));

			TableColumn col_P_origin1 = new TableColumn("원산지");
			col_P_origin1.setPrefWidth(70);
			col_P_origin1.setStyle("-fx-allignment:CENTER");
			col_P_origin1.setCellValueFactory(new PropertyValueFactory<>("p_origin"));

			TableColumn col_P_brand1 = new TableColumn("브랜드");
			col_P_brand1.setPrefWidth(70);
			col_P_brand1.setStyle("-fx-allignment:CENTER");
			col_P_brand1.setCellValueFactory(new PropertyValueFactory<>("p_brand"));

			TableColumn col_P_part1 = new TableColumn("부위");
			col_P_part1.setPrefWidth(80);
			col_P_part1.setStyle("-fx-allignment:CENTER");
			col_P_part1.setCellValueFactory(new PropertyValueFactory<>("p_part"));

			TableColumn col_S_number = new TableColumn("수량");
			col_S_number.setPrefWidth(70);
			col_S_number.setStyle("-fx-allignment:CENTER");
			col_S_number.setCellValueFactory(new PropertyValueFactory<>("s_number"));

			TableColumn col_S_kg = new TableColumn("중량");
			col_S_kg.setPrefWidth(70);
			col_S_kg.setStyle("-fx-allignment:CENTER");
			col_S_kg.setCellValueFactory(new PropertyValueFactory<>("s_kg"));

			TableColumn col_S_cost = new TableColumn("단가");
			col_S_cost.setPrefWidth(70);
			col_S_cost.setStyle("-fx-allignment:CENTER");
			col_S_cost.setCellValueFactory(new PropertyValueFactory<>("s_cost"));

			TableColumn col_S_totalmoney = new TableColumn("총 금액");
			col_S_totalmoney.setPrefWidth(70);
			col_S_totalmoney.setStyle("-fx-allignment:CENTER");
			col_S_totalmoney.setCellValueFactory(new PropertyValueFactory<>("s_totalMoney"));

			TableColumn col_S_state = new TableColumn("상태");
			col_S_state.setPrefWidth(50);
			col_S_state.setStyle("-fx-allignment:CENTER");
			col_S_state.setCellValueFactory(new PropertyValueFactory<>("s_state"));

			stockTableView.setItems(stockDataList);
			stockTableView.getColumns().addAll(col_S_No, col_B_date, col_S_code, col_P_type1, col_P_origin1,
					col_P_brand1, col_P_part1, col_S_number, col_S_kg, col_S_cost, col_S_totalmoney, col_S_state);

			// 재고 전체 목록
			stockTotalList();

			// 텍스트 필드 엔터키 이벤트
			txt_p_type.setOnKeyPressed(event -> handlerTxt_p_typeKeyPressed(event)); // 종류
			txt_p_origin.setOnKeyPressed(event -> handlerTxt_p_originKeyPressed(event)); // 원산지
			txt_p_brand.setOnKeyPressed(event -> handlerTxt_p_brandKeyPressed(event)); // 브랜드
			txt_p_part.setOnKeyPressed(event -> handlerTxt_p_partKeyPressed(event)); // 부위
			txt_S_dealDate.setOnKeyPressed(event -> handlertxt_S_dealDateKeyPressed(event));
			txt_S_number.setOnKeyPressed(event -> handlertxt_S_numberKeyPressed(event));
			txt_S_kg.setOnKeyPressed(event -> handlertxt_S_kgKeyPressed(event));
			txt_S_cost.setOnKeyPressed(event -> handlertxt_S_costKeyPressed(event));

			// 상품등록버튼 이벤트
			btn_p_register.setOnAction(event -> handlerBtn_p_registerAction(event));

			// 재고 테이블 더블클릭 이벤트
			stockTableView.setOnMouseClicked(event -> handlerstockTableViewAction(event));

			// 출고 버튼 이벤트
			btn_s_deal.setOnAction(event -> handlerdealAction(event));

			// 콤보박스 이벤트
			cbx_ccountChoice.setOnAction(event -> hacdlercbx_ccountChoiceAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 출고버튼 이벤트 핸들러
	public void handlerdealAction(ActionEvent event) {
		deal();
	}

	// 엔터키 이벤트 핸들러
	public void handlertxt_S_dealDateKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_S_number.requestFocus();
		}
	}

	public void handlertxt_S_numberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_S_kg.requestFocus();
		}
	}

	public void handlertxt_S_kgKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getCode() == KeyCode.ENTER) {
			txt_S_cost.requestFocus();
		}
	}

	public void handlertxt_S_costKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getCode() == KeyCode.ENTER) {
			deal();
		}
	}

	// ENTER 하면 다음 텍스트필드로
	public void handlerTxt_p_typeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_p_origin.requestFocus();
		}
	}

	public void handlerTxt_p_originKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_p_brand.requestFocus();
		}
	}

	public void handlerTxt_p_brandKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_p_part.requestFocus();
		}
	}

	public void handlerTxt_p_partKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btn_p_register.requestFocus();
		}
	}

	// 상품전체리스트
	public static void productTotalList() {
		productDataList.removeAll(productDataList);

		ProductDAO pDao = new ProductDAO();
		ProductVO pVo = null;

		ArrayList<ProductVO> list;

		list = pDao.getProductTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			pVo = list.get(index);
			productDataList.add(pVo);
		}
	}

	// 상품등록 이벤트 핸들러
	public void handlerBtn_p_registerAction(ActionEvent event) {
		// 하나라도 공백시 오류발생

		Alert alert = null; // 알럿 객체 null 초기화

		if (txt_p_type.getText().trim().equals("")) {
			txt_p_type.clear();
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록");
			alert.setHeaderText("소/돼지를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_p_origin.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록");
			alert.setHeaderText("원산지를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_p_brand.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록");
			alert.setHeaderText("브랜드를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		} else if (txt_p_part.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("상품등록");
			alert.setHeaderText("부위를를 입력하세요");
			alert.setContentText("다시입력해주세요");
			alert.showAndWait();
		}
		if (alert != null) {
			return;
		}
		try {
			productDataList.removeAll(productDataList);

			ProductVO pvo = null;
			ProductDAO pdao = null;

			pvo = new ProductVO(txt_p_type.getText().trim(), txt_p_origin.getText().trim(),
					txt_p_brand.getText().trim(), txt_p_part.getText().trim());
			pdao = new ProductDAO();
			pdao.getProductRegiste(pvo);

			if (pdao != null) {
				productTotalList();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상품 등록");
				alert.setHeaderText("상품이 추가되었습니다");
				alert.setContentText("다음 상품을 입력하세요");
				alert.showAndWait();

				txt_p_type.clear();
				txt_p_origin.clear();
				txt_p_brand.clear();
				txt_p_part.clear();
				txt_p_type.requestFocus();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 상품 전체 리스트
	public static void stockTotalList() {
		stockDataList.removeAll(stockDataList);

		StockDAO sDao = new StockDAO();
		StockVO sVo = null;

		ArrayList<StockVO> list;

		list = sDao.getStockTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			stockDataList.add(sVo);
		}
	}

	// 더블클릭 이벤트 핸들러
	public void handlerstockTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectStock = stockTableView.getSelectionModel().getSelectedItems();
				selectedStockIndex = selectStock.get(0).getS_no();
				int selectedS_number = selectStock.get(0).getS_number();
				double selectedS_kg = selectStock.get(0).getS_kg();
				int selectedS_cost = selectStock.get(0).getS_cost();
				String selectedS_state = selectStock.get(0).getS_state();

				origin = selectStock.get(0).getP_origin();
				brand = selectStock.get(0).getP_brand();
				part = selectStock.get(0).getP_part();
				b_code = selectStock.get(0).getB_code();

				txt_S_number.setText(selectedS_number + "");
				txt_S_kg.setText(selectedS_kg + "");
				txt_S_cost.setText(selectedS_cost + "");
				S_state = selectedS_state;

				btn_s_deal.setDisable(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 선택된 상품명 인덱스값
			ProductDAO pDao = new ProductDAO();

			try {

				p_no = pDao.getproductNum(origin, brand, part);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
	}

	// 식별번호로 입고번호 찾기
	public void b_no(String b_code) {
		try {
			BuyDAO bDAO = new BuyDAO();

			b_no = bDAO.b_no(b_code);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	// 출고
	public void deal() {
		b_no(b_code);
		try {

			boolean sucess;

			StockDAO sDao = new StockDAO();
			sucess = sDao.getDeal(txt_S_dealDate.getText().trim(), txt_S_number.getText().trim(),
					txt_S_kg.getText().trim(), txt_S_cost.getText().trim(), selectedStockIndex, S_state, p_no, a_no,
					b_no);
			if (sucess) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 재고 수량 변경
		try {

			boolean sucess;

			StockDAO sDao = new StockDAO();
			sucess = sDao.getStockUpdateStock(txt_S_number.getText().trim(), txt_S_kg.getText().trim(),
					selectedStockIndex);
			if (sucess) {
				stockDataList.removeAll(stockDataList);
				stockTotalList();

				txt_S_dealDate.clear();
				txt_S_number.clear();
				txt_S_kg.clear();
				txt_S_cost.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 콤보박스 선택 거래처 번호 가져오기
	public void hacdlercbx_ccountChoiceAction(ActionEvent event) {

		// 선택된 직원명 인덱스값
		String selectedNameIndex = cbx_ccountChoice.getSelectionModel().getSelectedItem();

		AccountTabDAO account = new AccountTabDAO();

		try {

			a_no = account.getaccountNum(selectedNameIndex);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	// 판매처 이름 가져오기
	public void accountName() {
		// TODO Auto-generated method stub
		// 배열 생성
		ArrayList<AccountVO> list = new ArrayList();
		ObservableList<String> AccountDataList = FXCollections.observableArrayList();
		AccountVO aVo = null;

		try {
			AccountTabDAO adao = new AccountTabDAO();

			// 직원명을 가져오는 메소드를 list에 저장
			list = adao.getaccountName();

			for (int i = 0; i < list.size(); i++) {
				aVo = list.get(i);
				AccountDataList.add(aVo.getA_name());
				cbx_ccountChoice.setItems(AccountDataList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
