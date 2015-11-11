package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Merchant;

public interface IMerchant {

	public List<Merchant> getMerchant();
	
	public List<Merchant> getMerchantByTag(String tag);
	
	public List<Merchant> getMerchantByPrice(double lowprice, double highprice);
	
	public List<Merchant> getMerchantByTagAndPrice(String tag, double price);

	public Merchant getMerchantByMerchantID(int merchantID);
	
	public Merchant getMerchantByProductID(int productID);
	
	public int addMerchant(Merchant merchant);
	
}
