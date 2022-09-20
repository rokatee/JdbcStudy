/*===============
  DBConn.java
===============*/
// 예외 처리 방법 변경
// try ~ catch → throws

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	// 변수 선언
	private static Connection dbConn;
	// private : 정보은닉
	// static : 정적
	// 자동으로 초기화 지원 : null
	
	// 메소드 정의 → 연결
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@211.238.142.52:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// OracleDriver 클래스에 대한 객체 생성
			
			dbConn = DriverManager.getConnection(url, user, pwd);
			// 오라클 서버 실제 연결
		}
		
		// 연결 객체 반환
		return dbConn;
	}
	
	// getConnection() 메소드 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if(dbConn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		
		return dbConn;
	}
	
	public static void close() throws SQLException
	{
		// dbConn 변수는
		// 데이터베이스가 연결되어 있으면 Connection 을 갖는다
		// 연결되어 있지 않으면 null 인 상황이 된다
		if (dbConn != null)
		{
			// 연결 객체가 닫혀있지 않으면 닫아줘라.
			if(!dbConn.isClosed())
				dbConn.close();
		}
		
		// 연결 객체 초기화
		dbConn = null;
	}
	
}