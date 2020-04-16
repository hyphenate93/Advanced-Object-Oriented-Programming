package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

/**
 * A class that implements an Observer object that displays a barchart view of a
 * data model.
 */
public class BarFrame extends JFrame implements ChangeListener, MouseListener {

	private ArrayList<Double> a;
	private DataModel dataModel;

	private static final int ICON_WIDTH = 200;
	private static final int ICON_HEIGHT = 200;

	/**
	 * Constructs a BarFrame object
	 * 
	 * @param dataModel the data that is displayed in the barchart
	 */
	public BarFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		a = dataModel.getData();

		setLocation(0, 200);
		setLayout(new BorderLayout());

		addMouseListener(this); 

		Icon barIcon = new Icon()

		{
			public int getIconWidth() {
				return ICON_WIDTH;
			}

			public int getIconHeight() {
				return ICON_HEIGHT;
			}

			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(Color.red);

				double max = (a.get(0)).doubleValue();

				for (Double v : a) {
					double val = v.doubleValue();
					if (val > max)
						max = val;
				}
				double barHeight = getIconHeight() / a.size();

				int i = 0;
				for (Double v : a) {
					double value = v.doubleValue();

					double barLength = getIconWidth() * value / max;
					Rectangle2D.Double rectangle = new Rectangle2D.Double(0, barHeight * i, barLength, barHeight);
					i++;
					g2.fill(rectangle);
				}
			}
		};

		add(new JLabel(barIcon));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Called when the data in the model is changed.
	 * 
	 * @param e the event representing the change
	 */
	public void stateChanged(ChangeEvent e) {
		a = dataModel.getData();

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		
		double value = (double) e.getX() / (double) getWidth() * 200;

		if (30 <= e.getY() && e.getY() < 80) {
			// first bar
			dataModel.update(0, value);
		}

		if (80 <= e.getY() && e.getY() < 130) {
			// second bar
			dataModel.update(1, value);
		}

		if (130 <= e.getY() && e.getY() < 180) {
			// third bar
			dataModel.update(2, value);
		}

		if (180 <= e.getY() && e.getY() < 230) {
			// fourth bar
			dataModel.update(3, value);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}