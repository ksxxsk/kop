package pl.gto.interpreter.expression;

import pl.gto.card.Rank;
import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public class FinitePairRangeExpression extends Expression {

    public FinitePairRangeExpression(String handString) {
        super(handString);
    }

    @Override
    public void interpret(Context context) {
        Rank firstRank = Rank.getByName(handString.charAt(0));
        Rank secondRank = Rank.getByName(handString.charAt(3));

        if (firstRank.compareTo(secondRank) > 0) {
            Rank tmp = firstRank;
            firstRank = secondRank;
            secondRank = tmp;
        }

        for (int i = firstRank.ordinal(); i <= secondRank.ordinal(); i++) {
            context.addPairsCombinations(Rank.values()[i]);
        }
    }
}
