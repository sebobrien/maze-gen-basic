package gifWriter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LZWCompressor {

	private BufferedImage image;
	private HashMap<ArrayList<Integer>, Integer> codeTable;
	private HashMap<ArrayList<Integer>, Integer> codeTableCopy;
	private ArrayList<Integer> indexBuffer;

	private Color[] colourTable;
	private int[] codeCount = { -1, -1, 4, 8, 16, 32, 64, 128, 256 };

	private ByteArrayOutputStream codeStream;
	private int minCodeSize;
	private int clear;
	private int EOF;
	private int CODE_CTR;

	public LZWCompressor() {

	}

	public byte[] compress(BufferedImage image, Color[] colourTable, int[] array) throws IOException {
		this.image = image;
		this.colourTable = colourTable;
		toCodeBits();
		// toUncompressedBytes();
		byte[] result = null;
		try {
			result = toBytes(array);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;

	}

	private void toUncompressedBytes() throws IOException {
		codeStream = new ByteArrayOutputStream();
		DataOutputStream result = new DataOutputStream(codeStream);

		minCodeSize = 2;
		for (int i = 2; codeCount[i - 1] < colourTable.length; i++) {
			minCodeSize = i;
		}

		this.codeTable = new HashMap<ArrayList<Integer>, Integer>();
		this.codeTableCopy = new HashMap<ArrayList<Integer>, Integer>();

		clear = codeCount[minCodeSize];
		// writeShort((short) clear, result);
		EOF = clear + 1;
		for (int i = 0; i < EOF + 1; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			row.add(i);
			codeTable.put(row, i);
		}
		codeTableCopy.putAll(codeTable);
		int iW = image.getWidth();
		int iH = image.getHeight();
		int minX = image.getMinX();
		int minY = image.getMinY();
		int[] indexStream = image.getData().getPixels(0, 0, iW, iH, (int[]) null); // TD: add control measures for
		int ctr = EOF + 1; // raster minX+minY
		// start algo
		for (int i = 0; i < indexStream.length; i += 4) {

			int r = indexStream[i];
			int g = indexStream[i + 1];
			int b = indexStream[i + 2];
			int a = indexStream[i + 3];// TD: add alpha support
			int k = -1;
			for (int j = 0; j < colourTable.length; j++) {// TD change to HashMap for larger colour tables for speedy
															// lookup
				Color c = colourTable[j];
				if (c.getRed() == r && c.getBlue() == b && c.getGreen() == g) {
					k = j;

					break;
				}
			}

			writeShort(k, result);
		}

	}

	private void toCodeBits() throws IOException {
		codeStream = new ByteArrayOutputStream();
		DataOutputStream result = new DataOutputStream(codeStream);

		minCodeSize = 2;
		for (int i = 2; codeCount[i - 1] < colourTable.length; i++) {
			minCodeSize = i;
		}
		//minCodeSize = 8;
		this.codeTable = new HashMap<ArrayList<Integer>, Integer>();
		this.codeTableCopy = new HashMap<ArrayList<Integer>, Integer>();
		this.indexBuffer = new ArrayList<Integer>();
		clear = codeCount[minCodeSize];
		//result.writeShort(clear);
		EOF = clear + 1;
		for (int i = 0; i < EOF + 1; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			row.add(i);
			codeTable.put(row, i);
		}
		codeTableCopy.putAll(codeTable);
		int iW = image.getWidth();
		int iH = image.getHeight();
		int minX = image.getMinX();
		int minY = image.getMinY();
		int[] indexStream = image.getData().getPixels(0, 0, iW, iH, (int[]) null); // TD: add control measures for
		int ctr = EOF + 1; // raster minX+minY
		// start algo
		for (int i = 0; i < indexStream.length; i += 4) {

			int r = indexStream[i];
			int g = indexStream[i + 1];
			int b = indexStream[i + 2];
			int a = indexStream[i + 3];// TD: add alpha support
			int k = -1;
			for (int j = 0; j < colourTable.length; j++) {// TD change to HashMap for larger colour tables for speedy
															// lookup
				Color c = colourTable[j];
				if (c.getRed() == r && c.getBlue() == b && c.getGreen() == g) {
					k = j;
					indexBuffer.add(k);
					break;
				}
			}
			if (!codeTable.containsKey(indexBuffer)) {
				codeTable.put(indexBuffer, ctr++);
				int idx = codeTable.get(indexBuffer.subList(0, indexBuffer.size() - 1));
				int reverse = Short.reverseBytes((short) idx);
				System.out.println(ctr - 1 + ": [" + to12bit(idx) + "] ");

				//writeShort(idx, result);//
				
				result.writeShort(idx);
				indexBuffer = new ArrayList<Integer>();
				indexBuffer.add(k);
			}
			if (ctr == 4096) {
				codeTable.putAll(codeTableCopy);
				result.writeShort(clear);
				ctr = 0;
			}
		}
		// System.out.println(codeTable.get(indexBuffer));
		result.writeShort(codeTable.get(indexBuffer));
		
		result.writeShort(EOF);
		result.close();

	}

	private byte[] toBytes(int[] array) throws Exception {

		CodeBitStream bits = new CodeBitStream(codeStream);
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		int codeSize = minCodeSize + 1;
		int byt = 0;
		int bitCtr = 0;		
		byt = 0;
		System.out.println("       " + to8bit(byt));
		while (bits.hasNextBit()) {
			if (bitCtr == 7) {
				System.out.println(" " + byt + ":" + Integer.toHexString(byt) + ":" + to8bit(byt));
				result.write(byt);
				byt = 0;
				bitCtr = 0;
				
			}
			int bit = bits.readNextBit() << bitCtr;
			byt = byt | bit;
			System.out.println("       " + to8bit(byt) + " b:" + to8bit((int) bits.buffer) + " "
//				+				to8bit((int) bits.inputStream[bits.idx])
						
			);
			bitCtr++;	
		}

		// **** what if clear?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*****

//		result.write(byt);
		array[0] = minCodeSize;
		result.close();
		return result.toByteArray();

	}

	void writeShort(int b, DataOutputStream out) throws IOException {
		if (b > 0xff) {
			out.write((byte) ((b >> 8) & 0xff));
		}
		
		out.write((byte) (b & 0xff));
//
//		out.write((byte) (b & 0xff));
//		if (b > 0xff) {
//			out.write((byte) ((b >> 8) & 0xff));
//		}
	}

	class CodeBitStream {
		byte[] inputStream;
		byte buffer;
		int i;
		int idx;
		int codeBit;
		int codeSize;
		int codeCtr;
		private boolean hasNext;

		public CodeBitStream(ByteArrayOutputStream codeStream) throws Exception {
			idx = 0;
			codeSize = minCodeSize +1;
			codeBit = codeSize;
			codeCtr = codeCount[minCodeSize]+1;
			this.inputStream = codeStream.toByteArray();
			if (hasNextByte()) {
				readBuffer();

			}

		}

		public boolean nextByte() {
			if (hasNext) {
				readBuffer();
				return true;
			} else {
				return false;
			}
		}

		private byte readNextBit() throws IOException, Exception {
			byte result = -1;
			
			if (codeBit == 0) {
				readBuffer();
				codeBit = codeSize;
				codeCtr++;				
				result = (byte) ((buffer & (1 << i)) >> i);
				
			}
			else if (i == 7) {
				readBuffer();				
				result = (byte) ((buffer & (1 << i)) >> i);
				codeBit--;
			}else {
				result = (byte) ((buffer & (1 << i)) >> i);
				
				codeBit--;
			}
			//System.out.println(test);
			i++;
			return result;
		}

		private void readBuffer() {			
			if (hasNextByte()) {
				buffer = inputStream[idx];
				this.i = 0;
			} else {
				buffer = 0;
			}
			idx++;
			if (codeCtr == (1 << codeSize)) {
				codeSize = codeSize == 12 ? 0 : codeSize + 1; System.out.println("+++++codeSize+1 = "+codeSize+" codeCtr:"+codeCtr+"++++");
			}

		}

		public boolean hasNextByte() {
			hasNext = idx < (inputStream.length);			
			return hasNext;
		}

		public boolean hasNextBit() {
			return i < 7 || hasNextByte();
		}

	}

	private String to8bit(Integer i) {
		String string = String.format("%8s", Integer.toBinaryString(i));
		String result = string.replace(' ', '0');
		return result;
	};

	private String to16bit(Integer i) {
		String string = String.format("%16s", Integer.toBinaryString(i));
		String result = string.replace(' ', '0');
		return result;
	};

	private String to12bit(Integer i) {
		String string = String.format("%12s", Integer.toBinaryString(i));
		String result = string.replace(' ', '0');
		return result;
	};

}
//System.out.println(

//System.out.println("       " + to8bit(byt));