package gifWriter;

public class FrameSequence {
	
	int size;
	Frame first;
	Frame last;
	
	public FrameSequence(Frame firstframe) {
		this.first = firstframe;
		this.last = first;
	}
	
	public void addFrame(Frame frame) {
		this.last.setNext(frame);
		this.last = frame;
		
	};
	
	public Frame[] toFrameArray() {
		Frame[] result = new Frame[size];
		Frame current = first;
		int idx = 0;
		result[idx] = current;
		while (current.hasNext()){
			current = current.getNext();
			result[idx++]=current;
		}
		return result;}
	
	
	

}
