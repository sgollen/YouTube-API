import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ThumbnailModel {

    public transient ArrayList<IView> observers;
    private String title;
    public Integer yearTime;
    public Integer monthTime;
    public int userRating;
    public transient BufferedImage Image;
    public  static URL videoUrl;
    public transient boolean addedToView;

    public ThumbnailModel() {
        this.userRating = -1;
        this.addedToView = false;
        this.observers = new ArrayList<>();
    }

    public void addObserver(IView view) {
        this.observers.add(view);
    }

    public void setRating(int rating) {
        this.userRating = rating;
        this.notifyViews(EventEnum.UpdateRating);
    }

    public void notifyViews(EventEnum a) {
        for (IView view : this.observers) {
            view.updateView(a);
        }
    }

    public String getDate(){
        //if (yearTime == 0) {
         //   return this.monthTime + "  months ago";
       // } else {
            return this.yearTime + "  year  " + this.monthTime + "  months ago";
        //}
    }
    public URL getVideoUrl() {
        return videoUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public static ThumbnailModel createImageModel(URL myImage, String title,String date ) {
        ThumbnailModel model = new ThumbnailModel();
       // System.out.println("GOT IN THUMBNAIL MDOEL");
        String[] tokens = date.split("-");
        String year = tokens[0];
        String month = tokens[1];
        int updatedyear = Integer.parseInt(year);
        int updatedmonth = Integer.parseInt(month);
        model.yearTime = 2018 - updatedyear;
        model.monthTime = updatedmonth - 6;
        try {
            model.title = title;
            model.Image = ImageIO.read(myImage);
        } catch (MalformedURLException e1) {
            System.out.println("BAD LINK");
            e1.printStackTrace();
        } catch (IOException e1) {
             System.out.println("BAD IMAGE");
        }
        return model;
    }
}
