package chart;

import dao.TrackDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import model.TrackStatistic;
import javax.swing.*;
import factory.DAOFactory;
public class GenreChart extends JFrame {

    public GenreChart() {

        setTitle("Genre Chart");

        setSize(600, 400);

        setLocationRelativeTo(null);

        DefaultPieDataset dataset =
                new DefaultPieDataset();

        TrackDAO dao =
        		DAOFactory.getTrackDAO();

        for(TrackStatistic s :
            dao.getStatistics()) {

        dataset.setValue(

        		s.genre(),

        		s.totalTracks()
        );
    }

        JFreeChart chart =
                ChartFactory.createPieChart(
                        "Track Genres",
                        dataset,
                        true,
                        true,
                        false
                );

        ChartPanel panel =
                new ChartPanel(chart);

        setContentPane(panel);
    }
}