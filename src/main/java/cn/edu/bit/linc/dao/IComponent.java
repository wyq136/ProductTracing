package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;

public interface IComponent {

	public List<Component> getComponents();
	
	public List<Component> getComponentByProductId(int productID);
	
	public List<Component> getComponentByComponentName(String componentName);
	
	public int addComponent(Component component);
}
