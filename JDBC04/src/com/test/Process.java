package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
	// 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → ScoreDAO
	private ScoreDAO dao;
	
	// 생성자 정의 → 사용자 정의 생성자
	public Process()
	{
		dao = new ScoreDAO();
	}
	
	// 주요 기능 구성
	// - 성적 입력 기능
	public void sungjukInsert()
	{
		try
		{
			// 데이터베이스 연결
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
					System.out.println(">> 성적 입력이 완료되었습니다.");
				}
				
			} while (true);
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	// - 성적 전체 출력 기능
	public void sungjukSelectAll()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호  이름  국어 영어 수학 총점  평균  석차");
			
			// 반복문을 통한 리스트 출력
			for (ScoreDTO dto : dao.list())
			{
				System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
						, dto.getSid(), dto.getName()
						, dto.getKor(), dto.getEng(), dto.getMat()
						, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
		
	// - 이름 검색 출력 기능
	public void sungjukSearchName()
	{
		try
		{
			// 검색할 이름 입력받기
			Scanner sc = new Scanner(System.in);
			
			System.out.print("검색할 이름 입력 : ");
			String name = sc.next();
			
			// 필요한 경우 이 과정에서 프로그래밍적으로 검증(검사) 수행
			// ex. 이름에 숫자가 들어가면 데이터베이스 연결 하지 않는 경우
			
			// 데이터베이스 연결
			dao.connection();
			
			// dao 의 list() 메소드 호출 → 매개변수로 검색할 이름 넘겨주기
			ArrayList<ScoreDTO> arrayList = dao.list(name);
			
			if (arrayList.size() > 0)
			{
				// 리스트 출력
				System.out.println("번호  이름  국어 영어 수학 총점  평균  석차");
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
			}
			else
			{
				// 검색 대상이 존재하지 않음
				System.out.println("검색 결과가 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// - 성적 수정 기능
	public void sungjukUpdate()
	{
		try
		{
			// 수정할 학생의 번호 입력
			Scanner sc = new Scanner(System.in);
			System.out.println("수정할 학생의 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			// 입력받은 번호로 체크해야 할 로직 적용 삽입 가능
			// ex. 번호만 입력해야하는데 숫자나 특수문자가 입력되어 있으면 db연결 하지 않는 것
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.list(sid);
			
			if (arrayList.size() > 0)
			{
				// 대상 확인
				System.out.println("번호  이름  국어 영어 수학 총점  평균  석차");
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
				String name = sc.next();
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// dto 구성
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				dto.setSid(String.valueOf(sid));
				
				int result = dao.modify(dto);
				if (result > 0)
					System.out.println(">> 수정이 완료되었습니다");
				
			}
			else
			{
				// 수정 대상이 존재하지 않는 상황
				System.out.println(">> 수정 대상이 존재하지 않습니다");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// - 성적 삭제 기능
	public void sungjukDelete()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.println("삭제할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.list(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호 이름 국어 영어 수학 충점 평균 석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.println(">> 정말 삭제하시겠습니까?(Y/N) : ");
				String response = sc.next();
				
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(sid);
					if (result > 0)
					{
						System.out.println("삭제 완료");
					}
					else
					{
						System.out.println("삭제 취소");
					}
				}
			}
			else
			{
				System.out.println("삭제할 대상이 존재하지 않습니다");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
}
