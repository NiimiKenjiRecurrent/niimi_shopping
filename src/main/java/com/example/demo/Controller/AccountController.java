package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AccountController {
	@Autowired
	HttpSession session;
	
//	@Autowired
//	AccountRepository accountRepository;

	
	@RequestMapping("/")
	public String login() {
		return "account/index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(
			ModelAndView mv,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		//未入力チェック
		if(email==null||email.length()==0) {
			mv.addObject("message","未入力");
			mv.setViewName("account/index");
		}else {
			// ok
			session.setAttribute("name", email);
			mv.setViewName("item/showItem");
		}
		return mv;
	}
	
	@RequestMapping("signup")
	public ModelAndView signup(ModelAndView mv) {
		
		mv.setViewName("account/signUp");
		return mv;
	}
	
	@RequestMapping(value="signup",method = RequestMethod.POST)
	public ModelAndView signup(ModelAndView mv,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("tell") int tell,
			@RequestParam("addressNumFront") int addressNumFront,
			@RequestParam("addressNumBack") int addressNumBack,
			@RequestParam("prefectures") String prefectures,
			@RequestParam("town")String town,
			@RequestParam("addrNum")String addrNum,
			@RequestParam("apart") String apart,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("passwordCon") String passwordCon) {
		
		
		
		mv.setViewName("account/index");
		return mv;
	}
}
