/*
 * ImgProc.java
 * Author:  Joshua Parker
 *
 * An image processing program to manipulate images
 * by changing rgb values and intensities
 */
import java.awt.*;
import javax.swing.*;

public class ImgProc extends JFrame {

  /**
  * Constructs and initializes the Image Processing program
  */
  public ImgProc(String fileName, String title, int x, int y, int width, int height) throws Exception {
    setTitle(title);
    setLocation(x, y);
    Container visibleArea = getContentPane();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(width, height));
     // declare a JPanel variable
    JPanel myJPanel = null;
    myJPanel = new ImgProcJPanel(fileName);
    visibleArea.add(myJPanel);
    myJPanel.requestFocusInWindow();
    pack();
    setVisible(true);
  }

  /**
   *  the main method
   */
  public static void main(String[] args) throws Exception {
    System.out.println("Test for extreme values:");
    PixelColor p1 = new PixelColor(0, 255, 0);
    PixelColor p2 = new PixelColor(300, 0, 0);
    PixelColor p3 = new PixelColor(208, 207, 228);
    System.out.println(p1 + ", " + Integer.toHexString(p1.getRGBIntegerPixel()));
    System.out.println(p2 + ", " + Integer.toHexString(p2.getRGBIntegerPixel()));
    System.out.println(p3 + ", " + Integer.toHexString(p3.getRGBIntegerPixel()));
    System.out.println("DONE-------------------");

    ImgProc imgProcFrame;
    if (args.length>0)
      imgProcFrame = new ImgProc(args[0], "Image Processing Example", 100, 120, 500, 300);
    else imgProcFrame = new ImgProc("731.bmp", "Image Processing Example", 100, 120, 500, 300);
  }
}