package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Copy_2_of_Main {

	private double Calculate() {
		return 0.0d;
	}

	public static void main(String[] args) {

		// input
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));

		// read
		String input = "";
		try {
			input = stdin.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// ouput
		System.out.println(input);
	}

}
