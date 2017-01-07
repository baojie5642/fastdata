package org.fastdata.core;


import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/*
 * thread-safe
 */

public class ExportTaskPool extends ThreadPoolExecutor {

	public static ExportTaskPool initExportTaskPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, SynchronousQueue<Runnable> workQueue) {
		ExportTaskPool exportTaskPool = new ExportTaskPool(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue);
		return exportTaskPool;
	}

	private ExportTaskPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			SynchronousQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, ThreadFactoryForThreadPool
				.createThreadFactory("liuxinExportThreadPool"), LocalRejectedExecutionHandler.init());
		super.allowCoreThreadTimeOut(true);
		super.prestartCoreThread();
	}
}
