package main.java.com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageFileIO {

    static Color[][] getImage(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            return convertImageToArr(img);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    static void saveImage(Color[][] rgb, String filename) {
        BufferedImage img = convertArrToImage(rgb);
        try {
            File outputFile = new File(filename);
            ImageIO.write(img, filename.substring(filename.length() - 3), outputFile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static Color[][] convertImageToArr(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        Color[][] rgb = new Color[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rgb[y][x] = new Color(img.getRGB(x, y));
            }
        }
        return rgb;
    }

    static BufferedImage convertArrToImage(Color[][] rgb) {
        int width = rgb[0].length;
        int height = rgb.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img.setRGB(x, y, rgb[y][x].getRGB());
            }
        }
        return img;
    }
}
