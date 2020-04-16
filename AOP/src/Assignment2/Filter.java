package Assignment2;

abstract class Filter {
	
	abstract boolean accept();
	
	//template method
	   public final boolean beginFilter(){
		  
		 return  accept();

}
}
