package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ImportionVO;

public class TradeTabDAO {
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
			System.out.println(se+"1");
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

	// 등록
	public void getImportionRegiste(ImportionVO iVo) throws Exception {

		String sql = "insert into Importion" 
				+ "(i_no,  i_name, i_businessNumber, I_represent, i_representPhone, "
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

	// 거래처 수정
	public boolean getImportionUpdate(int i_no, String i_name, String i_businessNumber, String i_represent,
			String i_representPhone, String i_charge, String i_chargePhone, String i_adress, String i_email,
			String i_business) throws Exception {

		String sql = "update Importion set i_name, i_businessNumber, I_represent,I_representPhone,"
				+ "I_charge, I_chargePhone, I_adress, I_email, I_business where i_no=?";
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

	// 거래처 번호
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

	
}
