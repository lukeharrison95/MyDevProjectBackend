package com.DM.test.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.DM.demo.Util.GameEnds;
import com.DM.demo.dto.LeagueRow;
import com.DM.demo.dto.LeagueTable;
import com.DM.demo.persistence.entities.Game;
import com.DM.demo.persistence.entities.Player;
import com.DM.demo.rest.PlayerController;
import com.DM.demo.service.PlayerService;



@RunWith(SpringRunner.class)
public class PlayerControllerTest {
	
	@InjectMocks
	private PlayerController playerController;
	
	@Mock
	private PlayerService playerService;
	private List<Player> playerList;
	private List<Game> gameList;
	private List<LeagueRow> leagueRows;
	private LeagueRow leagueRow;
	private LeagueTable leagueTable;
	private Player testPlayer;
	private Game testGame;
	private Player testPlayerWithId;
	//private Game testGameWithId;
	final Long playerId=1L;
	final Long gameId=2L;
	private Player testPlayerWithGame;
	final int points=3;
	final int wins=1;
	final int draws=0;
	final int losses=0;
	
	
	@Before
	public void init() {
	this.playerList = new ArrayList<>();
	this.gameList = new ArrayList<>();
	this.testGame = new Game(2L,20L,GameEnds.WIN);
	this.testGame.setGameId(gameId);
	this.testPlayer = new Player("Big Phil");
	this.testPlayerWithId = new Player(testPlayer.getPlayerName());
	playerList.add(testPlayer);
	gameList.add(testGame);
	this.testPlayerWithGame = new Player(testPlayer.getPlayerName());
	testPlayerWithGame.setPlayerId(playerId);
	testPlayerWithGame.setGames(gameList);
	this.leagueRow = new LeagueRow(testPlayerWithGame.getPlayerName(),playerService.getPoints(playerId)
					,playerService.getWins(playerId), playerService.getDraws(playerId), playerService.getLost(playerId));
	this.leagueRows = new ArrayList<>();
	this.leagueRows.add(leagueRow);
	this.leagueTable = new LeagueTable();
	this.leagueTable.setRows(leagueRows);
	
	
	
	}
	
	

	@Test
	public void createPlayertest() {
		when(this.playerService.createPlayer(testPlayer)).thenReturn(testPlayerWithId);
		assertEquals(this.testPlayerWithId, this.playerController.createPlayer(testPlayer));
		verify(this.playerService, times(1)).createPlayer(testPlayer);
	}
	
	@Test
	public void deletePlayertest() {
		this.playerController.deletePlayer(playerId);
		verify(this.playerService, times(1)).deletePlayer(playerId);
	}
	

	
	@Test
	public void findAllPlayers() {
		when(this.playerService.readPlayer()).thenReturn(playerList);
		assertFalse("No Players Found",this.playerController.findAllPlayers().isEmpty());
		verify(this.playerService, times(1)).readPlayer();
		
	}
	
	@Test
	public void findPlayerById() {
		when(this.playerService.findPlayerById(playerId)).thenReturn(testPlayerWithId);
		assertEquals(this.testPlayerWithId,this.playerController.findPlayerById(playerId));
		verify(this.playerService, times(1)).findPlayerById(playerId);
	}
	
	@Test
	public void updatePlayer() {
		Player newPlayerData = new Player("Tiny Phil");
		Player updatedPlayer = new Player(newPlayerData.getPlayerName());
		updatedPlayer.setPlayerId(playerId);
		
		when(this.playerService.updatePlayer(newPlayerData, this.playerId)).thenReturn(updatedPlayer);
		assertEquals(updatedPlayer, this.playerController.updatePlayer(newPlayerData, playerId));
		verify(this.playerService, times(1)).updatePlayer(newPlayerData, playerId);
		
		
	}
	
	@Test
	public void addGameTest() {
		when(this.playerService.addGame(testGame, playerId)).thenReturn(testPlayerWithGame);
		assertEquals(this.testPlayerWithGame,this.playerController.addGame(testGame, playerId));
		
	}
	
	@Test
	public void getPointsTest() {
		when(this.playerService.getPoints(playerId)).thenReturn(points);
		assertEquals(points, this.playerController.getPoints(playerId));
	}
	
	@Test
	public void getWinsTest() {
		when(this.playerService.getWins(playerId)).thenReturn(wins);
		assertEquals(wins, this.playerController.getWins(playerId));
		
	}
	
	@Test
	public void getDrawsTest() {
		when(this.playerService.getDraws(playerId)).thenReturn(draws);
		assertEquals(draws, this.playerController.getDraws(playerId));
		
	}

	@Test
	public void getLossesTest() {
		when(this.playerService.getLost(playerId)).thenReturn(losses);
		assertEquals(losses, this.playerController.getLoss(playerId));
	}
	
	@Test
	public void getTableTest() {
		when(this.playerService.getTable()).thenReturn(leagueTable);
		assertEquals(leagueTable, this.playerController.getTable());
	}




}

