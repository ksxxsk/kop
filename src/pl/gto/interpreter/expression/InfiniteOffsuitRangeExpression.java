package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public class InfiniteOffsuitRangeExpression extends InfiniteRangeExpression {

    public InfiniteOffsuitRangeExpression(String handString) {
        super(handString);
    }

    @Override
    protected void addCardCombinations(Context context, Rank firstRank, Rank secondRank) {
        context.addOffsuitCombinations(firstRank, secondRank);
    }
}
