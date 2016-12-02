package pl.gto.card;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 10/17/2016.
 */
public enum Suit {
    SPADE('s'), HEART('h'), DIAMOND('d'), CLUB('c');

    private static Map<Character, Suit> map = Arrays.stream(values()).collect(Collectors.toMap(Suit::getShortName, Function.identity()));

    private char shortName;

    Suit(char shortName) {
        this.shortName = shortName;
    }

    public static Suit getByName(char shortName) {
        return map.get(shortName);
    }

    public char getShortName() {
        return shortName;
    }
}
