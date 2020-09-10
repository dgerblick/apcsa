package mrstark;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class SnapComponent_DanielGerblick extends JComponent implements ActionListener {

	BufferedImage image;

	final int TRANSPARENCY =  255;

	final Color BEE_DARK = new Color(179, 167, 26, TRANSPARENCY);
	final Color BEE_LIGHT = new Color(223, 209, 36, TRANSPARENCY);
	final Color SHIRT_DARK = new Color(200, 200, 200, TRANSPARENCY);
	final Color SHIRT_LIGHT = new Color(230, 230, 230, TRANSPARENCY);
	final Color NOT_BLACK = new Color(20, 20, 20, TRANSPARENCY);
	final Color HAIR = new Color(75, 52, 31, TRANSPARENCY);
	final Color MOUTH = new Color(131, 52, 31, TRANSPARENCY);
	
	final GradientPaint GP_BARRY = new GradientPaint(587, 356, BEE_DARK, 660, 293, BEE_LIGHT, false);
	final GradientPaint GP_BARRY_EYELID = new GradientPaint(587, 356, BEE_DARK, 660, 293, BEE_LIGHT, false);
	final GradientPaint GP_BARRY_TIE = new GradientPaint(680, 680, NOT_BLACK, 670, 670, BEE_LIGHT, true);
	final GradientPaint GP_BARRY_BUTT = new GradientPaint(1020, 633, NOT_BLACK, 1008, 630, BEE_LIGHT, true);
	final GradientPaint GP_ADAM = new GradientPaint(509, 550, BEE_DARK, 262, 351, BEE_LIGHT, false);
	final GradientPaint BACKGROUND = new GradientPaint(0, 0, new Color(255, 231, 173), 0, 780, new Color(117, 88, 0));

	final Font FONT = new Font("Comic Sans MS", Font.PLAIN, 50);
	
	final int NUM_DUST = 1500;
	
	Graphics2D g2;
	AffineTransform at;
	Timer timer = new Timer(30, this);
	
	Vector<Polygon> dust;
	int[] xVel = new int[NUM_DUST];
	int[] yVel = new int[NUM_DUST];

	
	public SnapComponent_DanielGerblick() {
		at = new AffineTransform();
		at.rotate(-0.737242650338);
		
		Random rand = new Random();
		dust = new Vector<Polygon>();
		for (int i = 0; i < NUM_DUST; i++) {
			dust.add(new Polygon());
			start(i);
			for (int j = 0; j < rand.nextInt(100); j++)
				moveDust(i);
		}
		
		timer.start();
	}

	// Place drawing instructions inside this method
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g; // Make graphics 2D
		
		g2.setPaint(BACKGROUND);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setPaint(GP_ADAM);
		for (int i = 0; i < dust.size(); i++) {
			moveDust(i);
			g2.fill(dust.get(i));
		}
		
		g2.setPaint(GP_BARRY_BUTT);
		g2.fill(barryButt());
		
		g2.setColor(SHIRT_DARK);
		g2.fill(adamShirt());
		
		g2.setColor(NOT_BLACK);
		g2.fill(barryJacket());
		g2.fill(adamJacket());
		g2.fillPolygon(barryAntennae());
		
		Polygon p = adamAntennae();
		g2.fill(p);
		p.translate(100, 20);
		g2.fill(p);
		
		g2.setColor(SHIRT_DARK);
		g2.fill(barryShirt());
		g2.fill(barryCuff());
		
		g2.setColor(SHIRT_LIGHT);
		g2.fill(barryShirtCollar());
		
		g2.setPaint(GP_BARRY_TIE);
		g2.fill(barryTie());
		

		g2.setColor(NOT_BLACK);
		g2.fill(barryArmLeft());
		g2.fill(barryArmRight());

		
		g2.setPaint(GP_BARRY);
		g2.fill(barryHead());
		g2.fill(barryHand());
		
		g2.setPaint(GP_BARRY_EYELID);
		g2.fill(barryEyelid());
		
		g2.setColor(SHIRT_LIGHT);
		g2.fill(barryEyeLeft());
		g2.fill(barryEyeRight());
		
		g2.setColor(HAIR);
		g2.fill(barryHair());
		g2.fill(barryPupilLeft());
		g2.fill(barryPupilRight());
		g2.fill(barryEyebrowLeft());
		g2.fill(barryEyebrowRight());
		
		g2.setPaint(GP_ADAM);
		g2.fill(adamHead());
		
		g2.setColor(SHIRT_LIGHT);
		g2.fill(adamEyeLeft());
		g2.fill(adamEyeRight());

		g2.setColor(HAIR);
		g2.fill(adamPupilLeft());
		g2.fill(adamPupilRight());
		
		g2.setColor(MOUTH);
		g2.fill(adamMouth());
		g2.fill(barryMouth());		
		
		g2.setColor(NOT_BLACK);
		g2.fill(adamTie());
		
		g2.setColor(SHIRT_LIGHT);
		g2.fill(adamCollar());
		
		g2.setColor(Color.WHITE);
		g2.setFont(FONT);
		g2.drawString("Mr. Benson I don't feel so good", 250, 700);
		
		g2.setTransform(at);
		g2.setColor(SHIRT_DARK);
		g2.setStroke(new BasicStroke(5));
		g2.drawOval(20, 465, 75, 60);
		g2.drawOval(-95, 465, 75, 60);
		g2.drawLine(20, 495, -20, 495);
	}
	
	public void start(int index) {
		dust.get(index).reset();
		Random rand = new Random();
		Vector<Point> points = new Vector<Point>();
		points.add(new Point(275, 327));
		points.add(new Point(270, 365));
		points.add(new Point(276, 392));
		points.add(new Point(284, 417));
		points.add(new Point(308, 445));
		points.add(new Point(334, 465));
		points.add(new Point(390, 501));
		points.add(new Point(364, 455));
		points.add(new Point(313, 414));
		points.add(new Point(307, 369));
		points.add(new Point(408, 516));
		points.add(new Point(437, 558));
		points.add(new Point(445, 586));
		points.add(new Point(409, 503));
		points.add(new Point(385, 494));
		points.add(new Point(420, 577));
		points.add(new Point(387, 511));
		points.add(new Point(445, 574));
		points.add(new Point(407, 559));
		points.add(new Point(397, 526));
		
		Point start = points.get(rand.nextInt(points.size()));
		double scale = rand.nextDouble();
		
		dust.get(index).addPoint(start.x, start.y);
		dust.get(index).addPoint(start.x + (int) (15 * scale), start.y + (int) (25 * scale));
		dust.get(index).addPoint(start.x + (int) (30 * scale), start.y);		
		
		xVel[index] = -rand.nextInt(7) - 3;
		yVel[index] = rand.nextInt(15) - 7;
	}
	
	public void moveDust(int index) {	
		Rectangle bounds = dust.get(index).getBounds();
		dust.get(index).translate(xVel[index], yVel[index]);
		
		if(bounds.x < -bounds.getWidth() || bounds.y < -bounds.getHeight() || bounds.y > bounds.getHeight() + getHeight()) {
			start(index);
		}
	}
	
	private Polygon barryHead() {
		Polygon p = new Polygon();
		p.addPoint(509, 353);
		p.addPoint(520, 358);
		p.addPoint(527, 359);
		p.addPoint(526, 368);
		p.addPoint(526, 375);
		p.addPoint(531, 386);
		p.addPoint(544, 395);
		p.addPoint(554, 408);
		p.addPoint(565, 421);
		p.addPoint(582, 428);
		p.addPoint(597, 431);
		p.addPoint(614, 436);
		p.addPoint(646, 436);
		p.addPoint(676, 431);
		p.addPoint(698, 421);
		p.addPoint(715, 414);
		p.addPoint(734, 399);
		p.addPoint(748, 387);
		p.addPoint(773, 372);
		p.addPoint(778, 362);
		p.addPoint(798, 180);
		p.addPoint(673, 82);
		p.addPoint(510, 111);
		p.addPoint(498, 139);
		p.addPoint(490, 157);
		p.addPoint(481, 177);
		p.addPoint(476, 192);
		p.addPoint(472, 213);
		p.addPoint(473, 232);
		p.addPoint(477, 244);
		p.addPoint(486, 256);
		p.addPoint(491, 270);
		p.addPoint(501, 280);
		p.addPoint(507, 293);
		p.addPoint(514, 304);
		p.addPoint(522, 324);
		p.addPoint(511, 334);
		p.addPoint(507, 348);
		
		return p;
	}
	
	private Polygon barryHair() {
		Polygon p = new Polygon();
		p.addPoint(776, 358);
		p.addPoint(788, 343);
		p.addPoint(799, 323);
		p.addPoint(810, 299);
		p.addPoint(824, 267);
		p.addPoint(835, 238);
		p.addPoint(835, 204);
		p.addPoint(832, 153);
		p.addPoint(809, 113);
		p.addPoint(788, 92);
		p.addPoint(753, 65);
		p.addPoint(707, 44);
		p.addPoint(667, 44);
		p.addPoint(623, 46);
		p.addPoint(566, 57);
		p.addPoint(510, 74);
		p.addPoint(478, 101);
		p.addPoint(480, 124);
		p.addPoint(503, 143);
		p.addPoint(526, 141);
		p.addPoint(566, 141);
		p.addPoint(607, 124);
		p.addPoint(645, 123);
		p.addPoint(687, 127);
		p.addPoint(718, 145);
		p.addPoint(740, 163);
		p.addPoint(761, 198);
		p.addPoint(771, 241);
		p.addPoint(769, 286);
		p.addPoint(764, 313);
		p.addPoint(761, 343);
		
		return p;
	}
	
	private Polygon barryEyebrowLeft() {
		Polygon p = new Polygon();
		p.addPoint(690, 239);
		p.addPoint(689, 233);
		p.addPoint(667, 224);
		p.addPoint(649, 217);
		p.addPoint(618, 210);
		p.addPoint(587, 206);
		p.addPoint(560, 195);
		p.addPoint(544, 179);
		p.addPoint(533, 173);
		p.addPoint(530, 187);
		p.addPoint(536, 200);
		p.addPoint(555, 213);
		p.addPoint(576, 219);
		p.addPoint(597, 224);
		p.addPoint(620, 224);
		p.addPoint(639, 228);
		p.addPoint(661, 235);
		p.addPoint(684, 241);
		
		return p;
	}

	private Polygon barryEyebrowRight() {
		Polygon p = new Polygon();
		p.addPoint(475, 200);
		p.addPoint(475, 189);
		p.addPoint(485, 189);
		p.addPoint(494, 183);
		p.addPoint(505, 174);
		p.addPoint(512, 184);
		p.addPoint(506, 199);
		p.addPoint(496, 203);
		p.addPoint(485, 203);
		p.addPoint(476, 204);
		
		return p;
	}

	private Polygon barryJacket() {
		Polygon p = new Polygon();
		p.addPoint(813, 290);
		p.addPoint(791, 345);
		p.addPoint(746, 427);
		p.addPoint(663, 450);
		p.addPoint(597, 425);
		p.addPoint(598, 460);
		p.addPoint(605, 482);
		p.addPoint(610, 511);
		p.addPoint(606, 554);
		p.addPoint(610, 589);
		p.addPoint(616, 624);
		p.addPoint(634, 657);
		p.addPoint(658, 689);
		p.addPoint(681, 710);
		p.addPoint(705, 733);
		p.addPoint(739, 754);
		p.addPoint(777, 765);
		p.addPoint(817, 766);
		p.addPoint(849, 764);
		p.addPoint(875, 743);
		p.addPoint(905, 723);
		p.addPoint(939, 694);
		p.addPoint(967, 660);
		p.addPoint(984, 618);
		p.addPoint(988, 572);
		p.addPoint(985, 531);
		p.addPoint(982, 489);
		p.addPoint(979, 459);
		p.addPoint(975, 444);
		p.addPoint(952, 428);
		p.addPoint(910, 406);
		p.addPoint(875, 389);
		p.addPoint(855, 377);
		p.addPoint(837, 356);
		p.addPoint(821, 326);
		p.addPoint(818, 295);
		
		return p;
	}
	
	private Polygon barryShirt() {
		Polygon p = new Polygon();
		p.addPoint(803, 324);
		p.addPoint(795, 359);
		p.addPoint(787, 378);
		p.addPoint(772, 399);
		p.addPoint(752, 423);
		p.addPoint(732, 453);
		p.addPoint(717, 483);
		p.addPoint(717, 519);
		p.addPoint(725, 546);
		p.addPoint(734, 576);
		p.addPoint(744, 610);
		p.addPoint(759, 642);
		p.addPoint(772, 674);
		p.addPoint(776, 684);
		p.addPoint(735, 686);
		p.addPoint(709, 688);
		p.addPoint(679, 686);
		p.addPoint(657, 678);
		p.addPoint(649, 656);
		p.addPoint(642, 638);
		p.addPoint(640, 616);
		p.addPoint(641, 594);
		p.addPoint(634, 567);
		p.addPoint(627, 534);
		p.addPoint(627, 506);
		p.addPoint(626, 474);
		p.addPoint(625, 447);
		p.addPoint(625, 435);
		p.addPoint(636, 433);
		p.addPoint(674, 430);
		p.addPoint(715, 408);
		p.addPoint(744, 390);
		p.addPoint(767, 373);
		p.addPoint(788, 349);
		
		return p;
	}

	private Polygon barryShirtCollar() {
		Polygon p = new Polygon();
		p.addPoint(653, 471);
		p.addPoint(625, 457);
		p.addPoint(624, 436);
		p.addPoint(791, 314);
		p.addPoint(803, 327);
		p.addPoint(798, 344);
		p.addPoint(797, 352);
		p.addPoint(795, 361);
		p.addPoint(791, 370);
		p.addPoint(785, 381);
		p.addPoint(779, 389);
		p.addPoint(773, 397);
		p.addPoint(674, 474);
		
		return p;
	}
	
	private Polygon barryTie() {
		Polygon p = new Polygon();
		p.addPoint(653, 474);
		p.addPoint(639, 444);
		p.addPoint(654, 362);
		p.addPoint(677, 431);
		p.addPoint(673, 474);
		p.addPoint(708, 678);
		p.addPoint(680, 711);
		p.addPoint(651, 682);
		p.addPoint(653, 474);
		
		return p;
	}
	
	private Polygon barryButt() {
		Polygon p = new Polygon();
		p.addPoint(976, 447);
		p.addPoint(1012, 467);
		p.addPoint(1027, 508);
		p.addPoint(1036, 539);
		p.addPoint(1039, 581);
		p.addPoint(1035, 633);
		p.addPoint(1018, 669);
		p.addPoint(1001, 700);
		p.addPoint(973, 723);
		p.addPoint(942, 745);
		p.addPoint(919, 752);
		p.addPoint(882, 759);
		p.addPoint(861, 759);
		p.addPoint(826, 553);
		return p;
	}
	
	private Polygon barryArmLeft() {
		Polygon p = new Polygon();
		p.addPoint(726, 492);
		p.addPoint(679, 558);
		p.addPoint(641, 613);
		p.addPoint(589, 654);
		p.addPoint(500, 716);
		p.addPoint(512, 737);
		p.addPoint(570, 717);
		p.addPoint(634, 686);
		p.addPoint(686, 627);
		p.addPoint(741, 553);
		
		return p;
	}
	
	private Polygon barryArmRight() {
		Polygon p = new Polygon();
		p.addPoint(615, 479);
		p.addPoint(618, 515);
		p.addPoint(595, 538);
		p.addPoint(575, 554);
		p.addPoint(543, 566);
		p.addPoint(523, 564);
		p.addPoint(520, 546);
		p.addPoint(536, 531);
		p.addPoint(564, 517);
		p.addPoint(591, 496);
		p.addPoint(607, 467);
		
		return p;
	}
	
	private Polygon barryPupilLeft() {
		Polygon p = new Polygon();
		p.addPoint(550, 259);
		p.addPoint(564, 275);
		p.addPoint(570, 290);
		p.addPoint(581, 303);
		p.addPoint(590, 308);
		p.addPoint(597, 304);
		p.addPoint(593, 295);
		p.addPoint(586, 284);
		p.addPoint(578, 273);
		p.addPoint(575, 268);
		p.addPoint(585, 266);
		p.addPoint(597, 272);
		p.addPoint(609, 275);
		p.addPoint(621, 281);
		p.addPoint(639, 285);
		p.addPoint(617, 278);
		p.addPoint(599, 270);
		p.addPoint(579, 259);
		p.addPoint(565, 257);
		p.addPoint(552, 257);
		
		return p;
	}
	
	private Polygon barryPupilRight() {
		Polygon p = new Polygon();
		p.addPoint(510, 271);
		p.addPoint(498, 279);
		p.addPoint(491, 272);
		p.addPoint(481, 261);
		p.addPoint(478, 245);
		p.addPoint(478, 237);
		p.addPoint(481, 234);
		p.addPoint(493, 239);
		p.addPoint(502, 244);
		p.addPoint(508, 248);
		p.addPoint(502, 246);
		p.addPoint(508, 250);
		p.addPoint(492, 241);
		p.addPoint(494, 252);
		p.addPoint(500, 262);
		p.addPoint(506, 268);
		p.addPoint(511, 272);
		
		return p;
	}
	
	private Polygon barryEyeLeft() {
		Polygon p = new Polygon();
		p.addPoint(569, 264);
		p.addPoint(585, 264);
		p.addPoint(596, 270);
		p.addPoint(602, 273);
		p.addPoint(607, 275);
		p.addPoint(633, 284);
		p.addPoint(624, 296);
		p.addPoint(610, 300);
		p.addPoint(602, 302);
		p.addPoint(595, 305);
		p.addPoint(575, 282);
		
		return p;
	}
	
	private Polygon barryEyeRight() {
		Polygon p = new Polygon();
		p.addPoint(502, 245);
		p.addPoint(508, 249);
		p.addPoint(492, 240);
		p.addPoint(488, 254);
		p.addPoint(508, 272);
		p.addPoint(510, 262);
		p.addPoint(509, 257);
		p.addPoint(508, 250);
		
		return p;
	}
	
	private Polygon barryAntennae() {
		Polygon p = new Polygon();
		p.addPoint(589, 55);
		p.addPoint(586, 37);
		p.addPoint(580, 17);
		p.addPoint(577, 0);
		p.addPoint(588, 0);
		p.addPoint(592, 10);
		p.addPoint(596, 31);
		p.addPoint(599, 45);
		p.addPoint(599, 67);
		p.addPoint(656, 55);
		p.addPoint(652, 38);
		p.addPoint(646, 17);
		p.addPoint(643, 0);
		p.addPoint(660, 0);
		p.addPoint(662, 20);
		p.addPoint(661, 41);
		p.addPoint(658, 62);
		p.addPoint(610, 86);
		
		return p;
	}
	
	private Polygon barryMouth() {
		Polygon p = new Polygon();
		p.addPoint(563, 390);
		p.addPoint(573, 387);
		p.addPoint(586, 389);
		p.addPoint(596, 392);
		p.addPoint(605, 396);
		p.addPoint(605, 397);
		p.addPoint(596, 399);
		p.addPoint(586, 399);
		p.addPoint(576, 399);
		p.addPoint(567, 395);
		p.addPoint(562, 391);
	
		return p;
	}
	
	private Polygon barryEyelid() {
		Polygon p = new Polygon();
		p.addPoint(551, 259);
		p.addPoint(563, 248);
		p.addPoint(572, 246);
		p.addPoint(578, 247);
		p.addPoint(586, 249);
		p.addPoint(590, 250);
		p.addPoint(596, 253);
		p.addPoint(602, 253);
		p.addPoint(609, 255);
		p.addPoint(616, 261);
		p.addPoint(619, 264);
		p.addPoint(626, 274);
		p.addPoint(632, 281);
		p.addPoint(631, 287);
		p.addPoint(595, 291);
		
		return p;
	}
	
	private Polygon barryCuff() {
		Polygon p = new Polygon();
		p.addPoint(499, 714);
		p.addPoint(485, 720);
		p.addPoint(491, 734);
		p.addPoint(500, 743);
		p.addPoint(514, 737);
		
		return p;
	}
	
	private Polygon barryHand() {
		Polygon p = new Polygon();
		p.addPoint(486, 720);
		p.addPoint(479, 712);
		p.addPoint(481, 724);
		p.addPoint(468, 730);
		p.addPoint(479, 731);
		p.addPoint(468, 737);
		p.addPoint(480, 739);
		p.addPoint(470, 747);
		p.addPoint(486, 743);
		p.addPoint(476, 754);
		p.addPoint(487, 748);
		p.addPoint(502, 742);
		
		return p;
	}
	
	private Polygon adamAntennae() {
		Polygon p = new Polygon();
		p.addPoint(298, 71);
		p.addPoint(294, 61);
		p.addPoint(279, 60);
		p.addPoint(264, 59);
		p.addPoint(238, 76);
		p.addPoint(221, 96);
		p.addPoint(203, 110);
		p.addPoint(185, 131);
		p.addPoint(166, 147);
		p.addPoint(147, 173);
		p.addPoint(142, 208);
		p.addPoint(147, 242);
		p.addPoint(153, 265);
		p.addPoint(168, 288);
		p.addPoint(192, 312);
		p.addPoint(222, 323);
		p.addPoint(240, 329);
		p.addPoint(251, 319);
		p.addPoint(222, 309);
		p.addPoint(195, 297);
		p.addPoint(177, 273);
		p.addPoint(163, 246);
		p.addPoint(155, 212);
		p.addPoint(164, 175);
		p.addPoint(184, 154);
		p.addPoint(215, 118);
		p.addPoint(231, 102);
		p.addPoint(242, 88);
		p.addPoint(267, 72);
		p.addPoint(299, 71);

		return p;
	}

	private Polygon adamHead() {
		Polygon p = new Polygon();
		p.addPoint(531, 577);
		p.addPoint(530, 549);
		p.addPoint(522, 532);
		p.addPoint(517, 515);
		p.addPoint(509, 487);
		p.addPoint(502, 457);
		p.addPoint(493, 438);
		p.addPoint(481, 419);
		p.addPoint(459, 392);
		p.addPoint(444, 370);
		p.addPoint(429, 352);
		p.addPoint(416, 336);
		p.addPoint(403, 322);
		p.addPoint(392, 305);
		p.addPoint(371, 294);
		p.addPoint(358, 287);
		p.addPoint(341, 285);
		p.addPoint(320, 284);
		p.addPoint(294, 295);
		p.addPoint(287, 299);
		p.addPoint(270, 307);
		p.addPoint(244, 326);
		p.addPoint(241, 347);
		p.addPoint(238, 363);
		p.addPoint(238, 382);
		p.addPoint(239, 402);
		p.addPoint(244, 422);
		p.addPoint(250, 438);
		p.addPoint(259, 453);
		p.addPoint(267, 463);
		p.addPoint(280, 472);
		p.addPoint(293, 482);
		p.addPoint(302, 489);
		p.addPoint(319, 501);
		p.addPoint(328, 509);
		p.addPoint(344, 516);
		p.addPoint(361, 527);
		p.addPoint(377, 542);
		p.addPoint(380, 548);
		p.addPoint(392, 565);
		p.addPoint(400, 577);
		p.addPoint(407, 588);
		p.addPoint(411, 594);
		p.addPoint(419, 609);
		p.addPoint(422, 615);
		p.addPoint(427, 622);
		return p;
	}
	
	private Polygon adamCollar() {
		Polygon p = new Polygon();
		p.addPoint(573, 641);
		p.addPoint(540, 569);
		p.addPoint(427, 621);
		p.addPoint(438, 651);
		p.addPoint(460, 689);
		p.addPoint(496, 595);
		p.addPoint(568, 645);
		return p;
	}	
	
	private Polygon adamTie() {
		Polygon p = new Polygon();
		p.addPoint(472, 650);
		p.addPoint(511, 677);
		p.addPoint(531, 800);
		p.addPoint(571, 800);
		p.addPoint(592, 780);
		p.addPoint(532, 671);
		p.addPoint(540, 609);
		p.addPoint(496, 595);
		
		return p;
	}
	
	private Polygon adamJacket() {
		Polygon p = new Polygon();
		p.addPoint(461, 690);
		p.addPoint(468, 794);
		p.addPoint(496, 791);
		p.addPoint(506, 772);
		p.addPoint(513, 748);
		p.addPoint(507, 726);
		p.addPoint(490, 695);
		p.addPoint(471, 684);
		p.addPoint(461, 676);
		p.addPoint(478, 628);
		p.addPoint(543, 618);
		p.addPoint(562, 648);
		p.addPoint(566, 672);
		p.addPoint(583, 691);
		p.addPoint(614, 716);
		p.addPoint(631, 737);
		p.addPoint(654, 754);
		p.addPoint(681, 762);
		p.addPoint(693, 759);
		p.addPoint(661, 739);
		p.addPoint(642, 714);
		p.addPoint(616, 679);
		p.addPoint(586, 640);
		p.addPoint(551, 604);
		p.addPoint(461, 633);
		
		return p;
	}
	
	private Polygon adamShirt() {
		Polygon p = new Polygon();
		p.addPoint(450, 618);
		p.addPoint(479, 770);
		p.addPoint(671, 753);
		p.addPoint(529, 582);
		
		return p;
	}
	
	private Polygon adamMouth() {
		Polygon p = new Polygon();
		p.addPoint(468, 467);
		p.addPoint(459, 469);
		p.addPoint(454, 469);
		p.addPoint(448, 473);
		p.addPoint(440, 476);
		p.addPoint(435, 480);
		p.addPoint(427, 484);
		p.addPoint(418, 495);
		p.addPoint(417, 500);
		p.addPoint(421, 502);
		p.addPoint(428, 499);
		p.addPoint(438, 494);
		p.addPoint(447, 489);
		p.addPoint(455, 482);
		p.addPoint(461, 475);
		
		return p;
	}
	
	private Polygon adamEyeLeft() {
		Polygon p = new Polygon();
		p.addPoint(369, 324);
		p.addPoint(366, 326);
		p.addPoint(364, 333);
		p.addPoint(364, 337);
		p.addPoint(367, 338);
		p.addPoint(374, 337);
		p.addPoint(380, 335);
		p.addPoint(383, 333);
		
		return p;
	}
	
	private Polygon adamEyeRight() {
		Polygon p = new Polygon();
		p.addPoint(284, 401);
		p.addPoint(281, 402);
		p.addPoint(279, 406);
		p.addPoint(278, 410);
		p.addPoint(278, 414);
		p.addPoint(281, 417);
		p.addPoint(287, 418);
		p.addPoint(291, 415);
		
		return p;
	}
	
	private Polygon adamPupilRight() {
		Polygon p = new Polygon();
		p.addPoint(283, 415);
		p.addPoint(281, 409);
		p.addPoint(281, 402);
		p.addPoint(285, 399);
		p.addPoint(289, 395);
		p.addPoint(297, 393);
		p.addPoint(304, 395);
		p.addPoint(305, 398);
		p.addPoint(302, 403);
		p.addPoint(301, 408);
		p.addPoint(296, 412);
		p.addPoint(291, 417);
		p.addPoint(288, 416);
		
		return p;
	}
	
	private Polygon adamPupilLeft() {
		Polygon p = new Polygon();
		p.addPoint(376, 316);
		p.addPoint(371, 320);
		p.addPoint(367, 326);
		p.addPoint(368, 332);
		p.addPoint(371, 335);
		p.addPoint(377, 336);
		p.addPoint(384, 334);
		p.addPoint(387, 329);
		p.addPoint(389, 323);
		p.addPoint(387, 317);
		
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
