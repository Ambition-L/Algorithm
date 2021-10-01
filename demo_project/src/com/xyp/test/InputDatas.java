package com.xyp.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Guan FuQing
 * @Date: 2021/09/29 9:16 上午
 * @Email: moumouguan@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputDatas {
    String report_prov;
    String uuid;
    String sub_device_type;
    String sub_device_name;
    String sub_device_manufacture;
    String sub_device_model;
    String sub_device_mac;
    String sub_device_wlan_radio_type;
    String sub_device_wlan_radio_power;
    String time_id;
}
