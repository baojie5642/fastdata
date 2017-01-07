package org.fastdata.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/*
 * thread-safe
 */

public class RejectedThreadPool extends ThreadPoolExecutor {

	public static RejectedThreadPool initRejectedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		RejectedThreadPool rejectedThreadPool = new RejectedThreadPool(corePoolSize, maximumPoolSize, keepAliveTime,
				unit, workQueue);
		return rejectedThreadPool;
	}

	private RejectedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, ThreadFactoryForThreadPool
				.createThreadFactory("LiuXinRejectedThreadPool"), new ThreadPoolExecutor.CallerRunsPolicy());
		super.allowCoreThreadTimeOut(true);
		super.prestartCoreThread();
	}
}
