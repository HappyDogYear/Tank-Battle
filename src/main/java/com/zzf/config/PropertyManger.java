package com.zzf.config;

import java.util.Properties;

public class PropertyManger {

    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyManger.class.getClassLoader().getResourceAsStream("config"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if(props == null) return null;
        return props.get(key);
    }

}
