package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.ItemRepository;
import com.example.demo.Repository.OrderRepository;

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

	@RequestMapping("/order")
	public ModelAndView order(ModelAndView mv) {
		
		mv.setViewName("order/orderCheck");
		return mv;
	}
}
