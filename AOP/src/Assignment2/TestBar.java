package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBar {

	public static void main(String[] args) {
		List<Double> addAll =  Arrays.asList(10.0,11.0,15.0,20.0);

		 ArrayList<Double> listForModel = new ArrayList<Double>();
		 listForModel.addAll(addAll);
		DataModel model = new DataModel(listForModel);

		  TextFrame frame = new TextFrame(model);

		BarFrame barFrame = new BarFrame(model);

		model.attach(barFrame);
		model.attach(frame);

	}
}
