package com.simplecoding.jpaexam.faq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqStatsDto {
    private Long sum;
    private Double avg;
    private Long max;
    private Long min;
}
