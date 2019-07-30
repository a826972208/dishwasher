package com.yc.intelligence.dishwasher.entity;

import com.yc.intelligence.dishwasher.common.BaseModel;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "device",uniqueConstraints = {@UniqueConstraint(columnNames = {"mobilePhone"})})
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

    @Column(name = "expiry_date",columnDefinition = "date")
    private LocalDate expiryDate;//到期时间

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


}
