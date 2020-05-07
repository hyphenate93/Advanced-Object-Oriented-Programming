package testProject;

/* Requires the following files.
 * resistor.jpg, capacitor.jpg, inductor.jpg, battery.jpg, oscillator.jpg and ground.jpg
 *
 *
 * Need to add functionality to buttons.
 *
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.text.DecimalFormat;


public class BreadboardGUI {
    public static MyPanel panel;
    public static ArrayList<Component> components = new ArrayList<Component>();
    public static JTextArea text;
    public static boolean selected = false;
    public static Contact contactA;
    public static Contact contactB;
    public static int oddeven = 1;
    public static int temp = -1;

    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // set up window using system look and feel.
        try {
            UIManager.setLookAndFeel(
                                     UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        JFrame frame = new JFrame("Breadboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MyPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(1000, 500));
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(toolBar(),BorderLayout.NORTH);

        text = new JTextArea(10, 15);
        text.setEditable(false);
        text.setLineWrap(true);
        final JPopupMenu pop = new JPopupMenu();
        JMenuItem clear = new JMenuItem("Clear");
        pop.add(clear);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text.replaceRange(null, 0, text.getText().length());
            }
        });

        text.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pop.show(text, e.getX(), e.getY());
                    pop.setVisible(true);
                }

                else pop.setVisible(false);
            }

            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e){}
        });
        final JScrollPane scroll = new JScrollPane(text);
        frame.getContentPane().add(scroll, BorderLayout.EAST);


        panel.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                //int oddeven = 1;
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int clickedComponent = panel.getClickedComponent(e.getX(), e.getY());
                    //System.out.println(clickedComponent);
                    //System.out.printf("%d, %d", e.getX(), e.getY());
                   // System.out.println("\n");
                    if(clickedComponent != -1) {
                        JPopupMenu popup = popupMenu(clickedComponent);
                        popup.show(panel, e.getX(), e.getY());
                        popup.setVisible(true);
                        // then you can change this component's properties, etc.
                    }


                }

                else if (e.getButton() == MouseEvent.BUTTON1) {
                    //popup.setVisible(false);
                    int clickedComponent = panel.getClickedComponent(e.getX(), e.getY());
                    if (clickedComponent != -1) {
                        temp = clickedComponent;
                        text.append(String.format("%s\n\nClick on 2 nodes to place component between\n\n", components.get(temp).toString()));
                        selected = true;
                    }

                    else if (
                             panel.isInBounds(new Point(e.getX(), e.getY()), panel.board.mxPos, panel.board.myPos, panel.board.mWidth, panel.board.mHeight) &&
                             selected) {
                        if (oddeven % 2 == 0) selected = false;
                        if (oddeven % 2 == 0) {
                            contactB = panel.getClosestContact(e.getX(), e.getY());
                            text.append(String.format("Node A: %d\nNodeB: %d\n\n", contactA.node, contactB.node));
                            Component comp = components.get(temp);
                           // panel.connectComponent(comp, contactA, contactB);
                            //panel.redraw();
                        }
                        else if (oddeven % 2 != 0) contactA = panel.getClosestContact(e.getX(), e.getY());
                        oddeven++;
                    }
                }
            }

            public void mousePressed(MouseEvent e) {
                //popup.setVisible(false);
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

        });

        frame.pack();
        frame.setVisible(true);
    }

    // the toolbar
    public static JToolBar toolBar() {
        JToolBar toolBar = new JToolBar();
        JButton button = null;

        // new Board with a blank Breadboard object. Clear everything.
        button = new JButton("Clear");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graphics g = panel.getGraphics();
                panel.board.drawBoard(g, MyPanel.m);
            	panel.board.clearEverything();
            	panel.redraw();
            }
        });
        toolBar.add(button);

        // Resistor
		button = makeButton("resistor.png", "add Resistor", "add Resistor");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
					Resistor r = new Resistor();
					r.setLocation(200, 50);
					components.add(r);
					panel.redraw();
				}
			
		});
		toolBar.add(button);

		// Capacitor.
		button = makeButton("capacitor", "add Capacitor", "add Capacitor");
		button.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
					Resistor c = new Resistor();
					c.setLocation(230, 50);
					components.add(c);
					panel.redraw();
			}
		});
        toolBar.add(button);

        button = makeButton("Sieg hej", "Solve", "Solve");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try{
					System.out.println("hej");
                //text.append(panel.board.solve());
				} catch (java.lang.ArrayIndexOutOfBoundsException ex){
					text.append("Your circuit is not complete.\n");
				}
            }
        });
        toolBar.add(button);
        return toolBar;
    }
    // method to add buttons to toolBar.
    public static JButton makeButton(String imageName, String
            toolTipText, String altText) {
    	JButton button = new JButton(imageName);
		return button;
    }


    //Popup menu comes up when component is right-clicked
    public static JPopupMenu popupMenu(int clickedComponent) {
        final int component = clickedComponent;
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem;

		menuItem = new JMenuItem("Change Value");
		menuItem.getAccessibleContext().setAccessibleDescription("Change the impedance of this component");
		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String input1 = JOptionPane.showInputDialog("Change value to: ");
				if (input1 != null) {
					double newvalue = Double.parseDouble(input1);
					//components.get(component).changeValue(newvalue);
				}
				String input2 = null;
				if (components.get(component).type().equalsIgnoreCase("ACVoltageSource")) {
					input2 = JOptionPane.showInputDialog("Change frequency to: ");
					if (input2 != null) {
						double newFrequency = Double.parseDouble(input2);
						//components.get(component).setFrequency(newFrequency);
					}
				}
			}
		});

        popupMenu.add(menuItem);

        menuItem = new JMenuItem("Flip Orientation");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.flip(components.get(component));
                panel.redraw();
            }
       });
        popupMenu.add(menuItem);

        menuItem = new JMenuItem("Remove");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                components.remove(component);
                ArrayList<Contact> contactsWithComponents = new ArrayList<Contact>();
                for (Component c : components) {
                    contactsWithComponents.add(c.getContactA());
                    contactsWithComponents.add(c.getContactB());
                }
                for (Contact c : panel.board.contacts) {
                    if (!contactsWithComponents.contains(c)) {
                        c.disconnect();
                    }
                }
                panel.redraw();
            }
        });
        popupMenu.add(menuItem);

        return popupMenu;
    	//}
    }
}
class MyPanel extends JPanel {
    public static double m = 1.4; // magnification
    public Board board;

    public MyPanel() {
        board = new Board();
        //nodesArray = board.getBreadBoard().nodes;
        //currentNode = nodesArray[0];
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                // to select a contact on the board.
                Point p = new Point(e.getX(), e.getY());
                Contact contact = getClosestContact(p.x-(int)(2*m), p.y-(int)(2*m));
                if (isInBounds(p, board.mxPos, board.myPos, board.mWidth, board.mHeight)) {
                    for (Contact c : board.contacts) {
                        if (c.is(contact)) continue;
                        if (c.isSelected) {
                            c.select(); // unselect any other selected contact.
                        }
                    }
                    contact.select();
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){ // NOT WORKING
				//Point p = new Point(e.getX(), e.getY());

               // if (isInBounds(p, board.mxPos, board.myPos, board.mWidth, board.mHeight)) {

					//moveBoard(e.getX(), e.getY());
               // }
            }
        });
    }
    // repaint
    public void redraw() {
        repaint();
    }


    // finds the closest contact to some point x,y, given a list of contacts.
    public Contact getClosestContact(int x, int y) {
        Point p = new Point(x, y);
        double distance = Double.POSITIVE_INFINITY;
        int index = 0;
        for (Contact c : board.contacts) {
            if (c.point.distance(p) < distance) {
                distance = c.point.distance(p);
                index = board.contacts.indexOf(c);
            }
        }
        return board.contacts.get(index);
    }

 // returns the component index on the breadboard if yes, -1 otherwise.
 public int getClickedComponent(int x, int y) {
  Point p = new Point(x, y);
  int ans = -1;
  for (Component c : BreadboardGUI.components) {
   if (c.getOrientation().equalsIgnoreCase("horizontal")) {
    int xComp = c.getPanelLocation().x;
    int yComp = c.getPanelLocation().y;
    if (isInBounds(p, xComp, yComp, (int) (20 * MyPanel.m), (int) (10 * MyPanel.m))) {
     ans = BreadboardGUI.components.indexOf(c);
    }
   } else if (c.getOrientation().equalsIgnoreCase("vertical")) {
    int xComp = c.getPanelLocation().x;
    int yComp = c.getPanelLocation().y;
    if (isInBounds(p, xComp, yComp, (int) (10 * MyPanel.m), (int) (20 * MyPanel.m))) {
     ans = BreadboardGUI.components.indexOf(c);
    }
   }
  }
  return ans;
 }

 // is point p within the rectangle at x, y of width w and height h?
 public boolean isInBounds(Point p, int x, int y, int w, int h) {
  boolean hor = p.x > x && p.x < (x + w);
  boolean vert = p.y > y && p.y < (y + h);
  return (hor && vert);
 }
 // to flip a component.

 public void flip(Component comp) {
  if (comp.getOrientation().equalsIgnoreCase("horizontal")) {
   comp.setOrientation("vertical");
  } else {
   comp.setOrientation("horizontal");
  }
 }
 // Connects to breadboard and updates component information.

 public void connectComponent(Component comp, Contact cont1, Contact cont2) {
  cont1.connect();
  cont2.connect();
  comp.plugIn();
  comp.setContactA(cont1);
  comp.setContactB(cont2);
  //clean up board
  ArrayList<Contact> contactsWithComponents = new ArrayList<Contact>();
  for (Component c : BreadboardGUI.components) {
   contactsWithComponents.add(c.getContactA());
   contactsWithComponents.add(c.getContactB());
  }
  for (Contact c : board.contacts) {
   if (!contactsWithComponents.contains(c)) {
    c.disconnect();
   }
  }
 }

 // add Component to board and BreadBoard (ie, graphically and the circuit)
 public void drawConnectedComponent(Component comp, Contact cont1, Contact cont2, Graphics g) {
  // now draw Component at x,y.
  int x;
  int y;
  int longSide = (int) (20 * m); // dimensions of a component.
  int shortSide = (int) (10 * m);
  String orientation = comp.getOrientation();
  if (orientation.equalsIgnoreCase("horizontal")) {
   x = (cont1.point.x + cont2.point.x) / 2 - longSide / 2;
   y = (cont1.point.y + cont2.point.y) / 2 - shortSide / 2 + (int) (2 * m);
   comp.setLocation((int) (x / m), (int) (y / m));
   comp.setPanelLocation(x, y);
   drawHorComponent(comp.type(), g, x, y);
   // now the actual connection between contacts and component.
   Point p1 = new Point(cont1.point.x + (int) (2 * m), cont1.point.y + (int) (2 * m));
   Point p2 = new Point(x, y + shortSide / 2);
   Point p3 = new Point(cont2.point.x + (int) (2 * m), cont2.point.y + (int) (2 * m));
   Point p4 = new Point(x + longSide, y + shortSide / 2);
   if (p1.distance(p2) < p1.distance(p4)) { // connect to closest side of the component.
    drawConnection(g, p1, p2);
    drawConnection(g, p3, p4);
   } else {
    drawConnection(g, p1, p4);
    drawConnection(g, p3, p2);
   }
  } else { // it's vertical otherwise, so ...
   x = (cont1.point.x + cont2.point.x) / 2 - shortSide / 2 + (int) (2 * m);
   y = (cont1.point.y + cont2.point.y) / 2 - longSide / 2;
   comp.setPanelLocation(x, y);
   drawVertComponent(comp.type(), g, x, y);
   // then the actual connection.
   Point p1 = new Point(cont1.point.x + (int) (2 * m), cont1.point.y + (int) (2 * m));
   Point p2 = new Point(x + shortSide / 2, y);
   Point p3 = new Point(cont2.point.x + (int) (2 * m), cont2.point.y + (int) (2 * m));
   Point p4 = new Point(x + shortSide / 2, y + longSide);
   // connect to closest side of the component.
   if (p1.distance(p2) < p1.distance(p4)) {
    drawConnection(g, p1, p2);
    drawConnection(g, p3, p4);
   } else {
    drawConnection(g, p1, p4);
    drawConnection(g, p3, p2);
   }
  }
 }
 // draws the actual 'wire' between twon components.
    public void drawConnection(Graphics g, Point p1, Point p2) {
 Color originalColor = g.getColor();
 g.setColor(new Color(139, 26, 26));
 Graphics2D g2D = (Graphics2D)g;
 BasicStroke bs = new BasicStroke(1.5F);
 int join = bs.getLineJoin();
 g2D.setStroke(new BasicStroke(2.5F, BasicStroke.CAP_ROUND, join));

 g2D.drawLine(p1.x, p1.y, p2.x, p2.y);
 g.setColor(originalColor);
    }

    // draw a vertical component at x,y with magnification m.
    public void drawVertComponent(String comp, Graphics g, int x, int y) {
        Image image = null;
        if (comp.equalsIgnoreCase("board")) board.drawBoard(g, MyPanel.m);
            if (comp.equalsIgnoreCase("Resistor"))
                image = makeBufferedImage("resistorVert.png");
            else if (comp.equalsIgnoreCase("Capacitor"))
                image = makeBufferedImage("capacitorVert.png");
            else if (comp.equalsIgnoreCase("Inductor"))
                image = makeBufferedImage("inductorVert.png");
            else if (comp.equalsIgnoreCase("DCVoltageSource"))
                image = makeBufferedImage("DCVoltageSourceVert.png");
            else if (comp.equalsIgnoreCase("ACVoltageSource"))
                image = makeBufferedImage("ACVoltageSource.png");

        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, (int)(10*MyPanel.m), (int)(20*MyPanel.m), (int)(3*MyPanel.m), (int)(3*MyPanel.m));
        g.drawImage(image, x, y, (int)(10*MyPanel.m), (int)(20*MyPanel.m), null);
		repaint();
    }




    // draw horizontal component between term1 and term2
    public void drawHorComponent(String comp, Graphics g, int x, int y) {
        Image image = null;
        if (comp.equalsIgnoreCase("board")) board.drawBoard(g, MyPanel.m);
			if (comp.equalsIgnoreCase("Resistor"))
                image = makeBufferedImage("resistorHor.png");
            else if (comp.equalsIgnoreCase("Capacitor"))
                image = makeBufferedImage("capacitorHor.png");
            else if (comp.equalsIgnoreCase("Inductor"))
                image = makeBufferedImage("inductorHor.png");
            else if (comp.equalsIgnoreCase("DCVoltageSource"))
                image = makeBufferedImage("DCVoltageSourceHor.png");
            else if (comp.equalsIgnoreCase("ACVoltageSource"))
                image = makeBufferedImage("ACVoltageSource.png");

        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, (int)(20*MyPanel.m), (int)(10*MyPanel.m), (int)(3*MyPanel.m), (int)(3*MyPanel.m));
        g.drawImage(image, x, y, (int)(20*MyPanel.m), (int)(10*MyPanel.m), null);
		repaint();
    }
    
    
	public Image makeBufferedImage(String imageName) {
		String imgLocation = "C:/hh/aoop/Exercise/src/testProject/images/"+imageName;
		Image image = null;
		try {
			image = ImageIO.read(new File(imgLocation));
		} catch (IOException e) {
			System.out.println("Vafan gick fel?");
		}
		return image;
	}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) (700*m),(int) (500*m));
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.drawBoard(g, m);
        for (Component c : BreadboardGUI.components) {
            if (BreadboardGUI.components.isEmpty()) break;
            int x = c.getLocation().x;
            int y = c.getLocation().y;
            c.setPanelLocation((int)(x*MyPanel.m), (int)(y*MyPanel.m));
            String type = c.type();
            if (c.getOrientation().equalsIgnoreCase("Horizontal") && !c.isPluggedIn()) {
                drawHorComponent(type, g, c.getPanelLocation().x, c.getPanelLocation().y);
            }
            else if (c.getOrientation().equalsIgnoreCase("Vertical") && !c.isPluggedIn()) {
                drawVertComponent(type, g, c.getPanelLocation().x, c.getPanelLocation().y);
            }
            else if (c.isPluggedIn()) {
                drawConnectedComponent(c, c.getContactA(), c.getContactB(), g);
            }
        }
    }
}
/* This class will take care of breadboard functions. It creates an instance of the
 * BreadBoard object which keeps an array of nodes. Nodes are indexed starting with the
 * vertical ones (from 0 to 45, then 46 to 91) and then horizontal ones(92 to 99),
 * starting from the top left bus(blue line), moving to the right then next row and
 * so on.
 *
 *
 */
