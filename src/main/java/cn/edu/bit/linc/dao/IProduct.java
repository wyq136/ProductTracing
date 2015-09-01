package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.pojo.ProductInfo;

public interface IProduct {

	public List<Product> selectProducts();

	public List<ProductInfo> getProductInfos();
	
	public Product getProductByID(int id);
}
