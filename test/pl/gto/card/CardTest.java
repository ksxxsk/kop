package pl.gto.card;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 10/17/2016.
 */
public class CardTest {

    @Test
    public void aceOfSpadesSuitCreated() {
        Card aS = CardPool.get("As");

        assertThat(aS.getSuit(), is(Suit.SPADE));
    }

    @Test
    public void aceOfSpadesRankCreated() {
        Card aS = CardPool.get("As");

        assertThat(aS.getRank(), is(Rank.ACE));
    }

    @Test
    public void queenOfClubsSuitCreated() {
        Card qC = CardPool.get("Qc");

        assertThat(qC.getSuit(), is(Suit.CLUB));
    }

    @Test
    public void queenOfClubdsRankCreated() {
        Card qC = CardPool.get("Qc");

        assertThat(qC.getRank(), is(Rank.QUEEN));
    }
}