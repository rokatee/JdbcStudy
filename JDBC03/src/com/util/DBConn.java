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
	private static Connection dbConn;
	// 정보은닉, 정적, 자동으로 초기화 지원 = null
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@211.238.142.52:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		
		return dbConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		return dbConn;
	}
	
	public void close() throws SQLException
	{
		if (dbConn != null)
		{
			if (!dbConn.isClosed())
			{
				dbConn.close();
			}
		}
		
		dbConn = null;
	}
}
