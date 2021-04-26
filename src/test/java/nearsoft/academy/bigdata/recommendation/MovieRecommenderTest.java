package nearsoft.academy.bigdata.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

//importar clase con el codigo funcional, es necesario tenerla dentro de un package para que Maven la detecte
import movie.recommender.MovieRecommender;
import movie.recommender.extract;

public class MovieRecommenderTest {
    @Test
    public void testDataInfo() throws IOException, TasteException {
        //download moviestest.gz from
        //    http://snap.stanford.edu/data/web-Movies.html

        //MovieRecommender recommender = new MovieRecommender("/Users/aileen.palafox/Documents/movies.txt.gz");
        MovieRecommender recommender = new MovieRecommender("/Users/aileen.palafox/Documents/academy-exercises/src/main/resources/moviestest2.txt.gz");
        assertEquals(7911684, recommender.getTotalReviews());
        assertEquals(253059, recommender.getTotalProducts());
        assertEquals(889176, recommender.getTotalUsers());

        //List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR"); //usar mahout
        //assertThat(recommendations, hasItem("B0002O7Y8U")); //dejar comentado
        //assertThat(recommendations, hasItem("B00004CQTF"));
        //assertThat(recommendations, hasItem("B000063W82"));

    }

}
