package com.yc.intelligence.dishwasher.model;

import com.yc.intelligence.dishwasher.entity.enums.DeviceDownTimeTypeEnum;
import com.yc.intelligence.dishwasher.entity.enums.DeviceOnlineStateEnum;
import com.yc.intelligence.dishwasher.entity.enums.DeviceRunStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class DeviceDetailVo implements Serializable {
    private static final long serialVersionUID = -3001881702285717151L;
    private Long id;
    private String deviceNumber;//设备编号
    private String deviceName;//设备名称
    private String longitude;//经度
    private String latitude;//纬度
    private String detailAddress;//详细地址
    private int power = 0;//设备电量
    private LocalDate expiryDate;//到期时间
    private DeviceRunStatusEnum runState;//设备运行状态
    private DeviceOnlineStateEnum onlineState;//设备在线状态
    private DeviceDownTimeTypeEnum downtimeType;//设备停机类型
    private List<DeviceSensorVo> deviceSensorVoList;
}
