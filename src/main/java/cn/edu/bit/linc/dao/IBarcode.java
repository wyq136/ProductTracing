package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Barcode;

public interface IBarcode {

	public List<Barcode> getBarcode();
	
	public List<Barcode> getBarcodeByProductId(int productID);
	
	public int addBarcode(Barcode barcode);
	
}
