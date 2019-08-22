package com.yc.intelligence.dishwasher.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DevicePositionRecordVo implements Serializable {
    private static final long serialVersionUID = 3608320609743016670L;
    private String longitude;//经度
    private String latitude;//纬度
    private String deviceNumber;//设备编号
}
