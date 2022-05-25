package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.OrderHis;
import com.example.demo.Entity.Account;
import com.example.demo.Entity.Item;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.ItemRepository;
import com.example.demo.Repository.OrderDetailRepository;

@Controller
public class AccountController {
	@Autowired
	HttpSession session;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@RequestMapping("/")
	public String login() {
		return "account/index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(
			ModelAndView mv,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		session.invalidate();
		//未入力チェック
		if(email==null||email.length()==0) {
			mv.addObject("nullmessage","未入力");
			mv.setViewName("account/index");
		// ok
		}else {
			Account accountList = accountRepository.findByEmail(email);
			//ログイン失敗チェック
//			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			if(accountList.getEmail().equals(email)&&accountList.getPassword().equals(password)) {
				List<Item> itemList=itemRepository.findALLByOrderByIdAsc();
				mv.addObject("items",itemList);
				mv.setViewName("item/showItem");
			}else {
				mv.addObject("loginmessage","一致するものが見つかりませんでした");
				mv.setViewName("account/index");
			}
			session.setAttribute("name", email);
			session.setAttribute("id", accountList.getId());
		}
		return mv;
	}
	
	@RequestMapping("signup")
	public ModelAndView signup(ModelAndView mv) {
		
		mv.setViewName("account/signUp");
		return mv;
	}
	
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public ModelAndView signup(ModelAndView mv,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("tell") String tell,
			@RequestParam("addressNumFront") String addressNumFront,
			@RequestParam("addressNumBack") String addressNumBack,
			@RequestParam("prefectures") String prefectures,
			@RequestParam("town")String town,
			@RequestParam("addrNum")String addrNum,
			@RequestParam("apart") String apart,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("passwordCon") String passwordCon) {
		String addressNum = addressNumFront + addressNumBack;
		String address = prefectures + town + addrNum + apart;
		
		// データベースに登録
		Account account =new Account(userName, address, email, tell, name, password,addressNum);
		accountRepository.saveAndFlush(account);

		mv.setViewName("account/index");
		return mv;
	}
	@RequestMapping("/userPage")
	public ModelAndView openUserPage(ModelAndView mv) {
		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		mv.addObject("account",accountInfo);
		mv.setViewName("/account/userPage");
		return mv;
	}
	
	
	@RequestMapping("/changeMyPage")
	public ModelAndView changeMyPage(ModelAndView mv) {
		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		mv.addObject("account",accountInfo);
		mv.setViewName("/account/userChange");
		return mv;
	}
	
	@RequestMapping(value="/changeMyPage" ,method = RequestMethod.POST)
	public ModelAndView changeMyPage(ModelAndView mv,
			@RequestParam("name")String name,
			@RequestParam("email")String email,
			@RequestParam("tell")String tell,
			@RequestParam("addressNum")String addressNum,
			@RequestParam("address")String address,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord) {
		
		// セッションからUseridを取得して、更新
		
//		accountRepository.deleteByEmail(email);
//		accountRepository.flush();
		
		Account account=new Account((int)session.getAttribute("id"),userName,address,email,tell,name,passWord,addressNum);
		accountRepository.saveAndFlush(account);
		
		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		mv.addObject("account",accountInfo);

		mv.setViewName("account/userPage");
		return mv;
	}
	@RequestMapping("/history")
	public ModelAndView history(ModelAndView mv) {
		
		
		List<OrderHis> his = orderDetailRepository.niimi((int)session.getAttribute("id"));
		
		mv.addObject("items", his);
		mv.setViewName("/account/history");
		return mv;
	}

}
