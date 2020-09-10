/**
 * This class contains class (static) methods that will help you test the
 * Picture_DanielGerblick class methods. Uncomment the methods and the code in the main to
 * test.
 * 
 * @author Barbara Ericson
 */
public class PictureTester_DanielGerblick {
	/** Method to test zeroBlue */
	public static void testZeroBlue() {
		Picture_DanielGerblick beach = new Picture_DanielGerblick("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}
	
	public static void testZeroRedStdForLoop() {
		Picture_DanielGerblick beach = new Picture_DanielGerblick("beach.jpg");
		beach.explore();
		beach.zeroRedStdForLoop();
		beach.explore();
	}
	
	public static void testKeepOnlyBlue() {
		Picture_DanielGerblick beach = new Picture_DanielGerblick("beach.jpg");
		beach.explore();
		beach.keepOnlyBlue();
		beach.explore();
	}
	
	public static void testNegate() {
		Picture_DanielGerblick beach = new Picture_DanielGerblick("beach.jpg");
		beach.explore();
		beach.negate();
		beach.explore();
	}
	
	public static void testGreyscale() {
		Picture_DanielGerblick beach = new Picture_DanielGerblick("beach.jpg");
		beach.explore();
		beach.greyscale();
		beach.explore();
	}
	

	public static void testFixUnderwater() {
		Picture_DanielGerblick water = new Picture_DanielGerblick("water.jpg");
		water.explore();
		water.fixUnderwater();
		water.explore();
	}

	/** Method to test mirrorVertical */
	public static void testMirrorVertical() {
		Picture_DanielGerblick caterpillar = new Picture_DanielGerblick("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorVertical();
		caterpillar.explore();
	}
	
	public static void testMirrorVerticalRTL() {
		Picture_DanielGerblick caterpillar = new Picture_DanielGerblick("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorVerticalRTL();
		caterpillar.explore();
	}
	
	public static void testMirrorHorizontal() {
		Picture_DanielGerblick caterpillar = new Picture_DanielGerblick("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorHorizontal();
		caterpillar.explore();
	}
	
	public static void testMirrorHorizontalBTT() {
		Picture_DanielGerblick caterpillar = new Picture_DanielGerblick("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorHorizontalBTT();
		caterpillar.explore();
	}
	
	public static void testMirrorDiagonal() {
		Picture_DanielGerblick redMotorcycle = new Picture_DanielGerblick("redMotorcycle.jpg");
		redMotorcycle.explore();
		redMotorcycle.mirrorDiagonal();
		redMotorcycle.explore();
	}                                                                                      

	/** Method to test mirrorTemple */
	public static void testMirrorTemple() {
		Picture_DanielGerblick temple = new Picture_DanielGerblick("temple.jpg");
		temple.explore();
		temple.mirrorTemple();
		temple.explore();
	}
	
	public static void testMirrorArms() {
		Picture_DanielGerblick temple = new Picture_DanielGerblick("snowman.jpg");
		temple.explore();
		temple.mirrorArms();
		temple.explore();
	}
	
	public static void testMirrorGull() {
		Picture_DanielGerblick temple = new Picture_DanielGerblick("seagull.jpg");
		temple.explore();
		temple.mirrorGull();
		temple.explore();
	}
	
	public static void testMirrorSelection(int mirror) {
		Picture_DanielGerblick temple = new Picture_DanielGerblick("temple.jpg");
		temple.explore();
		temple.mirrorSelection(100, 100, 200, 200, mirror);
		temple.explore();
	}

	/** Method to test the collage method */
	public static void testCollage() {
		Picture_DanielGerblick canvas = new Picture_DanielGerblick("640x480.jpg");
		canvas.createCollage();
		canvas.explore();
	}

	/** Method to test edgeDetection */
	public static void testEdgeDetection() {
		Picture_DanielGerblick swan = new Picture_DanielGerblick("swan.jpg");
		swan.edgeDetection(10);
		swan.explore();
	}

	/**
	 * Main method for testing. Every class can have a main method in Java
	 */
	public static void main(String[] args) {
		// uncomment a call here to run a test
		// and comment out the ones you don't want
		// to run
		//testZeroBlue();
		//testZeroRedStdForLoop();
		//testKeepOnlyBlue();
		//testGreyscale();
		//testKeepOnlyRed();
		//testKeepOnlyGreen();
		//testNegate();
		//testGrayscale();
		//testFixUnderwater();
		//testMirrorVertical();
		//testMirrorVerticalRTL();
		//testMirrorHorizontal();
		//testMirrorHorizontalBTT();
		//testMirrorDiagonal();
		testMirrorTemple();
		testMirrorArms();
		testMirrorGull();
		testMirrorSelection(Picture_DanielGerblick.MIRROR_BOTTOM);
		//testMirrorDiagonal();
		//testCollage();
		//testCopy();
		//testEdgeDetection();
		//testEdgeDetection2();
		//testChromakey();
		//testEncodeAndDecode();
		//testGetCountRedOverValue(250);
		//testSetRedToHalfValueInTopHalf();
		//testClearBlueOverValue(200);
		//testGetAverageForColumn(0);
	}
}