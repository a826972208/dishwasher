package com.yc.intelligence.dishwasher.entity;

import com.yc.intelligence.dishwasher.common.BaseModel;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "device_position_record")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DevicePositionRecord extends BaseModel {

    private static final long serialVersionUID = -1280268937281977412L;

    @Column(name = "longitude",length = 20)
    private String longitude;//经度

    @Column(name = "latitude",length = 20)
    private String latitude;//纬度

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @NonNull
    private Device device;

    public DevicePositionRecord(String longitude,String latitude,Device device){
        this.latitude = latitude;
        this.longitude = longitude;
        this.device = device;
    }
}
