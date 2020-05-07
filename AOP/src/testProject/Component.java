package testProject;

/* Components keep track of which nodes and which components they are connected to.
*
*  NB: In the case of a voltage
*/
import java.awt.Point;

public interface Component {

   public String type();

   public void setOrientation(String orientation);

   public String getOrientation();

   public void setID(int id);

   public int getID();

   public void setLocation(int x, int y);

   public void setPanelLocation(int x, int y);

   public Point getLocation();

   public Point getPanelLocation();

   public boolean equals(Component c);

   // public LinkedList<Node> getNodes(LinkedList<Node> nodes);

   public String toString();

   public void addConnection(Component c); // just to distinguish from Node's
   // addComponent method. MAY NOT BE NECESSARY.

   public void setNodeA(Node nodeA);

   public void setNodeB(Node nodeB);

   public Node getNodeA();

   public Node getNodeB();

public void setContactA(Contact contA);

public void setContactB(Contact contB);

public Contact getContactA();

public Contact getContactB();

public void plugIn();

public void unplug();

public boolean isPluggedIn();

   // public Boolean isHorizontal(); // need to know these. Check out Marks suggestion of inherited classes to make all these a lot easier.

   //public Boolean isVertical();

   //public int compareTo();

}