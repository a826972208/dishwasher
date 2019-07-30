package com.yc.intelligence.dishwasher.repository;

import com.yc.intelligence.dishwasher.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {

}
