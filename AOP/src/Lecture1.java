

public class Lecture1 {

	public static void main(String[] args) {
		Matrix test = new Matrix(5,4);
		Matrix test2 = new Matrix(4,2);
		Matrix result ;
	
		for(int i = 0; i < test.getRows(); i++)
	    {
	        for(int j = 0; j < test.getColumns(); j++)
	        {
	        	test.setElement(4,i,j);
	        }
	    }
	    
		for(int i = 0; i < test2.getRows(); i++)
	    {
	        for(int j = 0; j < test2.getColumns(); j++)
	        {
	        	test2.setElement(3,i,j);
	        }
	    }
		
		result = Matrix.Multiply(test,test2);
		
		for(int i = 0; i < result.getRows(); i++)
	    {
	        for(int j = 0; j < result.getColumns(); j++)
	        {
	        	System.out.println(result.getElement(i,j));
	        	
	        }
	    }
	 
		}	
	}
	

	
	



