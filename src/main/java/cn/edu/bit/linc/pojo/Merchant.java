package cn.edu.bit.linc.pojo;

import java.util.List;

public class Merchant {

	private int merchantID;
	private String merchantName;
	private String description;
	private String picture;
	private String address;
	private double positionX;
	private double positionY;
	private double price;
	private double rating;
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(int merchantID) {
		this.merchantID = merchantID;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Merchant [merchantID=" + merchantID + ", merchantName="
				+ merchantName + ", description=" + description + ", picture="
				+ picture + ", address=" + address + ", positionX=" + positionX
				+ ", positionY=" + positionY + ", price=" + price + ", rating="
				+ rating + ", products=" + products + "]";
	}
	
	
	
	
}
