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

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Administrator;
import com.example.demo.Entity.Item;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.AdministratorRepository;
import com.example.demo.Repository.ItemRepository;

@Controller
public class AdministratorController {

	@Autowired
	HttpSession session;
	@Autowired
	AdministratorRepository administratorRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	AccountRepository accountRepository;



	@RequestMapping("/admin")
	public String login() {
		
		return "/Admin/adminLogin";
	}
	
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	public ModelAndView adminlogin(
			ModelAndView mv,
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		session.invalidate();
		//未入力チェック
		if(name==null||name.length()==0) {
			mv.addObject("nullmessage","未入力");
			mv.setViewName("/admin/adminLogin");
		// ok
		}else {
			Administrator adminInfo = administratorRepository.findByName(name);
			if(adminInfo==null) {
				mv.addObject("loginmessage", "名前とパスワードが一致しませんでした");
				mv.setViewName("/Admin/adminLogin");
				return mv;
			}
			//ログイン失敗チェック
//			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			if(adminInfo.getName().equals(name)&&adminInfo.getPassword().equals(password)) {
				mv.setViewName("Admin/adminPage");
			}else {
				mv.addObject("loginmessage","一致するものが見つかりませんでした");
				mv.setViewName("/Admin/adminLogin");
			}
			session.setAttribute("adminId", adminInfo.getId());
		}
		return mv;
	}
	
	@RequestMapping("/adminItem")
	public ModelAndView showItem(ModelAndView mv) {
		
		List<Item> itemList=itemRepository.findALLByOrderByIdAsc();
		mv.addObject("items",itemList);

		mv.setViewName("Admin/adminItem");

		return mv;
	}

	@RequestMapping("/admin/delete/{id}")
	public ModelAndView deleteItem(ModelAndView mv,
			@PathVariable("id")int id) {
		itemRepository.deleteById(id);
		List<Item> itemList=itemRepository.findALLByOrderByIdAsc();
		mv.addObject("items",itemList);

		mv.setViewName("Admin/adminItem");
		return mv;
	}
	
	@RequestMapping("/admin/change/{id}")
	public ModelAndView changeItem(ModelAndView mv,
			@PathVariable("id")int id) {
		mv.addObject("itemId", id);
		Item itemInfo = itemRepository.findById(id).get();
		mv.addObject("item",itemInfo);
		mv.setViewName("/Admin/adminItemListing");
		return mv;
	}
	@RequestMapping(value="/changeItemInfo/{id}",method = RequestMethod.POST)
	public ModelAndView changeItemInfo(ModelAndView mv,
			@RequestParam("name")String name,
			@RequestParam("price")int price,
			@PathVariable("id")int id) {
		Item itemInfo = itemRepository.findById(id).get();
		Item updateItem = new Item(itemInfo.getId(),price,itemInfo.getStock(),itemInfo.getImage(),name);
		itemRepository.saveAndFlush(updateItem);
		List<Item> itemList=itemRepository.findALLByOrderByIdAsc();
		mv.addObject("items",itemList);

		mv.setViewName("Admin/adminItem");
		return mv;
	}
	@RequestMapping("/admin/add")
	public ModelAndView addItem(ModelAndView mv) {
		
		mv.setViewName("/Admin/addItem");
		return mv;
	}
	@RequestMapping(value="/admin/add",method = RequestMethod.POST)
	public ModelAndView addItem(ModelAndView mv,
			@RequestParam("name")String name,
			@RequestParam("price")int price,
			@RequestParam("img")String img) {
		Item updateItem = new Item(price,10,img,name);
		itemRepository.saveAndFlush(updateItem);
		List<Item> itemList=itemRepository.findALLByOrderByIdAsc();
		mv.addObject("items",itemList);

		mv.setViewName("Admin/adminItem");
		return mv;
	}
	
	@RequestMapping("adminShowUser")
	public ModelAndView showUser(ModelAndView mv) {
		List<Account> accountList = accountRepository.findALLByOrderByIdAsc();
		mv.addObject("accounts", accountList);
		mv.setViewName("/Admin/adminShowUser");
		return mv;
	}
	@RequestMapping("/user/delete/{id}")
	public ModelAndView deleteUser(ModelAndView mv,
			@PathVariable("id")int id) {
		accountRepository.deleteById(id);
		
		List<Account> accountList = accountRepository.findALLByOrderByIdAsc();
		mv.addObject("accounts", accountList);

		mv.setViewName("Admin/adminShowUser");
		return mv;
	}
}
