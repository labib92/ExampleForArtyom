import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    private static int getRed(int pixel){
        return (pixel >> 16) & 0xff;
    }

    private static int getGreen(int pixel){
        return (pixel >> 8) & 0xff;
    }

    private static int getBlue(int pixel){
        return (pixel) & 0xff;
    }


    public static void main(String[] args) {

        Set<String> textNoDuplicate = new LinkedHashSet<String>();
        BufferedImage image1;
        PrintWriter out;
        try {
            image1 = ImageIO.read(new File("C:\\Users\\Labib\\Desktop\\edges\\bubbles\\bubble 16\\1.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        int height = image1.getHeight();
        int width = image1.getWidth();
        System.out.println("All pixels = " + height * width);
        System.out.println();
        int pixel;
        int pixel2;
        int count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = image1.getRGB(x, y);
                int red = getRed(pixel);
                int green = getGreen(pixel);
                int blue = getBlue(pixel);
                for(int i = 0; i < height; i++){
                    for(int j = 0;j < width; j++){
                        pixel2 = image1.getRGB(j,i);
                        int red2 = getRed(pixel2);
                        int green2 = getGreen(pixel2);
                        int blue2 = getBlue(pixel2);
                        if(red2 == red && green2 == green && blue2 == blue){
                            count++;
                        }
                    }
                }
                //String text = "Pixel: ("+ x +", "+ y+") | Red :" + red + " | Green :" + green + " | Blue :" + blue+"\n";
                String text = "(Red: "+red+" , Green: "+green+" , Blue: "+blue+") = "+count;
                textNoDuplicate.add(text);
                count = 0;
            }
        }

        try {
            out = new PrintWriter("C:\\Users\\Labib\\Desktop\\exampe.txt");
            for(String s:textNoDuplicate){
                out.println(s);
            }
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}