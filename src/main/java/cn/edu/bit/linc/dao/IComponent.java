package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;
import cn.edu.bit.linc.pojo.ProductDetail;

public interface IComponent {

	public List<Component> getComponents();
	
	public List<Component> getComponentByProductID(int productID);
	
	public List<Component> getComponentByBarcodeID(int barcodeID);
	
	public Component getComponentByComponentName(String componentName);
	
	public int addComponent(Component component);
	
	public List<ProductDetail> getProductDetail();

}
