# 사원 게시판
## 사원 게시판 주소: http://localhost:8080/emp
## spring - jpa 코딩 순서: 1) entity 2) dto  3) repository  4) service 5) controller(생략)
## 사원 테이블: TB_EMP
    ENO	        NUMBER
    ENAME	    VARCHAR2(255 BYTE)
    JOB	        VARCHAR2(255 BYTE)
    MANAGER	    NUMBER
    HIREDATE	DATE
    SALARY	    NUMBER
    COMMISSION	NUMBER
    DNO	        NUMBER