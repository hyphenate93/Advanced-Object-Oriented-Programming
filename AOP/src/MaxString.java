import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MaxString implements Comparator<String> {
	
	
	public static String maximum(ArrayList<String> a, Comparator<String> c) {
		
		String Longest = "";
		
		Longest = Collections.max(a, c);
		return Longest;
		
	}
	
	 public static void main(String[] args) {

	        ArrayList<String> test = new ArrayList<String>();
	        test.add("hej");
	        test.add("hej på dig");
	        test.add("kanske");
	        test.add("hello");
	        test.add("this is the longest string and should be returned");
	        test.add("a");
	     
	        Comparator<String> b =new MaxString();
	        
	        String Longestsest = maximum(test, b);
	        System.out.println(Longestsest);
	    }

	 @Override
	    public int compare(String o1, String o2) {
	       
	        if(o1.length()>o2.length())
	            return 1;
	        else if(o1.length()<o2.length())
	            return -1;
	        else
	            return 0;

	    }

}
