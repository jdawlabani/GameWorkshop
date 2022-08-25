package com.jon.gameWorkshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jon.gameWorkshop.models.Game;

@Repository
public interface GameRepo extends CrudRepository<Game,Long>{
	List<Game> findAll();
	Optional<Game> findById(Long id);
}
