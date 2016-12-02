package pl.gto.hand;


import pl.gto.card.Card;
import pl.gto.card.Rank;
import pl.gto.card.Suit;
import pl.gto.rank.HandStrengthCalculator;
import pl.gto.rank.PokerHand;

import java.util.*;

/**
 * Created on 11/8/2016.
 */
public class Hand {

    private final SortedSet<Card> cards;

    private final Set<Suit> suits = new HashSet<>();

    private final NavigableMap<Rank, Integer> ranks;

    private PokerHand handStrength;

    public Hand(Card... cards) {
        if (cards.length == 0)
            throw new IllegalArgumentException();

        this.cards = new TreeSet<>();

        Map<Rank, Integer> tmpRanks = new HashMap<>();

        for (int i = 0; i < cards.length; i++) {
            this.cards.add(cards[i]);

            suits.add(cards[i].getSuit());
            tmpRanks.compute(cards[i].getRank(), (k, v) -> {
                Integer value = tmpRanks.get(k);
                return value == null ? 1 : ++value;
            });
        }

        ranks = new TreeMap<>((Rank a, Rank b) -> {
            int valueComparision = tmpRanks.get(a).compareTo(tmpRanks.get(b));
            return valueComparision == 0 ? a.compareTo(b) : valueComparision;
        });
        ranks.putAll(tmpRanks);

        if (this.cards.size() == 5)
            handStrength = HandStrengthCalculator.calculateRank(this);
    }

    public SortedSet<Card> getCards() {
        return cards;
    }

    public PokerHand getHandStrength() {
        return handStrength;
    }

    public Set<Suit> getSuits() {
        return Collections.unmodifiableSet(suits);
    }

    public NavigableMap<Rank, Integer> getRanks() {
        return Collections.unmodifiableNavigableMap(ranks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hand hand = (Hand) o;

        return cards.equals(hand.cards);
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }

    @Override
    public String toString() {
        String result = "";
        for (Card c : cards)
            result += c + " ";

        return result;
    }

    public boolean containsAnyOf(Collection<Card> cardsCollection) {
        Set<Card> cardsCopy = new HashSet<>(this.cards);
        cardsCopy.retainAll(cardsCollection);
        return cardsCopy.size() > 0;
    }
}
