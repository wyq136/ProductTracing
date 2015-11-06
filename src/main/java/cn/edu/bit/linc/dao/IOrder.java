package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Order;

public interface IOrder {
	
	public List<Order> getOrderByBarcodeID(int barcodeID);
	
	public List<Order> getOrderByUserID(int userID);

}
