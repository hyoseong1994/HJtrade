package control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CollectVO;
import model.PaymentVO;

public class MoneyTabController implements Initializable {
	@FXML
	private TableView<CollectVO> collectTableView = new TableView<>();
	@FXML
	private TableView<PaymentVO> paymentTableView = new TableView<>();
	@FXML
	private Button btn_c_search;
	@FXML
	private Button btn_c_barchart;
	@FXML
	private Button btn_p_search;
	@FXML
	private Button btn_p_barchart;
	@FXML
	private TextField txt_c_search;
	@FXML
	private TextField txt_p_search;

	public static ObservableList<CollectVO> collcetDataList = FXCollections.observableArrayList();
	ObservableList<CollectVO> selectCollect = null; // 매입거래처 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 거래처 정보 인덱스 저장

	public static ObservableList<PaymentVO> paymentDataList = FXCollections.observableArrayList();
	ObservableList<PaymentVO> selectPayment = null; // 매입거래처 테이블에서 선택한 정보 저장

	// 초기설정
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 거래처등록 초기화
			collectTableView.setEditable(false);
			paymentTableView.setEditable(false);

			// 수금 테이블 뷰 컬럼이름 설정
			TableColumn colCNo = new TableColumn("수금 번호");
			colCNo.setPrefWidth(90);
			colCNo.setStyle("-fx-allignment: CENTER");
			colCNo.setCellValueFactory(new PropertyValueFactory<>("c_no"));

			TableColumn colCDate = new TableColumn("수금 날짜");
			colCDate.setPrefWidth(160);
			colCDate.setStyle("-fx-allignment: CENTER");
			colCDate.setCellValueFactory(new PropertyValueFactory<>("c_date"));

			TableColumn colCName = new TableColumn("상 호 명");
			colCName.setPrefWidth(90);
			colCName.setStyle("-fx-allignment: CENTER");
			colCName.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colABusinessNumber = new TableColumn("사업자 번호");
			colABusinessNumber.setPrefWidth(90);
			colABusinessNumber.setStyle("-fx-allignment: CENTER");
			colABusinessNumber.setCellValueFactory(new PropertyValueFactory<>("a_businessNumber"));

			TableColumn colCBusiness = new TableColumn("업	태");
			colCBusiness.setPrefWidth(90);
			colCBusiness.setStyle("-fx-allignment: CENTER");
			colCBusiness.setCellValueFactory(new PropertyValueFactory<>("c_business"));

			TableColumn colCCollectMoney = new TableColumn("수 금 액");
			colCCollectMoney.setPrefWidth(90);
			colCCollectMoney.setStyle("-fx-allignment: CENTER");
			colCCollectMoney.setCellValueFactory(new PropertyValueFactory<>("c_collectMoney"));

			collectTableView.setItems(collcetDataList);
			collectTableView.getColumns().addAll(colCNo, colCDate, colCName, colABusinessNumber, colCBusiness,
					colCCollectMoney);

			// 거래처 전체 목록
			CollectTotalList();

			// 입금 테이블 뷰 컬럼이름 설정
			TableColumn colPNo = new TableColumn("입금 번호");
			colPNo.setPrefWidth(90);
			colPNo.setStyle("-fx-allignment: CENTER");
			colPNo.setCellValueFactory(new PropertyValueFactory<>("p_no"));

			TableColumn colPDate = new TableColumn("입금 날짜");
			colPDate.setPrefWidth(160);
			colPDate.setStyle("-fx-allignment: CENTER");
			colPDate.setCellValueFactory(new PropertyValueFactory<>("p_date"));

			TableColumn colPName = new TableColumn("상 호 명");
			colPName.setPrefWidth(90);
			colPName.setStyle("-fx-allignment: CENTER");
			colPName.setCellValueFactory(new PropertyValueFactory<>("p_name"));

			TableColumn colIBusinessNumber = new TableColumn("사업자 번호");
			colIBusinessNumber.setPrefWidth(90);
			colIBusinessNumber.setStyle("-fx-allignment: CENTER");
			colIBusinessNumber.setCellValueFactory(new PropertyValueFactory<>("i_businessNumber"));

