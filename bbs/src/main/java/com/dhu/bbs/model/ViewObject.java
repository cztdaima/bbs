package com.dhu.bbs.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewObject {
    private Map<String, Object> objS = new HashMap<String, Object>();
    public void set(String key, Object value){
        objS.put(key, value);
    }

    public Object get(String key){
        return objS.get(key);
    }
}
