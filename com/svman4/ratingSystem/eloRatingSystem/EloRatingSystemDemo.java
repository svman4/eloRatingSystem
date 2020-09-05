/**
 * 29 Αυγ 2020 - 8:20:47 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem;

/**
 * 05/09/2020
 * <h1>Demo program for saw the application of eloRatingLibrary.</h1><br>
 * 
 * Program create two player (p1 & p2).<br>
 * EloCalculator calculate the new elo<br>
 * And then print the result.
 * 
 * @author Manos Gerakianakis
 * 
 *
 */
public class EloRatingSystemDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create two player with same Elo and k-factor.
		int firstEloRatingForBothPlayer=1000;
		
		IEloRatingPlayer p1 = new SimpleEloPlayer(firstEloRatingForBothPlayer, 32);
		IEloRatingPlayer p2 = new SimpleEloPlayer(firstEloRatingForBothPlayer, 32);

		EloCalculator calc = new EloCalculator();
		try {
			calc.calculateElo(p1, p2, EloCalculator.WINNER_IS_THE_FIRST);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}

		System.out.println("Elo rating for player p1 go from "+ firstEloRatingForBothPlayer +" to "  + p1.getEloRating());
		System.out.println("Elo rating for player p2 go from "+ firstEloRatingForBothPlayer +" to "  + p2.getEloRating());
		
	} // end of main(String[])

} // end of class EloRatingSystemDemo
