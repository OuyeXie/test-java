package threadPool;

import java.util.concurrent.Callable;

class MyCallable implements Callable<String> {
	private String name;

	public MyCallable(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String call() throws Exception {
		System.out.println(this.name + " starts。。。");

		// test exception
		if (this.name.equals("1")) {
			throw new BusinessException("4000", "Exception task 1");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(this.name + " stops。。。");
		return "result of TaskWithResult " + name;
	}
}