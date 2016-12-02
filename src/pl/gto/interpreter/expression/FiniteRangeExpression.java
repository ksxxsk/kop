package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public abstract class FiniteRangeExpression extends Expression {

    public FiniteRangeExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank firstRank = Rank.getByName(handString.charAt(0));
        Rank secondRank = Rank.getByName(handString.charAt(1));
        Rank secondRankLimit = Rank.getByName(handString.charAt(5));

        for (int i = secondRank.ordinal(); i <= secondRankLimit.ordinal(); i++) {
            addCardCombinations(context, firstRank, Rank.values()[i]);
        }
    }

    protected abstract void addCardCombinations(Context context, Rank firstRank, Rank secondRank);
}
