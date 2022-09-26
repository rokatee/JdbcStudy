/*======================================================
	Test001.java
	- CallableStatement 를 활용한 SQL 구문 전송 실습 1
======================================================*/

package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("번호 입력(-1 종료) : ");
				String sid = sc.next();
				
				if (sid.equals("-1"))
					break;
				
				System.out.print("이름 입력 : ");
				String name = sc.next();
				
				System.out.print("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println(">> 데이터베이스 연결 성공");
					
					try
					{
						/*
						Statement stmt = conn.createStatement();
						String sql = "INSERT ...";
						...
						*/
						
						/*
						// PreparedStatement 작업 객체 활용
						String sql = "INSERT ... ";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						*/
						
						// CallableStatement 작업 객체 활용
						String sql = "{call PRC_MEMBERINSERT(?, ?, ?)}";
						
						CallableStatement cstmt = conn.prepareCall(sql);
						
						cstmt.setInt(1, Integer.parseInt(sid));
						cstmt.setString(2, name);
						cstmt.setString(3, tel);
						
						int result = cstmt.executeUpdate();
						
						if (result > 0)
							System.out.println(">> 회원 정보 입력 완료");
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
			} while (true);
			
			DBConn.close();
			System.out.println(">> 데이터베이스 연결 종료");
			System.out.println(">> 프로그램 종료");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
