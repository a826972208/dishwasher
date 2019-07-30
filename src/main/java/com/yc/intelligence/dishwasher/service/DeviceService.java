package com.yc.intelligence.dishwasher.service;

import com.yc.intelligence.dishwasher.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
}
