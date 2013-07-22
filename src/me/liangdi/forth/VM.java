package me.liangdi.forth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private ForthStack dataStack = new ForthStack();
	/**
	 * 返回堆栈
	 */
	private ForthStack returnStack = new ForthStack();
	/**
	 * 内存区域
	 */
	private List<Integer> mem = new ArrayList<>();
	/**
	 * 程序指针寄存器
	 */
	private Register regIP = new Register();
	/**
	 * 数据堆栈指针寄存器
	 */
	private Register regPSP = new Register();
	/**
	 * 返回堆栈寄存器
	 */
	private Register regRSP = new Register();
	/**
	 * 数据堆栈顶端寄存器
	 */
	private Register regTOS = new Register();
	/**
	 * 用户寄存器
	 */
	private Register regUP = new Register();
	/**
	 * 辅助工作寄存器
	 */
	private Register regW = new Register();
	public Map<Integer, Register> regMaps = new HashMap<>();
	public static final int REG_IP = 0;
	public static final int REG_PSP = 1;
	public static final int REG_RSP = 2;
	public static final int REG_TOS = 3;
	public static final int REG_UP = 4;
	public static final int REG_W = 5;
	
	
	public static final int STATUS_STOP = 0;
	public static final int STATUS_RUN = 1;
	public static final int STATUS_PAUSE = 2;
	private int status = STATUS_STOP;

	public VM() {
		init();
	}

	public VM(int memSize, int dsSize, int rsSize) {
		this.memSize = memSize;
		this.dsSize = dsSize;
		this.rsSize = rsSize;
		init();
	}

	private void init() {
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
		ret = code.code << 16 & 0xFFFF0000 | code.value;
		return ret;
	}

	/**
	 *
	 * @param code
	 * @return
	 */
	private OpCode separateOps(int code) {
		OpCode opCode = new OpCode();
		opCode.value =  code & 0x0000FFFF;
		opCode.code = code >>> 16;
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

	public void setRegBit(int reg, int bit, int value) {
		regMaps.get(reg).setBit(bit, value);
	}

	/**
	 * 设置内存数据
	 *
	 * @param index
	 * @param value
	 */
	public void setMem(int index, int value) {
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
		this.status = STATUS_RUN;
		while(this.status == STATUS_RUN){
			processNextOpCode();
		}
		String finish = "VM Execute Finished!";
		showMsg(finish);
	}
	
	private void processNextOpCode(){
		int ipAddr = this.regIP.value;
		/**
		 * 超出代码内存
		 */
		if(ipAddr >= this.mem.size()){
			this.status = STATUS_STOP;
			return;
		}
		/**
		 * 执行代码
		 */
		int memValue = this.mem.get(ipAddr);
		executeOpCode(memValue);
		ipAddr++;
		this.regIP.setValue(ipAddr);
		
	}
	private void executeOpCode(int memValue){
		OpCode opCode = separateOps(memValue);
		switch(opCode.code){
			case OpCode.SHOW:	//Process     .s
				int showSize = 5;
				int dSize = dataStack.size();
				String output = "Stack Size:" + dSize + "  ";
				for (int i = dSize - Math.min(dSize, showSize); i <dSize; i++) {
					output += dataStack.get(i) +" ";
				}
				output+="ok";
				showMsg(output);
				break;
			case OpCode.CONST:	
				int value = opCode.value;
				 dSize = dataStack.size();
				if(dSize >= this.dsSize){
					showError(VMError.ERROR_DATA_STACK_OVERFLOW);
					this.status = STATUS_STOP;
					break;
				}
				this.dataStack.push(value);
				break;
			case OpCode.DOT:		//Processs    .
				dSize = dataStack.size();
				if(dSize<=0){
					showError(VMError.ERROR_DATA_STACK_UNDERFLOW);
					this.status = STATUS_STOP;
					break;
				}
				int item = dataStack.pop();
				output = "DataStack pop item:" + item + "\tok";
				showMsg(output);
				break;
			default:
				showError(VMError.ERROR_UNKNOW_OPCODE);
				this.status = STATUS_STOP;
		}
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
		String out = String.format("%16s", Integer.toBinaryString(reg)).replace(' ', '0');
		showMsg(out);
	}

	/**
	 * 输出寄存器信息
	 */
	public void dumpRegs() {
		showMsg("DUMP OF REG IP:");
		dumpReg(regIP.value);
		showMsg("DUMP OF REG PSP:");
		dumpReg(regPSP.value);
		showMsg("DUMP OF REG RSP:");
		dumpReg(regRSP.value);
		showMsg("DUMP OF REG TOS:");
		dumpReg(regTOS.value);
		showMsg("DUMP OF REG UP:");
		dumpReg(regUP.value);
		showMsg("DUMP OF REG W:");
		dumpReg(regW.value);
	}

	/**
	 * 输出内存信息
	 */
	public void dumpMem() {
		for (int i = 0; i < mem.size(); i++) {
			String out = String.format("%8s", Integer.toBinaryString(mem.get(i))).replace(' ', '0');
			showMsg(out);

		}
	}

	private void showError(String msg) {
		System.out.println("Error: = " + msg);
	}
	private void showMsg(String msg){
		System.out.println(msg );
	}
	
	static class VMError {
		public static final String ERROR_UNKNOW_OPCODE = "Unknow Opcode.";
		public static final String ERROR_DATA_STACK_OVERFLOW = "Data Stack Overflow.";
		public static final String ERROR_DATA_STACK_UNDERFLOW = "Data Stack Underflow.";
		public static final String ERROR_RETURN_STACK_OVERFLOW = "Return Stack Overflow.";
	}
}
