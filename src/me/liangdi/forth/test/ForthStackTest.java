package me.liangdi.forth.test;

import me.liangdi.forth.ForthStack;

/**
 *
 * @author liangdi
 */
public class ForthStackTest {
	public static void main(String[] args) {
		ForthStack fs = new ForthStack();
		fs.push(1);
		fs.push(2);
		System.out.println("fs = " + fs.size());
		System.out.println("fs = " + fs.pop());
		System.out.println("fs = " + fs.pop());
	}
}
