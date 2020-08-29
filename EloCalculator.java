/**
 * 29 Αυγ 2020 - 6:24:05 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem;

/**
 * @author Manos Gerakianakis
 *
 */
public class EloCalculator {
	public static int	WINNER_IS_THE_FIRST		= 1;
	public static int	WINNER_IS_THE_SECOND	= 2;
	public static int	DRAW									= 3;
	
	/**
	 * Calculate the final elo rating after the match.

	 * @param winner
	 *   Select the player who win.<br>
 *   <b>WINNER_IS_THE_FIRST</b> if the winner is the first player,<br>
 *   <b>WINNDER_IS_THE_SECOND</b> if the winner is the second player, <br>
 *   <b>DRAW </b> if the game its a draw.
	 * @throws Exception
	 */
	public void calculateElo(	EloRatingPlayer player1, EloRatingPlayer player2,
														int winner)
			throws Exception {
		if ((winner == WINNER_IS_THE_FIRST
				|| winner == WINNER_IS_THE_SECOND) == false || winner == DRAW) {
			throw new Exception("invalid winner parameter.");
		}

		// calculate transformed Rating for each player.
		double t1 = Math.pow(10, (player1.getEloRating() / 400));
		double t2 = Math.pow(10, (player2.getEloRating() / 400));

		// calculate expected score for each player.
		double e1 = t1 / (t1 + t2);
		double e2 = t2 / (t1 + t2);

		// set the actual score
		double s1;
		double s2;
		if (winner == WINNER_IS_THE_FIRST) {
			s1 = 1;
			s2 = 0;
		} else if (winner == WINNER_IS_THE_SECOND) {
			s1 = 0;
			s2 = 1;
		} else {
			// if its draw (winner=DRAW)
			s1 = 0.5;
			s2 = 0.5;
		}

		// calculate a new elo for each player
		double elo1 = player1.getEloRating() + player1.getK_Factor() * (s1 - e1);
		double elo2 = player2.getEloRating() + player2.getK_Factor() * (s2 - e2);
		player1.setEloRating((int) Math.round(elo1));
		player2.setEloRating((int) Math.round(elo2));
	} // end of calculateElo
	/**
	 * @param i
	 */
} // end of class EloCalculator
