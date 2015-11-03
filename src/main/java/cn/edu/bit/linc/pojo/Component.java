package cn.edu.bit.linc.pojo;

import java.util.List;

public class Component {

	private int componentID;
	private String componentName;
	private List<Attribute> attributes;
	
	public int getComponentID() {
		return componentID;
	}
	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String toString() {
		return "Component [componentID=" + componentID + ", componentName="
				+ componentName + ", attributes=" + attributes + "]";
	}
	
}
