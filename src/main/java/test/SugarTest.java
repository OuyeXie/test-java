package test;

public class SugarTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        /*
         * http://www.oschina.net/question/112684_76396
         * JVM会自动维护八种基本类型的常量池，int常量池中初始化-128~127的范围，所以当为Integer i=127时，在自动装箱过程中是取自常量池中的数值，而当Integer i=128时，128不在常量池范围内，所以在自动装箱过程中需new 128，所以地址不一样。
         */
        final Integer h = 127;
        final Integer i = 127;

        System.out.println(c == d);
        System.out.println(c.equals(d));
        System.out.println(e == f);
        System.out.println(e.equals(f));
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        System.out.println(h == i);
        System.out.println(h.equals(i));

        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);

        if (true) {
            System.out.println("it is true");
        }

        boolean t = true;
        if (t) {
            System.out.println("it is true");
        }

        final boolean tt = true;
        if (tt) {
            System.out.println("it is true");
        }

        final boolean ttt = true;
        while (ttt) {
            System.out.println("it is true");
            break;
        }
    }

    /*
    public static void main(String[] args) {
        Integer a = Integer.valueOf(1);
        Integer b = Integer.valueOf(2);
        Integer c = Integer.valueOf(3);
        Integer d = Integer.valueOf(3);
        Integer e = Integer.valueOf(321);
        Integer f = Integer.valueOf(321);
        Long g = Long.valueOf(3L);
        Integer h = Integer.valueOf(127);
        Integer i = Integer.valueOf(127);
        System.out.println(c == d);
        System.out.println(c.equals(d));
        System.out.println(e == f);
        System.out.println(e.equals(f));
        System.out.println(c.intValue() == a.intValue() + b.intValue());
        System.out.println(c.equals(Integer.valueOf(a.intValue() + b.intValue())));
        System.out.println(g.longValue() == (long)(a.intValue() + b.intValue()));
        System.out.println(g.equals(Integer.valueOf(a.intValue() + b.intValue())));
        System.out.println(h == i);
        System.out.println(h.equals(i));
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println("it is true");
        boolean t = true;
        if(t) {
            System.out.println("it is true");
        }

        boolean tt = true;
        System.out.println("it is true");
        boolean ttt = true;
        System.out.println("it is true");
    }
     */
}
