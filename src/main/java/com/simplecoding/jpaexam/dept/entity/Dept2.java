package com.simplecoding.jpaexam.dept.entity;


import com.simplecoding.jpaexam.common.BaseTimeEntity;
import com.simplecoding.jpaexam.emp.entity.Emp2;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "TB_DEPT2")
@SequenceGenerator(
        name = "SQ_DEPT2_JPA",
        sequenceName = "SQ_DEPT2",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "emp2")
@EqualsAndHashCode(of = "dno", callSuper = false)
public class Dept2 extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT2_JPA"
    )
    private Long dno;
    private String  dname;
    private String  loc;

    @OneToMany(mappedBy = "dept2")
    List<Emp2> emp2=new ArrayList<>();

    public void addEmp(Emp2 emp) {
        if (!emp2.contains(emp)) {
            emp2.add(emp);
            emp.setDept2(this);
        }
    }

    public void removeEmp(Emp2 emp) {
        emp2.remove(emp);
        emp.setDept2(null);
    }
}

