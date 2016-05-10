package gui;

import data.WordMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

class ImageJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * show map data
	 */
	private final int[][] map;
	/**
	 * set the size of show board
	 */
	private final int width = WordMap.unit_width*4, height = WordMap.unit_height*4;

	ImageJPanel(int map[][]) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.map = map;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		g2d.setColor(Color.RED);
		for(int i=0;i<width;i+=8) {
			g2d.drawLine(i, 0, i, height);
		}
		for(int i=0;i<height;i+=8) {
			g2d.drawLine(0, i, width, i);
		}
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < WordMap.unit_width; ++i) {
			for (int j = 0; j < WordMap.unit_height; ++j) {
				if (map[i][j] == 1)
					g2d.fillRect(i*4, j*4, 8, 8);
			}
		}
	}

}

