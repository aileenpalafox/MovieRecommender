package movie.recommender;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MovieRecommender {
    private int totalReviews = 0;
    private int totalProducts = 0;
    private int totalUsers = 0;
    private int idproduct = 0;
    private int iduser = 0;
    private String idproductline = null;
    private String iduserline = null;
    private String score;
    private List<String> RecommendationsForUser;
    Hashtable<String, Integer> productsIds = new Hashtable<String, Integer>();
    Hashtable<String, Integer> usersIds = new Hashtable<String, Integer>();

    public MovieRecommender(String path) throws IOException {
        //decodes gzip file
        InputStream gzipStream = new GZIPInputStream(new FileInputStream(path)); //InputStream fileStream = new FileInputStream(filename);
        BufferedReader buffered = new BufferedReader(new InputStreamReader(gzipStream)); //Reader decoder = new InputStreamReader(gzipStream, encoding)
        String readingLine;
        FileWriter writer = new FileWriter("/Users/aileen.palafox/Documents/movies.csv");

        while ((readingLine = buffered.readLine()) != null) { //
            //uses the word "product" as identifier for the beginning of each review
            if (readingLine.startsWith("product/productId")) {
                totalReviews++;
                System.out.println(totalReviews);
                idproductline = readingLine.split(" ")[1];
                //stores unique products ids in arraylist
                if (!productsIds.contains(idproductline)) {
                    totalProducts++;
                    productsIds.put(idproductline, totalProducts);
                    idproduct = totalProducts;
                } else {
                    idproduct = productsIds.get(idproductline);
                }
            }
            if (readingLine.startsWith("review/userId")) {
                iduserline = readingLine.split(" ")[1];
                //stores unique users ids in arraylist
                if (!usersIds.contains(iduserline)) {
                    totalUsers++;
                    usersIds.put(iduserline, totalUsers);
                    iduser = totalUsers;
                } else {
                    iduser = usersIds.get(iduserline);
                }
            }
            if (readingLine.startsWith("review/score")) {
                score = readingLine.split(" ")[1];
                writer.write(iduser + "," + idproduct + "," + score + "\n");
            }

        }
        buffered.close();
        gzipStream.close();
        writer.close();

        System.out.println("total reviews " + totalReviews);
        System.out.println("total products " + productsIds.size());
        System.out.println("total users " + usersIds.size());
    }

    /**
     * Based on the given files count the total of reviews by the users.
     * @return Total reviews
     */
    public int getTotalReviews() {
        return totalReviews;
    }

    /**
     * Based on the given file counts the number of different products
     * @return Total products
     */
    public int getTotalProducts() {
        return totalProducts;
    }

    /**
     * Based on the given file counts the number of different users
     * @return Total users
     */
    public int getTotalUsers() {
        return totalUsers;
    }

    /**
     * Given an user returns 3 movies recommendations based on they similarity with other users
     * @param userId
     * @return A list of recommendations for the user
     */
    public List<String> getRecommendationsForUser(String userId) {
        try {
            int key = usersIds.get(userId);
            DataModel model = new FileDataModel(new File("/Users/aileen.palafox/Documents/movies.csv"));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            List<RecommendedItem> recommendations = recommender.recommend(key, 3);
            for (RecommendedItem recommendation : recommendations) {
                RecommendationsForUser.add(recommendation.toString());
                System.out.println("recommendations " + recommendation);
            }
        } catch (TasteException | IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Recommendations " + RecommendationsForUser);
        return RecommendationsForUser;
    }

}