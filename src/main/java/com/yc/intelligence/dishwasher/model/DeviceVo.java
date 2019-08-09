package com.yc.intelligence.dishwasher.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DeviceVo implements Serializable {
    private static final long serialVersionUID = 4956742513130986797L;
    private String deviceNumber;//设备编号
    private String deviceName;//设备名称
    private String longitude;//经度
    private String latitude;//纬度
    private String detailAddress;//详细地址
    private LocalDate expiryDate;//到期时间
    private Long accountId;//账户ID
}
