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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.ProductVO;

public class ProductTotalTabController implements Initializable {
	@FXML
	private int p_no;
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
	
	
	public static ObservableList<ProductVO> productDataList = FXCollections.observableArrayList();
	ObservableList<ProductVO> selectProduct = null; // 테이블에서 선택한 정보저장
	int selectedProductIndex;// 테이블에 선택한 상품 정보 인덱스저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			btn_p_register.setDisable(false);
			productTableView.setEditable(false);

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

			txt_p_type.setOnKeyPressed(event -> handlerTxt_p_typeKeyPressed(event)); // 종류
			txt_p_origin.setOnKeyPressed(event -> handlerTxt_p_originKeyPressed(event)); // 원산지
			txt_p_brand.setOnKeyPressed(event -> handlerTxt_p_brandKeyPressed(event)); // 브랜드
			txt_p_part.setOnKeyPressed(event -> handlerTxt_p_partKeyPressed(event)); // 부위
			// 상품등록버튼
			btn_p_register.setOnAction(event -> handlerBtn_p_registerAction(event));

		} catch (Exception e) {
			e.printStackTrace();
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
	public void productTotalList() throws Exception {
		productDataList.removeAll(productDataList);

		ProductDAO pDao = new ProductDAO();
		ProductVO pVo = null;

		ArrayList<String> title;
		ArrayList<ProductVO> list;

		title = pDao.getProductColumnName();
		int columnCount = title.size();

		list = pDao.getProductTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			pVo = list.get(index);
			productDataList.add(pVo);
		}
	}

//상품등록 이벤트 핸들러
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

}
