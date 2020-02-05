package com.DM.demo.dto;

public class LeagueRow implements Comparable<LeagueRow> {

	private String name;

	private int wins;

	private int losses;

	private int draws;

	private int points;

	public LeagueRow() {
	}

	public LeagueRow(String name, int wins, int losses, int draws, int points) {
		super();
		this.name = name;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int compareTo(LeagueRow to) {
		return new Integer(this.getPoints()).compareTo(to.getPoints()) * -1;
	}
}

