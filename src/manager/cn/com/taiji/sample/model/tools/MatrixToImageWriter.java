package cn.com.taiji.sample.model.tools;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;
public class MatrixToImageWriter {
     private static final int BLACK = 0xFF000000;
       private static final int WHITE = 0xFFFFFFFF;
     
       private MatrixToImageWriter() {}
     
       
       public static BufferedImage toBufferedImage(BitMatrix matrix) throws IOException {
         int width = matrix.getWidth();
         int height = matrix.getHeight();
         BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         for (int x = 0; x < width; x++) {
           for (int y = 0; y < height; y++) {
             image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
           }
         }
//         File logoFile=new File("C:\\Users\\admin02\\Desktop\\furen.png");
//         if (Objects.nonNull(logoFile) && logoFile.exists()) {
//             // 构建绘图对象
//             Graphics2D g = image.createGraphics();
//             // 读取Logo图片
//             BufferedImage logo = ImageIO.read(logoFile);
//             // 开始绘制logo图片
//             g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
//             g.dispose();
//             logo.flush();
//         }
         return image;
       }
     
       
       public static void writeToFile(BitMatrix matrix, String format, File file)
           throws IOException {
         BufferedImage image = toBufferedImage(matrix);
         if (!ImageIO.write(image, format, file)) {
           throw new IOException("Could not write an image of format " + format + " to " + file);
         }
       }
     
       
       public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
           throws IOException {
         BufferedImage image = toBufferedImage(matrix);
         if (!ImageIO.write(image, format, stream)) {
           throw new IOException("Could not write an image of format " + format);
         }
       }
}