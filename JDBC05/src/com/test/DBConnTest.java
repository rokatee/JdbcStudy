package com.test;

import java.sql.Connection;

import com.util.DBConn;

public class DBConnTest
{
	public static void main(String[] args)
	{
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공");
		}
		else
		{
			System.out.println("실패....");
		}
		
		DBConn.close();
	}
}
