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

		CompletableFuture<Void> runnableExample = runRunnableExample();
		
		System.out.println("\n*************************************************************************\n");
		
		CompletableFuture<Void> supplierExample = runSupplierExample();
		
		//wait for all to finish
		CompletableFuture.allOf(runnableExample, supplierExample).join();


	}
	

	
	
	/**
	 * Run runnable example.
	 * @return 
	 */
	private static CompletableFuture<Void> runRunnableExample(){
		return CompletableFuture.runAsync(() -> System.out.println("I am a Runnable"))
		.whenComplete((result, exc) -> System.out.println("Value: " + result));		
	}
	
	
	
	/**
	 * Run supplier example.
	 * @return 
	 */
	private static CompletableFuture<Void> runSupplierExample(){
		return CompletableFuture.supplyAsync(() -> {return "I am a Supplier";})
		.whenComplete((result, exc) -> System.out.println("Result Value: " + result))
		.thenApply(str -> str.substring(7))
		.thenApplyAsync(str -> str += ": So Cool")
		.thenAccept(str -> System.out.println(str));
	}
	

	

}
