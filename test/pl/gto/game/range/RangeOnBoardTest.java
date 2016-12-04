package pl.gto.game.range;

import org.junit.After;
import org.junit.Test;
import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.game.RangeOnBoard;
import pl.gto.hand.Hand;
import pl.gto.interpreter.Parser;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 2016-12-03.
 */
public class RangeOnBoardTest {
    private final Set<Hand> heroRange = new HashSet<>();
    private final List<Card> board = Arrays.asList(CardPool.get("Td"), CardPool.get("Jc"), CardPool.get("3s"), CardPool.get("2d"), CardPool.get("4d"));

    @After
    public void tearDown() {
        heroRange.clear();
    }

    @Test
    public void handsWithBoardsCardsRemovedFromHeroStartingRange() {

        heroRange.addAll(Parser.parse("A9o+"));

        Set<Hand> referenceRange = new HashSet<>(heroRange);
        removeHandsFromRangeWithCardsFromCollection(referenceRange, board);

        RangeOnBoard range = new RangeOnBoard(heroRange, board);

        assertThat(new HashSet<>(range.getRange()), is(equalTo(referenceRange)));
    }


    private void removeHandsFromRangeWithCardsFromCollection(Set<Hand> hands, Collection<Card> forbiddenCards) {
        hands.removeIf(hand -> hand.containsAnyOf(board));
    }

    @Test
    public void handsInHeroRangeAreSortedAccordingItsStrength() {

        Hand pairOfTens = Parser.parse("AdTs").get(0);
        Hand pairOfJacks = Parser.parse("KcJd").get(0);
        Hand straight = Parser.parse("6d5c").get(0);
        Hand trip = Parser.parse("JdJh").get(0);

        heroRange.add(pairOfTens);
        heroRange.add(pairOfJacks);
        heroRange.add(straight);
        heroRange.add(trip);

        RangeOnBoard range = new RangeOnBoard(heroRange, board);

        List<Hand> referenceRange = new ArrayList<>();
        referenceRange.add(straight);
        referenceRange.add(trip);
        referenceRange.add(pairOfJacks);
        referenceRange.add(pairOfTens);

        assertThat(range.getRange(), is(equalTo(referenceRange)));
    }

}