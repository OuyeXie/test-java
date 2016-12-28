package test;

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
                    synchronized (CoModificationTest.iterator) {
                        int i = iterator.next();
//                        iterator.remove();
                        System.out.println(count + " : " + this.getName() + " : " + i);
                        count++;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(this.getName() + " interrupted!!!");
                        throw e;
                    }
                }
                System.out.println(this.getName() + " finished!!!");
            } catch (Exception ee) {
                System.out.println(this.getName() + " error!!!");
                ee.printStackTrace();
            }
        }
    }

    static class ResetThread extends Thread {
        public ResetThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                while (count < 1000) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(this.getName() + " interrupted!!!");
                        throw e;
                    }
//                    iterator = list.iterator();
                    iterator = null;
                    System.out.println(this.getName() + " invoked!!!");
                }
                System.out.println(this.getName() + " finished!!!");
            } catch (Exception ee) {
                System.out.println(this.getName() + " error!!!");
                ee.printStackTrace();
            }
        }
    }

    static class RemoveThread extends Thread {
        public RemoveThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(this.getName() + " interrupted!!!");
                    throw e;
                }
                list = null;
                System.out.println(this.getName() + " invoked!!!");
                System.out.println(this.getName() + " finished!!!");
            } catch (Exception ee) {
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
            ;

            ExecutorService service = Executors.newCachedThreadPool();

            for (int i = 0; i < 2; i++) {
                Thread thread = new MyThread("MyThread-Name-" + i);
                service.submit(thread);
            }

            Thread resetThread = new ResetThread("ResetThread-Name-0");
            service.submit(resetThread);

//            Thread removeThread = new RemoveThread("RemoveThread-Name-0");
//            service.submit(removeThread);

            service.shutdown();
            service.awaitTermination(100 * 1000, TimeUnit.MILLISECONDS);

            System.out.println("final count : " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
