package Ex2;

import java.util.*;

public class Node<T> implements Tree<T> {	
	
	private List<Tree<T>> children;
	private T value;
	
	public Node(List<Tree<T>> trees, T v) {
		children = trees;
		value = v;
	}
	
	public T getValue() {
		return value;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}
	
	public <R, A> R accept(TreeVisitor<T, R, A> v, A acc) {
		return v.visit(this, acc);
	}

	public String toString() {
		return getClass().getName() + "[children = " + children + "]";
	}
}
