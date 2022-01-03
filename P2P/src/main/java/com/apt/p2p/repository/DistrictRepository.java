package com.apt.p2p.repository;

import com.apt.p2p.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, String> {
    @Query("SELECT d FROM District d WHERE d.province.provinceId=:id ORDER BY d.name")
    List<District> findAllByProvinceId(@Param("id") String provinceId);

    List<District> findAllByOrderByName();
}
