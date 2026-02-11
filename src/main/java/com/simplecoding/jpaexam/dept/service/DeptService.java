package com.simplecoding.jpaexam.dept.service;

import com.simplecoding.jpaexam.common.MapStruct;
import com.simplecoding.jpaexam.dept.dto.DeptDto;
import com.simplecoding.jpaexam.dept.dto.DeptStatsDto;
import com.simplecoding.jpaexam.dept.entity.Dept;
import com.simplecoding.jpaexam.dept.repository.DeptRepository;
import com.simplecoding.jpaexam.dept.repository.DeptRepositoryDsl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;
    private final MapStruct mapStruct;

    private final DeptRepositoryDsl deptRepositoryDsl;

    public DeptDto findById(long dno) {

        Dept dept = deptRepository.findById(dno)
                .orElseThrow(() -> new RuntimeException("정보를 찾을 수 없습니다."));

        return mapStruct.toDto(dept);
    }

    public List<DeptDto> findAll() {
        List<Dept> list = deptRepository.findAll();
        return list.stream().map(dept -> mapStruct.toDto(dept)).toList();
    }

    public Page<DeptDto> findAll(Pageable pageable) {
        Page<Dept> page = deptRepository.findAll(pageable);
        return page.map(dept -> mapStruct.toDto(dept));
    }

    public void save(DeptDto deptDto) {
        Dept dept = mapStruct.toEntity(deptDto);
        deptRepository.save(dept);
    }

    @Transactional
    public void updateFromDto(DeptDto deptDto) {

        Dept dept = deptRepository.findById(deptDto.getDno())
                .orElseThrow(() -> new RuntimeException("정보를 찾을 수 없습니다."));

        mapStruct.updateFromDto(deptDto, dept);
    }

    public void deleteById(long dno) {
        deptRepository.deleteById(dno);
    }

    public Page<DeptDto> selectByDnameAndLoc(String dname, String loc, Pageable pageable) {
        Page<Dept> page = deptRepository.selectByDnameAndLoc(dname, loc, pageable);
        return page.map(dept -> mapStruct.toDto(dept));
    }

    public DeptStatsDto selectGroup() {
        return deptRepository.selectGroup();
    }


    public Page<DeptDto> selectAll(String searchKeyword, Pageable pageable) {
        Page<Dept> page = deptRepository.selectAll(searchKeyword, pageable);
        return page.map(dept -> mapStruct.toDto(dept));
    }


    public void bulkDelete(long dno) {
        deptRepository.bulkDelete(dno);
    }

    public Page<DeptDto> queryByDnameAndLoc(String dname, String loc, Pageable pageable) {
        Page<Dept> page = deptRepositoryDsl.queryByDnameAndLoc(dname, loc, pageable);
        return page.map(dept -> mapStruct.toDto(dept));
    }

    public DeptStatsDto queryGroup() {
        return deptRepositoryDsl.queryGroup();
    }

    public Page<DeptDto> queryByDnameOrLoc(String dname, String loc, Pageable pageable) {
        Page<Dept> page = deptRepositoryDsl.queryByDnameOrLoc(dname, loc, pageable);
        return page.map(dept -> mapStruct.toDto(dept));
    }
}

