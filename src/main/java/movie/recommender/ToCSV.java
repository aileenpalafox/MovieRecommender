package movie.recommender;


import java.io.*;
import java.util.zip.GZIPInputStream;

public class ToCSV {
    public ToCSV(String path) throws IOException {
        InputStream gzipStream = new GZIPInputStream(new FileInputStream(path)); //InputStream fileStream = new FileInputStream(filename);
        BufferedReader buffered = new BufferedReader(new InputStreamReader(gzipStream)); //Reader decoder = new InputStreamReader(gzipStream, encoding)
        String readingLine;
        StringBuilder sb = new StringBuilder();

        try {
            PrintWriter writer = new PrintWriter(new File("/Users/aileen.palafox/Documents/moviestest2.csv"));

            while ((readingLine=buffered.readLine())!=null){
                if(readingLine.startsWith("review/text")){
                    sb.append(readingLine.substring((readingLine.indexOf(" "))+1));
                    sb.append('\n');
                    writer.write(sb.toString());
                    System.out.println(sb.toString());
                }
                sb.append((readingLine.substring((readingLine.indexOf(" "))+1))+",");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
