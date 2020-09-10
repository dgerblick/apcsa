import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture_DanielGerblick extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture_DanielGerblick() {
		/*
		 * not needed but use it to show students the implicit call to super() child
		 * constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture_DanielGerblick(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture_DanielGerblick(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture_DanielGerblick(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture_DanielGerblick(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName, height
	 *         and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}
	
	public void zeroRedStdForLoop() {
		Pixel[][] pixels = this.getPixels2D();
		for (int i = 0; i < pixels.length; i++)
			for (int j = 0; j < pixels[i].length; j++)
				pixels[i][j].setRed(0);
	}

	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				pixels[i][j].setRed(0);
				pixels[i][j].setGreen(0);
			}
		}
	}
	
	
	/**
	 * Method that mirrors the picture around a vertical mirror in the center of the
	 * picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}
	
	public void mirrorVerticalRTL() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = width / 2; col < width; col++) {
				rightPixel = pixels[row][col];
				leftPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}
	
	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int row = height / 2; row < height; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				topPixel = pixels[height - 1 - row][col];
				bottomPixel = pixels[row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}
	
	public void mirrorHorizontalBTT() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int row = height / 2; row < height; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				topPixel = pixels[height - 1 - row][col];
				bottomPixel = pixels[row][col];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}
	
	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		int width = pixels[0].length;
		for (int row = 0; row < height; row++) {
			double percentDown = (double) row / height;
			double targetCol = width * percentDown;
			for (int col = 0; col < targetCol; col++) {
				double percentRight = (double) col / width;
				double targetRow = height * percentRight;
				topPixel = pixels[(int) targetRow][(int) targetCol];
				
				bottomPixel = pixels[row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}
	
	public void mirrorArms() {
		mirrorSelection(160, 105, 190, 169, MIRROR_BOTTOM);
		mirrorSelection(172, 239, 194, 292, MIRROR_BOTTOM);
	}
	
	public void mirrorGull() {
		int blend = 10;
		mirrorSelection(235, 238, 322, 345, MIRROR_RIGHT);
	}
	
	
	public static final int MIRROR_LEFT = 0;
	public static final int MIRROR_RIGHT = 1;
	public static final int MIRROR_TOP = 2;
	public static final int MIRROR_BOTTOM = 3;
	public void mirrorSelection(int startRow, int startCol, int endRow, int endCol, int edge) {
		Pixel start;
		Pixel mirror;
		
		Pixel[][] pixels = this.getPixels2D();
		for (int row = startRow; row <= endRow; row++) {				
			for (int col = startCol; col <= endCol; col++) {
				
				start = pixels[row][col];
				switch (edge) {
				case MIRROR_RIGHT:
					mirror = pixels[row][2 * endCol - col];
					break;
				case MIRROR_LEFT:
					mirror = pixels[row][2 * startCol - col];
					break;
				case MIRROR_TOP:
					mirror = pixels[2 * startRow - row][col];
					break;
				case MIRROR_BOTTOM:
					mirror = pixels[2 * endRow - row][col];
					break;
				default:
					mirror = null;
					break;
				}
				
				Color c = start.getColor();						
				mirror.setColor(c);
			}
		}
	}
	

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in the
	 * current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	public void negate() {
		Pixel[][] pixels = getPixels2D();

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				pixels[i][j].setRed(255 - pixels[i][j].getRed());
				pixels[i][j].setGreen(255 - pixels[i][j].getGreen());
				pixels[i][j].setBlue(255 - pixels[i][j].getBlue());
			}
		}
	}
	
	public void greyscale() {
		Pixel[][] pixels = getPixels2D();

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				int brightness = (pixels[i][j].getRed() + pixels[i][j].getGreen() + pixels[i][j].getBlue()) / 3;
				pixels[i][j].setColor(new Color(brightness, brightness, brightness));
			}
		}
	}
	
	public void fixUnderwater() {
		int sat = 4;
		Pixel[][] pixels = getPixels2D();
		Color avg = new Color(pixels[0][0].getRed(), pixels[0][0].getGreen(), pixels[0][0].getBlue());
		
		for (int i = 0; i < pixels.length; i++)
			for (int j = 0; j < pixels[i].length; j++)
				avg = averageColors(avg, pixels[i][j].getColor());
		
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				pixels[i][j].setRed(sat * Math.abs(pixels[i][j].getRed() - avg.getRed()));
				pixels[i][j].setGreen(sat * Math.abs(pixels[i][j].getGreen() - avg.getGreen()));
				pixels[i][j].setBlue(sat * Math.abs(pixels[i][j].getBlue() - avg.getBlue()));
			}
		}
	}
	
	private Color averageColors(Color c1, Color c2) {
		int r = (c1.getRed() + c2.getRed()) / 2;
		int g = (c1.getGreen() + c2.getGreen()) / 2;
		int b = (c1.getBlue() + c2.getBlue()) / 2;
		return new Color(r, g, b);
	}
	
	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
