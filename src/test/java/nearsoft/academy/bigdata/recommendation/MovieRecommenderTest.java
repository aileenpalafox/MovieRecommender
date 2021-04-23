package nearsoft.academy.bigdata.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

//importar clase con el codigo funcional, es necesario tenerla dentro de un package para que Maven la detecte
import movie.recommender.MovieRecommender;

public class MovieRecommenderTest {
    @Test
    public void testDataInfo() throws IOException, TasteException {
        //download movies.txt.gz from 
        //    http://snap.stanford.edu/data/web-Movies.html
        MovieRecommender recommender = new MovieRecommender("/Users/aileen.palafox/Documents/academy-exercises/src/main/java/movie/recommender/movies.txt.gz");
        assertEquals(7911684, recommender.getTotalReviews()); //atributos de la clase principal
        assertEquals(253059, recommender.getTotalProducts());
        assertEquals(889176, recommender.getTotalUsers());

        //List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR"); //usar mahout
        //assertThat(recommendations, hasItem("B0002O7Y8U"));
        //assertThat(recommendations, hasItem("B00004CQTF"));
        //assertThat(recommendations, hasItem("B000063W82"));

    }

}
