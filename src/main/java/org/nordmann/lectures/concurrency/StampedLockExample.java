package org.nordmann.lectures.concurrency;

import java.util.concurrent.locks.StampedLock;

/**
 * The Class StampedLockExample.
 */
public class StampedLockExample {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		StampedLock lock = new StampedLock();
		
		long stamp = lock.tryOptimisticRead(); // non blocking
		read();
		if (!lock.validate(stamp)) { // if a write occurred, try again with a read lock
		 
		    stamp = lock.readLock();
		 
		    try {
		        read();
		    } finally {
		        lock.unlock(stamp);
		    }
		}
	}

	/**
	 * Read.
	 */
	private static void read() {
		// TODO Do some reading from a cache of some sort
	}

}
