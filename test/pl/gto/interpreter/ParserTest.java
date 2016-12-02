package pl.gto.interpreter;

import org.junit.After;
import org.junit.Test;
import pl.gto.card.CardPool;
import pl.gto.card.Suit;
import pl.gto.hand.Hand;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created on 11/30/2016.
 */
public class ParserTest {

    Set<Hand> referenceResult = new HashSet<>();
    Parser parser = new Parser();
    String hand;

    @After
    public void tearDown() {
        assertThat(parser.parse(hand), is(equalTo(referenceResult)));
        referenceResult.clear();
    }

    @Test
    public void parseSingleHand() {
        hand = "AdKh";
        Hand ak = new Hand(CardPool.get("Ad"), CardPool.get("Kh"));
        referenceResult.add(ak);

        assertThat(parser.parse(hand), is(equalTo(referenceResult)));
    }

    @Test
    public void parseSingeSuitedHand() {
        hand = "AKs";
        Hand diamond = new Hand(CardPool.get("Ad"), CardPool.get("Kd"));
        Hand heart = new Hand(CardPool.get("Ah"), CardPool.get("Kh"));
        Hand spade = new Hand(CardPool.get("As"), CardPool.get("Ks"));
        Hand club = new Hand(CardPool.get("Ac"), CardPool.get("Kc"));

        referenceResult.add(diamond);
        referenceResult.add(heart);
        referenceResult.add(spade);
        referenceResult.add(club);
    }

    @Test
    public void parseSingleOffsuitHand() {
        hand = "AKo";
        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("A" + first.getShortName()), CardPool.get("K" + second.getShortName())));
            }
        }
    }

    @Test
    public void parseSuitedInfiniteRangeAQPlus() {

        hand = "AQs+";
        for (Suit suit : Suit.values()) {
            referenceResult.add(new Hand(CardPool.get("A" + suit.getShortName()), CardPool.get("Q" + suit.getShortName())));
            referenceResult.add(new Hand(CardPool.get("A" + suit.getShortName()), CardPool.get("K" + suit.getShortName())));
        }

    }

    @Test
    public void parseSuitedInfiniteRangeKTPlus() {

        hand = "KTs+";
        for (Suit suit : Suit.values()) {
            referenceResult.add(new Hand(CardPool.get("K" + suit.getShortName()), CardPool.get("T" + suit.getShortName())));
            referenceResult.add(new Hand(CardPool.get("K" + suit.getShortName()), CardPool.get("J" + suit.getShortName())));
            referenceResult.add(new Hand(CardPool.get("K" + suit.getShortName()), CardPool.get("Q" + suit.getShortName())));
            referenceResult.add(new Hand(CardPool.get("K" + suit.getShortName()), CardPool.get("A" + suit.getShortName())));
        }

    }

    @Test
    public void parseOffsuitInfiniteRangeAQPlus() {

        hand = "AQo+";

        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("A" + first.getShortName()), CardPool.get("Q" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("A" + first.getShortName()), CardPool.get("K" + second.getShortName())));
            }
        }

    }

    @Test
    public void parseOffsuitInfiniteRangeKTPlus() {

        hand = "KTo+";

        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("T" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("J" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("Q" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("A" + second.getShortName())));
            }
        }

    }

    @Test
    public void parsePairsInfiniteRange77Plus() {

        hand = "JJ+";

        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("A" + first.getShortName()), CardPool.get("A" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("K" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("Q" + first.getShortName()), CardPool.get("Q" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("J" + first.getShortName()), CardPool.get("J" + second.getShortName())));
            }
        }
    }

    @Test
    public void parsePairFiniteRange77tiJJ() {

        hand = "77-JJ";

        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("7" + first.getShortName()), CardPool.get("7" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("8" + first.getShortName()), CardPool.get("8" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("9" + first.getShortName()), CardPool.get("9" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("T" + first.getShortName()), CardPool.get("T" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("J" + first.getShortName()), CardPool.get("J" + second.getShortName())));
            }
        }
    }

    @Test
    public void addSinglePair88() {

        hand = "88";

        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("8" + first.getShortName()), CardPool.get("8" + second.getShortName())));
            }
        }
    }

    @Test
    public void parseSuitedFiniteRangeATtoAQ() {

        hand = "ATs-AQs";
        for (Suit suit : Suit.values()) {
            referenceResult.add(new Hand(CardPool.get("A" + suit.getShortName()), CardPool.get("T" + suit.getShortName())));
            referenceResult.add(new Hand(CardPool.get("A" + suit.getShortName()), CardPool.get("J" + suit.getShortName())));
            referenceResult.add(new Hand(CardPool.get("A" + suit.getShortName()), CardPool.get("Q" + suit.getShortName())));
        }
    }

    @Test
    public void addOffsuitCombinationsK8toKJ() {
        hand = "K8o-KJo";
        for (Suit first : Suit.values()) {
            for (Suit second : Suit.values()) {
                if (first == second)
                    continue;

                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("8" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("9" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("T" + second.getShortName())));
                referenceResult.add(new Hand(CardPool.get("K" + first.getShortName()), CardPool.get("J" + second.getShortName())));
            }
        }
    }
}