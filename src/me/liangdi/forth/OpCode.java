package me.liangdi.forth;

/**
 *
 * @author liangdi
 */
public class OpCode {
	public static final int DOT = 0X01;
	
	
	public short code;
	public short value;

	@Override
	public String toString() {
		return "OpCode{" + "code=" + code + ", value=" + value + "}\n" +
			 "B:"+  String.format("%8s", Integer.toBinaryString(code)).replace(' ', '0')  + String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0')+ "\n" + 
			 "H:0x" +  String.format("%2s", Integer.toHexString(code)).replace(' ', '0')   + String.format("%2s", Integer.toHexString(value)).replace(' ', '0') ;
	}
	
	
}
