package Assignment2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * @author andreas
 *
 */
public abstract class Presenter extends JFrame implements ActionListener {

	private JTextArea textField;
	private JLabel label;
	
	protected JButton north;
	protected JButton south;
	protected JButton east;
	protected JButton west;
	
	public abstract java.awt.Component createCenterComponent();

	public abstract void northButtonPressed();

	public abstract void eastButtonPressed();

	public abstract void southButtonPressed();

	public abstract void westButtonPressed();
	
	public Presenter() {
			
		
		Component centerComponent = createCenterComponent();
		JPanel ImageIcon =  (JPanel) centerComponent;
		ImageIcon.setPreferredSize(new Dimension(800, 800));
		north = new JButton("north");
		south = new JButton("south");
		east = new JButton("east");
		west = new JButton("west");
		north.addActionListener(this);
		south.addActionListener(this);
		east.addActionListener(this);
		west.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new LineBorder(Color.BLACK, 1));
		buttonPanel.setBackground(Color.GREEN);
		buttonPanel.setLayout(new BorderLayout());
		
		buttonPanel.add(north,BorderLayout.NORTH);
		buttonPanel.add(south,BorderLayout.SOUTH);
		buttonPanel.add(west,BorderLayout.WEST);
		buttonPanel.add(east,BorderLayout.EAST);

		JPanel textPanel = new JPanel();
		textField = new JTextArea(5,40);
		textPanel.setBorder(new LineBorder(Color.BLACK, 1));
		textPanel.setBackground(Color.YELLOW);
		textPanel.add(textField);
	
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.setBackground(Color.BLACK);
		southPanel.add(buttonPanel);
		southPanel.add(textPanel);
		Container mainContainer = this.getContentPane();
		mainContainer.add(ImageIcon,BorderLayout.CENTER);
		mainContainer.add(southPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	
	public void showText(String text) {

		textField.setText(text);	
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}



}

