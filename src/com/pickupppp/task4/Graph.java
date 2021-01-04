package com.pickupppp.task4;

import java.util.List;

public interface Graph {
	public int n();

	public int e();

	public Edge first(int v);

	public Edge next(Edge w);

	public boolean isEdge(Edge w);

	public boolean isEdge(int v1, int v2);

	public int v1(Edge w);

	public int v2(Edge w);

	public void setEdge(int i, int j, int weight);

	public void setEdge(Edge w, int weight);

	public void delEdge(Edge w);

	public void delEdge(int i, int j);

	public int weight(int i, int j);

	public int weight(Edge w);

	public void setMark(int v, int val);

	public int getMark(int v);

	public Vertex vertVal(int v);

}
