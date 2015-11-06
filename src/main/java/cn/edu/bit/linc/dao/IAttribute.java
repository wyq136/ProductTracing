package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Attribute;

public interface IAttribute {

	public List<Attribute> getAttributeFromProductByComponentID(int componentID);
	
	public List<Attribute> getAttributeFromBarcodeByBarcodeID(int componentID);
	
	//public int insertComponent(Component component);
}
