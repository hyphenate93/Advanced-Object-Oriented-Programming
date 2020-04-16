package assignment2;

public class DefaultSampler implements Sampler {

	@Override
	public double read() {
		double random = Math.random() * 10;
		return random;
	}

	@Override
	public void updateSignal(double amp) {
		// TODO Auto-generated method stub
		
	}

}
