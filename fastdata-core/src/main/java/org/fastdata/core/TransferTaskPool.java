package org.fastdata.core;


import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/*
 * thread-safe
 */

public class TransferTaskPool extends ThreadPoolExecutor {

	public static TransferTaskPool initTransferTaskPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, SynchronousQueue<Runnable> workQueue) {
		TransferTaskPool transferTaskPool = new TransferTaskPool(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue);
		return transferTaskPool;
	}

	private TransferTaskPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			SynchronousQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, ThreadFactoryForThreadPool
				.createThreadFactory("liuxinTransferThreadPool"), LocalRejectedExecutionHandler.init());
		super.allowCoreThreadTimeOut(true);
		super.prestartCoreThread();
	}
}
