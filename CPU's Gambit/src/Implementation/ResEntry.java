package Implementation;
public class ResEntry {
	//note: busy was deleted 3lshan f el reservationStation law null --> busy = false
	//vk should be the offset 
	public String op;
	public double vj, vk;
	public String qj, qk; // "0" when resolved
	public int A;
	public String address;	//rep of address as a string -> 32+R2
	int initialExecutionCycle;
	int jReady;
	int kReady;

	public ResEntry(String op, double vj, double vk, String qj, String qk, int A, int jReady, int kReady,String address) {
		this.op = op;
		this.vj = vj;
		this.vk = vk;
		this.qj = qj;
		this.qk = qk;
		this.A = A;
		this.jReady = jReady;
		this.kReady = kReady;
		this.address=address;
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
}
