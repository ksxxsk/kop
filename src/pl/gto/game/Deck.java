package pl.gto.game;


import pl.gto.card.Card;
import pl.gto.card.Rank;
import pl.gto.card.Suit;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 12/1/2016.
 */
public class Deck extends ArrayList<Card> {

    public Deck() {
        initDeck();
    }

    public Deck(Collection<Card> cardsToRemove) {
        this();
        removeAll(cardsToRemove);
    }

    private void initDeck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values())
                add(new Card(rank, suit));
        }


    }

}