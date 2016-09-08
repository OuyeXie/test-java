package test;

public class Test {
	public static void main(String[] args) {
		int a = 100;
		int b = 200;
		int c = 301;
		float d = (float) (a - (b + c) / 2);
		float e = (float) a - ((float) (b + c) / 2.0f);
		System.out.println(String.format("d: %f, e: %f", d, e));

		System.out.println(String.format("combine result: %f",
				combine(0.9f, combine(0.6f, combine(0.51f, 0.49f)))));

		System.out.println(String.format("combine result: %f",
				combine(0.78542f, 0.6127f)));
	}

	private static float combine(float prob1, float prob2) {
		return (prob1 * prob2)
				/ ((prob1 * prob2) + ((1.0f - prob1) * (1.0f - prob2)));
	}
}
