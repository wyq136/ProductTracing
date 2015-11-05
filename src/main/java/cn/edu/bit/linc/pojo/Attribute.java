package cn.edu.bit.linc.pojo;

public class Attribute {

	private int attributeID;
	private int referenceID;
	private String refenenceTable;
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
	public String getRefenenceTable() {
		return refenenceTable;
	}
	public void setRefenenceTable(String refenenceTable) {
		this.refenenceTable = refenenceTable;
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
				+ referenceID + ", refenenceTable=" + refenenceTable
				+ ", attributeName=" + attributeName + ", attributeValue="
				+ attributeValue + "]";
	}
	
	
}
