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
 * Created by admin on 2015/8/17.
 */
public class makeFood {
    Jedis jedis;
    int numOfFood = 0;
    int recommendNum = 10;
    int requestSize = 0;
    ArrayList<String> requestList = new ArrayList<String>();
    ArrayList<String> mostResult = new ArrayList<String>();
    ArrayList<String> rareResult = new ArrayList<String>();
    //ArrayList<String> typeResult = new ArrayList<String>();
    ArrayList<String> recommendResult = new ArrayList<String>();
    //Set<String> allType = new HashSet<String>();
    HashMap<String,Boolean> componentMap = new HashMap<String, Boolean>();      //initialize to be true, if have, change to false
    HashMap<String,Integer> product = new HashMap<String, Integer>();
    ArrayList<String> recommendCount = new ArrayList<String>();
    int[][] score = {{100,100,100},{60,100,100},{60,80,100},{60,75,87}};

    public void config() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);

        config.setTestOnBorrow(false);

        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        this.jedis = jedisPool.getResource();

    }

    /**
     * Save product from file to Redis
     * @param filePath
     * @param datasource 0-local;1-dianping
     * @throws IOException
     */
    public void saveProduct(String filePath,int datasource) throws IOException {
        File f = new File(filePath);

        jedis.select(datasource);

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = "";
        while((line = reader.readLine()) != null){
            String[] content = line.split(":");

            String pid = content[0];
            String component = content[1];

            jedis.hset(pid, "component", component);

            String components[] = component.split(",");
            for(String str:components){
                jedis.sadd(str,pid);
            }
            //numOfFood++;
        }
        reader.close();
    }
    /**
     * Save user request(like) to DB
     * @param user
     * @param time
     * @param req
     */
    public void saveReqToDB(String user,String time,String req){
        jedis.hset(user, "time", time);
        jedis.hset(user, "component", req);
        String[] userLike = req.split(":");
        for(String str:userLike){
            requestList.add(str);
        }
        requestSize = requestList.size();
    }

    /**
     * Get votes of each product
     * @return
     */
    public HashMap<String,Integer> countProduct(String userId,String dislike){
        HashMap<String,Integer> hm = new HashMap<String, Integer>();
        //Get components user likes
        //String[] component = jedis.hget(userId, "component").split(":");
        //Get the only component user dislikes

        for(String str:requestList) {
            componentMap.put(str,true);
            Set<String> smembers = jedis.smembers(str);
            for (String s : smembers) {
                if (hm.get(s) == null) {
                    hm.put(s, 1);
                } else {
                    hm.put(s, hm.get(s) + 1);
                }
            }
        }

        //Remove products user dislikes
        Set<String> sdislike = jedis.smembers(dislike);
        for(String s : sdislike){
            if(hm.get(s) != null)   hm.remove(s);
        }
        
        System.out.println(hm.size());

        return hm;
    }

    /**
     * Add products which have most votes
     * @param productCount
     */
    public void addMost(HashMap<String,Integer> productCount,String userDislikeString){
        this.numOfFood = 0;
        int max = 0;

        for(Map.Entry<String,Integer> entry : productCount.entrySet()){
            int val = entry.getValue();
            if(val > max) {
                max = val;
            }
        }

        for(Map.Entry<String,Integer> entry : productCount.entrySet()){
            int val = entry.getValue();
            if(val == max) {
                String key = entry.getKey();

                String component = jedis.hget(key,"component");
                if(!"".equals(userDislikeString) && component.indexOf(userDislikeString) != -1)   continue;
                else{
                    mostResult.add(key);
                    this.numOfFood ++;
                    String[] c = component.split(",");
                    for(String str:c){
                        if(componentMap.get(str) != null && componentMap.get(str)){
                            componentMap.put(str,false);
                            requestList.remove(str);
                        }
                    }

                }


//                String type = jedis.hget(key,"type");
//                typeMap.put(type,false);
            }
        }
    }

    /**
     * Add rare component from local
     */
    public void addRare(String userDislikeString){

//        for(Map.Entry<String,Boolean> entry:componentMap.entrySet()){
//            //there's component to be true(left)
//            if(entry.getValue()){
//                String componentLeft = (String) entry.getKey();
        for(int i = 0;i < requestList.size();i++){
            String componentLeft = requestList.get(i);
            Set<String> smembers = jedis.smembers(componentLeft);

            //If the left component is in local
            if(smembers != null) {
                for (String str : smembers) {
                    String comp = jedis.hget(str,"component");
                    if(!"".equals(userDislikeString) && comp.indexOf(userDislikeString) != -1)   continue;
                    rareResult.add(str);
                    numOfFood++;
                    componentMap.put(componentLeft, false);
                    requestList.remove(componentLeft);

                }
            }
        }
    }

    public void addType(String userDislikeString){
        int max = 0;
        String res = null;
        String resType = null;
        for(Map.Entry<String,Integer> entry : this.product.entrySet()){
            String key = entry.getKey();
            int val = entry.getValue();
        }
        if(res != null){
            
            numOfFood++;
        }

    }

    public void makeFinalRecommend(String userId, String userLikeString ,String userDislikeString, int dataSource){

        jedis.select(dataSource);

        product = countProduct(userId, userDislikeString);

        addMost(product, userDislikeString);
        if(requestList.size() > 0  && numOfFood < recommendNum){
            addRare(userDislikeString);
        }

//        System.out.println("most:"+mostResult);
//        System.out.println("rare:"+rareResult);
//        System.out.println("type:" + typeResult);

        recommendResult.addAll(mostResult);
        recommendResult.addAll(rareResult);

        for(int i = 0;i < recommendResult.size();i++){
            int val = product.get(recommendResult.get(i));
            if(val < 4 ){
            	String num = score[requestSize-1][val-1] + "%";
            	recommendCount.add(num);
            }
            else{
            	recommendCount.add("100%");
            }
        }
    }
}