class Board {

    private int xPos = 100;
    public int mxPos; // magnified xPos.
    private int yPos = 100;
    public int myPos;
    private int width = 520; // should be 530 if voltage terminals are to be included.
    public int mWidth;
    private int height = 175;
    public int mHeight;
    private Breadboard Bb;
    public ArrayList<Contact> contacts;
    private int c; // keep count of contacts

    public Board() {
        Bb = new Breadboard(100);
        contacts = new ArrayList<Contact>();
        for (int i = 0; i < 660; i++) {
            Contact cont  = new Contact();
            contacts.add(cont);
        }
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }

    public int getxPos(){
        return xPos;
    }

    public void setyPos(int yPos){
        this.yPos = yPos;
    }

    public int getyPos(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
 /* returns nodes used in this instance of BreadBoard.
    public BreadBoard getBreadBoard() {
        return Bb;
    }*/


    /* draw the nodes.
     * The total number of nodes is 100. 46 on each of the two rows with vertical
     * nodes and 8 horizontal nodes. Horizontals are interconnected as shown by the
     * red and blue lines to form only 8 nodes.(or buses)
     * Create a breadboard object which maintains an array of nodes. the first 92 are
     * the verticals and the next 8 the buses.
     */
    public void drawNodes(Graphics g) {
        double m = MyPanel.m;
        int length = (int)Math.round(4*m); // size of contacts.
        Font font = new Font("Serif", Font.PLAIN, 8);
        g.setFont(font);
        // vertical nodes.
        int x = (int)((xPos+30)*m);
        int y = (int)((yPos+40)*m);
        for (int index = 1; index <= 46; index++) {
            g.setColor(new Color(204, 170, 170));
            g.drawString(String.format("%d", index), x, (int)((yPos+40-3)*m));
            for (int n = 0; n < 5; n++) {
                Point p = new Point(x, y);
                Contact contact = contacts.get(c);
                contact.setLocation(p);
                contact.setNode(index);
                contact.drawContact(g, length);
                c++;
                y += (int)(8*m);
            }
            y = (int)((yPos+40)*m); // reset y, increment x.
            x += (int)(10*m);
        }
        x = (int)((xPos+30)*m);
        y = (int)((yPos+40+55)*m);
        for (int index = 47; index <= 92; index++) {
            g.setColor(new Color(204, 170, 170));
            g.drawString(String.format("%d", index), x, (int)((yPos+80+55+3)*m));
            for (int n = 0; n < 5; n++) {
                Point p = new Point(x, y);
                Contact contact = contacts.get(c);
                contact.setLocation(p);
                contact.setNode(index);
                contact.drawContact(g, length);
                c++;
                y += (int)(8*m);
            }
            y = (int)((yPos+40+55)*m); // reset y and increment x.
            x += (int)(10*m);
        }

        // horizontal nodes
        int index = 93;
        drawHorizontalNode(g, index, (int)((xPos+35)*m), (int)((yPos+14)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+260)*m), (int)((yPos+14)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+35)*m), (int)((yPos+14+8)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+260)*m), (int)((yPos+14+8)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+35)*m), (int)((yPos+height-27)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+260)*m), (int)((yPos+height-27)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+35)*m), (int)((yPos+height-20)*m));
        index++;
        drawHorizontalNode(g, index, (int)((xPos+260)*m), (int)((yPos+height-20)*m));
        c = 0; // reset count.
    }

    // draw horizontal Node at this location. c is the first contact. x, y position of
    // the first contact.
    public void drawHorizontalNode(Graphics g, int index, int x, int y) {
        double m = MyPanel.m;
        int length = (int)Math.round(4*m); // size of contacts.
        for (int i = 0; i < 25; i++) {
            Point p = new Point(x, y);
            Contact contact = contacts.get(c);
            contact.setLocation(p);
            contact.setNode(index);
            contact.drawContact(g, length);
            c++;
            if((i ==4 || i == 9) || (i == 14 || i == 19)) {
                x += (int)(13*MyPanel.m);
            }
            else x += (int)(8*MyPanel.m);
        }
    }


    public void drawBoard(Graphics g, double m) { // m = magnfication

        g.setColor(Color.WHITE); // the background
        mWidth = (int)(width*m);
        mHeight = (int)(height*m);
        mxPos = (int)(xPos*m);
        myPos = (int)(yPos*m);
        g.fillRect(mxPos,myPos,mWidth,mHeight);
        g.setColor(Color.BLACK); // all the outlines.
        g.drawRect(mxPos,myPos,mWidth,mHeight);
        g.setColor(Color.BLUE);
        g.drawLine((int)((xPos+35)*m), (int)((yPos+10)*m), (int)((xPos+248)*m), (int)((yPos+10)*m));
        g.drawLine((int)((xPos+260)*m), (int)((yPos+10)*m), (int)((xPos+473)*m), (int)((yPos+10)*m));
        g.drawLine((int)((xPos+35)*m), (int)((yPos+height-30)*m), (int)((xPos+248)*m), (int)((yPos+height-30)*m));
        g.drawLine((int)((xPos+260)*m), (int)((yPos+height-30)*m), (int)((xPos+473)*m), (int)((yPos+height-30)*m));
        g.setColor(Color.RED);
        g.drawLine((int)((xPos+35)*m), (int)((yPos+10+20)*m), (int)((xPos+248)*m), (int)((yPos+10+20)*m));
        g.drawLine((int)((xPos+260)*m), (int)((yPos+10+20)*m), (int)((xPos+473)*m), (int)((yPos+10+20)*m));
        g.drawLine((int)((xPos+35)*m), (int)((yPos+height-30+20)*m), (int)((xPos+248)*m), (int)((yPos+height-30+20)*m));
        g.drawLine((int)((xPos+260)*m), (int)((yPos+height-30+20)*m), (int)((xPos+473)*m), (int)((yPos+height-30+20)*m));
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)((xPos+35)*m), (int)((yPos+82)*m), (int)((width-75)*m), (int)(8*m));
        drawNodes(g);
    }

 // solve the circuit on this BreadBoard. Add all to circuit then compute results.
	/*
    public String solve() {
		Bb.clearAll();
		ArrayList<Component> components = BreadboardGUI.components;
		int lowestNode = 99; // lowest numbered node used as ground for every circuit.
		int nodeA;
		int nodeB;
		for (Component comp : components) {
			if (comp.isPluggedIn()) {
				nodeA = comp.getContactA().node;
				nodeB = comp.getContactB().node;
				Bb.addComponent(comp, nodeA, nodeB);
				if (nodeA < lowestNode) {
					lowestNode = nodeA;
				}
				if (nodeB < lowestNode) {
					lowestNode = nodeB;
				}
			}
		}
	

		return Bb.matrixX() + "\n";
	}
*/
 // Clear everything to start over.
 public void clearEverything() {
  Bb.clearAll();
  BreadboardGUI.components.clear();
  for (Contact c : contacts) {
   if (c.isConnected) c.disconnect();
   if (c.isSelected) c.select();
  }

 }
}

