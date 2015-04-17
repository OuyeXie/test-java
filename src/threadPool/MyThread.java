package threadPool;

public class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " starts。。。");
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " stops。。。");
	}
}
