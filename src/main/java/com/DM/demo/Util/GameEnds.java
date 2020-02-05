package com.DM.demo.Util;

public enum GameEnds {
	WIN {
		@Override
		public int getPoints() {
			return 3;
		}
	}, LOSS {
		@Override
		public int getPoints() {
			return 0;
		}
	}, DRAW {
		@Override
		public int getPoints() {
			return 1;
		}
	};

	public abstract int getPoints();
}
