/*=======================
    ScoreMain.java
========================*/

/*
○ 성적 처리 → 데이터베이스 연동 (데이터베이스 연결 및 액션 처리)
            	ScoreDTO 클래스 활용 (속성만 존재하는 클래스, getter / setter 구성)
            	ScoreDAO 클래스 활용 (데이터베이스 액션 처리 전용 클래스 구성)
            	Process 클래스 활용 (단위 기능 구성)
            
여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
총점, 평균, 석차 등을 계산하여 출력하는 프로그램 구현.
출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.
※ 서브 메뉴 구성 → Process 클래스 활용.

실행 예)
====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 1

4번 학생 성적 입력(이름 국어 영어 수학) : 정미경 50 60 70
>> 성적 입력이 완료되었습니다.
5번 학생 성적 입력(이름 국어 영어 수학) : 조영관 80 80 80
>> 성적 입력이 완료되었습니다.
6번 학생 성적 입력(이름 국어 영어 수학) : .

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 2
전체 인원 : 5명
번호  이름  국어 영어 수학 총점  평균  석차
 1
 2
 3
 4	 조영관  80   80   80  xxx   xx.xx   x
 5
 
====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : -1
프로그램이 종료되었습니다.
*/

package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		ScoreProcess prc = new ScoreProcess();

		do
		{
			System.out.println();
			System.out.println("====[ 성적 처리 ]====");
			System.out.println("1. 성적 입력");
			System.out.println("2. 성적 전체 출력");
			System.out.println("3. 이름 검색 출력");
			System.out.println("4. 성적 수정");
			System.out.println("5. 성적 삭제");
			System.out.println("=====================");
			System.out.print(">> 선택(1~5, -1종료) : ");

			// int menu = sc.nextInt(); // 숫자 형태가 아닐 경우 에러가 뜨므로
			// 아래 try ~ catch 가 더 바람직하다
			String menus = sc.next();

			try
			{
				int menu = Integer.parseInt(menus);

				if (menu == -1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다");
					return;
				}

				switch (menu)
				{
				case 1:
					// 성적 입력 기능 수행
					prc.sungjukInsert();
					break;
				case 2:
					// 성적 전체 출력 기능 수행
					prc.sungjukSelectAll();
					break;
				case 3:
					// 이름 검색 출력 기능 수행
					prc.sungjukSearchName();
					break;
				case 4:
					// 성적 수정 출력 기능 수행
					prc.sungjukUpdate();
					break;
				case 5:
					// 성적 삭제 출력 기능 수행
					prc.sungjukDelete();
					break;
				}

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}

		} while (true);

		
	} // end main()
	
} // end class ScoreMain
