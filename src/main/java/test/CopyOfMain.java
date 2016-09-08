package test;

import java.util.Scanner;

public class CopyOfMain {
	private static int a = 0;
	private static int b = 0;
	private static int m = 1;

	private static int calculate(int x) {
		return (a * x + b) % m;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		a = sc.nextInt();
		b = sc.nextInt();
		int x = sc.nextInt();
		int k = sc.nextInt();
		m = sc.nextInt();

		for (int i = 1; i < k + 5; i++) {
			if (i >= k) {
				System.out.println(x);
			}
			x = calculate(x);
		}
	}
}