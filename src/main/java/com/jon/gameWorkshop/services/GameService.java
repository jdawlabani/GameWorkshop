package com.jon.gameWorkshop.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jon.gameWorkshop.models.Game;
import com.jon.gameWorkshop.models.Mechanic;
import com.jon.gameWorkshop.repositories.GameRepo;

@Service
public class GameService {
	private final GameRepo gRepo;
	
	public GameService(GameRepo gRepo) {
		this.gRepo = gRepo;
	}
	
	public List<Game> getAll(){
		List<Game> g = gRepo.findAll();
		//sorts alphabetically by name of game
		g.sort(Comparator.comparing(Game::getName));
		return g;	
	}
	
	public Game getById(Long id) {
		return gRepo.findById(id).orElse(null);
	}
	
	public boolean addMechanic(Game game, Mechanic mechanic) {
		return game.getMechanics().add(mechanic);
	}
	
	public Game create(Game g) {
		return gRepo.save(g);
	}
	
	public Game update(Game g) {
		return gRepo.save(g);
	}
	
	public void destroy(Game g) {
		gRepo.delete(g);
	}
}