class Contact {
    public int node; // this contact belongs to this node in the breadboard.
    public Point point; // location of this contact.
    public boolean isConnected; // state of this contact, ie, is something connected to it?
    public boolean isSelected;


    public Contact() {
        isConnected = false;
        isSelected = false;
    }
    public void drawContact(Graphics g, int length) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(point.x, point.y, length, length, (int)(2*MyPanel.m), (int)(2*MyPanel.m));
        if (isConnected) { // something to indicate connection.

            g.setColor(new Color(0, 34, 34));
            g.drawRoundRect(point.x, point.y, length, length, (int)(2*MyPanel.m), (int)(2*MyPanel.m));
            g.drawRect(point.x, point.y, length, length);
   Graphics2D g2D = (Graphics2D)g;
            g.drawOval(point.x, point.y, length, length);
        }
        if (isSelected) {
            g.setColor(new Color(0, 34, 170));
            g.drawRoundRect(point.x, point.y, length, length, (int)(2*MyPanel.m), (int)(2*MyPanel.m));
            g.drawRect(point.x, point.y, length, length);
        }
    }
    public void setNode(int node) {
        this.node = node;
    }
    public void setLocation(Point p) {
        point = p;
    }
    public void connect() {
        isConnected = true;
    }
    public void disconnect() {
        isConnected = false;
    }
    public void select() {
        isSelected = !isSelected;
    }

    // is this contact the same thing as another contact c?
    public boolean is(Contact c) {
        boolean ans = BreadboardGUI.panel.board.contacts.indexOf(c) ==
            BreadboardGUI.panel.board.contacts.indexOf(this);
        return ans;
    }
    public String toString() {
        String str = String.format("%d", node);
        str += " " + point.toString();
        if(isSelected) str += " SELECTED";
        else str += " NOT SELECTED";
        return str;
    }
}

