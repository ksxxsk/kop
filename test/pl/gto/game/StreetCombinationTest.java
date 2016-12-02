package pl.gto.game;

import org.junit.Test;
import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.game.streets.StreetCombinations;
import pl.gto.hand.Hand;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created on 12/1/2016.
 */
public class StreetCombinationTest {

    private static final int DECK_SIZE = 52;
    private static final int sevenFive = 21;
    private static final int fourtySevenTwo = 1081;
    @Test
    public void combinationOnTheFlop() {
        Hand hand = new Hand(CardPool.get("Ad"), CardPool.get("Kh"));
        Card qS = CardPool.get("Qs");
        Card twoD = CardPool.get("2d");
        Card tC = CardPool.get("Tc");

        Collection<Hand> result = StreetCombinations.getAllCombinations(hand, qS, twoD, tC);

        assertThat(result.size(), is(equalTo(sevenFive * fourtySevenTwo)));

    }

    @Test
    public void combinationOnTheTurn() {
        Hand hand = new Hand(CardPool.get("Ad"), CardPool.get("Kh"));
        Card qS = CardPool.get("Qs");
        Card twoD = CardPool.get("2d");
        Card tC = CardPool.get("Tc");
        Card threeC = CardPool.get("3c");

        Collection<Hand> result = StreetCombinations.getAllCombinations(hand, qS, twoD, tC, threeC);

        assertThat(result.size(), is(equalTo(sevenFive * (DECK_SIZE - 6))));

    }

    @Test
    public void combinationOnTheRiver() {
        Hand hand = new Hand(CardPool.get("Ad"), CardPool.get("Kh"));
        Card qS = CardPool.get("Qs");
        Card twoD = CardPool.get("2d");
        Card tC = CardPool.get("Tc");
        Card threeC = CardPool.get("3c");
        Card fourD = CardPool.get("4d");

        Collection<Hand> result = StreetCombinations.getAllCombinations(hand, qS, twoD, tC, threeC, fourD);

        assertThat(result.size(), is(sevenFive));

    }
}