package com.jon.gameWorkshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jon.gameWorkshop.models.Mechanic;

@Repository
public interface MechanicRepo extends CrudRepository<Mechanic,Long>{
	List<Mechanic> findByGameId(Long id);
	void deleteById(Long id);
	Optional<Mechanic> findById(Long id);
	
	
}
