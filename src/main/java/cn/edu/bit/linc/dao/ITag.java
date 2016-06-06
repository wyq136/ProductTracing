package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.Tag;

public interface ITag {

	public List<Tag> getTag();
	
	public List<Tag> getTagByMerchantID(int merchantID);
	
	public Tag getTagByTagName(String tagName);
	
	public int addTag(Tag tag);
	
}
