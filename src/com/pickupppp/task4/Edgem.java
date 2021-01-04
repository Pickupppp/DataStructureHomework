package com.pickupppp.task4;

public class Edgem implements Edge {

	private int vert1, vert2;

	public Edgem(int vert1, int vert2) {
		this.vert1 = vert1;
		this.vert2 = vert2;
	}

	@Override
	public int v1() {
		return vert1;
	}

	@Override
	public int v2() {
		return vert2;
	}

}
