package cn.edu.bit.linc.pojo;

public class Barcode {
	
	private int barcodeID;
	private int productID;
	private String barcode;
	
	public int getBarcodeID() {
		return barcodeID;
	}
	public void setBarcodeID(int barcodeID) {
		this.barcodeID = barcodeID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	@Override
	public String toString() {
		return "Barcode [barcodeID=" + barcodeID + ", productID=" + productID
				+ ", barcode=" + barcode + "]";
	}

	
}