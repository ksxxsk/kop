package pl.gto.rank;


import pl.gto.card.Rank;
import pl.gto.hand.Hand;

/**
 * Created on 11/8/2016.
 */
public class HandStrengthCalculator {

    public static PokerHand calculateRank(Hand hand) {

        boolean isFlush = hand.getSuits().size() == 1;
        boolean isStraight = hand.getRanks().size() == 5 && (hand.getCards().last().getRank().ordinal() - hand.getCards().first().getRank().ordinal()) == 4;

        if (isStraight && isFlush) {
            if (hand.getCards().first().getRank().equals(Rank.TEN)) {
                return PokerHand.ROYALSTRAIGHTFLUSH;
            } else {
                return PokerHand.STRAIGHTFLUSH;
            }
        } else if (hand.getRanks().size() == 2 && hand.getRanks().lastEntry().getValue() == 4)
            return PokerHand.QUADS;
        else if (hand.getRanks().size() == 2)
            return PokerHand.FULLHOUSE;
        else if (isFlush)
            return PokerHand.FLUSH;
        else if (isStraight)
            return PokerHand.STRAIGHT;
        else if (hand.getRanks().lastEntry().getValue() == 3)
            return PokerHand.TRIPS;
        else if (hand.getRanks().size() == 3)
            return PokerHand.TWOPAIRS;
        else if (hand.getRanks().size() == 4)
            return PokerHand.PAIR;
        else
            return PokerHand.HIGHCARD;
    }

}
