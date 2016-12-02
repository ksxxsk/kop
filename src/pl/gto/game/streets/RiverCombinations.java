package pl.gto.game.streets;


import pl.gto.card.Card;
import pl.gto.hand.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/1/2016.
 */
public class RiverCombinations extends StreetCombinations {

    @Override
    public List<Hand> getAllCombinationsForStreet(Hand hand, Card... cards) {
        List<Hand> result = new ArrayList<>();
        Card[] dealtCards = new Card[7];
        System.arraycopy(hand.getCards().toArray(), 0, dealtCards, 0, hand.getCards().size());
        System.arraycopy(cards, 0, dealtCards, hand.getCards().size(), cards.length);

        addHandCombinations(result, dealtCards);
        return result;
    }
}
