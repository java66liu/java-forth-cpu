package me.liangdi.forth;

/**
 *
 * @author liangdi
 */
public class Register {

	public static final int REG_VALUE_1 = 0x01;
	public static final int REG_VALUE_0 = 0x00;
	public int bit = 16;
	public int value = 0x00;

	/**
	 * 设置位 值
	 *
	 * @param bit
	 * @param nValue
	 */
	public void setBit(int bit, int nValue) {
		if(bit < 0) {
			return;
		}
		if (nValue == Register.REG_VALUE_1) {
			value =(1 << (bit-1) | value);

		} else if (nValue == Register.REG_VALUE_0) {
			value = ((~(1 << (bit-1)) )& value &(0xFFFFFFFF>>>16));
			 
		}
	}
	public void setValue(int value){
		this.value = value;
	}
	/**
	 * 重置
	 */
	public void reset() {
		value = 0x00;
	}
}
