public class ResEntry {
	//note: busy was deleted 3lshan f el reservationStation law null --> busy = false
	//vk should be the offset 
	String op;
	double vj, vk;
	String qj, qk; // "0" when resolved
	int A;
	String address;//rep of address as a string -> 32+R2
	//int cyclesLeft;	//Qi
	int initialExecutionCycle;
	int jReady;
	int kReady;

	public ResEntry(String op, double vj, double vk, String qj, String qk, int A, int jReady, int kReady) {
		this.op = op;
		this.vj = vj;
		this.vk = vk;
		this.qj = qj;
		this.qk = qk;
		this.A = A;
		this.jReady = jReady;
		this.kReady = kReady;

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
				+ "\tqj: " + qj 
				+ "\tqk: " + qk 
				+ "\tA: " + A 
				+ "\tjReady: " + jReady
				+ "\tkReady: " + kReady
				+ "\n";
	}

	//	void setV(String att, double value) {
	//		if(att.equals("j"))
	//			this.vj = value;
	//		else
	//			this.vk = value;
	//	}




}
