package pl.gto.interpreter.expression;

import pl.gto.interpreter.Context;

/**
 * Created on 2016-12-01.
 */
public abstract class Expression {

    protected final String handString;

    public Expression(String handString) {
        this.handString = handString;
    }

    public abstract void interpret(Context context);

}