			TableColumn colPBusiness = new TableColumn("업	태");
			colPBusiness.setPrefWidth(90);
			colPBusiness.setStyle("-fx-allignment: CENTER");
			colPBusiness.setCellValueFactory(new PropertyValueFactory<>("p_business"));

			TableColumn colPPayment = new TableColumn("입 금 액");
			colPPayment.setPrefWidth(90);
			colPPayment.setStyle("-fx-allignment: CENTER");
			colPPayment.setCellValueFactory(new PropertyValueFactory<>("p_paymentMoney"));

			paymentTableView.setItems(paymentDataList);
			paymentTableView.getColumns().addAll(colPNo, colPDate, colPName, colIBusinessNumber, colPBusiness,
					colPPayment);

			// 거래처 전체 목록
			PaymentTotalList();

			// 거래처 등록, 수정, 삭제 이벤트 등록
			btn_c_search.setOnAction(event -> handlerbtn_c_searchActoion(event));
			btn_c_barchart.setOnAction(event -> handlerbtn_c_barchartActoion(event));
			btn_p_search.setOnAction(event -> handlerbtn_p_searchActoion(event));
			btn_p_barchart.setOnAction(event -> handlerbtn_p_barchartActoion(event));
			txt_c_search.setOnKeyPressed(event -> handlertxt_c_searchKeyPressedAction(event));
			txt_p_search.setOnKeyPressed(event -> handlertxt_p_searchKeyPressedAction(event));
			collectTableView.setOnMouseClicked(event -> handlerCollectTableViewAction(event));
			paymentTableView.setOnMouseClicked(event -> handlerPaymentTableViewAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 수금 테이블뷰 더블클릭 선택 이벤트 핸들러
	public void handlerCollectTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectCollect = collectTableView.getSelectionModel().getSelectedItems();
				selectedIndex = selectCollect.get(0).getC_no();
				String selectedA_name = selectCollect.get(0).getC_name();

				txt_c_search.setText(selectedA_name);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 입금 테이블뷰 더블클릭 선택 이벤트 핸들러
	public void handlerPaymentTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectPayment = paymentTableView.getSelectionModel().getSelectedItems();
				selectedIndex = selectPayment.get(0).getP_no();
				String selectedA_name = selectPayment.get(0).getP_name();

				txt_p_search.setText(selectedA_name);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 입금 검색 텍스트 엔터키 이벤트 핸들러
	public void handlertxt_p_searchKeyPressedAction(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			p_search();
		}

	}

