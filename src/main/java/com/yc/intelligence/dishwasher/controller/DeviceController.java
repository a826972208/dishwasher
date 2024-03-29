package com.yc.intelligence.dishwasher.controller;

import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.common.ResultUtil;
import com.yc.intelligence.dishwasher.model.DeviceEditVo;
import com.yc.intelligence.dishwasher.model.DevicePositionRecordVo;
import com.yc.intelligence.dishwasher.model.DeviceSensorVo;
import com.yc.intelligence.dishwasher.model.DeviceVo;
import com.yc.intelligence.dishwasher.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

    @PostMapping("/edit/{deviceNumber}")
    public Result editDevice(@PathVariable String deviceNumber, @RequestBody DeviceVo deviceVo){
        return deviceService.editDevice(deviceNumber,deviceVo);
    }

    @PostMapping("/change/status/{deviceId}")
    public Result changeDeviceSensorStatus(@PathVariable Long deviceId, @RequestBody List<DeviceSensorVo> deviceSensorVoList){
        return deviceService.changeDeviceSensorStatus(deviceId,deviceSensorVoList);
    }

    @PostMapping("/change/enabled")
    public Result changeDeviceSensorEnabled(@RequestBody DeviceSensorVo sensorVo){
        return deviceService.changeDeviceSensorEnabled(sensorVo);
    }

    @PostMapping("/change/power/{deviceNumber}")
    public Result changeDevicePower(@PathVariable String deviceNumber, int power){
        return deviceService.changeDevicePower(deviceNumber,power);
    }

    @PostMapping("/change/run/status/{deviceNumber}")
    public Result changeDevicePower(@PathVariable String deviceNumber, boolean isOpen){
        return deviceService.changeDeviceRunState(deviceNumber,isOpen);
    }

    @GetMapping("/detail")
    public Result getDeviceDetail(String deviceNumber){
        return deviceService.getDeviceDetail(deviceNumber);
    }

    @GetMapping("/detail/{deviceNumber}")
    public Result getDeviceDetail(@PathVariable String deviceNumber,String sensorCodes){
        return deviceService.getDeviceDetail(deviceNumber,sensorCodes);
    }

    @PostMapping("/position/record/add")
    public Result addDevicePositionRecord(@RequestBody DevicePositionRecordVo vo){
        return deviceService.addDevicePositionRecord(vo);
    }

    @PostMapping("/edit")
    public Result addDevicePositionRecord(@RequestBody DeviceEditVo vo){
        return deviceService.editDeviceInfo(vo);
    }

    @PostMapping("/list")
    public Result getDeviceList(int page,int size){
        return deviceService.getDeviceByPage(page,size);
    }

    @GetMapping("/getCurrentTime")
    public Result getCurrentTime(){
        HashMap<String,Object> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        map.put("datetime", formatter.format(LocalDateTime.now()));
        return ResultUtil.success(map);
    }
}
