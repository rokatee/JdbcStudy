/*===========================
 	Test002.java
 	- 쿼리문 전송 실습 2
============================*/

/*
실행 예)

번호 입력(-1 종료) : 8
이름 입력 : 홍길동
전화번호 입력 : 010-8888-8888
>> 데이터베이스 연결 성공
>> 회원 정보 입력 완료

번호 입력(-1 종료) : 9
이름 입력 : 고길동
전화번호 입력 : 010-9999-9999
>> 데이터베이스 연결 성공
>> 회원 정보 입력 완료

번호 입력(-1 종료) : -1
>> 데이터베이스 연결 종료
>> 프로그램 종료

*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.util.DBConn;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("\n번호 입력(-1 종료) : ");
				String sid = sc.next();
				
				if (sid.equals("-1"))
				{
					break;
				}
				
				System.out.print("이름 입력 : ");
				String name = sc.next();
				System.out.print("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println(">> 데이터베이스 연결 성공");
					
					try
					{
						// ※ 작업 객체 생성 전에 쿼리문 준비
						String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(?, ?, ?)";
						
						// 작업 객체 생성
						PreparedStatement pstmt = conn.prepareStatement(sql);
						// → 작업 객체 생성 과정에서 쿼리문 전달
						
						// 『?』 매개변수 전달
						pstmt.setInt(1, Integer.parseInt(sid));
						pstmt.setString(2, name);
						pstmt.setString(3, tel);
						
						// 작업 객체 실행 과정(execute)에서
						// 쿼리문 전달되지 않음
						int result = pstmt.executeUpdate();
						
						if (result > 0)
							System.out.println(">> 회원 정보 입력 완료");
						
						pstmt.close();
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
			
			} while (true);
			
			DBConn.close();
			System.out.println(">> 데이터베이스 연결 닫힘");
			System.out.println(">> 프로그램 종료");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end main
	
} // end class Test002

/*
번호 입력(-1 종료) : 8
이름 입력 : 홍길동
전화번호 입력 : 010-8888-8888
>> 데이터베이스 연결 성공
>> 회원 정보 입력 완료

번호 입력(-1 종료) : 9
이름 입력 : 고길동
전화번호 입력 : 010-9999-9999
>> 데이터베이스 연결 성공
>> 회원 정보 입력 완료

번호 입력(-1 종료) : -1
>> 데이터베이스 연결 닫힘
>> 프로그램 종료

*/