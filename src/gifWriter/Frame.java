package gifWriter;

import java.awt.Image;

public class Frame {
	
	private Image image;
	private int delay;
	private Frame next;
	// other fields? eg: alpha...
	public Frame(Image image, int delay) {		
		this.image = image;
		this.delay = delay;
		this.next = null;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}	
	
	public boolean hasNext() {return next != null;}
	
	public Frame getNext() {
		return next;
	}
	public void setNext(Frame next) {
		this.next = next;
	}
	
	
	 

}
