package com.DM.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class LeagueTable {
	
	List<LeagueRow> rows = new ArrayList<>();

	public List<LeagueRow> getRows() {
		return rows;
	}

	public void setRows(List<LeagueRow> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "LeagueTable [rows=" + rows + "]";
	}

	
	
}
