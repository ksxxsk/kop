package pl.gto.card;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 10/17/2016.
 */
public enum Rank {
    TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');

    private static final Map<Character, Rank> map = Arrays.stream(values()).collect(Collectors.toMap(Rank::getShortName, Function.identity()));

    private final char shortName;

    Rank(char shortName) {
        this.shortName = shortName;
    }

    public static Rank getByName(char shortName) {
        return map.get(shortName);
    }

    public char getShortName() {
        return shortName;
    }
}
