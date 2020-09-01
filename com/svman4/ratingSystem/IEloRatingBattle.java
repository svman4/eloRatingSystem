/**
 * 1 Σεπ 2020 - 12:03:32 π.μ.
 * 
 */
package com.svman4.ratingSystem;

/**
 * @author Manos Gerakianakis
 * @date 01/09/2020
 */
public interface IEloRatingBattle {

	/**
	 * @return
	 */
	IEloRatingPlayer[] getPlayers();

	public void setPlayers(IEloRatingPlayer[] players);

	/**
	 * @param prob
	 */
	void setWinningPropability(double[] prob);

	double[] getWinningPropability();

	/**
	 * @return
	 */
	boolean isComplete();
	void isComplete(boolean complete);
	/**
	 * @return
	 */
	IEloRatingPlayer getWinner();

	void setWinner(IEloRatingPlayer player);
}
