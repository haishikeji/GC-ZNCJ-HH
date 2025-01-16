package com.pc.system.dto;

import com.pc.system.domain.DeviceRuntimeDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 设备实时运行表
 * @author 品讯科技
 * @Version V1.0.0
 * @date 2024-08
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRealTimeRunningDTO {

    //设备名称
    private String deviceName;

    //设备编号
    private String deviceNumber;

    //设备ip
    private String ipAddress;

    //设备运行状态
    List<DeviceRuntimeDO> deviceRuntimeDOList;

}
