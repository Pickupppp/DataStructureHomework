package com.pickupppp.task4;

public class Utils {
	public static boolean isRight(String word1, String word2) {
		if (word1.length() != word2.length()) {
			return false;
		}
		int count = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				count++;
			}
		}
		return count == 1;
	}
}
