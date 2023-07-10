package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.Member;
import edu.pnu.Service.MemberService;

@Controller
public class LoginController {

	@GetMapping("/login")
	public void login() {
	}

	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}

	@GetMapping("/accessDenied")
	public void accessDenied() {
	}

	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		if (user == null) {
			return "user is Null";
		}
		return user.toString();
	}

	@Autowired
	private MemberService memberService;

	@GetMapping("/join")
	public void join() {
	}

	@PostMapping("/join")
	public String joinProc(Member member) {
		memberService.save(member);
		return "welcome";

	}

}
