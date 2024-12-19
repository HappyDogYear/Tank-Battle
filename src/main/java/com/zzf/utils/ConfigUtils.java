package com.zzf.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ConfigUtils {

    private static Properties properties = new Properties();


    static {
        try {
            properties.load(ConfigUtils.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getKey(String key){
        if(Objects.isNull(properties)){
            return null;
        }

        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(ConfigUtils.getKey("age"));
    }

}
