package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bitmap {

    private BufferedImage image;
    private String output;

    public Bitmap(String path, String output) {

        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.output = output;

    }

    public void invert() {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getRGB(x, y);
                Color pixelColor = new Color(pixel, false);

                int inverseRed = 255 - pixelColor.getRed();
                int inverseGreen = 255 - pixelColor.getGreen();
                int inverseBlue = 255 - pixelColor.getBlue();

                int[] colorArr = new int[] { inverseRed, inverseGreen, inverseBlue };
                String[] colorHex = new String[colorArr.length];

                for (int i = 0; i < colorArr.length; i++) {
                    if (colorArr[i] < 16) {
                        colorHex[i] = "0" + Integer.toHexString(colorArr[i]);
                    } else {
                        colorHex[i] = Integer.toHexString(colorArr[i]);
                    }

                }

                String hex = colorHex[0] + "" + colorHex[1] + "" + colorHex[2];
                image.setRGB(x, y, Integer.parseInt(hex, 16));
            }

        }

        try {
            File reversedImage = new File(output + "/invertedImage.bmp");
            ImageIO.write(image, "bmp", reversedImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void imageRotate(int angle) {

        try {

            BufferedImage SubImg = rotate(image, angle);
            File outputfile = new File(output + "/rotatedImage.bmp");
            ImageIO.write(SubImg, "bmp", outputfile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void blackAndWhite() {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getRGB(x, y);
                Color pixelColor = new Color(pixel, false);

                int greayScaling = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;

                int[] colorArr = new int[] { greayScaling, greayScaling, greayScaling };
                String[] colorBnW = new String[colorArr.length];

                for (int i = 0; i < colorArr.length; i++) {
                    if (colorArr[i] < 16) {
                        colorBnW[i] = "0" + Integer.toHexString(colorArr[i]);
                    } else {
                        colorBnW[i] = Integer.toHexString(colorArr[i]);
                    }

                }

                String hex = colorBnW[0] + "" + colorBnW[1] + "" + colorBnW[2];
                image.setRGB(x, y, Integer.parseInt(hex, 16));
            }

        }

        try {
            File reversedImage = new File(output + "/greyScaledImage.bmp");
            ImageIO.write(image, "bmp", reversedImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static BufferedImage rotate(BufferedImage img, int angle) {

        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
        g2.drawImage(img, null, 0, 0);

        return newImage;
    }
}