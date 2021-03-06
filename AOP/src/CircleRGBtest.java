import java.util.Arrays;



	
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;


	public class CircleRGBtest
	{
		
		private static CircleRGB icon;
	    private static JLabel label;

	    
	    public static void main(String[] args)
	    {
	    	JFrame frame = new JFrame();
	      	JButton red = new JButton("Red");

	      	red.addActionListener(
	      			colorListener("red"));
	      
	    	JButton blue = new JButton("Blue");
	    	
	    	blue.addActionListener(
	    			colorListener("blue"));      

	    	JButton green = new JButton("Green");
	    	green.addActionListener(
	    			colorListener("green"));

	    	icon = new CircleRGB(200);
	    	label = new JLabel(icon);

	    	frame.setLayout(new FlowLayout());
	    	
	    	frame.add(red);
	    	frame.add(green);
	    	frame.add(blue);
	    	frame.add(label);

	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      	frame.pack();
	      	frame.setVisible(true);
	    }

	    
	    public static ActionListener colorListener(final String button) {
	      return new
	         ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               if(button == "blue"){
	            	   icon.setColor(Color.blue);
	               }
	               else if(button == "green"){
	            	   icon.setColor(Color.green);
	               }
	               else if(button == "red"){
	            	   icon.setColor(Color.red);
	               }
	               label.repaint();
	            }
	         };
	   }

	}
	
