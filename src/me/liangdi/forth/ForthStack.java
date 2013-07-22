package me.liangdi.forth;

import java.util.Stack;

/**
 *
 * @author liangdi
 */
public class ForthStack {
	public Stack<Integer> stack = new Stack<>();

	public ForthStack() {
		
	}
	public Integer pop(){
		return stack.pop();
	}
	
	public Integer push(Integer item){
		return stack.push(item);
	}
	
	public Integer size (){
		return stack.size();
	}
	
	public Integer get(int index){
		return stack.get(index);
	}
	
}
