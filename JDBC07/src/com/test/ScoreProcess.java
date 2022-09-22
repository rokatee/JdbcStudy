package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	private ScoreDAO dao;
	
	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}
	
	public void sungjukInsert()
	{
		try
		{
			dao.connection();
			
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			do
			{
				System.out.println();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				if (name.equals("."))
				{
					break;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO();
				
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				int result = dao.add(dto);
				
				if (result > 0)
				{
					System.out.println("성적 입력 완료");
				}
				
			} while (true);
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	
	} // end sungjukInsert()
	
	public void sungjukSelectAll()
	{
		try
		{
			dao.connection();
			
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호   이름    국어  영어  수학 총점    평균 석차");
			
			for (ScoreDTO dto : dao.list())
			{
				System.out.printf("%3s %5s %5d %5d %5d %5d %7.2f %3d\n"
									, dto.getSid(), dto.getName()
									, dto.getKor(), dto.getEng(), dto.getMat()
									, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	} // end sungjukSelectAll()
	
	public void sungjukSearchName()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("검색할 이름 입력 : ");
			String name = sc.next();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.list(name);
			
			if (arrayList.size() > 0)
			{
				System.out.println();
				System.out.println("번호   이름    국어  영어  수학 총점    평균 석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %5s %5d %5d %5d %5d %7.2f %3d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
			} else
			{
				System.out.println("검색한 이름이 존재하지 않습니다");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end sungjukSearchName()
	
	public void sungjukUpdate()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.list(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println();
				System.out.println("번호   이름    국어  영어  수학 총점    평균 석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %5s %5d %5d %5d %5d %7.2f %3d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.println();
				System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
				String name = sc.next();
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				dto.setSid(String.valueOf(sid));
				
				int result = dao.modify(dto);
				
				if (result > 0)
				{
					System.out.println("수정 완료되었습니다.");
				}
				
			} else
			{
				System.out.println("수정할 대상이 존재하지 않습니다");
			}

			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end sungjukUpdate()
	
	
	public void sungjukDelete()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.list(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println();
				System.out.println("번호   이름    국어  영어  수학 총점    평균 석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %5s %5d %5d %5d %5d %7.2f %3d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.println();
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
				String response = sc.next();
				
				if (response.equalsIgnoreCase("Y"))
				{
					int result = dao.remove(sid);
					
					if (result > 0)
					{
						System.out.println("삭제 완료되었습니다.");
					}
				} else
				{
					System.out.println("취소되었습니다.");
				}
			} else
			{
				System.out.println("삭제할 대상이 존재하지 않습니다");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	} // end sungjukDelete()
	
} // end class ScoreProcess
