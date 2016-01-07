package cn.edu.bit.linc.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bit.linc.dao.IUserOperation;
import cn.edu.bit.linc.pojo.User;
import cn.edu.bit.linc.util.DBUtil;

@Controller
public class HelloWorld {

	@RequestMapping("/hello")
	public String sayHello(){
		System.out.println("hello!");
		return "test"; 
	}
	
	@ResponseBody
	@RequestMapping("/testMybatis")    
	public List<User> testMybatis(){ 
		SqlSession session = DBUtil.openSession();
//		User user = session.selectOne("cn.edu.bit.linc.dao.IUserOperation.selectUserByUsername", "user01");
//		List<User> users = session.selectList("cn.edu.bit.linc.dao.IUserOperation.selectUser");
		
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = userOperation.selectUserByUsername("test");
		List<User> users = userOperation.selectUser();
		
		System.out.println(users.size());
		System.out.println(user);
		return users;
	}
}
