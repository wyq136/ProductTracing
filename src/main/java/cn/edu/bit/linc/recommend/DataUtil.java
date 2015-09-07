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
			if(!res.containsKey(p.getProduct_id())){
				List<String> list = new ArrayList<String>();
				list.add("catalog_" + p.getCatalog_id());			
				list.add("component_" + p.getComponent_id());
				res.put(p.getProduct_id(), list);
			}
			else{
				res.get(p.getProduct_id()).add("component_" + p.getComponent_id());
			}
		}
		
		File file = new File("Data/localFood");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
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
	}
	
	public static void getZhDataFromDB() throws IOException{
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		List<ProductInfo> products = iproduct.getProductInfos();
		session.close();
		
		Map<Integer, List<String>> res = new HashMap<Integer, List<String>>();
		for(ProductInfo p : products){
			if(!res.containsKey(p.getProduct_id())){
				List<String> list = new ArrayList<String>();
				list.add(p.getCatalog_name());			
				list.add(p.getComponent_name());
				res.put(p.getProduct_id(), list);
			}
			else{
				res.get(p.getProduct_id()).add(p.getComponent_name());
			}
		}
		
		File file = new File("Data/localFood");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
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
