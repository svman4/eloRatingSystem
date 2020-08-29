/**
 * 29 Αυγ 2020 - 6:25:26 μ.μ.

 */
package com.svman4.ratingSystem.eloRatingSystem;

/**
 * @author Manos Gerakianakis
 *
 */
public interface EloRatingPlayer {
	/**
	 * Επιστρέφει την βαθμολογία Elo για αυτό τον παίχτη.
	 * @return
	 */
	public int getEloRating();

	/**
	 * @param elo1
	 */
	public void setEloRating(int elo1);

	/**
	 * @return
	 */
	public int getK_Factor();

}
