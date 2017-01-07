package org.fastdata.core;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


/*
 * thread-safe
 */

public class LocalRejectedExecutionHandler implements RejectedExecutionHandler {

	public static LocalRejectedExecutionHandler init() {
		LocalRejectedExecutionHandler localRejectedExecutionHandler = new LocalRejectedExecutionHandler();
		return localRejectedExecutionHandler;
	}

	private LocalRejectedExecutionHandler() {
		super();
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		if(executor.getQueue().offer(r))
			return;
		StaticForAllThreadPool.REJECTED_THREAD_POOL.submit(r);
	}
}
