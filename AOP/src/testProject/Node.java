package testProject;


/*
 * A node represents the interconnected set of holes in a physical breadboard.
 * It keeps track of components that are directly and indirectly connected to it.
 */
import java.util.LinkedList;


public class Node {

	private LinkedList<Component> connectedComps;
	private boolean ground;
	private int nodeID; // node's id in the circuit.
         // parallel int arrays with x and y coordinates of holes.

	public Node() {
            connectedComps = new LinkedList<Component>(); // start out with an empty list.
            ground = false;
	}

	public void setNodeID(int n) {
		nodeID = n;
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setGround() {
		ground = true;
	}

	public void unsetGround() {
		ground = false;
	}

	public boolean getGround() {
		return ground;
	}

        // set location of terminal x, y. i is the index of the terminal in this vertical node.
        //public void setVertTerminal(int i, int x, int y) {
             //vertPoints[i].setLocation(x, y);
      //  }

        // set location of terminal x, y. i is the index of the terminal in this horizontal
        //node.
     //   public void setHorTerminal(int i, int x, int y) {
       //     horPoints[i].setLocation(x, y);
        //}

	//does this node contain a component c?
	public boolean contains(Component c) {
		boolean answer = false;
		for (Component d : connectedComps) {
			if (c.equals(d)) answer = true;
		}
		return answer;
	}

	//adds component to the node
	public void addComponent(Component c) {
		connectedComps.add(c);
	}

	// removes component from the node.\
	public void removeComponent(Component c) {
		connectedComps.remove(c);
	}
	// return the components connected to this node.
	public LinkedList<Component> getComponents() {
		if (connectedComps.size() == 0) return null;
		else return connectedComps;
	}
	//String representation of this node, returns what is connected to it.
	public String toString() {
		String str1 = "";
		if (ground) str1 = "(Ground) " + "\n" + "        ";
		String str2 = "";
		for (Component comp: connectedComps) {
			str2 += comp.toString() + " ";
		}
		return str1 + str2;
	}

	public static void main(String[] args) {

	}
}

