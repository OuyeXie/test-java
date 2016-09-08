package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Copy_5_of_Main {

	private static List<Entry<String, Integer>> sortKeywordMap(
			Map<String, Integer> keywordMap) {
		List<Entry<String, Integer>> arrayList = new ArrayList<Entry<String, Integer>>(
				keywordMap.entrySet());
		Collections.sort(arrayList, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> e1,
					Entry<String, Integer> e2) {
				if (e2.getValue() == e1.getValue()) {
					return (e1.getKey()).compareTo(e2.getKey());
				}
				return (e2.getValue()).compareTo(e1.getValue());
			}
		});
		return arrayList;
	}

	private static List<Entry<String, Integer>> calculate(String input) {
		Map<String, Integer> map = new HashMap<>();
		for (int index = 0; index < input.length(); index++) {
			String currString = input.substring(index, index + 1);
			;
			if (map.containsKey(currString)) {
				int currValue = map.get(currString);
				currValue++;
				map.put(currString, currValue);
			} else {
				map.put(currString, 1);
			}
		}

		List<Entry<String, Integer>> list = sortKeywordMap(map);

		return list;
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

		List<Entry<String, Integer>> list = calculate(input);

		// ouput
		for (Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey());
		}
	}

}
