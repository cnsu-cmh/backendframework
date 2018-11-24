package com.xiaoshu.backendframework.util;

import com.google.common.collect.Maps;
import com.xiaoshu.backendframework.model.Dict;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class ObjectUtils {

    static {
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new DateConverter(null), Date.class);
        ConvertUtils.register(new Converter() {

            public String convert(Class clazz, Object value) {
                if(value == null){
                    return null;
                }else if(!(value instanceof String)){
                    return null;
                }else if(value.toString().trim().equals("")){
                    return null;
                }
                return value.toString();
            }
        }, String.class);
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null) {
            return null;
        }
        try {
            Object obj = beanClass.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
            return obj;
        } catch (Exception e) {

        }
        return null;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static void main(String[] args) {
        Map map = Maps.newHashMap();
        map.put("type","type");
        map.put("type","k");
        map.put("type","val");
        map.put("id","");
        map.put("start",0);
        map.put("length",10);
        Dict o = (Dict)mapToObject(map, Dict.class);
        System.out.println(o.getK());
    }
}
