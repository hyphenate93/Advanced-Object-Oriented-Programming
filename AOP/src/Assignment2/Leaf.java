package Ex2;

public class Leaf<T> implements Tree<T> {

	private T value;

	public Leaf(T v) {
		value = v;
	}

	public <R, A> R accept(TreeVisitor<T, R, A> v, R res, A acc) {
		return v.visit(this, res, acc);
	}

	public String toString() {
		return getClass().getName() + "[value = " + value + "]";
	}

	public T getValue() {
		return value;
	}
	/*
	public String show(int level) {
		return indent(level) + value.toString();
	}
	
	private String indent(int level) {
		String indent = "";
		for(int i = 0; i < level; i++) {
			indent = indent + "   ";
		}
		return indent;
	}
	*/

}
