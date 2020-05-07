package testProject;

import java.util.LinkedList;
import java.awt.Point;
import java.awt.Graphics;

public class Resistor implements Component {
 //   private double resistance;
    private int id;
    private Point location; // stores location of this component on the panel. It does not
    // change with relative to the breadboard.
    private Point panelLocation; // this changes relative to the breadboard. Useful for
    // zooming and dragging board.
    private LinkedList<Component> connections; // keeps track of components connected
    // to it.
    private Node nodeA;
    private Node nodeB; // a component will always be connected to two nodes.
    private Contact contactA;
    private Contact contactB;
    private boolean isPluggedIn;
    private String orientation;

    public Resistor() {
//      resistance = r;
        connections = new LinkedList<Component>(); // start out with an empty list.
        location = new Point();
        panelLocation = new Point();
        orientation = "Vertical"; // default orientation.
        isPluggedIn = false;
    }

    public void setContactA(Contact contA) {
        contactA = contA;
    }

    public void setContactB(Contact contB) {
        contactB = contB;
    }

    public Contact getContactA() {
        return contactA;
    }

    public Contact getContactB() {
        return contactB;
    }

    public void plugIn() {
        isPluggedIn = true;
    }

    public void unplug() {
        isPluggedIn = false;
    }

    public boolean isPluggedIn() {
        return isPluggedIn;
    }

    public void setLocation(int x, int y) {
        location.setLocation(x, y);
    }

    public Point getLocation() {
        return location;
    }

    public void setPanelLocation(int x, int y) {
        panelLocation.setLocation(x, y);
    }

    public Point getPanelLocation() {
        return panelLocation;
    }
    public String type() {
        return "Resistor";
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }

    public boolean equals(Component c) {
        return (c.getID() == id);
    }

    public void addConnection(Component c) {
        connections.add(c);
    }

    /*public LinkedList<Node> getNodes(LinkedList<Node> nodes) {
     //ListIterator iterator = nodes.listIterator(0);
     LinkedList<Node> adjacentNodes = new LinkedList<Node>();
     adjacentNodes.add(nodeA);
     adjacentNodes.add(nodeB);
     return adjacentNodes;
     }*/

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;

    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;

    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

//    public String toString() {
//        return String.format("Resistance: %.3f", resistance, id);
//    }

//    public void changeValue(double newValue) {
//      resistance = newValue;
//  }

}
