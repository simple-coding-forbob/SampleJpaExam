package com.simplecoding.jpaexam.emp.repository;

import com.simplecoding.jpaexam.emp.dto.EmpStatsDto;
import com.simplecoding.jpaexam.emp.entity.Emp;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends JpaRepository<Emp,Long> {

    @Query(value = "select e from Emp e\n" +
            "where e.commission is null\n" +
            "and   e.salary >= :salary")
    Page<Emp> selectSalary(@Param("salary") long salary,Pageable pageable);

    @Query(value = "select new com.simplecoding.jpaexam.emp.dto.EmpStatsDto(sum(e.salary),round(avg(e.salary)),max(e.salary),min(e.salary))\n" +
            "from Emp e")
    EmpStatsDto selectGroup();

    @Query(value = "select e from Emp e where e.ename like %:searchKeyword%")
    Page<Emp> selectAll(
            @Param("searchKeyword") String searchKeyword,
            Pageable pageable
    );

    @Transactional
    @Modifying
    @Query(value = "delete Emp where eno=:eno")
    void bulkDelete(@Param("eno") long eno);
}
