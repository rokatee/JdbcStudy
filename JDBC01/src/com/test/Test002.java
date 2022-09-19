/*==========================
  Test002.java
  - 데이터 입력 실습 진행
==========================*/

package com.test;

import java.sql.Connection;
import java.sql.Statement;

import com.util.DBConn;

public class Test002
{
	public static void main(String[] args)
	{
		// 연결 객체 준비
		Connection conn = DBConn.getConnection();
		
		if (conn == null)
		{
			System.out.println("데이터베이스 연결 실패~!!!");
			System.exit(0);
		}
		
		// System.out.println("데이터베이스 연결 성공~!!!");
		
		try
		{
			// 작업 객체 준비
			Statement stmt = conn.createStatement();
			
			// 데이터 입력 쿼리 실행 과정
			// 한 번 실행하면(입력하면) 다시 실행하지 못한다
			// 기본키 제약조건이 설정되어 있으므로...
			
			// 쿼리문 준비
			String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(2, '민찬우', '010-2222-2222')";
			// -- 주의. 『""』 안에 쿼리문 끝의 『;』 붙이지 않는다.
			// -- 주의. 자바에서 실행한 DML 구문은 내부적으로 자동 COMMIT 된다.
			// -- 주의. 오라클에서 트랜잭션 처리가 끝나지 않으면
			//          데이터 액션 처리가 이루어지지 않는다
			//			(즉, 오라클에서 직접 쿼리문을 테스트할 경우
			//			 COMMIT 또는 ROLLBACK 을 반드시 수행할 수 있도록 한다.)
			
			int result = stmt.executeUpdate(sql);
			
			if (result > 0)
			{
				System.out.println("데이터 입력 성공~!!!");
			}
			else
			{
				System.out.println("데이터 입력 실패~ :(");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		DBConn.close();
		
	}
}








