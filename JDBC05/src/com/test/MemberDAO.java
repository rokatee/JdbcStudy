package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 속성 구성
	private Connection conn;
	
	// 주요 기능 구성
	// - 데이터베이스 연결 담당 메소드
	public Connection connection()
	{
		conn = DBConn.getConnection();
		
		return conn;
	}
	
	// 데이터 입력 담당 메소드
	// 기능 → 메소드 정의 → 데이터를 입력하는 기능 → insert 쿼리문 수행
	// 1. 직원 정보 입력
	public int add(MemberDTO dto) throws SQLException
	{
		// 반환할 결과값을 담을 변수(적용된 행의 개수)
		int result = 0;
		// 작업 객체 생성	
		Statement stmt = conn.createStatement();
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN"
													+ ", IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
								 + " VALUES (EMPSEQ.NEXTVAL, '%s', '%s'"
								 		+ ", TO_DATE('%s', 'YYYY-MM-DD')"
								 		+ ", (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC = '%s')"
								 		+ ", '%s'"
								 		+ ", (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
								 		+ ", (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s')"
								 		+ ", %d, %d)"
								 , dto.getEmpName(), dto.getSsn(), dto.getIbsaDate()
								 , dto.getCityLoc(), dto.getTel(), dto.getBuseoName()
								 , dto.getJikwiName(), dto.getBasicpay(), dto.getSudang());
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
		// 리소스 반납
		stmt.close();
		// 최종 결과값 반환
		return result;
		
	} // end add()
	
	
	// 직원 전체 출력 담당 메소드
	// 2. 직원 전체 출력
	public ArrayList<MemberDTO> list(String key) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
			
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN"
									  + ", TO_CHAR(IBSADATE, 'YYYY-MM-DD') AS IBSADATE"
									  + ", CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, PAY"
								 + " FROM EMPVIEW"
								 + " ORDER BY %s", key);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityLoc(rs.getString("CITY_LOC"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	} // end list()
	
	
	// 인원 수 조회
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		return result;
	} // end count()
	
	
	// 세부 인원 수 조회(ex. 사번 검색, 이름 검색, 부서 검색, 직위 검색)
	public int count(String key, String value) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT COUNT(*) AS COUNT FROM EMPVIEW WHERE %s = '%s'", key, value);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		return result;
	} // end count()
	
	
	// 3. 직원 검색 출력
	public ArrayList<MemberDTO> search(String key, String value) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN"
									  + ", TO_CHAR(IBSADATE, 'YYYY-MM-DD') AS IBSADATE"
									  + ", CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, PAY"
									  + " FROM EMPVIEW"
									  + " WHERE %s = '%s'", key, value);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityLoc(rs.getString("CITY_LOC"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	} // end search()
	
	
	// 지역 리스트 조회
	public ArrayList<MemberDTO> cityLocation() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		
		String sql = "SELECT CITY_LOC FROM TBL_CITY";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setCityLoc(rs.getString("CITY_LOC"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();

		return result;
		
	} // end cityLocation() 
	
	
	// 부서 리스트 조회
	public ArrayList<MemberDTO> buseoList() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		
		String sql = "SELECT BUSEO_NAME FROM TBL_BUSEO";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();

		return result;
		
	} // end buseoList()
	
	
	// 직위 리스트 조회
	public ArrayList<MemberDTO> jikwiList() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		
		String sql = "SELECT JIKWI_NAME FROM TBL_JIKWI";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();

		return result;
		
	} // end jikwiList()
	
	
	// 기본급 리스트 조회
	public int minBasicpay(String value) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT MIN_BASICPAY FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", value);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
//			MemberDTO dto = new MemberDTO();
//			dto.setBasicpay(rs.getInt("MIN_BASICPAY"));
			result = rs.getInt("MIN_BASICPAY");
		}
		
		rs.close();
		stmt.close();

		return result;
		
	} // end minBasicpay()
	
	
	// 4. 직원 정보 수정
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("UPDATE TBL_EMP"
								   + " SET EMP_NAME = '%s', SSN = '%s'"
								   + ", IBSADATE = TO_DATE('%s', 'YYYY-MM-DD')"
								   + ", CITY_ID = (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC = '%s')"
								   + ", TEL = '%s'"
								   + ", BUSEO_ID = (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
								   + ", JIKWI_ID = (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s')"
								   + ", BASICPAY = %d, SUDANG = %d"
								 + " WHERE EMP_ID = %d"
								 	, dto.getEmpName(), dto.getSsn(), dto.getIbsaDate()
								 	, dto.getCityLoc(), dto.getTel(), dto.getBuseoName()
								 	, dto.getJikwiName(), dto.getBasicpay(), dto.getSudang()
								 	, dto.getEmpId());
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
		
	} // end modify()
	
	
	// 5. 직원 정보 삭제
	public int remove(int empId) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", empId);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
		
	} // end remove()
	
} // end class MemberDAO
