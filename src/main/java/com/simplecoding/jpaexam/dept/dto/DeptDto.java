package com.simplecoding.jpaexam.dept.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeptDto {
    private Long dno;
    private String  dname;
    private String  loc;
}
