public class ResEntry {
	//note: busy was deleted 3lshan f el reservationStation law null --> busy = false
	//vk should be the offset 
	String op;
	double vj, vk;
	String qj, qk;
	int A;
	String address;//rep of address as a string -> 32+R2
	//int cyclesLeft;	//Qi
	int initialExecutionCycle;

	public ResEntry(String op, double vj, double vk, String qj, String qk, int A) {
		this.op = op;
		this.vj = vj;
		this.vk = vk;
		this.qj = qj;
		this.qk = qk;
		this.A = A;

//		if(op.equals("LD") || op.equals("SD"))
//			cyclesLeft = 2;
//		else if(op.equals("ADD") || op.equals("SUB"))
//			cyclesLeft = 3;
//		else if(op.equals("MUL"))
//			cyclesLeft = 5;
//		else 
//			cyclesLeft = 7;		
	}	
	
	public String toString() {
		return "Res Entry:" 
				+ "\t" + op 
				+ "\t" + vj 
				+ "\t" + vk 
				+ "\t" + qj 
				+ "\t" + qk 
				+ "\t" + A + "\n";
	}
}
