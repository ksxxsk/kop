package pl.gto.interpreter.expression;

import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.card.Rank;
import pl.gto.card.Suit;
import pl.gto.hand.Hand;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public class SingleHandSpecificExpression extends Expression {

    public SingleHandSpecificExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank rank = Rank.getByName(handString.charAt(0));
        Suit suit = Suit.getByName(handString.charAt(1));
        Card first = CardPool.get(rank, suit);

        rank = Rank.getByName(handString.charAt(2));
        suit = Suit.getByName(handString.charAt(3));
        Card second = CardPool.get(rank, suit);

        context.addHand(new Hand(first, second));
    }
}
