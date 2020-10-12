package mazeGenAlgo;

import java.util.Arrays;
import java.util.Random;

public class RandomIntegerQueue {
	
	private int[] integers;
	private int lastInt;
	private Random random;
	
	public RandomIntegerQueue(int size) {
		this.random = new Random();
		this.integers =  new int[size];
		Arrays.setAll(integers, i -> i);
		lastInt = --size;
	}
	
	public int Deque() {
		int i = random.nextInt(lastInt+1);
		int result = integers[i];
		integers[i] = integers[lastInt];
		lastInt -= 1;		
		return result;
	}

}
