SELECT USER
FROM DUAL;
--==>> SCOTT;

SELECT *
FROM TBL_MEMBER;
--==>> 
/*
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
*/

--○ 데이터 입력 쿼리문 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '장영준', '010-6666-6666');
--> 한 줄 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '장영준', '010-6666-6666')
;
--==>> 1 행 이(가) 삽입되었습니다.

COMMIT;
--==>> 커밋 완료.

--○ Test001.java 실행 후 조회
SELECT *
FROM TBL_MEMBER;
--==>>
/*
7	임시연	010-7777-7777
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
6	장영준	010-6666-6666
*/

--○ 전체 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
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


--○ 테이블 조회
SELECT *
FROM TBL_SCORE;

-- 인서트 쿼리문 작성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '민찬우', 90, 80, 70)
;

-- 전체 리스트 조회 쿼리문 작성
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT) / 3 AS AVG
     , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK
FROM TBL_SCORE
ORDER BY SID ASC
;
--> 한 줄 구성
SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT) / 3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;

-- 이름 검색 조회 쿼리문 작성
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT) / 3 AS AVG
     , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK
FROM TBL_SCORE
WHERE NAME LIKE '%%%찬%%'
;

SELECT * 
FROM 
(
    SELECT SID, NAME, KOR, ENG, MAT
         , (KOR+ENG+MAT) AS TOT
         , (KOR+ENG+MAT)/3 AS AVG
         , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
    FROM TBL_SCORE
)
WHERE NAME LIKE '민찬우'
;
--> 한 줄 구성
SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME LIKE '민찬우'
;

-- 인원 수 조회 쿼리문 작성
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

-- 번호 검색 조회 쿼리문 작성
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT) / 3 AS AVG
     , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK
FROM TBL_SCORE
WHERE SID = 1
;
--> 한 줄 구성
SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT) / 3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC ) AS RANK FROM TBL_SCORE WHERE SID = 1
;

-- 수정 쿼리문 작성
UPDATE TBL_SCORE
SET NAME = '엄복동'
  , KOR = 50
  , ENG = 50
  , MAT = 50
WHERE SID = 1
;
--> 한 줄 구성
UPDATE TBL_SCORE SET NAME = '엄복동', KOR = 50, ENG = 50, MAT = 50 WHERE SID = 1
;

-- 삭제 쿼리문 작성
DELETE
FROM TBL_SCORE
WHERE SID = 1
;
--> 한 줄 구성
DELETE FROM TBL_SCORE WHERE SID = 5
;