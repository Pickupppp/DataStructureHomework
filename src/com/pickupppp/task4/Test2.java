package com.pickupppp.task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {
	public static void main(String[] args) {
		File file = new File("src//com//pickupppp//task4//words5.txt");
		BufferedReader br = null;
		Map<Integer, Vertex> map = new HashMap<>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String word = "";
			int index = 0;
			while ((word = br.readLine()) != null) {
				map.put(index, new Vertexm(word));
				index++;
			}
			Graphm graph = new Graphm(map);
			for (int i = 0; i < graph.n(); i++) {
				for (int j = 0; j < graph.n(); j++) {
					if (isRight(map.get(i).value(), map.get(j).value())) {
						graph.setEdge(i, j, 1);
					}
				}
			}
//			int v1 = -1;
//			int v2 = -1;
//			List<Integer> list = new ArrayList<>();
//			do {
//				v1 = (int) (Math.random() * map.size());
//				v2 = (int) (Math.random() * map.size());
//			} while (!graph.isLinked(v1, v2));
//			graph.isLinked(v1, v2, list);
//			System.out.println(list);
//			System.out.println(map.get(v1).value() + "--->" + map.get(v2).value());
//			List<List<Integer>> res = new ArrayList<>();
//			graph.findAll(v1, v2, list, res);
//			System.out.println(res);
//			for(int temp:list) {
//				System.out.print(map.get(temp).value()+"-->");
//			}
			System.out.println(graph.isLinked("brunt", "chard"));

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
