package cn.edu.bit.linc.util;

import java.util.Random;

public class RandomUtil {
	
	private final static Random r = new Random();
	
	public static int[] RandomArray(int length){
		int[] ran = new int[length];
		for(int i=0; i<ran.length; i++){
			ran[i] = i;
		}
		
		int[] res = new int[length];
		for(int i=0; i<res.length; i++){
			int r = (int) (Math.random() * length);
			res[i] = ran[r];
			ran[r] = ran[length-1];
			length--;
		}
//		for(int i:res){
//			System.out.println(i);
//		}
		
		return res;
	}
	
	public static int RandomNum(int up) {
		return r.nextInt(up+1);
	}

	public static void main(String[] args) {
		
		RandomUtil.RandomArray(10);
	}

}
