package me.liangdi.forth;

/**
 *
 * @author liangdi
 */
public class OpCode {
	/**
	 * 点操作
	 */
	public static final int DOT = 0x0001;
	public static final String DOT_STR = ".";
	/*
	 * 加号
	 */
	public static final int PLUS = 0x0002;
	public static final String PLUS_STR = "+";
	/*
	 * 减号
	 */
	public static final int MINUS = 0x0003;
	public static final String MINUS_STR = "-";
	/**
	 * 乘号
	 */
	public static final int MUL = 0x0005;
	public static final String MUL_STR = "*";
	/**
	 * 除号
	 */
	public static final int DIV = 0x0006;
	public static final String DIV_STR = "/";
	
	/**
	 * 常数
	 */
	public static final int CONST = 0x1001;
	/**
	 * 打印 PS
	 */
	public static final int SHOW = 0x1002;
	public static final String SHOW_STR = ".s";
	
	/**
	 * 复制栈顶元素
	 */
	public static final int DUP = 0x2001;
	public static final String DUP_STR = "dup";
	/**
	 * 交换堆栈上的两个元素
	 */
	public static final int SWAP = 0x2002;
	public static final String SWAP_STR = "swap";
	/**
	 * 移去栈顶元素
	 */
	public static final int DROP = 0x2003;
	public static final String DROP_STR = "drop";
	/**
	 * 复制次栈顶元素
	 */
	public static final int OVER = 0x2004;
	public static final String OVER_STR = "over";
	/**
	 * 复制栈顶元素到次栈顶元素之下，这个操作等效于 SWAP OVER
	 */
	public static final int TUCK = 0x2005;
	public static final String TUCK_STR = "tuck";
	/**
	 * ROT ( n1 n2 n3 -- n2 n3 n1 )旋转堆栈上的三个元素，原来的第三个元素变成了第一个元素
	 */
	public static final int ROT = 0x2006;
	public static final String ROT_STR = "rot";
	/**
	 * -ROT ( n1 n2 n3 -- n3 n1 n2 )反向旋转堆栈顶部的三个元素，栈顶元素被旋转到了第二位置
	 */
	public static final int _ROT = 0x2007;
	public static final String _ROT_STR = "-rot";
	/**
	 * 从堆栈上移去第二个元素，这个操作等效于 SWAP DROP ，如 6 2 NIP . 将打印 2
	 */
	public static final int NIP = 0x2008;
	public static final String NIP_STR = "nip";
	/**
	 * 复制栈顶两个元素，如 2 4 2 DUP .S 将打印 2 4 2 4
	 */
	public static final int _2DUP = 0x2009;
	public static final String _2DUP_STR = "2dup";
	/**
	 * 把栈顶的两个元素与第三个和第四个元素交换，如 2 4 6 8 2SWAP .S 将打印 6 8 2 4
	 */
	public static final int _2SWAP  = 0x200A;
	public static final String _2SWAP_STR = "2swap";
	/**
	 * 从堆栈上移去栈顶两个元素
	 */
	public static final int _2DROP  = 0x200B;
	public static final String _2DROP_STR = "2drop";
	/**
	 * 从栈顶计算 n1 位置（不包含 n1 ），把这个位置的值复制到栈顶，栈顶与 n1 位置对应的是 0.
	0 PICK 等效于 DUP
	1 PICK 等效于 OVER
	2 4 6 8 2 PICK .S 将打印 2 4 6 8 4
	 */
	public static final int PICK  = 0x200C;
	public static final String PICK_STR = "pick";
	/**
	 * 旋转位置 n ( 不包含 n) 到栈顶， n 必须大于 0.
		1 ROLL 等效于 SWAP
		2 ROLL 等效于 ROT
	 */
	public static final int ROLL  = 0x200D;
	public static final String ROLL_STR = "roll";
	
	public static final int INVALID_OP = 0xF001;
	public static final String INVALID_OP_STR = "Invalid Ops";
	
	public int code;
	public int value;
	public String str = "";

	@Override
	public String toString() {
		return "OpCode{" + "str=" + str + ",code=" + code + ", value=" + value + "}\n" +
			 "B:"+  String.format("%8s", Integer.toBinaryString(code)).replace(' ', '0')  + String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0')+ "\n" + 
			 "H:0x" +  String.format("%2s", Integer.toHexString(code)).replace(' ', '0')   + String.format("%2s", Integer.toHexString(value)).replace(' ', '0') ;
	}
	
	
}
