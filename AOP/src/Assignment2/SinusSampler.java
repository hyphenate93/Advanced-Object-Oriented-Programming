package assignment2;

/**
 * @author {Andreas}
 *
 */
public class SinusSampler implements Sampler {

	private double arg;
	private double delta;
	
	public SinusSampler(double start, double delta) {
		
		arg = start;
		this.delta = delta;
	}
	
	@Override
	public double read() {
		
		double x = arg;
		arg +=delta;
		
		return 60 + Math.sin(x) * 30;
	}

	@Override
	public void updateSignal(double amp) {
		// TODO Auto-generated method stub
		
	}

}
