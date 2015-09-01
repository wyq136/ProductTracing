package cn.edu.bit.linc.pojo;

public class ProductInfo {

	private int product_id;
	private String component_name;
	private String catalog_name;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	
	@Override
	public String toString() {
		return "ProductInfo [product_id=" + product_id + ", component_name=" + component_name + ", catalog_name="
				+ catalog_name + "]";
	}
}
