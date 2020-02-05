package com.DM.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DM.demo.persistence.entities.Game;

public interface GameRepo extends JpaRepository<Game, Long>{

}
