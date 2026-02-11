# querydsl 코딩법
# sql 을 작성하지 않고 sql 명령어와 비슷한 이름의 함수를 조립해서 사용합니다.
# Entity 클래스를 복사한 Q클래스를 사용합니다.
# 예) Dept -> QDept(자동 만듬)
# select -> select(), from -> from() 등
# jquery 와 유사하게 .함수().함수() 형태로 연결해서 작성합니다.
# 예) 
    List<Dept> 변수 = queryFactory
            .selectFrom(dept)                                  // select 부분
            .where(dept.dname.eq(dname).and(dept.loc.eq(loc))) // where 조건
            .fetch();                                          // 실행

# 설정: config 폴더에서 querydsl 설정 클래스를 추가합니다.
# QuerydslConfig 클래스: 복사 붙여넣기


