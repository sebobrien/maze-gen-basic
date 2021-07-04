package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import analysis.Benchmark;
import gifWriter.GifWriter;
import gifWriter.JavaIOGifWriter;
import gifWriter.LZWCompressor;
import maze.Maze;
import mazeGen.MazeGenFac;
import output.MazeDrawer;

public class lab {

	// "BitSetMaze" "CompositeMaze"
	// "DisjointSetArrayUT", "DisjointSetCompUT" "RecursiveDivAlgo"
	static java.util.List<String> mazeTypes = Arrays.asList("BitSetMaze", "CompositeMaze");
	static java.util.List<String> algoTypes = Arrays.asList("DisjointSetArrayUT", "DisjointSetCompUT",
			"RecursiveDivAlgo");

	public static void run() {
      
		Maze maze1 = MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(2), 6, 6, 0, 0).generateMaze();
//		Maze maze2 = MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(0), 1000, 1000, 0, 49).generateMaze();
//		// MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(1), 10, 10, 0, 9).GenerateAndDraw();
//		try {
//			Draw(maze1,20,algoTypes.get(2), Color.WHITE,Color.BLACK);
//			Draw(maze2,20,algoTypes.get(0), Color.WHITE,Color.BLACK);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//makeGif(maze1);
		GifWriter gifer;
		gifer = new JavaIOGifWriter();
		gifer.writeGif("testGifer2", "mazes/gifs/", MazeDrawer.getImage(maze1, 20,20, Color.WHITE, Color.BLACK));
//		int b = 0b00001111;
//		int code = 0b101010101;
//		int from = 9;
//		int	to = 4;
//		int mask  = ((int) (Math.pow(2, from)-1) << to);
//		int bits = mask & code;
//		b = bits | b;
//		System.out.println(Integer.toBinaryString(b));
	}

	private static void makeGif(Maze maze) {

		try {
			DataOutputStream writer = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream("mazes/gifs/test.gif")));

//			BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
//			Graphics graphics = image.createGraphics();
//			graphics.setColor(Color.white);
//			graphics.fillRect(0, 0, 100, 100);
//			graphics.setColor(Color.RED);
//			graphics.fillRect(0, 0, 5, 3);
//			graphics.fillRect(0, 2, 3, 4);
//			graphics.fillRect(7, 5, 9, 7);
//			graphics.fillRect(5, 7, 10, 900);
//			graphics.setColor(Color.BLUE);
//			graphics.fillRect(5, 0, 9, 3);
//			graphics.fillRect(7, 2, 9, 3);
//			graphics.fillRect(0, 5, 3, 6);
//			graphics.fillRect(0, 7, 999, 10);
//			Color[] ct = new Color[] { Color.WHITE, Color.RED, Color.BLUE, Color.BLACK };

			Color[] ct = new Color[] { Color.WHITE, Color.RED, Color.BLUE, Color.BLACK };
			BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = image.createGraphics();
			graphics.setColor(Color.white);
			graphics.fillRect(0, 0, 10, 10);
			graphics.setColor(Color.RED);
			graphics.fillRect(0, 0, 5, 3);
			graphics.fillRect(0, 2, 3, 4);
			graphics.fillRect(7, 5, 9, 7);
			graphics.fillRect(5, 7, 10, 9);
			graphics.setColor(Color.BLUE);
			graphics.fillRect(5, 0, 9, 3);
			graphics.fillRect(7, 2, 9, 3);
			graphics.fillRect(0, 5, 3, 6);
			graphics.fillRect(0, 7, 5, 10);

