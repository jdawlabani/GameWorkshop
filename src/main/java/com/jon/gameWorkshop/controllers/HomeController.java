package com.jon.gameWorkshop.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jon.gameWorkshop.models.LoginUser;
import com.jon.gameWorkshop.models.User;
import com.jon.gameWorkshop.services.GameService;
import com.jon.gameWorkshop.services.UserService;



@Controller
public class HomeController {

	@Autowired
	private UserService users;
	@Autowired
	private GameService games;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
		
		
		User user = users.register(newUser, result);

		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		session.setAttribute("userId", user.getId());

		return "redirect:/home";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = users.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("userId", user.getId());

		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		model.addAttribute("games", games.getAll());
		model.addAttribute("user", users.findById((Long) session.getAttribute("userId")));
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
