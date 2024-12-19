package com.zzf.enums;

public enum GroupEnums {

    GOOD, BAD;

    public static boolean isGood(GroupEnums groupEnums){
        return groupEnums == GOOD;
    }
}
