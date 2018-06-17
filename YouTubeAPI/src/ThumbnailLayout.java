import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

public class ThumbnailLayout extends JPanel implements IView {
    ThumbnailModel Tmodel;
    Model model;
    ThumbImage thumbImage;
    JLabel nameLabel;
    JLabel dateLabel;
    RatingPanel ratePanel;
    JButton unrateButton;
    JPanel infoBox;
    URL videoUrl;

    public ThumbnailLayout(ThumbnailModel thumbnailmodel, Model model) {
        this.model = model;
        infoBox = new JPanel();
        JPanel ratingPanel = new JPanel();
        Box box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalGlue());
        box1.add(Box.createHorizontalGlue());
        infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));
        Tmodel = thumbnailmodel;
        Tmodel.addObserver(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        this.add(Box.createHorizontalGlue());
        this.add(Box.createVerticalGlue());
        thumbImage = new ThumbImage(Tmodel.Image, videoUrl); ///////
        this.add(this.thumbImage);
        thumbImage.setAlignmentY(Component.CENTER_ALIGNMENT);
        ratePanel = new RatingPanel(Tmodel);
        ratePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box1.add(ratePanel);
        infoBox.add(ratePanel);
        unrateButton = new JButton("Reset rating");
        unrateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        unrateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tmodel.setRating(-1);
            }
        });
        box1.add(unrateButton);
        infoBox.add(this.unrateButton);
        nameLabel = new JLabel( Tmodel.getTitle());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box1.add(nameLabel);
        infoBox.add(nameLabel);
        dateLabel = new JLabel( Tmodel.getDate());
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box1.add(dateLabel);
        infoBox.add(dateLabel);
        //ratingPanel.add(box1);
        // this.add(ratingPanel, CENTER_ALIGNMENT);
        this.add(infoBox);
        this.add(Box.createHorizontalGlue());
        this.add(Box.createVerticalGlue());
    }

    public void updateView(EventEnum e) {
        switch (e) {
            case UpdateLayout:
                if (model.listView) {
                    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                } else {
                    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                }

                break;
        }

        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
