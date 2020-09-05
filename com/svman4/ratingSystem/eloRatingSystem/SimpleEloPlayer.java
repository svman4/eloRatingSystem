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
class SimpleEloPlayer implements IEloRatingPlayer {
	private int	elo_rating;
	private int	k_factor	= 32;

	
	public SimpleEloPlayer(int elo, int kFactor ) {
		this.elo_rating = elo;
		this.k_factor = kFactor;
	} // end of constructor(int,int)

	@Override
	public int getEloRating() {
		return elo_rating;
	} // end of getEloRating()

	@Override
	public void setEloRating(int elo1) {
		this.elo_rating = elo1;
	} // end of setEloRating(int)

	@Override
	public int getK_Factor() {
		return k_factor;
	} // end of getK_Factor()

	@Override
	public void setK_factor(int k_factor) {
		this.k_factor = k_factor;
	} // end of setK_factor(int)

} // end of class SimpleEloPlayer
