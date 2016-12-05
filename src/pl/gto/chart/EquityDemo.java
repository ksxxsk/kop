package pl.gto.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import pl.gto.card.Card;
import pl.gto.card.CardPool;
import pl.gto.game.EquityRepresentation;
import pl.gto.hand.Hand;
import pl.gto.interpreter.Parser;

import java.util.*;

/**
 * Created on 2016-12-04.
 */
public class EquityDemo extends ApplicationFrame {

    public EquityDemo(String title) {
        super(title);
        JFreeChart lineChart = ChartFactory.createLineChart(
                title,
                "Years", "Number of Schools",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    public static void main(String... args) {

        EquityDemo chart = new EquityDemo("Equity");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    private CategoryDataset createDataset() {
        String heroRangeString = "QQ-88,AQs,KJs+,QJs,JTs,AKo,KQo";
        String villainRangeString = "55+,ATs+,KTs+,QTs+,J9s+,T9s,98s,87s,76s,ATo+,KQo";
        List<Card> board = Arrays.asList(CardPool.get("Td"), CardPool.get("Jc"), CardPool.get("3s"),
                CardPool.get("2d"), CardPool.get("4d"));

        Set<Hand> heroStartingRange = new HashSet<>();
        Set<Hand> villainStartingRange = new HashSet<>();

        parseHands(heroStartingRange, heroRangeString);
        parseHands(villainStartingRange, villainRangeString);

        EquityRepresentation eq = new EquityRepresentation(heroStartingRange, villainStartingRange, board);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Hand> range = new ArrayList<>(eq.getHeroEquityMap().keySet());
        range.sort((h1, h2) -> -Double.compare(eq.getHeroEquityMap().get(h1), eq.getHeroEquityMap().get(h2)));

        for (Hand hand : range) {
            dataset.addValue(eq.getHeroEquityMap().get(hand), "hero", hand.toString());
        }

        return dataset;
    }

    private void parseHands(Set<Hand> range, String rangeString) {
        for (String handString : rangeString.split(",")) {
            range.addAll(Parser.parse(handString));
        }
    }
}
