package threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

public class TestCallableThreadPoolExecutor {
	public static void main(String[] args) {

		// start
		System.out.println("Program starts");

		// 创建一个可重用固定线程数的线程池
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 1L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
				Executors.defaultThreadFactory(), new AbortPolicy());

		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		MyCallable t1 = new MyCallable("1");
		MyCallable t2 = new MyCallable("2");
		MyCallable t3 = new MyCallable("3");
		MyCallable t4 = new MyCallable("4");
		MyCallable t5 = new MyCallable("5");
		MyCallable t6 = new MyCallable("6");
		MyCallable t7 = new MyCallable("7");
		MyCallable t8 = new MyCallable("8");

		// 将线程放入池中进行执行
		Future<String> result1 = null;
		try {
			result1 = pool.submit(t1);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t1.getName());
		}

		Future<String> result2 = null;
		try {
			result2 = pool.submit(t2);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t2.getName());
		}

		Future<String> result3 = null;
		try {
			result3 = pool.submit(t3);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t3.getName());
		}

		Future<String> result4 = null;
		try {
			result4 = pool.submit(t4);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t4.getName());
		}

		Future<String> result5 = null;
		try {
			result5 = pool.submit(t5);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t5.getName());
		}

		Future<String> result6 = null;
		try {
			result6 = pool.submit(t6);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t6.getName());
		}

		Future<String> result7 = null;
		try {
			result7 = pool.submit(t7);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t7.getName());
		}

		Future<String> result8 = null;
		try {
			result8 = pool.submit(t8);
		} catch (RejectedExecutionException e) {
			System.out.println("Reject task: " + t8.getName());
		}

		// print out queue info
		System.out.println("tasks in queue: " + pool.getQueue().size());

		// try print out result
		try {
			System.out.println(result1.get());
		} catch (ExecutionException e) {
			System.out.println("Result 1 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 1 exception: " + e.getMessage());
		}

		try {
			System.out.println(result2.get());
		} catch (ExecutionException e) {
			System.out.println("Result 2 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 2 exception: " + e.getMessage());
		}

		try {
			System.out.println(result3.get());
		} catch (ExecutionException e) {
			System.out.println("Result 3 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 3 exception: " + e.getMessage());
		}

		try {
			System.out.println(result4.get());
		} catch (ExecutionException e) {
			System.out.println("Result 4 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 4 exception: " + e.getMessage());
		}

		try {
			System.out.println(result5.get());
		} catch (ExecutionException e) {
			System.out.println("Result 5 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 5 exception: " + e.getMessage());
		}

		try {
			System.out.println(result6.get());
		} catch (ExecutionException e) {
			System.out.println("Result 6 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 6 exception: " + e.getMessage());
		}

		try {
			System.out.println(result7.get());
		} catch (ExecutionException e) {
			System.out.println("Result 7 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 7 exception: " + e.getMessage());
		}

		try {
			System.out.println(result8.get());
		} catch (ExecutionException e) {
			System.out.println("Result 8 exception: "
					+ e.getCause().getMessage());
		} catch (InterruptedException | NullPointerException e) {
			System.out.println("Result 8 exception: " + e.getMessage());
		}

		// 关闭线程池
		pool.shutdown();

		// end
		System.out.println("Program stops");
	}
}