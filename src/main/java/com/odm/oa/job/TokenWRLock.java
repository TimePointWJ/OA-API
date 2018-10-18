package com.odm.oa.job;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TokenWRLock {
	
	//微信token信息，当超时对其加读写，保证微信业务正常  
    private static String accessToken = null;  
    //创建一个读写锁  
    private static ReadWriteLock lock = new ReentrantReadWriteLock();  
  
    /* 
    * 
    */  
    public static void setToken(String token) {  
        try {  
            lock.writeLock().lock();  
            //Thread.sleep(1000);  
            accessToken = token;
//            Constants.ACCESS_TOKEN_KBN_PARENT = token;
//            Constants.ACCESS_TOKEN_KBN_TEACHER = token;
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            lock.writeLock().unlock();  
        }  
    }  
  
    /* 
    * 获取token加锁 
    */  
    public static String getTokenWithLock() throws Exception {  
        lock.readLock().lock();  
        //logger.info("@@@@@"+new Date().getSeconds());  
        return accessToken;  
        //fun();  
        //lock.readLock().unlock();  
    }  
  
    /* 
    * 释放token锁 
    */  
    public static void unLockToken() throws Exception {  
        //lock.readLock().lock();  
        //logger.info("@@@@@"+new Date().getSeconds());  
        //fun();  
        lock.readLock().unlock();  
    }
}
