package org.nordmann.lectures.concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * The Class AccumulatorExample1.
 */
public class CompletableFutureExample {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		runRunnableExample();
		
		System.out.println("\n*************************************************************************\n");
		
		runSupplierExample();


	}
	

	
	
	/**
	 * Run runnable example.
	 */
	private static void runRunnableExample(){
		CompletableFuture.runAsync(() -> System.out.println("I am a Runnable"))
		.whenComplete((result, exc) -> System.out.println("Value: " + result));		
	}
	
	
	
	/**
	 * Run supplier example.
	 */
	private static void runSupplierExample(){
		CompletableFuture.supplyAsync(() -> {return "I am a Supplier";})
		.whenComplete((result, exc) -> System.out.println("Result Value: " + result))
		.thenApply(str -> str.substring(7))
		.thenApplyAsync(str -> str += ": So Cool")
		.thenAccept(str -> System.out.println(str));
	}
	

	

}
