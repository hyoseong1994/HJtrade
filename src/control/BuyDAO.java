package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BuyVO;
import model.ProductVO;

public class BuyDAO {
	// 입금 목록
	public ArrayList<BuyVO> getbuyTotalList() throws Exception {
		ArrayList<BuyVO> list = new ArrayList<>();

		String sql = "select * from buy order by b_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyVO bVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				bVo = new BuyVO();
				bVo.setB_no(rs.getInt("B_no"));
				bVo.setB_buyDate(rs.getString("B_buyDate"));
				bVo.setB_date(rs.getString("B_date"));
				bVo.setB_name(rs.getString("B_name"));
				bVo.setB_code(rs.getString("B_code"));
				bVo.setB_type(rs.getString("B_type"));
				bVo.setB_orgin(rs.getString("B_orgin"));
				bVo.setB_brand(rs.getString("B_brand"));
				bVo.setB_part(rs.getString("B_part"));
				bVo.setB_number(rs.getInt("B_number"));
				bVo.setB_kg(rs.getDouble("B_kg"));
				bVo.setB_cost(rs.getInt("B_cost"));
				bVo.setB_totalMoney(rs.getDouble("B_totalMoney"));
				bVo.setB_state(rs.getString("B_state"));

				list.add(bVo);
				
			}
		
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return list;
	}

	// 입고 등록
	public void getBuyRegiste(BuyVO bvo) throws Exception {
		String sql = "insert into buy values(buy_seq.nextval, ?, sysdate ,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bvo.getB_buyDate());
			pstmt.setString(2, bvo.getB_name());
			pstmt.setString(3, bvo.getB_code());
			pstmt.setString(4, bvo.getB_type());
			pstmt.setString(5, bvo.getB_orgin());
			pstmt.setString(6, bvo.getB_brand());
			pstmt.setString(7, bvo.getB_part());
			pstmt.setInt(8, bvo.getB_number());
			pstmt.setDouble(9, bvo.getB_kg());
			pstmt.setInt(10, bvo.getB_cost());
			pstmt.setDouble(11, bvo.getB_totalMoney());
			pstmt.setString(12, bvo.getB_state());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("입고 등록");
				alert.setHeaderText("입고 등록 완료");
				alert.setContentText("입고 완료");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("입고 등록");
				alert.setHeaderText("입고 등록 실패");
				alert.setContentText("입고등록 실패!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

	// 데이터 베이스에서 상품 테이블의 컬럼의 갯수
	public ArrayList<String> getBuyColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select* from buy";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// Result setMetaData 객체 변순 선언
		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
		return columnName;
	}

	// 상품 선택시 브랜드 정보 가져오기
	public ArrayList<String> getProductInfo() throws Exception {

		ArrayList<String> list = new ArrayList();

		String sql = "select DISTINCT p_brand from product";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담아줄 그릇
			pstmt = con.prepareStatement(sql);
			// jvo에서 변수들을 가져와서 sql문에 넣어준다.
			rs = pstmt.executeQuery();
			// sql을 날리고 불러온 값이 있으면 로그인결과변수 true
			while (rs.next()) {

				System.out.println(rs.getString("p_brand"));
				list.add(rs.getString("p_brand"));
			}

		} catch (SQLException e) {
			System.out.println(e);
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
			} catch (SQLException e) {
			}
		}

		return list;

	}

}
