package main.java.com;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ImagePreviewPanel extends JPanel {

    private BufferedImage img;
    private JLabel imageLabel;

    public ImagePreviewPanel() {
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);
    }

    public void updateImage() {
        remove(imageLabel);
        imageLabel = new JLabel(new ImageIcon(img));
        add(imageLabel, BorderLayout.CENTER);
        revalidate();
    }

    public void setImage(BufferedImage img) {
        this.img = img;
        updateImage();
    }

}
