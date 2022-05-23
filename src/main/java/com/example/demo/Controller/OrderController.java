package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Pay;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.ItemRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.PayRepository;

@Controller
public class OrderController {
	@Autowired
	HttpSession session;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	PayRepository payRepository;

	@RequestMapping("/order")
	public ModelAndView order(ModelAndView mv) {
		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		mv.addObject("account",accountInfo);
		List<Pay> payList = payRepository.findByUserId((int)session.getAttribute("id"));
		Cart cart = (Cart)session.getAttribute("cart");

		mv.addObject("items", cart.getItems());

		mv.addObject("pays",payList);
		mv.setViewName("order/orderCheck");
		return mv;
	}
	
	@RequestMapping("/order/check")
	public ModelAndView check(ModelAndView mv) {
		
		mv.setViewName("order/orderComplete");
		return mv;
	}
	@RequestMapping("/addAddress")
	public ModelAndView addAddress(ModelAndView mv,
			@RequestParam("addressNumFront") String addressNumFront,
			@RequestParam("addressNumBack") String addressNumBack,
			@RequestParam("prefectures") String prefectures,
			@RequestParam("town")String town,
			@RequestParam("addrNum")String addrNum,
			@RequestParam("apart") String apart) {
		boolean flg=false;
		String address = prefectures + town + addrNum + apart;

		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		accountInfo.setAddress(address);
		mv.addObject("account",accountInfo);
		List<Pay> payList = payRepository.findByUserId((int)session.getAttribute("id"));
		Cart cart = (Cart)session.getAttribute("cart");

		mv.addObject("items", cart.getItems());
		mv.addObject("flg",flg);
		mv.addObject("pays",payList);
		mv.setViewName("order/orderCheck");

		return mv;
	}
	@RequestMapping("/addPay")
	public ModelAndView addPay(ModelAndView mv,
			@RequestParam("creNum")String creNum,
			@RequestParam("secNum")int secNum) {
		Account accountInfo=accountRepository.findByEmail((String)session.getAttribute("name"));
		mv.addObject("account",accountInfo);
		List<Pay> payList = payRepository.findByUserId((int)session.getAttribute("id"));
		payList.add(new Pay((int)session.getAttribute("id"),creNum,secNum));
		Cart cart = (Cart)session.getAttribute("cart");

		mv.addObject("items", cart.getItems());
		mv.addObject("flg","true");
		mv.addObject("pays",payList);

		mv.setViewName("order/orderCheck");
		return mv;
	}
}
