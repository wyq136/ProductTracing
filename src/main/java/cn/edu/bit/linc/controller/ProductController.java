package cn.edu.bit.linc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ibm.db2.jcc.a.c;
import com.ibm.db2.jcc.am.po;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.bit.linc.dao.IAttribute;
import cn.edu.bit.linc.dao.IComponent;
import cn.edu.bit.linc.dao.IProduct;
import cn.edu.bit.linc.dao.IUserOperation;
import cn.edu.bit.linc.pojo.Attribute;
import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.pojo.RequestComponent;
import cn.edu.bit.linc.pojo.User;
import cn.edu.bit.linc.recommend.makeRec;
import cn.edu.bit.linc.util.DBUtil;
import cn.edu.bit.linc.util.RandomUtil;

@SessionAttributes
@Controller
public class ProductController {

	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/download")
	public String download(){
		return "download";
	}
	
	@RequestMapping("/")
	public String add(Model model){
		//System.out.println("hello!");
		//SqlSession session = DBUtil.openSession();
	//	ICatalog icatalog = session.getMapper(ICatalog.class);
		//List<Catalog> catalogs = icatalog.selectCatalogs();
		
		//model.addAttribute("catalogList", catalogs);
		
		return "addProduct";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/product")
	public List<Product> getProducts(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		List<Product> products = null;
		
		String id = request.getParameter("id");
		if(id != null) {
			products = iproduct.getProductById(id);
		}
		else {
			products = iproduct.getProducts();
		}
		
		session.close();
		return products;
	}
	
	
	
@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ModelAndView showProduct(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView("productInfo");
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		IComponent icomponent = session.getMapper(IComponent.class);
		IAttribute iattribute = session.getMapper(IAttribute.class);
		
		Product product = iproduct.getProductByID(id);
		List<Component> components = icomponent.getComponentByProductId(product.getProductID());
		//TODO
		//for(Component com : components) {
			//List<Attribute> attributes = iattribute.selectAttributeByComponentId(com.getId());
			//com.setAttributes(attributes);
		//}
		
		mv.addObject("product", product);
		mv.addObject("components", components);
		
		return mv;
	}
//TODO
/*
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
		
		product.setProductName(name);
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
	public String addProductPost(HttpServletRequest request, HttpServletResponse response, Model model) throws IllegalStateException, IOException{
		SqlSession session = DBUtil.openSession();
		IProduct iproduct = session.getMapper(IProduct.class);
		IComponent iComponent = session.getMapper(IComponent.class);
		
		Product product = new Product();
		
		String name = request.getParameter("product_name");
		String catalog_id = request.getParameter("catalog_id");
		String description = request.getParameter("description");
		String shop = request.getParameter("shop");
		String[] component_name = request.getParameterValues("component_name");
		String[] component_description = request.getParameterValues("component_description");
		
//		System.out.println(component_name[0] + " " + component_name[1]);
//		System.out.println(component_description[0] + " " + component_description[1]);
		
		if(name.equals(""))
			name = null;
		product.setProductName(name);
		try {
			if(catalog_id.equals(""))
				catalog_id = null;
			product.setCatalog_id(Integer.parseInt(catalog_id));
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(description.equals(""))
			description = null;
		product.setDescription(description);
		if(shop.equals(""))
			shop = null;
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
                    String path = request.getSession().getServletContext().getRealPath("/resourses/imgUpload/") + "/" + fileName;  
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
		
		int ret = 0;
		
		try {
			ret = iproduct.insertProduct(product);
		} catch(Exception e) {
			ret = 0;
			e.printStackTrace();
		}
		
		System.out.println(product.toString());
		
		if(ret == 1 && product.getId() != 0) {
			if(component_name != null && component_description != null) {
				for(int i = 0; i < component_name.length; ++i) {
					try {
						
						Component component = new Component();
						component.setComponent_name(component_name[i]);
						component.setDescription(component_description[i]);
						component.setProduct_id(product.getId());
						System.out.println(component.toString());
						
						ret = iComponent.insertComponent(component);
						if(0 == ret)
							break;
						
					} catch (Exception e) {
						ret = 0;
						e.printStackTrace();
					}
				}
			}
			else ret = 0;
		}
		
		if(ret != 0) {
			session.commit();
			model.addAttribute("message", "娣诲姞鎴愬姛" + product.getId());
		}
		else
			model.addAttribute("message", "娣诲姞澶辫触");
		
		session.close();
		
		return "addProduct";
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="/component")
	public List<Component> getComponents(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IComponent icomponent = session.getMapper(IComponent.class);
		List<Component> components = null;
		
		String product_id = request.getParameter("product_id");
		if(product_id != null) {
			components = icomponent.getComponentByProductId(Integer.parseInt(product_id));
		}
		else {
			components = icomponent.getComponents();
		}
		session.close();
		int[] res = RandomUtil.RandomArray(components.size());
		List<Component> random = new ArrayList<Component>();
		for(int i=0; i<components.size(); i++){
			random.add(components.get(res[i]));
		}
		return random;
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IUserOperation iUser = session.getMapper(IUserOperation.class);
		User res = null;
		String username = request.getParameter("username");
		res = iUser.selectUserByUsername(username);
		System.out.println(res);
		session.close();
		if(res != null)
		return "success";
		else return "用户名或密码错误";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/rigister")
	public String rigister(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IUserOperation iUser = session.getMapper(IUserOperation.class);

		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		System.out.println("name: " + username + " pass: " +password+ " email:" + email);
		User usr = null;
		usr = iUser.selectUserByUsername(username);
		if(usr != null)
			return "用户名已存在";
		usr.setUsername(username);
		usr.setPassword(password);
		usr.setEmail(email);
		iUser.addUser(usr);
		session.commit();
		session.close();
		

		
		
		return "success";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/recommend")
	public List<Product> getRecommend(@RequestBody RequestComponent requestComponent, HttpSession session){
		System.out.println("session id: " + session.getId());
		Component[] components = requestComponent.getLike();
		Component[] dislikeComponents = requestComponent.getDislike();
		//System.out.println(components.length);
		//System.out.println(components[0]);
		//System.out.println(dislikeComponents.length);
		if(components.length == 0){
			List<Product> products = new ArrayList<Product>();
			SqlSession sqlsession = DBUtil.openSession();
			IProduct iproduct = sqlsession.getMapper(IProduct.class);
			products = iproduct.getProducts();
			sqlsession.close();
			
			List<Product> random = new ArrayList<Product>();
			int[] ran = RandomUtil.RandomArray(products.size());
			for(int i=0; i<ran.length && i<3; i++){
				random.add(products.get(ran[i]));
			}
			return random;
		}
		
		//like
		String userLikeString = "";
		for(int i=0; i<components.length-1; i++){
			userLikeString += components[i].getComponentName() + ":";
		}
		if(components.length > 0)
			userLikeString += components[components.length-1].getComponentName();
		System.out.println(userLikeString);
		
		//dislike
		String userDislikeString = "";
		for(int i=0; i<dislikeComponents.length-1; i++){
			userDislikeString += dislikeComponents[i].getComponentName() + ":";
		}
		if(dislikeComponents.length > 0)
			userDislikeString += dislikeComponents[dislikeComponents.length-1].getComponentName();
		System.out.println(userDislikeString);
		
		//get recommend
		LinkedHashMap<String, String> result = null;
		makeRec mr = new makeRec();
		result = mr.recommendList(session.getId(), userLikeString, userDislikeString);
		
		List<Product> products = new ArrayList<Product>();
		SqlSession sqlsession = DBUtil.openSession();
		IProduct iproduct = sqlsession.getMapper(IProduct.class);
		for(String p : result.keySet()){
			System.out.println(p + " : " + result.get(p));
			int id = -1;
			try{
				id = Integer.parseInt(p);
			}
			catch(NumberFormatException e){
				//e.printStackTrace();
				break;
			}
			if(id != -1){
				Product product = iproduct.getProductByID(id);
				product.setRate(result.get(p));
				products.add(product);
			}
		}
		sqlsession.close();
		//System.out.println(products);
		
		return products;
		
	}
	
}
