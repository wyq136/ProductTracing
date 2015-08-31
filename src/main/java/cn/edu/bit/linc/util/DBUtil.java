package cn.edu.bit.linc.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {

	private static SqlSessionFactory factory;
	
	static{
		try{
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession(){
		return factory.openSession();
	}
	
	public static void closeSession(SqlSession session){
		if(session != null)
			session.close();
	}
	
}
