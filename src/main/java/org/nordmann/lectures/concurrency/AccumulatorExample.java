package org.nordmann.lectures.concurrency;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

// TODO: Auto-generated Javadoc
/**
 * The Class AccumulatorExample1.
 */
public class AccumulatorExample {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		System.out.println("Example 1");
		example1((x, y) -> x + y);
		
		System.out.println("\n********************************************\n");
		System.out.println("Example 2");
		example2((x, y) -> x >= y ? x : y);

	}
	
	
	/**
	 * Example1.
	 *
	 * @param op the op
	 */
	private static void example1(LongBinaryOperator op){
		LongAccumulator maxAccumulator = new LongAccumulator(op, 1L);
		maxAccumulator.accumulate(1);
		System.out.println(maxAccumulator.longValue());
		maxAccumulator.accumulate(2);
		System.out.println(maxAccumulator.longValue());	
	}
	
	
	/**
	 * Example2.
	 *
	 * @param op the op
	 */
	private static void example2(LongBinaryOperator op){
		LongAccumulator maxAccumulator = new LongAccumulator(op, Long.MIN_VALUE);
		maxAccumulator.accumulate(100);
		System.out.println(maxAccumulator.longValue());
		maxAccumulator.accumulate(20);
		System.out.println(maxAccumulator.longValue());	
	}

}
