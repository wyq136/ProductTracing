package cn.edu.bit.linc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.edu.bit.linc.dao.ICatalog;
import cn.edu.bit.linc.dao.IComponent;
import cn.edu.bit.linc.dao.IProduct;
import cn.edu.bit.linc.pojo.Catalog;
import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.recommend.Food;
import cn.edu.bit.linc.util.DBUtil;

@SessionAttributes
@Controller
public class ProductController {

	@ResponseBody
	@RequestMapping(value="/catalog", method=RequestMethod.GET)
	public List<Catalog> getCatalogs(){
		SqlSession session = DBUtil.openSession();
		ICatalog icatalog = session.getMapper(ICatalog.class);
		List<Catalog> catalogs = icatalog.selectCatalogs();
		session.close();
		return catalogs;
	}
	
	@ResponseBody
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public List<Product> getProducts(){
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		List<Product> products = iproduct.selectProducts();
		session.close();
		return products;
	}
	
	@ResponseBody
	@RequestMapping(value="/component", method=RequestMethod.GET)
	public List<Component> getComponents(){
		SqlSession session = DBUtil.openSession();
		IComponent icomponent = session.getMapper(IComponent.class);
		List<Component> components = icomponent.selectComponents();
		session.close();
		return components;
	}
	
	@ResponseBody
	@RequestMapping(value="/recommend")
	public List<Product> getRecommend(@RequestBody Component[] components, HttpSession session){
		System.out.println("session id: " + session.getId());
		System.out.println(components.length);
		System.out.println(components[0]);
		
		//构建请求字符
		String reqString = "";
		for(int i=0; i<components.length-1; i++){
			reqString += components[i].getComponent_name() + ":";
		}
		reqString += components[components.length-1];
		
		//获取推荐的 product id
		ArrayList<String> result = null;
		try {
			result = Food.getRecommend(session.getId(), reqString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//构建推荐结果
		List<Product> products = new ArrayList<Product>();
		SqlSession sqlsession = DBUtil.openSession();
		IProduct iproduct = sqlsession.getMapper(IProduct.class);
		for(String p : result){
			int id = -1;
			try{
				id = Integer.parseInt(p);
			}
			catch(NumberFormatException e){
				//e.printStackTrace();
				break;
			}
			if(id != -1)
				products.add(iproduct.getProductByID(id));
		}
		
		return products;
		
		
	}
	
}
