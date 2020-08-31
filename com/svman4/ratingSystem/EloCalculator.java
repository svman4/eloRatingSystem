/**
 * 29 Αυγ 2020 - 6:24:05 μ.μ.
 * 
 */
package com.svman4.ratingSystem;

/**
 * Simple Elo Calculating Library.
 * 
 * @Version 1.1 - 30/08/2020 Create Rating calculate for match with many player.
 *          (Now without draw selection).<br>
 *          Create Elo Calculate for battle (IEloRatingBattle
 * 
 * 
 * @version 1.0 - 29/08/2020 - Create Simple Elo calculating library.
 * @author Manos Gerakianakis
 *
 */
public class EloCalculator {
	public static int	WINNER_IS_THE_FIRST		= 1;
	public static int	WINNER_IS_THE_SECOND	= 2;
	public static int	DRAW									= 3;

	/**
	 * Return probability of winning for each player
	 * 
	 */
	public double[] getProbabilityForWinning(IEloRatingPlayer[] players) {
		// calculate transformed Rating for each player.
		int numOfPlayers = players.length;
		double sum = 0;
		double t[] = new double[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			t[i] = Math.pow(10, players[i].getEloRating() / 400);
			sum += t[i];
		}

		// calculate expected score for each player.
		double e[] = new double[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			e[i] = t[i] / sum;
		}
		return e;

	} // end of getProbabilityForWinning(IEloRatingPlayer()

	/**
	 * Calculate the final elo rating after the match.
	 * 
	 * @param winner
	 *          Select the player who win.<br>
	 *          <b>WINNER_IS_THE_FIRST</b> if the winner is the first player,<br>
	 *          <b>WINNDER_IS_THE_SECOND</b> if the winner is the second player,
	 *          <br>
	 *          <b>DRAW </b> if the game its a draw.
	 * @throws Exception
	 */
	public void calculateElo(	IEloRatingPlayer player1, IEloRatingPlayer player2,
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
	 * 
	 * @param players
	 *          Players on match.
	 * @param numberOfWinner
	 *          Player number who win. No draw allow.
	 */
	public void calculateElo(IEloRatingPlayer players[], int numberOfWinner)
			throws Exception {

		if (numberOfWinner < players.length || numberOfWinner > players.length) {
			throw new Exception("invalid winner parameter.");
		}

		// calculate transformed Rating for each player.
		double t[] = new double[players.length];
		for (int i = 0; i < players.length; i++) {
			t[i] = Math.pow(10, (players[i].getEloRating() / 400));
		}

		// calculate expected score for each player.
		double sumOfTransRating = 0;
		for (int i = 0; i < players.length; i++) {
			sumOfTransRating += t[i];
		}
		double e[] = new double[players.length];
		for (int i = 0; i < players.length; i++) {
			e[i] = t[i] / sumOfTransRating;
		}

		// calculate a new elo for each player
		double elo;
		for (int i = 0; i < players.length; i++) {
			if (i == numberOfWinner) {
				elo = players[i].getEloRating() + players[i].getK_Factor() * (1 - e[i]);
			} else {
				elo = players[i].getEloRating() + players[i].getK_Factor() * (0 - e[i]);
			}
			players[i].setEloRating((int) Math.round(elo));
		}
	} // end of calculateElo(IEloRatingPlayer)

	/**
	 * Calculate probability of winning and new Elo Rating (if match complete) for
	 * each player of match. A new elo save on Player class (The last one is lost.
	 * @version 1.1 (01/9/2020)
	 * @author Manos Gerakianakis
	 * 
	 * @param battle
	 * @throws Exception
	 *           when winner not found on players array.
	 */
	public void calculateElo(IEloRatingBattle battle) throws Exception {
		// Players of the match
		IEloRatingPlayer players[] = battle.getPlayers();

		// Propability to win for any of players.
		double prob[] = getProbabilityForWinning(players);
		battle.setWinningPropability(prob);

	
		if (battle.isComplete() == true) {

			int winnerIndex = -1;
			IEloRatingPlayer winner = battle.getWinner();
			if (winner == null) {
				throw new Exception("No valid winner found");
			}
			// found the index of winner on players array.
			for (int i = 0; i < players.length; i++) {
				if (players[i] == winner) {
					break;
				}
			}
			if (winnerIndex == -1) {
				throw new Exception("No valid winner found");
			}

			this.calculateElo(players, winnerIndex);
		}
	} // end of calculateElo(IEloRatingBattle)
} // end of class EloCalculator
