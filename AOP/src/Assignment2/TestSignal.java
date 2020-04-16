package assignment2;

import javax.swing.JTextArea;


public class TestSignal {
    public static void main(String[] args) {
        Sampler strategy = new DefaultSampler();
        Sampler sinus = new SinusSampler(0, 0.1);
        Signal theSignal = new Signal(strategy);
        theSignal.addObserver(new StarObserver());
        theSignal.addObserver(new SignalWindow());
    }
}