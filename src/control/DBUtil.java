package control;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
<<<<<<< HEAD
	static final String url = "jdbc:oracle:thin:@198.168.0.103:1521:orcl";
=======
	static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";
>>>>>>> c5e8f71aca9519994a0f12fb162736012fa70d27

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "scott", "tiger");
		System.out.println("DB 연결 성공");
		return con;
	}
}
