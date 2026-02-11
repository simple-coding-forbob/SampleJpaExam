package com.simplecoding.jpaexam.emp.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpDto {
    private Long eno;
    private String ename;
    private String job;
    private Long manager;
    private LocalDate hiredate;
    private Long salary;
    private Long commission;
    private Long dno;
    private String  dname;
}
