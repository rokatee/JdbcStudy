/*=========================
  Test001.java
  - Database 연결 테스트
=========================*/

package com.test;

import java.sql.Connection;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		// 연결 객체 준비
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공~!!!");
		}
		
		DBConn.close();
	}
}
