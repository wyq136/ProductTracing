package cn.edu.bit.linc.pojo;

public class Component {

	private int id;
	private int product_id;
	private String component_name;
	private String description;
	private String picture;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
}
