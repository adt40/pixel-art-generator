package main.java.com;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ImageSettingsPanel extends JPanel implements ChangeListener, ActionListener {

    private static final int MIN_PIXEL_SIZE = 1;
    private static final int MAX_PIXEL_SIZE = 16;
    private static final int MIN_FLATTEN = 0;
    private static final int MAX_FLATTEN = 7;

    private JSlider pixelSizeSlider;
    private JSlider flattenAmountSlider;
    private ImagePreviewPanel imagePreviewPanel;

    private int pixelSize;
    private int flattenAmount;

    private Color[][] rgb;

    ImageSettingsPanel(ImagePreviewPanel imagePreviewPanel) {
        this.imagePreviewPanel = imagePreviewPanel;
        pixelSize = MIN_PIXEL_SIZE;
        flattenAmount = MIN_FLATTEN;

        setLayout(new GridLayout(16, 1));

        JLabel pixelLabel = new JLabel();
        pixelLabel.setText("Pixel Size");
        add(pixelLabel);

        pixelSizeSlider = new JSlider(JSlider.HORIZONTAL, MIN_PIXEL_SIZE, MAX_PIXEL_SIZE, MIN_PIXEL_SIZE);
        pixelSizeSlider.addChangeListener(this);
        pixelSizeSlider.setMajorTickSpacing(4);
        pixelSizeSlider.setMinorTickSpacing(1);
        pixelSizeSlider.setPaintTicks(true);
        pixelSizeSlider.setSnapToTicks(true);
        add(pixelSizeSlider);

        add(new JLabel());

        JLabel flattenLabel = new JLabel();
        flattenLabel.setText("Flatten Amount");
        add(flattenLabel);

        flattenAmountSlider = new JSlider(JSlider.HORIZONTAL, MIN_FLATTEN, MAX_FLATTEN, MIN_FLATTEN);
        flattenAmountSlider.addChangeListener(this);
        flattenAmountSlider.setMajorTickSpacing(4);
        flattenAmountSlider.setMinorTickSpacing(1);
        flattenAmountSlider.setPaintTicks(true);
        flattenAmountSlider.setSnapToTicks(true);
        add(flattenAmountSlider);

        add(new JLabel());

        JButton saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        add(saveButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().compareTo("save") == 0) {
            Color[][] pixelated = Pixelator.pixelate(rgb, pixelSize, flattenAmount);
            ImageFileIO.saveImage(pixelated, "pixel.jpg");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == pixelSizeSlider) {
            pixelSize = pixelSizeSlider.getValue();
            updateImage();
        } else if (e.getSource() == flattenAmountSlider) {
            flattenAmount = flattenAmountSlider.getValue();
            updateImage();
        }
    }

    void setImageRGB(Color[][] rgb) {
        this.rgb = rgb;
        updateImage();
    }


    private void updateImage() {
        Color[][] pixelated = Pixelator.pixelate(rgb, pixelSize, flattenAmount);
        BufferedImage img = ImageFileIO.convertArrToImage(pixelated);
        imagePreviewPanel.setImage(img);
    }
}
