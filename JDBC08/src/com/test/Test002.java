/*======================================================
	Test002.java
	- CallableStatement 를 활용한 SQL 구문 전송 실습 2
======================================================*/

package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class Test002
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
					String sql = "{call PRC_MEMBERSELECT(?)}";
					
					CallableStatement cstmt = conn.prepareCall(sql);
					
					// 프로시저 내부에서 SYS_REFCURSOR 를 사용하고 있기 때문에
					// OracleType.CURSOR 를 사용하기 위한 등록 과정이 필요한 상황
					
					// 1. Project Explorer 상에서 해당 프로젝트 마우스 우클릭
					//    > Build Path > Configure Build Path 클릭
					//	  > Libraries 탭 선택 > Add External JARs 버튼 클릭
					//    > 『ojdbc6.jar』 또는 『ojdbc14.jar』파일 추가 등록
					//    (외부 jar 파일 연결)
					// 2. 『import oracle.jdbc.OracleTypes;』 구문 추가
					
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					ResultSet rs = (ResultSet)cstmt.getObject(1);
					// → ResultSet에 바로 담지 못하므로 다운캐스팅을 하고 난 뒤 담는다
					
					while (rs.next())
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %12s", sid, name, tel);
						
						System.out.println(str);
					}
					
					rs.close();
					cstmt.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			} // end if
			
			DBConn.close();
			
			System.out.println("\n>> 데이터베이스 연결 닫힘");
			System.out.println(">> 프로그램 종료");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	} // end main
} // end class Test002

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
 10     김보경 010-0022-0022

>> 데이터베이스 연결 닫힘
>> 프로그램 종료
*/
