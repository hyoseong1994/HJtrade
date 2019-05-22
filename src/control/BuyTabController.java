package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.BuyVO;
import model.ProductVO;

public class BuyTabController implements Initializable {
	@FXML
	private ComboBox<String> cbx_b_importion;
	@FXML
	private ComboBox<String> cbx_b_type;
	@FXML
	private ComboBox<String> cbx_b_origin;
	@FXML
	private ComboBox<String> cbx_b_brand;
	@FXML
	private ComboBox<String> cbx_b_part; // 부위
	@FXML
	private TextField txt_b_buydate;
	@FXML
	private TextField txt_b_code;
	@FXML
	private TextField txt_b_number;
	@FXML
	private TextField txt_b_kg;
	@FXML
	private TextField txt_b_cost;
	@FXML
	private Button btn_b_order; // 입고 버튼
	@FXML
	private Button btn_b_return; // 반품 버튼
	@FXML
	private TableView<BuyVO> BuyTableView = new TableView<>();

	ObservableList<BuyVO> buyDataList = FXCollections.observableArrayList();
	ObservableList<BuyVO> selectBuy = null;

	int selectedBuyIndex; // 테이블에 선택한 상품 정보 인덱스저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		productBrand();

		try {
			btn_b_order.setDisable(false);
			btn_b_return.setDisable(false);

			cbx_b_type.setItems(FXCollections.observableArrayList("소", "돼지"));
			cbx_b_importion.setItems(FXCollections.observableArrayList("22"));
			cbx_b_origin.setItems(FXCollections.observableArrayList("미국"));
			cbx_b_part.setItems(FXCollections.observableArrayList("삼겹살", "갈비"));

			// 매입 전체 목록
			buyTotalList();

			// 입고 테이블 뷰 컬럼이름 설정
			TableColumn col_B_No = new TableColumn("입고번호");
			col_B_No.setPrefWidth(70);
			col_B_No.setStyle("-fx-allignment:CENTER");
			col_B_No.setCellValueFactory(new PropertyValueFactory<>("b_no"));

			TableColumn col_B_buyDate = new TableColumn("주문일자");
			col_B_buyDate.setPrefWidth(70);
			col_B_buyDate.setStyle("-fx-allignment:CENTER");
			col_B_buyDate.setCellValueFactory(new PropertyValueFactory<>("b_buyDate"));

			TableColumn col_B_date = new TableColumn("거래일자");
			col_B_date.setPrefWidth(70);
			col_B_date.setStyle("-fx-allignment:CENTER");
			col_B_date.setCellValueFactory(new PropertyValueFactory<>("b_date"));

			TableColumn col_B_name = new TableColumn("상호명");
			col_B_name.setPrefWidth(70);
			col_B_name.setStyle("-fx-allignment:CENTER");
			col_B_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));

			TableColumn col_B_code = new TableColumn("식별번호");
			col_B_code.setPrefWidth(70);
			col_B_code.setStyle("-fx-allignment:CENTER");
			col_B_code.setCellValueFactory(new PropertyValueFactory<>("b_code"));

			TableColumn col_B_type = new TableColumn("분류");
			col_B_type.setPrefWidth(70);
			col_B_type.setStyle("-fx-allignment:CENTER");
			col_B_type.setCellValueFactory(new PropertyValueFactory<>("b_type"));

			TableColumn col_B_orgin = new TableColumn("원산지");
			col_B_orgin.setPrefWidth(70);
			col_B_orgin.setStyle("-fx-allignment:CENTER");
			col_B_orgin.setCellValueFactory(new PropertyValueFactory<>("b_orgin"));

			TableColumn col_B_brand = new TableColumn("브랜드");
			col_B_brand.setPrefWidth(70);
			col_B_brand.setStyle("-fx-allignment:CENTER");
			col_B_brand.setCellValueFactory(new PropertyValueFactory<>("b_brand"));

			TableColumn col_B_part = new TableColumn("부위");
			col_B_part.setPrefWidth(80);
			col_B_part.setStyle("-fx-allignment:CENTER");
			col_B_part.setCellValueFactory(new PropertyValueFactory<>("b_part"));

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
			col_B_totalMoney.setPrefWidth(70);
			col_B_cost.setStyle("-fx-allignment:CENTER");
			col_B_cost.setCellValueFactory(new PropertyValueFactory<>("b_totalMoney"));

			TableColumn col_B_state = new TableColumn("상태");
			col_B_state.setPrefWidth(50);
			col_B_cost.setStyle("-fx-allignment:CENTER");
			col_B_cost.setCellValueFactory(new PropertyValueFactory<>("b_state"));

			BuyTableView.setItems(buyDataList);
			BuyTableView.getColumns().addAll(col_B_No, col_B_buyDate, col_B_date, col_B_name, col_B_code, col_B_type,
					col_B_orgin, col_B_brand, col_B_part, col_B_number, col_B_kg, col_B_cost, col_B_totalMoney,
					col_B_state);

			// 텍스트
			txt_b_buydate.setOnKeyPressed(event -> handlerTxt_b_buydateKeyPressed(event));
			txt_b_code.setOnKeyPressed(event -> handlerTxt_b_codeKeyPressed(event));
			txt_b_number.setOnKeyPressed(event -> handlerTxt_b_numberKeyPressed(event));
			txt_b_kg.setOnKeyPressed(event -> handlerTxt_b_kgKeyPressed(event));
			txt_b_cost.setOnKeyPressed(event -> handlerTxt_b_costKeyPressed(event));
			// 콤보박스 선택
			cbx_b_importion.setOnAction(event -> handlerCbx_b_importionAction(event));
			cbx_b_type.setOnAction(event -> handlerCbx_b_typeAction(event));
			cbx_b_origin.setOnAction(event -> handlerCbx_b_originAction(event));
			cbx_b_brand.setOnAction(event -> handlerCbx_b_brandAction(event));
			cbx_b_part.setOnAction(event -> handlerCbx_b_partAction(event));

			// 상픔 입고 등록 버튼
			btn_b_order.setOnAction(event -> handlerBtn_b_orderAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 거래처및 상품 선택 이벤트 핸들러
	public void handlerCbx_b_importionAction(ActionEvent event) {

	}

	public void handlerCbx_b_typeAction(ActionEvent event) {

	}

	public void handlerCbx_b_originAction(ActionEvent event) {

	}

	public void handlerCbx_b_brandAction(ActionEvent event) {

		try {

			ProductDAO pdao = new ProductDAO();
			// 선택된 직원명 인덱스값
			int selectedNameIndex = cbx_b_brand.getSelectionModel().getSelectedIndex();
			// 배열 생성
			ArrayList<ProductVO> list = new ArrayList();
			// 직원명을 가져오는 메소드를 list에 저장
			list = pdao.getProductTotalList();

			ProductVO pvo = new ProductVO();

			list = pdao.getProductTotalList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 브랜드명 가져오기
	public void productBrand() {

		BuyDAO bdao = new BuyDAO();
		ArrayList productbrand = new ArrayList<>();
		Set<Object> s = new HashSet<Object>();
		try {
			productbrand = bdao.getProductInfo();
			cbx_b_brand.setItems(FXCollections.observableArrayList(productbrand));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handlerCbx_b_partAction(ActionEvent event) {

	}

	public void handlerTxt_b_buydateKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_code.requestFocus();
		}
	}

	public void handlerTxt_b_codeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_number.requestFocus();
		}
	}

	public void handlerTxt_b_numberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_kg.requestFocus();
		}
	}

	public void handlerTxt_b_kgKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txt_b_cost.requestFocus();
		}
	}

	public void handlerTxt_b_costKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btn_b_order.requestFocus();
		}
	}

	// 전체리스트
	public void buyTotalList() throws Exception {
		
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

	// 주문버튼 클릭시 저장
	public void handlerBtn_b_orderAction(ActionEvent event) {

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
		try {
			buyDataList.removeAll(buyDataList);

			BuyVO bvo = null;
			BuyDAO bdao = null;
			System.out.println((cbx_b_importion.getSelectionModel().getSelectedItem()));

			bvo = new BuyVO(txt_b_buydate.getText().trim(),(cbx_b_importion.getSelectionModel().getSelectedItem()),
					txt_b_code.getText().trim(),(cbx_b_type.getSelectionModel().getSelectedItem()),
					(cbx_b_origin.getSelectionModel().getSelectedItem()),
					(cbx_b_brand.getSelectionModel().getSelectedItem()),
					(cbx_b_part.getSelectionModel().getSelectedItem()), 
					 Integer.parseInt(txt_b_number.getText().trim()),
					Double.parseDouble(txt_b_kg.getText().trim()), Integer.parseInt(txt_b_cost.getText().trim()));
			
			bdao = new BuyDAO();
			bdao.getBuyRegiste(bvo);

			if (bdao != null) {
				buyTotalList();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상품 등록");
				alert.setHeaderText("상품이 추가되었습니다");
				alert.setContentText("다음 상품을 입력하세요");
				alert.showAndWait();

				cbx_b_importion.getSelectionModel().clearSelection();
				cbx_b_type.getSelectionModel().clearSelection();
				cbx_b_origin.getSelectionModel().clearSelection();
				cbx_b_brand.getSelectionModel().clearSelection();
				cbx_b_part.getSelectionModel().clearSelection();

				txt_b_buydate.clear();
				txt_b_code.clear();
				txt_b_number.clear();
				txt_b_kg.clear();
				txt_b_cost.clear();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}