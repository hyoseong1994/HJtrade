package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CollectVO;

public class CollectTabDAO {

	public ArrayList<CollectVO> getCollectVOTotalList() {
		ArrayList<CollectVO> list = new ArrayList<>();

		String sql = "select c.c_no, c.c_date, c.c_name, a.a_businessNumber, c.c_business, c.c_collectMoney"+
		" from collect c, account a"+
		" where c.a_no = a.a_no"+
		" order by c_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CollectVO cVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 인스턴스 생성
				cVo = new CollectVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				cVo.setC_no(rs.getInt("c_no"));
				cVo.setC_date(rs.getString("c_date"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setA_businessNumber(rs.getString("a_businessNumber"));
				cVo.setC_business(rs.getString("c_business"));
				cVo.setC_collectMoney(rs.getInt("c_collectMoney"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추가한다.
				list.add(cVo);
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

	// 수금 정보 탭에서 상호명 으로 검색
	public ArrayList<CollectVO> getSearchList(String serchName) throws Exception {
		// ArrayList배열 생성
		ArrayList<CollectVO> list = new ArrayList<>();
		// 이름으로 데이터를 가져오는 sql문
		String sql = "select c.c_no, c.c_date, c.c_name, a.a_businessNumber, c.c_business, c.c_collectMoney" +
				" from collect c , account a" +
				" where c.a_no = a.a_no and c.c_name = ?" +
				" order by c_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 인스턴스 생성
		CollectVO cVo = null;
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
				cVo = new CollectVO();
				// 쿼리문을 날리고 얻은 결과에서 값을 가져와 객체의 필드값을 설정한다.
				cVo.setC_no(rs.getInt("c_no"));
				cVo.setC_date(rs.getString("c_date"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setA_businessNumber(rs.getString("a_businessNumber"));
				cVo.setC_business(rs.getString("c_business"));
				cVo.setC_collectMoney(rs.getInt("c_collectMoney"));

				// 필드값을 설정해준후 arraylist배열에 객체를 추갛ㄴ다.
				list.add(cVo);
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
