package pl.gto.interpreter;

import pl.gto.hand.Hand;
import pl.gto.interpreter.expression.*;

import java.util.Set;

/**
 * Created on 11/30/2016.
 */
public class Parser {

    public Set<Hand> parse(String handString) {
        Context context = new Context();
        Expression expression = null;

        switch (handString.length()) {
            case 2:
                expression = new SinglePairExpression(handString);
                break;
            case 3:
                if (handString.contains("+")) {
                    expression = new InfinitePairRangeExpression(handString);
                } else {
                    RangeIndicator rangeIndicator = RangeIndicator.valueOf(handString.substring(2));

                    if (rangeIndicator.equals(RangeIndicator.s)) {
                        expression = new SingleHandSuitedExpression(handString);
                    } else if (rangeIndicator.equals(RangeIndicator.o)) {
                        expression = new SingleHandOffsuitExpression(handString);
                    }
                }
                break;

            case 4:
                if (handString.contains("+")) {

                    RangeIndicator rangeIndicator = RangeIndicator.valueOf(handString.substring(2, 3));


                    if (rangeIndicator.equals(RangeIndicator.s)) {
                        expression = new InfiniteSuitedRangeExpression(handString);

                    } else if (rangeIndicator.equals(RangeIndicator.o)) {
                        expression = new InfiniteOffsuitRangeExpression(handString);
                    }
                } else {

                    expression = new SingleHandSpecificExpression(handString);
                }
                break;
            case 5:
                expression = new FinitePairRangeExpression(handString);
                break;
            case 7:
                RangeIndicator rangeIndicator = RangeIndicator.valueOf(handString.substring(2, 3));

                if (rangeIndicator.equals(RangeIndicator.s)) {
                    expression = new FiniteSuitedRangeExpression(handString);
                } else if (rangeIndicator.equals(RangeIndicator.o)) {
                    expression = new FiniteOffsuitRangeExpression(handString);
                }

        }
        expression.interpret(context);
        return context.getParsedHands();
    }

    public enum RangeIndicator {
        o, s
    }
}
