package com.pickupppp.task4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class WordLadder {

	public static void main(String[] args) {
		File file = new File("src//com//pickupppp//task4//words5.txt");
		BufferedReader br = null;
		Map<Integer, Vertex> map = new HashMap<>();
		BufferedWriter bw = null;
		BufferedWriter bw2 = null;
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
			File file1 = new File("src//com//pickupppp//task4//noladder.txt");
			File file2 = new File("src//com//pickupppp//task4//others.txt");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1)));
			bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
			for (int i = 0; i < graph.n(); i++) {
				if (graph.isAlone(i)) {
					bw.write(map.get(i).value());
					bw.write("\n");
				} else {
					bw2.write(map.get(i).value());
					bw2.write("\n");
				}
			}
			bw.flush();
			bw2.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bw2) {
				try {
					bw2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
