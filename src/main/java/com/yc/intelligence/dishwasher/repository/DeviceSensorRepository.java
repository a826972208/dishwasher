package com.yc.intelligence.dishwasher.repository;

import com.yc.intelligence.dishwasher.entity.DeviceSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceSensorRepository extends JpaRepository<DeviceSensor,Long> {
    DeviceSensor findBySensorCodeAndDevice_Id(String sensorCode,Long deviceId);
}
