
--※ 프로시저 있으니 컴파일하려면 블럭 잡아서 실행할 것

SELECT USER
FROM DUAL;
--==>> SCOTT;

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
6	장영준	010-6666-6666
7	임시연	010-7777-7777
8	홍길동	010-8888-8888
9	고길동	010-9999-9999
*/

--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명 : PRC_MEMEBERINSERT
--   프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VSID  IN TBL_MEMBER.SID%TYPE
, VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    --VSID    TBL_MEMBER.SID%TYPE;
BEGIN 
    
    -- 기존 SID 의 최대값 얻어오기
--    SELECT NVL(MAX(SID),0) INTO VSID
--    FROM TBL_MEMBER;
    
    -- 데이터 입력 쿼리문 구성
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES(VSID, VNAME, VTEL);
    
    -- 커밋
    COMMIT;

END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.

--○ 테이블 조회
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
6	장영준	010-6666-6666
7	임시연	010-7777-7777
8	홍길동	010-8888-8888
9	고길동	010-9999-9999
10	김보경	010-0022-0022
*/

COMMIT;
--==>> 커밋 완료.

--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명 : PRC_MEMBERSELECT
--   프로시저 기능 : TBL_MEMBER 테이블의 데이터를 읽어오는 프로시저
--   ※ 『SYS_REFCURSOR』자료형을 이용하여 커서 다루기 
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT 
( VRESULT   OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN VRESULT FOR 
    SELECT SID, NAME, TEL
    FROM TBL_MEMBER
    ORDER BY SID;
    --CLOSE VRESULT;
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.
