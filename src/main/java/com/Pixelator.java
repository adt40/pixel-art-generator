package main.java.com;

import java.awt.*;

class Pixelator {

    private static Color[][] pixeled;

    static Color[][] pixelate(Color[][] rgb, int newPixelSize, int flattenAmount) {
        int width = rgb[0].length;
        int height = rgb.length;
        pixeled = new Color[height][width];
        for (int y = 0; y < height; y+=newPixelSize) {
            for (int x = 0; x < width; x+=newPixelSize) {
                if (x + newPixelSize > width && y + newPixelSize > height) {
                    setAverage(x, y, width, height, newPixelSize, width, height, rgb, flattenAmount);
                } else if (x + newPixelSize > width) {
                    setAverage(x, y, width, height, newPixelSize, width, y + newPixelSize, rgb, flattenAmount);
                } else if (y + newPixelSize > height) {
                    setAverage(x, y, width, height, newPixelSize, x + newPixelSize, height, rgb, flattenAmount);
                } else {
                    setAverage(x, y, width, height, newPixelSize, x + newPixelSize, y + newPixelSize, rgb, flattenAmount);
                }
            }
        }

        return pixeled;
    }

    private static void setAverage(int x, int y, int width, int height, int newPixelSize, int xBound, int yBound, Color[][] rgb, int flattenAmount) {
        int rav = 0, gav = 0, bav = 0;
        for (int sy = y; sy < yBound; sy++) {
            for (int sx = x; sx < xBound; sx++) {
                rav += rgb[sy][sx].getRed();
                gav += rgb[sy][sx].getGreen();
                bav += rgb[sy][sx].getBlue();
            }
        }
        int pixelSizeSquared = newPixelSize * newPixelSize;
        rav /= pixelSizeSquared;
        gav /= pixelSizeSquared;
        bav /= pixelSizeSquared;

        for (int sy = y; sy < yBound; sy++) {
            for (int sx = x; sx < xBound; sx++) {
                pixeled[sy][sx] = getFlatColor(rav, gav, bav, flattenAmount);
            }
        }
    }

    private static Color getFlatColor(int r, int g, int b, int flattenAmount) {
        if (flattenAmount > 0 && flattenAmount <= 7) {
            int roundingFactor = (int)Math.pow(2, flattenAmount + 1);

            int rRound = (int)(((double)r / (double)roundingFactor) + 0.5) * roundingFactor;
            int gRound = (int)(((double)g / (double)roundingFactor) + 0.5) * roundingFactor;
            int bRound = (int)(((double)b / (double)roundingFactor) + 0.5) * roundingFactor;

            if (rRound != 0) {
                rRound--;
            }
            if (gRound != 0) {
                gRound--;
            }
            if (bRound != 0) {
                bRound--;
            }

            return new Color(rRound, gRound, bRound);
        } else {
            return new Color(r, g, b);
        }
    }
}
