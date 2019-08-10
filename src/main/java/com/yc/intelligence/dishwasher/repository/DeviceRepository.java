package com.yc.intelligence.dishwasher.repository;

import com.yc.intelligence.dishwasher.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> findByAccount_Id(Long id);
}
