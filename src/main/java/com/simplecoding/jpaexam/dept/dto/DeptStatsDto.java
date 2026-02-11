package com.simplecoding.jpaexam.dept.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeptStatsDto {
    private Long sum;
    private Double avg;
    private Long max;
    private Long min;
}
