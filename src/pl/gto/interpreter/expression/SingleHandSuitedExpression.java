package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;
import pl.gto.interpreter.Parser;

/**
 * Created on 2016-12-01.
 */
public class SingleHandSuitedExpression extends SingleHandSetExpression {

    public SingleHandSuitedExpression(String handString) {
        super(handString);
    }

    @Override
    protected void addCardCombinations(Context context, Rank firstRank, Rank secondRank) {
        context.addSuitedCombinations(firstRank, secondRank);
    }
}
