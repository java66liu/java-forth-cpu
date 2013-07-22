package me.liangdi.forth.test;

import me.liangdi.forth.OpCode;

/**
 *
 * @author liangdi
 */
public class OpCodeTest {

	public static void main(String[] args) {
		OpCode oc = new OpCode();
		oc.code = 4097;
		oc.value = 110;
		
		System.out.println("oc = " + oc);
		int ret = mergeOps(oc);
		System.out.println("ret = " + Integer.toBinaryString(ret));
		
		OpCode oc2 = separateOps(ret);
		System.out.println("oc2 = " + oc2);
		
		System.out.println(Integer.toBinaryString(~(1<<6) & 0xFFFFFFFF >>> 24));
		System.out.println(Integer.toBinaryString(0xFF));
	}

	private static int mergeOps(OpCode code) {
		int ret = 0x00;
		ret = code.code << 16 & 0xFFFF0000 | code.value;
		return ret;
	}

	private static OpCode separateOps(int code) {
		OpCode opCode = new OpCode();
		opCode.value =code & 0x0000FFFF;
		opCode.code =(code >>> 16);
		return opCode;
	}
}
