package assignment2;

public class StarObserver implements Sampler {

	
	
	private static void printStars(int num) {
		
		for(int i =0; i <num; i++) {
			System.out.print("*");
		}
		System.out.println();
		
	}


	@Override
	public void updateSignal(double amp) {
		printStars((int)amp);

	}


	@Override
	public double read() {
		// TODO Auto-generated method stub
		return 0;
	}

}
