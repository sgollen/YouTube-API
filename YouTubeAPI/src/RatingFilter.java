import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RatingFilter extends JPanel implements IView {
    StarButton[] stars = new StarButton[5];
    Model model;

    public RatingFilter(Model model) {
        this.model = model;
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.model.addObserver(this);
        for (int index = 0; index < 5; ++index) {
            if (index==0) {
                stars[index] = new StarButton(true, index + 1);
            } else {
                stars[index] = new StarButton(false, index + 1);
            }
            stars[index].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int val = ((StarButton) (e.getSource())).Rating;
                    model.setCurrentFilter(val);
                }
            });
            this.add(stars[index]);
        }
    }
    public void updateView(EventEnum e) {
        switch (e) {
            case NewFilter:
                for (int index1 = 0; index1 < model.currentFilterValue; ++index1) {
                    stars[index1].selected = true;
                }
                for (int index2 = model.currentFilterValue; index2 < 5; ++index2) {
                    stars[index2].selected = false;
                }
            case UpdateLayout:
        }
        revalidate();
        repaint();
    }
}
