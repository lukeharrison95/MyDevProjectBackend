package com.DM.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DM.demo.Util.GameNotFoundException;
import com.DM.demo.persistence.entities.Game;
import com.DM.demo.persistence.repo.GameRepo;

@Service
public class GameService {
	
	@Autowired
	private GameRepo repo;
	
	public Game createGame(Game game) {
		return this.repo.saveAndFlush(game);
	}
	
	public Game findGameById(Long gameId) {
		return this.repo.findById(gameId).orElseThrow(() ->  new GameNotFoundException());
	}
	
	public List<Game> readGame(){
		return this.repo.findAll();
	}
	
	public Game updateGame(Game game) {
		return this.repo.save(game);
	}
	

}

