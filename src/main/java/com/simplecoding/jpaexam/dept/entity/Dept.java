package com.simplecoding.jpaexam.dept.entity;


import com.simplecoding.jpaexam.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "TB_DEPT")
@SequenceGenerator(
        name = "SQ_DEPT_JPA",
        sequenceName = "SQ_DEPT",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "dno", callSuper = false)
public class Dept extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_JPA"
    )
    private Long dno;
    private String  dname;
    private String  loc;
}

