import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanelDisplay extends JPanel implements IView {
    Model model;
    ArrayList<ThumbnailLayout> gridThumbnails;
    ArrayList<ThumbnailModel> Thumbnails;

    public GridPanelDisplay(Model model) {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(new GridLayout(0, 2));
        this.gridThumbnails = new ArrayList<>();
        this.Thumbnails = new ArrayList<>();
        updateDisplay();
    }

    public void updateDisplay() {
        this.Thumbnails = new ArrayList<>();
        if (model.isFilterEnabled == false) {
            Thumbnails = model.ThumbnailList;
        } else {
            for (ThumbnailModel imageModel : model.ThumbnailList) {
                if (imageModel.userRating >= model.currentFilterValue) {
                    this.Thumbnails.add(imageModel);
                }
            }
        }

    }
    public void updateView(EventEnum e) {
        switch (e) {
            case UpdateLayout:
                this.setLayout(new GridLayout(0, model.changeColumns()));
                break;
            case ImageUpdate:
                updateDisplay();
                this.setLayout(new GridLayout(0, 2));
                break;
            case UpdateSize:
                this.setLayout(new GridLayout(0,model.changeColumns()));
                break;
            case FilterVideos:
                this.removeAll();
                this.gridThumbnails = new ArrayList<>();
                for (ThumbnailModel imageModel: Thumbnails) {
                    imageModel.addedToView = false;
                }
                updateDisplay();
                this.setLayout(new GridLayout(0, model.changeColumns()));
                break;
        }

        revalidate();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ThumbnailModel Tmodel:Thumbnails) {
            if (!Tmodel.addedToView && Tmodel.Image != null) {
                ThumbnailLayout gridThump = new ThumbnailLayout(Tmodel, model);
                gridThumbnails.add(gridThump);
                this.add(gridThump);
                Tmodel.addedToView = true;
            }
        }
        this.setLayout(new GridLayout(0, this.model.changeColumns()));
        revalidate();
    }
}
