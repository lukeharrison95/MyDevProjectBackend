package com.DM.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DM.demo.persistence.entities.Player;

@Repository
public interface PlayerRepo  extends JpaRepository<Player, Long>{
	
	
	
	

}
