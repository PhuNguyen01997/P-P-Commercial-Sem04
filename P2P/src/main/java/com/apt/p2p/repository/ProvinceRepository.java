package com.apt.p2p.repository;

import com.apt.p2p.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, String> {
    List<Province> findAllByOrderByName();
}
