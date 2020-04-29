package Ex2;

public class Leaf<T> implements Tree<T> {

	private T value;

	public Leaf(T v) {
		value = v;
	}

	public <R, A> R accept(TreeVisitor<T, R, A> v, A acc) {
		return v.visit(this, acc);
	}

	public String toString() {
		return getClass().getName() + "[value = " + value + "]";
	}

	public T getValue() {
		return value;
	}
	

}
