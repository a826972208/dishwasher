package com.yc.intelligence.dishwasher.entity;

import com.yc.intelligence.dishwasher.common.BaseModel;
import com.yc.intelligence.dishwasher.entity.enums.DeviceSensorCodeEnum;
import com.yc.intelligence.dishwasher.entity.enums.SensorStatusEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "device_sensor")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DeviceSensor extends BaseModel {

    private static final long serialVersionUID = -5655901234024333567L;

    @Enumerated(EnumType.STRING)
    @Column(name = "sensor_code",length = 50)
    private DeviceSensorCodeEnum sensorCode;//传感器编码

    @Column(name = "sensor_name",length = 50)
    private String sensorName;//传感器名称

    @Enumerated(EnumType.STRING)
    @Column(name = "sensor_status",length = 20)
    private SensorStatusEnum sensorStatus = SensorStatusEnum.NORMAL;//传感器状态

    @Column(name = "is_enabled")
    private boolean enabled = true;//是否启用

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @NonNull
    protected Device device;

    public DeviceSensor(DeviceSensorCodeEnum sensorCode, String sensorName,SensorStatusEnum sensorStatus,Device device){
        this.sensorCode = sensorCode;
        this.sensorName = sensorName;
        this.sensorStatus = sensorStatus;
        this.device = device;
    }

    public void changeStatus(SensorStatusEnum sensorStatus){
        this.sensorStatus = sensorStatus;
    }

    public void chageEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
