package com.simplecoding.jpaexam.emp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpStatsDto {
    private Long sum;
    private Double avg;
    private Long max;
    private Long min;
}
