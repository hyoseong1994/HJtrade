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
import control.StudentTabController;
import control.SubjectTabController;
import control.LessonTabController;
import control.TraineeTotalTabController;
import javafx.application.Platform;

public class MainController implements Initializable {
	
	@FXML
	private TabPane mainPane;
	@FXML
	private Tab subject;
	@FXML
	private SubjectTabController subjectTabController; // ���������� �ο� ���: include�� ����� id+"Controller"
	@FXML
	private Tab student;
	@FXML
	private StudentTabController studentTabController;
	@FXML
	private Tab lesson;
	@FXML
	private LessonTabController lessonTabController;
	@FXML
	private Tab traineeTotal;
	@FXML
	private TraineeTotalTabController traineeTotalTabController;

	// �޴�
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			
			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if(newValue == subject) {
						System.out.println("�а�");
						try {
							subjectTabController.subjectTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(newValue == student) {
						try {
							studentTabController.studentTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(newValue == lesson) {
						try {
							lessonTabController.lessonTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}else if(newValue == traineeTotal) {
						try {
							traineeTotalTabController.traineeTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			// �޴� �̺�Ʈ ���
			menuExit.setOnAction(event -> handlerMenuExitAction(event));
			menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
			menuInfo.setOnAction(event -> handlerMenuInfoAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޴� �̺�Ʈ �ڵ鷯
	public void handlerMenuLogoutAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("�̷� ���б� �л����");
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
		alert.setTitle("�̷� ���б�");
		alert.setHeaderText("�̷� ���б� ������û ���α׷�");
		alert.setContentText("Future Universit Register Courses Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerMenuExitAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�̷� ���б�");
		alert.setHeaderText("�̷� ���б� ������û ���α׷� ����");
		alert.setContentText("Ȯ�� ��ư�� Ŭ���ϸ� �̷� ���б� ������û ���α׷� �����մϴ�.");
		alert.setResizable(false);
		alert.showAndWait();
		
		Platform.exit();
	}

}
