package com.pickupppp.task4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Map<Integer, Vertex> map = new HashMap<>();
		for (int i = 0; i < 7; i++) {
			map.put(i, new Vertexm(""));
		}
		Graphm graph = new Graphm(map);
		graph.setEdge(0, 1, 1);

		graph.setEdge(1, 0, 1);
		graph.setEdge(1, 2, 1);
		graph.setEdge(1, 4, 1);

		graph.setEdge(2, 1, 1);
		graph.setEdge(2, 3, 1);

		graph.setEdge(3, 2, 1);
		graph.setEdge(3, 4, 1);

		graph.setEdge(4, 1, 1);
		graph.setEdge(4, 3, 1);
		graph.setEdge(4, 5, 1);

		graph.setEdge(5, 4, 1);

		List<Integer> list = new ArrayList<>();
		List<List<Integer>> set = new ArrayList<>();
		graph.findAll(0, 5, list, set);
		System.out.println(list);
		System.out.println(set);
	}
}
