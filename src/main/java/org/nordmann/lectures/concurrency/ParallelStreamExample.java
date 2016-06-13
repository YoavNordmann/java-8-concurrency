package org.nordmann.lectures.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * The Class StreamExample4.
 */
public class ParallelStreamExample {

	
	/** The test data count. */
	public static int TEST_DATA_COUNT = 10;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		List<Integer> list = getTestData(TEST_DATA_COUNT);
		
		System.out.printf("Running Parallel Stream test on %s-core machine. Test Data Size: %s%n%n", 
				Runtime.getRuntime().availableProcessors(), TEST_DATA_COUNT);
		
		System.out.printf("\nSingle Stream Version Time: %.3f seconds.%n", timingTest(list.stream()));
		
		System.out.println("\n\n***********************************************************************\n");
		
		System.out.printf("\nParallel Stream Version Time: %.3f seconds.%n", timingTest(list.parallelStream()));
		
	}


	/**
	 * Timing test.
	 *
	 * @param testStream the test stream
	 * @return the double
	 */
	private static double timingTest(Stream<Integer> testStream) {
		long startTime = System.nanoTime();
		testStream.forEach(i -> doSlowOp(i));
		long endTime = System.nanoTime();
		return deltaSeconds(startTime, endTime);
	}

	
	/**
	 * Timing test ordered.
	 *
	 * @param testStream the test stream
	 * @return the double
	 */
	private static double timingTestOrdered(Stream<Integer> testStream) {
		long startTime = System.nanoTime();
		testStream.forEachOrdered(i -> doSlowOp(i));
		long endTime = System.nanoTime();
		return deltaSeconds(startTime, endTime);
	}


	/**
	 * Delta seconds.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return the double
	 */
	private static double deltaSeconds(long startTime,	long endTime) {
		return((endTime - startTime) / 1000000000);
	}

	
	/**
	 * Do slow op.
	 *
	 * @param i the i
	 */
	static void doSlowOp(Integer i) {
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.print(i + " ");
		} catch (InterruptedException ie) {
			// Nothing to do here.	
		}
	}


	/**
	 * Gets the test data.
	 *
	 * @param num the num
	 * @return the test data
	 */
	static List<Integer> getTestData(int num){

		List<Integer> list = new LinkedList<>();

		for (int i = 0; i < num; i++) {
			list.add(new Integer(i));
		}

		return list;
	}

}
