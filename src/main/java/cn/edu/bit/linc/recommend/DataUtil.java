package cn.edu.bit.linc.recommend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.edu.bit.linc.dao.IProduct;
import cn.edu.bit.linc.pojo.ProductInfo;
import cn.edu.bit.linc.util.DBUtil;

public class DataUtil {

	public static void getDataFromDB() throws IOException{
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		List<ProductInfo> products = iproduct.getProductInfos();
		session.close();
		
		Map<Integer, List<String>> res = new HashMap<Integer, List<String>>();
		for(ProductInfo p : products){
			if(!res.containsKey(p.getProductID())){
				List<String> list = new ArrayList<String>();		
				list.add("component_" + p.getComponentID());
				res.put(p.getProductID(), list);
			}
			else{
				res.get(p.getProductID()).add("component_" + p.getComponentID());
			}
		}
		
		File file = new File("Data/localFood");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for(Map.Entry<Integer, List<String>> entry : res.entrySet()){
			String line = "";
			line += entry.getKey() + ":";
			List<String> val = entry.getValue();
			for(int i=0; i<val.size()-1; i++){
				line += val.get(i) + ",";
			}
			line += val.get(val.size()-1);
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}
	
	public static void getZhDataFromDB() throws IOException{
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		// local food data
		List<ProductInfo> products = iproduct.getProductInfos();
		
		Map<Integer, List<String>> res = new HashMap<Integer, List<String>>();
		for(ProductInfo p : products){
			if(!res.containsKey(p.getProductID())){
				List<String> list = new ArrayList<String>();
				//list.add(p.getCatalog_name());
				list.add(p.getComponentName());
				res.put(p.getProductID(), list);
			}
			else{
				res.get(p.getProductID()).add(p.getComponentName());
			}
		}
		
		File file = new File("Data/localFood");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for(Map.Entry<Integer, List<String>> entry : res.entrySet()){
			String line = "";
			line += entry.getKey() + ":";
			List<String> val = entry.getValue();
			for(int i=0; i<val.size()-1; i++){
				line += val.get(i) + ",";
			}
			line += val.get(val.size()-1);
			bw.write(line);
			bw.newLine();
		}
		bw.close();
		/*
		// remote food data
		products = iproduct.getRemoteProductInfos();
		session.close();
		
		res = new HashMap<Integer, List<String>>();
		for(ProductInfo p : products){
			if(!res.containsKey(p.getProductID())){
				List<String> list = new ArrayList<String>();
				//list.add(p.getCatalog_name());			
				list.add(p.getComponentName());
				res.put(p.getProductID(), list);
			}
			else{
				res.get(p.getProductID()).add(p.getComponentName());
			}
		}
		
		file = new File("Data/remoteFood");
		bw = new BufferedWriter(new FileWriter(file));
		for(Map.Entry<Integer, List<String>> entry : res.entrySet()){
			String line = "";
			line += entry.getKey() + ":";
			List<String> val = entry.getValue();
			for(int i=1; i<val.size()-1; i++){
				line += val.get(i) + ",";
			}
			line += val.get(val.size()-1) + ":";
			line += val.get(0);
			bw.write(line);
			bw.newLine();
		}
		bw.close();
		*/
	}
	
	public static void main(String[] args) {
		try {
			//DataUtil.getDataFromDB();
			DataUtil.getZhDataFromDB();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
