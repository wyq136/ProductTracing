package cn.edu.bit.linc.pojo;

public class Catalog {

	private int 	id;
	private String 	catalog_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCatalog_name() {
		return catalog_name;
	}
	public void setcatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	
	@Override
	public String toString() {
		return "Catalog [id=" + id + ", name=" + catalog_name + "]";
	}
	
}
