package com.jon.gameWorkshop.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jon.gameWorkshop.models.Game;
import com.jon.gameWorkshop.models.Mechanic;
import com.jon.gameWorkshop.models.User;
import com.jon.gameWorkshop.services.GameService;
import com.jon.gameWorkshop.services.MechanicService;
import com.jon.gameWorkshop.services.UserService;

@Controller
@RequestMapping("/games")
public class GameController {

	@Autowired
	private UserService users;
	@Autowired
	private GameService games;
	@Autowired
	private MechanicService mechanics;
	
	@GetMapping("/new")
	public String addGame(@ModelAttribute("game") Game game,
			Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = users.findById((Long) session.getAttribute("userId"));
		model.addAttribute("user", user);
		return "newGame.jsp";
	}
	
	@PostMapping("")
	public String createGame(@Valid @ModelAttribute("game") Game game,
			BindingResult result, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "newGame.jsp";
		}
		List<Game> allGames = games.getAll();
		for (Game g:allGames) {
			if (g.getName().equals(game.getName())) {
				result.rejectValue("name", "Not Unique", "Game Name already taken!");
				return "newGame.jsp";
			}
		}
		games.create(game);
		return "redirect:/home";
	}
	
	@GetMapping("{id}/edit")
	public String editGame(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Game game= games.getById(id);
		model.addAttribute("game", game);
		return "editGame.jsp";

	}
	
	@GetMapping("/{id}")
	public String showGame(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Game game = games.getById(id);
		List<Mechanic> mech = mechanics.getMechanicsFromGame(id);
		model.addAttribute("game", game);
		model.addAttribute("mechanics", mech);
		model.addAttribute("newMechanic", new Mechanic());
		model.addAttribute("user", users.findById((Long) session.getAttribute("userId")));
		return "viewGame.jsp";
	}
	
	@PutMapping("/{id}")
	public String updateTeam(@Valid @ModelAttribute("game") Game game, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "editGame.jsp";
		}

		System.out.println("Game to be saved to DB: " + game.getId());

		games.update(game);

		return "redirect:/home";
	}
	
	@DeleteMapping("/{id}")
	public String destroyTeam(@PathVariable("id") Long id) {
		games.destroy(games.getById(id));
		return "redirect:/home";
	}
	
	@PostMapping("/{id}/mechanics")
	public String addMechanic(@Valid @ModelAttribute("mechanic") Mechanic mechanic,
			BindingResult result, Model model, HttpSession session,
			@PathVariable("id") Long id) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Game game = games.getById(id);
		if(result.hasErrors()) {
			List<Mechanic> mech = mechanics.getMechanicsFromGame(id);
			model.addAttribute("game", game);
			model.addAttribute("mechanics", mech);
			model.addAttribute("newMechanic", new Mechanic());
			model.addAttribute("user", users.findById((Long) session.getAttribute("userId")));
			return "viewGame.jsp";
		}
		Mechanic m = new Mechanic();
		m.setName(mechanic.getName());
		m.setUser(users.findById((Long) session.getAttribute("userId")));
		m.setGame(game);
		mechanics.create(m);
		games.addMechanic(game, m);
		return "redirect:/games/" + id;
		
	}
	
	@DeleteMapping("/{id}/mechanics/{mechId}")
	public String destroyMechanic(@PathVariable("mechId") Long mechId,
			@PathVariable("id") Long id) {
		mechanics.destroy(mechanics.getById(mechId));
		return "redirect:/games/" + id;
	}
	
	
}
