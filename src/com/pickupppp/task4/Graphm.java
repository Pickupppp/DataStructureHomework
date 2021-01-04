package com.pickupppp.task4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graphm implements Graph {

	private int[][] matrix;
	private int numEdge;
	private Map<Integer, Vertex> map; // 序号与结点的映射
	public int[] Mark;

	public Graphm(int n) {
		Mark = new int[n];
		matrix = new int[n][n];
		numEdge = 0;
		map = new HashMap<Integer, Vertex>();
	}

	public Graphm(Map<Integer, Vertex> map) {
		Mark = new int[map.size()];
		matrix = new int[map.size()][map.size()];
		numEdge = 0;
		this.map = map;
	}

	@Override
	public int n() {
		return matrix.length;
	}

	@Override
	public int e() {
		return numEdge;
	}

	@Override
	public Edge first(int v) {
		for (int i = 0; i < Mark.length; i++) {
			if (matrix[v][i] != 0) {
				return new Edgem(v, i);
			}
		}
		return null;
	}

	@Override
	public Edge next(Edge w) {
		if (null == w) {
			return null;
		}
		for (int i = w.v2() + 1; i < Mark.length; i++) {
			if (matrix[w.v1()][i] != 0) {
				return new Edgem(w.v1(), i);
			}
		}
		return null;
	}

	@Override
	public boolean isEdge(Edge w) {
		if (null == w) {
			return false;
		} else {
			return matrix[w.v1()][w.v2()] != 0;
		}
	}

	@Override
	public boolean isEdge(int v1, int v2) {
		return matrix[v1][v2] != 0;
	}

	public boolean isEdge(String word1, String word2) {
		if (null != word1 && null != word2) {
			int v1 = -1, v2 = -1;
			for (Entry<Integer, Vertex> entry : map.entrySet()) {
				if (entry.getValue().value().equals(word1)) {
					v1 = entry.getKey();
					break;
				}
			}
			if (v1 == -1) {
				return false;
			}
			for (Entry<Integer, Vertex> entry : map.entrySet()) {
				if (entry.getValue().value().equals(word2)) {
					v2 = entry.getKey();
					break;
				}
			}
			if (v2 == -1) {
				return false;
			}
			return isEdge(v1, v2);
		}
		return false;
	}

	@Override
	public int v1(Edge w) {
		return w.v1();
	}

	@Override
	public int v2(Edge w) {
		return w.v2();
	}

	@Override
	public void setEdge(int i, int j, int weight) {
		if (weight != 0) {
			matrix[i][j] = weight;
			numEdge++;
		}
	}

	@Override
	public void setEdge(Edge w, int weight) {
		if (w != null && weight != 0) {
			setEdge(w.v1(), w.v2(), weight);
		}
	}

	@Override
	public void delEdge(Edge w) {
		if (null != w) {
			if (matrix[w.v1()][w.v2()] != 0) {
				matrix[w.v1()][w.v2()] = 0;
				numEdge--;
			}
		}
	}

	@Override
	public void delEdge(int i, int j) {
		if (matrix[i][j] != 0) {
			matrix[i][j] = 0;
			numEdge--;
		}
	}

	@Override
	public int weight(int i, int j) {
		return matrix[i][j];
	}

	@Override
	public int weight(Edge w) {
		if (null == w) {
			return 0;
		} else {
			return matrix[w.v1()][w.v2()];
		}
	}

	@Override
	public void setMark(int v, int val) {
		Mark[v] = val;
	}

	@Override
	public int getMark(int v) {
		return Mark[v];
	}

	public Vertex vertVal(int v) {
		return map.get(v);
	}

	public boolean isAlone(int v) {
		if (v > -1 && v < n()) {
			for (int i = 0; i < n(); i++) {
				if (matrix[v][i] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isLinked(int v1, int v2, List<Integer> list) {
		for (int i = 0; i < Mark.length; i++) {
			setMark(i, 0);
		}
		return isLinkedHelp(v1, v2, list);
	}

	public boolean isLinked(String word1, String word2) {
		if (null != word1 && null != word2) {
			int v1 = -1, v2 = -1;
			for (Entry<Integer, Vertex> entry : map.entrySet()) {
				if (entry.getValue().value().equals(word1)) {
					v1 = entry.getKey();
					break;
				}
			}
			if (v1 == -1) {
				return false;
			}
			for (Entry<Integer, Vertex> entry : map.entrySet()) {
				if (entry.getValue().value().equals(word2)) {
					v2 = entry.getKey();
					break;
				}
			}
			if (v2 == -1) {
				return false;
			}
			for (int i = 0; i < Mark.length; i++) {
				setMark(i, 0);
			}
			return isLinkedHelp(v1, v2);
		}
		return false;
	}

	private boolean isLinkedHelp(int v1, int v2, List<Integer> list) {
		if (getMark(v1) != 0) {
			return false;
		}
		if (isAlone(v1) || isAlone(v2)) {
			return false;
		}

		if (matrix[v1][v2] != 0) {
			if (!list.contains(v1)) {
				list.add(v1);
			}
			if (!list.contains(v2)) {
				list.add(v2);
			}
			return true;
		}

		if (!list.contains(v1)) {
			list.add(v1);
		}

		for (int i = 0; i < n(); i++) {
			if (list.contains(i)) {
				continue;
			}

			if (getMark(i) != 0) {
				continue;
			}

			if (matrix[v1][i] != 0) {
				list.add(i);
				if (isLinkedHelp(i, v2, list)) {
					return true;
				}
				// list.remove(list.size()-1);
				int item = list.remove(list.size() - 1);
				setMark(item, 1);
			}
		}
		return false;
	}

	public boolean isLinked(int v1, int v2) {
		for (int i = 0; i < Mark.length; i++) {
			setMark(i, 0);
		}
		return isLinkedHelp(v1, v2);
	}

	private boolean isLinkedHelp(int v1, int v2) {
		if (getMark(v1) != 0) {
			return false;
		}
		if (isAlone(v1) || isAlone(v2)) {
			return false;
		}

		if (matrix[v1][v2] != 0) {
			setMark(v1, 1);
			setMark(v2, 1);
			return true;
		}

		setMark(v1, 1);

		for (int i = 0; i < n(); i++) {
			if (getMark(i) != 0) {
				continue;
			}

			if (matrix[v1][i] != 0) {
				if (isLinkedHelp(i, v2)) {
					return true;
				}
			}
		}
		return false;
	}

	public void findAll(int v1, int v2, List<Integer> list, List<List<Integer>> listSet) {
		for (int i = 0; i < Mark.length; i++) {
			setMark(i, 0);
		}
		findAllHelp(v1, v2, list, listSet);
	}

	private void findAllHelp(int v1, int v2, List<Integer> list, List<List<Integer>> listSet) {
		if (isAlone(v1)) {
			setMark(v1, 1);
			return;
		}
		if (isAlone(v2)) {
			setMark(v2, 1);
			return;
		}
		if (matrix[v1][v2] != 0) {
			if (!list.contains(v1)) {
				list.add(v1);
				setMark(v1, 1);
			}
			if (!list.contains(v2)) {
				list.add(v2);
				setMark(v2, 1);
			}
			listSet.add(new ArrayList<>(list));
			list.remove(list.size() - 1);
			setMark(v2, 0);
			return;
		}

		if (!list.contains(v1)) {
			list.add(v1);
			setMark(v1, 1);
		}

		for (int i = 0; i < n(); i++) {
			if (getMark(i) != 0) {
				continue;
			}
			if (matrix[v1][i] != 0) {
				int[] temp = new int[Mark.length];
				for (int m = 0; m < Mark.length; m++) {
					temp[m] = Mark[m];
				}
				if (isLinked(i, v2)) {
					for (int m = 0; m < Mark.length; m++) {
						Mark[m] = temp[m];
					}
					findAllHelp(i, v2, list, listSet);
					list.remove(list.size() - 1);
					setMark(i, 0);
				} else {
					setMark(i, 1);
				}
			}
		}
		return;
	}

}
