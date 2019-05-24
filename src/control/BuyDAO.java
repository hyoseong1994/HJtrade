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
import model.ImportionVO;
import model.ProductVO;

public class BuyDAO {
	// 입고 목록
	public ArrayList<BuyVO> getbuyTotalList() throws Exception {
		ArrayList<BuyVO> list = new ArrayList<>();

		String sql = "select b.b_no, b.b_buyDate, b.b_date, i.i_name, b.b_code, p.p_type, p.p_origin, p.p_brand, p.p_part, b.b_number, b.b_kg , b.b_cost, b.b_totalmoney, s.s_state"
				+ " from buy b, stock s, product p, importion i"
				+ " where b.s_no = s.s_no and b.p_no = p.p_no and b.i_no = i.i_no";
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
				bVo.setI_name(rs.getString("I_name"));
				bVo.setB_code(rs.getString("B_code"));
				bVo.setP_type(rs.getString("P_type"));
				bVo.setP_origin(rs.getString("P_origin"));
				bVo.setP_brand(rs.getString("P_brand"));
				bVo.setP_part(rs.getString("P_part"));
				bVo.setB_number(rs.getInt("B_number"));
				bVo.setB_kg(rs.getDouble("B_kg"));
				bVo.setB_cost(rs.getInt("B_cost"));
				bVo.setB_totalMoney(rs.getInt("B_totalMoney"));
				bVo.setS_state(rs.getString("S_state"));

				list.add(bVo);

			}

		} catch (SQLException se) {
			System.out.println(123);
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

		String sql = "insert into buy"
				+ "(B_no , B_buyDate , B_date, B_code ,B_number, B_kg, B_cost, B_totalmoney, I_no, P_no ,S_no)"
				+ "values" + "(buy_seq.nextval, ?, sysdate,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bvo.getB_buyDate());
			pstmt.setString(2, bvo.getB_code());
			pstmt.setInt(3, bvo.getB_number());
			pstmt.setDouble(4, bvo.getB_kg());
			pstmt.setInt(5, bvo.getB_cost());
			pstmt.setDouble(6, bvo.getB_kg() * bvo.getB_cost());
			pstmt.setInt(7, bvo.getI_no());
			pstmt.setInt(8, bvo.getP_no());
			pstmt.setInt(9, bvo.getS_no());
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

	// 거래처 상호명 가져오기
	public ArrayList<String> getImportion() throws Exception {

		ArrayList<String> list = new ArrayList();

		String sql = "select i_name from Importion";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ImportionVO ivo = null;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담아줄 그릇
			pstmt = con.prepareStatement(sql);
			// jvo에서 변수들을 가져와서 sql문에 넣어준다.
			rs = pstmt.executeQuery();
			// sql을 날리고 불러온 값이 있으면 로그인결과변수 true
			while (rs.next()) {

				list.add(rs.getString("i_name"));
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

	// 상호명으로 번호 가져오기
	public String getImportionNum(String i_name) throws Exception {

		String sql = "select i_no from Importion where i_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String i_no = "";

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, i_name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				i_no = rs.getString("i_no");
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
		return i_no;
	}

	public ArrayList<String> getOrigin() throws Exception {

		ArrayList<String> list = new ArrayList();

		String sql = "select DISTINCT p_origin from Product";
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

				list.add(rs.getString("p_origin"));
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

	// 소 클릭시 소 부위 가져오기
	public ArrayList<String> getcowPart() throws Exception {

		ArrayList<String> list = new ArrayList();

		String sql = "select DISTINCT p_part from product where p_type ='소'";
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

				list.add(rs.getString("p_part"));
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

	public ArrayList<String> getpigPart() throws Exception {

		ArrayList<String> list = new ArrayList();

		String sql = "select DISTINCT p_part from product where p_type ='돼지'";
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

				list.add(rs.getString("p_part"));
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
