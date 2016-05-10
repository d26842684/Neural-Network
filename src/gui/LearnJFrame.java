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

public class LearnJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageJPanel image;
	private JTextField jtf_value;
	private JButton jbu_confirm,jbu_cancel;
	private final int[][] my_map;
	
	private JFrame context;

	LearnJFrame(JFrame father,int map[][]) {
		super("Train Board");
		context=this;
		this.setLocation(father.getLocation().x + 20,
				father.getLocation().y + 80);
		this.setLayout(new GridLayout(1, 2));
		this.my_map=map;
		image=new ImageJPanel(map);
		this.add(image);
		JPanel temp_panel=new JPanel();
		this.add(temp_panel);
		temp_panel.setLayout(new GridLayout(2,1));
		jtf_value=new JTextField("What's this?",10);
		addComponentHelper(temp_panel,jtf_value);
		JPanel button_panel=new JPanel();
		button_panel.setLayout(new GridLayout(1,2));
		temp_panel.add(button_panel);
		jbu_confirm=new JButton("Confirm");
		jbu_cancel=new JButton("Cancel");
		jbu_confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Util.train(my_map, jtf_value.getText());
				context.dispose();
			}
		});
		jbu_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
	 * add button
	 * @param base
	 * @param button
	 */
	private void addComponentHelper(JPanel base,JComponent button) {
		JPanel temp_panel=new JPanel();
		base.add(temp_panel);
		temp_panel.add(button);
	}

}

