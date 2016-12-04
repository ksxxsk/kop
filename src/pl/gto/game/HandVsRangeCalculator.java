package pl.gto.game;

import pl.gto.hand.Hand;
import pl.gto.hand.HandComparator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 12/1/2016.
 */
public class HandVsRangeCalculator {

    private final static HandComparator handComparator = new HandComparator();
    private final static BigDecimal half = BigDecimal.valueOf(0.5);

    public static double handVsRange(Hand hand, Collection<Hand> range) {

        int[] comparision = range.stream().mapToInt(h -> handComparator.compare(hand, h)).toArray();
        long win = Arrays.stream(comparision).filter(i -> i > 0).count();
        long draw = (int) Arrays.stream(comparision).filter(i -> i == 0).count();

        BigDecimal equity = half.multiply(BigDecimal.valueOf(draw)).add(BigDecimal.valueOf(win))
                .divide(BigDecimal.valueOf(comparision.length), 4, BigDecimal.ROUND_HALF_UP);

        return equity.doubleValue();
    }

}
