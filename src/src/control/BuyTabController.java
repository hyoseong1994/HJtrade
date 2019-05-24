package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.sun.javafx.scene.control.skin.LabeledText;

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
import model.ImportionVO;
import model.ProductVO;

public class BuyTabController implements Initializable {
	@FXML
	private ComboBox<String> cbx_b_importion;
	@FXML
	private String b_type;
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
	private TextField txt_b_totalMoney;
	@FXML
	private ComboBox<String> cbx_b_importion2;
	@FXML
	private ToggleGroup productType;
	@FXML
	private RadioButton typeCow;
	@FXML
	private RadioButton typePig;
	@FXML
	private Button btn_b_order; // 입고 버튼
	@FXML
	private Button btn_b_return; // 반품 버튼
	@FXML
	private Label txt_i_address;
	@FXML
	private Label txt_i_business;
	@FXML
	private Label txt_i_representPhone;
	@FXML
	private TableView<BuyVO> BuyTableView = new TableView<>();

	ObservableList<BuyVO> buyDataList = FXCollections.observableArrayList();
	ObservableList<BuyVO> selectBuy = null;

	int selectedBuyIndex; // 테이블에 선택한 상품 정보 인덱스저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		productOrigin();
		productBrand();
		importionName();
		importionName2();

		try {

			btn_b_order.setDisable(false);
			btn_b_return.setDisable(false);

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

			TableColumn col_I_name = new TableColumn("상호명");
			col_I_name.setPrefWidth(70);
			col_I_name.setStyle("-fx-allignment:CENTER");
			col_I_name.setCellValueFactory(new PropertyValueFactory<>("i_name"));

			TableColumn col_S_code = new TableColumn("식별번호");
			col_S_code.setPrefWidth(70);
			col_S_code.setStyle("-fx-allignment:CENTER");
			col_S_code.setCellValueFactory(new PropertyValueFactory<>("s_code"));

			TableColumn col_P_type = new TableColumn("분류");
			col_P_type.setPrefWidth(70);
			col_P_type.setStyle("-fx-allignment:CENTER");
			col_P_type.setCellValueFactory(new PropertyValueFactory<>("p_type"));

			TableColumn col_P_orgin = new TableColumn("원산지");
			col_P_orgin.setPrefWidth(70);
			col_P_orgin.setStyle("-fx-allignment:CENTER");
			col_P_orgin.setCellValueFactory(new PropertyValueFactory<>("p_orgin"));

			TableColumn col_P_brand = new TableColumn("브랜드");
			col_P_brand.setPrefWidth(70);
			col_P_brand.setStyle("-fx-allignment:CENTER");
			col_P_brand.setCellValueFactory(new PropertyValueFactory<>("p_brand"));

			TableColumn col_P_part = new TableColumn("부위");
			col_P_part.setPrefWidth(80);
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
			col_B_totalMoney.setPrefWidth(70);
			col_B_totalMoney.setStyle("-fx-allignment:CENTER");
			col_B_totalMoney.setCellValueFactory(new PropertyValueFactory<>("b_totalMoney"));

			TableColumn col_S_state = new TableColumn("상태");
			col_S_state.setPrefWidth(50);
			col_S_state.setStyle("-fx-allignment:CENTER");
			col_S_state.setCellValueFactory(new PropertyValueFactory<>("s_state"));

			BuyTableView.setItems(buyDataList);
			BuyTableView.getColumns().addAll(col_B_No, col_B_buyDate, col_B_date, col_I_name, col_S_code, col_P_type,
					col_P_orgin, col_P_brand, col_P_part, col_B_number, col_B_kg, col_B_cost, col_B_totalMoney,
					col_S_state);

			// 매입 전체 목록
			BuyTotalList();

			// 텍스트
			txt_b_buydate.setOnKeyPressed(event -> handlerTxt_b_buydateKeyPressed(event));
			txt_b_code.setOnKeyPressed(event -> handlerTxt_b_codeKeyPressed(event));
			txt_b_number.setOnKeyPressed(event -> handlerTxt_b_numberKeyPressed(event));
			txt_b_kg.setOnKeyPressed(event -> handlerTxt_b_kgKeyPressed(event));
			txt_b_cost.setOnKeyPressed(event -> handlerTxt_b_costKeyPressed(event));

			// 소 또는 돼지 선택 이벤트
			typeCow.setOnAction(event -> handlerTypeCowAction(event));
			typePig.setOnAction(event -> handlerTypePigAction(event));
			//

			cbx_b_importion.setOnAction(event -> handlerCbx_b_importionAction(event));

			// 상픔 입고 등록 버튼
			btn_b_order.setOnAction(event -> handlerBtn_b_orderAction(event));

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

	// 거래처 상호명 가져오기
	public void importionName2() {

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
				cbx_b_importion2.setItems(ImportionDataList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 거래처 상호명 누르면 회사 정보 가져오기
	public void handlerCbx_b_importion2Action(String I_name) {
		try {

			String selectedNameIndex = cbx_b_importion2.getSelectionModel().getSelectedItem();

			ImportionTabDAO idao = new ImportionTabDAO();
			ArrayList<ImportionVO> list = new ArrayList();
			// 직원명을 가져오는 메소드를 list에 저장

			ImportionVO ivo = new ImportionVO();

			list = idao.getImportionInfo(I_name);

			txt_i_address.setText(list.get(0).getI_address());
			txt_i_business.setText(list.get(0).getI_business());
			txt_i_representPhone.setText(list.get(0).getI_representPhone());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerCbx_b_importionAction(ActionEvent event) {
		// 선택한 직원명 인덱스값

		String selectedNameIndex = cbx_b_importion.getSelectionModel().getSelectedItem();

		ImportionTabDAO importion = new ImportionTabDAO();

		String no;
		try {

			no = importion.getImportionNum(selectedNameIndex);
			System.out.println(selectedNameIndex);
			System.out.println(no);

		} catch (Exception e) {
			System.out.println(e);
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

	// 엔터시 다음 택스트 필드 이동
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

	// 주문버튼 클릭시 저장
	public void handlerBtn_b_orderAction(ActionEvent event) {

		Alert alert = null; // 알럿 객체 null 초기화

		// 빈칸시 경고창 실행

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

			bvo = new BuyVO(txt_b_buydate.getText().trim(), (cbx_b_importion.getSelectionModel().getSelectedItem()),
					txt_b_code.getText().trim(), b_type, (cbx_b_origin.getSelectionModel().getSelectedItem()),
					(cbx_b_brand.getSelectionModel().getSelectedItem()),
					(cbx_b_part.getSelectionModel().getSelectedItem()), Integer.parseInt(txt_b_number.getText().trim()),
					Double.parseDouble(txt_b_kg.getText().trim()), Integer.parseInt(txt_b_cost.getText().trim()));

			bdao = new BuyDAO();
			bdao.getBuyRegiste(bvo);

			if (bdao != null) {
				BuyTotalList();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상품 등록");
				alert.setHeaderText("상품이 추가되었습니다");
				alert.setContentText("다음 상품을 입력하세요");
				alert.showAndWait();

				// 등록후 초기화
				cbx_b_importion.getSelectionModel().clearSelection();
				cbx_b_origin.getSelectionModel().clearSelection();
				cbx_b_brand.getSelectionModel().clearSelection();
				cbx_b_part.getSelectionModel().clearSelection();

				typePig.setSelected(false);
				typeCow.setSelected(false);

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