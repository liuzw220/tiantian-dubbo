package com.tiantian.common.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json和对象实体类相互转化 ,常用操作
 * @author schoolBoy 刘志伟
 * @data 2015年5月18日 下午2:22:55
 */
public class JsonEntityUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 吧一个json转化成实体对象 ,如果能转则返回转化后的结果集合，否则返回null
     * @param jsonData
     * @param clazz 对象字节码信息
     * @return 对象集合
     * @throws IOException 
     * @throws JsonProcessingException 
     */
    public static List<?> jsonToEntity(String jsonData, Class<?> clazz){
        try {
            //定义一个jsonNode
            JsonNode jsonNode=MAPPER.readTree(jsonData);
            //定义一个泛型集合
            List<?> list = null;
            //json转化对象集合
            if(jsonNode.isArray()&&jsonNode.size()>0){
                list = MAPPER.readValue(jsonNode.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return list;
        } catch ( Exception e) {
            //e.printStackTrace();
            System.out.println("对象序列化为json失败");
            return null;
        } 
    }
    /**
     * 吧一个实体对象(或者集合)转化成json对象
     * @param obj 实体对象(或者集合)
     * @return json对象
     */
    public static String entityToJson(Object obj){
        try {
            return  MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("写日志：对象序列化为"+obj.getClass().getName()+"失败");
            return null;
        }
    }

    /**
     *  吧一个json字符安装指定的类型强制转化
     * @param jsonData json字符
     * @param clazz 需要转化的类型对象
     * @return 
     */
    public static Object jsonToObject(String jsonData,Class<?> clazz){
        try {
            return MAPPER.readValue(jsonData, clazz);
        } catch ( Exception e) {
            System.out.println("写日志 ,json转化为"+clazz.getName()+"出错");
            return null;
        } 
    }

    /**
     * 获取json对象的某个属性的值
     * @param jsonData json对象
     * @param property  json对象的属性名称
     * @return json对象的属性名值
     */
    public static String jsonToProperty(String jsonData,String property){
        try {
            JsonNode jsonNode=MAPPER.readTree(jsonData);
            return jsonNode.get(property).toString();
        } catch ( Exception e) {
            //e.printStackTrace();
            System.out.println("写日志 ,获取"+property+"属性出错");
            return null;
        } 
    }

    /**
     * 吧一个json对象装换为 JsonNode
     * @param jsonData  json对象
     * @return 转化好的JsonNode对象 
     */
    public static JsonNode jsonToJsonNode(String jsonData){
        try {
            return MAPPER.readTree(jsonData);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("写日志 ,json转成出错");
            return null;
        }
    }


}
