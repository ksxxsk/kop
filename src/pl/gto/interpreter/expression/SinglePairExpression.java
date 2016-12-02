package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public class SinglePairExpression extends Expression {

    public SinglePairExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank rank = Rank.getByName(handString.charAt(0));

        context.addPairsCombinations(rank);
    }

}
