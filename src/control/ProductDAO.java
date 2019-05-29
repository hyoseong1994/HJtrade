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
	public ArrayList<ProductVO> getProductTotalList() throws Exception {
		// ArrayList배열 생성
		ArrayList<ProductVO> list = new ArrayList<>();
		// sql문
		String sql = "select * from product order by p_no";

		// connection, preparedstatement, ResultSet, ProductVO null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pVo = null;
		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			// sql문을 날리고 결과를 저장
			rs = pstmt.executeQuery();

			// 결과를 가져오기위한 반복문
			while (rs.next()) {
				// 인스턴스 생성
				pVo = new ProductVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				pVo.setP_no(rs.getInt("p_no"));
				pVo.setP_type(rs.getString("P_type"));
				pVo.setP_origin(rs.getString("p_origin"));
				pVo.setP_brand(rs.getString("p_brand"));
				pVo.setP_part(rs.getString("p_part"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추가한다.
				list.add(pVo);
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

		// list 객체 배열 반환
		return list;
	}

	// 상품등록
	public void getProductRegiste(ProductVO pvo) throws Exception {
		// sql문
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";

		// connection, preparedstatement null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getP_type());
			pstmt.setString(2, pvo.getP_origin());
			pstmt.setString(3, pvo.getP_brand());
			pstmt.setString(4, pvo.getP_part());

			// 결과값 변수에 저장
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
		// ArrayList배열 생성
		ArrayList<String> columnName = new ArrayList<String>();
		// sql문
		String sql = "select* from product";

		// connection, preparedstatement, ResultSet, ResultSetMetaData null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// Result setMetaData 객체 변순 선언
		ResultSetMetaData rsmd = null;
		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			// sql문을 날리고 결과를 저장
			rs = pstmt.executeQuery();
			// 결과값 저장
			rsmd = rs.getMetaData();
			// 결과값 변수에 저장
			int cols = rsmd.getColumnCount();

			// 테이블 입력하기 위한 for문
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
				System.out.println(se);
			}
		}
		// 결과값 columName 반환
		return columnName;
	}

	// 상품 인덱스 가져오기
	public String getproductNum(String origin, String brand, String part) {
		// sql문
		String sql = "select P_no from product " + "where p_origin = ? and p_brand = ? and p_part = ?";

		// connection, preparedstatement, ResultSet null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String P_no = "";

		try {

			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, brand);
			pstmt.setString(3, part);

			// 결과값 변수에 저장
			rs = pstmt.executeQuery();

			// 변수에 결과값 저장
			if (rs.next()) {
				P_no = rs.getString("P_no");
			}

		} catch (SQLException se) {
			System.out.println(se + "1");
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
		// 결과값 P_no 반환
		return P_no;
	}

}