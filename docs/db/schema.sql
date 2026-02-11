-- Table , 시퀀스 등 구조 정의
DROP SEQUENCE SQ_DEPT;
CREATE SEQUENCE SQ_DEPT START WITH 10 INCREMENT BY 10;

DROP SEQUENCE SQ_EMP;
CREATE SEQUENCE SQ_EMP START WITH 8000 INCREMENT BY 1;

DROP SEQUENCE SQ_FAQ;
CREATE SEQUENCE SQ_FAQ START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_QNA;
CREATE SEQUENCE SQ_QNA START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_FREE_BOARD;
CREATE SEQUENCE SQ_FREE_BOARD START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_NEWS_BOARD;
CREATE SEQUENCE SQ_NEWS_BOARD START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_NOTICE;
CREATE SEQUENCE SQ_NOTICE START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_EVENT_NOTICE;
CREATE SEQUENCE SQ_EVENT_NOTICE START WITH 1 INCREMENT BY 1;

DROP TABLE TB_EMP CASCADE CONSTRAINT;
DROP TABLE TB_DEPT CASCADE CONSTRAINT;

DROP TABLE TB_FAQ CASCADE CONSTRAINT;
DROP TABLE TB_QNA CASCADE CONSTRAINT;

DROP TABLE TB_FREE_BOARD CASCADE CONSTRAINT;
DROP TABLE TB_NEWS_BOARD CASCADE CONSTRAINT;

DROP TABLE TB_NOTICE CASCADE CONSTRAINT;
DROP TABLE TB_EVENT_NOTICE CASCADE CONSTRAINT;

DROP TABLE TB_FILE_DB CASCADE CONSTRAINT;
DROP TABLE TB_GALLERY CASCADE CONSTRAINT;

CREATE TABLE TB_DEPT (
                         DNO NUMBER NOT NULL PRIMARY KEY,
                         DNAME VARCHAR2(255),
                         LOC VARCHAR2(255),
                         INSERT_TIME DATE,
                         UPDATE_TIME DATE
);

CREATE TABLE TB_EMP
(
    ENO NUMBER NOT NULL PRIMARY KEY,
    ENAME VARCHAR2(255),
    JOB   VARCHAR2(255),
    MANAGER  NUMBER,
    HIREDATE DATE,
    SALARY NUMBER,
    COMMISSION NUMBER,
    DNO NUMBER,
    INSERT_TIME DATE,
    UPDATE_TIME DATE,
    FOREIGN KEY(DNO) REFERENCES TB_DEPT(DNO) ON DELETE CASCADE
);

CREATE TABLE TB_FAQ (
                        FNO NUMBER NOT NULL PRIMARY KEY,
                        TITLE VARCHAR2(255),
                        CONTENT VARCHAR2(255),
                        INSERT_TIME DATE,
                        UPDATE_TIME DATE
);

CREATE TABLE TB_QNA (
                        QNO NUMBER NOT NULL PRIMARY KEY,
                        QUESTIONER VARCHAR2(255 BYTE) NOT NULL,
                        QUESTION VARCHAR2(4000 BYTE) NOT NULL,
                        ANSWER VARCHAR2(4000 BYTE),
                        ANSWERER VARCHAR2(255 BYTE),
                        INSERT_TIME DATE,
                        UPDATE_TIME DATE
);


CREATE TABLE TB_NOTICE (
                           NID NUMBER NOT NULL PRIMARY KEY,
                           TITLE VARCHAR2(255),
                           CONTENT VARCHAR2(255),
                           IS_VISIBLE CHAR(1) DEFAULT 'N',
                           START_DATE DATE,
                           END_DATE DATE,
                           INSERT_TIME DATE,
                           UPDATE_TIME DATE
);

CREATE TABLE TB_EVENT_NOTICE (
                                 EID NUMBER NOT NULL PRIMARY KEY,
                                 SUBJECT VARCHAR2(255),
                                 TEXT VARCHAR2(255),
                                 IS_VISIBLE CHAR(1) DEFAULT 'N',
                                 START_DATE DATE,
                                 END_DATE DATE,
                                 INSERT_TIME DATE,
                                 UPDATE_TIME DATE
);

CREATE TABLE TB_FREE_BOARD (
                               FID NUMBER NOT NULL PRIMARY KEY,
                               TITLE VARCHAR2(255),
                               CONTENT VARCHAR2(255),
                               AUTHOR VARCHAR2(255),
                               VIEW_COUNT NUMBER,
                               INSERT_TIME DATE,
                               UPDATE_TIME DATE
);

