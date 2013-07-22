package me.liangdi.forth;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author liangdi
 */
public class Compiler {
	public List<OpCode> codes = new ArrayList<>();

	public List<OpCode> compiler(String source) {
		
		String[] lines = source.split("\n");
		for (String line : lines) {
			parse(line, codes);
		}
		return codes;
	}
	
	private void parse(String line,List<OpCode> codes){
		String[] words = line.split(" ");
		for (String word : words) {
			if(word.isEmpty()){
				continue;
			}
			OpCode opCode = new OpCode();
			opCode.str = word;
			switch(word){
				case OpCode.DOT_STR: //点操作
					opCode.code = OpCode.DOT;
					break;
				case OpCode.PLUS_STR:
					opCode.code = OpCode.PLUS;
					break;
				case OpCode.MINUS_STR:
					opCode.code = OpCode.MINUS;
					break;
				case OpCode.MUL_STR:
					opCode.code = OpCode.MUL;
					break;
				case OpCode.DIV_STR:
					opCode.code = OpCode.DIV;
					break;
				case OpCode.SHOW_STR:
					opCode.code = OpCode.SHOW;
					break;
				case OpCode.DUP_STR:
					opCode.code = OpCode.DUP;
					break;
				case OpCode.SWAP_STR:
					opCode.code = OpCode.SWAP;
					break;
				case OpCode.DROP_STR:
					opCode.code = OpCode.DROP;
					break;
				case OpCode.OVER_STR:
					opCode.code = OpCode.OVER;
					break;
				case OpCode.TUCK_STR:
					opCode.code = OpCode.TUCK;
					break;
				case OpCode.ROT_STR:
					opCode.code = OpCode.ROT;
					break;
				case OpCode._ROT_STR:
					opCode.code = OpCode._ROT;
					break;
				case OpCode.NIP_STR:
					opCode.code = OpCode.NIP;
					break;
				case OpCode._2DUP_STR:
					opCode.code = OpCode._2DUP;
					break;
				case OpCode._2SWAP_STR:
					opCode.code = OpCode._2SWAP;
					break;
				case OpCode._2DROP_STR:
					opCode.code = OpCode._2DROP;
					break;
				case OpCode.PICK_STR:
					opCode.code = OpCode.PICK;
					break;
				case OpCode.ROLL_STR:
					opCode.code = OpCode.ROLL;
					break;
				default:
					/**
					 * 数字常量
					 */
					if(StringUtils.isNumeric(word)){
						opCode.code = OpCode.CONST;
						opCode.value = Integer.valueOf(word);
					} else {
						/**
						 * 非法输入
						 */
						opCode.code = OpCode.INVALID_OP;
						
					}
					
			}
			/**
			 * 加入OP代码列表
			 */
			codes.add(opCode);
			
		}
	}
	
	public void dumpOps(){
		int i = 0;
		for (OpCode opCode : codes) {
			System.out.println("COUNT:"+i);
			System.out.println("opCode = " + opCode);
			i++;
		}
	}
}
