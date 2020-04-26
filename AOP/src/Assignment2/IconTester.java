package assignment2;

import javax.swing.*;

/**
   Tests the composite icon class
*/
public class IconTester
{
   /**
     Creates and displays an composite icon that contains car and mars icons.
     @param args unused
   */
   public static void main(String[] args)
   {
      CompositeIcon composite = new CompositeIcon(100, 100);
      composite.addIcon(new CarIcon(30), 0, 0);
      composite.addIcon(new MarsIcon(30), 0, 50);
      JOptionPane.showMessageDialog(
         null,
         "Hello, Mars!",
         "Message",
         JOptionPane.INFORMATION_MESSAGE,
         composite);
      System.exit(0);
   }
}

