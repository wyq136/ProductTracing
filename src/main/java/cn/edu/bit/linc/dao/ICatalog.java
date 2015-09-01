package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Catalog;

public interface ICatalog {

	public List<Catalog> selectCatalogs();
	
	public List<Catalog> selectCatalogById(String id);
	
	public List<Catalog> selectCatalogByName(String name);
	
}
