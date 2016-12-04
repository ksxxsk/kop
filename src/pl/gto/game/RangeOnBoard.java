package pl.gto.game;

import pl.gto.card.Card;
import pl.gto.game.streets.StreetCombinations;
import pl.gto.hand.Hand;
import pl.gto.hand.HandComparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2016-12-03.
 */
public class RangeOnBoard {

    private final Comparator<Hand> comparator = new HandComparator().reversed();
    private final List<Hand> filteredRange;
    private final Map<Hand, Hand> strongestHands;

    public RangeOnBoard(Collection<Hand> startingRange, Collection<Card> board) {
        filteredRange = removeHandsWithCardFromCollection(startingRange, board);
        strongestHands = new HashMap<>(startingRange.size());

        findStrongestHandOnBoard(filteredRange, board);
        sortStartingRange();
    }

    private List<Hand> removeHandsWithCardFromCollection(Collection<Hand> range, Collection<Card> forbiddenCards) {
        return range.stream().filter(h -> !h.containsAnyOf(forbiddenCards)).collect(Collectors.toList());
    }

    private void sortStartingRange() {
        filteredRange.sort((c1, c2) -> comparator.compare(strongestHands.get(c1), strongestHands.get(c2)));
    }

    private void findStrongestHandOnBoard(List<Hand> range, Collection<Card> board) {
        for (Hand hand : range) {
            Hand fullHand = StreetCombinations.getStrongestCombination(hand, board.toArray(new Card[board.size()]));

            strongestHands.put(hand, fullHand);
        }
    }

    public List<Hand> getRange() {
        return filteredRange;
    }

    public Collection<Hand> getStrongestHands() {
        return strongestHands.values();
    }

    public Hand getStrongestHand(Hand hand) {
        return strongestHands.get(hand);
    }
}
