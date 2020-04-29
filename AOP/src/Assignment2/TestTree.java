package Ex2;

import java.util.*;

public class TestTree {
	
	public static void main(String[] args) {
		
		List<Tree<String>> leaves1 = new ArrayList<Tree<String>>();
		List<Tree<String>> leaves2 = new ArrayList<Tree<String>>();
		
		leaves1.add(new Leaf<String>("one1"));
		leaves1.add(new Leaf<String>("two1"));
		leaves1.add(new Leaf<String>("three1"));
		
		leaves2.add(new Leaf<String>("one2"));
		leaves2.add(new Leaf<String>("two2"));
		leaves2.add(new Leaf<String>("three2"));
		
		Tree<String> t1 = new Node<String>(leaves1, "subTree1");
		Tree<String> t2 = new Node<String>(leaves2, "subTree2 ");
		
		List<Tree<String>> lst = new ArrayList<Tree<String>>();
		lst.add(t1);
		lst.add(t2);
		lst.add(t1);
		
		Tree<String> t = new Node<String>(lst, "root:");
		
		TreeVisitor<String, String, Integer> llv = new PrettyPrinterVisitor<String>();
		
		List<String> result = new ArrayList<String>();

		result.add(t.accept(llv, 0));
		for(String s : result){
			System.out.println(s);

		}	
    }
}
