package Ex2;


public class PrettyPrinterVisitor<T> implements TreeVisitor<T, String, Integer> {
	
	private String result = "";
	
	public void visit(Tree<T> t, Integer level) {	
		t.accept(this, level);
	}
	
	public String visit(Leaf<T> l, Integer level) {
		result = result + indent(level) + (String) l.getValue()+ "\n";
		return result;
	}

	public String visit(Node<T> n, Integer level) {
		result = result + indent(level) + (String) n.getValue()+ "\n";
		for (Tree<T> c : n.getChildren()) {
			c.accept(this, level + 1);
		}
		return result;
	}
		
	public String indent(Integer level) {
		String result = "";
		for(int i = 0; i < level; i++) {
			result = result + "   ";
		}
		return result;
	}
}
