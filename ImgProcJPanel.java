/*
 * ImgProcJPanel.java
 * Author:  Joshua Parker
 *
 * Main JPanel for program
 */

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImgProcJPanel extends JPanel  implements ActionListener {
  /**
  * A BufferedImage object
  */
  private BufferedImage bi = null; // the image buffer

  /**
  * A 2-D array of PixelColor objects in the default RGB color model
  */
  private PixelColor[][] pixels2D = null;

  /**
  * Buttons and TextFields
  */
  private JTextField filenameTextField, intensityTextField;
  private JButton invertButton;
  private JCheckBox redJCheckBox, greenJCheckBox, blueJCheckBox;

  /**
  * Values to compare to for text fields
  */
  private String original, file;
  private int startIntensity;

  /**
  * Constructs and initializes GUI components
  * @param fileName,  the fileName of an image
  */
  public ImgProcJPanel(String fileName) throws Exception {
	setLayout(new BorderLayout());

    // create the top panel
    JPanel topJPanel = new JPanel();
	topJPanel.add(new JLabel("Filename:"));

	filenameTextField = new JTextField(fileName, 20);
	filenameTextField.addActionListener(this);

	topJPanel.add(filenameTextField);

    add(topJPanel, BorderLayout.NORTH);

    // create the bottom panel
    JPanel bottomPanel = new JPanel();

	redJCheckBox = new JCheckBox("Red", false);
    greenJCheckBox = new JCheckBox("Green", false);
	blueJCheckBox = new JCheckBox("Blue", false);

	invertButton = new JButton("Invert");
	intensityTextField = new JTextField("10", 5);

    redJCheckBox.addActionListener(this);
	greenJCheckBox.addActionListener(this);
	blueJCheckBox.addActionListener(this);
	invertButton.addActionListener(this);
	intensityTextField.addActionListener(this);

	bottomPanel.add(redJCheckBox, BorderLayout.SOUTH);
    bottomPanel.add(greenJCheckBox, BorderLayout.SOUTH);
	bottomPanel.add(blueJCheckBox, BorderLayout.SOUTH);
 	bottomPanel.add(invertButton, BorderLayout.SOUTH);

	bottomPanel.add(new JLabel("Adjust Intensity by:"));
	bottomPanel.add(intensityTextField);

    add(bottomPanel, BorderLayout.SOUTH);

    // read the image file
    readImage(fileName);
  }

  /**
  * Draws the BufferedImage in the centre of the program
  * @param       g, the graphics object
  */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (bi!= null) {
      int marginWidth = getWidth();
      int marginHeight = getHeight();
      int w = bi.getWidth();
      int h = bi.getHeight();
      g.drawImage(bi, (marginWidth-w)/2, (marginHeight-h)/2, this);
    }
  }

  /**
  * Reads An array of integer pixels in the RGB colour model
  * @param fileName,  the fileName of an image
  */
  private void readImage(String fileName) {
    try{
      bi = ImageIO.read(new File(fileName)); //read the bmp image file
      //get RGB values
      int w = bi.getWidth(); //numOfCols
      int h = bi.getHeight(); //numOfRows

	  int[] pixels = new int[h * w];
	  pixels = bi.getRGB(0,0,w,h,pixels,0,w);
	  readRGB2DArray(pixels, h, w);

	  int x = pixels2D[0].length -1;
	  int y = pixels2D.length -1;
	  System.out.println("\nCurrent image values:");
	  System.out.println(pixels2D[0][0] + ", " +
	  Integer.toHexString(pixels2D[0][0].getRGBIntegerPixel()));
	  System.out.println(pixels2D[y/2][x] + ", " +
	  Integer.toHexString(pixels2D[y/2][x].getRGBIntegerPixel()));
	  System.out.println(pixels2D[y][x] + ", " +
	  Integer.toHexString(pixels2D[y][x].getRGBIntegerPixel()));
	  System.out.println("DONE-------------------");

    } catch(Exception ex){
      System.out.println("Error loading the file");
    }
  }

  /**
  * Reads 2-D array of PixelColor objects from the array of integer pixels in the RGB colour model
  * @param pixels, the 1-d array of integer pixels
  * @param numOfRows, the number of rows of the image
  * @param numOfCols, the number of columns of the image
  */
  private void readRGB2DArray(int[] pixels, int numOfRows, int numOfCols){
    try{
	  pixels2D = new PixelColor[numOfRows][numOfCols];
	  int startX = 0;
	  int startY = 0;
	  int offset = 0;
	  int scansize = numOfCols;
	  int rgbValue;

	  for(int x = 0; x < numOfRows; x++)
	    for(int y = 0; y < numOfCols; y++) {
		  rgbValue = pixels[offset + (x - startX) * scansize + (y - startY)];
		  pixels2D[x][y] = new PixelColor(rgbValue);
		}
	
	} catch(ArrayIndexOutOfBoundsException e){
	  System.out.println("Array error has occurred");
	}
 }

  /**
  *  Handles button presses by the user
  * @param e, the ActionEvent object
  */
  public void actionPerformed(ActionEvent e)  {
    try {
      Object source = e.getSource();
	  if (source == invertButton) {
	    changeImage(false, 0);

		int x = pixels2D[0].length -1;
	    int y = pixels2D.length -1;
	    System.out.println("\nInvert change:");
	    System.out.println(pixels2D[0][0] + ", " +
	    Integer.toHexString(pixels2D[0][0].getRGBIntegerPixel()));
	    System.out.println(pixels2D[y/2][x] + ", " +
	    Integer.toHexString(pixels2D[y/2][x].getRGBIntegerPixel()));
 	    System.out.println(pixels2D[y][x] + ", " +
	    Integer.toHexString(pixels2D[y][x].getRGBIntegerPixel()));
	    System.out.println("DONE-------------------");
	  }

	  if(source == filenameTextField){
	    String file = filenameTextField.getText();
  	    bi = ImageIO.read(new File(file));
	    readImage(file);
	  }

	  if(source == intensityTextField){
		changeImage(true, Integer.parseInt(intensityTextField.getText()));

		int x = pixels2D[0].length -1;
	    int y = pixels2D.length -1;
	    System.out.println("\nIntensity Change:");
	    System.out.println(pixels2D[0][0] + ", " +
	    Integer.toHexString(pixels2D[0][0].getRGBIntegerPixel()));
	    System.out.println(pixels2D[y/2][x] + ", " +
	    Integer.toHexString(pixels2D[y/2][x].getRGBIntegerPixel()));
 	    System.out.println(pixels2D[y][x] + ", " +
	    Integer.toHexString(pixels2D[y][x].getRGBIntegerPixel()));
	    System.out.println("DONE-------------------");
	  }

      repaint();
    } catch(NullPointerException ex){
      System.out.println("No image buffered");
	} catch(NumberFormatException ex){
	  System.out.println("Please Input a valid number");
	} catch(FileNotFoundException ex){
	  System.out.println("File not found");
	} catch(IOException ex){
	  System.out.println("File missing or incorrect file name used");
	} catch(Exception ex){
      System.out.println(ex.toString());
    }
  }

  /**
  * Changes the images intensity or inverts the image based on what the users input is.
  * @param changeIntensity, if true adjusts the intensity else will invert the colours
  * @param intensityAmount, the amount the user has input to change the intensity by
  */
  public void changeImage(boolean changeIntensity, int intensityAmount){
    try{
	  int tempRed, tempGreen, tempBlue;
      for(int x = 0; x < pixels2D.length; x++) {
	    for(int y = 0; y < pixels2D[0].length; y++) {
	      if(redJCheckBox.isSelected()){
		    if(changeIntensity)
			  tempRed = pixels2D[x][y].adjust(pixels2D[x][y].getRed(), intensityAmount);
			else
			  tempRed = pixels2D[x][y].invertColour(pixels2D[x][y].getRed());
		  }else
		    tempRed = pixels2D[x][y].getRed();

		  if(greenJCheckBox.isSelected()){
		    if(changeIntensity)
			  tempGreen = pixels2D[x][y].adjust(pixels2D[x][y].getGreen(), intensityAmount);
			else
			  tempGreen = pixels2D[x][y].invertColour(pixels2D[x][y].getGreen());
		  }else
		    tempGreen = pixels2D[x][y].getGreen();

		  if(blueJCheckBox.isSelected()){
		    if(changeIntensity)
			  tempBlue = pixels2D[x][y].adjust(pixels2D[x][y].getBlue(), intensityAmount);
			else
			  tempBlue = pixels2D[x][y].invertColour(pixels2D[x][y].getBlue());
		  }else
		    tempBlue = pixels2D[x][y].getBlue();

		  int rgb = new PixelColor(tempRed, tempGreen, tempBlue).getRGBIntegerPixel();
		  bi.setRGB(y,x,rgb);
		  pixels2D[x][y] = new PixelColor(rgb);
		}
	  }
	} catch(NullPointerException e ){
	  System.out.println("Null Pointer: " + e);
	} catch(Exception e){
	  System.out.println(e);
	}
  }

}