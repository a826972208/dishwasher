package com.yc.intelligence.dishwasher.entity;

import com.yc.intelligence.dishwasher.common.BaseModel;
import com.yc.intelligence.dishwasher.entity.enums.DeviceRunStatusEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device",uniqueConstraints = {@UniqueConstraint(columnNames = {"device_number"})})
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Device extends BaseModel {

    private static final long serialVersionUID = 5355116811237361849L;

    @Column(name = "device_number",length = 20,nullable = false,unique = true)
    private String deviceNumber;//设备编号

    @Column(name = "device_name",length = 50)
    private String deviceName;//设备名称

    @Column(name = "longitude",length = 20)
    private String longitude;//经度

    @Column(name = "latitude",length = 20)
    private String latitude;//纬度

    @Column(name = "detail_address",length = 200)
    private String detailAddress;//详细地址

    @Column(name = "power")
    private int power = 0;//设备电量

    @Column(name = "expiry_date",columnDefinition = "date")
    private LocalDate expiryDate;//到期时间

    @Enumerated(EnumType.STRING)
    @Column(name = "run_state",length = 20)
    private DeviceRunStatusEnum runState = DeviceRunStatusEnum.RUNNING;//设备运行状态

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id asc")
    private List<DeviceSensor> items = new ArrayList<>();

    public Device(String deviceNumber,String deviceName,String latitude,String longitude,String detailAddress,LocalDate expiryDate,Account account){
        this.account = account;
        this.detailAddress = detailAddress;
        this.deviceName  = deviceName;
        this.deviceNumber = deviceNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.expiryDate = expiryDate;
    }

    public void changeExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }

    public void editDevice(String deviceName,String latitude,String longitude,String detailAddress){
        this.detailAddress = detailAddress;
        this.deviceName  = deviceName;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
