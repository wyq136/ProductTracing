package cn.edu.bit.linc.pojo;

public class Product {

	private int id;
	private int catalog_id;
	private String product_name;
	private String description;
	private String picture;
	private String shop;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}public int getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(int catalog_id) {
		this.catalog_id = catalog_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	
	@Override
	public String toString() {
		return "Product [id =" + id + "product_name=" + product_name + ", catalog_id=" + catalog_id + ", description=" + description + ", picture=" + picture + ", shop=" + shop + "]";
	}
}
