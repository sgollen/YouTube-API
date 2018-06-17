import java.util.ArrayList;

public class Model {
    public ArrayList<ThumbnailModel> ThumbnailList;
    private transient ArrayList<IView> observers;
    public boolean listView;
    public int frameWidth;
    public transient int currentFilterValue;
    public transient boolean isFilterEnabled;
    public String videoTitle;

    public Model() {
        this.ThumbnailList = new ArrayList<>();
        this.frameWidth = 700;
        this.observers = new ArrayList<>();
        this.listView = false;
        this.currentFilterValue = 1;
        this.isFilterEnabled = false;
    }

    public int getFilteredImagesCount() {
        int filter = 0;
        if (this.isFilterEnabled) {
            for (ThumbnailModel im:this.ThumbnailList) {
                if (im.userRating >= this.currentFilterValue) {
                    filter++;
                }
            }
        } else {
            filter = this.ThumbnailList.size();
        }

        return filter;
    }

    public void updateTitle(String title) {
        this.videoTitle = title;
    }

    public String getTitle() {
        return this.videoTitle;
    }

    public void addObserver(IView view) {
        this.observers.add(view);
    }

    public void notifyViews(EventEnum a) {
        for (IView view : this.observers) {
            view.updateView(a);
        }
    }

    public int changeColumns() {
        if (this.listView) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setCurrentFilter(int index) {
        this.currentFilterValue = index;
        this.notifyViews(EventEnum.NewFilter);
    }

    public void filterVideos() {
        this.isFilterEnabled = !this.isFilterEnabled;
        this.notifyViews(EventEnum.FilterVideos);
    }

    public void resizeFrame(int w) {
        this.frameWidth = w;
        this.notifyViews(EventEnum.UpdateSize);
    }

    public void setListView(boolean b) {
        listView = b;
        this.notifyViews(EventEnum.UpdateLayout);
        for (ThumbnailModel im:this.ThumbnailList) {
            im.notifyViews(EventEnum.UpdateLayout);
        }
    }

   public void addImage(ThumbnailModel image) {
       ThumbnailList.add(image);
       this.notifyViews(EventEnum.ImageUpdate);
   }


}
