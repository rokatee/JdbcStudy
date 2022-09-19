SELECT USER
FROM DUAL;
--==>> SCOTT


--○ 데이터 잘라내기
TRUNCATE TABLE TBL_MEMBER;
--==>> Table TBL_MEMBER이(가) 잘렸습니다.

--○ 시퀀스 생성
CREATE SEQUENCE MEMBERSEQ
NOCACHE;
--==>> Sequence MEMBERSEQ이(가) 생성되었습니다.

--○ 조회(확인)
SELECT *
FROM TBL_MEMBER;
--==>> 조회 결과 없음


--○ 데이터 입력 쿼리문 준비
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '조영관', '010-1111-1111')
;


--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_MEMBER;
--> 자바에서 사용하기 위해 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_MEMBER
;
--==>>


--○ 전체 리스트 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;

