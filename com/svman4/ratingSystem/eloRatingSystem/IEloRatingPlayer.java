/**
 * 29 Αυγ 2020 - 6:25:26 μ.μ.

 */
package com.svman4.ratingSystem.eloRatingSystem;

/**
 * @author Manos Gerakianakis
 * @version 1.0.0
 * @date 29/08/2020
 */
public interface IEloRatingPlayer {
	public static final int DEFAULT_ELO=1000;
	/**
	 * Return elo rating
	 * @return
	 */
	public int getEloRating();
	public void setEloRating(int elo1);

	public int getK_Factor();
	
	public void setK_factor(int k_factor);
} // end of interface IEloRatingPlayer()
