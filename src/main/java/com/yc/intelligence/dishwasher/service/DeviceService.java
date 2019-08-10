package com.yc.intelligence.dishwasher.service;

import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.common.ResultUtil;
import com.yc.intelligence.dishwasher.entity.Account;
import com.yc.intelligence.dishwasher.entity.Device;
import com.yc.intelligence.dishwasher.entity.DeviceSensor;
import com.yc.intelligence.dishwasher.entity.enums.DeviceSensorCodeEnum;
import com.yc.intelligence.dishwasher.entity.enums.SensorStatusEnum;
import com.yc.intelligence.dishwasher.model.DeviceVo;
import com.yc.intelligence.dishwasher.repository.AccountRepository;
import com.yc.intelligence.dishwasher.repository.DeviceRepository;
import com.yc.intelligence.dishwasher.repository.DeviceSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DeviceSensorRepository deviceSensorRepository;

    @Transactional
    public Result addDevice(DeviceVo deviceVo){
        Account account = accountRepository.getOne(deviceVo.getAccountId());
        Device device = new Device(deviceVo.getDeviceNumber(),deviceVo.getDeviceName(),deviceVo.getLatitude(),
                deviceVo.getLongitude(),deviceVo.getDetailAddress(),deviceVo.getExpiryDate(),account);
        deviceRepository.save(device);
        List<DeviceSensor> sensorList = new ArrayList<>();
        for (DeviceSensorCodeEnum codeEnum: DeviceSensorCodeEnum.values()){
            DeviceSensor sensor = new DeviceSensor(codeEnum,codeEnum.name(), SensorStatusEnum.NORMAL,device);
            sensorList.add(sensor);
        }
        deviceSensorRepository.saveAll(sensorList);
        return ResultUtil.success();
    }

    @Transactional
    public Result editDevice(Long deviceId,DeviceVo deviceVo){
        Device device = deviceRepository.getOne(deviceId);
        device.editDevice(deviceVo.getDeviceName(),deviceVo.getLatitude(),deviceVo.getLongitude(),deviceVo.getDetailAddress());
        return ResultUtil.success();
    }

}
