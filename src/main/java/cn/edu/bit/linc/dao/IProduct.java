package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Product;

public interface IProduct {

	public List<Product> selectProducts();
	
	public List<Product> selectProductById(String id);
	
	public int insertProduct(Product p);
	
}
