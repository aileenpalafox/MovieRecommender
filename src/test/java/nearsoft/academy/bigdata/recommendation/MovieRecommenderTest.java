package nearsoft.academy.bigdata.recommendation;

import movie.recommender.extract;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import movie.recommender.MovieRecommender;
import movie.recommender.ToCSV;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class MovieRecommenderTest {
    @Test
    public void testDataInfo() throws IOException, TasteException {
        //download moviestest.gz from
        //    http://snap.stanford.edu/data/web-Movies.html

        MovieRecommender recommender = new MovieRecommender("/Users/aileen.palafox/Documents/movies.txt.gz");
        //MovieRecommender recommender = new MovieRecommender("/Users/aileen.palafox/Documents/academy-exercises/src/main/resources/movies50k.txt.gz");
        assertEquals(7911684, recommender.getTotalReviews());
        assertEquals(253059, recommender.getTotalProducts());
        assertEquals(889176, recommender.getTotalUsers());

        //ToCSV tocsv = new ToCSV("/Users/aileen.palafox/Documents/academy-exercises/src/main/resources/movies50k.txt.gz");
        //extract extractMovies = new extract("/Users/aileen.palafox/Documents/movies.txt.gz");

        List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR");//A141HP4LYPWMSR
        assertThat(recommendations, hasItem("B0002O7Y8U")); //dejar comentado
        assertThat(recommendations, hasItem("B00004CQTF"));
        assertThat(recommendations, hasItem("B000063W82"));

    }

}
