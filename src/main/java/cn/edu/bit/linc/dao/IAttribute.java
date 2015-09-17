package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Attribute;

public interface IAttribute {

	public List<Attribute> selectAttributeByComponentId(int id);
	
	//public int insertComponent(Component component);
}
