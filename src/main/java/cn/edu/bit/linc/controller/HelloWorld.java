package cn.edu.bit.linc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

	@RequestMapping("/hello")
	public String sayHello(){
		System.out.println("hello!");
		return "success";
	}
}
