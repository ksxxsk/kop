package pl.gto.hand;

import pl.gto.card.Rank;

import java.util.Comparator;
import java.util.NavigableSet;

/**
 * Created on 11/29/2016.
 */
public class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand hand1, Hand hand2) {
        int strengthComparision = hand1.getHandStrength().compareTo(hand2.getHandStrength());

        return strengthComparision != 0 ? strengthComparision : compareRanks(hand1, hand2);
    }

    private int compareRanks(Hand hand1, Hand hand2) {
        NavigableSet<Rank> hand1RanksSet = hand1.getRanks().descendingKeySet();
        Rank[] hand1Ranks = hand1RanksSet.toArray(new Rank[hand1RanksSet.size()]);

        NavigableSet<Rank> hand2RanksSet = hand2.getRanks().descendingKeySet();
        Rank[] hand2Ranks = hand2RanksSet.toArray(new Rank[hand2RanksSet.size()]);

        for (int i = 0; i < hand1Ranks.length; i++) {
            int comparision = hand1Ranks[i].compareTo(hand2Ranks[i]);
            if (comparision != 0)
                return comparision;
        }

        return 0;
    }
}