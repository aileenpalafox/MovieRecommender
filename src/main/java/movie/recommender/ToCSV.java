package movie.recommender;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class ToCSV {
    public ToCSV(String path) throws IOException {
        InputStream gzipStream = new GZIPInputStream(new FileInputStream(path)); //InputStream fileStream = new FileInputStream(filename);
        BufferedReader buffered = new BufferedReader(new InputStreamReader(gzipStream)); //Reader decoder = new InputStreamReader(gzipStream, encoding)
        String readingLine;
        StringBuilder row = new StringBuilder();

        try {
            PrintWriter writer = new PrintWriter(new File("/Users/aileen.palafox/Documents/movies.csv")); //65535 rows
            while ((readingLine=buffered.readLine())!=null){
                //if line is empty read next line
                if (readingLine.length()==0){
                    readingLine= buffered.readLine();
                }
                //if line has userId or productId or score add to row
                if(readingLine.matches("(review/userId|product/productId|review/score).*")){
                    row.append((readingLine.substring((readingLine.indexOf(" "))+1))+",");
                    //if is the last item of the row format and write in csv
                    if (readingLine.startsWith("review/score")){
                        row.delete(row.length()-1,row.length());
                        row.append('\n');
                        writer.write(row.toString());
                        //empty row
                        row.delete(0,row.length());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}