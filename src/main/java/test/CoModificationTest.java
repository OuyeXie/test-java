package test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ouyexie on 27/12/2016.
 */
public class CoModificationTest {
    static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();

        new Thread("Thread 0"){
            @Override
            public void run() {
                while (iterator.hasNext()){
                    int i = iterator.next();
                    System.out.println(this.getName() + " : " + i);
                }
                System.out.println(this.getName() + " finished!!!");
            }
        }.start();

        new Thread("Thread 1"){
            @Override
            public void run() {
                while (iterator.hasNext()){
                    int i = iterator.next();
                    System.out.println(this.getName() + " : " + i);
                }
                System.out.println(this.getName() + " finished!!!");
            }
        }.start();
    }
}
