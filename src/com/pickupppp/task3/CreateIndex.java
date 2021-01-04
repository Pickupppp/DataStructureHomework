package com.pickupppp.task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateIndex {

	public static void main(String[] args) {
		MyBST<String, List<Integer>> index = new MyBST<>();
		File file = new File("src//com//pickupppp//task3//article.txt");
		BufferedReader br = null;
		Map<String, List<Integer>> map = new HashMap<>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String sentence = "";
			int row = 0;
			while ((sentence = br.readLine()) != null) {
				row++;
				sentence = sentence.trim();
				String[] words = sentence.split(" ");
				if (!sentence.equals("")) {
					for (String word : words) {
						if (!(word.equals("Dr.") || word.equals("Mr.") || word.equals("D.M."))) {
							Pattern p = Pattern.compile("[a-zA-Z0-9]+'?[a-zA-Z0-9]+");
							Matcher matcher = p.matcher(word);
							if (matcher.find()) {
								word = matcher.group();
							} else {
								word = null;
							}
						}
						if (null != word) {
							if (!map.containsKey(word)) {
								ArrayList<Integer> list = new ArrayList<>();
								list.add(row);
								map.put(word, list);
							} else {
								if (!map.get(word).contains(row)) {
									map.get(word).add(row);
								}
							}
						}
					}
				}
			}
			for(String key:map.keySet()) {
				index.insert(key, map.get(key));
			}
			index.printInorder(new PrintWriter(new FileWriter("src//com//pickupppp//task3//index_result.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
