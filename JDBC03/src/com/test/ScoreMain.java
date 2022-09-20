/*=================
  ScoreMain.java
=================*/

/*
○ 실습 문제
    성적 처리 프로그램 구현 → 데이터베이스 연동 → ScoreDAO, ScoreDTO 클래스 활용
 	여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
 	총점, 평균을 연산하여 출력하는 프로그램을 구현한다.
 	출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.
 
실행 예)
1번 학생 성적 입력(이름 국어 영어 수학) : 박원석  80 75 60
2번 학생 성적 입력(이름 국어 영어 수학) : 조영관 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 김보경  80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .
-----------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
1		박원석	  80	  75	  60	 XXX	XX.X
2		조영관	 100	  90	  80	 XXX	XX.X
3		김보경	  80	  85	  80	 XXX	XX.X
-----------------------------------------------------
*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			ScoreDAO dao = new ScoreDAO();
			
			// 테스트
			//System.out.println("데이터베이스 연결 성공~!!!");
			
			int count = dao.count();
			
			do
			{
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				if (name.equals("."))
				{
					break;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
//				int tot = sc.nextInt();
//				double avg = sc.nextDouble();
				
				// ScoreDTO 객체 생성 및 멤버 구성
				ScoreDTO dto = new ScoreDTO();
				
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
//				dto.setTot(tot);
//				dto.setAvg(avg);
				
				// 데이터베이스에 데이터를 입력하는 메소드 호출 → dao.add();
				int result = dao.add(dto);
				
				if (result > 0)
				{
					System.out.println(">> 회원 정보 입력 완료");
				}
				
				
			} while (true);
			
			System.out.println();
			System.out.println("------------------------------------------------------");
			System.out.println("번호	이름	국어	영어	수학	총점	평균");
			for (ScoreDTO obj : dao.lists())
			{
				System.out.printf("%2s %7s %5d %7d %7d %8d %8.2f\n"
								  , obj.getSid(), obj.getName(), obj.getKor(), obj.getEng(), obj.getMat(), obj.getTot(), obj.getAvg());
			}
			System.out.println("------------------------------------------------------");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		finally
		{
			try
			{
				DBConn.close();
				System.out.println(">> 데이터베이스 연결 닫힘");
				System.out.println(">> 프로그램 종료됨~!!!");
			
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
}

/*
1번 학생 성적 입력(이름 국어 영어 수학) : 박원석  80 75 60
>> 회원 정보 입력 완료
2번 학생 성적 입력(이름 국어 영어 수학) : 조영관 100 90 80
>> 회원 정보 입력 완료
3번 학생 성적 입력(이름 국어 영어 수학) : 김보경  80 85 80
>> 회원 정보 입력 완료
4번 학생 성적 입력(이름 국어 영어 수학) : .

------------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
 1     박원석    80      75      60      215    71.67
 2     조영관   100      95      80      275    91.67
 3     김보경    80      85      80      245    81.67
------------------------------------------------------
>> 데이터베이스 연결 닫힘
>> 프로그램 종료됨~!!!
*/
