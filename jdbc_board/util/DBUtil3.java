package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// 이 클래스는 JDBC드라이버를 로딩하고 DB시스템에 접속하여 Connection객체를 반환하는 역할을 수행하는 클래스이다.

public class DBUtil3 {
	private static ResourceBundle bundle;
	
	// static 초기화 블럭
	static {
		bundle = ResourceBundle.getBundle("dbinfo"); // ResourceBundle객체 생성
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~~");
			e.printStackTrace();
		}
	}
	// DB시스템에 접속한 후 접속 정보를 갖는 Connection객체를 반환하는 메소드
	public static Connection getConnection() {
		try {
			String url = bundle.getString("url");
			String user = bundle.getString("user");
			String password = bundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패!!");
			return null;
		}
	}
}
