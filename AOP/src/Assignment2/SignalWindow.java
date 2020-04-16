package assignment2;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SignalWindow extends JFrame implements Sampler{
	
	private JTextArea jta;
	public SignalWindow() {
		
		jta = new JTextArea(100,20);
		jta.append("The Signal amplitude. \n");
		add(jta);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public JTextArea getTextArea() {
		return jta;
	}



	@Override
	public double read() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateSignal(double amp) {
		jta.append(""+String.format("%.6f", amp)+"\n");
	}

}
