package com.example.an.retrofittest;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author: WDSG
 * date: 2017/3/7
 */
public class Conts {

    public static Map getConfigMap(){
        Map map = new HashMap();
        map.put("os_type", "1");
        map.put("appid", "1");
        map.put("imei", "867614023363542");
        map.put("app_version", "35000");
        map.put("channel", "web");
        map.put("auth_tms", "20170306133730");
        map.put("auth_did", "5793");
        map.put("auth_dsig", "08526488a4ade5a8");
        map.put("auth_uid", "193594");
        map.put("auth_usig", "8493bf3e111114c9");
        map.put("xid", "193594");
        return map;
    }
}
