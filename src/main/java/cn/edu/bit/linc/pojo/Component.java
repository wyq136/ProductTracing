package cn.edu.bit.linc.pojo;

import java.util.List;

public class Component {

	private int id;
	private int product_id;
	private String component_name;
	private String description;
	private String picture;
	private List<Attribute> attributes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
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
	/*public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}*/
	
	@Override
	public String toString() {
		return "Component [id=" + id + ", product_id=" + product_id + ", component_name=" + component_name
				+ ", description=" + description + ", picture=" + picture + "]";
	}
	
}
