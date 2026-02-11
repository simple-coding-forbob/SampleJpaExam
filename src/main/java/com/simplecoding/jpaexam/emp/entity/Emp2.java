package com.simplecoding.jpaexam.emp.entity;

import com.simplecoding.jpaexam.common.BaseTimeEntity;
import com.simplecoding.jpaexam.dept.entity.Dept2;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "TB_EMP2")
@SequenceGenerator(
        name = "SQ_EMP2_JPA",
        sequenceName = "SQ_EMP2",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "eno", callSuper = false)
public class Emp2 extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMP2_JPA"
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
    private Dept2 dept2;

    public void changeDept(Dept2 newDept) {
        if (this.dept2 != null) {
            this.dept2.removeEmp(this);
        }
        this.dept2 = newDept;
        if (newDept != null) {
            newDept.addEmp(this);
        }
    }
}
