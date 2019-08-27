package com.yc.intelligence.dishwasher.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeviceEditVo implements Serializable {

    private static final long serialVersionUID = 2610484958724830550L;

    private String deviceNumber;//设备编号
    private String longitude;//经度
    private String latitude;//纬度
    private Integer power;//设备电量
    private List<String> faultCode;//故障代码(1001:门控故障,1002:取框故障,1003:漂洗温度传感器故障,1004:主洗温度传感器故障,1005:气包低水位故障,1006:水箱低水位门控故障)
    private String runState;//运行状态
}
