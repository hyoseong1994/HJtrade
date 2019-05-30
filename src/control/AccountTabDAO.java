package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AccountVO;

public class AccountTabDAO {

	// 전체 리스트
	public ArrayList<AccountVO> getaccountVOTotalList() throws Exception {
		// ARrayList 에 VO 넣기
		ArrayList<AccountVO> list = new ArrayList<>();

		// 쿼리문
		String sql = "select * from account order by A_no";

		// connection, preparedstatement, resultset VO null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO aVo = null;

		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			// ResultSet 결과값 저장
			rs = pstmt.executeQuery();

			// VO에 DB값을 저장하기위한 반복문
			while (rs.next()) {
				aVo = new AccountVO();
				aVo.setA_no(rs.getInt("A_no"));
				aVo.setA_name(rs.getString("A_name"));
				aVo.setA_businessNumber(rs.getString("A_businessNumber"));
				aVo.setA_represent(rs.getString("A_represent"));
				aVo.setA_representPhone(rs.getString("A_representPhone"));
				aVo.setA_charge(rs.getString("A_charge"));
				aVo.setA_chargePhone(rs.getString("A_chargePhone"));
				aVo.setA_address(rs.getString("A_address"));
				aVo.setA_email(rs.getString("A_email"));
				aVo.setA_business(rs.getString("A_business"));
				aVo.setA_collect(rs.getInt("A_collect"));

				list.add(aVo);
			}

		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		// 결과값 list 반환
		return list;
	}

	// 판매 거래처 등록
	public void getAccountRegiste(AccountVO aVo) throws Exception {

		// 쿼리문
		String sql = "insert into Account" + "(A_no,  A_name, A_businessNumber, A_represent, A_representPhone, "
				+ "A_charge, A_chargePhone, A_address, A_email, A_business, A_collect)" + " values "
				+ "(Account_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, aVo.getA_name());
			pstmt.setString(2, aVo.getA_businessNumber());
			pstmt.setString(3, aVo.getA_represent());
			pstmt.setString(4, aVo.getA_representPhone());
			pstmt.setString(5, aVo.getA_charge());
			pstmt.setString(6, aVo.getA_chargePhone());
			pstmt.setString(7, aVo.getA_address());
			pstmt.setString(8, aVo.getA_email());
			pstmt.setString(9, aVo.getA_business());
			pstmt.setInt(10, aVo.getA_collect());

			// 결과값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 등록");
				alert.setHeaderText(aVo.getA_name() + " 등록 완료.");
				alert.setContentText("거래처 등록 성공!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래처 등록");
				alert.setHeaderText("거래처 등록 실패.");
				alert.setContentText("거래처 등록 실패!!!");
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
	}

	// 데이터베이스에서 거래처 테이블의 컬럼의 갯수
	public ArrayList<String> getaccountColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>();

		// 쿼리문
		String sql = "select * from account";

		// connection, preparedstatement, resultset, ResultSetMetaData null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			// ResultSet 결과값 저장
			rs = pstmt.executeQuery();
			// ResultSetMetaData 결과값 저장
			rsmd = rs.getMetaData();

			// 결과값 변수에 저장
			int cols = rsmd.getColumnCount();

