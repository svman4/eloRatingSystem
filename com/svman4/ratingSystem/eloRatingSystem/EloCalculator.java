/**
 * 29 Αυγ 2020 - 6:24:05 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem;

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
	 *
	 * @return double[] with probability
	 * 
	 */
	public double[] getProbabilityForWinning(IEloRatingPlayer[] players)
			throws Exception {
		// calculate transformed Rating for each player.

		int numOfPlayers = players.length;

		double sum = 0;
		double t[] = new double[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			t[i] = Math.pow(10, (((double) players[i].getEloRating())) / 400);
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
	 * Calculate the final elo rating after the match.<br>
	 * A new elo save on Player class .
	 * 
	 * @param winner
	 *          Select the player who win.<br>
	 *          <b>WINNER_IS_THE_FIRST</b> if the winner is the first player,<br>
	 *          <b>WINNDER_IS_THE_SECOND</b> if the winner is the second player,
	 *          <br>
	 *          <b>DRAW </b> if the game its a draw.
	 * @throws Exception
	 *           When the the third parameter (winner) is invalid.
	 */
	public void calculateElo(	IEloRatingPlayer player1, IEloRatingPlayer player2,
														int winner)
			throws Exception {
		if ((winner == WINNER_IS_THE_FIRST || winner == WINNER_IS_THE_SECOND
				|| winner == DRAW) == false) {
			throw new Exception("invalid winner parameter.");
		}
		double player1Rating=player1.getEloRating();
		double player2Rating=player2.getEloRating();
		double player1KFactor=player1.getK_Factor();
		double player2KFactor=player2.getK_Factor();

		// calculate transformed Rating for each player.
		double t1 = Math.pow(10, (player1Rating / 400));
		double t2 = Math.pow(10, (player2Rating / 400));

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
		double elo1 = player1Rating + player1KFactor * (s1 - e1);
		double elo2 = player2Rating + player2KFactor * (s2 - e2);
		player1.setEloRating((int) Math.round(elo1));
		player2.setEloRating((int) Math.round(elo2));
	} // end of calculateElo

	/**
	 * 
	 * @param players
	 *          Players on match. (Maximum two)
	 * @param numberOfWinner
	 *          Player number who win. No draw allow.
	 */
	public void calculateElo(IEloRatingPlayer players[], int numberOfWinner)
			throws Exception {
		// Check if numberOfWinner parameter is valid.
		if (players == null || players.length > 2) {
			throw new Exception(
					"Players array is null or number of players is more than two.");
		}
		if (numberOfWinner >= players.length || numberOfWinner > players.length) {
			throw new Exception("invalid winner parameter.");
		}

		double e[] = getProbabilityForWinning(players);

		// calculate a new elo for each player
		double elo;
		for (int i = 0; i < players.length; i++) {
			double playerEloRating=players[i].getEloRating();
			double playersKFactor=players[i].getK_Factor();
			if (i == numberOfWinner) {
				elo =playerEloRating + playersKFactor * (1 - e[i]);
			} else {
				elo = playerEloRating + playersKFactor * (0 - e[i]);
			}
			players[i].setEloRating((int) Math.round(elo));
		}
	} // end of calculateElo(IEloRatingPlayer)

	/**
	 * Calculate probability of winning and new Elo Rating (if match complete) for
	 * each player of match. A new elo save on Player class (The last one is
	 * lost).
	 * 
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
					winnerIndex = i;
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
