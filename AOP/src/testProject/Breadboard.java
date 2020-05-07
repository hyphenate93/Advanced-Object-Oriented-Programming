package testProject;

import java.util.ArrayList;


public class Breadboard {
	
	public Node[] nodes; // BreadBoard's nodes.
	private int size; // size of BreadBoard (maximum number of nodes supported).
	private ArrayList<Node> nodesList; // does not contain ground node.
	private ArrayList<Component> components; // includes voltage sources.
	private ArrayList<Component> vs; // list of voltage sources.
	//private int nodeID;
	private int id; // this is probably useless. id's are basically the components' position.
	// in the components or vs lists.


	public Breadboard( int size) {
		id = 0;
		//nodeID = 1;
		this.size = size; // we'll make this user-defined or something.
		nodes = new Node[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = new Node();
			nodes[i].setNodeID(i);
		}
		nodesList = new ArrayList<Node>(); // initial size is 10.
		components = new ArrayList<Component>();
		vs = new ArrayList<Component>();
		//nodesList.add(0); // reserved for ground. Prevents NPE.
	}

	// add component between two nodes.
	public void addComponent(Component c, int nodeA, int nodeB) {
		c.setID(id);
		id++;
		c.setNodeA(nodes[nodeA]);
		c.setNodeB(nodes[nodeB]);
		if (c.type().equals("DCVoltageSource") || c.type().equals("ACVoltageSource")) {
			vs.add(c);
		}
		nodes[nodeA].addComponent(c);
		nodes[nodeB].addComponent(c);
		components.add(c);
		// update node information.
		if (!nodesList.contains(nodes[nodeA]) && !nodes[nodeA].getGround()) {
			nodesList.add(nodes[nodeA]);
		}
		if (!nodesList.contains(nodes[nodeB]) && !nodes[nodeB].getGround()) {
			nodesList.add(nodes[nodeB]);
		}
	}

        public ArrayList<Component> getComponents() {
            return components;
        }
	// set nodes[i] as the ground.
	public void setGround(int i) {
		nodes[i].setGround();
	}

	public void unsetGround(int i) {
		nodes[i].unsetGround();
	}

	public ArrayList<Node> getNodesList() {
//		removeGroundedNode();
		return nodesList;
	}

	//Clear the BreadBoard. Reset everything. Start over.
	public void clearAll() {
		for (int i = 0; i < size; i++) {
			nodes[i] = new Node();
		}
		nodesList.clear();
		//nodesList.add(0); // prevents NPE.
		components.clear();
		vs.clear();
		//nodeID = 1;
		id = 0;
	}

	// to remove a component, clear board and re-add items.
	public void removeComponent(Component c) {
		components.remove(c);
		if (c.type().equals("Voltage Source"))	vs.remove(c);
		components.remove(c);
		ArrayList<Component> tempcomponents = new ArrayList<Component>();
		for (Component comp : components) tempcomponents.add(comp);
		clearAll();
		for (Component comp : tempcomponents) {
			int nodeA = comp.getNodeA().getNodeID();
			int nodeB = comp.getNodeB().getNodeID();
			if (nodes[nodeA].getGround()) setGround(nodeA);
			if (nodes[nodeB].getGround()) setGround(nodeB);
			addComponent(comp, nodeA, nodeB);
		}
	}

	// the solution..Each element in v (index 0 to N - 1) corresponds to the voltage at the
	// equivalent node in the circuit. The rest are the currents through each voltage source.

	public static void main(String[] args) {
		// in the case of a voltage source, nodeA is positive, nodeB is negative.
		// start by setting ground so that that node is not included in nodesList.
		Breadboard Bb = new Breadboard(100);
		//Case 1. (value, nodeA, nodeB)
/*	
		Bb.setGround(23);
		Resistor r1 = new Resistor(2);
		Bb.addComponent(r1, 23, 3);
		Resistor r2 = new Resistor(4);
		Bb.addComponent(r2, 4, 5);
		Resistor r3 = new Resistor(8);
		Bb.addComponent(r3, 23, 4);
*/		/**
		StdOut.println(Bb + "\n");
		StdOut.println(Bb.matrixG() + "\n");
		StdOut.println(Bb.matrixB() + "\n");
		StdOut.println(Bb.matrixC() + "\n");
		StdOut.println(Bb.matrixD() + "\n");
		StdOut.println(Bb.matrixA() + "\n");
		StdOut.println(Bb.matrixX() + "\n");
		*/
/*		Breadboard Bb2 = new Breadboard(100);
		Resistor res1 = new Resistor(10);
		Resistor res2 = new Resistor(10);
*///		VoltageSource vols = new VoltageSource(20);
//		Bb2.addComponent(res1, 10, 16);
//		Bb2.addComponent(res2, 10, 16);
//		Bb2.setGround(10);
		/*
		StdOut.println(Bb2 + "\n");
		StdOut.println(Bb2.matrixG() + "\n");
		StdOut.println(Bb2.matrixB() + "\n");
		StdOut.println(Bb2.matrixC() + "\n");
		StdOut.println(Bb2.matrixD() + "\n");
		StdOut.println(Bb2.matrixA() + "\n");
		StdOut.println(Bb2.matrixX() + "\n");
		*/
	}
}