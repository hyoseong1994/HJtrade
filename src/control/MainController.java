package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML
	private TabPane mainPane;
	@FXML
	private Tab trade;
	@FXML
	private Tab money;
	@FXML
	private Tab receipe;
	@FXML
	private Tab product;

	// 메뉴
	@FXML
	private MenuItem m_excel;
	@FXML
	private MenuItem m_pdf;
	@FXML
	private MenuItem menuInfo;
	@FXML
	private MenuItem menuLogout;
	
	@FXML
	private ImportionTabController tradeTabController;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			
			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if(newValue == trade) {
						System.out.println("DB 연결 성공");
						try {
							tradeTabController.TradeTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(newValue == money) {
						try {
							MoneyTabController.studentTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(newValue == receipe) {
						try {
							ReceipeTabController.lessonTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}else if(newValue == product) {
						try {
							ProductTotalTabController.traineeTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			// 기능 메뉴바 이벤트
			menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
			menuInfo.setOnAction(event -> handlerMenuInfoAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그아웃 메뉴 이벤트 핸들러
	public void handlerMenuLogoutAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("육류 유통관리 프로그램");
			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) mainPane.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuInfoAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("도움말");
		alert.setHeaderText("육류 유통관리 프로그램");
		alert.setContentText("beaf distribution Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}
}
