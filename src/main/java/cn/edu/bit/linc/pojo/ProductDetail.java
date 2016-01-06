package cn.edu.bit.linc.pojo;

public class ProductDetail {

	private int productDetailID;
	private int productID;
	private int componentID;
	
	public int getProductDetailID() {
		return productDetailID;
	}
	public void setProductDetailID(int productDetailID) {
		this.productDetailID = productDetailID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getComponentID() {
		return componentID;
	}
	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}
	
	@Override
	public String toString() {
		return "ProductDetail [productDetailID=" + productDetailID
				+ ", productID=" + productID + ", componentID=" + componentID
				+ "]";
	}
	
}
