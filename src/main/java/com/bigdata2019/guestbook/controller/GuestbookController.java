package com.bigdata2019.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigdata2019.guestbook.repository.GuestbookRepository;
import com.bigdata2019.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform() {
		return "/WEB-INF/views/deleteform.jsp";
	}

	@RequestMapping("/delete")
	public String delete(
		@RequestParam(value="no", required=true, defaultValue="0") Long no,
		@RequestParam(value="password", required=true, defaultValue="") String password) {
		guestbookRepository.delete(no, password);
		return "redirect:/";
	}
}
