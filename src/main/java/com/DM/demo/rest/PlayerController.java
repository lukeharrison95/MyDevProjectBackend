package com.DM.demo.rest;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DM.demo.dto.LeagueTable;
import com.DM.demo.persistence.entities.Game;
import com.DM.demo.persistence.entities.Player;
import com.DM.demo.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService service;

	public PlayerController(PlayerService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createPlayer")
	public Player createPlayer(@RequestBody Player player) {
		return this.service.createPlayer(player);
	}
	
	@PatchMapping("/addGame/{id}")
	public Player addGame(@RequestBody Game game, @PathVariable Long id) {
		return this.service.addGame(game, id);
	}
	
	@DeleteMapping("/deletePlayer/{playerId}")
	public void deletePlayer(@PathVariable Long playerId) {
		this.service.deletePlayer(playerId);
	}
	
	@PatchMapping("/updatePlayer/{playerId}")
	public Player updatePlayer(@RequestBody Player player, @PathVariable Long playerId) {
		return this.service.updatePlayer(player, playerId);
	}
	
	@GetMapping("/findPlayer/{playerId}")
	public Player findPlayerById(@PathVariable long playerId) {
		return this.service.findPlayerById(playerId);
	}
	
	@GetMapping("/findPlayer")
	public List<Player> findAllPlayers(){
		return this.service.readPlayer();
	}
	
	@GetMapping("/getPoints/{playerId}")
	public int getPoints(@PathVariable long playerId) {
		return this.service.getPoints(playerId);
	}
	
	@GetMapping("/getWins/{playerId}")
	public long getWins(@PathVariable long playerId) {
		return this.service.getWins(playerId);
	}
	
	@GetMapping("/getDraws/{playerId}")
	public long getDraws(@PathVariable long playerId) {
		return this.service.getDraws(playerId);
	}
	
	@GetMapping("/getLoss/{playerId}")
	public long getLoss(@PathVariable long playerId) {
		return this.service.getLost(playerId);
	}
	
	@GetMapping("/getTable")
	public LeagueTable getTable() {
		return this.service.getTable();
	}
	
	
	
}
