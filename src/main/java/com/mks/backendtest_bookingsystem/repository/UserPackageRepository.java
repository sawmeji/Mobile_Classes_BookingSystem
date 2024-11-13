package com.mks.backendtest_bookingsystem.repository;

import com.mks.backendtest_bookingsystem.entity.BSUser;
import com.mks.backendtest_bookingsystem.entity.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPackageRepository extends JpaRepository<UserPackage, Long>, JpaSpecificationExecutor<UserPackage> {

    List<UserPackage> findByUser(BSUser user);

}
