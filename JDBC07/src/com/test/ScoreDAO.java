package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	public Connection connection()
	{
		conn = DBConn.getConnection();
		
		return conn;	
	}
	
	// 성적 입력
	public int add(ScoreDTO dto)
	{
		int result = 0;
		
		String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMat());
			
			result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println(">> 데이터 입력 성공");
			
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	
	// 전체 리스트
	public ArrayList<ScoreDTO> list()
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
						+ ", (KOR + ENG + MAT) AS TOT"
						+ ", (KOR + ENG + MAT) / 3 AS AVG"
						+ ", RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK"
				  + " FROM TBL_SCORE"
				  + " ORDER BY SID ASC";
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ScoreDTO dto = new ScoreDTO();
				dto.setSid(rs.getString("SID"));
				dto.setName(rs.getString("NAME"));
				dto.setKor(rs.getInt("Kor"));
				dto.setEng(rs.getInt("ENG"));
				dto.setMat(rs.getInt("MAT"));
				dto.setTot(rs.getInt("TOT"));
				dto.setAvg(rs.getDouble("AVG"));
				dto.setRank(rs.getInt("RANK"));
				
				result.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return result;
	}
	
	
	// 이름 검색 
	public ArrayList<ScoreDTO> list(String name)
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = String.format("SELECT *"
								+ " FROM"
								+ " ("
									+ "SELECT SID, NAME, KOR, ENG, MAT"
									     + ", (KOR+ENG+MAT) AS TOT"
									     + ", (KOR+ENG+MAT)/3 AS AVG"
									     + ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
								   + " FROM TBL_SCORE"
								 + ")"
								+ " WHERE NAME LIKE ?", name);
							  //+ " WHERE NAME LIKE %||?||%", name);	//line 119와 연동
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%%%"+name+"%%");
			//pstmt.setString(1, name);									//line 113과 연동
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ScoreDTO dto = new ScoreDTO();
				dto.setSid(rs.getString("SID"));
				dto.setName(rs.getString("NAME"));
				dto.setKor(rs.getInt("Kor"));
				dto.setEng(rs.getInt("ENG"));
				dto.setMat(rs.getInt("MAT"));
				dto.setTot(rs.getInt("TOT"));
				dto.setAvg(rs.getDouble("AVG"));
				dto.setRank(rs.getInt("RANK"));
				
				result.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
		
	} // end 이름검색

	
	// 번호 검색
	public ArrayList<ScoreDTO> list(int sid)
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT"
									  + ", (KOR + ENG + MAT) AS TOT"
									  + ", (KOR + ENG + MAT) / 3 AS AVG"
									  + ", RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK"
								+ " FROM TBL_SCORE WHERE SID = ?", sid);
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ScoreDTO dto = new ScoreDTO();
				dto.setSid(rs.getString("SID"));
				dto.setName(rs.getString("NAME"));
				dto.setKor(rs.getInt("Kor"));
				dto.setEng(rs.getInt("ENG"));
				dto.setMat(rs.getInt("MAT"));
				dto.setTot(rs.getInt("TOT"));
				dto.setAvg(rs.getDouble("AVG"));
				dto.setRank(rs.getInt("RANK"));
				
				result.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	
	// 인원 수 조회
	public int count()
	{
		int result = 0;
		
		try
		{
			String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				result = rs.getInt("COUNT");
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	
	// 성적 수정
	public int modify(ScoreDTO dto)
	{
		int result = 0;
		
		try
		{
			String sql = String.format("UPDATE TBL_SCORE"
									   + " SET NAME = ?"
									      + ", KOR = ?"
									      + ", ENG = ?"
									      + ", MAT = ?"
									 + " WHERE SID = ?"
									 ,dto.getName()
									 , dto.getKor(), dto.getEng(), dto.getMat()
									 , dto.getSid());
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMat());
			pstmt.setString(5, dto.getSid());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
		
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	
	// 성적 삭제
	public int remove(int sid)
	{
		int result = 0;
		
		try
		{
			String sql = "DELETE FROM TBL_SCORE WHERE SID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sid);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}

	// 데이터베이스 연결 종료 담당 메소드
	public void close()
	{
		try
		{
			DBConn.close();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	
	} // end close() 메소드
	
}// end class ScoreDAO
