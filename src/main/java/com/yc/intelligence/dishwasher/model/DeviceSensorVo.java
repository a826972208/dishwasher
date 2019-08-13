package com.yc.intelligence.dishwasher.model;

import com.yc.intelligence.dishwasher.entity.enums.DeviceSensorCodeEnum;
import com.yc.intelligence.dishwasher.entity.enums.SensorStatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceSensorVo implements Serializable {

    private static final long serialVersionUID = -2402984651806536018L;
    private Long id;
    private DeviceSensorCodeEnum sensorCode;
    private String sensorName;
    private SensorStatusEnum sensorStatus;
    private Boolean enabled;
    private String sensorValue;
}
