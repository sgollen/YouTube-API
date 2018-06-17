import javax.swing.*;
import java.awt.*;

public class StarButton extends JButton {
    Icon starFilled = new ImageIcon("star_Filled.png");
    Icon starEmpty = new ImageIcon("star_Empty.png");
    public boolean selected;
    public int Rating;
    
    public StarButton(boolean selected, int givenRating) {
        this.Rating = givenRating;
        this.selected = selected;
        if (selected) {
            this.setIcon(starFilled);
        } else {
            this.setIcon(starEmpty);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.selected) {
            this.setIcon(starFilled);
        } else {
            this.setIcon(starEmpty);
        }
        this.revalidate();
    }
}
