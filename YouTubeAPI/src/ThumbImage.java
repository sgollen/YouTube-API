import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;

public class ThumbImage extends JPanel {
    private BufferedImage image;
    public URL videoLink;
    public ThumbImage(BufferedImage image, URL link) {
        videoLink = link;
        this.image = image;
        Dimension myDim = new Dimension(200, 200);
        double Imageratio = Math.min(myDim.getWidth() / image.getWidth(), myDim.getHeight() / image.getHeight());
        Dimension newDimen = new Dimension((int) (Imageratio * image.getWidth()), (int) (Imageratio * image.getHeight()));
        Dimension dimensions = newDimen;
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setMinimumSize(dimensions);
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        this.revalidate();
    }
}
