package pl.gto.game.streets;

import pl.gto.card.Card;
import pl.gto.game.CombinationUtil;
import pl.gto.hand.Hand;
import pl.gto.hand.HandComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created on 12/1/2016.
 */
public abstract class StreetCombinations {

    public static final int HAND_SIZE = 5;

    protected final int[] indexes = IntStream.range(0, 7).toArray();;
    protected final List<int[]> combinations = new ArrayList<>();

    public StreetCombinations() {
        CombinationUtil.addCombination(combinations, indexes, indexes.length, HAND_SIZE);
    }

    public static List<Hand> getAllCombinations(Hand hand, Card... cards) {
        StreetCombinations combinations = null;

        int size = hand.getCards().size() + cards.length;
        switch (size) {
            case 7:
                combinations = new RiverCombinations();
                break;
            case 6:
                combinations = new TurnCombinations();
                break;
            case 5:
                combinations = new FlopCombinations();
                break;
        }

        return combinations == null ? null : combinations.getAllCombinationsForStreet(hand, cards);
    }

    public static Hand getStrongestCombination(Hand hand, Card... cards) {
        List<Hand> combinations = getAllCombinations(hand, cards);
        combinations.sort(new HandComparator());

        return combinations.get(combinations.size() - 1);
    }

    public abstract List<Hand> getAllCombinationsForStreet(Hand hand, Card... cards);

    protected void addHandCombinations(List<Hand> handCombinations, Card[] cards) {
        for (int[] combination : combinations) {
            Card[] cardToIndex = new Card[5];
            for (int i = 0; i < combination.length; i++) {
                cardToIndex[i] = cards[combination[i]];
            }
            handCombinations.add(new Hand(cardToIndex));
        }
    }
}
