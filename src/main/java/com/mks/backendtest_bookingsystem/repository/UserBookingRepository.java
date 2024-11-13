package com.mks.backendtest_bookingsystem.repository;

import com.mks.backendtest_bookingsystem.entity.BSUser;
import com.mks.backendtest_bookingsystem.entity.ClassSchedule;
import com.mks.backendtest_bookingsystem.entity.UserBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookingRepository extends JpaRepository<UserBooking, Long>, JpaSpecificationExecutor<UserBooking> {
    int countByScheduledClassAndIsWaitlistedFalseAndStatusContaining(ClassSchedule scheduledClass, String status);

    List<UserBooking> findByUserAndIsWaitlistedFalse(BSUser user);

    List<UserBooking> findByUserAndIsWaitlistedFalseAndStatusIsContaining(BSUser user,String status);

    List<UserBooking> findByUserAndStatusIsContaining(BSUser user,String status);
    List<UserBooking> findByScheduledClassAndIsWaitlistedTrue(ClassSchedule classSchedule);
}
