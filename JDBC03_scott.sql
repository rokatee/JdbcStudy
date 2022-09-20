SELECT USER
FROM DUAL;
--==>> SCOTT


--�� �ǽ� ���̺� ����(TBL_SCORE)
CREATE TABLE TBL_SCORE
( SID   NUMBER
, NAME  VARCHAR2(30)
, KOR   NUMBER(3)
, ENG   NUMBER(3)
, MAT   NUMBER(3)
);
--==>> Table TBL_SCORE��(��) �����Ǿ����ϴ�.

--�� �������� �߰�(SID �÷��� PK �������� �߰�)
ALTER TABLE TBL_SCORE
ADD CONSTRAINT SCORE_SID_PK PRIMARY KEY(SID);
--==>> Table TBL_SCORE��(��) ����Ǿ����ϴ�.

--�� �������� �߰�(KOR, ENG, MAT �÷��� CK �������� �߰�)
ALTER TABLE TBL_SCORE
ADD ( CONSTRAINT SCORE_KOR_CK CHECK(KOR BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_ENG_CK CHECK(ENG BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_MAT_CK CHECK(MAT BETWEEN 0 AND 100) );
--==>> Table TBL_SCORE��(��) ����Ǿ����ϴ�.



--�� ������ �߶󳻱�
TRUNCATE TABLE TBL_SCORE;
--==>> Table TBL_SCORE��(��) �߷Ƚ��ϴ�.

--�� ������ ����
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.

--�� ��ȸ(Ȯ��)
SELECT *
FROM TBL_SCORE;
--==>> ��ȸ ��� ����

--�� ������ �Է� ������ �غ�
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(MEMBERSEQ.NEXTVAL, '�ڿ���', 80, 75, 60)
;

--�� �ο� �� Ȯ�� ������ ����
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> �ڹٿ��� ����ϱ� ���� �� �� ����
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--�� ��ü ����Ʈ ��ȸ ������ ����
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR+ENG+MAT) AS TOT
     , (KOR+ENG+MAT)/3 AS AVG 
FROM TBL_SCORE 
ORDER BY SID ASC;
--> �ڹٿ��� ����ϱ� ���� �� �� ����
SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT)AS TOT, (KOR+ENG+MAT)/3 AS AVG FROM TBL_SCORE ORDER BY SID ASC
;