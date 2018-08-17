package main.java.com;

import javax.swing.*;
import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        ImagePreviewPanel imagePreviewPanel = new ImagePreviewPanel();
        ImageSettingsPanel imageSettingsPanel = new ImageSettingsPanel(imagePreviewPanel);
        imageSettingsPanel.setImageRGB(ImageFileIO.getImage("Desert.jpg"));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1800, 900);
        Container pane = frame.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        pane.add(imageSettingsPanel, c);
        c.gridx = 1;
        c.weightx = 1;
        pane.add(imagePreviewPanel, c);
        frame.setVisible(true);
    }
}
