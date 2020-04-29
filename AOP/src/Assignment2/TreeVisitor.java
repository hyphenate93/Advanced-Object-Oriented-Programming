package Ex2;


public interface TreeVisitor<T, R, A> {
		
	void visit(Tree<T> t, A acc);

	R visit(Leaf<T> l, A acc);

	R visit(Node<T> n, A acc);
}
