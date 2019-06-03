package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ProductVO;

public class ProductDAO {

	// 상품목록
	public ArrayList<ProductVO> getProductTotalList() {
		ArrayList<ProductVO> list = new ArrayList<>();
		// 쿼리문
		String sql = "select * from product order by p_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pVo = new ProductVO();
				pVo.setP_no(rs.getInt("p_no"));
				pVo.setP_type(rs.getString("P_type"));
				pVo.setP_origin(rs.getString("p_origin"));
				pVo.setP_brand(rs.getString("p_brand"));
				pVo.setP_part(rs.getString("p_part"));

				list.add(pVo);
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

	// 상품등록
	public void getProductRegiste(ProductVO pvo) throws Exception {
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getP_type());
			pstmt.setString(2, pvo.getP_origin());
			pstmt.setString(3, pvo.getP_brand());
			pstmt.setString(4, pvo.getP_part());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상품 등록");
				alert.setHeaderText(pvo.getP_part() + "상품 등록 완료");
				alert.setContentText("상품 등록 완료");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("상품 등록");
				alert.setHeaderText("상품 등록 실패");
				alert.setContentText("상품등록 실패!!");
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
	public ArrayList<String> getProductColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select* from product";
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

	// 상품 인덱스 가져오기
	public String getproductNum(String origin, String brand, String part) {
		// 쿼리문
		String sql = "select P_no from product " + "where p_origin = ? and p_brand = ? and p_part = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String P_no = "";

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, brand);
			pstmt.setString(3, part);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				P_no = rs.getString("P_no");
			}

		} catch (SQLException se) {
			System.out.println(se + "1");
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
		return P_no;
	}

}