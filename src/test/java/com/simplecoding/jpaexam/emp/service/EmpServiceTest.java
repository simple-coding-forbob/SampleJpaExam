package com.simplecoding.jpaexam.emp.service;

import com.simplecoding.jpaexam.emp.dto.EmpDto;
import com.simplecoding.jpaexam.emp.dto.EmpStatsDto;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@SpringBootTest
@EnableJpaAuditing
class EmpServiceTest {

    @Autowired
    EmpService empService;

    @Test
    void findById() {
        //		1) 테스트 조건(given)
        int eno=8001;
//		2) 실제 메소드실행(when)
        EmpDto empDto=empService.findById(eno);
//		3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info(empDto);
    }

    @Transactional
    @Test
    void findAll() {
        List<EmpDto> list=empService.findAll();
        log.info("테스트 :"+list);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0,3);
        Page<EmpDto> page=empService.findAll(pageable);
        log.info("테스트 :"+page.getContent());
    }

    @Test
    void insert() {
//		1) 테스트 조건:
        LocalDate now=LocalDate.now();
        EmpDto empDto=new EmpDto();
        empDto.setEname("홍길동");
        empDto.setJob("부장");
        empDto.setManager((long)8000);
        empDto.setHiredate(now);
        empDto.setSalary((long)5000);
        empDto.setCommission((long)1000);
        empDto.setDno((long)10);
//		2) 실제 메소드실행
        empService.save(empDto);
//		3) 검증(확인): 로그 , DB 확인, assert~ (DB확인)
    }


    @Test
    void update() {
//		1) 테스트 조건:
        LocalDate now=LocalDate.now();
        EmpDto empDto=new EmpDto();
        empDto.setEno((long)8002);
        empDto.setEname("홍길동");
        empDto.setJob("부장");
        empDto.setManager((long)8000);
        empDto.setHiredate(now);
        empDto.setSalary((long)5000);
        empDto.setCommission((long)1000);
        empDto.setDno((long)20);
//		2) 실제 메소드실행
        empService.save(empDto);
//		3) 검증(확인): 로그 , DB 확인, assert~ (DB확인)
    }

    @Test
    void update2() {
//		1) 테스트 조건:
        EmpDto empDto=new EmpDto();
        empDto.setEno((long)8002);
        empDto.setEname("홍길동");
        empDto.setJob("부장");
        empDto.setManager((long)8000);
        empDto.setHiredate(LocalDate.now());
        empDto.setSalary((long)5000);
        empDto.setCommission((long)1000);
        empDto.setDno((long)20);
//		2) 실제 메소드실행
        empService.updateFromDto(empDto);
//		3) 검증(확인): 로그 , DB 확인, assert~ (DB확인)
    }

    @Test
    void deleteById() {
        //		1) 테스트 조건:
        int eno=8000;
//		2) 실제 메소드실행
        empService.deleteById(eno);
//		3) 검증(확인): 로그 , DB 확인, assert~ (DB확인)
    }

    @Test
    void selectSalary() {
        //        1) 테스트 조건(given):
        int salary=1600;
        Pageable pageable = PageRequest.of(0,3);
//        2) 실제 메소드실행(when):
        Page<EmpDto> page = empService.selectSalary(salary,  pageable);
//        3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info("테스트 : "+page.getContent());  // page 클래스의 content 에 dept 객체가 있습니다.
    }

    @Test
    void selectGroup() {
        EmpStatsDto empStatsDto=empService.selectGroup();
        log.info(empStatsDto);
    }

    @Transactional
    @Test
    void selectAll() {
//        1) 테스트 조건(given):
        String searchKeyword="";
        Pageable pageable = PageRequest.of(0,3);
//        2) 실제 메소드실행(when):
        Page<EmpDto> page = empService.selectAll(searchKeyword, pageable);
//        3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info("테스트 : "+page.getContent());  // page 클래스의 content 에 dept 객체가 있습니다.
    }

    @Test
    void bulkDelete() {
//		1) 테스트 조건(given)
        int eno=8010;
//		2) 실제 메소드 실행(when)
        empService.bulkDelete(eno);
//		3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
    }
}