package pl.gto.game;

import pl.gto.card.Card;
import pl.gto.hand.Hand;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 12/2/2016.
 */
public class EquityRepresentation {

    private final Set<Hand> heroRange;
    private final Set<Hand> villainRange;

    public EquityRepresentation(Set<Hand> heroRange, Set<Hand> villainRange, Collection<Card> board) {

        this.heroRange = removeHandsWithCardFromCollection(heroRange, board);
        this.villainRange = removeHandsWithCardFromCollection(villainRange, board);
    }

    private Set<Hand> removeHandsWithCardFromCollection(Set<Hand> range, Collection<Card> forbiddenCards) {
        return range.stream().filter(h -> !h.containsAnyOf(forbiddenCards)).collect(Collectors.toSet());
    }

    public Set<Hand> getHeroRange() {
        return Collections.unmodifiableSet(heroRange);
    }

    public Set<Hand> getVillainRange() {
        return villainRange;
    }
}
