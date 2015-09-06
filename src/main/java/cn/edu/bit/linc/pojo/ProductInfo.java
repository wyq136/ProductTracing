package cn.edu.bit.linc.pojo;

public class ProductInfo {

	private int product_id;
	private String component_id;
	private String catalog_id;
	private String component_name;
	private String catalog_name;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getComponent_id() {
		return component_id;
	}
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}
	public String getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}
	public String getComponent_name() {
		return component_name;
	}
	public void setComponent_name(String component_name) {
		this.component_name = component_name;
	}
	public String getCatalog_name() {
		return catalog_name;
	}
	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	
}
