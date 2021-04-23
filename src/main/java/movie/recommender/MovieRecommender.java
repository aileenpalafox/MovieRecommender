package movie.recommender;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class MovieRecommender {
    int totalReviews = 0;
    int totalProducts = 0;
    int totalUsers = 0;

    public MovieRecommender(String path) throws IOException {
        //lectura del path local movies.txt.gz
        //extraer archivo gzip
        InputStream gzipStream = new GZIPInputStream(new FileInputStream(path)); //InputStream fileStream = new FileInputStream(filename);
        BufferedReader buffered = new BufferedReader(new InputStreamReader(gzipStream)); //Reader decoder = new InputStreamReader(gzipStream, encoding);

        while (buffered.readLine()!=null){
            System.out.println(buffered.readLine());
        }
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

}
