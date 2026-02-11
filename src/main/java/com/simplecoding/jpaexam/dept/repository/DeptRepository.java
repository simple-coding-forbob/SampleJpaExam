package com.simplecoding.jpaexam.dept.repository;


import com.simplecoding.jpaexam.dept.dto.DeptStatsDto;
import com.simplecoding.jpaexam.dept.entity.Dept;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept,Long> {

    @Query(value = "select d from Dept d\n" +
            "where d.dname = :dname\n" +
            "and   d.loc   = :loc")
    Page<Dept> selectByDnameAndLoc(
            @Param("dname") String dname,
            @Param("loc") String loc,
            Pageable pageable
    );

    @Query(value = "select new com.simplecoding.jpaexam.dept.dto.DeptStatsDto(sum(d.dno),avg(d.dno),max(d.dno),min(d.dno))\n" +
            "from Dept d")
    DeptStatsDto selectGroup();

    @Query(value = "select d from Dept d\n" +
            "where d.dname like %:searchKeyword%")
    Page<Dept> selectAll(
            @Param("searchKeyword") String searchKeyword,
            Pageable pageable
    );

    @Transactional
    @Modifying
    @Query(value = "delete Dept where dno=:dno")
    void bulkDelete(@Param("dno") long dno);
}
