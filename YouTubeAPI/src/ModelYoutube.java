import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.api.client.util.DateTime;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.net.URL;
import java.net.MalformedURLException;

public class ModelYoutube implements Serializable {

    private static long MAX_NUM_RESULTS = 25;  // 25

    private static String apiKey;
    private YouTube youtube;
    private static JFrame myframe;
    public static ArrayList<String> title;
    public static ArrayList<String> videoUrl;
    public  static Model myModel;



    public ModelYoutube(String apiKey, Model model) {
        this.myModel = model;
        this.title = new ArrayList<String>();
        this.videoUrl = new ArrayList<String>();
        this.apiKey = apiKey;

        try {
            YouTube.Builder builder = new YouTube.Builder(
                    new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest httpRequest) throws IOException {
                }
            });
            builder.setApplicationName("a4-youtube");
            youtube = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
            this.throwModelInitializationException();
        }
       // searchVideos("puppies");

    }

    public  void searchVideos(String query) {

        if (youtube == null) {
            this.throwModelInitializationException();
        }

        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");

            search.setKey(this.apiKey);
            search.setQ(query);
            search.setType("video");
            search.setSafeSearch("strict");
            search.setMaxResults(this.MAX_NUM_RESULTS);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> resultsList = searchResponse.getItems();

            if (resultsList != null) {
                Iterator<SearchResult> resultsIterator = resultsList.iterator();

                while (resultsIterator.hasNext()) {
                  //  System.out.println("--------------------------------------------------");      ///////////////

                    SearchResult searchResult = resultsIterator.next();

                    ResourceId rId = searchResult.getId();
                    SearchResultSnippet snippet = searchResult.getSnippet();

                //   System.out.println("Title: " + snippet.getTitle());
                //    System.out.println("video ID: " + rId.getVideoId());
                   //
                   // System.out.println("Raw data:" + searchResult.toString());
                    String myTitle = snippet.getTitle();
                    title.add(myTitle);
                    //String rawData = searchResult.toString();
                    //String[] tokens = rawData.split(",");
                    DateTime pre_date = snippet.getPublishedAt();
                    String rawData = pre_date.toString();
                    //  System.out.println(date);
                    String videoID = rId.getVideoId();
                    String url = "https://i.ytimg.com/vi/" + videoID + "/default.jpg";
                    URL webUrl = new URL (url);
                    createModelYouTube(webUrl,myTitle,rawData);

                  //  System.out.println(videoUrl.size());

                   // System.out.println("--------------------------------------------------");
                }
             //   RootView.mytitle = title;
              //  RootView.myvideoUrl = videoUrl;
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.throwModelInitializationException();
        }

    }

    // helper calls for error reporting and debugging
    private void throwModelInitializationException() {

        throw new RuntimeException("Couldn't initialize the YouTube object. You may want to check your API Key.");

    }

    public static int createModelYouTube(URL myurl, String title, String mydate) {
       // System.out.println("GOT IN YOUTUBE MDOEL");
        myModel.addImage(ThumbnailModel.createImageModel(myurl,title, mydate));
        return 0;
    }
}

