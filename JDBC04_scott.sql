SELECT USER
FROM DUAL;
--==>> SCOTT

--�� ���� ���̺� ����
DROP TABLE TBL_SCORE;
--==>> Table TBL_SCORE��(��) �����Ǿ����ϴ�.

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

--�� ������ ����
DROP SEQUENCE SCORESEQ;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.

--�� ������ ����
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.
--drop SEQUENCE SCORESEQ;

--�� �����ͺ��̽� �׼� ó���� �ʿ��� ������ �غ�
-- 1. ������ �Է� ������ ����
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)
VALUES(SCORESEQ.NEXTVAL, 'ȫ�浿', 90, 80, 70);
--> �ڹٿ��� ����ϱ� ���� �� �� ����
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, 'ȫ�浿', 90, 80, 70)
;
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

COMMIT;
--==>> Ŀ�� �Ϸ�.

-- 2. ����Ʈ ��� ������ ����(����, ���, ���� ����)
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT)/3 AS AVG
     , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
FROM TBL_SCORE
ORDER BY SID ASC;
--> �ڹٿ��� ����ϱ� ���� �� �� ����
SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;
--==>> 1	ȫ�浿	90	80	70	240	80	1

-- 3. �ο� �� ��ȸ ������ ����
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> �� �� ����
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;
--==>> 1

-- 4. �̸� �˻� ������ ����(����, ���, ���� ����)
SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK
FROM
(
    SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT)/3 AS AVG
     , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
    FROM TBL_SCORE
)
WHERE NAME LIKE '%�浿%';
--> �� �� ����
SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK FROM ( SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE ) WHERE NAME LIKE '%�浿%'
;
--==>> 1	ȫ�浿	90	80	70	240	80	1

-- 5. ��ȣ �˻� ������ ����(����, ���, ���� ����)
SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK
FROM
(
    SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT)/3 AS AVG
     , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
    FROM TBL_SCORE
)
WHERE SID = 1;
--> �� �� ����
SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK FROM ( SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT)/3 AS AVG , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE ) WHERE SID = 1
;
--==>> 1	ȫ�浿	90	80	70	240	80	1

-- 6. ������ ���� ������ ����
UPDATE TBL_SCORE
SET NAME = '��浿', KOR = 100, ENG = 100, MAT = 100
WHERE SID = 1;
--> �� �� ����
UPDATE TBL_SCORE SET NAME = '��浿', KOR = 100, ENG = 100, MAT = 100 WHERE SID = 1
;
--==>> 1 �� ��(��) ������Ʈ�Ǿ����ϴ�.

COMMIT;
--==>> Ŀ�� �Ϸ�.

-- 7. ������ ���� ������ ����
DELETE
FROM TBL_SCORE
WHERE SID = 1;
--> �� �� ����
DELETE FROM TBL_SCORE WHERE SID = 1
;
--==>> 1 �� ��(��) �����Ǿ����ϴ�.

ROLLBACK;
--==>> �ѹ� �Ϸ�.



