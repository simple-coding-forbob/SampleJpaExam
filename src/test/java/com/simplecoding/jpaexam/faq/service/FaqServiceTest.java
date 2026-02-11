package com.simplecoding.jpaexam.faq.service;

import com.simplecoding.jpaexam.dept.dto.DeptDto;
import com.simplecoding.jpaexam.dept.service.DeptService;
import com.simplecoding.jpaexam.faq.entity.Faq;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
@EnableJpaAuditing
class FaqServiceTest {

    @Autowired
    FaqService faqService;

    @Test
    void findById() {
//		1) 테스트 조건(given)
        long fno=1;
//		2) 실제 메소드실행(when)
        Faq faqDto=faqService.findById(fno);
//		3) 검증(then): 로그 , DB 확인, assert~ (DB확인)
        log.info(faqDto);
    }
}