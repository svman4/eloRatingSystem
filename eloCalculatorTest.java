/**
 * 29 Αυγ 2020 - 7:25:12 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Manos Gerakianakis
 *
 */
public class eloCalculatorTest {
	EloCalculator calc;
	@Before
	public void setUpTest() {
		calc=new EloCalculator();
	} // end of setUpTest
	@Test
	public void eloCalculatorfirstWin() {
			Player manos=new Player(2400,32);
			Player John=new Player(2000,32);
			try {
				calc.calculateElo(manos, John, EloCalculator.WINNER_IS_THE_FIRST);
			} catch (Exception exc) {
				System.out.println(exc.getMessage());
			}
			System.out.println(manos.getEloRating());
			assertTrue(manos.getEloRating()==2403);
			
	}	// end of test eloCalculatorFirstWin
	@Test
	public void eloCalculatorSecondWin() {
			Player manos=new Player(2400,32);
			Player John=new Player(2000,32);
			try {
				calc.calculateElo(manos, John, EloCalculator.WINNER_IS_THE_SECOND);
			} catch (Exception exc) {
				System.out.println(exc.getMessage());
			}
			System.out.println(manos.getEloRating());
			assertTrue(manos.getEloRating()==2371);
	} // end of test eloCalculatorSecondWin
	

		class Player implements EloRatingPlayer {
			public Player(int elo) {
				this.elo=elo;
			}
			public Player(int elo,int k_factor ) {
				this(elo);
				this.elo_K_Factor=k_factor;
			}
			private int elo;
			
			private int elo_K_Factor;
			@Override
			public int getEloRating() {
				return elo;
			}

			@Override
			public void setEloRating(int elo1) {
				elo=elo1;
			}

			@Override
			public int getK_Factor() {
				return elo_K_Factor;
			} 
			
		}

}
