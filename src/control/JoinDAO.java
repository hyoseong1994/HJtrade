package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.JoinVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JoinDAO {

	// 직원 등록
	public boolean getManagerRegiste(JoinVO jvo) throws Exception {
		// sql문
		String sql = "insert into employee2 " + "(id, password, e_name, e_adress, e_phone)" + " values "
				+ "(?, ?, ?, ?, ?)";

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		// 등록 성공여부확인을 위한 변수선언
		boolean joinSucess = false;

		try {

			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getId());
			pstmt.setString(2, jvo.getPassword());
			pstmt.setString(3, jvo.getName());
			pstmt.setString(4, jvo.getAdress());
			pstmt.setString(5, jvo.getPhone());

			// 결과값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("직원 등록");
				alert.setHeaderText(jvo.getName() + " 직원 등록 완료.");
				alert.setContentText("직원 등록 성공!!!");
				alert.showAndWait();
				joinSucess = true;
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("직원 등록");
				alert.setHeaderText("직원 등록 실패.");
				alert.setContentText("직원 등록 실패!!!");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		// 결과값 joinSucess 반환
		return joinSucess;
	}

	// 아이디 중복 체크
	public boolean getIdOverlap(String idOverlap) throws Exception {
		// sql문
		String sql = "select * from employee2 where id = ?";

		// connection, preparedstatement, ResultSet null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 아이디 중복체크 성공여부확인을 위한 변수선언
		boolean idOverlapResult = false;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idOverlapResult = true; // 중복된 아이디가 있다.
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		// 결과값 idOverlapResult 반환
		return idOverlapResult;
	}
}
