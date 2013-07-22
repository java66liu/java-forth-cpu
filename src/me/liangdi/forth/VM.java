package me.liangdi.forth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author liangdi
 */
public class VM {

	/**
	 * 内存大小
	 */
	private int memSize = 16;
	/**
	 * 数据堆栈大小
	 */
	private int dsSize = 16;
	/**
	 * 返回堆栈大小
	 */
	private int rsSize = 16;
	/**
	 * 数据堆栈
	 */
	private Stack<Integer> dataStack = new Stack<>();
	/**
	 * 返回堆栈
	 */
	private Stack<Integer> returnStack = new Stack<>();
	/**
	 * 内存区域
	 */
	private List<Integer> mem = new ArrayList<>();
	/**
	 * 程序指针寄存器
	 */
	private Byte regIP = 0;
	/**
	 * 数据堆栈指针寄存器
	 */
	private Byte regPSP = 0;
	/**
	 * 返回堆栈寄存器
	 */
	private Byte regRSP = 0;
	/**
	 * 数据堆栈顶端寄存器
	 */
	private Byte regTOS = 0;
	/**
	 * 用户寄存器
	 */
	private Byte regUP = 0;
	/**
	 * 辅助工作寄存器
	 */
	private Byte regW = 0;
	
	Map<Integer,Byte> regMaps = new HashMap<>();
	public static final int REG_IP = 0;
	public static final int REG_PSP = 1;
	public static final int REG_RSP = 2;
	public static final int REG_TOS = 3;
	public static final int REG_UP = 4;
	public static final int REG_W = 5;
	public static final int REG_VALUE_1 = 0x01;
	public static final int REG_VALUE_0 = 0x00;
	
	public VM() {
		init();
	}
	
	public VM(int memSize, int dsSize, int rsSize) {
		this.memSize = memSize;
		this.dsSize = dsSize;
		this.rsSize = rsSize;
		init();
	}
	
	private void init(){
		regMaps.put(REG_IP, regIP);
		regMaps.put(REG_PSP, regPSP);
		regMaps.put(REG_RSP, regRSP);
		regMaps.put(REG_TOS, regTOS);
		regMaps.put(REG_UP, regUP);
		regMaps.put(REG_W, regW);
	}

	/**
	 * 将 OpCode 的 code 和 value
	 *
	 * @param code
	 * @return
	 */
	private int mergeOps(OpCode code) {
		int ret = 0x00;
		ret = code.code << 8 & 0xFF00 | code.value;
		return ret;
	}

	/**
	 *
	 * @param code
	 * @return
	 */
	private OpCode separateOps(int code) {
		OpCode opCode = new OpCode();
		opCode.value = (short) (code & 0x00FF);
		opCode.code = (short) (code >>> 8);
		return opCode;
	}

	/**
	 *
	 * @param ops
	 */
	public void loadOps(List<OpCode> ops) {
		int minSize = Math.min(memSize, ops.size());
		/**
		 * 清除并加载OpCode
		 */
		mem.clear();
		for (int i = 0; i < minSize; i++) {
			mem.add(mergeOps(ops.get(i)));
		}
	}
	public void setRegBit(int reg,int bit,int value){
		Byte regPoint = regMaps.get(reg);
		if(value == REG_VALUE_1){
			
			regPoint = (byte) (1<< bit | regPoint);
			
		} else if(value == REG_VALUE_0) {
			
		}
	}
	/**
	 * 设置内存数据
	 * @param index
	 * @param value 
	 */
	public void setMem(int index,int value){
		mem.set(index, value);
	}
	/**
	 * 以解析器的方式运行虚拟机
	 */
	public void runAsInterpreter() {
		
	}

	/**
	 * 正常运行虚拟机
	 */
	public void run() {
		
	}

	/**
	 * 以命令执行方式运行虚拟机,如 单步执行等
	 */
	public void runByCommand() {
		
	}

	/**
	 * 输出寄存器信息
	 *
	 * @param reg
	 */
	public void dumpReg(int reg) {
		String out = String.format("%8s", Integer.toBinaryString(reg)).replace(' ', '0');
		System.out.println(out);
	}

	/**
	 * 输出寄存器信息
	 */
	public void dumpRegs() {
		System.out.println("DUMP OF REG IP:");
		dumpReg(regIP);
		System.out.println("DUMP OF REG PSP:");
		dumpReg(regPSP);
		System.out.println("DUMP OF REG RSP:");
		dumpReg(regRSP);
		System.out.println("DUMP OF REG TOS:");
		dumpReg(regTOS);
		System.out.println("DUMP OF REG UP:");
		dumpReg(regUP);
		System.out.println("DUMP OF REG W:");
		dumpReg(regW);
	}
	/**
	 * 输出内存信息
	 */
	public void dumpMem() {
		for (int i = 0; i < mem.size(); i++) {
			String out = String.format("%8s", Integer.toBinaryString(mem.get(i))).replace(' ', '0');
			System.out.println(out);
			
		}
	}
}
