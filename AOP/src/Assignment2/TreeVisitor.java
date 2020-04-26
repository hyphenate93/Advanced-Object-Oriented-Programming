package Ex2;


public interface TreeVisitor<T, R, A> {
		
	R visit(Tree<T> t, R res, A acc);

	R visit(Leaf<T> l, R res, A acc);

	R visit(Node<T> n, R res, A acc);
	
	void show(R res, A acc);
}
