package com.yc.intelligence.dishwasher.entity.enums;

public enum DeviceSensorCodeEnum {
    MEN_KONG_SENSOR("门控传感器"),QU_KUANG_SENSOR("取框传感器"),WATER_TANK_HYDROPENIA_SENSOR("水箱缺水传感器"),
    BUBBLE_HYDROPENIA_SENSOR("气泡缺水传感器"),WATER_TANK_TEMPERATURE_SENSOR("水箱温控传感器"),DISINFECTION_TEMPERATURE_SENSOR("消毒温控传感器");
    private String name;
    DeviceSensorCodeEnum(String name){
        this.name = name;
    }
}
