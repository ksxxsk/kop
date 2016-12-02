package pl.gto.hand;

import org.junit.Test;
import pl.gto.card.CardPool;
import pl.gto.card.Rank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 11/8/2016.
 */
public class HandTest {

    @Test
    public void addFourAcesAndKingOfDiamonds() {
        Hand hand = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("As"), CardPool.get("Ah"), CardPool.get("Kd"));

        assertThat(hand.getSuits().size(), is(4));
        assertThat(hand.getRanks().size(), is(2));
        assertThat(hand.getRanks().get(Rank.ACE), is(4));
        assertThat(hand.getRanks().get(Rank.KING), is(1));
    }

    @Test
    public void addTwoAcesTwoKingsTen() {
        Hand hand = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ks"), CardPool.get("Th"), CardPool.get("Kd"));

        assertThat(hand.getSuits().size(), is(4));
        assertThat(hand.getRanks().size(), is(3));
        assertThat(hand.getRanks().get(Rank.ACE), is(2));
        assertThat(hand.getRanks().get(Rank.KING), is(2));
        assertThat(hand.getRanks().get(Rank.TEN), is(1));
        assertThat(hand.getRanks().lastKey(), is(equalTo(Rank.ACE)));
    }


}