package edu.pnu.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.pnu.Service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController

public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		 System.out.println("MemberController가 생성되었습니다.");
	      log.info("MemberController가 생성되었습니다.");
	      memberService = new MemberService();
	      
	}
		
}
