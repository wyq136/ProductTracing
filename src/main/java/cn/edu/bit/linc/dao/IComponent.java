package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Component;
import cn.edu.bit.linc.pojo.Product;

public interface IComponent {

	public List<Component> selectComponents();
	
	public List<Component> selectComponentByProductId(String product_id);
	
	public int insertComponent(Component component);
}
