package pl.gto.interpreter;


import pl.gto.card.CardPool;
import pl.gto.card.Rank;
import pl.gto.card.Suit;
import pl.gto.hand.Hand;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2016-12-01.
 */
public class Context {

    public Set<Hand> getParsedHands() {
        return parsedHands;
    }

    private Set<Hand> parsedHands = new HashSet<>();

    public void addOffsuitCombinations(Rank firstRank, Rank secondRank) {
        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                parsedHands.add(new Hand(CardPool.get(firstRank, first), CardPool.get(secondRank, second)));
            }
        }
    }

    public void addSuitedCombinations(Rank firstRank, Rank secondRank) {
        for (Suit suit : Suit.values()) {
            parsedHands.add(new Hand(CardPool.get(firstRank, suit), CardPool.get(secondRank, suit)));
        }
    }

    public void addPairsCombinations(Rank rank) {
        for (int i = 0; i < Suit.values().length; i++) {
            for (int j = i + 1; j < Suit.values().length; j++) {
                parsedHands.add(new Hand(CardPool.get(rank, Suit.values()[i]), CardPool.get(rank, Suit.values()[j])));
            }
        }
    }

    public void addHand(Hand hand) {
        parsedHands.add(hand);
    }
}
