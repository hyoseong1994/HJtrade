package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class LoginController implements Initializable {

	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnJoin;
	@FXML
	private ToggleGroup loginGroup;

	public static String name = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		txtId.setOnKeyPressed(event -> handerTxtIdKeyPressed(event)); // 아이디 입력에서 Enter키 이벤트 적용
		txtPassword.setOnKeyPressed(event -> handerTxtPasswordKeyPressed(event)); // 패스워드 입력에서 Enter키 이벤트 적용
		btnJoin.setOnAction(event -> handerBtnJoinAction(event)); // 등록창으로 전환
		btnLogin.setOnAction(event -> handlerBtnLoginActoion(event)); // 로그인
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 로그인창 닫기

	}

	// 아이디 입력에서 Enter키 이벤트 적용
	public void handerTxtIdKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtPassword.requestFocus();
		}
	}

	// 패스워드 입력에서 Enter키 이벤트 적용
	public void handerTxtPasswordKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	// 등록창으로 전환
	public void handerBtnJoinAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/join.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("직원 등록");
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnLogin.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("오류" + e);
		}
	}

	// 로그인창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		Platform.exit();
	}

	// 로그인
	public void handlerBtnLoginActoion(ActionEvent event) {
		login();
	}

	// 로그인 메소드
	public void login() {
		LoginDAO login = new LoginDAO();

		boolean sucess = false;

		try {
			name = LoginName();
			sucess = login.getLogin(txtId.getText().trim(), txtPassword.getText().trim());
			// 로그인 성공시 메인 페이지로 이동
			if (sucess) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
					Parent mainView = (Parent) loader.load();
					Scene scane = new Scene(mainView);
					Stage mainMtage = new Stage();
					mainMtage.setTitle("육류 유통관리 프로그램");
					mainMtage.setResizable(false);
					mainMtage.setScene(scane);
					Stage oldStage = (Stage) btnLogin.getScene().getWindow();
					oldStage.close();
					mainMtage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("오류" + e);
				}
			} else {
				// 아이디패스워드 확인하라는 창
				Alert alert;
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("로그인 실패");
				alert.setHeaderText("아이디와 비밀번호 불일치");
				alert.setContentText("아이디와 비밀번호가 일치하지 않습니다." + "\n 다시 제대로 입력하시오.");
				alert.setResizable(false);
				alert.showAndWait();

				txtId.clear();
				txtPassword.clear();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (txtId.getText().equals("") || txtPassword.getText().equals("")) {
			Alert alert;
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디와 비밀번호 미입력");
			alert.setContentText("아이디와 비밀번호중 입력하지 않은 항목이 있습니다." + "\n 다시 제대로 입력하시오.");
			alert.setResizable(false);
			alert.showAndWait();
		}

	}

	// 로그인 이름 가져오기
	public String LoginName() {
		LoginDAO ldao = new LoginDAO();

		String name = null;

		try {
			name = ldao.getLoginName(txtId.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
}
