package ua.cn.al.teach.library2.utils;

import java.util.UUID;

/*
 * 
 * 
 */

/**
 *
 * @author al
 */
public class EntityIdGenerator {
    public static Long random(){
        Long l = UUID.randomUUID().getLeastSignificantBits();
        if(l<0){
            l=-l;
        }
        return l;
    }
}
