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
import model.BuyVO;
import model.ImportionVO;

public class ImportionTabDAO {

	// 전체 리스트
	public ArrayList<ImportionVO> getImportionVOTotalList() throws Exception {
		ArrayList<ImportionVO> list = new ArrayList<>();

		String sql = "select * from importion order by i_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ImportionVO iVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				iVo = new ImportionVO();
				iVo.setI_no(rs.getInt("i_no"));
				iVo.setI_name(rs.getString("i_name"));
				iVo.setI_businessNumber(rs.getString("i_businessNumber"));
				iVo.setI_represent(rs.getString("i_represent"));
				iVo.setI_representPhone(rs.getString("i_representPhone"));
				iVo.setI_charge(rs.getString("i_charge"));
				iVo.setI_chargePhone(rs.getString("i_chargePhone"));
				iVo.setI_address(rs.getString("i_address"));
				iVo.setI_email(rs.getString("i_email"));
				iVo.setI_business(rs.getString("i_business"));
				iVo.setI_payment(rs.getInt("i_payment"));

				list.add(iVo);
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

	// 매입거래처 등록
	public void getImportionRegiste(ImportionVO iVo) throws Exception {

		String sql = "insert into Importion" + "(i_no,  i_name, i_businessNumber, I_represent, i_representPhone, "
				+ "I_charge, I_chargePhone, I_address, I_email, I_business, I_payment)" + " values "
				+ "(importion_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iVo.getI_name());
			pstmt.setString(2, iVo.getI_businessNumber());
			pstmt.setString(3, iVo.getI_represent());
			pstmt.setString(4, iVo.getI_representPhone());
			pstmt.setString(5, iVo.getI_charge());
			pstmt.setString(6, iVo.getI_chargePhone());
			pstmt.setString(7, iVo.getI_address());
			pstmt.setString(8, iVo.getI_email());
			pstmt.setString(9, iVo.getI_business());
			pstmt.setInt(10, iVo.getI_payment());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 등록");
				alert.setHeaderText(iVo.getI_name() + " 등록 완료.");
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
	public ArrayList<String> getImportionColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from importion";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
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
			}
		}
		return columnName;
	}

	// 매입거래처 수정
	public boolean getImportionUpdate(int i_no, String i_name, String i_businessNumber, String i_represent,
			String i_representPhone, String i_charge, String i_chargePhone, String i_adress, String i_email,
			String i_business) throws Exception {

		String sql = "update Importion set i_name=?, i_businessNumber=?, I_represent=? ,I_representPhone=?,"
				+ "I_charge=?, I_chargePhone=?, I_address=?, I_email=?, I_business=? where i_no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean importionUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, i_name);
			pstmt.setString(2, i_businessNumber);
			pstmt.setString(3, i_represent);
			pstmt.setString(4, i_representPhone);
			pstmt.setString(5, i_charge);
			pstmt.setString(6, i_chargePhone);
			pstmt.setString(7, i_adress);
			pstmt.setString(8, i_email);
			pstmt.setString(9, i_business);
			pstmt.setInt(10, i_no);
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 수정");
				alert.setHeaderText(i_name + " 거래처 수정 완료.");
				alert.setContentText("거래처 수정 성공!!!");
				alert.showAndWait();
				importionUpdateSucess = true;
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
		return importionUpdateSucess;
	}

	// 매입 거래처 번호
	public int getImportionNum(String i_name) throws Exception {

		String sql = "select i_no from Importion where i_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int i_no = 0;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, i_name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				i_no = rs.getInt("i_no");
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

	// 거래처 삭제
	public boolean getImportionDelete(int i_no) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from Importion where i_no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean imporiontDeleteSucess = false;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, i_no);

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
		return imporiontDeleteSucess;
	}

	// 아이디 중복 체크
	public boolean getOverlapBN(String searchBN) throws Exception {

		String sql = "select * from importion where i_businessNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean BNOverlapResult = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchBN);
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
		return BNOverlapResult;
	}

	// 미입금금액 입금
	public boolean getPayment(String I_name, String I_businessNumber, String I_business, String I_payment, int I_no)
			throws Exception {

		String sql = "insert into Payment" + "(P_no, P_date, P_name, P_business, P_paymentMoney, I_no)" + " values "
				+ "(payment_seq.nextval, sysdate, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean paymentUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, I_name);
			pstmt.setString(2, I_business);
			pstmt.setInt(3, Integer.parseInt(I_payment));
			pstmt.setInt(4, I_no);
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("입금 확인");
				alert.setHeaderText(I_name + " 입금 완료.");
				alert.setContentText("입금 성공!!!");
				alert.showAndWait();
				paymentUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("입금 확인");
				alert.setHeaderText("입금 실패.");
				alert.setContentText("입금 실패!!!");
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
		return paymentUpdateSucess;
	}

	// 미입금금액 수정
	public boolean getaccountUpdateCollect(int I_no, String I_payment) throws Exception {

		String sql = "update Importion set I_payment = I_payment+? where I_no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean importionUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(I_payment) * (-1));
			pstmt.setInt(2, I_no);
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("입금금액 수정");
				alert.setHeaderText(" 입금금액 수정 완료.");
				alert.setContentText("입금금액 수정 성공!!!");
				alert.showAndWait();
				importionUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("입금금액 수정");
				alert.setHeaderText("입금금액 수정 실패.");
				alert.setContentText("입금금액 수정 실패!!!");
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
		return importionUpdateSucess;
	}

	// 상호명을 DB에서 가져오는 쿼리문
	public ArrayList<ImportionVO> getimportionName() {
		// TODO Auto-generated method stub
		ArrayList<ImportionVO> list = new ArrayList<>();

		String sql = "select i_name from importion";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ImportionVO iVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				iVo = new ImportionVO();
				iVo.setI_name(rs.getString("I_name"));

				list.add(iVo);
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

	// 거래처 정보 가져오기
	public ArrayList<ImportionVO> getImportionInfo(String i_name) throws Exception {

		ArrayList<ImportionVO> list = new ArrayList();

		String sql = "select i_address , i_business ,i_representPhone from Importion where i_name = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ImportionVO ivo = null;

		try {
			// DB연동
			con = DBUtil.getConnection();
			// sql문을 담아줄 그릇
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, i_name);
			// jvo에서 변수들을 가져와서 sql문에 넣어준다.
			rs = pstmt.executeQuery();
			// sql을 날리고 불러온 값이 있으면 로그인결과변수 true
			while (rs.next()) {
				ivo = new ImportionVO();

				ivo.setI_address(rs.getString("I_address"));
				ivo.setI_business(rs.getString("i_business"));
				ivo.setI_representPhone(rs.getString("i_representPhone"));

				list.add(ivo);

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
	public ArrayList<BuyVO> getselectTotalList(String i_name) throws Exception {
		ArrayList<BuyVO> list = new ArrayList<>();

		String sql = "select b.b_no, b.b_buyDate, b.b_date, i.i_name, b.b_code, p.p_type, p.p_origin, p.p_brand, p.p_part, b.b_number, b.b_kg , b.b_cost, b.b_totalmoney,s.s_state"
				+ " from buy b, product p, importion i, stock s" + " where b.p_no = p.p_no and b.i_no = i.i_no and b.s_no = s.s_no and i.i_name=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyVO bVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, i_name);
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
}
