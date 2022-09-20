package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBConn;

public class Test4
{
	public static void main(String[] args)
	{
		// 데이터베이스 연결 객체 생성
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공");
			
			try
			{
				// 작업 객체 생성
				Statement stmt = conn.createStatement();
				// 쿼리문 준비
				String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY 1";
				// 쿼리문 수행 → 결과 집합 수신
				ResultSet rs = stmt.executeQuery(sql);
				
				// 반복문을 활용한 ResultSet 컨트롤(제어)
				while (rs.next())
				{
					// 레코드에서 결과값을 가져오는 것은 getter() 메소드
					String sid = rs.getString("SID");
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL");
					
					String str = String.format("%3s %8s %12s", sid,name,tel);
					
					System.out.println(str);
				}

				// 리소스 반납
				rs.close();
				stmt.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		// 연결객체 리소스 반납
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘 및 프로그램 종료");
	
	}
}
