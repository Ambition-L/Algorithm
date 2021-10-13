package com.xyp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputData {
    public static void main(String[] args) {
        InputData inputData = new InputData();
        List<String> inputs = inputData.inputs("/Users/xueyupeng/Desktop/1.txt");
        System.out.println(inputData.inversion(inputs));
    }

    /**
     * 将行数据封装到对象集合中
     * @param datas
     * @return
     */
    public List<InputDatas> inversion(List<String> datas) {
        if (datas == null || datas.size() == 0) {
            return null;
        }

        /**
         * 过滤 分割行字符串 和 字段行字符串
         *   这里的字段过滤可以根据 反射拿到对象的字段名放入 集合数组中 通过方法传入 进行比较过滤 更具有通用性
         */
        List<String> filterList =
                datas.stream()
                        .filter(t -> Objects.nonNull(t) && t.charAt(0) != '+' && !t.contains("uuid"))
                        .collect(Collectors.toList());

        if (filterList.size() == 0) {
            return null;
        }

        // 返回对象
        return filterList.stream().map(t -> {
            String[] split = spilts(t);

            // 封装对象
            return InputDatas.builder()
                    .report_prov(split[0])
                    .sub_device_mac(split[1])
                    .sub_device_name(split[2])
                    .sub_device_type(split[3])
                    .sub_device_manufacture(split[4])
                    .sub_device_model(split[5])
                    .sub_device_wlan_radio_power(split[6])
                    .sub_device_wlan_radio_type(split[7])
                    .time_id(split[8])
                    .uuid(split[9])
                    .build();
        }).collect(Collectors.toList());
    }

    /**
     * 切割字符串 并去除字符串前后空格
     * @param str
     * @return
     */
    public String[] spilts(String str) {
        // 根据 ｜分割字符串 \\转义
        String[] split = str.split("\\|");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }

    /**
     * 读取txt中的数据 把每一行数据封装入集合中
     * @param filePath
     * @return
     */
    public List<String> inputs(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            File filename = new File(filePath); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            list.add(line);
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
