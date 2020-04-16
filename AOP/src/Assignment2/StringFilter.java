package Assignment2;

public class StringFilter extends Filter{
	String toCheck = "";
	public StringFilter(String x){
		 toCheck = x;
	}
	
	@Override
	 boolean accept() {
		if (toCheck.length() <= 3) {

			return true;

		}

		else {

			return false;

		}
	}
	

	public static void main(String[] args) {
		String notWorking = "hejsan";
		String works = "hej";
		
		 Filter filter  = new StringFilter(notWorking);
		 Filter filter2  = new StringFilter(works);
		System.out.println(filter.beginFilter()); 
		System.out.println(filter2.beginFilter()); 
		
	}
	

	
	
}
