package com.simplecoding.jpaexam.emp.service;

import com.simplecoding.jpaexam.common.MapStruct;
import com.simplecoding.jpaexam.emp.dto.EmpDto;
import com.simplecoding.jpaexam.emp.dto.EmpStatsDto;
import com.simplecoding.jpaexam.emp.entity.Emp;
import com.simplecoding.jpaexam.emp.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepository empRepository;
    private final MapStruct mapStruct;

    public EmpDto findById(long eno) {

        Emp emp= empRepository.findById(eno)
                .orElseThrow(() -> new RuntimeException("정보를 저장할 수 없습니다."));
        return mapStruct.toDto(emp);
    }

    public List<EmpDto> findAll() {
        List<Emp> list= empRepository.findAll();
        return list.stream().map(emp->mapStruct.toDto(emp)).toList();
    }

    public List<Emp> findAll2() {
        List<Emp> list= empRepository.findAll();
        return list;
    }

    public Page<EmpDto> findAll(Pageable pageable) {
        Page<Emp> page= empRepository.findAll(pageable);
        return page.map(emp->mapStruct.toDto(emp));
    }

    public void save(EmpDto empDto) {

        Emp emp=mapStruct.toEntity(empDto);
        empRepository.save(emp);
    }

    @Transactional
    public void updateFromDto(EmpDto empDto) {

        Emp emp=empRepository.findById(empDto.getEno())
                .orElseThrow(() -> new RuntimeException("정보를 찾을 수 없습니다."));

        mapStruct.updateFromDto(empDto, emp);
    }

    public void deleteById(long eno) {
        empRepository.deleteById(eno);
    }

    public Page<EmpDto> selectSalary(long salary, Pageable pageable) {
        Page<Emp>  page= empRepository.selectSalary(salary, pageable);
        return page.map(emp -> mapStruct.toDto(emp));
    }

    public EmpStatsDto selectGroup() {
        return empRepository.selectGroup();
    }


    public Page<EmpDto> selectAll(String searchKeyword, Pageable pageable) {
        Page<Emp>  page= empRepository.selectAll(searchKeyword, pageable);
        return page.map(emp -> mapStruct.toDto(emp));
    }

    public void bulkDelete(long eno) {
        empRepository.bulkDelete(eno);
    }

}

