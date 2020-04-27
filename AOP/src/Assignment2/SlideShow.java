package Assignment2;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author andreas
 *
 */
public class SlideShow extends Presenter {

	private int count = -1;
	private String[] pictures;
	private String[] descriptions;
	private JComponent pictureComponent = null;
	
	public static void main(String[] args) {
		
		 String[] texts = { "picture 1", "picture 2", "picture 3" };
		 String image1 = "C:/Year2/AOP/Advanced-Object-Oriented-Programming/AOP/src/Assignment2/pictures/cow.JPG";
		 String image2 = "C:/Year2/AOP/Advanced-Object-Oriented-Programming/AOP/src/Assignment2/pictures/insect.JPG";
		String image3 = "C:/Year2/AOP/Advanced-Object-Oriented-Programming/AOP/src/Assignment2/pictures/sky.JPG";
		 String[] pics = {image1,image2,image3};
		new SlideShow(pics,texts);
		
		
	}

	SlideShow(String[] IncPictures,String[] IncDescriptions){
		 pictures = IncPictures;
		 descriptions = IncDescriptions;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == north) {
			northButtonPressed();
		}
		if (e.getSource() == west) {
			westButtonPressed();
		}
		if (e.getSource() == east) {
			eastButtonPressed();
		}
		if (e.getSource() == south) {
			southButtonPressed();
		}

	}

	@Override
	public Component createCenterComponent() {
		 ImageIcon image ;
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.BLACK);
		image = new ImageIcon();
		setLabel(new JLabel(image));
		imagePanel.add(getLabel());
		
return imagePanel;
		
	} 
	public void showImage(String imageName) {
		
		ImageIcon icon = new ImageIcon(imageName);
		icon.getImage().flush();
		getLabel().setIcon(icon);
		getLabel().revalidate();
		getLabel().repaint();
		
	}
	@Override
	public void northButtonPressed() {
		count++;
		if(count== 3) {
			count = 0;
		} 
		
	

		showText(descriptions[count]);
		showImage(pictures[count]);
		
	}

	@Override
	public void eastButtonPressed() {

		
		
	}

	@Override
	public void southButtonPressed() {
		count--;
		if(count  == -2 || count == -1) {
		count = 2;
		}
		

		showText(descriptions[count]);
		showImage(pictures[count]);
		

	}
	

	@Override
	public void westButtonPressed() {

		
	}

}