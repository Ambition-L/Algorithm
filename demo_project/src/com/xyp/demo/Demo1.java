//package com.xyp.demo;
//
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//public class Demo1 {
//    private Integer a = 1;
//    public static void main(String[] args) {
//
//        List<JSONObject> jsonObjects = new ArrayList<>();
//
//        jsonObjects.stream().map(t -> {
//            List<String> strings = new ArrayList<>();
//            strings.add("sss");
//            return strings;
//        }).collect(Collectors.toList());
//
//        List<Student> students = Arrays.asList(
//                new Student("zs", 1001),
//                new Student("zs", 1001),
//                new Student("zs", 1001)
//                );
//
//        List<Teacher> teachers = students.stream().map(t -> {
//            return Teacher.builder()
//                    .name(t.getName())
//                    .num(t.getNum())
//                    .build();
//        }).collect(Collectors.toList());
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("stu", new Student("ls",1005));
//
//        String jsonString = JSON.toJSONString(map);
//        System.out.println(jsonString);
//        Map<String, Object> parse = null;
//        try {
//            parse = (Map<String, Object>) JSON.parse(jsonString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(parse);
//    }
//}
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//class Teacher {
//    private String name;
//    private Integer num;
//}
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//class Student {
//    private String name;
//    private Integer num;
//}
