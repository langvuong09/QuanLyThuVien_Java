package MyCustom;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image img;
    private int width;
    private int height;

    public ImagePanel(String imgPath, int width, int height) {
        this(new ImageIcon(imgPath).getImage(), width, height);
    }

    public ImagePanel(Image img, int width, int height) {
        this.img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.width = width;
        this.height = height;
        Dimension size = new Dimension(width, height);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, width, height, null);
    }
}
