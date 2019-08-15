package com.yc.intelligence.dishwasher.controller;

import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.model.DeviceSensorVo;
import com.yc.intelligence.dishwasher.model.DeviceVo;
import com.yc.intelligence.dishwasher.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/add")
    public Result addDevice(@RequestBody DeviceVo deviceVo){
        return deviceService.addDevice(deviceVo);
    }

    @PostMapping("/edit/{deviceId}")
    public Result editDevice(@PathVariable Long deviceId, @RequestBody DeviceVo deviceVo){
        return deviceService.editDevice(deviceId,deviceVo);
    }

    @PostMapping("/change/status/{deviceId}")
    public Result changeDeviceSensorStatus(@PathVariable Long deviceId, @RequestBody List<DeviceSensorVo> deviceSensorVoList){
        return deviceService.changeDeviceSensorStatus(deviceId,deviceSensorVoList);
    }

    @PostMapping("/change/enabled")
    public Result changeDeviceSensorEnabled(@RequestBody DeviceSensorVo sensorVo){
        return deviceService.changeDeviceSensorEnabled(sensorVo);
    }

    @PostMapping("/change/power/{deviceId}")
    public Result changeDevicePower(@PathVariable Long deviceId, int power){
        return deviceService.changeDevicePower(deviceId,power);
    }

    @GetMapping("/detail")
    public Result getDeviceDetail(String deviceNumber){
        return deviceService.getDeviceDetail(deviceNumber);
    }
}
