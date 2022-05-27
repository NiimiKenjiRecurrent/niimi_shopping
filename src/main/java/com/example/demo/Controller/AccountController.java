package com.example.demo.Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.OrderHis;
import com.example.demo.OrderHisDisp;
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
			MessageDigest sha512 = null;
			try {
				sha512 = MessageDigest.getInstance("SHA-512");
			} catch (NoSuchAlgorithmException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			byte[] sha512_result = sha512.digest(password.getBytes());
			session.setAttribute("password", String.format("%040x", new BigInteger(1, sha512_result)));
			if(accountList.getEmail().equals(email)&& accountList.getPassword().equals(String.format("%040x", new BigInteger(1, sha512_result)))) {
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
		String email="";
		String tell="";
		String name="";
		Account account =new Account("","",email, tell, name,"","");
		mv.addObject("accountInfo", account);
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
		
		
		MessageDigest sha512 = null;
		try {
			sha512 = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		byte[] sha512_result = sha512.digest(password.getBytes());
		
		
		
		Account account =new Account(userName, address, email, tell, name, String.format("%040x", new BigInteger(1, sha512_result)),addressNum);
		if(!checkPassword(password)) {
			mv.addObject("accountInfo", account);
			mv.setViewName("account/signUp");
			return mv;
		}else if(!password.equals(passwordCon)) {
			mv.addObject("accountInfo", account);
			mv.setViewName("account/signUp");
			return mv;
		}
		List<Account> userList = accountRepository.findAllByEmail(email);
		mv.addObject("users", userList);
		
		if(userList.size() != 0) {
			mv.addObject("message", "登録済のユーザーです");
			mv.setViewName("account/signup");
		}
		

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
			@RequestParam("userName")String userName) {
		
		// セッションからUseridを取得して、更新
		
//		accountRepository.deleteByEmail(email);
//		accountRepository.flush();
		
		Account account=new Account((int)session.getAttribute("id"),userName,address,email,tell,name,(String)session.getAttribute("password"),addressNum);
		accountRepository.saveAndFlush(account);
		
		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		mv.addObject("account",accountInfo);

		mv.setViewName("account/userPage");
		return mv;
	}
	
	
	@RequestMapping("/history")
	public ModelAndView history(ModelAndView mv) {
		Map<Integer, OrderHisDisp> displist = new HashMap<>();
		
		List<OrderHis> datas = orderDetailRepository.niimi((int)session.getAttribute("id"));

		for(OrderHis his: datas) {
			OrderHisDisp existed = displist.get(his.getOrderId());
			
			if(existed != null) {
				existed.getOrderhiss().add(his);
			}
			else {
				OrderHisDisp disp = new OrderHisDisp();
				disp.setOrderId(his.getOrderId());
				disp.setOrderedDate(his.getOrderedDate());
				disp.setTotalPrice(his.getTotalPrice());
				disp.getOrderhiss().add(his);
				
				displist.put(his.getOrderId(), disp);
			}
			
		}

		mv.addObject("items", displist);
		mv.setViewName("/account/history");
		return mv;
	}
	
	public boolean checkPassword(String pass) {
		Pattern p = Pattern.compile("^$|^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])[!-~]*");
		Matcher m = p.matcher(pass);
		return m.matches();
	}
	

}
