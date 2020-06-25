//Author: Jennifer Berner
//Date:6/20/2020
//Module 8 Concurrency Assignment
package deafault_package;
import java.util.Random;

public class Main {

	public static void main(String[] args){
		Random rand = new Random();
		int[] num = new int[200000000]; //200 million

		for (int i = 0; i < num.length; i++) {
			num[i] = rand.nextInt(100);
		}

		long start = System.currentTimeMillis();
		App.sum(num);
		//showing the sum and time for single thread
		System.out.println("Single thread sum:" + (App.sum(num)) +" ,with the time of: "+ (System.currentTimeMillis() - start) +"ms");

		start = System.currentTimeMillis();
		App.parallelSum(num);
		//showing the sum and time for parallel thread
		System.out.println("Parallel thread sum:" + (App.parallelSum(num)) +" ,with the time of: "+ (System.currentTimeMillis() - start) +"ms");
	}
}