	// 수금 검색 텍스트 엔터키 이벤트 핸들러
	public void handlertxt_c_searchKeyPressedAction(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			c_search();
		}
	}

	// 입금바차트 이벤트 핸들러
	public void handlerbtn_p_barchartActoion(ActionEvent event) {
		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_p_barchart.getScene().getWindow());
			dialog.setTitle("막대 그래프");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));

			BarChart barChart = (BarChart) parent.lookup("#barChart");

			XYChart.Series seriesP_collect = new XYChart.Series();
			seriesP_collect.setName("입 금 액");
			ObservableList P_collectList = FXCollections.observableArrayList();
			for (int i = 0; i < paymentDataList.size(); i++) {
				P_collectList.add(new XYChart.Data(paymentDataList.get(i).getP_name(),
						paymentDataList.get(i).getP_paymentMoney()));
			}

			seriesP_collect.setData(P_collectList);
			barChart.getData().add(seriesP_collect);

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();

			// 막대 그래프 이미지 저장
			WritableImage snapShot = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("chartImage/paymentBarChart.png"));
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수금 검색 버튼 이벤트 핸들러
	public void handlerbtn_p_searchActoion(ActionEvent event) {
		p_search();
	}

	// 수금 검색
	public void p_search() {
		PaymentVO pVo = new PaymentVO();
		PaymentTabDAO pDao = new PaymentTabDAO();

		String serchName = "";
		boolean searchResult = false;

		serchName = txt_p_search.getText().trim();
		try {
			if (serchName.equals("")) {
				try {
					searchResult = true;

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("입금 정보 검색");
					alert.setHeaderText("입금 검색 정보를 입력하세요");
					alert.setContentText("다음에는 주의하세요");
					alert.showAndWait();

					PaymentTotalList(); // 수금 전체 목록 메소드 호출
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ArrayList<PaymentVO> list = null;

				list = pDao.getSearchList(serchName);
				if (list.size() == 0) { // 값이 없을 때
					txt_p_search.clear();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("입금 정보 검색");
					alert.setHeaderText(serchName + " 이 리스트에 없습니다");
					alert.setContentText("다시 검색하세요");
					alert.showAndWait();

					list = pDao.getPaymentVOTotalList();
				}
				txt_c_search.clear();
				paymentDataList.removeAll(paymentDataList);

				int rowCount = list.size();
				for (int index = 0; index < rowCount; index++) {
					pVo = list.get(index);
					paymentDataList.add(pVo);
				}
				searchResult = true;
			}

			if (!searchResult) {
				txt_p_search.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("입금 정보 검색");
				alert.setHeaderText(serchName + " 이 리스트에 없습니다");
				alert.setContentText("다시 검색하세요");
				alert.showAndWait();
			}
		} catch (Exception e) {
		}
	}

	// 입금 테이블 전체 리스트
	static void PaymentTotalList() {
		paymentDataList.removeAll(paymentDataList);

		PaymentTabDAO pDao = new PaymentTabDAO();
		PaymentVO pVo = null;
		ArrayList<PaymentVO> list;

		list = pDao.getPaymentVOTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			pVo = list.get(index);
			paymentDataList.add(pVo);
		}
	}

	// 수금바차트 이벤트 핸들러
	public void handlerbtn_c_barchartActoion(ActionEvent event) {
		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_c_barchart.getScene().getWindow());
			dialog.setTitle("막대 그래프");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));

			BarChart barChart = (BarChart) parent.lookup("#barChart");

			XYChart.Series seriesC_collect = new XYChart.Series();
			seriesC_collect.setName("수 금 액");
			ObservableList C_collectList = FXCollections.observableArrayList();
			for (int i = 0; i < collcetDataList.size(); i++) {
				C_collectList.add(new XYChart.Data(collcetDataList.get(i).getC_name(),
						collcetDataList.get(i).getC_collectMoney()));
			}
			seriesC_collect.setData(C_collectList);
			barChart.getData().add(seriesC_collect);

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();

			// 막대 그래프 이미지 저장
			WritableImage snapShot = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("chartImage/collectBarChart.png"));
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 수금 검색 버튼 이벤트 핸들러
	public void handlerbtn_c_searchActoion(ActionEvent event) {
		c_search();
	}

	// 수금 검색
	public void c_search() {
		CollectVO cVo = new CollectVO();
		CollectTabDAO cDao = new CollectTabDAO();

		String serchName = "";
		boolean searchResult = false;

		serchName = txt_c_search.getText().trim();
		try {
			if (serchName.equals("")) {
				try {
					searchResult = true;

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("수금 정보 검색");
					alert.setHeaderText("수금 검색 정보를 입력하세요");
					alert.setContentText("다음에는 주의하세요");
					alert.showAndWait();

					CollectTotalList(); // 수금 전체 목록 메소드 호출
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ArrayList<CollectVO> list = null;

				list = cDao.getSearchList(serchName);
				if (list.size() == 0) { // 값이 없을 때
					txt_c_search.clear();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("수금 정보 검색");
					alert.setHeaderText(serchName + " 이 리스트에 없습니다");
					alert.setContentText("다시 검색하세요");
					alert.showAndWait();

					list = cDao.getCollectVOTotalList();
				}
				txt_c_search.clear();
				collcetDataList.removeAll(collcetDataList);

				int rowCount = list.size();
				for (int index = 0; index < rowCount; index++) {
					cVo = list.get(index);
					collcetDataList.add(cVo);
				}
				searchResult = true;
			}

			if (!searchResult) {
				txt_c_search.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("수금 정보 검색");
				alert.setHeaderText(serchName + " 이 리스트에 없습니다");
				alert.setContentText("다시 검색하세요");
				alert.showAndWait();
			}
		} catch (Exception e) {
		}
	}

	// 수금 테이블 전체 리스트
	static void CollectTotalList() {
		collcetDataList.removeAll(collcetDataList);

		CollectTabDAO cDao = new CollectTabDAO();
		CollectVO cVo = null;
		ArrayList<CollectVO> list;

		list = cDao.getCollectVOTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			cVo = list.get(index);
			collcetDataList.add(cVo);
		}
	}

}
