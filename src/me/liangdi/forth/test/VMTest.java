package me.liangdi.forth.test;

import me.liangdi.forth.Register;
import me.liangdi.forth.VM;

/**
 *
 * @author liangdi
 */
public class VMTest {
	public static void main(String[] args) {
		VM vm = new VM();
		vm.setRegBit(VM.REG_IP, 1, Register.REG_VALUE_1);
		vm.setRegBit(VM.REG_IP, 12, Register.REG_VALUE_1);
		vm.setRegBit(VM.REG_IP, 11, Register.REG_VALUE_0);
		vm.dumpRegs();
		

	}
}
