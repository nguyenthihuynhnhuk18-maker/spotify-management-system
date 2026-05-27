package chart;

import dao.TrackDAO;
import factory.DAOFactory;
import model.TrackStatistic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.DefaultCategoryDataset;

public class PopularityChart {

    public PopularityChart() {

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        for(TrackStatistic s :
                dao.getStatistics()) {

            dataset.addValue(

            		s.avgPopularity(),

                    "Popularity",

                    s.genre()
            );
        }

        JFreeChart chart =

                ChartFactory.createBarChart(

                        "Average Popularity by Genre",

                        "Genre",

                        "Popularity",

                        dataset
                );

        ChartFrame frame =
                new ChartFrame(
                        "Popularity Chart",
                        chart
                );

        frame.setSize(800, 500);

        frame.setVisible(true);
    }
}