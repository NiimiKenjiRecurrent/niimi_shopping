package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Item;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@RequestMapping(value="/search" ,method=RequestMethod.POST)
	public ModelAndView search(
			ModelAndView mv,
			@RequestParam("searchWord") String searchWord) {
		List<Item> itemList=itemRepository.findAllByNameContaining(searchWord);
		mv.addObject("items",itemList);
		mv.setViewName("item/showItem");
		return mv;
	}
	
	
	@RequestMapping("/showItem")
	public ModelAndView showItem(ModelAndView mv) {
		List<Item> itemList=itemRepository.findALLByOrderByIdAsc();
		mv.addObject("items",itemList);

		mv.setViewName("item/showItem");
		return mv;
	}
	
	
	
	
	@RequestMapping("/item/detail/{id}")
	public ModelAndView detail(ModelAndView mv,@PathVariable("id")int id) {
		Item item = itemRepository.findById(id).get();
		mv.addObject("items",item);
		mv.setViewName("/item/itemOnry");
		return mv;
	}
	

}
