package com.yc.intelligence.dishwasher.service;

import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.common.ResultUtil;
import com.yc.intelligence.dishwasher.entity.Account;
import com.yc.intelligence.dishwasher.entity.Device;
import com.yc.intelligence.dishwasher.model.DeviceVo;
import com.yc.intelligence.dishwasher.repository.AccountRepository;
import com.yc.intelligence.dishwasher.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Result addDevice(DeviceVo deviceVo){
        Account account = accountRepository.getOne(deviceVo.getAccountId());
        Device device = new Device(deviceVo.getDeviceNumber(),deviceVo.getDeviceName(),deviceVo.getLatitude(),
                deviceVo.getLongitude(),deviceVo.getDetailAddress(),deviceVo.getExpiryDate(),account);
        deviceRepository.save(device);
        return ResultUtil.success();
    }

    @Transactional
    public Result editDevice(Long deviceId,DeviceVo deviceVo){
        Device device = deviceRepository.getOne(deviceId);
        device.editDevice(deviceVo.getDeviceName(),deviceVo.getLatitude(),deviceVo.getLongitude(),deviceVo.getDetailAddress());
        return ResultUtil.success();
    }
}
