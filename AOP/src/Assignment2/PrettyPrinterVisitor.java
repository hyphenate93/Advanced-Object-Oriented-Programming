package Ex2;

import java.util.*;

public class PrettyPrinterVisitor<T> implements TreeVisitor<T, List<String>, List<Integer>> {

	
	public List<String> visit(Tree<T> t, List<String> result, List<Integer> level) {
		return t.accept(this, result, level);
	}

	
	public List<String> visit(Leaf<T> l, List<String> result, List<Integer> level) {
		level.add(2);
		result.add((String) l.getValue());
		return result;
	}

	
	public List<String> visit(Node<T> n, List<String> result, List<Integer> level) {
		level.add(1);
		result.add((String) (n.getValue()));
		for (Tree<T> c : n.getChildren()) {
			c.accept(this, result, level);
		}
		return result;
	}
	
	
	private String indent(int level) {
		String indent = "";
		for(int i = 0; i < level; i++) {
			indent = indent + "    ";
		}
		return indent;
	}
	
	public void show(List<String> result, List<Integer> level) {
		List<String> pp = new ArrayList<String>();
		level.set(0, 0);
		for(int i = 0; i < level.size(); i++){
			pp.add(indent(level.get(i)) + result.get(i));
		}
		for(int i = 0; i < pp.size(); i++){
			System.out.println(pp.get(i));
		}
	}
}
