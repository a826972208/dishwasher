package com.yc.intelligence.dishwasher.service;

import com.yc.intelligence.dishwasher.common.PageResponse;
import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.common.ResultUtil;
import com.yc.intelligence.dishwasher.entity.Account;
import com.yc.intelligence.dishwasher.entity.Device;
import com.yc.intelligence.dishwasher.entity.DevicePositionRecord;
import com.yc.intelligence.dishwasher.entity.DeviceSensor;
import com.yc.intelligence.dishwasher.entity.enums.DeviceRunStatusEnum;
import com.yc.intelligence.dishwasher.entity.enums.DeviceSensorCodeEnum;
import com.yc.intelligence.dishwasher.entity.enums.SensorStatusEnum;
import com.yc.intelligence.dishwasher.model.*;
import com.yc.intelligence.dishwasher.repository.AccountRepository;
import com.yc.intelligence.dishwasher.repository.DevicePositionRecordRepository;
import com.yc.intelligence.dishwasher.repository.DeviceRepository;
import com.yc.intelligence.dishwasher.repository.DeviceSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DeviceSensorRepository deviceSensorRepository;

    @Autowired
    private DevicePositionRecordRepository devicePositionRecordRepository;

    @Transactional
    public Result addDevice(DeviceVo deviceVo){
        Account account = accountRepository.getOne(deviceVo.getAccountId());
        Device device = new Device(deviceVo.getDeviceNumber(),deviceVo.getDeviceName(),deviceVo.getLatitude(),
                deviceVo.getLongitude(),deviceVo.getDetailAddress(),deviceVo.getExpiryDate(),account);
        deviceRepository.save(device);
        List<DeviceSensor> sensorList = new ArrayList<>();
        for (DeviceSensorCodeEnum codeEnum: DeviceSensorCodeEnum.values()){
            DeviceSensor sensor = new DeviceSensor(codeEnum,codeEnum.name, SensorStatusEnum.NORMAL,device,null);
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

    @Transactional
    public Result changeDeviceSensorStatus(Long deviceId,List<DeviceSensorVo> sensorVoList){
        sensorVoList.forEach(deviceSensorVo -> {
            DeviceSensor sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(deviceSensorVo.getSensorCode(),deviceId);
            sensor.setSensorStatus(deviceSensorVo.getSensorStatus());
        });
        return ResultUtil.success();
    }

    @Transactional
    public Result changeDeviceSensorEnabled(DeviceSensorVo sensorVo){
        DeviceSensor sensor = deviceSensorRepository.getOne(sensorVo.getId());
        sensor.chageEnabled(sensorVo.getEnabled());
        return ResultUtil.success();
    }

    @Transactional
    public Result changeDevicePower(String deviceNumber,int power){
        Device device = deviceRepository.findByDeviceNumber(deviceNumber);
        device.setPower(power);
        return ResultUtil.success();
    }


    @Transactional
    public Result changeDeviceRunState(String deviceNumber,boolean isOpen){
        Device device = deviceRepository.findByDeviceNumber(deviceNumber);
        if (isOpen){
            device.setRunState(DeviceRunStatusEnum.RUNNING);
        }else {
            device.setRunState(DeviceRunStatusEnum.STOP);
        }
        return ResultUtil.success();
    }

    public Result getDeviceDetail(String deviceNumber){
        Device device = deviceRepository.findByDeviceNumber(deviceNumber);
        if (device != null){
            DeviceDetailVo detailVo = new DeviceDetailVo();
            detailVo.setDetailAddress(device.getDetailAddress());
            detailVo.setDeviceName(device.getDeviceName());
            detailVo.setDeviceNumber(device.getDeviceNumber());
            detailVo.setExpiryDate(device.getExpiryDate());
            detailVo.setId(device.getId());
            detailVo.setLatitude(device.getLatitude());
            detailVo.setLongitude(device.getLongitude());
            detailVo.setPower(device.getPower());
            detailVo.setRunState(detailVo.getRunState());
            List<DeviceSensorVo> sensorVoList = device.getItems().stream().map(deviceSensor -> {
                DeviceSensorVo deviceSensorVo = new DeviceSensorVo();
                deviceSensorVo.setEnabled(deviceSensor.isEnabled());
                deviceSensorVo.setId(deviceSensor.getId());
                deviceSensorVo.setSensorCode(deviceSensor.getSensorCode());
                deviceSensorVo.setSensorName(deviceSensor.getSensorName());
                deviceSensorVo.setSensorStatus(deviceSensor.getSensorStatus());
                deviceSensorVo.setSensorValue(deviceSensor.getSensorValue());
                return deviceSensorVo;
            }).collect(Collectors.toList());
            detailVo.setDeviceSensorVoList(sensorVoList);
            return ResultUtil.success(detailVo);
        }else {
            return ResultUtil.error(1,"设备不存在");
        }
    }

    public Result getDeviceDetail(String deviceNumber,String sensorCodes){
        Device device = deviceRepository.findByDeviceNumber(deviceNumber);
        if (device != null){
            HashMap<String,Object> result = new LinkedHashMap<>();
            result.put("deviceNumber",device.getDeviceNumber());
            result.put("longitude",device.getLongitude());
            result.put("latitude",device.getLatitude());
            result.put("power",device.getPower());
            result.put("expiryDate",device.getExpiryDate());
            result.put("sensorAble","0xFF");
            if (StringUtils.hasText(sensorCodes)){
                String[] array = sensorCodes.split(",");
                for (String sensor : array) {
                    result.put(sensor,device.getItems().stream().filter(deviceSensor -> deviceSensor.getSensorCode().name().equals(sensor))
                            .collect(Collectors.toList()).get(0).getSensorStatus().code);
                }
            }
            return ResultUtil.success(result);
        }else {
            return ResultUtil.error(1,"设备不存在");
        }
    }

    @Transactional
    public Result addDevicePositionRecord(DevicePositionRecordVo recordVo){
        Device device = deviceRepository.findByDeviceNumber(recordVo.getDeviceNumber());
        DevicePositionRecord record = new DevicePositionRecord(recordVo.getLongitude(),recordVo.getLatitude(),device);
        devicePositionRecordRepository.save(record);
        return ResultUtil.success();
    }

    @Transactional
    public Result editDeviceInfo(DeviceEditVo editVo){
        Device device = deviceRepository.findByDeviceNumber(editVo.getDeviceNumber());
        if (device != null){
            if (StringUtils.hasText(editVo.getLatitude()) && StringUtils.hasText(editVo.getLongitude())){
                DevicePositionRecord record = new DevicePositionRecord(editVo.getLongitude(),editVo.getLatitude(),device);
                devicePositionRecordRepository.save(record);
            }
            if (editVo.getPower()!= null){
                device.setPower(editVo.getPower());
            }
            if (StringUtils.hasText(editVo.getRunState())){
                device.setRunState(DeviceRunStatusEnum.valueOf(editVo.getRunState()));
            }
            if (editVo.getFaultCode() != null && editVo.getFaultCode().size()>0){
                editVo.getFaultCode().forEach(s -> {
                    DeviceSensor sensor;
                    switch (s){
                        case "1001"://门控故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.MEN_KONG_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.ABNORMAL);
                            break;
                        case "1002"://取框故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.QU_KUANG_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.ABNORMAL);
                            break;
                        case "1003"://漂洗温度传感器故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.DISINFECTION_TEMPERATURE_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.ABNORMAL);
                            break;
                        case "1004"://主洗温度传感器故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.WATER_TANK_TEMPERATURE_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.ABNORMAL);
                            break;
                        case "1005"://气包低水位故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.BUBBLE_HYDROPENIA_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.ABNORMAL);
                            break;
                        case "1006"://水箱低水位门控故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.WATER_TANK_HYDROPENIA_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.ABNORMAL);
                            break;
                        default:
                            break;
                    }
                });
                List<String> codeList = new ArrayList<>(Arrays.asList("1001","1002","1003","1004","1005","1006"));
                codeList.removeAll(editVo.getFaultCode());
                codeList.forEach(s -> {
                    DeviceSensor sensor;
                    switch (s){
                        case "1001"://门控故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.MEN_KONG_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.NORMAL);
                            break;
                        case "1002"://取框故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.QU_KUANG_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.NORMAL);
                            break;
                        case "1003"://漂洗温度传感器故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.DISINFECTION_TEMPERATURE_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.NORMAL);
                            break;
                        case "1004"://主洗温度传感器故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.WATER_TANK_TEMPERATURE_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.NORMAL);
                            break;
                        case "1005"://气包低水位故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.BUBBLE_HYDROPENIA_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.NORMAL);
                            break;
                        case "1006"://水箱低水位门控故障
                            sensor = deviceSensorRepository.findBySensorCodeAndDevice_Id(DeviceSensorCodeEnum.WATER_TANK_HYDROPENIA_SENSOR,device.getId());
                            sensor.setSensorStatus(SensorStatusEnum.NORMAL);
                            break;
                        default:
                            break;
                    }
                });
            }else {
                device.getItems().forEach(deviceSensor -> deviceSensor.setSensorStatus(SensorStatusEnum.NORMAL));
            }
            return ResultUtil.success();
        }else {
            return ResultUtil.error(1,"设备不存在");
        }
    }

    public Result getDeviceByPage(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Device> devicePage = deviceRepository.findAll(pageable);
        List<DeviceListVo> list = devicePage.getContent().stream().map(DeviceListVo::new).collect(Collectors.toList());
        return ResultUtil.success(new PageResponse<>(devicePage,list));
    }
}
