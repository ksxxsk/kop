package pl.gto.game;

import org.junit.Test;
import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.game.streets.StreetCombinations;
import pl.gto.hand.Hand;
import pl.gto.interpreter.Parser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 12/1/2016.
 */
public class HandStrengthUtilTest {

    @Test
    public void AAvsAAKK() throws Exception {
        Hand AA = new Hand(CardPool.get("Ad"),CardPool.get("Ac"));
        Hand KK = new Hand(CardPool.get("Kd"),CardPool.get("Kc"));
        Card[] board = new Card[]{CardPool.get("Td"), CardPool.get("Jc"), CardPool.get("3s"), CardPool.get("2d"), CardPool.get("4d")};

        List<Hand> hero = StreetCombinations.getAllCombinations(AA, board);

        List<Hand> range = new ArrayList<>();
        range.addAll(StreetCombinations.getAllCombinations(KK, board));
        range.addAll(StreetCombinations.getAllCombinations(AA, board));

        Set<Card> dealtCards = new HashSet<>();
        dealtCards.addAll(AA.getCards());
        System.out.println(HandStrengthUtil.handVsRange(hero.get(2), range, dealtCards));
    }

    @Test
    public void rangeVsRange() throws Exception {
        Card[] board = new Card[]{CardPool.get("Td"), CardPool.get("Jc"), CardPool.get("3s"), CardPool.get("2d"), CardPool.get("4d")};
        Parser parser = new Parser();

        List<Hand> hero = new ArrayList<>();
        hero.addAll(parser.parse("AQo"));
//        hero.addAll(parser.parse("AJo+"));

        Set<Card> dealtCards = new HashSet<>();
        dealtCards.add(CardPool.get("Ad"));
        dealtCards.add(CardPool.get("Qh"));

        Iterator<Hand> iterator = hero.iterator();
        while(iterator.hasNext()) {
            Hand hand = iterator.next();
            if(hand.containsAnyOf(Arrays.stream(board).collect(Collectors.toSet())))
                iterator.remove();
        }
        List<Hand> villain = new ArrayList<>();
//        villain.addAll(parser.parse("TT+"));
        villain.addAll(parser.parse("ATo+"));
        villain.addAll(parser.parse("KTo+"));
//        villain.addAll(parser.parse("QTo+"));
//        villain.addAll(parser.parse("JTo"));

        iterator = villain.iterator();
        while(iterator.hasNext()) {
            Hand hand = iterator.next();
            if(hand.containsAnyOf(Arrays.stream(board).collect(Collectors.toSet())))
                iterator.remove();
        }

        List<Hand> heroRange = new ArrayList<>();
        for(Hand hand: hero)
            heroRange.addAll(StreetCombinations.getAllCombinations(hand, board));
//        heroRange.addAll(StreetCombinations.getAllCombinations(new Hand(CardPool.get("Ad"), CardPool.get("Qh")), board));
        List<Hand> villainRange = new ArrayList<>();
        for(Hand hand: villain)
            villainRange.add(StreetCombinations.getStrongestCombination(hand, board));
        Hand hand = new Hand(CardPool.get("Ad"), CardPool.get("Qh"), CardPool.get("Jc"), CardPool.get("Td"), CardPool.get("4d"));
        System.out.println(HandStrengthUtil.handVsRange(hand, villainRange, dealtCards ));
//        System.out.println(HandStrengthUtil.rangeVsRange(heroRange, villainRange));
    }

}