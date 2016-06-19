package org.nordmann.lectures.concurrency;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ForkJoinPool;

/**
 * The Class CompletableFutureSupplier.
 */
public class CompletableFutureSupplier {

	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		CompletionStage<Integer> cf = CompletableFutureSupplier.getStockInfo("ILS");
		cf.whenComplete((result, exc) -> System.out.println("Result of Calculation: " + result));
	}	
	
	/**
	 * Gets the stock info.
	 *
	 * @param str the str
	 * @return the stock info
	 */
	public static CompletionStage<Integer> getStockInfo(String str){
		CompletableFuture<Integer> future = new CompletableFuture<Integer>();
		Runnable task = new Runnable() {
			public void run() {
				try{
					Integer result = doSomethingReallyNasty();
					future.complete(result);
				}catch(Exception exception){
					future.completeExceptionally(exception);
				}
			}
		};
		ForkJoinPool.commonPool().submit(task);
		return future;
	}
	
	
	/**
	 * Do something really nasty.
	 *
	 * @return the integer
	 */
	private static Integer doSomethingReallyNasty(){
		//do some really long calculation here...
		return new Random().nextInt();
	}

}
