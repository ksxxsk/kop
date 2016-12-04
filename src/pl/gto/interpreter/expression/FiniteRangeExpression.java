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
        Rank secondRankStart = Rank.getByName(handString.charAt(1));
        Rank secondRankEnd = Rank.getByName(handString.charAt(5));

        if (secondRankStart.compareTo(secondRankEnd) > 0) {
            Rank tmp = secondRankStart;
            secondRankStart = secondRankEnd;
            secondRankEnd = tmp;
        }

        for (int i = secondRankStart.ordinal(); i <= secondRankEnd.ordinal(); i++) {
            addCardCombinations(context, firstRank, Rank.values()[i]);
        }
    }

    protected abstract void addCardCombinations(Context context, Rank firstRank, Rank secondRank);
}
