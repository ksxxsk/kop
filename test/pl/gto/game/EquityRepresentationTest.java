package pl.gto.game;

import org.junit.After;
import org.junit.Test;
import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.hand.Hand;
import pl.gto.interpreter.Parser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 12/2/2016.
 */
public class EquityRepresentationTest {


    private final Set<Hand> heroRange = new HashSet<>();
    private final Set<Hand> villainRange = new HashSet<>();
    private final List<Card> board = Arrays.asList(CardPool.get("Td"), CardPool.get("Jc"), CardPool.get("3s"),
            CardPool.get("2d"), CardPool.get("4d"));

    @After
    public void tearDown() {
        heroRange.clear();
        villainRange.clear();
    }

    @Test
    public void utgOpenSBCall() {

        String heroRangeString = "9d9s";
        String villainRangeString = "88-QQ,AQs,KJs+";

        Set<Hand> heroStartingRange = new HashSet<>();
        Set<Hand> villainStartingRange = new HashSet<>();

        parseHands(heroStartingRange, heroRangeString);
        parseHands(villainStartingRange, villainRangeString);

        EquityRepresentation eq = new EquityRepresentation(heroStartingRange, villainStartingRange, board);

        BigDecimal equity = eq.getHeroEquityMap().values().stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(eq.getHeroEquityMap().size()), 4, BigDecimal.ROUND_HALF_UP);

        assertThat(equity, is(equalTo(BigDecimal.valueOf(0.4167))));
    }

    private void parseHands(Set<Hand> range, String rangeString) {
        for (String handString : rangeString.split(",")) {
            range.addAll(Parser.parse(handString));
        }
    }
}