package com.simplecoding.jpaexam.faq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqDto {
    private Long fno;
    private String  title;
    private String  content;
}
