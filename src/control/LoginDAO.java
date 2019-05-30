package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	// 로그인 쿼리문
	public boolean getLogin(String loginId, String loginPassword) throws Exception {
		// sql문
		String sql = "select * from employee where id = ? and password = ?";

		// connection, preparedstatement, ResultSet null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 로그인 성공여부 저장을 위한 변수선언
		boolean loginResult = false;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPassword);
			// sql문을 날리고 결과를 저장
			rs = pstmt.executeQuery();
			// 로그인 성공여부 저장
			if (rs.next()) {
				loginResult = true;
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
		// 결과값 loginResult 반환
		return loginResult;
	}

	// 이름 찾기 퀴리문
	public String getLoginName(String loginId) throws Exception {
		// sql문
		String sql = "select e_name from employee where id = ?";

		// connection, preparedstatement, ResultSet null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 찾은 이름을 저장하기위한 변수 선언
		String loginName = null;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			// sql문을 날리고 결과를 저장
			rs = pstmt.executeQuery();
			// 찾은 이름 저장
			if (rs.next()) {
				loginName = rs.getString(1);
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
		// 결과값 loginName 반환
		return loginName;
	}
}
