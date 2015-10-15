package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.pojo.ProductInfo;

public interface IProduct {

	public List<Product> selectProducts();

	public List<ProductInfo> getProductInfos();
	
	public List<ProductInfo> getLocalProductInfos();
	
	public List<ProductInfo> getRemoteProductInfos();
	
	public Product getProductByID(int id);
	
	public List<Product> selectProductById(String id);
	
	public List<Product> selectProductsLocal();
	
	public int insertProduct(Product p);
	
}
