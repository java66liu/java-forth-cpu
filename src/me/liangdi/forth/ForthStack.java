package me.liangdi.forth;

import java.util.Stack;

/**
 *
 * @author liangdi
 */
public class ForthStack {
	public Stack<Integer> stack = new Stack<>();
	public int size = 16;

	public ForthStack() {
	}

	
	public ForthStack(int size) {
		this.size = size;
	}
	
}
