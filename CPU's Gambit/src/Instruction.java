public class Instruction {
	String type; //ADD, SUB, MUL, DIV, LD, SD

	String dest, src1, src2;

	public Instruction(String type, String dest, String src1, String src2) {
		this.type = type;
		this.dest = dest;
		this.src1 = src1;
		this.src2 = src2;
	}

	@Override
	public String toString() {
		return "Instruction:" 
				+ "\ntype= " + type 
				+ "\ndest= " + dest 
				+ "\nsrc1=" + src1 
				+ "\nsrc2=" + src2;
	}
}