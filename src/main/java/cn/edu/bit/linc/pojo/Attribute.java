package cn.edu.bit.linc.pojo;

public class Attribute {

	private int attributeID;
	private int referenceID;
	private String referenceTable;
	private String attributeName;
	private String attributeValue;
	
	public int getAttributeID() {
		return attributeID;
	}
	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}
	public int getReferenceID() {
		return referenceID;
	}
	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}
	public String getReferenceTable() {
		return referenceTable;
	}
	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	@Override
	public String toString() {
		return "Attribute [attributeID=" + attributeID + ", referenceID="
				+ referenceID + ", referenceTable=" + referenceTable
				+ ", attributeName=" + attributeName + ", attributeValue="
				+ attributeValue + "]";
	}
	
	
}
