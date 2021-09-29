package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Bitmap {

    private ArrayList<Integer[]> colorList = new ArrayList<Integer[]>();
    private String imageName;
    private String imagePath;


    public Bitmap(String path) {
        this.imagePath = path;
        looper();
    }

//
//    public void reverser(String output){
//
//        File newImage = new File(imagePath);
//        try{
//
//            BufferedImage image = ImageIO.read(newImage);
//
//            for (int y = 0; y < image.getHeight(); y++) {
//                for (int x = 0; x < image.getWidth(); x++) {
//                    int pixel = image.getRGB(x, y);
//                    Color color = new Color(pixel, false);
//                }
//
//            }
//
////            File reversedImage = new File(output);
////            ImageIO.write(image,"reversedImage",reversedImage);
//
//
//        }catch (
//                IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//
//
    private void looper(){

        File newImage = new File(imagePath);
        try{
            BufferedImage image = ImageIO.read(newImage);

            for (int y = 0; y < image.getHeight(); y++) {

                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    Color color = new Color(pixel, true);
                    Integer[] pixelColor = new Integer[]{color.getRed(),color.getGreen(),color.getBlue()};
                    colorList.add(pixelColor);
                }

            }


        }catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

}