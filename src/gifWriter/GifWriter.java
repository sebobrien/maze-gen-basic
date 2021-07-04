package gifWriter;

import java.awt.image.RenderedImage;

public interface GifWriter {

	void writeGif(String fileName,String fileDir, RenderedImage image);
	void writeAnimatedGif(String fileName,String fileDir, Frame[] frames);
	
}
