package cn.edu.bit.linc.pojo;

public class ProductInfo {

	private int productID;
	private String componentID;
	private String componentName;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getComponentID() {
		return componentID;
	}
	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	@Override
	public String toString() {
		return "ProductInfo [productID=" + productID + ", componentID="
				+ componentID + ", componentName=" + componentName + "]";
	}
	
}