			// 결과를 컬럼에 넣기 위한 for문
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		// 결과값 columnName 반환
		return columnName;
	}

	// 판매 거래처 수정
	public boolean getaccountUpdate(int A_no, String A_name, String A_businessNumber, String A_represent,
			String A_representPhone, String A_charge, String A_chargePhone, String A_adress, String A_email,
			String A_business) throws Exception {

		// 쿼리문
		String sql = "update account set A_name=?, A_businessNumber=?, A_represent=? ,A_representPhone=?,"
				+ "A_charge=?, A_chargePhone=?, A_address=?, A_email=?, A_business=? where A_no=?";
		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;

		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, A_name);
			pstmt.setString(2, A_businessNumber);
			pstmt.setString(3, A_represent);
			pstmt.setString(4, A_representPhone);
			pstmt.setString(5, A_charge);
			pstmt.setString(6, A_chargePhone);
			pstmt.setString(7, A_adress);
			pstmt.setString(8, A_email);
			pstmt.setString(9, A_business);
			pstmt.setInt(10, A_no);

			// 결과값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 수정");
				alert.setHeaderText(A_name + " 거래처 수정 완료.");
				alert.setContentText("거래처 수정 성공!!!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 수정");
				alert.setHeaderText("거래처 수정 실패.");
				alert.setContentText("거래처 수정 실패!!!");
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
		// 결과값 accountUpdateSucess 반환
		return accountUpdateSucess;
	}

	// 판매거래처 번호
	public String getaccountNum(String A_name) throws Exception {

		// 쿼리문
		String sql = "select A_no from account where A_name = ?";
		// connection, preparedstatement, resultset null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 결과값 저장을 위한 변수 선언
		String A_no = "";

		try {

			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, A_name);
			// ResultSet 결과값 저장
			rs = pstmt.executeQuery();
			if (rs.next()) {
				A_no = rs.getString("A_no");
			}

		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		// 결과값 A_no 반환
		return A_no;
	}

	// 거래처 삭제
	public boolean getaccountDelete(int A_no) throws Exception {

		// 쿼리문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from account where A_no = ?");

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean imporiontDeleteSucess = false;

		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, A_no);

			// 결과값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 삭제");
				alert.setHeaderText("거래처 삭제 완료.");
				alert.setContentText("거래처 삭제 성공!!!");
				alert.showAndWait();
				imporiontDeleteSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 삭제");
				alert.setHeaderText("거래처 삭제 실패.");
				alert.setContentText("거래처 삭제 실패!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ⑥ 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		// 결과값 imporiontDeleteSucess 반환
		return imporiontDeleteSucess;
	}

	// 아이디 중복 체크
	public boolean getOverlapBN(String searchBN) throws Exception {

		// 쿼리문
		String sql = "select * from account where A_businessNumber = ?";

		// connection, preparedstatement, ResultSet null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean BNOverlapResult = false;

		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchBN);
			// ResultSet 결과값 저장
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BNOverlapResult = true; // 중복된 사업자번호가 있다.
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ⑥ 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		// 결과값 BNOverlapResult 반환
		return BNOverlapResult;
	}

	// 수금 등록
	public boolean getCollect(String A_name, String A_business, String A_collect, int A_no) throws Exception {

		// 쿼리문
		String sql = "insert into Collect" + "(C_no, C_date, C_name, C_business, c_collectMoney, a_no)" + " values "
				+ "(Collect_seq.nextval, sysdate, ?,  ?, ?, ?)";

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean collectUpdateSucess = false;

		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, A_name);
			pstmt.setString(2, A_business);
			pstmt.setInt(3, Integer.parseInt(A_collect));
			pstmt.setInt(4, A_no);

			// 결과 값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("수금 확인");
				alert.setHeaderText(A_name + " 수금 완료.");
				alert.setContentText("수금 성공!!!");
				alert.showAndWait();
				collectUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("수금 확인");
				alert.setHeaderText("수금 실패.");
				alert.setContentText("수금 실패!!!");
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
		// 결과값 collectUpdateSucess 반환
		return collectUpdateSucess;
	}

	// 거래처 미수금액 수정
	public boolean getaccountUpdateCollect(int A_no, String A_collect) throws Exception {

		// 쿼리문
		String sql = "update account set A_collect = A_collect-? where A_no=?";

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;

		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(A_collect));
			pstmt.setInt(2, A_no);

			// 결과 값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("미수금액 수정");
				alert.setHeaderText(" 미수금액 수정 완료.");
				alert.setContentText("미수금액 수정 성공!!!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("미수금액 수정");
				alert.setHeaderText("미수금액 수정 실패.");
				alert.setContentText("미수금액 수정 실패!!!");
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
		// 결과값 accountUpdateSucess 반환
		return accountUpdateSucess;
	}

	// 상호명을 DB에서 가져오는 쿼리문
	public ArrayList<AccountVO> getaccountName() {
		// TODO Auto-generated method stub
		ArrayList<AccountVO> list = new ArrayList<>();

		// 쿼리문
		String sql = "select a_name from account";

		// connection, preparedstatement, ResultSet null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO aVo = null;

		try {

			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			// ResultSet 결과값 저장
			rs = pstmt.executeQuery();

			// 결과를 가져오기위한 반복문
			while (rs.next()) {
				aVo = new AccountVO();
				aVo.setA_name(rs.getString("A_name"));

				list.add(aVo);
			}

		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		// 결과값 list 반환
		return list;
	}

	public boolean UpdateCollect(int A_no, String S_cost, String S_kg) throws Exception {

		// 쿼리문
		String sql = "update account set A_collect = A_collect + ? where A_no = ?";

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;

		int total = Integer.parseInt(S_cost) * Integer.parseInt(S_kg);

		System.out.println(total);
		try {
			// DB 연결
			con = DBUtil.getConnection();
			// PreparedStatement 에 쿼리문 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, total);
			pstmt.setInt(2, A_no);

			// 결과 값 변수에 저장
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("미수금액 수정");
				alert.setHeaderText(" 미수금액 수정 완료.");
				alert.setContentText("미수금액 수정 성공!!!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("미수금액 수정");
				alert.setHeaderText("미수금액 수정 실패.");
				alert.setContentText("미수금액 수정 실패!!!");
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
		// 결과값 accountUpdateSucess 반환
		return accountUpdateSucess;
	}
}