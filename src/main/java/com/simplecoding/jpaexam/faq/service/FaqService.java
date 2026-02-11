package com.simplecoding.jpaexam.faq.service;

import com.simplecoding.jpaexam.common.MapStruct;
import com.simplecoding.jpaexam.dept.dto.DeptDto;
import com.simplecoding.jpaexam.dept.dto.DeptStatsDto;
import com.simplecoding.jpaexam.dept.entity.Dept;
import com.simplecoding.jpaexam.dept.repository.DeptRepository;
import com.simplecoding.jpaexam.dept.repository.DeptRepositoryDsl;
import com.simplecoding.jpaexam.faq.entity.Faq;
import com.simplecoding.jpaexam.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public Faq findById(long dno) {
        Faq faq=faqRepository.findById(dno).get();

        return faq;
    }
}

