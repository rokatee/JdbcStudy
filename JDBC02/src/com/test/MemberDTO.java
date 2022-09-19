/*=========================================================================
  MemberDTO.java
  - 과거 우리가 실습하는 과정에서 속성만 존재하는 클래스로 설계했던 클래스
  - 데이터 저장 및 전송 전용 객체 생성
==========================================================================*/

package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	private String sid, name, tel;

	// getter / setter 구성
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
}
