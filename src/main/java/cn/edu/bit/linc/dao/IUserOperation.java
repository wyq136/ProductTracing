package cn.edu.bit.linc.dao;

import java.util.List;

import cn.edu.bit.linc.pojo.User;

public interface IUserOperation {

	public User selectUserByUsername(String username);
	
	public List<User> selectUser();
}
