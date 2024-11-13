package com.mks.backendtest_bookingsystem.repository;

import com.mks.backendtest_bookingsystem.entity.BSUser;
import com.mks.backendtest_bookingsystem.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long>, JpaSpecificationExecutor<ClassSchedule> {
    List<ClassSchedule> findByCountry(String country);

    List<ClassSchedule> findClassScheduleByEndTimeBefore(LocalDateTime endTime);
}
