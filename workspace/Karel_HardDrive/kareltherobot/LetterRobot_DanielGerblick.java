package kareltherobot;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class LetterRobot_DanielGerblick extends BetterRobot_DanielGerblick {

	private Map<Character, Integer> templates = new HashMap<Character, Integer>();
	
	
	public LetterRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
		initTemplates();
	}
	public LetterRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
		initTemplates();
	}
	
	private void initTemplates() {
		templates.put(' ', 0x100000);
		templates.put('!', 0x180888);
		templates.put('"', 0x1000aa);
		templates.put('#', 0x1afafa);
		templates.put('$', 0x1f7fef);
		templates.put('%', 0x1bf6fd);
		templates.put('&', 0x1da5ae);
		templates.put('\'', 0x100222);
		templates.put('(', 0x134443);
		templates.put(')', 0x1c222c);
		templates.put('*', 0x100a4a);
		templates.put('+', 0x104e40);
		templates.put(',', 0x188000);
		templates.put('-', 0x100600);
		templates.put('.', 0x180000);
		templates.put('/', 0x184210);
		templates.put('0', 0x16dfb6);
		templates.put('1', 0x172226);
		templates.put('2', 0x1f8f1f);
		templates.put('3', 0x1f171f);
		templates.put('4', 0x111f99);
		templates.put('5', 0x1f1f8f);
		templates.put('6', 0x1f9f8f);
		templates.put('7', 0x11111f);
		templates.put('8', 0x1f9f9f);
		templates.put('9', 0x1f1f9f);
		templates.put(':', 0x108080);
		templates.put(';', 0x188080);
		templates.put('<', 0x124842);
		templates.put('=', 0x10f0f0);
		templates.put('>', 0x184248);
		templates.put('?', 0x14042c);
		templates.put('@', 0x10779f);
		templates.put('a', 0x199f96);
		templates.put('b', 0x1e9e9e);
		templates.put('c', 0x1f888f);
		templates.put('d', 0x1e999e);
		templates.put('e', 0x1f8e8f);
		templates.put('f', 0x188e8f);
		templates.put('g', 0x1f9b8f);
		templates.put('h', 0x199f99);
		templates.put('i', 0x1f666f);
		templates.put('j', 0x1c222f);
		templates.put('k', 0x199e99);
		templates.put('l', 0x1f8888);
		templates.put('m', 0x1999f9);
		templates.put('n', 0x19bdd9);
		templates.put('o', 0x1f999f);
		templates.put('p', 0x188f9f);
		templates.put('q', 0x12f99f);
		templates.put('r', 0x19af9f);
		templates.put('s', 0x1e1687);
		templates.put('t', 0x16666f);
		templates.put('u', 0x1f9999);
		templates.put('v', 0x169999);
		templates.put('w', 0x19f999);
		templates.put('x', 0x199699);
		templates.put('y', 0x166699);
		templates.put('z', 0x1f861f);	
		templates.put('[', 0x174447);
		templates.put('\\', 0x112480);
		templates.put(']', 0x1e222e);
		templates.put('^', 0x100052);
		templates.put('_', 0x1f0000);
		templates.put('`', 0x100024);
		templates.put('{', 0x132423);
		templates.put('|', 0x122222);
		templates.put('}', 0x1c424c);
		templates.put('~', 0x1000a5);
	}
	
	public void drawE() {
		draw(0x1f8e8f);
	}
	
	public void drawH() {
		draw(0x199f99);
	}
	
	public void drawLetter(char letter) {
		draw(templates.get(letter));
	}
	
	public void nextSpace() {
		turnRight();
		move(5);
		turnLeft();
	}
	
	public void drawString(String draw) {
		draw.toLowerCase();
		for (char c : draw.toCharArray()) {
			drawLetter(c);
			nextSpace();
		}
	}
	
	public void draw(int template) {
		String str = Integer.toBinaryString(template);		
		
		for (int i = 0; i < 5; i++) {
			turnRight();
			for (int j = 0; j < 4; j++) {
				int index = 4 * i + j + 1;
				
				boolean put = str.charAt(index) == '1';
				System.out.println(str.charAt(index));
				if (put)
					putBeeper();
				if (j != 3)
					move();
			}
			turnAround();
			move(3);
			turnRight();
			move();
		}
		
		turnAround();
		move(5);
		turnAround();
	}
}
