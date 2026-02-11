package com.simplecoding.jpaexam.dept.service;

import com.simplecoding.jpaexam.dept.dto.DeptDto;
import com.simplecoding.jpaexam.dept.dto.DeptStatsDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@Log4j2
@SpringBootTest
@EnableJpaAuditing
class DeptServiceTest {

    @Autowired
    DeptService deptService;

    @Test
    void findById() {
//		1) 테스트 조건(given)
        long dno=20;
//		2) 실제 메소드실행(when)
        DeptDto deptDto=deptService.findById(dno);
//		3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info(deptDto);
    }

    @Test
    void findAll() {
        List<DeptDto> list=deptService.findAll();
        log.info(list);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0,3);
        Page<DeptDto> page=deptService.findAll(pageable);
        log.info(page);
    }

    @Test
    void insert() {
        DeptDto deptDto=new DeptDto();
        deptDto.setDname("개발부2");
        deptDto.setLoc("서울");
        deptService.save(deptDto);
    }

    @Test
    void update() {
        DeptDto deptDto=new DeptDto();
        deptDto.setDno((long)20);
        deptDto.setDname("영업부");
        deptDto.setLoc("부산");
        deptService.save(deptDto);
    }

    @Test
    void update2() {
//		1) 테스트 조건(given)
        DeptDto deptDto=new DeptDto();
        deptDto.setDno((long)20);
        deptDto.setDname("개발부");
        deptDto.setLoc("부산");
//		2) 실제 메소드실행(when)
//      dirty checking: 미리 상세조회로 db의 결과를 jpa 관리공간에(메모리) 올림
        deptService.updateFromDto(deptDto);
    }

    @Test
    void deleteById() {
        deptService.deleteById(40);
    }


    @Test
    void selectByDnameAndLoc() {
        //        1) 테스트 조건(given):
        String dname="SALES";
        String loc="CHICAGO";
        Pageable pageable = PageRequest.of(0,3);
//        2) 실제 메소드실행(when):
        Page<DeptDto> page = deptService.selectByDnameAndLoc(dname, loc, pageable);
//        3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info("테스트 : "+page.getContent());  // page 클래스의 content 에 dept 객체가 있습니다.
    }

    @Test
    void selectByGroupFunc() {
        DeptStatsDto deptStatsDto = deptService.selectGroup();
        log.info(deptStatsDto);
    }

    @Test
    void testSelectAll() {
//        1) 테스트 조건(given):
        String searchKeyword="ACCOUNTING";
        Pageable pageable = PageRequest.of(0,3);
//        2) 실제 메소드실행(when):
        Page<DeptDto> page = deptService.selectAll(searchKeyword, pageable);
//        3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info("테스트 : "+page.getContent());  // page 클래스의 content 에 dept 객체가 있습니다.
    }

    @Test
    void bulkDelete() {
//		1) 테스트 조건(given)
        long dno=220;
//		2) 실제 메소드 실행(when)
        deptService.bulkDelete(dno);
//		3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
    }

    @Test
    void queryByDnameAndLoc() {
        //        1) 테스트 조건(given):
        String dname="SALES";
        String loc="CHICAGO";
        Pageable pageable = PageRequest.of(0,3);
//        2) 실제 메소드실행(when):
        Page<DeptDto> page = deptService.queryByDnameAndLoc(dname, loc, pageable);
//        3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info("테스트 : "+page.getContent());  // page 클래스의 content 에 dept 객체가 있습니다.
    }

    @Test
    void queryGroup() {
        DeptStatsDto deptStatsDto = deptService.queryGroup();
        log.info(deptStatsDto);
    }

    @Test
    void queryByDnameOrLoc() {
//        1) 테스트 조건(given):
        String dname="";
        String loc="CHICAGO";
        Pageable pageable = PageRequest.of(0,3);
//        2) 실제 메소드실행(when):
        Page<DeptDto> page = deptService.queryByDnameOrLoc(dname, loc, pageable);
//        3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info("테스트 : "+page.getContent());  // page 클래스의 content 에 dept 객체가 있습니다.
    }
}