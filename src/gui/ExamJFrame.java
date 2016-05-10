package gui;

import util.Util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExamJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageJPanel image;
	private JTextField jtf_value;
	private JButton jbu_confirm,jbu_cancel;
	
	private JFrame context;

	ExamJFrame(JFrame father,final int map[][],final String value) {
		super("识别面板");
		context=this;
		this.setLocation(father.getLocation().x + 20,
				father.getLocation().y + 80);
		this.setLayout(new GridLayout(1, 2));
		image=new ImageJPanel(map);
		this.add(image);
		JPanel temp_panel=new JPanel();
		this.add(temp_panel);
		temp_panel.setLayout(new GridLayout(2,1));
		jtf_value=new JTextField(value,10);
		jtf_value.setEnabled(false);
		addComponentHelper(temp_panel,jtf_value);
		JPanel button_panel=new JPanel();
		button_panel.setLayout(new GridLayout(1,2));
		temp_panel.add(button_panel);
		jbu_confirm=new JButton("正确");
		jbu_cancel=new JButton("错误");
		jbu_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Util.train(map, value);
				context.dispose();
			}
		});
		jbu_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LearnJFrame(MyJFrame.context, map).setVisible(true);
				context.dispose();
			}
		});
		addComponentHelper(button_panel,jbu_confirm);
		addComponentHelper(button_panel,jbu_cancel);
		this.setResizable(false);
		this.pack();
		image.repaint();
	}
	
	/**
	 * 添加按钮辅助函数
	 * @param base
	 * @param button
	 */
	private void addComponentHelper(JPanel base,JComponent button) {
		JPanel temp_panel=new JPanel();
		base.add(temp_panel);
		temp_panel.add(button);
	}

}

