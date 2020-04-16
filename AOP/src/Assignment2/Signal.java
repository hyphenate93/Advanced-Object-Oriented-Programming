package assignment2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
public class Signal {

    private ArrayList<Sampler> myObservers;
    private Sampler sample;

    public void addObserver(Sampler s) {
        myObservers.add(s);
    }

    public Signal(Sampler sample) {
        myObservers = new ArrayList<Sampler>();
        this.sample = sample;

        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextValue(sample.read());
            }
        });
        t.start();

    }

    private void nextValue(double x) {
        // update amplitude depending on what sample strategy we are using
        for (Sampler so : myObservers) {
            so.updateSignal(x);
        }
    }

	public void setSampler(Sampler sinus) {
		sample = sinus;
		
	}

}