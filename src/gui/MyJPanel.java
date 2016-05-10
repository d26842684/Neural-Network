package gui;

import data.WordMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MyJPanel extends JPanel implements MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 保存绘制的文字数据坐标
	 */
	private WordMap word;

	/**
	 * 用于绘制的地图缓存
	 */
	private int[][] work_map;

	/**
	 * 绘制面板的长和宽
	 */
	private final int width = 400;
	private final int height = 400;

	MyJPanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseMotionListener(this);
		word = new WordMap(width, height);
	}
	
	/**
	 * 清除屏幕
	 */
	void clearScreen() {
		word.resetMap();
		this.repaint();
	}
	
	/**
	 * 分析辅助函数
	 */
	void analysis() {
		int temp_width = word.getWordWidth();
		int temp_height = word.getWordHeight();
		work_map = word.getWordMap();
		System.out.println("the word's size is: width:" + temp_width
				+ " height:" + temp_height);
		word.getFormateMap();
		word.copyMap();
		this.repaint();
	}
	
	/**
	 * 返回地图格式化之后的数据
	 * @return
	 */
	int[][] getMap() {
		return word.getFormateMap();
	}
	
	

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);// 使用大写方便跨平台
		g2d.fillRect(0, 0, width, height);
		g2d.setColor(Color.CYAN);// 使用大写方便跨平台
		g2d.drawLine(width / 2, 0, width / 2, height);
		g2d.drawLine(0, height / 2, width, height / 2);
		g2d.drawRect((width - WordMap.unit_width) / 2,
				(height - WordMap.unit_height) / 2, WordMap.unit_width,
				WordMap.unit_height);
		g2d.setColor(Color.BLACK);// 使用大写方便跨平台
		work_map = word.getMap();
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				if (work_map[i][j] == 1) {
					g2d.fillRect(i - 1, j - 1, 2, 2);
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		/**
		 * 绘制的时候为了增加文字信息量所以绘制多个点
		 */
		word.setPoint(e.getX() - 3, e.getY());
		word.setPoint(e.getX() + 3, e.getY());
		word.setPoint(e.getX() - 3, e.getY() - 3);
		word.setPoint(e.getX() + 3, e.getY() + 3);
		word.setPoint(e.getX(), e.getY());
		word.setPoint(e.getX(), e.getY() - 3);
		word.setPoint(e.getX(), e.getY() + 3);
		word.setPoint(e.getX() - 3, e.getY() - 3);
		word.setPoint(e.getX() + 3, e.getY() + 3);
		repaint(e.getX() - 10, e.getY() - 10, e.getX() + 10, e.getY() + 10);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
