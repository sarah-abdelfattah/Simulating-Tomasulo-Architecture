
public class ReservationStation {
	String type;//ADD, MUL

	ResEntry[] resEntries;


	public ReservationStation(String type, int size) {
		this.type = type;
		resEntries = new ResEntry[size];
	}	

	boolean add(Instruction inst){

		int idx= hasPlace();
		if(idx==-1)
			return false;

		ResEntry rs = parse(inst);
		resEntries[idx] = rs;

		return true;
	}

	int hasPlace() {
		for(int i= 0; i< resEntries.length; i++) {
			if(resEntries[i] == null) {
				//entry[i] = rs;
				return i;
			}
		}
		return -1;
	}

	ResEntry parse(Instruction inst) {
		//ADD F0,F1,F2
		String op = inst.type;

		int index = Integer.parseInt(inst.src1.charAt(1)+"");
		int index2 = Integer.parseInt(inst.src2.charAt(1)+"");

		//if RegisterFile of index !null --> V else --> Q
		double vj=0, vk=0;
		String qj="", qk="";
		
		int A=0;		//TODO: when is it computed? 


		return new ResEntry(op, vj, vk, qj, qk, A);		
	}
}
