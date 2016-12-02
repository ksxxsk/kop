package pl.gto.game;

import org.junit.After;
import org.junit.Test;
import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.hand.Hand;
import pl.gto.interpreter.Parser;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created on 12/2/2016.
 */
public class EquityRepresentationTest {

    private Set<Hand> heroRange = new HashSet<>();
    private Set<Hand> villainRange = new HashSet<>();
    private List<Card> board = Arrays.asList(new Card[]{CardPool.get("Td"), CardPool.get("Jc"), CardPool.get("3s"), CardPool.get("2d"), CardPool.get("4d")});

    @After
    public void tearDown() {
        heroRange.clear();
        villainRange.clear();
    }

    @Test
    public void handsWithBoardsCardsRemovedFromHeroStartingRange() {

        heroRange.addAll(Parser.parse("A9o+"));

        Set<Hand> referenceRange = new HashSet<>(heroRange);
        removeHandsFromRangeWithCardsFromCollection(referenceRange, board);

        EquityRepresentation eq = new EquityRepresentation(heroRange, villainRange, board);

        assertThat(eq.getHeroRange(), is(equalTo(referenceRange)));
    }

    @Test
    public void handsWithBoardsCardsRemovedFromVillainStartingRange() {

        villainRange.addAll(Parser.parse("A2s+"));

        Set<Hand> referenceRange = new HashSet<>(villainRange);
        removeHandsFromRangeWithCardsFromCollection(referenceRange, board);

        EquityRepresentation eq = new EquityRepresentation(heroRange, villainRange, board);

        assertThat(eq.getVillainRange(), is(equalTo(referenceRange)));
    }

    private void removeHandsFromRangeWithCardsFromCollection(Set<Hand> hands, Collection<Card> forbiddenCards) {
        Iterator<Hand> iterator = hands.iterator();
        while(iterator.hasNext()) {
            Hand hand = iterator.next();
            if(hand.containsAnyOf(board)) {
                iterator.remove();
            }
        }
    }

    @Test
    public void countEquitiesForEachStartingHandInHeroRange() {

        heroRange.addAll(Parser.parse("AdTs"));
        heroRange.addAll(Parser.parse("KcJd"));
        heroRange.addAll(Parser.parse("6d7d"));
        heroRange.addAll(Parser.parse("JdJh"));

        EquityRepresentation eq = new EquityRepresentation(heroRange, villainRange, board);


    }

}