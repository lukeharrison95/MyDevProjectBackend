package com.DM.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DM.demo.persistence.entities.Game;
import com.DM.demo.service.GameService;

@RestController
public class GameController {
	
	private GameService service;
	
	public GameController(GameService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createGame")
	public Game createGame(@RequestBody Game game) {
		return this.service.createGame(game);
	}
	
	@PutMapping("/updateGame")
	public Game updateGame(@RequestBody Game game) {
		return this.service.updateGame(game);
	}
	
	@GetMapping("/findGame")
	public List<Game> findAllGames(){
	return this.service.readGame();
	}
	
	@GetMapping("/findGame/{gameId}")
	public Game findGameByID(@PathVariable Long gameId){
		return this.service.findGameById(gameId);
	}
	                     
	
	
	

}
