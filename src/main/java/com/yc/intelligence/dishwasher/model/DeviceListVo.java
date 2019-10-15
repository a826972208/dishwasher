package com.yc.intelligence.dishwasher.model;

import com.yc.intelligence.dishwasher.entity.Device;
import com.yc.intelligence.dishwasher.entity.enums.DeviceOnlineStateEnum;
import com.yc.intelligence.dishwasher.entity.enums.DeviceRunStatusEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DeviceListVo implements Serializable {
    private Long id;
    private String deviceNumber;//设备编号
    private String deviceName;//设备名称
    private String detailAddress;//详细地址
    private LocalDate expiryDate;//到期时间
    private DeviceRunStatusEnum runState;//设备运行状态
    private DeviceOnlineStateEnum onlineState;//设备在线状态

    public DeviceListVo(Device device){
        BeanUtils.copyProperties(device,this);
    }
}
