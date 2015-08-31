package cn.edu.bit.linc.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bit.linc.dao.ICatalog;
import cn.edu.bit.linc.dao.IComponent;
import cn.edu.bit.linc.dao.IProduct;
import cn.edu.bit.linc.pojo.Catalog;
import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.util.DBUtil;

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
	
}