CREATE TABLE TB_NEWS_BOARD (
                               BID NUMBER NOT NULL PRIMARY KEY,
                               SUBJECT VARCHAR2(255),
                               TEXT VARCHAR2(255),
                               WRITER VARCHAR2(255),
                               VIEW_COUNT NUMBER,
                               INSERT_TIME DATE,
                               UPDATE_TIME DATE
);



-- Upload Table
CREATE TABLE TB_FILE_DB
(
    UUID         VARCHAR2(1000) NOT NULL PRIMARY KEY, -- 파일 UUID
    FILE_TITLE   VARCHAR2(1000),           -- 제목
    FILE_CONTENT VARCHAR2(1000),           -- 내용
    FILE_URL     VARCHAR2(1000),           -- 파일 다운로드 URL
    INSERT_TIME DATE,
    UPDATE_TIME DATE
);

-- Upload Gallery Table
CREATE TABLE TB_GALLERY
(
    UUID              VARCHAR2(1000) NOT NULL PRIMARY KEY, -- 파일 UUID
    GALLERY_TITLE     VARCHAR2(1000),      -- 제목
    GALLERY_FILE_URL  VARCHAR2(1000),      -- 파일 다운로드 URL
    INSERT_TIME DATE,
    UPDATE_TIME DATE
);

-- TODO: 인증관련 테이블 정의
-- 유저 테이블
-- login table ddl
DROP TABLE TB_MEMBER CASCADE CONSTRAINTS;

CREATE TABLE TB_MEMBER
(
    EMAIL       VARCHAR2(1000) NOT NULL PRIMARY KEY, -- id (email)
    PASSWORD    VARCHAR2(1000),                      -- 암호
    NAME        VARCHAR2(1000),                      -- 유저명
    CODE_NAME   VARCHAR2(1000),                      -- 권한코드명(ROLE_USER, ROLE_ADMIN)
    INSERT_TIME DATE,
    UPDATE_TIME DATE
);

DROP TABLE TB_FILE_DB_LIKES CASCADE CONSTRAINTS;

-- 1️⃣ 시퀀스 생성
DROP SEQUENCE SQ_FILE_DB_LIKES;
CREATE SEQUENCE SQ_FILE_DB_LIKES START WITH 1 INCREMENT BY 1;

-- 2️⃣ 테이블 생성
CREATE TABLE TB_FILE_DB_LIKES (
                                  ID          NUMBER       PRIMARY KEY,  -- 단일 기본키 (시퀀스)
                                  EMAIL       VARCHAR2(1000) NOT NULL,  -- 사용자 이메일
                                  UUID        VARCHAR2(1000) NOT NULL,  -- 책 ID 또는 고유 식별자
                                  LIKE_COUNT  NUMBER(10) DEFAULT 0,          -- 좋아요 수
                                  INSERT_TIME DATE,                          -- 데이터 입력일시
                                  UPDATE_TIME DATE,                            -- 데이터 수정일시
                                  CONSTRAINT FK_MEMBER_EMAIL FOREIGN KEY (EMAIL) REFERENCES TB_MEMBER (EMAIL),
                                  CONSTRAINT FK_FILE_DB_UUID FOREIGN KEY (UUID) REFERENCES TB_FILE_DB (UUID)
);

DROP TABLE TB_GALLERY_LIKES CASCADE CONSTRAINTS;

-- 1️⃣ 시퀀스 생성
DROP SEQUENCE SQ_GALLERY_LIKES;
CREATE SEQUENCE SQ_GALLERY_LIKES START WITH 1 INCREMENT BY 1;

-- 2️⃣ 테이블 생성
CREATE TABLE TB_GALLERY_LIKES (
                                  ID          NUMBER      PRIMARY KEY,  -- 단일 기본키 (시퀀스)
                                  EMAIL       VARCHAR2(1000) NOT NULL,  -- 사용자 이메일
                                  UUID        VARCHAR2(1000) NOT NULL,  -- 책 ID 또는 고유 식별자
                                  LIKE_COUNT  NUMBER(10) DEFAULT 0,          -- 좋아요 수
                                  INSERT_TIME DATE,                          -- 데이터 입력일시
                                  UPDATE_TIME DATE,                            -- 데이터 수정일시
                                  CONSTRAINT FK_MEMBER_EMAIL2 FOREIGN KEY (EMAIL) REFERENCES TB_MEMBER (EMAIL),
                                  CONSTRAINT FK_GALLERY_UUID FOREIGN KEY (UUID) REFERENCES TB_GALLERY (UUID)
);

