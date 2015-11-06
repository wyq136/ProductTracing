package cn.edu.bit.linc.pojo;

public class Order {
	
	private int orderID;
	
	private int barcodeID;
	
	private int userID;
	
	private String remarks;
	
	private String createTime;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getBarcodeID() {
		return barcodeID;
	}

	public void setBarcodeID(int barcodeID) {
		this.barcodeID = barcodeID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", barcodeID=" + barcodeID
				+ ", userID=" + userID + ", remarks=" + remarks
				+ ", createTime=" + createTime + "]";
	}

}
