package cn.edu.bit.linc.pojo;

public class Barcode {
	
	private int productID;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "Barcode [productID=" + productID + "]";
	}
	
}