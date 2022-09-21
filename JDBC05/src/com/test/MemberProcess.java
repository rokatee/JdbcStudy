package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberProcess
{
	private MemberDAO dao;
	
	public MemberProcess()
	{
		dao = new MemberDAO();
	}
	
	// 직원 정보 입력 기능
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);

		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			System.out.println();
			System.out.println("직원 정보 입력 -------------------------------------------------------------");
			System.out.print("이름 : ");
			String empName = sc.next();
			System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsaDate = sc.next();
			
			// 지역 리스트
			//System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/) : ");
			ArrayList<MemberDTO> memberCity = dao.cityLocation();
			
			System.out.print("지역(");
			for (MemberDTO dto : memberCity)
			{
				System.out.printf("%s / ", dto.getCityLoc());
			}
			System.out.print(") : ");
			String cityLoc = sc.next();
			
			System.out.print("전화번호 : ");
			String tel = sc.next();
			
			// 부서 리스트
			//System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
			ArrayList<MemberDTO> memberBuseo = dao.buseoList();
			
			System.out.print("부서(");
			for (MemberDTO dto : memberBuseo)
			{
				System.out.printf("%s / ", dto.getBuseoName());
			}
			System.out.print(") : ");
			String buseoName = sc.next();
			
			// 직위 리스트
			//System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : ");
			ArrayList<MemberDTO> memberJikwi = dao.jikwiList();
			
			System.out.print("직위(");
			for (MemberDTO dto : memberJikwi)
			{
				System.out.printf("%s / ", dto.getJikwiName());
			}
			System.out.print(") : ");
			String jikwiName = sc.next();
			
			System.out.printf("기본급(최소 %d원 이상) : ", dao.minBasicpay(jikwiName));
			int basicpay = sc.nextInt();
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			System.out.println();
			
			MemberDTO dto = new MemberDTO();
			dto.setEmpName(empName);
			dto.setSsn(ssn);
			dto.setIbsaDate(ibsaDate);
			dto.setCityLoc(cityLoc);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicpay(basicpay);
			dto.setSudang(sudang);
			
			int result = dao.add(dto);
			
			if (result > 0)
			{
				System.out.println(">> 직원 정보 입력 완료~!!!");
			}
			
			System.out.println("----------------------------------------------------------------------------");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end memberInsert()
	
	// 직원 전체 출력 기능
	public void memberList()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("1.  사번 정렬");
		System.out.println("2.  이름 정렬");
		System.out.println("3.  부서 정렬");
		System.out.println("4.  직위 정렬");
		System.out.println("5.  급여 내림차순 정렬");
		System.out.print(">> 선택(1~5, -1종료) : ");
		
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			
			if (menu == -1)
			{
				return;
			}
			
			String key = "";
			switch (menu)
			{
				case 1:
					// 사번 정렬
					key = "EMP_ID";
					break;
				case 2:
					// 이름 정렬
					key = "EMP_NAME";
					break;
				case 3:
					// 부서 정렬
					key = "BUSEO_NAME";
					break;
				case 4:
					// 직위 정렬
					key = "JIKWI_NAME";
					break;
				case 5:
					// 급여 내림차순 정렬
					key = "PAY DESC";
					break;
			}
			
			// 데이터베이스 연결
			dao.connection();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.count());
			System.out.println(" 사번    이름     주민번호        입사일  지역      전화번호"
							 + "    부서  직위     기본급       수당       급여");
			
			ArrayList<MemberDTO> memberList = dao.list(key);
			
			for (MemberDTO dto : memberList)
			{
				System.out.printf("%5d %5s %14s %10s %3s %13s %4s %3s %10d %10d %10d\n"
									, dto.getEmpId(), dto.getEmpName(), dto.getSsn()
									, dto.getIbsaDate(), dto.getCityLoc(), dto.getTel()
									, dto.getBuseoName(), dto.getJikwiName()
									, dto.getBasicpay(), dto.getSudang(), dto.getPay());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end memberList()
	
	// 직원 검색 출력 기능
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. 사번 검색");
		System.out.println("2. 이름 검색");
		System.out.println("3. 부서 검색");
		System.out.println("4. 직위 검색");
		System.out.print(">> 선택(1~4, -1종료) : ");
		
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			
			if (menu == -1)
			{
				return;
			}
			
			String key = "";
			String value = "";
			
			switch (menu)
			{
				case 1:
					// 사번 검색
					key = "EMP_ID";
					System.out.print("검색할 사원번호 입력 : ");
					value = sc.next();
					break;
				case 2:
					// 이름 검색
					key = "EMP_NAME";
					System.out.print("검색할 사원이름 입력 : ");
					value = sc.next();
					break;
				case 3:
					// 부서 검색
					key = "BUSEO_NAME";
					System.out.print("검색할 부서이름 입력 : ");
					value = sc.next();
					break;
				case 4:
					// 직위 검색
					key = "JIKWI_NAME";
					System.out.print("검색할 직위이름 입력 : ");
					value = sc.next();
					break;
			}
			
			// 데이터베이스 연결
			dao.connection();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.count(key, value));
			System.out.println(" 사번    이름     주민번호        입사일  지역      전화번호"
							 + "    부서  직위     기본급       수당       급여");
			
			ArrayList<MemberDTO> memberList = dao.search(key, value);
			
			for (MemberDTO dto : memberList)
			{
				System.out.printf("%5d %5s %14s %10s %3s %13s %4s %3s %10d %10d %10d\n"
									, dto.getEmpId(), dto.getEmpName(), dto.getSsn()
									, dto.getIbsaDate(), dto.getCityLoc(), dto.getTel()
									, dto.getBuseoName(), dto.getJikwiName()
									, dto.getBasicpay(), dto.getSudang(), dto.getPay());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end memberSearch()
	
	// 직원 정보 수정 기능
	public void memberUpdate()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.println();
			System.out.print("수정할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			dao.connection();
			
			ArrayList<MemberDTO> memberDTO = dao.search("EMP_ID", value);
			
			if (memberDTO.size() > 0)
			{
				System.out.println();
				System.out.println(" 사번    이름     주민번호        입사일  지역      전화번호"
								 + "    부서  직위     기본급       수당       급여");
				
				for (MemberDTO dto : memberDTO)
				{
					System.out.printf("%5d %5s %14s %10s %3s %13s %4s %3s %10d %10d %10d\n"
										, dto.getEmpId(), dto.getEmpName(), dto.getSsn()
										, dto.getIbsaDate(), dto.getCityLoc(), dto.getTel()
										, dto.getBuseoName(), dto.getJikwiName()
										, dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				
				System.out.println();
				System.out.println("직원 정보 수정 -------------------------------------------------------------");
				System.out.print("이름 : ");
				String empName = sc.next();
				System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsaDate = sc.next();
				
				// 지역 리스트
				//System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/) : ");
				ArrayList<MemberDTO> memberCity = dao.cityLocation();
				
				System.out.print("지역(");
				for (MemberDTO dto : memberCity)
				{
					System.out.printf("%s / ", dto.getCityLoc());
				}
				System.out.print(") : ");
				String cityLoc = sc.next();
				
				System.out.print("전화번호 : ");
				String tel = sc.next();
				
				// 부서 리스트
				//System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
				ArrayList<MemberDTO> memberBuseo = dao.buseoList();
				
				System.out.print("부서(");
				for (MemberDTO dto : memberBuseo)
				{
					System.out.printf("%s / ", dto.getBuseoName());
				}
				System.out.print(") : ");
				String buseoName = sc.next();
				
				// 직위 리스트
				//System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : ");
				ArrayList<MemberDTO> memberJikwi = dao.jikwiList();
				
				System.out.print("직위(");
				for (MemberDTO dto : memberJikwi)
				{
					System.out.printf("%s / ", dto.getJikwiName());
				}
				System.out.print(") : ");
				String jikwiName = sc.next();
				
				System.out.printf("기본급(최소 %d원 이상) : ", dao.minBasicpay(jikwiName));
				int basicpay = sc.nextInt();
				System.out.print("수당 : ");
				int sudang = sc.nextInt();
				System.out.println();
				
				MemberDTO dto = new MemberDTO();
				//int empId2 = dto.getEmpId();
				//dto.setEmpId(empId2);
				// 이렇게하면 사원번호를 못 불러오는 이유?
				
				
				dto.setEmpId(Integer.parseInt(value));
				dto.setEmpName(empName);
				dto.setSsn(ssn);
				dto.setIbsaDate(ibsaDate);
				dto.setCityLoc(cityLoc);
				dto.setTel(tel);
				dto.setBuseoName(buseoName);
				dto.setJikwiName(jikwiName);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
				
				int result = dao.modify(dto);
				
				if (result > 0)
				{
					System.out.println(">> 직원 정보 수정 완료~!!!");
				}
				else if (result <= 0)
				{
					System.out.println(">> 수정 정보가 담기지 않음");
				}
				
				System.out.println("----------------------------------------------------------------------------");
				
			}
			else
			{
				System.out.println("수정 대상이 존재하지 않습니다.");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end memberUpdate()
	
	// 직원 정보 삭제 기능
	public void memberDelete()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.print("삭제할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			dao.connection();
			
			ArrayList<MemberDTO> memberDTO = dao.search("EMP_ID", value);
			
			if (memberDTO.size() > 0)
			{
				System.out.println();
				System.out.println(" 사번    이름     주민번호        입사일  지역      전화번호"
								 + "    부서  직위     기본급       수당       급여");
				
				for (MemberDTO dto : memberDTO)
				{
					System.out.printf("%5d %5s %14s %10s %3s %13s %4s %3s %10d %10d %10d\n"
							, dto.getEmpId(), dto.getEmpName(), dto.getSsn()
							, dto.getIbsaDate(), dto.getCityLoc(), dto.getTel()
							, dto.getBuseoName(), dto.getJikwiName()
							, dto.getBasicpay(), dto.getSudang(), dto.getPay());
					System.out.println();
				}
				
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
				String response = sc.next();
				
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(Integer.parseInt(value));
					
					if (result > 0)
					{
						System.out.println("직원 정보가 삭제 완료되었습니다.");
					}
				}
				else
				{
					System.out.println("취소 되었습니다.");
				}
				
			}
			else
			{
				System.out.println("삭제할 대상이 존재하지 않습니다");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end memberDelete()
	
} // end class MemberProcess
