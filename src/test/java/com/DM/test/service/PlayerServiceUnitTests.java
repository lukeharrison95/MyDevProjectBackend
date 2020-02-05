package com.DM.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.DM.demo.Util.GameEnds;
import com.DM.demo.dto.LeagueRow;
import com.DM.demo.dto.LeagueTable;
import com.DM.demo.persistence.entities.Game;
import com.DM.demo.persistence.entities.Player;
import com.DM.demo.persistence.repo.GameRepo;
import com.DM.demo.persistence.repo.PlayerRepo;
import com.DM.demo.service.GameService;
import com.DM.demo.service.PlayerService;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceUnitTests {

	@InjectMocks
	private PlayerService playerService;
	
	@Mock
	private GameRepo gameRepo;
	@Mock
	private GameService gameService;
	@Mock
	private PlayerRepo playerRepo;
	final Long playerId = 1L;
	private List<Player> playerList;
	private List<Game> gameList;
	private Game testGame;
	private Game testGameWithId;
	private Player testPlayer;
	private Player testPlayerWithGame;
	private Player testPlayerWithId;


	final Long gameId = 1L;
	final int points = 1;

	@Before
	public void init() {
		this.playerList = new ArrayList<>();
		this.gameList = new ArrayList<>();
		this.testPlayer = new Player("Big Phil");
		this.testGame = new Game(2L, 20L, GameEnds.DRAW);
		playerList.add(testPlayer);
		this.testPlayerWithId = new Player(testPlayer.getPlayerName());
		testPlayerWithId.setPlayerId(playerId);
		this.testGameWithId = new Game(testGame.getFinishingDouble(), testGame.getNumberOfDartsThrown(),
				testGame.getResult());
		testGameWithId.setGameId(gameId);
		gameList.add(testGameWithId);
		this.testPlayerWithGame = new Player(testPlayer.getPlayerName());
		testPlayerWithGame.setPlayerId(playerId);
		testPlayerWithGame.setGames(gameList);

	}

	@Test
	public void addGameTest() {
		when(this.playerRepo.findById(this.playerId)).thenReturn(Optional.of(this.testPlayerWithId));
		when(this.gameService.createGame(testGame)).thenReturn(testGameWithId);
		when(this.playerRepo.saveAndFlush(this.testPlayerWithGame)).thenReturn(testPlayerWithGame);

		assertEquals(testPlayerWithGame, this.playerService.addGame(this.testGame, this.playerId));

		verify(this.playerRepo, times(1)).findById(this.playerId);
		verify(this.playerRepo, times(1)).saveAndFlush(testPlayerWithGame);
	}

	@Test
	public void createPlayertest() {
		when(this.playerRepo.saveAndFlush(testPlayer)).thenReturn(this.testPlayerWithId);
		assertEquals(this.testPlayerWithId, this.playerService.createPlayer(testPlayer));
		verify(this.playerRepo, times(1)).saveAndFlush(testPlayer);
	}

	@Test
	public void deletePlayerTest() {
		when(this.playerRepo.existsById(playerId)).thenReturn(true, false);
		this.playerService.deletePlayer(playerId);
		verify(this.playerRepo, times(1)).deleteById(playerId);
		verify(this.playerRepo, times(2)).existsById(playerId);
	}

	@Test
	public void findAllPlayersTest() {
		when(this.playerRepo.findAll()).thenReturn(playerList);
		assertEquals(playerList, this.playerService.readPlayer());
		verify(this.playerRepo, times(1)).findAll();
	}

	@Test
	public void findPlayerByIdTest() {
		when(this.playerRepo.findById(playerId)).thenReturn(Optional.of(testPlayerWithId));
		assertEquals(testPlayerWithId, this.playerService.findPlayerById(playerId));
		verify(this.playerRepo, times(1)).findById(1L);
	}

	@Test
	public void updatePlayerTest() {
		Player newpdata = new Player("Tiny Tim");
		Player updatedPlayer = new Player(newpdata.getPlayerName());
		updatedPlayer.setPlayerId(playerId);

		when(this.playerRepo.findById(this.playerId)).thenReturn(Optional.of(testPlayerWithId));
		when(this.playerRepo.save(updatedPlayer)).thenReturn(updatedPlayer);
		assertEquals(updatedPlayer, this.playerService.updatePlayer(newpdata, playerId));
		verify(this.playerRepo, times(1)).findById(1L);
		verify(this.playerRepo, times(1)).save(updatedPlayer);
	}
	
	
	@Test
	public void getPointsTest() {
		when(this.playerRepo.findById(playerId)).thenReturn(Optional.of(testPlayerWithGame));
		assertEquals(points,this.playerService.getPoints(playerId));
	}
	
	@Test
	public void getWins() {
		when(this.playerRepo.findById(playerId)).thenReturn(Optional.of(testPlayerWithGame));
		assertEquals(0,this.playerService.getWins(playerId));
	}
	
	@Test 
	public void getDrawsTest() {
		when(this.playerRepo.findById(playerId)).thenReturn(Optional.of(testPlayerWithGame));
		assertEquals(1,this.playerService.getDraws(playerId));
	}
	
	@Test
	public void getlostTest() {
		when(this.playerRepo.findById(playerId)).thenReturn(Optional.of(testPlayerWithGame));
		assertEquals(0, this.playerService.getLost(playerId));
	}
	
	@Test
	public void getTableTest() {
		LeagueTable leagueTable = new LeagueTable();

		assertEquals(leagueTable.toString(), this.playerService.getTable().toString());
	}


//	@Test
//	public void fullTableTest() {
//		LeagueTable leagueTable = new LeagueTable();
//		LeagueRow leagueRow1 = new LeagueRow("Big Phil", 1,0,0,3);
//		LeagueRow leagueRow2 = new LeagueRow("Tiny Tim", 0,1,0,1);
//		LeagueRow leagueRow3 = new LeagueRow("Average Larry", 0,0,1,0);
//		List<LeagueRow> rows = new ArrayList<>();
//		rows.add(leagueRow1);
//		rows.add(leagueRow2);
//		rows.add(leagueRow3);
//		leagueTable.setRows(rows);
//		
//		Player bigPhil = new Player("Big Phil");
//		bigPhil.setPlayerId(1L);
//		Player tinyTim = new Player("Tiny Tim");
//		tinyTim.setPlayerId(2L);
//		Player averageLarry = new Player("AverageLarry");
//		averageLarry.setPlayerId(3L);
//		
//		Game philsGame = new Game(2L,20L,GameEnds.WIN);
//		when(this.playerRepo.findById(bigPhil.getPlayerId())).thenReturn(Optional.of(bigPhil));
//		playerService.addGame(philsGame, bigPhil.getPlayerId());
//		
//		Game timsGame = new Game(4L,35L,GameEnds.DRAW);
//		when(this.playerRepo.findById(tinyTim.getPlayerId())).thenReturn(Optional.of(tinyTim));
//		playerService.addGame(timsGame, tinyTim.getPlayerId());
//		
//		Game larrysGame = new Game(3L,40L,GameEnds.LOSS);
//		when(this.playerRepo.findById(averageLarry.getPlayerId())).thenReturn(Optional.of(averageLarry));
//		playerService.addGame(larrysGame, averageLarry.getPlayerId());
//		List<Player> playerListTest = new ArrayList<>();
//		playerListTest.add(bigPhil);
//		playerListTest.add(tinyTim);
//		playerListTest.add(averageLarry);
//		
//		when(this.playerRepo.findAll()).thenReturn(playerListTest);
//		System.out.println(playerListTest);
//		assertEquals(leagueTable.toString(), this.playerService.getTable().toString());
//	}
		
	}
