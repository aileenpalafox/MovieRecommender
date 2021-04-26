package movie.recommender;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class MovieRecommender {
    private int totalReviews = 0;
    private int totalProducts = 0;
    private int totalUsers = 0;
    ArrayList<String> productsIds = new ArrayList<String>();
    ArrayList<String> usersIds = new ArrayList<String>();

    public MovieRecommender(String path) throws IOException {
        //decodes gzip file
        InputStream gzipStream = new GZIPInputStream(new FileInputStream(path)); //InputStream fileStream = new FileInputStream(filename);
        BufferedReader buffered = new BufferedReader(new InputStreamReader(gzipStream)); //Reader decoder = new InputStreamReader(gzipStream, encoding)
        String readingLine;
        String productId; //mas descriptivo
        String userId;

        while ((readingLine=buffered.readLine())!=null){ //
            //uses the word "product" as identifier for the beginning of each review
           if(readingLine.startsWith("product")){
               this.totalReviews++;

              //stores unique products ids in arraylist
               productId = readingLine.split(" ")[1];
               if (!this.productsIds.contains(productId)){
                   this.productsIds.add(productId);
               }
           }

           if(readingLine.startsWith("review/userId")){
               //stores unique users ids in arraylist
               userId = readingLine.split(" ")[1];
               if (!usersIds.contains(userId)){
                   usersIds.add(userId);
               }
           }
        }

        System.out.println("total reviews "+totalReviews);
        System.out.println("total products "+productsIds.size());
        System.out.println("total users "+usersIds.size());
    }

    public int getTotalReviews() {
        return this.totalReviews;
    }

    public int getTotalProducts() {
        return this.totalProducts=productsIds.size();
    }

    public int getTotalUsers() {
        return totalUsers=usersIds.size();
    }

}