package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.StockVO;

public class StockDAO {

	// 재고 테이블 전체 리스트
	public ArrayList<StockVO> getStockTotalList() {
		ArrayList<StockVO> list = new ArrayList<>();

		String sql = "select s.s_no, b.b_date, b.b_code, p.p_type, p.p_origin, p.p_brand, p.p_part,"
				+ " s.s_number, s.s_kg, s.s_cost, s.s_totalMoney, s.s_state" + " from stock s, buy b, product p"
				+ " where s.s_no = b.s_no and s.p_no = p.p_no and s.s_no = b.s_no" + " order by s.s_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StockVO sVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 인스턴스 생성
				sVo = new StockVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				sVo.setS_no(rs.getInt("s_no"));
				sVo.setB_date(rs.getString("b_date"));
				sVo.setB_code(rs.getString("b_code"));
				sVo.setP_type(rs.getString("p_type"));
				sVo.setP_origin(rs.getString("p_origin"));
				sVo.setP_brand(rs.getString("p_brand"));
				sVo.setP_part(rs.getString("p_part"));
				sVo.setS_number(rs.getInt("s_number"));
				sVo.setS_kg(rs.getDouble("s_kg"));
				sVo.setS_cost(rs.getInt("s_cost"));
				sVo.setS_totalMoney(rs.getInt("s_totalMoney"));
				sVo.setS_state(rs.getString("s_state"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추가한다.
				list.add(sVo);
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

	// 출고의 의한 재고 수정
	public boolean getStockUpdateStock(int selectedStockIndex, String s_number, String s_kg, String s_cost) {
		String sql = "update Stock set S_number = S_number-? , S_kg = S_kg-?, txt_S_cost = txt_S_cost-? where S_no = selectedStockIndex";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean stockUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(s_number));
			pstmt.setDouble(2, Integer.parseInt(s_kg) * 0.1);
			pstmt.setInt(3, Integer.parseInt(s_cost));
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("출고의 의한 재고 수정");
				alert.setHeaderText(" 출고의 의한 재고 수정 완료.");
				alert.setContentText("출고의 의한 재고 수정 성공!!!");
				alert.showAndWait();
				stockUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("출고의 의한 재고 수정");
				alert.setHeaderText("출고의 의한 재고 수정 실패.");
				alert.setContentText("출고의 의한 재고 수정 실패!!!");
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
		return stockUpdateSucess;
	}

	// 출고
	public boolean getDeal(String d_dealDate, String d_number, String d_kg, String d_cost, int selectedStockIndex,
			String s_state, int p_no, int a_no) {

		String sql = "insert into deal"
				+ "(d_no, d_date, d_dealDate, a_no, s_no, p_no, d_number, d_kg, d_cost, d_totalmoney)" + " values "
				+ "(Deal_seq.nextval, sysdate, ?,  ?,selectedStockIndex, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean DealUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, d_dealDate);
			pstmt.setInt(2, a_no);
			pstmt.setInt(3, p_no);
			pstmt.setInt(4, Integer.parseInt(d_number));
			pstmt.setDouble(5, Integer.parseInt(d_kg) * 0.1);
			pstmt.setInt(6, Integer.parseInt(d_cost));
			pstmt.setDouble(7, (Integer.parseInt(d_kg) * 0.1) * Integer.parseInt(d_cost));
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("출고 확인");
				alert.setHeaderText(" 출고 완료.");
				alert.setContentText("출고 성공!!!");
				alert.showAndWait();
				DealUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("출고 확인");
				alert.setHeaderText("출고 실패.");
				alert.setContentText("출고 실패!!!");
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
		return DealUpdateSucess;
	}
}
