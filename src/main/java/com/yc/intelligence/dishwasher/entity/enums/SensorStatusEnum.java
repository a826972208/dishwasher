package com.yc.intelligence.dishwasher.entity.enums;

public enum SensorStatusEnum {
    NORMAL(0),ABNORMAL(1);
    public int code;
    SensorStatusEnum(int code){
        this.code = code;
    }
}
