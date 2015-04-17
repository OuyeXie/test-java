package test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Copy_3_of_Main {

	private static int a[][];

	private static int n = 0;

	private static double calculate(int me, Set<Integer> known) {
		boolean done = true;
		for (int i = 1; i <= n; i++) {
			if (a[me][i] == 1) {
				if (!known.contains(i)) {
					done = false;
					break;
				}
			}
		}
		if (done) {
			return 0.0d;
		}

		double result = 1.0d;
		int numOfKnown = known.size();
		int numOfUnknown = n - numOfKnown;
		Set<Integer> knownNext = new HashSet<>(known);
		knownNext.add(me);
		for (int i = 1; i <= n; i++) {
			if (!knownNext.contains(i)) {
				result += (0.0 + (calculate(i, knownNext))
						/ (double) numOfUnknown);
			}
		}
		return result;
	}

	public static void main(String[] args) {

		// input
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		a = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			String string = sc.next();
			for (int j = 1; j <= n; j++) {
				String currString = string.substring(j - 1, j);
				if (currString.equalsIgnoreCase("Y")) {
					a[i][j] = 1;
				} else {
					a[i][j] = 0;
				}
			}
		}

		Set<Integer> known = new HashSet<>();
		known.add(1);
		double result = 1 + calculate(1, known);

		// ouput
		System.out.println(result);
	}

}
