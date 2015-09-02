package cn.edu.bit.linc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.edu.bit.linc.dao.ICatalog;
import cn.edu.bit.linc.dao.IComponent;
import cn.edu.bit.linc.dao.IProduct;
import cn.edu.bit.linc.pojo.Catalog;
import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.util.DBUtil;

@SessionAttributes
@Controller
public class ProductController {

	@RequestMapping("/add")
	public String add(){
		//System.out.println("hello!");
		return "addProduct";
	}
	
	@ResponseBody
	@RequestMapping(value="/catalog", method=RequestMethod.GET)
	public List<Catalog> getCatalogs(HttpServletRequest request){

		SqlSession session = DBUtil.openSession();
		ICatalog icatalog = session.getMapper(ICatalog.class);
		List<Catalog> catalogs = null;
		
		System.out.println(request.getCharacterEncoding());
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(id != null) {
			catalogs = icatalog.selectCatalogById(id);
		}
		else if(name != null) {
			System.out.println("ProductController.getCatalogs() " + name);
			catalogs = icatalog.selectCatalogByName(name);
		}
		else {
			catalogs = icatalog.selectCatalogs();
		}
		session.close();
		return catalogs;
	}
	
	@ResponseBody
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public List<Product> getProducts(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		List<Product> products = null;
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(id != null) {
			products = iproduct.selectProductById(id);
		}
		else if(name != null) {
			//System.out.println("ProductController.getCatalogs() " + name);
			//catalogs = icatalog.selectCatalogByName(name);
		}
		else {
			products = iproduct.selectProducts();
		}
		
		session.close();
		return products;
	}
	
	@ResponseBody
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public int addProduct(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		Product product = new Product();
		
		String name = request.getParameter("name");
		String catalog_id = request.getParameter("catalog_id");
		String description = request.getParameter("description");
		String shop = request.getParameter("shop");
		
		product.setProduct_name(name);
		try {
			product.setCatalog_id(Integer.parseInt(catalog_id));
		} catch(Exception e) {
			e.printStackTrace();
		}
		product.setDescription(description);
		product.setPicture("22");
		product.setShop(shop);
		
		System.out.println(product.toString());
		
		int ret = iproduct.insertProduct(product);
		
		session.commit();
		session.close();
		return ret;
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String addProductPost(HttpServletRequest request) throws IllegalStateException, IOException{
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		Product product = new Product();
		
		String name = request.getParameter("product_name");
		String catalog_id = request.getParameter("catalog");
		String description = request.getParameter("description");
		String shop = request.getParameter("shop");
		
		product.setProduct_name(name);
		try {
			product.setCatalog_id(Integer.parseInt(catalog_id));
		} catch(Exception e) {
			e.printStackTrace();
		}
		product.setDescription(description);
		product.setShop(shop);
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
                request.getSession().getServletContext());  
   
        if (multipartResolver.isMultipart(request)) {  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
  
            Iterator<String> iter = multiRequest.getFileNames();  
            while (iter.hasNext()) {  
  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if (file != null) {
                    String fileName = System.currentTimeMillis() + file.getOriginalFilename();  
                    String path = request.getSession().getServletContext().getRealPath("/resourses/imgUpload/") + fileName;  
                    System.out.println(path + " " + file.getOriginalFilename());
                    
                    if(file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
                    	product.setPicture(fileName);
                    	File localFile = new File(path);  
                    	file.transferTo(localFile);
                    }
                }
            }  
        }
		
		System.out.println(product.toString());
		
		int ret = iproduct.insertProduct(product);
		
		session.commit();
		session.close();
		return "addProduct";
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
