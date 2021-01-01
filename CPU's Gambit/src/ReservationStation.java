
public class ReservationStation {
	String type; //ADD, MUL, LD, SD

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
		String dest;
		double vj = 0, vk = 0;
		String qj = "0", qk = "0";
		int A=0;		//TODO: when is it computed? 


		if(op.equals("LD") || op.equals("SD")) {
			dest = inst.dest;

			String[] data = inst.src1.split("("); //25(R1) -> 25 , R1)
			vk = Double.parseDouble(data[0]); // 25

			//TODO: need to access the memory to check whether vj or qj
			vj = (int)(512*Math.random()); //a value for R1 , or randomized one?0->511

			//			A = vk+vj;

		} else {
			int index = Integer.parseInt(inst.src1.charAt(1)+"");
			int index2 = Integer.parseInt(inst.src2.charAt(1)+"");

			//	if RegisterFile of index !null --> V else --> Q

			/*A value of 0 indicates that the source operand is 
			already available in Vj or Vk, or is unnecessary. */

			//			 vj=0, vk=0;
			//			 qj="0", qk="0";
		}

		return new ResEntry(op, vj, vk, qj, qk, A);		
	}
	
	public String toString() {
		String s = "";
		
		for(int i =0; i<resEntries.length ;i++)
			s += resEntries[i];
		
		return s;
	}
}
