package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Account;
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
		mv.addObject("pays",payList);
		mv.setViewName("order/orderCheck");
		return mv;
	}
}
