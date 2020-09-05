/**
 * 1 Σεπ 2020 - 11:21:44 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Manos Gerakianakis
 *
 */
public class EloCalculatorTest {

	private static EloCalculator calc;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		calc = new EloCalculator();
	}

	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#getProbabilityForWinning(com.svman4.ratingSystem.IEloRatingPlayer[])}.
	 */
	@Test
	public void testGetProbabilityForWinning() {
		Player manos = new Player(2400, 32);
		Player john = new Player(2000, 32);
		IEloRatingPlayer[] players=new Player[] {manos,john};
		double e[]=new double[players.length];
		try {
			e=calc.getProbabilityForWinning(players);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		int compareValue=Double.compare(0.9090909090909091,e[0]);
		assertTrue(compareValue==0);
	}

	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#calculateElo(com.svman4.ratingSystem.IEloRatingPlayer, com.svman4.ratingSystem.IEloRatingPlayer, int)}.
	 */
	@Test
	public void testCalculateEloIEloRatingPlayerIEloRatingPlayerInt() {

		// Check when winner is the first one
		Player manos = new Player(2400, 32);
		Player john = new Player(2000, 32);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_FIRST);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 2403);

		// check when winner is the second one.

		manos.setEloRating(2400);
		john.setEloRating(2000);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_SECOND);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 2371);

		// check when the match is draw.
		manos.setEloRating(2400);
		john.setEloRating(2000);
		try {
			calc.calculateElo(manos, john, EloCalculator.DRAW);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 2387);
	}

	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#calculateElo(com.svman4.ratingSystem.IEloRatingPlayer[], int)}.
	 */
	@Test
	public void testCalculateEloIEloRatingPlayerArrayInt() {
		Player manos = new Player(2400, 32);
		Player john = new Player(2000, 32);
		Player players[] = new Player[] { manos, john };
		try {
			calc.calculateElo(players, 0);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		assertTrue(manos.getEloRating() == 2403);
	}

	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#calculateElo(com.svman4.ratingSystem.IEloRatingBattle)}.
	 */
	@Test
	public void testCalculateEloIEloRatingBattleWhenBattleNotComplete() {
		Player manos = new Player(2400, 32);
		Player john = new Player(2000, 32);
		Player players[] = new Player[] { manos, john };
		Battle battle = new Battle(players);
		battle.setWinner(manos);
		battle.isComplete(false);

		try {
			calc.calculateElo(battle);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		int compareValue=Double.compare(00.9090909090909091,battle.getWinningPropability()[0]);
		assertTrue(compareValue==0);
		assertTrue(manos.getEloRating() == 2400);
		
		
	}
	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#calculateElo(com.svman4.ratingSystem.IEloRatingBattle)}.
	 */
	@Test
	public void testCalculateEloIEloRatingBattleWhenBattleComplete() {
		Player manos = new Player(2400, 32);
		Player john = new Player(2000, 32);
		Player players[] = new Player[] { manos, john };
		Battle battle = new Battle(players);
		battle.setWinner(john);
		battle.isComplete(true);

		try {
			calc.calculateElo(battle);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		int compareValue=Double.compare(00.9090909090909091,battle.getWinningPropability()[0]);
		assertTrue(compareValue==0);
		assertTrue(manos.getEloRating() == 2371);
		
		
	}

	class Player implements IEloRatingPlayer {
		public Player(int elo ) {
			this.elo = elo;
		}

		public Player(int elo, int k_factor ) {
			this(elo);
			this.elo_K_Factor = k_factor;
		}
		private int	elo;

		private int	elo_K_Factor;
		@Override
		public int getEloRating() {
			return elo;
		}

		@Override
		public void setEloRating(int elo1) {
			elo = elo1;
		}

		@Override
		public int getK_Factor() {
			return elo_K_Factor;
		}

		@Override
		public void setK_factor(int k_factor) {
			elo_K_Factor = k_factor;
		}

	}

	/**
	 * @author Manos Gerakianakis
	 *
	 */
	class Battle implements IEloRatingBattle {

		IEloRatingPlayer	players[];
		double						winningForEachPlayer[];

		/**
		 * @param players2
		 */
		public Battle(Player[] players ) {
			this.players = players;
		}

		@Override
		public IEloRatingPlayer[] getPlayers() {
			return players;
		}

		@Override
		public void setPlayers(IEloRatingPlayer[] players) {
			this.players = players;
		}

		@Override
		public void setWinningPropability(double[] prop) {
			winningForEachPlayer = prop;
		}

		@Override
		public double[] getWinningPropability() {
			return winningForEachPlayer;
		}

		@Override
		public boolean isComplete() {
			return isComplete;
		}
		private boolean isComplete = false;
		@Override
		public IEloRatingPlayer getWinner() {
			return winner;

		}
		private IEloRatingPlayer winner;
		@Override
		public void setWinner(IEloRatingPlayer winner) {
			this.winner = winner;

		}

		@Override
		public void isComplete(boolean complete) {
			isComplete = complete;

		}
	}

}
