package org.fastdata.core;

import java.util.concurrent.ScheduledThreadPoolExecutor;


/*
 * thread-safe
 */

public class ScheduledTaskPool extends ScheduledThreadPoolExecutor {

	public static ScheduledTaskPool initScheduledTaskPool(int corePoolSize) {
		ScheduledTaskPool scheduledTaskPool = new ScheduledTaskPool(corePoolSize);
		return scheduledTaskPool;
	}

	private ScheduledTaskPool(int corePoolSize) {
		super(corePoolSize, ThreadFactoryForThreadPool.createThreadFactory("liuxinScheduledThreadPool"), LocalRejectedExecutionHandler
				.init());
		super.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		super.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
		super.setRemoveOnCancelPolicy(true);
		// super.setKeepAliveTime(200, TimeUnit.SECONDS);
		// super.allowCoreThreadTimeOut(true);
	}
}
