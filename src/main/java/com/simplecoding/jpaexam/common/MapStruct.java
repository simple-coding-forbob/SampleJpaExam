package com.simplecoding.jpaexam.common;

import com.simplecoding.jpaexam.dept.dto.DeptDto;
import com.simplecoding.jpaexam.dept.entity.Dept;
import com.simplecoding.jpaexam.emp.dto.EmpDto;
import com.simplecoding.jpaexam.emp.entity.Emp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  // null 제외 기능(update 시 사용)
)
public interface MapStruct {
    @Mapping(source = "dept.dno", target = "dno")
    @Mapping(source = "dept.dname", target = "dname")
    EmpDto toDto(Emp emp);
    @Mapping(source = "dno", target = "dept.dno")
    @Mapping(source = "dname", target = "dept.dname")
    Emp toEntity(EmpDto empDto);
    void updateFromDto(DeptDto deptDto, @MappingTarget Dept dept);

    @Mapping(target = "dept", ignore = true)
    void updateFromDto(EmpDto empDto, @MappingTarget Emp emp);
    DeptDto toDto(Dept dept);
    Dept toEntity(DeptDto deptDto);
}
