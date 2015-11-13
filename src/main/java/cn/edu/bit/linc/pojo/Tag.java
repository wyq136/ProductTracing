package cn.edu.bit.linc.pojo;

public class Tag {
	
	private int tagID;
	private String tagName;
	private int merchantID;
	
	public int getTagID() {
		return tagID;
	}
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(int merchantID) {
		this.merchantID = merchantID;
	}
	
	@Override
	public String toString() {
		return "Tag [tagID=" + tagID + ", tagName=" + tagName
				+ ", merchantID=" + merchantID + "]";
	}
	
}