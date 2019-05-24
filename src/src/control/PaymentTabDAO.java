package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PaymentVO;

public class PaymentTabDAO {

	// 입금 테이블 쿼리문
	public ArrayList<PaymentVO> getPaymentVOTotalList() {
		ArrayList<PaymentVO> list = new ArrayList<>();

		String sql = "select p.p_no, p.p_date, p.p_name, i.i_businessNumber, p.p_business, p.p_paymentMoney"
				+ " from payment p , importion i" + " where p.i_no = i.i_no" + " order by P_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PaymentVO pVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 인스턴스 생성
				pVo = new PaymentVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				pVo.setP_no(rs.getInt("P_no"));
				pVo.setP_date(rs.getString("P_date"));
				pVo.setP_name(rs.getString("P_name"));
				pVo.setI_businessNumber(rs.getString("I_businessNumber"));
				pVo.setP_business(rs.getString("P_business"));
				pVo.setP_paymentMoney(rs.getInt("P_paymentMoney"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추갛ㄴ다.
				list.add(pVo);
			}

		} catch (SQLException se) {
			System.out.println(se);
			se.printStackTrace();
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

	// 검색 쿼리문
	public ArrayList<PaymentVO> getSearchList(String serchName) {
		// ArrayList배열 생성
		ArrayList<PaymentVO> list = new ArrayList<>();
		// 이름으로 데이터를 가져오는 sql문
		String sql = "select p.p_no, p.p_date, p.p_name, i.i_businessNumber, p.p_business, p.p_paymentMoney"
				+ " from payment p , importion i" + " where p.i_no = i.i_no and p.p_name = ?" + " order by P_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 인스턴스 생성
		PaymentVO pVo = null;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담을 그릇
			pstmt = con.prepareStatement(sql);
			// 학번입력
			pstmt.setString(1, serchName);
			// sql문을 날리고 결과를 저장
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 인스턴스 생성
				pVo = new PaymentVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				pVo.setP_no(rs.getInt("P_no"));
				pVo.setP_date(rs.getString("P_date"));
				pVo.setP_name(rs.getString("P_name"));
				pVo.setI_businessNumber(rs.getString("I_businessNumber"));
				pVo.setP_business(rs.getString("P_business"));
				pVo.setP_paymentMoney(rs.getInt("P_paymentMoney"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추갛ㄴ다.
				list.add(pVo);

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// DB연결 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
			}
		}

		// customerVO 객체 배열 반환
		return list;
	}

}
