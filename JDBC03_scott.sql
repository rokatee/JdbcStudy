SELECT USER
FROM DUAL;
--==>> SCOTT


--○ 실습 테이블 생성(TBL_SCORE)
CREATE TABLE TBL_SCORE
( SID   NUMBER
, NAME  VARCHAR2(30)
, KOR   NUMBER(3)
, ENG   NUMBER(3)
, MAT   NUMBER(3)
);
--==>> Table TBL_SCORE이(가) 생성되었습니다.

--○ 제약조건 추가(SID 컬럼에 PK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD CONSTRAINT SCORE_SID_PK PRIMARY KEY(SID);
--==>> Table TBL_SCORE이(가) 변경되었습니다.

--○ 제약조건 추가(KOR, ENG, MAT 컬럼에 CK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD ( CONSTRAINT SCORE_KOR_CK CHECK(KOR BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_ENG_CK CHECK(ENG BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_MAT_CK CHECK(MAT BETWEEN 0 AND 100) );
--==>> Table TBL_SCORE이(가) 변경되었습니다.



--○ 데이터 잘라내기
TRUNCATE TABLE TBL_SCORE;
--==>> Table TBL_SCORE이(가) 잘렸습니다.

--○ 시퀀스 생성
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ이(가) 생성되었습니다.

--○ 조회(확인)
SELECT *
FROM TBL_SCORE;
--==>> 조회 결과 없음

--○ 데이터 입력 쿼리문 준비
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(MEMBERSEQ.NEXTVAL, '박원석', 80, 75, 60)
;

--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> 자바에서 사용하기 위해 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--○ 전체 리스트 조회 쿼리문 구성
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR+ENG+MAT) AS TOT
     , (KOR+ENG+MAT)/3 AS AVG 
FROM TBL_SCORE 
ORDER BY SID ASC;
--> 자바에서 사용하기 위해 한 줄 구성
SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT)AS TOT, (KOR+ENG+MAT)/3 AS AVG FROM TBL_SCORE ORDER BY SID ASC
;