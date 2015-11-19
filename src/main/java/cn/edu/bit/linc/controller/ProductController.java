package cn.edu.bit.linc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.omg.CORBA.PUBLIC_MEMBER;
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
import com.ibm.db2.jcc.am.o;
import com.ibm.db2.jcc.am.po;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.edu.bit.linc.dao.IAttribute;
import cn.edu.bit.linc.dao.IComponent;
import cn.edu.bit.linc.dao.IMerchant;
import cn.edu.bit.linc.dao.IProduct;
import cn.edu.bit.linc.dao.ITag;
import cn.edu.bit.linc.dao.IUserOperation;
import cn.edu.bit.linc.pojo.Attribute;
import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Merchant;
import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.pojo.RequestComponent;
import cn.edu.bit.linc.pojo.Tag;
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
	
	@ResponseBody
	@RequestMapping(value="/hktest")
	public List<ProductAndMerchant> test(HttpServletRequest request) {
		
		List<Product> products = new ArrayList<Product>();
		SqlSession sqlsession = DBUtil.openSession();
		IProduct iproduct = sqlsession.getMapper(IProduct.class);
		IMerchant imerchant = sqlsession.getMapper(IMerchant.class); 
		List<ProductAndMerchant> resList = new ArrayList();
		
		products = iproduct.getProducts();
		sqlsession.close();
		
		List<Product> random = new ArrayList<Product>();
		int[] ran = RandomUtil.RandomArray(products.size());
		for(int i=0; i<ran.length && i<3; i++){
			random.add(products.get(ran[i]));
		}
		
		for(Product p : random) {
			ProductAndMerchant pam = new ProductAndMerchant();
			Merchant m = imerchant.getMerchantByProductID(p.getProductID());
			pam.setProduct(p);
			pam.setMerchant(m);
			resList.add(pam);
		}
		sqlsession.close();
		
		return resList;
	}
	
	@RequestMapping("/download")
	public String download(){
		return "download";
	}
	
	@RequestMapping("/")
	public String add(Model model){
		//System.out.println("hello!");
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
		System.out.println(product);
		List<Component> components = icomponent.getComponentByProductID(product.getProductID());
		
		for(Component com : components) {
			List<Attribute> attributes = iattribute.getAttributeFromProductByComponentID(com.getComponentID());
			com.setAttributes(attributes);
			
			//System.out.println(attributes);
		}
		
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
			model.addAttribute("message", "添加成功" + product.getId());
		}
		else
			model.addAttribute("message", "添加失败");
		
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
			components = icomponent.getComponentByProductID(Integer.parseInt(product_id));
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
		else return "登录失败";
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
		User nusr =  new User();
		nusr.setUsername(username);
		nusr.setPassword(password);
		nusr.setEmail(email);
		iUser.addUser(nusr);
		session.commit();
		session.close();
	
		return "success";
	}
	
	public class ProductAndMerchant {
		
		private Product product;
		private Merchant merchant;
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public Merchant getMerchant() {
			return merchant;
		}
		public void setMerchant(Merchant merchant) {
			this.merchant = merchant;
		}
		
		@Override
		public String toString() {
			return "ProductAndMerchant [product=" + product + ", merchant="
					+ merchant + "]";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/recommend")
	public List<ProductAndMerchant> getRecommend(@RequestBody RequestComponent requestComponent, HttpSession session){
		System.out.println("session id: " + session.getId());
		Component[] components = requestComponent.getLike();
		Component[] dislikeComponents = requestComponent.getDislike();
		//System.out.println(components.length);
		//System.out.println(components[0]);
		//System.out.println(dislikeComponents.length);
		
		List<Product> products = new ArrayList<Product>();
		SqlSession sqlsession = DBUtil.openSession();
		IProduct iproduct = sqlsession.getMapper(IProduct.class);
		IMerchant imerchant = sqlsession.getMapper(IMerchant.class); 
		List<ProductAndMerchant> resList = new ArrayList();
		
		if(components.length == 0){
			
			products = iproduct.getProducts();
			sqlsession.close();
			
			List<Product> random = new ArrayList<Product>();
			int[] ran = RandomUtil.RandomArray(products.size());
			for(int i=0; i<ran.length && i<3; i++){
				random.add(products.get(ran[i]));
			}
			
			for(Product p : random) {
				Merchant m = imerchant.getMerchantByProductID(p.getProductID());
				ProductAndMerchant pam = new ProductAndMerchant();
				pam.setProduct(p);
				pam.setMerchant(m);
				resList.add(pam);
			}
			
			return resList;
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
		
		for(Product p : products) {
			Merchant m = imerchant.getMerchantByProductID(p.getProductID());
			ProductAndMerchant pam = new ProductAndMerchant();
			pam.setProduct(p);
			pam.setMerchant(m);
			resList.add(pam);
		}
		
		sqlsession.close();
		System.out.println(resList);
		
		return resList;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/merchant")
	public List<Merchant> getMerchants(HttpServletRequest request){
		SqlSession session = DBUtil.openSession();
		IMerchant imerchant = session.getMapper(IMerchant.class);
		IProduct iproduct = session.getMapper(IProduct.class);
		List<Merchant> merchants = null;
		
		String tag = request.getParameter("tag");
		String priceLow = request.getParameter("priceLow");
		String priceHigh = request.getParameter("priceHigh");
		String positionX = request.getParameter("positionX");
		String positionY = request.getParameter("positionY");
		
		if(tag != null && !tag.equals("") && priceLow != null && !priceLow.equals("") && priceHigh != null && !priceHigh.equals("")) {
			merchants = imerchant.getMerchantByTagAndPrice("%"+tag+"%", Integer.parseInt(priceLow), Integer.parseInt(priceHigh));
		}
		else if(tag != null && !tag.equals("")) {
			merchants = imerchant.getMerchantByTag("%"+tag+"%");
			System.out.println(tag);
		}
		else if(priceLow != null && !priceLow.equals("") && priceHigh != null && !priceHigh.equals("")) {
			merchants = imerchant.getMerchantByPrice(Integer.parseInt(priceLow), Integer.parseInt(priceHigh));
		}
		else {
			merchants = imerchant.getMerchant();
			//System.out.println("Parameter Error!!!");
		}
		
		if(positionX != null && !positionX.equals("") && positionY != null && !positionY.equals("")) {
			Iterator<Merchant> it = merchants.iterator();
			while(it.hasNext()) {
				Merchant m = it.next();
				
				//delete merchant by position
				double dis = getDistance(Double.parseDouble(positionX), Double.parseDouble(positionY), m.getPositionX(), m.getPositionY());
				//if(dis > 5.0)
					//it.remove();
				
				m.setPositionX(dis);
			}
			List<Merchant> resList = new ArrayList();
			for(int i = 0; i < 3 && i < merchants.size(); ++i) {
				Merchant minMerchant = merchants.get(0);
				double min = merchants.get(0).getPositionX();
				
				for(Merchant m: merchants) {
					if(m.getPositionX() < min && m.getPositionY() > 0) {
						min = m.getPositionX();
						minMerchant = m;
					}
				}
				
				minMerchant.setPositionY(-1.0);
				resList.add(minMerchant);
			}
			 merchants = resList;
		}
		
		for(Merchant merchant : merchants) {
			
			merchant.setProducts(iproduct.getProductByMerchantID(merchant.getMerchantID()));
			System.out.println(merchant);
		}
		
		session.close();
		return merchants;
	}
	
	@ResponseBody
	@RequestMapping(value="/read")
	public void read(HttpServletRequest request){
		//Do not call again
		
		try {
			SqlSession session = DBUtil.openSession();
			IMerchant imerchant = session.getMapper(IMerchant.class);
			IProduct iproduct = session.getMapper(IProduct.class);
			ITag itag = session.getMapper(ITag.class);
			IComponent icomponent = session.getMapper(IComponent.class);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse("C:\\Users\\HK\\Desktop\\DataSet1.xml"); // 获取到xml文件
			
			// 下面开始读取
			Element root = doc.getDocumentElement(); // 获取根元素
			NodeList restaurants = root.getElementsByTagName("Restaurant");
			System.out.println(restaurants.getLength());
			//Vector<E> students_Vector = new Vector();
			for (int i = 0; i < restaurants.getLength(); i++) {
				
				Element restaurant = (Element) restaurants.item(i);
				
				String restName = restaurant.getElementsByTagName("Name").item(0).getFirstChild().getNodeValue();
				String type = restaurant.getElementsByTagName("Type").item(0).getFirstChild().getNodeValue();
				String address = restaurant.getElementsByTagName("Location").item(0).getFirstChild().getNodeValue();
				String positionString = restaurant.getElementsByTagName("Coordinate").item(0).getFirstChild().getNodeValue();
				String price = restaurant.getElementsByTagName("Price").item(0).getFirstChild().getNodeValue();
				String rating = restaurant.getElementsByTagName("Rating").item(0).getFirstChild().getNodeValue();
				String picture = restaurant.getElementsByTagName("Images").item(0).getFirstChild().getNodeValue();
				System.out.println(" ");
				
				Merchant merchant = new Merchant();
				merchant.setAddress(address);
				merchant.setMerchantName(restName);
				merchant.setPositionX(Double.parseDouble(positionString.split(",")[0]));
				merchant.setPositionY(Double.parseDouble(positionString.split(",")[1]));
				merchant.setPrice(Double.parseDouble(price));
				merchant.setRating(Double.parseDouble(rating));
				merchant.setPicture(picture+".jpg");
				
				imerchant.addMerchant(merchant);
				//System.out.println(merchant.getMerchantID());
				
				Tag tag = new Tag();
				tag.setMerchantID(merchant.getMerchantID());
				tag.setTagName(type);
				
				itag.addTag(tag);
				
				NodeList dishes = restaurant.getElementsByTagName("Dish");
				//System.out.println(dishes.getLength());
				
				for(int j = 0; j < dishes.getLength(); ++j) {
					Element dish = (Element) dishes.item(j);
					String dishName = dish.getElementsByTagName("Name").item(0).getFirstChild().getNodeValue();
					String componentString = dish.getElementsByTagName("Component").item(0).getFirstChild().getNodeValue();
					
					Product product = new Product();
					product.setProductName(dishName);
					product.setMerchantID(merchant.getMerchantID());
					
					iproduct.addProduct(product);
					System.out.println(product.getProductID());
					
					String[] components = componentString.split(",");
					for(int k = 0; k < components.length; ++k) {
						//System.out.println(components[k]);
						
						Component component = icomponent.getComponentByComponentName(components[k]);
						if(null == component) {
							component = new Component();
							component.setComponentName(components[k]);
							
							int componentID = icomponent.addComponent(component);
							component.setComponentID(componentID);
						}
						
						iproduct.addProductDetail(product.getProductID(), component.getComponentID());
						
					}
				}
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)
    /** 
     * 转化为弧度(rad) 
     * */  
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}
	/**
	 * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
	 * @param lon1 第一点的精度
	 * @param lat1 第一点的纬度
	 * @param lon2 第二点的精度
	 * @param lat3 第二点的纬度
	 * @return 返回的距离，单位km
	 * */
	public static double getDistance(double lng_a,double lat_a,double lng_b, double lat_b)
	{
		double pk = 180 / Math.PI;
		double a1 = lat_a / pk;
		double a2 = lng_a / pk; 
		double b1 = lat_b / pk; 
		double b2 = lng_b / pk; 
		double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
		double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
		double t3 = Math.sin(a1) * Math.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);
		return 6366 * tt;
	}
	
}
