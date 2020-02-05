package com.DM.demo.persistence.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {

	@Id
	@GeneratedValue
	private long playerId;

	private String playerName;

	@OneToMany
	private List<Game> games = new ArrayList<>();

	public Player(String playerName, List<Game> games) {
		super();
		this.playerName = playerName;
		this.games = games;
	}

	public Player() {
	};

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> gameSet) {
		this.games = gameSet;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (playerId != other.playerId)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}


}
