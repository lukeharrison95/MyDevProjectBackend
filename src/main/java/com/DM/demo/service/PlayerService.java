package com.DM.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.DM.demo.Util.GameEnds;
import com.DM.demo.Util.GameNotFoundException;
import com.DM.demo.Util.PlayerNotFoundException;
import com.DM.demo.dto.LeagueRow;
import com.DM.demo.dto.LeagueTable;
import com.DM.demo.persistence.entities.Game;
import com.DM.demo.persistence.entities.Player;
import com.DM.demo.persistence.repo.PlayerRepo;

@Service
public class PlayerService {

	private PlayerRepo repo;

	private GameService gameService;

	public PlayerService(PlayerRepo repo, GameService gameService) {
		this.repo = repo;
		this.gameService = gameService;
	}

	public Player createPlayer(Player player) {
		return this.repo.saveAndFlush(player);
	}

	public boolean deletePlayer(Long playerId) {
		if (!this.repo.existsById(playerId)) {
			throw new PlayerNotFoundException();
		}
		this.repo.deleteById(playerId);
		return this.repo.existsById(playerId);
	}

	public Player findPlayerById(Long playerId) {
		return this.repo.findById(playerId).orElseThrow(() -> new PlayerNotFoundException());
	}

	public List<Player> readPlayer() {
		return this.repo.findAll();
	}

	public Player updatePlayer(Player player, Long id) {
		Player updatedPlayer = this.repo.findById(id).orElseThrow(PlayerNotFoundException:: new);
		updatedPlayer.setPlayerName(player.getPlayerName());
		return this.repo.save(updatedPlayer);
	}

	
	public int getPoints(Long id) {
		return this.repo.findById(id).orElseThrow(PlayerNotFoundException::new).getGames().stream()
				.map(game -> game.getResult()).map(result -> result.getPoints()).reduce((acc, next) -> acc + next)
				.orElse(0);
	}

	public int getWins(long id) {
		return (int) this.repo.findById(id).orElseThrow(PlayerNotFoundException::new).getGames().stream()
				.map(game -> game.getResult()).filter(i -> i == GameEnds.WIN).count();
	}

	public int getDraws(long id) {
		return (int) this.repo.findById(id).orElseThrow(PlayerNotFoundException::new).getGames().stream()
				.map(game -> game.getResult()).filter(i -> i == GameEnds.DRAW).count();
	}

	public int getLost(long id) {
		return (int) this.repo.findById(id).orElseThrow(PlayerNotFoundException::new).getGames().stream()
				.map(game -> game.getResult()).filter(i -> i == GameEnds.LOSS).count();
	}

	public Player addGame(Game game, Long id) {
		Player player = this.repo.findById(id).orElseThrow(PlayerNotFoundException::new);
		player.getGames().add(gameService.createGame(game));
		return this.repo.saveAndFlush(player);
	}

	public LeagueTable getTable() {
		LeagueTable table = new LeagueTable();
		List<LeagueRow> rows = this.readPlayer().stream()
				.map(player -> new LeagueRow(player.getPlayerName(), getWins(player.getPlayerId()),
						getLost(player.getPlayerId()), getDraws(player.getPlayerId()), getPoints(player.getPlayerId())))
				.collect(Collectors.toList());
		Collections.sort(rows);
		table.setRows(rows);
		return table;
	}

	
	
}

