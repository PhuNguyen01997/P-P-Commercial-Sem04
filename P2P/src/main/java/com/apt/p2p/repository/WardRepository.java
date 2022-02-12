package com.apt.p2p.repository;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {
    @Query("SELECT w FROM Ward w WHERE w.district.districtId=:id ORDER BY w.name")
    List<Ward> findAllByDistrictId(@Param("id") String districtId);

    List<Ward> findAllByOrderByName();
}
