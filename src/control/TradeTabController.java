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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.ImportionVO;
public class TradeTabController implements Initializable{
		
			// 거래처 등록 탭
			@FXML
			private TextField txt_name;
			@FXML
			private TextField txt_businessNumber;
			@FXML
			private TextField txt_represent;
			@FXML
			private TextField txt_representNumber;
			@FXML
			private TextField txt_charge;
			@FXML
			private TextField txt_chargeNumber;
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

			public static ObservableList<ImportionVO> TradeDataList = FXCollections.observableArrayList();
			ObservableList<ImportionVO> selectTrade = null; // 테이블에서 선택한 정보 저장
			int selectedIndex; // 테이블에서 선택한 거래처 정보 인덱스 저장

			@Override
			public void initialize(URL location, ResourceBundle resources) {
				try {

					// 거래처등록 초기화
					btn_update.setDisable(true);
					btn_i_delete.setDisable(true);
					ImportionTableView.setEditable(false);

					// 거래처 테이블 뷰 컬럼이름 설정
					TableColumn colINum = new TableColumn("거래처 번호");
					colINum.setPrefWidth(90);
					colINum.setStyle("-fx-allignment: CENTER");
					colINum.setCellValueFactory(new PropertyValueFactory<>("i_number"));

					TableColumn colIName = new TableColumn("상 호 명");
					colSName.setPrefWidth(160);
					colSName.setStyle("-fx-allignment: CENTER");
					colSName.setCellValueFactory(new PropertyValueFactory<>("i_name"));

					ImportionTableView.setItems(TradeDataList);
					ImportionTableView.getColumns().addAll(colNo, colSNum, colSName);

					// 거래처 전체 목록
					TradeTotalList();

					// 거래처 키 이벤트 등록
					txtTradeNum.setOnKeyPressed(event -> handlerTxtTradeNumKeyPressed(event));

					// 거래처 등록, 수정, 삭제 이벤트 등록
					btnInsert.setOnAction(event -> handlerBtnInsertActoion(event));
					btn_i_delete.setOnAction(event -> handlerbtn_i_deleteActoion(event));
					btn_update.setOnAction(event -> handlerbtn_updateActoion(event));
					ImportionTableView.setOnMouseClicked(event -> handlerImportionTableViewActoion(event));
					btnRead.setOnAction(event -> handlerBtnReadAction(event));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 거래처 테이블뷰 더블클릭 선택 이벤트 핸들러
			public void handlerImportionTableViewActoion(MouseEvent event) {
				if (event.getClickCount() == 2) {
					try {
						selectTrade = ImportionTableView.getSelectionModel().getSelectedItems();
						selectedIndex = selectTrade.get(0).getNo();
						String selectedi_number = selectTrade.get(0).geti_number();
						String selectedi_name = selectTrade.get(0).geti_name();

						txtTradeNum.setText(selectedi_number);
						txtTradeName.setText(selectedi_name);

						btn_update.setDisable(false);
						btn_i_delete.setDisable(false);
						btnInsert.setDisable(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			// 거래처 등록 텍스트 필드 키 이벤트 핸들러
			public void handlerTxtTradeNumKeyPressed(KeyEvent event) {

				if ((txtTradeNum.getText().length() >= 3)) {
					txtTradeNum.clear();
				}
				if (event.getCode() == KeyCode.ENTER) {
					txtTradeName.requestFocus();
				}
			}

			// 거래처 전체 리스트
			public void TradeTotalList() throws Exception {
				TradeDataList.removeAll(TradeDataList);

				TradeDAO sDao = new TradeDAO();
				ImportionVO sVo = null;
				ArrayList<String> title;
				ArrayList<ImportionVO> list;

				title = sDao.getTradeColumnName();
				int columnCount = title.size();

				list = sDao.getTradeTotalList();
				int rowCount = list.size();

				for (int index = 0; index < rowCount; index++) {
					sVo = list.get(index);
					TradeDataList.add(sVo);
				}
			}

			// 거래처 등록 이벤트 핸들러
			public void handlerBtnInsertActoion(ActionEvent event) {
				try {
					TradeDataList.removeAll(TradeDataList);

					ImportionVO svo = null;
					TradeDAO sdao = null;

					svo = new ImportionVO(txtTradeNum.getText().trim(), txtTradeName.getText().trim());
					sdao = new TradeDAO();
					sdao.getTradeRegiste(svo);

					if (sdao != null) {
						
						TradeTotalList();

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("거래처 입력");
						alert.setHeaderText(txtTradeName.getText() + " 거래처가 성공적으로 추가되었습니다..");
						alert.setContentText("다음 거래처를 입력하세요");
						alert.showAndWait();

						txtTradeNum.clear();
						txtTradeName.clear();
						txtTradeNum.requestFocus();
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

					TradeDAO sdao = new TradeDAO();
					sucess = sdao.getTradeUpdate(selectedIndex, txtTradeNum.getText().trim(),
							txtTradeName.getText().trim());
					if (sucess) {
						TradeDataList.removeAll(TradeDataList);
						TradeTotalList();

						txtTradeNum.clear();
						txtTradeName.clear();
						btnInsert.setDisable(false);
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

					TradeDAO sdao = new TradeDAO();
					sucess = sdao.getTradeDelete(selectedIndex);
					if (sucess) {
						TradeDataList.removeAll(TradeDataList);
						TradeTotalList();

						txtTradeNum.clear();
						txtTradeName.clear();
						btnInsert.setDisable(false);
						btn_update.setDisable(true);
						btn_i_delete.setDisable(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 테이블 뷰 읽기 테스트
			public void handlerBtnReadAction(ActionEvent event) {
				try {
					int count = ImportionTableView.getItems().size();
					System.out.println("count : " + count);
					for (int i = 0; i < count; i++) {
						selectTrade = ImportionTableView.getItems();
						int index = selectTrade.get(i).getNo();
						String selectedi_number = selectTrade.get(i).geti_number();
						String selectedi_name = selectTrade.get(i).geti_name();

						System.out.print(index + " ");
						System.out.print(selectedi_number + " ");
						System.out.println(selectedi_name + " ");
						/*
						 * ImportionVO svo = null; TradeDAO sdao = null;
						 * 
						 * svo = new ImportionVO(index, selectedi_number, selectedi_name); sdao = new
						 * TradeDAO();
						 * 
						 * sdao.getTradeRegiste(svo);
						 */
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}

}
