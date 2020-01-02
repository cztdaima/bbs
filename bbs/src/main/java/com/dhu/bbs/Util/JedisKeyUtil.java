package com.dhu.bbs.Util;

public class JedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_INFORM = "INFORM";


    public static String getLikeKey(int entityId){
        return BIZ_LIKE + SPLIT + String.valueOf(entityId);
    }

    public static String getInformKey( int entityId){
        return BIZ_INFORM + SPLIT + String.valueOf(entityId);
    }
}
