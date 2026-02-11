package com.simplecoding.jpaexam.emp.entity;

import com.simplecoding.jpaexam.common.BaseTimeEntity;
import com.simplecoding.jpaexam.dept.entity.Dept;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "TB_EMP")
@SequenceGenerator(
        name = "SQ_EMP_JPA",
        sequenceName = "SQ_EMP",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "eno", callSuper = false)
public class Emp extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMP_JPA"
    )
    private Long eno;
    private String ename;
    private String job;
    private Long manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredate;
    private Long salary;
    private Long commission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dno")
    private Dept dept;
}
