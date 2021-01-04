package com.pickupppp.task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TestMyBST {

	public static void operate(String sentence, MyBST<String, String> tree) {
		String[] components = sentence.split(" ");
		char operation = sentence.charAt(0);
		switch (operation) {
		case '+': {
			tree.insert(components[1], components[3].substring(1, components[3].length() - 1));
			break;
		}
		case '-': {
			String value = tree.remove(components[1]);
			if (null == value) {
				System.out.println("remove unsuccess ---" + components[1]);
			} else {
				System.out.println("remove success ---" + components[1] + " " + value);
			}
			break;
		}
		case '?': {
			String value = tree.search(components[1]);
			if (null == value) {
				System.out.println("search unsuccess ---" + components[1]);
			} else {
				System.out.println("search success ----" + components[1] + " " + value);
			}
			break;
		}
		case '=': {
			boolean flag = tree.update(components[1], components[3].substring(1, components[3].length() - 1));
			if (flag) {
				System.out.println("update success ---" + components[1] + " "
						+ components[3].substring(1, components[3].length() - 1));
			} else {
				System.out.println("update unsuccess ---" + components[1]);
			}
			break;
		}
		case '#': {
			try {
				tree.showStructure(new PrintWriter(System.out));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operation);
		}
	}

	public static void main(String[] args) {
		MyBST<String, String> tree = new MyBST<>();
		File file = new File("src//com//pickupppp//task3//homework2_testcases.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String sentence = "";
			while ((sentence = br.readLine()) != null) {
				operate(sentence, tree);
			}

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
