/**
 * 29 Αυγ 2020 - 8:20:47 μ.μ.

 */
package com.svman4.ratingSystem.eloRatingSystem;

import java.util.Random;

/**
 * @author Manos Gerakianakis
 *
 */
public class EloRatingSystemMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EloRatingPlayer p1=new SimpleEloPlayer(1000,32);
		EloRatingPlayer p2=new SimpleEloPlayer(1000,32);
		EloCalculator calc=new EloCalculator();
		Random rand=new Random();
		rand.setSeed(System.currentTimeMillis());
		
		for (int k=1  ; k<50 ; k++ ) {
			p1.setK_factor(k);
			p1.setEloRating(1000);
			p2.setEloRating(1000);
			p2.setK_factor(1+rand.nextInt(32-1));
			try {
				calc.calculateElo(p1, p2, EloCalculator.WINNER_IS_THE_FIRST);
			} catch (Exception exc) {
			}
			System.out.printf("%3d  %4s  %4d(%3d)  %4s %4d(%3d) \n", k,"p1",p1.getEloRating(),p1.getK_Factor(),"p2",p2.getEloRating(),p2.getK_Factor());
			
		}
		
		// TODO Auto-generated method stub

	}
	

}
