package org.fastdata.core;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

//不采用单例模式，因为一个线程池对应一个线程工厂，这样比较合适
public class ThreadFactoryForThreadPool implements ThreadFactory {
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final AtomicLong threadNumber = new AtomicLong(1);
	private final String namePrefix;
	private final String factoryName;
	private final UncaughtExceptionHandler unCaughtExceptionHandler = new ThreadUncaughtExceptionHandler();

	private ThreadFactoryForThreadPool(final String name) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		factoryName = name;
		namePrefix = factoryName + "-" + poolNumber.getAndIncrement() + "-thread-";
	}

	public static ThreadFactoryForThreadPool createThreadFactory(final String name) {
		ThreadFactoryForThreadPool threadFactoryForThreadPool = null;
		threadFactoryForThreadPool = new ThreadFactoryForThreadPool(name);
		return threadFactoryForThreadPool;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		if (t.isDaemon())
			t.setDaemon(false);
		if (t.getPriority() != Thread.NORM_PRIORITY)
			t.setPriority(Thread.NORM_PRIORITY);
		t.setUncaughtExceptionHandler(unCaughtExceptionHandler);
		return t;
	}
}
