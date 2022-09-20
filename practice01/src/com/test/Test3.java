package com.test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class Test3
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Connection conn = DBConn.getConnection();
		
		do
		{
			System.out.print("번호 입력(-1 종료) : ");
			String sid = sc.next();
			
			if (sid.equals("-1"))
			{
				break;
			}
			
			System.out.print("이름 입력 : ");
			String name = sc.next();
			
			System.out.print("번호 입력 : ");
			String tel = sc.next();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공");
				
				try
				{
					Statement stmt = conn.createStatement();
					
					String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(%s, '%s', '%s')"
												, sid, name, tel);
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
					{
						System.out.println("회원 정보 입력 완료");
					}
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
			
		} while (true);
		
		sc.close();
		DBConn.close();
		System.out.println("데이터 베이스 닫힘");
		System.out.println("프로그램 종료");
		
	}
}
