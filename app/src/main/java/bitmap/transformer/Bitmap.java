package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Bitmap {

    private ArrayList<Integer[]> colorList = new ArrayList<Integer[]>();
    private BufferedImage image;


    public Bitmap(String path) {

        try {
            this.image = ImageIO.read(new File(path));
            looper();
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void invert(String output) {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                Color pixelColor = new Color(pixel, false);

                int inverseRed = 255 - pixelColor.getRed();
                int inverseGreen = 255 - pixelColor.getGreen();
                int inverseBlue = 255 - pixelColor.getBlue();

                int[] colorArr = new int[]{inverseRed, inverseGreen, inverseBlue};
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
            File reversedImage = new File(output);
            ImageIO.write(image, "bmp", reversedImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


    private void looper() {

        for (int y = 0; y < image.getHeight(); y++) {

            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel, true);
                Integer[] pixelColor = new Integer[]{color.getRed(), color.getGreen(), color.getBlue()};
                colorList.add(pixelColor);
            }
        }
    }


    public void imageRotate(String output, int angle) {

        try {

            BufferedImage SubImg = rotate(image, angle);
            File outputfile = new File(output);
            ImageIO.write(SubImg, "bmp", outputfile);
            System.out.println(
                    "Image rotated successfully: "
                            + outputfile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage rotate(BufferedImage img, int angle) {

        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
        g2.drawImage(img, null, 0, 0);


        return newImage;
    }
}