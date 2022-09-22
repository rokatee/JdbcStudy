/*===========================
	Test001.java
	- 쿼리문 전송 실습 1
============================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					/*
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '고연수', '010-7777-7777')";
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공");
					
					stmt.close();
					*/
					
					/*
					// 위의 주석과 비교해서
					// Statement 와 sql 구문 순서를 바꿔도 문제가 없다
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '고연수', '010-7777-7777')";

					Statement stmt = conn.createStatement();
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공");
					
					stmt.close();
					*/
					
					/*
					// PreparedStatement 사용
					// sql 구문이 먼저 와야 한다
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '고연수', '010-7777-7777')";
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					int result = pstmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공");
					
					pstmt.close();
					*/
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(?, ?, ?)";
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 7);
					pstmt.setString(2, "임시연");
					pstmt.setString(3, "010-7777-7777");

					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("데이터 입력 성공");
					
					pstmt.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end main
	
} // end class Test001
