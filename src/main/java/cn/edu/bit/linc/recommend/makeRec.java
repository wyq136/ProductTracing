package cn.edu.bit.linc.recommend;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by admin on 2015/9/2.
 */
public class makeRec {
    String ipAdress = "127.0.0.1";
    Jedis jedis;

    public makeFood localFood;
    public makeFood remoteFood;

    public makeRec(){

        localFood = new makeFood();
        remoteFood = new makeFood();
        localFood.config();
        remoteFood.config();
    }

    public void initData(){
        try {
//            String localFoodString = "D:\\workspace\\ProductTracing\\Data/localFood";
//            String remoteFoodString = "D:\\workspace\\ProductTracing\\Data/remoteFood";
            String localFoodString = "Data/localFood";
            String remoteFoodString = "Data/remoteFood";
            localFood.saveProduct(localFoodString,0);
            remoteFood.saveProduct(remoteFoodString, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<String,String> recommendList(String userId, String userLikeString, String userDislikeString) {
//        String userLikeString = "芝士:奥利奥饼干:苹果片:红豆";
//
//        String userDislikeString = "巧克力";

        int recommendNum = 10;

        LinkedHashMap<String,String > finalRecommend = new LinkedHashMap<String,String>();

        Date date = new Date();
        String time = date.getHours() + ":" + date.getMinutes();
        localFood.saveReqToDB(userId, time, userLikeString);

        localFood.makeFinalRecommend(userId,userLikeString,userDislikeString,0);

        for(int i = 0;i < localFood.recommendCount.size();i++){
            finalRecommend.put(localFood.recommendResult.get(i),localFood.recommendCount.get(i));
        }

        if(localFood.numOfFood < recommendNum && localFood.requestList.size() > 0) {
            userLikeString = "";
            for(String str:localFood.requestList){
                userLikeString += str + ":";
            }
            userLikeString.substring(0, userLikeString.length() - 2);

            remoteFood.saveReqToDB(userId,time,userLikeString);

            remoteFood.makeFinalRecommend(userId, userLikeString, userDislikeString,1);

            for(int i = 0;i < remoteFood.recommendCount.size();i++){
                finalRecommend.put(remoteFood.recommendResult.get(i),remoteFood.recommendCount.get(i));
            }
        }
        return finalRecommend;
    }

    public static void main(String[] args){
        makeRec mr = new makeRec();
        mr.initData();
        System.out.println("test");
    }

}
