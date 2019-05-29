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
	private TextField txtId;// 직원코드
	@FXML
	private PasswordField txtPassword;// 패스워드
	@FXML
	private Button btnCancel;// 닫기 버튼
	@FXML
	private Button btnLogin;// 로그인 버튼
	@FXML
	private Button btnJoin;// 직원 등록 버튼

	// 이름 변수 선언
	public static String name = "";

	// 초기 설정
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 텍스트 필드 Enter 이벤트 등록
		txtId.setOnKeyPressed(event -> handerTxtIdKeyPressed(event)); // 아이디 입력에서 Enter키 이벤트 적용
		txtPassword.setOnKeyPressed(event -> handerTxtPasswordKeyPressed(event)); // 패스워드 입력에서 Enter키 이벤트 적용

		// 버튼 이벤트 등록
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
			login();// 로그인 메소드 호출
		}
	}

	// 등록창으로 전환
	public void handerBtnJoinAction(ActionEvent event) {
		// 직원 등록 UI
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
			// 직원 등록 UI 실행중 오류시 오류 내용 출력
		} catch (IOException e) {
			System.err.println("오류" + e);
		}
	}

	// 로그인창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		Platform.exit();
	}

	// 로그인
	public void handlerBtnLoginActoion(ActionEvent event) {
		login();// 로그인 메소드 호출
	}

	// 로그인 메소드
	public void login() {
		// DAO 인스턴스 선언
		LoginDAO login = new LoginDAO();

		// 결과값 저장을 위한 변수 선언
		boolean sucess = false;

		try {
			name = LoginName();// 로그인 메소드 호출및 결과값 name에 저장
			// 결과값 변수에 저장
			sucess = login.getLogin(txtId.getText().trim(), txtPassword.getText().trim());
			// 로그인 성공시 메인 페이지로 이동
			if (sucess) {
				// 로그인 UI 실행
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
					// 로그인 UI 실행중 오류시 오류내용출력
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("오류" + e);
				}
				// 로그인 실패시 출력
			} else {
				// 아이디패스워드 확인하라는 창
				Alert alert;
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("로그인 실패");
				alert.setHeaderText("아이디와 비밀번호 불일치");
				alert.setContentText("아이디와 비밀번호가 일치하지 않습니다." + "\n 다시 제대로 입력하시오.");
				alert.setResizable(false);
				alert.showAndWait();

				// 텍스트 필드 초기화
				txtId.clear();
				txtPassword.clear();
			}
			// 로그인 실행중 오류시 오류내용출력
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 직원코드 또는 패스워드 공란일떄 실행
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
		// DAO 인스턴스 선언
		LoginDAO ldao = new LoginDAO();

		// 코드 변수 null값 초기화
		String code = null;

		try {
			// 텍스트 필드에 있는 직원코드 입력
			code = ldao.getLoginName(txtId.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 결과값 name 반화
		return name;
	}
}
