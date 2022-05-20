package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
	@RequestMapping(value="/search" ,method=RequestMethod.POST)
	public ModelAndView search(
			ModelAndView mv,
			@RequestParam("searchWord") String searchWord) {
		mv.setViewName("item/showItem");
		return mv;
	}
	
	@RequestMapping("/cart/add/{id}")
	public ModelAndView addCart(
			ModelAndView mv,
			@PathVariable("id")int id) {
		
		mv.setViewName("item/showItem");
		return mv;
	}
	
}
