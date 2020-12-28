
public class ReservationStation {
	String type;//ADD, MUL, LD, SD
	ResEntry[] entry;

	public ReservationStation(String type, int size) {
		this.type = type;
		entry = new ResEntry[size];
	}	

	boolean add(Instruction inst){

		int idx= hasPlace();
		if(idx==-1)return false;
		ResEntry rs = parse(inst);
		
		return true;
	}

	private int hasPlace() {
		for(int i= 0; i< entry.length; i++) {
			if(entry[i] == null) {
				//entry[i] = rs;
				return i;
			}
		}
		return -1;
	}

	ResEntry parse(Instruction inst) {
		String op = inst.type;
		double vj=0, vk=0;
		String qj="", qk="";
		int A=0;		//TODO: when is it computed? 
		if(op.equals("SD")||op.equals("LD")) {
			String[]comp=inst.src1.split("(");//25(R1) -> 25 , R1)
			int offset=Integer.parseInt(comp[0]);
			int base=(int)(512*Math.random());//a value for R1 , or randomized one?0->511
			//0<=base+offset<=1023(size of mem 1024)
			A=base+offset;
		}else {
			
		}
		
		

		return new ResEntry(op, vj, vk, qj, qk, A);
		
		
	}
}
