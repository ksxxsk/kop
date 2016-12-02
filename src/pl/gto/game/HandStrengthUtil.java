package pl.gto.game;


import pl.gto.card.Card;
import pl.gto.hand.Hand;
import pl.gto.hand.HandComparator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 12/1/2016.
 */
public class HandStrengthUtil {

    private final static HandComparator handComparator = new HandComparator();

    public static double handVsRange(Hand hand, Set<Hand> range, Collection<Card> dealtCards) {

        int[] comparision = range.stream().filter(h -> !h.containsAnyOf(dealtCards)).mapToInt( h -> handComparator.compare(hand, h)).toArray();
        long win = Arrays.stream(comparision).filter( i -> i > 0).count();
        long draw = (int) Arrays.stream(comparision).filter(i -> i == 0).count();

        return ( 0.5 * draw + win ) / comparision.length;
    }

    public static double rangeVsRange(Collection<Hand> heroRange, Set<Hand> villainRange) {
        double sum = 0;
        for (Hand hand : heroRange) {
            sum += handVsRange(hand, villainRange, Collections.emptySet());
        }

        return sum / heroRange.size();
    }
}
