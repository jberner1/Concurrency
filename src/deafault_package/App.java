//Author: Jennifer Berner
//Date:6/20/2020
//Module 8 Concurrency Assignment 
package deafault_package;
import java.util.Random;

public class App extends Thread {
	private int[] nums;
	private int low;//low index ,start of interval
	private int high;//high index , end of interval
	private int partialSum;//sum within the range of the low index and the high index
	public App(int[] nums, int low, int high){
		this.nums = nums;
		this.low = low; 
		this.high = high; 
	}

	public int getPartialSum(){ //getter
		return this.partialSum;
	}

	public void run(){ // need to implement run 
		partialSum = sum(nums, low, high);
	}

	public static int sum(int[] arr){
		return sum(arr, 0, arr.length);
	}

	public static int sum(int[] nums, int low, int high){
		int total = 0;
		for (int i = low; i < high; i++) {
			total = total + nums[i];
		}
		return total;
	}

	public static int parallelSum(int[] nums){
		return parallelSum(nums, Runtime.getRuntime().availableProcessors());
	}

	public static int parallelSum(int[] nums, int numOfThreads){
		int steps = (int) Math.ceil(nums.length * 1.0 / numOfThreads); //divide the lengths of the one dimensional array we would like to sum up by the number of threads.
		App[] sums = new App[numOfThreads];

		for (int i = 0; i < numOfThreads; i++) {
			sums[i] = new App(nums, i*steps, (i+1)*steps);// i*steps is low index and (i+1)*steps is the high index
			sums[i].start(); 
		}
		try { //Java thread join method can be used to pause the current thread execution until the specified thread is dead.
			for (App sum : sums) {
				sum.join();
			}
		} catch (InterruptedException e) {//join needs a catch block
			e.printStackTrace();
		} 
		int total = 0; 
		for (App sum : sums) {
			total = total + sum.getPartialSum();
		}
		return total;
	}
}

