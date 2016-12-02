package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public abstract class InfiniteRangeExpression extends Expression {

    public InfiniteRangeExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank firstRank = Rank.getByName(handString.charAt(0));
        Rank secondRank = Rank.getByName(handString.charAt(1));

        for (int i = secondRank.ordinal(); i <= Rank.values().length - 1; i++) {
            if (i == firstRank.ordinal()) continue;
            addCardCombinations(context, firstRank, Rank.values()[i]);
        }
    }

    protected abstract void addCardCombinations(Context context, Rank firstRank, Rank secondRank);
}
