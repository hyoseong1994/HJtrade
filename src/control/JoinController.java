package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.JoinVO;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class JoinController implements Initializable {

	@FXML
	private TextField txtId;// 직원 코드
	@FXML
	private PasswordField txtPassword;// 패스워드
	@FXML
	private PasswordField txtPasswordRepeat;// 패스워드확인
	@FXML
	private TextField txtName;// 이름
	@FXML
	private TextField txtAddress;// 주소
	@FXML
	private TextField txtPhone;// 핸드폰번호
	@FXML
	private Button btnCancel;// 닫기버튼
	@FXML
	private Button btnJoin;// 직원등록버튼
	@FXML
	private Button btnOverlap;// 직원코드 중복검사

	// 인스턴스 생성
	JoinVO joinVO = new JoinVO();

	// 초기설정
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 등록 버튼 비활성화
		btnJoin.setDisable(true);

		// 텍스트필드 설정
		txtPassword.setEditable(false);// 패스워드수정불가
		txtPasswordRepeat.setEditable(false);// 패스워드 확인 수정불가
		txtName.setEditable(false);// 이름 수정불가
		txtAddress.setEditable(false);// 주소 수정불가
		txtPhone.setEditable(false);// 핸드폰번호 수정불가

		// 버튼 이벤트 등록
		btnOverlap.setOnAction(event -> handlerBtnOverlapActoion(event)); // 아이디 중복 검사
		btnJoin.setOnAction(event -> handlerBtnJoinActoion(event)); // 직원 등록
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 등록창 닫기
	}

	// 아이디 중복 검사
	public void handlerBtnOverlapActoion(ActionEvent event) {
		// 인스턴스 null값 초기화
		JoinDAO jDao = null;

		// 직원코드 넣을 변수 선언
		String searchId = "";
		// 아이디 중복검사 결과값 저장을 위한 변수선언
		boolean searchResult = true;

		try {
			// 입력되 직원코드 변수에 저장
			searchId = txtId.getText().trim();
			// dao 인스턴스
			jDao = new JoinDAO();
			// 아이디 중복검사 결과 저장
			searchResult = (boolean) jDao.getIdOverlap(searchId);

			// 결과가 ture 이고 공란이 아닐시 실행
			if (!searchResult && !searchId.equals("")) {

				// 직원 코드 사용가능하다는 정보창 출력
				txtId.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 있습니다.");
				alert.setContentText("패스워드를 입력하세요.");
				alert.showAndWait();

				// 필드 설정
				btnJoin.setDisable(false);
				btnOverlap.setDisable(true);
				txtPassword.setEditable(true);
				txtPasswordRepeat.setEditable(true);
				txtName.setEditable(true);
				txtAddress.setEditable(true);
				txtPhone.setEditable(true);
				txtPassword.requestFocus();

				// 버튼 설정
				btnJoin.setDisable(false);
				btnOverlap.setDisable(true);

				// 공란일 시 실행
			} else if (searchId.equals("")) {
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디를 입력하시오.");
				alert.setContentText("등록할 아이디를 입력하세요!");
				alert.showAndWait();

				// 결과가 false일 시 실행
			} else {

				// 직원 코드 텍스트 필드 초기화
				txtId.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다.");
				alert.setContentText("아이디를 다른것으로 입력하세요.");
				alert.showAndWait();

				// 직원 코드 번호 텍스트 필드로 커서 이동
				txtId.requestFocus();
			}
			// 직원코드 중복 검사중 오류 발생시 오류 내용 출력
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 등록창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		// 로그인 UI로 이동
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("직원 로그인");
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnJoin.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
			// 로그인 UI로 이동중 오류 발생시 오류내용 출력
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("오류" + e);
		}
	}

	// 직원 등록
	public void handlerBtnJoinActoion(ActionEvent event) {
		// VO, DAO null값 초기화
		JoinVO jvo = null;
		JoinDAO jdao = null;

		// 등록 결과값 저장을위한 변수 선언
		boolean joinSucess = false;

		// 패스워드 확인
		if (txtPassword.getText().trim().equals(txtPasswordRepeat.getText().trim())
				&& !txtName.getText().trim().equals("")) {

			// VO 에 텍스트필드값 입력
			jvo = new JoinVO(txtId.getText().trim(), txtPassword.getText().trim(), txtName.getText().trim(),
					txtAddress.getText().trim(), txtPhone.getText().trim());
			// DAO 인스턴스 생성
			jdao = new JoinDAO();
			try {
				// 반환된 결과값을 변수에 저장
				joinSucess = jdao.getManagerRegiste(jvo);

				// 결과값이 true 일 경우 로그인 UI로 이동
				if (joinSucess) {
					handlerBtnCancelActoion(event);
				}
				// 직원등록중 오류 발생시 오류 내용 출력
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// 패스워드와 패스워드 확인이 같지 않거나 이름이 공란일 경우 실행
		} else {
			txtPassword.clear();
			txtPasswordRepeat.clear();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("패스워드, 이름 확인");
			alert.setHeaderText("패스워드, 이름 확인 검사에 오류가 발생하였습니다.");
			alert.setContentText("패스워드와 이름을 다시 입력하세요.");
			alert.showAndWait();
		}
	}
}
