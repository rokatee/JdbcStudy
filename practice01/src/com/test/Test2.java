package com.test;

import java.sql.Connection;
import java.sql.Statement;

import com.util.DBConn;

public class Test2
{
	public static void main(String[] args)
	{
		Connection conn = DBConn.getConnection();
		
		if (conn == null)
		{
			System.out.println("데이터베이스 연결 실패");
			System.exit(0);
		}
		
		try
		{
			Statement stmt = conn.createStatement();
			
			String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(2, '민찬우', '010-2222-2222')";
			
			int result = stmt.executeUpdate(sql);
			
			if (result > 0)
			{
				System.out.println("데이터 입력 성공");
			}
			else 
			{
				System.out.println("데이터 입력 실패");				
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		DBConn.close();
	}
}
