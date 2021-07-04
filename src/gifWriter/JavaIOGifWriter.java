package gifWriter;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JavaIOGifWriter implements GifWriter {
	
	

	
	public void writeGif(String fileName, String fileDir, RenderedImage image) {
		try {
			ImageIO.write(image, "gif", new File( fileDir+"/"+fileName+".gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	public void writeAnimatedGif(String fileName, String fileDir, Frame[] frames) {
		// TODO Auto-generated method stub

	}

}
