package com.yc.intelligence.dishwasher.controller;

import com.yc.intelligence.dishwasher.entity.Device;
import com.yc.intelligence.dishwasher.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/web/device")
public class DeviceWebController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/index")
    public ModelAndView index(){
        List<Device> deviceList = deviceRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("deviceList",deviceList);
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        Device device = deviceRepository.getOne(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("device",device);
        modelAndView.addObject("sensorList",device.getItems());
        modelAndView.setViewName("detail.html");
        return modelAndView;
    }
}
