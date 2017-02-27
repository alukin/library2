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
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
