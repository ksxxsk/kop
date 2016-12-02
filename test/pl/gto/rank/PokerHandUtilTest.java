package pl.gto.rank;

import org.junit.Test;
import pl.gto.card.*;
import pl.gto.hand.Hand;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 11/8/2016.
 */
public class PokerHandUtilTest {

    @Test
    public void fourAcesAndKingOfDiamondsIsQuads() {
        Hand hand = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("As"), CardPool.get("Ah"), CardPool.get("Kd"));

        assertThat(HandStrengthCalculator.calculateRank(hand), is(PokerHand.QUADS));
    }

    @Test
    public void twoAcesTwoKingsTenIsTwoPair() {
        Hand hand = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ks"), CardPool.get("Th"), CardPool.get("Kd"));

        assertThat(HandStrengthCalculator.calculateRank(hand), is(PokerHand.TWOPAIRS));
    }

    @Test
    public void threeAcesKingTenIsTrips() {
        Hand hand = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ts"), CardPool.get("Ah"), CardPool.get("Kd"));

        assertThat(HandStrengthCalculator.calculateRank(hand), is(PokerHand.TRIPS));
    }
}