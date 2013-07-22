package me.liangdi.forth.test;

import java.util.List;
import me.liangdi.forth.OpCode;
import me.liangdi.forth.VM;

/**
 *
 * @author liangdi
 */
public class CompilerTest {
	public static void main(String[] args) {
		String code = "1 .s a 2 3111 .s . . . .";
		//code += "\n DUP .s a"; 
		me.liangdi.forth.Compiler compiler = new me.liangdi.forth.Compiler();
		List<OpCode> ops = compiler.compiler(code);
		//System.out.println("ops = " + ops.size());
		//compiler.dumpOps();
		//System.out.println("");
		VM vm = new VM(64, 8, 8);
		vm.loadOps(ops);
		vm.run();
		
	}
}
