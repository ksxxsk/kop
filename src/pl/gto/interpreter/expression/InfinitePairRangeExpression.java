package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public class InfinitePairRangeExpression extends Expression {

    public InfinitePairRangeExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank rank = Rank.getByName(handString.charAt(0));

        for (int i = rank.ordinal(); i < Rank.values().length; i++) {
            context.addPairsCombinations(Rank.values()[i]);
        }
    }

}
