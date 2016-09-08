package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Copy_4_of_Main {

	static String firstNonRepeatingCharacter(String input) {
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < input.length(); i++) {
			String currString = input.substring(i, i + 1);
			if (map.containsKey(currString)) {
				int value = map.get(currString);
				map.put(currString, ++value);
			} else {
				map.put(currString, 1);
			}
		}

		String result = null;
		for (int i = 0; i < input.length(); i++) {
			String currString = input.substring(i, i + 1);
			Integer value = map.get(currString);
			if (value != null && value == 1) {
				result = currString;
				break;
			}
		}
		return result;
	}

	static String characterReverse(String input) {

		String duplicate = new String(input);
		Map<Integer, String> map = new HashMap<>();
		Map<Integer, List<String>> map2 = new HashMap<>();

		for (int i = 0; i < input.length(); i++) {
			String currString = input.substring(i, i + 1);
			if (currString.equals("t") || currString.equals("h")) {
				map.put(i, currString);
			}
		}

		for (int i = 0; i < input.length(); i++) {
			List<String> list = new LinkedList<>();
			int start = 0;
			if (map.containsKey(i) && map.containsKey(i + 1)) {
				start = i;
				list.add(map.get(i));
			} else if (!list.isEmpty()) {
				Collections.sort(list);
				String sub = "";
				for (String currString : list) {
					sub += currString;
				}
				duplicate = duplicate.substring(0, start) + sub
						+ duplicate.substring(start + list.size());
			}
		}
		return duplicate;
	}

	public static void main(String[] args) {

		// input
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			String string = sc.next();
		}

//		// ouput
//		for (Entry<String, Integer> entry : list) {
//			System.out.println(entry.getKey());
//		}
	}

}
