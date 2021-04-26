package movie.recommender;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class extract {

    private int totalReviews = 0; //privat evita que se acceda a ellsa de otras partes

    public extract(String path) throws IOException {
        //decodes gzip file
        InputStream gzipStream = new GZIPInputStream(new FileInputStream(path)); //InputStream fileStream = new FileInputStream(filename);
        BufferedReader buffered = new BufferedReader(new InputStreamReader(gzipStream)); //Reader decoder = new InputStreamReader(gzipStream, encoding);

        String readingLine;
        //number of reviews to read and write
        int p=3;

        FileWriter myWriter = new FileWriter("/Users/aileen.palafox/Documents/moviestest.txt");

        while (p!=0){
            readingLine=buffered.readLine();
            //uses the word "review/text" as identifier for the end of each review
            if(readingLine.startsWith("review/text")){
                this.totalReviews++;
                System.out.println(p);
                p--;
            }

            try {
                myWriter.write(readingLine+"\n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        myWriter.close();
        System.out.println("total reviews "+totalReviews);
    }

}
