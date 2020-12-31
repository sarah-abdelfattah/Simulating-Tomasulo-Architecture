public class ResEntry {
	//note: busy was deleted 3lshan f el reservationStation law null --> busy = false
	//vk should be the offset 
	String op;
	double vj, vk;
	String qj, qk;
	int A;
	int cyclesLeft;

	public ResEntry(String op, double vj, double vk, String qj, String qk, int A) {
		this.op = op;
		this.vj = vj;
		this.vk = vk;
		this.qj = qj;
		this.qk = qk;
		this.A = A;

		if(op.equals("LD") || op.equals("SD"))
			cyclesLeft = 3;
		else if(op.equals("ADD") || op.equals("SUB"))
			cyclesLeft = 4;
		else if(op.equals("MUL"))
			cyclesLeft = 5;
		else 
			cyclesLeft = 7;		
	}	
}