//			Color[] ct = new Color[] {Color.BLACK,Color.WHITE};
//			BufferedImage image = MazeDrawer.getImage(maze, 20,20, Color.WHITE, Color.BLACK);
//			ImageIO.write(image, "gif", new File( "mazes/gifs/test2.gif"));		

			writeHeader(writer);
			writeLSD(writer, image.getWidth(), image.getHeight(), 2, 2);
			writeCt(writer, ct);
			WriteGCE(writer, (short) 0, 0, false, false, 0);
			WriteImgDescr(image, writer, 0, 0);
			WriteImage(image, writer, ct);
			writer.writeByte(0x3B);// EOF
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private static void WriteImgDescr(BufferedImage image, DataOutputStream writer, int x, int y) throws IOException {
		int packedByte = 0; // tbd: local ct, interlace flag, sort flag, size local ct
		writer.writeByte(0x2C);
		writer.writeShort(Short.reverseBytes((short) x));
		writer.writeShort(Short.reverseBytes((short) y));
		writer.writeShort(Short.reverseBytes((short) image.getWidth()));
		writer.writeShort(Short.reverseBytes((short) image.getHeight()));
		writer.writeByte(packedByte);

	}

	private static void WriteImage(BufferedImage image, DataOutputStream writer, Color[] ct) throws IOException {
		int[] array = new int[] { -1 };
		byte[] bytes = new LZWCompressor().compress(image, ct, array);
		writer.writeByte(array[0]);
		int tail = bytes.length % 254;
		for (int i = 0; i < Math.floor(bytes.length / 254); i++) {
			writer.writeByte(254);
			for (int j = i * 254; j < (i + 1) * 254; j++) {
				writer.writeByte(bytes[j]);
			}
		}
		if (tail > 0) {
			writer.writeByte(tail);
			for (int i = bytes.length - tail; i < bytes.length; i++) {
				writer.writeByte(bytes[i]);
			}

		}
//		System.out.println(bytes.size());
//		System.out.println(bytes.size());
//		writer.writeByte(bytes.lenght());
//		bytes.writeTo(writer);
		writer.writeByte(0);
	}

	private static void WriteGCE(DataOutputStream writer, short delay, int disposal, boolean hasUserInput,
			boolean hasTransparency, int transColIdx) throws IOException {
		writer.writeByte(0x21);
		writer.writeByte(0xF9);
		writer.writeByte(0x04);// might need to change this to variable size for larger transparency colour
								// index table
		int packedByte = 0;

		switch (disposal) {
		case 0: {
			break;
		}
		case 1: {
			packedByte = packedByte | 0b00000100;
			break;
		}
		case 2: {
			packedByte = packedByte | 0b00001000;
			break;
		}
		case 3: {
			packedByte = packedByte | 0b00001100;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected disposal value: " + disposal);
		}
		if (hasUserInput) {
			packedByte = packedByte | 0b00000010;
		}
		if (hasTransparency) {
			packedByte = packedByte | 0b00000001;
		}
		writer.writeByte(packedByte);
		writer.writeShort(delay);
		writer.writeByte(transColIdx);
		writer.writeByte(0); // terminator byte

	}
//****** add sort flag
	private static void writeLSD(DataOutputStream writer, int w, int h, int ctSize, int bitdepth) throws IOException {

		writer.writeShort(Short.reverseBytes((short) w));
		writer.writeShort(Short.reverseBytes((short) h));
		int packedByte = 0;
		if (ctSize > 0) {
			packedByte = packedByte | 0b10000000;
		}
		if (bitdepth == 8) {
			packedByte = packedByte | 0b01110000;
		} else if (bitdepth == 4) {
			packedByte = packedByte | 0b00110000;
		} else
			packedByte = packedByte | 0b00010000;

		short minBits = (short) Math.ceil(Math.log(ctSize));
		// System.out.println(minBits);
		packedByte = packedByte | minBits; //td fix
		writer.writeByte(packedByte);
		writer.writeByte(0);
		writer.writeByte(0);
	}

	private static void writeCt(DataOutputStream writer, Color[] colours) throws IOException {
		for (Color color : colours) {
			writer.writeByte(color.getRed());
			writer.writeByte(color.getGreen());
			writer.writeByte(color.getBlue());
		}
	}

	private static void writeHeader(DataOutputStream writer) throws IOException {

		writer.writeBytes(("GIF89a"));

	}

	private static void Draw(Maze maze, int sizeFactor, String name, Color mazeColour, Color BackGroundColour)
			throws IOException {

		MazeDrawer.drawMaze(maze, "png", "mazes/", name + ".png", 10, 10, mazeColour, BackGroundColour);
	}

	private static void Bench() {
		Benchmark.printAverageOf(30, 1000, 1000, mazeTypes, algoTypes);
	}

}
