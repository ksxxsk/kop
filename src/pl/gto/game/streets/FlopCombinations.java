package pl.gto.game.streets;


import pl.gto.card.Card;
import pl.gto.game.CombinationUtil;
import pl.gto.game.Deck;
import pl.gto.hand.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created on 12/1/2016.
 */
public class FlopCombinations extends StreetCombinations {

    private final int[] additonalCardsIndexes = IntStream.range(0, 47).toArray();
    private final List<int[]> additonalCardsCombinations = new ArrayList<>();

    public FlopCombinations() {
        CombinationUtil.addCombination(additonalCardsCombinations, additonalCardsIndexes, additonalCardsIndexes.length, 2);
    }

    @Override
    public List<Hand> getAllCombinationsForStreet(Hand hand, Card... cards) {
        List<Hand> result = new ArrayList<>();
        Card[] dealtCards = new Card[7];
        System.arraycopy(hand.getCards().toArray(), 0, dealtCards, 0, hand.getCards().size());
        System.arraycopy(cards, 0, dealtCards, hand.getCards().size(), cards.length);

        Deck deck = new Deck(hand.getCards());
        deck.removeAll(Arrays.asList(cards));

        for (int[] ac : additonalCardsCombinations) {
            dealtCards[5] = deck.get(ac[0]);
            dealtCards[6] = deck.get(ac[1]);

            addHandCombinations(result, dealtCards);
        }

        return result;
    }
}
