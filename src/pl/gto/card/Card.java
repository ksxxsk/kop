package pl.gto.card;

/**
 * Created on 10/17/2016.
 */
public class Card implements Comparable<Card> {

    private final Suit suit;
    private final Rank rank;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card card) {
        int rankComparision = rank.compareTo(card.rank);
        return rankComparision == 0 ? suit.compareTo(card.suit) : rankComparision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Card card = (Card) o;

        if (suit != card.suit)
            return false;
        return rank == card.rank;

    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" + rank.getShortName() + suit.getShortName();

    }
}
