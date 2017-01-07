package org.fastdata.core;


import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/*
 * thread-safe
 */

public class ExtractTaskPool extends ThreadPoolExecutor {

	public static ExtractTaskPool initExtractTaskPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, SynchronousQueue<Runnable> workQueue) {
		ExtractTaskPool extractTaskPool = new ExtractTaskPool(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue);
		return extractTaskPool;
	}

	private ExtractTaskPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			SynchronousQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, ThreadFactoryForThreadPool
				.createThreadFactory("liuxinExtractThreadPool"), LocalRejectedExecutionHandler.init());
		super.allowCoreThreadTimeOut(true);
		super.prestartCoreThread();
	}
}
