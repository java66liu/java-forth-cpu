package me.liangdi.forth.test;

import me.liangdi.forth.OpCode;

/**
 *
 * @author liangdi
 */
public class OpCodeTest {

	public static void main(String[] args) {
		OpCode oc = new OpCode();
		oc.code = 0xF1;
		oc.value = 0x10;
		
		System.out.println("oc = " + oc);
		int ret = mergeOps(oc);
		System.out.println("ret = " + Integer.toBinaryString(ret));
		
		OpCode oc2 = separateOps(ret);
		System.out.println("oc2 = " + oc2);
		
		System.out.println(Integer.toBinaryString(~((byte)(1<<6))));
	}

	private static int mergeOps(OpCode code) {
		int ret = 0x00;
		ret = code.code << 8 & 0xFF00 | code.value;
		return ret;
	}

	private static OpCode separateOps(int code) {
		OpCode opCode = new OpCode();
		opCode.value =(short)(code & 0x00FF);
		opCode.code = (short)(code >>> 8);
		return opCode;
	}
}
