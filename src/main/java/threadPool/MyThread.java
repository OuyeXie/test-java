package threadPool;

public class MyThread extends Thread {

	MyThread() {
		super();
	}

	MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println(getName() + " starts。。。");
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getName() + " stops。。。");
	}
}
