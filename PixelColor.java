/*
 * PixelColor.java
 * Author:  Joshua Parker
 *
 * PixelColour class
 * Gets and sets each colour
 * and outputs a string with the given values
 */

public class PixelColor {
  /**
  * The red component of this PixelColor.
  * If no red component is set it will default to 0.
  */
  private int red;

  /**
  * The green component of this PixelColor.
  * If no green component is set it will default to 0.
  */
  private int green;

  /**
  * The blue component of this PixelColor.
  * If no blue component is set it will default to 0.
  */
  private int blue;

  /**
  * The RGB component
  */
  public static final int RED=0,GREEN=1,BLUE=2; // the rgb component

  /**
  * The maximum possible colour value
  */
  private static final int MAX_VALUE = 255;

  /**
  * The minimum possible colour value
  */
  private static final int MIN_VALUE = 0;

  /**
  * Constructs and initializes a black pixelColor object
  */
  public PixelColor() {
    this(0, 0, 0);
  }

  /**
  * Constructs and initializes a pixelColor object by the specified RGB component values
  * @param r the red component value
  * @param g the green component value
  * @param b the blue component value
  */
  public PixelColor(int r, int g, int b) {
	setRed(r);
	setGreen(g);
	setBlue(b);
  }

  public int getRed(){
	return red;
  }

  public int getGreen(){
	return green;
  }

  public int getBlue(){
	return blue;
  }

  public void setRed(int r){
	red = r;
	if(red < MIN_VALUE)
		red = MIN_VALUE;
	else if(red >= MAX_VALUE)
		red = MAX_VALUE;
  }

  public void setGreen(int g){
	green = g;
	if(green < MIN_VALUE)
		green = MIN_VALUE;
	else if(green >= MAX_VALUE)
		green = MAX_VALUE;
  }

  public void setBlue(int b){
	blue = b;
	if(blue < MIN_VALUE)
		blue = MIN_VALUE;
	else if(blue >= MAX_VALUE)
		blue = MAX_VALUE;
  }


  /**
  * Constructs and initializes a PixelColor object by the specified Integer pixel value in the RGB colour model
  * @param rgb the Integer pixel value
  * The following method which uses shifts to extract the colour component values from an Integer pixel value.
  */
  public PixelColor(int rgb) {
    this.red =(rgb >> 16) & 0xFF;
    this.green =(rgb >> 8) & 0xFF;
    this.blue = rgb & 0xFF;
  }

  /**
  * Converts three 8-bit RGB intensity values, 0-255, to an integer pixel value in the default RGB color model
  */
  public int getRGBIntegerPixel() {
    int rgb = red << 16;
    rgb = rgb + (green << 8);
    rgb = rgb + blue;
    return rgb;
  }

  /**
  * Returns a string representation of this PixelColor object.
  * @return  a string representation of this PixelColor
  */
  public String toString() {
    return "[" + red + ", " + green + ", " + blue + "]";
  }


  /**
  * Inverts the value of the given input
  * @param    original - the integer colour value to be changed
  * @return   inverted - the inverted integer colour value
  */
  public int invertColour(int original){
    int inverted = MAX_VALUE - original;
	if(inverted < MIN_VALUE)
		inverted = MIN_VALUE;
	else if(inverted >= MAX_VALUE)
		inverted = MAX_VALUE;
	return inverted;
  }


  /**
  * Adjusts the intensity by the users chosen amount
  * @param    currentColourValue - the current integer value of the colour
  * @param    valueToAdjustBy - the amount the user wishes to change the intensity by
  * @return   the adjusted value of the colour
  */
  public int adjust(int currentColourValue, int valueToAdjustBy){
	return valueToAdjustBy + currentColourValue;
  }

}