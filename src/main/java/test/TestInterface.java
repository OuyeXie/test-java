package test;

interface Interface1 {
	public static final int a = 1;
	public static final int b = 11;

	public void print ();
}

interface Interface2 {
	public static final int a = 2;
	public static final int c = 22;

	public void print ();
}

public class TestInterface implements Interface1, Interface2{

	public void print () {
//		System.out.println(this.a);
		System.out.println(this.b);
		System.out.println(this.c);
	}
	public static void main(String[] args) {
		TestInterface testInterface = new TestInterface();
		testInterface.print();
	}
}
