# spring 환경 설정
# 1) pom.xml 
## jsp 라이브러리 추가 : dependencis 안에 넣기

	//    TODO: dto , entity 변환 라이브러리(롬북보다 순서상 밑에 위치해야 정상작동함)
	implementation 'org.mapstruct:mapstruct:1.5.5.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'

	// TODO: querydsl jakarta 의존성 추가
	implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
	annotationProcessor 'com.querydsl:querydsl-apt:5.0.0:jakarta'
	annotationProcessor 'jakarta.annotation:jakarta.annotation-api'
	annotationProcessor 'jakarta.persistence:jakarta.persistence-api'

# 2) application.properties
## 일반 설정: db 설정, upload 위치/크기 설정 등 아래 내용 붙여 넣기
    
    # TODO: DB 라이브러리 설정
    spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
    # TODO: DB 접속 설정  : 도커 오라클 , 계정/암호, db명(서비스이름)(xepdb1)
    # todo: spring.datasource.url=jdbc:log4jdbc:oracle:thin:@ip주소:db포트번호/db이름
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
    spring.datasource.username=scott
    spring.datasource.password=!Ds1234567890
    
    # TODO: JPA 접속 설정
    spring.jpa.hibernate.ddl-auto=none
    # sql 로그 출력
    spring.jpa.properties.hibernate.format_sql=true
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.orm.jdbc.bind=TRACE

# 3) 리액트와 데이터를 주고 받기 위해서는 웹 보안 설정이 필요합니다. : cors 보안 설정
# cors 보안 설정: 같은 pc 에서 인터넷 주소가 다른 것 2개가 통신할수 없게 만드는 규칙(사이트 위조 공격 해킹 방어)
#  예) react(localhost:5178) <-> spring boot(localhost:8080) : 포트번호가 틀려서 통신할 수 없음(같은 pc)
