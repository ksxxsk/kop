package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public abstract class SingleHandSetExpression extends Expression {

    public SingleHandSetExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank firstRank = Rank.getByName(handString.charAt(0));
        Rank secondRank = Rank.getByName(handString.charAt(1));

        addCardCombinations(context, firstRank, secondRank);

    }

    protected abstract void addCardCombinations(Context context, Rank firstRank, Rank secondRank);


}
