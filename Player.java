/**
 * 29 Αυγ 2020 - 8:22:00 μ.μ.
 * 
 */
package com.svman4.ratingSystem.eloRatingSystem;

/**
 * default k_factor is 32.
 * 
 * @author Manos Gerakianakis
 *
 */
class SimpleEloPlayer implements EloRatingPlayer {
	private int	elo_rating;
	private int	k_factor	= 32;
	/**
	 * @param i
	 * @param j
	 */
	public SimpleEloPlayer(int elo, int kFactor ) {
		this.elo_rating = elo;
		this.k_factor = kFactor;
	}

	@Override
	public int getEloRating() {
		return elo_rating;
	}

	@Override
	public void setEloRating(int elo1) {
		this.elo_rating = elo1;
	}

	@Override
	public int getK_Factor() {
		return k_factor;
	}

	@Override
	public void setK_factor(int k_factor) {
		this.k_factor = k_factor;
	}

}
