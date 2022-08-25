package com.jon.gameWorkshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jon.gameWorkshop.models.Mechanic;
import com.jon.gameWorkshop.repositories.MechanicRepo;

@Service
public class MechanicService {
	private final MechanicRepo mRepo;
	
	public MechanicService(MechanicRepo mRepo) {
		this.mRepo = mRepo;
	}
	
	public List<Mechanic> getMechanicsFromGame(Long gameId){
		return mRepo.findByGameId(gameId);
	}
	
	public Mechanic create(Mechanic mechanic) {
		return mRepo.save(mechanic);
	}
	
	public void destroy(Mechanic mechanic) {
		mRepo.delete(mechanic);
	}
	
	public Mechanic getById(Long id) {
		return mRepo.findById(id).orElse(null);
	}
}
