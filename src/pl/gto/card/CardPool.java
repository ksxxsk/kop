package pl.gto.card;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10/17/2016.
 */
public class CardPool {


    private static Map<Rank, Map<Suit, Card>> cards = new HashMap<>();

    static {

        for (Rank rank : Rank.values()) {

            Map<Suit, Card> map = new HashMap<>();
            for (Suit suit : Suit.values()) {

                map.put(suit, new Card(rank, suit));
            }
            cards.put(rank, map);
        }
    }

    public static Card get(String rankSuit) {
        if (rankSuit.length() != 2)
            throw new IllegalArgumentException("Incorrect rank & suit");

        Rank rank = Rank.getByName(rankSuit.charAt(0));
        if (rank == null)
            throw new IllegalArgumentException("Incorrect rank");

        Suit suit = Suit.getByName(rankSuit.charAt(1));
        if (suit == null)
            throw new IllegalArgumentException("Incorrect suit");

        return get(rank, suit);
    }

    public static Card get(Rank rank, Suit suit) {
        return cards.get(rank).get(suit);
    }

}
