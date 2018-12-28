package com.example.neo.helloworld;

import java.io.Serializable;

public class SingleProgress implements Serializable {
	private static final long serialVersionUID = 236464L;
	public String topic, content;
	public int capacity, achieved;
	public SingleProgress(String t, String c, int cap, int ach) {
		topic = t;
		content = c;
		capacity = cap;
		achieved = ach;
	}
	
	public SingleProgress() {
		capacity = 100;
		achieved = 0;
	}
	
	@Override
	public String toString() {
		String r = topic;
		return r;
	}
}
