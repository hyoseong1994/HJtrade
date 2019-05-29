package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DealVO;

public class DealDAO {

	// 수금 리스트 DB에서 호출
	public ArrayList<DealVO> getDealVOTotalList() {
		// ArrayList배열 생성
		ArrayList<DealVO> list = new ArrayList<>();
		// sql문
		String sql = "select d_no, d_number, d_kg, d_cost, d_buyDate, d_totalMoney,"
				+ " s_state, a_name, b_code,  p_type, p_origin, p_brand,  p_part, d_date"
				+ " from deal d, account a, product p, stock s"
				+ " where d.a_no = a.a_no and d.s_no = s.s_no and d.p_no = p.p_no" + " order by d_no";

		// connection, preparedstatement, ResultSet, DealVO null값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DealVO dVo = null;

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
				dVo = new DealVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				dVo.setD_no(rs.getInt("d_no"));
				dVo.setD_dealDate(rs.getString("d_dealDate"));
				dVo.setD_date(rs.getString("d_date"));
				dVo.setA_name(rs.getString("a_name"));
				dVo.setB_code(rs.getString("b_code"));
				dVo.setP_type(rs.getString("p_type"));
				dVo.setP_origin(rs.getString("p_origin"));
				dVo.setP_brand(rs.getString("p_brand"));
				dVo.setP_part(rs.getString("p_part"));
				dVo.setD_number(rs.getInt("d_number"));
				dVo.setD_kg(rs.getDouble("d_kg"));
				dVo.setD_cost(rs.getInt("d_cost"));
				dVo.setD_totalMoney(rs.getInt("d_totalMoney"));
				dVo.setS_state(rs.getString("s_state"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추가한다.
				list.add(dVo);
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
}
