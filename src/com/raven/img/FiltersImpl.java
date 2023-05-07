package com.raven.img;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FiltersImpl extends UnicastRemoteObject implements Filters {

    public static double[][] kernel_moy = {{1 / 9f, 1 / 9f, 1 / 9f}, {1 / 9f, 1 / 9f, 1 / 9f}, {1 / 9f, 1 / 9f, 1 / 9f}};
    public static double[][] kernel_sbl1 = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
    public static double[][] kernel_sbl2 = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
    public static double[][] kernel_blr = {{1 / 16f, 2 / 16f, 1 / 16f}, {2 / 16f, 4 / 16f, 2 / 16f}, {1 / 16f, 2 / 16f, 1 / 16f}};
    public static double[][] kernel_plc = {{1, 1, 1}, {1, 8, 1}, {1, 1, 1}};
    public static double[][] kernel_oln = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    public static double[][] kernel_ved = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}};
    public static double[][] kernel_hed = {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};

    public FiltersImpl() throws RemoteException{
    };

    @Override
    public byte[] filterImage(byte[] imageByte, int kernel) throws RemoteException {

        try {
            BufferedImage image = null;
            InputStream is = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(is);

            double[][] ker = switch (kernel) {
                case 1 -> kernel_moy;
                case 2 -> kernel_sbl1;
                case 3 -> kernel_sbl2;
                case 4 -> kernel_blr;
                case 5 -> kernel_plc;
                case 6 -> kernel_oln;
                case 7 -> kernel_ved;
                case 8 -> kernel_hed;
                default -> null;
            };

            int[][] og_image = ImageToGray(image);

            int[][] res_image = convolution2D(og_image,ker);


            ByteArrayOutputStream os = new ByteArrayOutputStream();

            BufferedImage image1 = GrayToBufferedImage(res_image);

            ImageIO.write(image1,"jpg",os);

            imageByte = os.toByteArray();

        }catch(IOException e){
            e.printStackTrace();
        }
        return imageByte;
    }

    public static int MultiplicationPixel(int[][] image, double[][] kernel, int x, int y) {
        double result = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                result += image[x + i][y + j] * kernel[i + 1][j + 1];
            }
        }
        return (int) Math.round(result);
    }

    public static int[][] convolution2D(int[][] image, double[][] kernel) {
        int width = image.length;
        int height = image[0].length;
        int[][] output = new int[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
                    output[i][j] = image[i][j];
                } else {
                    output[i][j] = (int) Math.round(MultiplicationPixel(image, kernel, i, j));
                }
            }
        }
        return output;
    }

    public static int[][] ImageToGray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int pixel;
        int[][] result = new int[width][height];
        for(int i = 0;i<width;i++){
            for(int j = 0;j<height;j++){
                pixel = image.getRGB(i,j);
                result[i][j]= (int) Math.round(0.2989 * ((pixel & 0xff0000) >> 16) + 0.5870 * (pixel & 0xff)+ 0.1140 * ((pixel & 0xff00) >> 8));
            }
        }
        return result;
    }

    public static Color GrayToRgb(int gvalue) {
        int rgb = gvalue << 16 | gvalue << 8 | gvalue;
        Color value = new Color(rgb);
        return value;
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


}
