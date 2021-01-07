package Implementation;
public class Instruction {
	public	String type; //ADD, SUB, MUL, DIV, LD, SD

	public String dest, src1, src2;
	public int issueCycle,executionCycle,finishCycle,finishExecCycle;	//beginning of issue , execution and finishing or writing result cycle
	public String reservationTag;
	public Instruction(String type, String dest, String src1, String src2) {
		this.type = type;
		this.dest = dest;
		this.src1 = src1;
		this.src2 = src2;
	}

	@Override
	public String toString() {
		return "Instruction:" 
				+ "\t" + type 
				+ "\t" + dest 
				+ "\t" + src1 
				+ "\t" + src2 
				+ "\t" + reservationTag 
				+ "\t" + issueCycle 
				+ "\t" + executionCycle 
				+ "\t" + finishCycle 
				+ "\n";
	}
}