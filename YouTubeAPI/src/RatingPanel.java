import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RatingPanel extends JPanel implements IView {
    private ThumbnailModel model;
    private StarButton[] stars = new StarButton[5];

    public RatingPanel(ThumbnailModel model) {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        boolean isfilled = true;
        for (int index = 0; index < 5; ++index) {
            if (model.userRating == -1 || index >= model.userRating) {
                isfilled = false;
            }
            stars[index] = new StarButton(isfilled, index + 1);
            stars[index].setAlignmentX(Box.CENTER_ALIGNMENT);
            stars[index].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int totalRating = ((StarButton)(e.getSource())).Rating;
                    model.setRating(totalRating);
                }
            });
            this.add(stars[index]);
        }
    }
    public void updateView(EventEnum e) {
        switch(e) {
            case UpdateRating:
                if (model.userRating != -1) {
                    for(int index=0; index < model.userRating; ++index) {
                        stars[index].selected = true;
                    }
                    for(int index1 = model.userRating; index1 < 5; ++index1) {
                        stars[index1].selected = false;
                    }
                } else {
                    for(int index2 =0; index2 < 5; ++index2) {
                        stars[index2].selected = false;
                    }
                }
                break;
            case UpdateLayout:
        }
        revalidate();
        repaint();
    }
}
