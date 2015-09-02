package cn.edu.bit.linc.recommend;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 2015/9/2.
 */
public class makeRec {
    String ipAdress = "127.0.0.1";
    Jedis jedis;

    public static makeFood localFood;
    public static makeFood remoteFood;

    static{
        localFood = new makeFood();
        remoteFood = new makeFood();
        localFood.config();
        remoteFood.config();

        try {
            String localFoodString = "Data/localFood";
            String remoteFoodString = "Data/remoteFood";
            localFood.saveProduct(localFoodString,0);
            remoteFood.saveProduct(remoteFoodString, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public ArrayList<String> recommendList(String userId) {
        String userLikeString = "芝士:奥利奥饼干:苹果片:红豆";

        String userDislikeString = "巧克力";

        int recommendNum = 10;

        Date date = new Date();
        String time = date.getHours() + ":" + date.getMinutes();
        localFood.saveReqToDB(userId, time, userLikeString);

        localFood.makeFinalRecommend(userId,userLikeString,userDislikeString,0);
        if(localFood.numOfFood < recommendNum && localFood.requestList.size() > 0) {
            userLikeString = "";
            for(String str:localFood.requestList){
                userLikeString += str + ":";
            }
            userLikeString.substring(0, userLikeString.length() - 2);

            remoteFood.saveReqToDB(userId,time,userLikeString);
            remoteFood.makeFinalRecommend(userId, userLikeString, userDislikeString,1);
        }

        ArrayList<String > finalRecommend = new ArrayList<String>();
        finalRecommend.addAll(localFood.recommendResult);
        finalRecommend.addAll(remoteFood.recommendResult);

        return finalRecommend;
    }


    public static void main(String[] args){
        makeRec mr = new makeRec();

        ArrayList<String > finalRecommend = mr.recommendList("claire");
        System.out.print(finalRecommend);

    }


}
