package com.simplecoding.jpaexam.dept.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simplecoding.jpaexam.dept.dto.DeptStatsDto;
import com.simplecoding.jpaexam.dept.entity.Dept;
import com.simplecoding.jpaexam.dept.entity.QDept;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeptRepositoryDsl {

    private final JPAQueryFactory queryFactory;

    QDept dept = QDept.dept;

    public Page<Dept> queryByDnameAndLoc(String dname, String loc, Pageable pageable) {

        List<Dept> content = queryFactory
                .selectFrom(dept)
                .where(dept.dname.eq(dname).and(dept.loc.eq(loc)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(dept.dno.asc())
                .fetch();

        long total = queryFactory
                .select(dept.count())
                .from(dept)
                .where(dept.dname.eq(dname).and(dept.loc.eq(loc)))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    public DeptStatsDto queryGroup() {

        return queryFactory
                .select(
                        Projections.constructor(
                                DeptStatsDto.class,
                                dept.dno.sum(),
                                dept.dno.avg(),
                                dept.dno.max(),
                                dept.dno.min()
                        )
                )
                .from(dept)
                .fetchOne();
    }

    public Page<Dept> queryByDnameOrLoc(String dname, String loc, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (dname != null && !dname.isBlank()) {
            builder.and(dept.dname.eq(dname));
        }

        if (loc != null && !loc.isBlank()) {
            builder.and(dept.loc.eq(loc));
        }

        List<Dept> content = queryFactory
                .selectFrom(dept)
                .where(builder)
                .fetch();

        long total = queryFactory
                .select(dept.count())
                .from(dept)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
