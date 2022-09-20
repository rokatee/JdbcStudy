package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			MemberDAO dao = new MemberDAO();
			
			//테스트
//			System.out.println("데이터베이스 연결 성공");
			
			int count = dao.count();
			
			do
			{
				System.out.printf("이름 전화번호 입력(%s) : ", ++count);
				String name = sc.next();
				
				if (name.equals("."))
				{
					break;
				}
				
				String tel = sc.next();
				
				MemberDTO dto = new MemberDTO();
				
				dto.setName(name);
				dto.setTel(tel);
				
				int result = dao.add(dto);
				
				if (result > 0)
				{
					System.out.println("데이터입력성공");
				}
				
			} while (true);
			
			System.out.println("-------------------------------");
			System.out.printf("전체회원수 : %d명\n",count);
			System.out.println("-------------------------------");
			System.out.println("번호	이름	전화번호");
			
			for (MemberDTO obj : dao.list())
			{
				System.out.printf("%3s %6s %12s\n"
								, obj.getSid(), obj.getName(), obj.getTel() );
			}
			System.out.println("-------------------------------");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		finally 
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘 및 프로그램 종료");
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
}
