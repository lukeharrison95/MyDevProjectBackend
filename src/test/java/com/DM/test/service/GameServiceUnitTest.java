package com.DM.test.service;

import static org.junit.Assert.*;
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
import org.springframework.test.context.junit4.SpringRunner;

import com.DM.demo.Util.GameEnds;
import com.DM.demo.persistence.entities.Game;
import com.DM.demo.persistence.repo.GameRepo;
import com.DM.demo.service.GameService;


@RunWith(SpringRunner.class)
public class GameServiceUnitTest {

	@InjectMocks
	private GameService service;
	
	@Mock
	private GameRepo repo;
	private List<Game> gameList;
	private Game testGame;
	private Game testGameWithId;
	final Long gameId = 1L;
	
	
	@Before
	public void init() {
	this.gameList = new ArrayList<>();
	this.testGame = new Game(2L,20L,GameEnds.WIN);
	this.testGameWithId = new Game(testGame.getFinishingDouble(), testGame.getNumberOfDartsThrown(), testGame.getResult());
	testGameWithId.setGameId(gameId);
	gameList.add(testGame);
	
	
	}
	
	@Test
	public void createGameTest() {
		when(this.repo.saveAndFlush(testGame)).thenReturn(testGameWithId);
		assertEquals(this.testGameWithId,this.service.createGame(testGame));
		verify(this.repo, times(1)).saveAndFlush(testGame);
		
	}
	
	@Test
	public void findGameByIdTest() {
		when(this.repo.findById(this.gameId)).thenReturn(Optional.of(this.testGameWithId));
		assertEquals(this.testGameWithId, this.service.findGameById(gameId));
		verify(this.repo, times(1)).findById(gameId);
		
	}
	
	@Test
	public void findAllGamesTest() {
		when(this.repo.findAll()).thenReturn(this.gameList);
		assertFalse("No Games Found",this.service.readGame().isEmpty());
		verify(this.repo, times(1)).findAll();	
		}
	
	@Test
	public void updateGameTest() {
		Game newGameData = new Game(10L,45L,GameEnds.LOSS);
		Game updatedGame = new Game(newGameData.getFinishingDouble(),newGameData.getNumberOfDartsThrown(),newGameData.getResult());
		updatedGame.setGameId(gameId);
		
		when(this.repo.save(newGameData)).thenReturn(updatedGame);
		assertEquals(updatedGame, this.service.updateGame(newGameData));
		verify(this.repo, times(1)).save(newGameData);
	}

}

