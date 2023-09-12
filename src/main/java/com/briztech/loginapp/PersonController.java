package com.briztech.loginapp;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PersonController {

	@Autowired
	PersonService pserv;
	
	@Autowired
	PersonRepository prepo;
	

	@GetMapping("/") 
	public String index()
	{   
		return "index";
	}        
	@GetMapping("/home")
	public String home()
	{
		return "home"; 
	} 
	@GetMapping("/signin")
	public String login()
	{
		return "login";
	}  
	@ModelAttribute 
	public void commonUser(Principal p,Model m)
	{
		if(p!=null)
		{
		String email=p.getName(); 
		Person ps=prepo.findByEmail(email);
		m.addAttribute("person",ps);   
		}
	}
	/*@GetMapping("/profile")
	public String profie(Principal p,Model m)
	{
		String email=p.getName();
		Person ps=prepo.findByEmail(email);
		m.addAttribute("person",ps); 		
		return "profile";    
	}   */
	@GetMapping("/register")
	public String register(Model model)
	{  
		Person person= new Person();
		model.addAttribute(person);
		return "register"; 
	}      
  	     
	@RequestMapping(value="/saved" , method=RequestMethod.POST)
	public String saveUser(@ModelAttribute(value="person") Person person,HttpSession session)
	{ 
  
		Person p=pserv.saveUsers(person);  
		
		if(p!= null) 
		{
			session.setAttribute("msg", "Register successfully !!");
		} 
		else
		{
		 	session.setAttribute("msg", "something wrong !!");  
		}    
		      
		return "redirect:/register";
	}
	

}


