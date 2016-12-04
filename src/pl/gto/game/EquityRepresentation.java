package pl.gto.game;

import pl.gto.card.Card;
import pl.gto.hand.Hand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 12/2/2016.
 */
public class EquityRepresentation {

    private final Map<Hand, Double> heroEquityMap;

    private final Map<Hand, Double> villainEquityMap;

    public EquityRepresentation(Collection<Hand> heroStartingRange, Collection<Hand> villainStartingRange, Collection<Card> board) {

        RangeOnBoard heroRange = new RangeOnBoard(heroStartingRange, board);
        RangeOnBoard villainRange = new RangeOnBoard(villainStartingRange, board);

        heroEquityMap = new HashMap<>(heroRange.getRange().size());
        villainEquityMap = new HashMap<>(villainRange.getRange().size());

        calculateEquities(heroRange, villainRange, heroEquityMap);
        calculateEquities(villainRange, heroRange, villainEquityMap);

    }

    private void calculateEquities(RangeOnBoard heroRange, RangeOnBoard villainRange, Map<Hand, Double> equityMap) {
        for (Hand hand : heroRange.getRange()) {

            Set<Hand> villainRangeWithoutHeroCards = villainRange.getStrongestHands().stream()
                    .filter(h -> !h.containsAnyOf(hand.getCards())).collect(Collectors.toSet());

            if (!villainRangeWithoutHeroCards.isEmpty()) {

                equityMap.put(hand, HandVsRangeCalculator.handVsRange(heroRange.getStrongestHand(hand), villainRangeWithoutHeroCards));

            }
        }
    }

    public Map<Hand, Double> getHeroEquityMap() {
        return heroEquityMap;
    }

    public Map<Hand, Double> getVillainEquityMap() {
        return villainEquityMap;
    }

}
