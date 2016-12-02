package pl.gto.game;


import pl.gto.card.Card;
import pl.gto.hand.Hand;
import pl.gto.hand.HandComparator;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 12/1/2016.
 */
public class HandStrengthUtil {

    private final static HandComparator handComparator = new HandComparator();

    public static double handVsRange(Hand hand, Collection<Hand> range, Collection<Card> dealtCards) {

        Set<Hand> filteredRange = range.stream().filter(h -> !h.containsAnyOf(dealtCards)).collect(Collectors.toSet());
        int win = filteredRange.stream().mapToInt( h -> handComparator.compare(hand, h) > 0 ? 1 : 0).sum();

        System.out.println(win);
        System.out.println(filteredRange.size());
        return (double) win / filteredRange.size();
    }

    public static double rangeVsRange(Collection<Hand> heroRange, Collection<Hand> villainRange) {
        double sum = 0;
        for (Hand hand : heroRange) {
            sum += handVsRange(hand, villainRange, Collections.emptySet());
        }

        return sum / heroRange.size();
    }
}
