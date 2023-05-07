package com.raven.img;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClientTools extends javax.swing.JFrame{

    public static int[][] ImageToMatrix(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth(), height = image.getHeight();
        int[][] result = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = image.getRGB(i, j);
            }
        }
        return result;
    }

    public static int[] RgbToArray(int pixel) {
        int blue = pixel & 0xff;
        int green = (pixel & 0xff00) >> 8;
        int red = (pixel & 0xff0000) >> 16;
        int[] result = new int[3];
        result[0] = red;
        result[1] = green;
        result[2] = blue;
        return result;
    }

    public static int RgbArrayToGray(int[] pixel) {
        return (int) Math.round(0.2989 * pixel[0] + 0.5870 * pixel[1] + 0.1140 * pixel[2]);
    }

    public static Color GrayToRgb(int gvalue) {
        int rgb = gvalue << 16 | gvalue << 8 | gvalue;
        Color value = new Color(rgb);
        return value;
    }

    public static int[][] ImageToGray(int[][] image) {
        int width = image.length;
        int height = image[0].length;
        int[][] resultat = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                resultat[i][j] = RgbArrayToGray(RgbToArray(image[i][j]));
            }
        }
        return resultat;
    }

    public static BufferedImage GrayToBufferedImage(int[][] imageg) {
        BufferedImage image = new BufferedImage(imageg.length, imageg[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < imageg.length; i++) {
            for (int j = 0; j < imageg[0].length; j++) {

                image.setRGB(i, j, GrayToRgb(imageg[i][j]).getRGB());
            }
        }
        return image;
    }

    public static String removeLastLine(String text){
        while(text.charAt(text.length() - 1)!='\n'){
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    public static String printProgress(String state,int progress){
        String text = state;
        int i;
        text += "| ";
        for(i = 0;i<progress;i++){
            text += "█";
        }
        for(int j=0;j<(20-progress);j++){
            text += "░";
        }
        text += " |";
        return text;
    }

    public static void SaveImage(BufferedImage image,String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path+".png"));
        System.out.println("done ☺♥☻ !");
    }
}
