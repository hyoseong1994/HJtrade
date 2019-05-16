package control;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
<<<<<<< HEAD
	static final String url = "jdbc:oracle:thin:@192.168.0.6:1521:xe";
=======
	static final String url = "jdbc:oracle:thin:@192.168.0.103:1521:orcl";
>>>>>>> c77a76ff00ab8c1f365f79581fd32129303a2e35

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "hr", "1234");
		System.out.println("DB 연결 성공");
		return con;
	}
}
