package test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ouyexie on 27/12/2016.
 */
public class CoModificationTest {

    private static List<Integer> list;
    private static Iterator<Integer> iterator;
    private static int count = 0;

    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                while (iterator.hasNext()) {
                    int i = iterator.next();
//                        iterator.remove();
                    synchronized (CoModificationTest.class) {
                        System.out.println(count + " : " + this.getName() + " : " + i);
                        count++;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(this.getName() + " interrupted!!!");
                    }
                }
                System.out.println(this.getName() + " finished!!!");
            } catch (ConcurrentModificationException ee) {
                System.out.println(this.getName() + " error!!!");
                ee.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        try {
            list = new LinkedList<>();
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
            iterator = list.iterator();

            Thread thread0 = new MyThread("Thread-Name-0");
            Thread thread1 = new MyThread("Thread-Name-1");

            ExecutorService service = Executors.newCachedThreadPool();
            service.submit(thread0);
            service.submit(thread1);
            service.shutdown();
            service.awaitTermination(100 * 1000, TimeUnit.MILLISECONDS);

            System.out.println("final count : " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
