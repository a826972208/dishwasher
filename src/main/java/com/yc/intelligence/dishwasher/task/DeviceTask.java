package com.yc.intelligence.dishwasher.task;

import com.yc.intelligence.dishwasher.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeviceTask {
    @Autowired
    private DeviceService deviceService;

    @Async
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateDeviceRunStatus(){
        deviceService.updateDevicesStatus();
    }
}
