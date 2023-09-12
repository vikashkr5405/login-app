package com.briztech.loginapp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import jakarta.servlet.http.HttpSession;

@Service
public class PersonServiceImpl implements PersonService
{
	@Autowired
	PersonRepository prepo;                                                          

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Person saveUsers(Person person) 
	{
		String password=passwordEncoder.encode(person.getPassword());
		person.setPassword(password);
		person.setRole("ROLE_USER");
	
		Person p= prepo.save(person);

		return p;
	}

	@Override
	public void removeSessionMessage()
	{
	  HttpSession session =	((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
	  session.removeAttribute("msg");
	}

	
	
	
}


