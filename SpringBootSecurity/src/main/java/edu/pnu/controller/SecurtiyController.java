package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurtiyController {
	
	@GetMapping({"/","/index"})
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";
	}
	@GetMapping({"/member"})
	public String forMember() {
		System.out.println("Member 요청입니다.");
		return "index";
	}
	@GetMapping({"/manager"})
	public String forManger() {
		System.out.println("manager 요청입니다.");
		return "index";
	}
	@GetMapping({"/admin"})
	public String forAdmin() {
		System.out.println("Admin 요청입니다.");
		return "index";
	}

}
