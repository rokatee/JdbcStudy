/*===========================
 	Test002.java
 	- 쿼리문 전송 실습 3
============================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConn;

public class Test003
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println(">> 데이터베이스 연결 성공");
				
				try
				{
					//쿼리문 준비
					String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
					
					// PreparedStatement 작업 객체 생성(쿼리문 전달)
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					// 작업 객체 실행 → executeQuery() → 쿼리문 전달하지 않음 → ResultSet 반환
					ResultSet rs = pstmt.executeQuery();
					
					// ResultSet 처리 → 반복문 구성 (단일 레코드일 경우 조건문 가능)
					while (rs.next())
					{
						int sid = rs.getInt("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3d %7s %12s", sid, name, tel);
						
						System.out.println(str);
					}

					rs.close();
					pstmt.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
			DBConn.close();
			
			System.out.println(">> 데이터베이스 연결 닫힘");
			System.out.println(">> 프로그램 종료");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}

/*
>> 데이터베이스 연결 성공
  1     장현성 010-1111-1111
  2     정미경 010-2222-2222
  3     엄소연 010-3333-3333
  4     박원석 010-4444-4444
  5     김유림 010-5555-5555
  6     장영준 010-6666-6666
  7     임시연 010-7777-7777
  8     홍길동 010-8888-8888
  9     고길동 010-9999-9999
>> 데이터베이스 연결 닫힘
>> 프로그램 종료
*/