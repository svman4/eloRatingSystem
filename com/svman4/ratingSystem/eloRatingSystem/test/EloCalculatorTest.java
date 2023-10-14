/**
 * 1 Σεπ 2020 - 11:21:44 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem.test;

import org.junit.jupiter.api.Test;

import com.svman4.ratingSystem.eloRatingSystem.EloCalculator;
import com.svman4.ratingSystem.eloRatingSystem.IEloRatingBattle;
import com.svman4.ratingSystem.eloRatingSystem.IEloRatingPlayer;

import junit.framework.TestCase;

/**
 * @author Manos Gerakianakis
 *
 */
public class EloCalculatorTest extends TestCase{

	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#getProbabilityForWinning(com.svman4.ratingSystem.IEloRatingPlayer[])}.
	 */
	@Test
	public void testGetProbabilityForWinning() {
		EloCalculator calc=new EloCalculator();
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
		EloCalculator calc=new EloCalculator();
		// Check when winner is the first one
		Player manos = new Player(2400, 32);
		Player john = new Player(2000, 32);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_FIRST);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		assertTrue(manos.getEloRating() == 2403);
		assertTrue(john.getEloRating()==1997);
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
		assertTrue(john.getEloRating()==2029);
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
		assertTrue(john.getEloRating()==2013);
	}
	@Test
	public void testCalculateEloWithKFactor10() {
		final int INITIAL_MANOS_ELO=1000;
		final int INITIAL_JOHN_ELO=1005;
		final int K_FACTOR=10;
		EloCalculator calc=new EloCalculator();
		// Check when winner is the first one
		Player manos = new Player(INITIAL_MANOS_ELO,K_FACTOR);
		Player john = new Player(INITIAL_JOHN_ELO, K_FACTOR);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_FIRST);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		assertTrue(manos.getEloRating() == 1005);
		assertTrue(john.getEloRating()==1000);

		// check when winner is the second one.

		manos.setEloRating(INITIAL_MANOS_ELO);
		john.setEloRating(INITIAL_JOHN_ELO);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_SECOND);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 995);
		assertTrue(john.getEloRating()==1010);

		// check when the match is draw.
		manos.setEloRating(INITIAL_MANOS_ELO);
		john.setEloRating(INITIAL_JOHN_ELO);
		try {
			calc.calculateElo(manos, john, EloCalculator.DRAW);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 1000);
		assertTrue(john.getEloRating()==1005);
	}

	@Test
	public void testCalculateEloWithSmallKFactor5() {
		final int INITIAL_MANOS_ELO=1000;
		final int INITIAL_JOHN_ELO=1005;
		final int K_FACTOR=5;
		EloCalculator calc=new EloCalculator();
		// Check when winner is the first one
		Player manos = new Player(INITIAL_MANOS_ELO,K_FACTOR);
		Player john = new Player(INITIAL_JOHN_ELO, K_FACTOR);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_FIRST);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		assertTrue(manos.getEloRating() == 1003);
		assertTrue(john.getEloRating()==1002);
		
		

		// check when winner is the second one.

		manos.setEloRating(INITIAL_MANOS_ELO);
		john.setEloRating(INITIAL_JOHN_ELO);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_SECOND);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 998);
		assertTrue(john.getEloRating()==1007);
		// check when the match is draw.
		manos.setEloRating(INITIAL_MANOS_ELO);
		john.setEloRating(INITIAL_JOHN_ELO);
		try {
			calc.calculateElo(manos, john, EloCalculator.DRAW);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == 1000);
		assertTrue(john.getEloRating()==1005);
	}

	@Test
	public void testCalculateEloWithBigDifference() {
		final int INITIAL_MANOS_ELO=2500;
		final int INITIAL_JOHN_ELO=1000;
		final int K_FACTOR=40;
		
		EloCalculator calc=new EloCalculator();
		// Check when winner is the first one
		Player manos = new Player(INITIAL_MANOS_ELO,K_FACTOR);
		Player john = new Player(INITIAL_JOHN_ELO, K_FACTOR);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_FIRST);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		assertTrue(manos.getEloRating() == INITIAL_MANOS_ELO);
		assertTrue(john.getEloRating()==INITIAL_JOHN_ELO);
		

		// check when winner is the second one.

		manos.setEloRating(INITIAL_MANOS_ELO);
		john.setEloRating(INITIAL_JOHN_ELO);
		try {
			calc.calculateElo(manos, john, EloCalculator.WINNER_IS_THE_SECOND);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == (INITIAL_MANOS_ELO-40));
		assertTrue(john.getEloRating()==(INITIAL_JOHN_ELO+40));

		// check when the match is draw.
		manos.setEloRating(INITIAL_MANOS_ELO);
		john.setEloRating(INITIAL_JOHN_ELO);
		try {
			calc.calculateElo(manos, john, EloCalculator.DRAW);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		// System.out.println(manos.getEloRating());
		assertTrue(manos.getEloRating() == (INITIAL_MANOS_ELO-20));
		assertTrue(john.getEloRating()==(INITIAL_JOHN_ELO+20));
	}


	/**
	 * Test method for
	 * {@link com.svman4.ratingSystem.EloCalculator#calculateElo(com.svman4.ratingSystem.IEloRatingPlayer[], int)}.
	 */
	@Test
	public void testCalculateEloIEloRatingPlayerArrayInt() {
		EloCalculator calc=new EloCalculator();
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
		EloCalculator calc=new EloCalculator();
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
		EloCalculator calc=new EloCalculator();
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
