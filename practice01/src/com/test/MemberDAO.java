/*=========================================
  MemberDAO.java
  - 데이터베이스 액션 처리 전용 객체 구성
=========================================*/

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 변수 선언(주요 속성 구성) → DB 연결 객체
	private Connection conn;
	
	// 생성자 정의 → 사용자 정의 생성자
	public MemberDAO()
	{
		// DB 연결
		conn = DBConn.getConnection();
	}
	
	// 기능 → 메소드 정의 → 데이터를 입력하는 기능 → insert 쿼리문 수행
	public int add(MemberDTO dto) throws SQLException
	{
		// 반환할 결과값을 담을 변수(적용된 행의 개수)
		int result = 0;
	
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
	
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								 + " VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')"
								, dto.getName(), dto.getTel());
	
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
	
		// 리소스 반납
		stmt.close();
	
		// 최종 결과값 반환
		return result;
	
	} // end add()
	
	
	// 기능 → 메소드 정의 → 인원 수를 파악하는 기능 → select 쿼리문 수행
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
	
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
	
		// 쿼리문 실행 → select 쿼리문 → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
	
		// ResultSet 처리 → 반복문 구성
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	} //end count()
	
	
	// 기능 → 메소드 정의 → 데이터 전체 조회하는 기능 → select 쿼리문 수행
	public ArrayList<MemberDTO> list() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		// 쿼리문 준비 → select 
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		// 쿼리문 실행 → select 쿼리문 → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
		// ResultSet 처리 → 반복문 구성 → MemberDTO 인스턴스 생성 → 속성 구성 → ArrayList에 적재
		while (rs.next())
		{
			// MemberDTO 객체 생성
			MemberDTO dto = new MemberDTO();
			
			// 생성된 MemberDTO 객체에 속성들 채워넣기
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			// 리스트에 값 넣기
			// ArrayList<ScoreDTO>타입의 result변수에 dto데이터 추가
			result.add(dto);
		}
	
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	} // end lists()
	
	// 메소드 정의 → 데이터베이스 연결 종료 → DBConn활용
	public void close()
	{
		DBConn.close();
	}
	
} // end class MemberDAO
