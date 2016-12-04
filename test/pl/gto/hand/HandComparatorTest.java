package pl.gto.hand;

import org.junit.Test;
import pl.gto.card.CardPool;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Created on 2016-12-04.
 */
public class HandComparatorTest {

    private final HandComparator comparator = new HandComparator();

    @Test
    public void pairAAGreaterThanKK() {
        Hand hand1 = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ks"), CardPool.get("Th"), CardPool.get("Qd"));
        Hand hand2 = new Hand(CardPool.get("Kc"), CardPool.get("Kd"), CardPool.get("3s"), CardPool.get("Th"), CardPool.get("Qd"));

        assertThat(comparator.compare(hand1, hand2), is(greaterThan(0)));
    }

    @Test
    public void pairAAKGreaterThanAAQ() {
        Hand hand1 = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ks"), CardPool.get("Th"), CardPool.get("Qd"));
        Hand hand2 = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("2s"), CardPool.get("Th"), CardPool.get("Qd"));

        assertThat(comparator.compare(hand1, hand2), is(greaterThan(0)));
    }

    @Test
    public void twoPairAAKKgreaterThanKKQQ() {
        Hand hand1 = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ks"), CardPool.get("Kh"), CardPool.get("Qd"));
        Hand hand2 = new Hand(CardPool.get("Kc"), CardPool.get("Kd"), CardPool.get("Qs"), CardPool.get("Th"), CardPool.get("Qd"));

        assertThat(comparator.compare(hand1, hand2), is(greaterThan(0)));
    }

    @Test
    public void twoPairAAKKKgreaterThanAAQQ() {
        Hand hand1 = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Ks"), CardPool.get("Kh"), CardPool.get("Qd"));
        Hand hand2 = new Hand(CardPool.get("Ac"), CardPool.get("Ad"), CardPool.get("Qs"), CardPool.get("Th"), CardPool.get("Qd"));

        assertThat(comparator.compare(hand1, hand2), is(greaterThan(0)));
    }

    @Test
    public void flushGreaterThanTrips() {
        Hand hand1 = new Hand(CardPool.get("Ac"), CardPool.get("2c"), CardPool.get("Kc"), CardPool.get("4c"), CardPool.get("Qc"));
        Hand hand2 = new Hand(CardPool.get("Kc"), CardPool.get("Kd"), CardPool.get("Ks"), CardPool.get("Th"), CardPool.get("Qd"));

        assertThat(comparator.compare(hand1, hand2), is(greaterThan(0)));
    }

}