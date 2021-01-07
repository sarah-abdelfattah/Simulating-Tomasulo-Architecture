package Implementation;

public class ReservationStation {
	public String type; //ADD, MUL, LD, SD

	public ResEntry[] resEntries;

	public int []integerRegisters;//Array for R0,R1,R2,...,R31 . Having values --> 0,16,32,48,...,496
	public ReservationStation(String type, int size) {
		this.type = type;
		resEntries = new ResEntry[size];
		integerRegisters=new int[32];
		for(int i=0;i<32;i++) {
			integerRegisters[i]=i*16;
		}
	}	

	int add(Instruction inst,RegisterFile rf,ReservationStation ld,ReservationStation sd){//uses register file to read/write tags from/into it

		int idx= hasPlace();
		if(idx==-1)
			return -1;
		ResEntry rs = parse(inst,rf);
		//check for loads and stores whether the current entry can be added or not
		boolean isLoad = inst.type.equals("LD");
		boolean isStore = inst.type.equals("SD");
		if((isLoad||isStore) && conflicting(rs.A,isLoad,ld,sd)) {
			//if cur is load it checks whether there is a store having the same effective address already exists
			//if cur is store checks whether there is a store/load having the same effective address already exists
			//those are handled in method conflicting
			return -1;
		}
		resEntries[idx] = rs;

		char prefix=inst.type.equals("SUB.D")?'A':inst.type.equals("DIV.D")?'M':inst.type.charAt(0);
		inst.reservationTag=prefix+""+(idx+1);
		
		if(!inst.type.equals("SD")) {
			int idx2 = Integer.parseInt(inst.dest.substring(1));
//			System.out.println("inst: " + inst);
//			System.out.println("idx: " + idx);

			rf.file[idx2].qi = inst.reservationTag;//writing in reg file
		}
		
		return idx;
	}

	private boolean conflicting(int a, boolean isLoad, ReservationStation ld, ReservationStation sd) {
		// for loads and stores only
		for(int i=0;i<sd.resEntries.length;i++) {
			ResEntry res=sd.resEntries[i];
			if(res!=null) {
				if(a==res.A)return true;
			}
		}
		for(int i=0;i<ld.resEntries.length&&!isLoad;i++) {//for stores checking (!isLoad)
			ResEntry res=ld.resEntries[i];
			if(res!=null) {
				if(a==res.A)return true;
			}
		}
		return false;
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

	ResEntry parse(Instruction inst,RegisterFile rf) {//CHECK
		//ADD F0,F1,F2
		String op = inst.type;
		String dest= inst.dest;;
		double vj = 0, vk = 0;
		String qj = "0", qk = "0";
		int A=0;		//when is it computed? upon issuing directly(right here)
		int jReady=-1, kReady =-1;
		String address="";

		if(op.equals("LD") || op.equals("SD")) {
			String[] data = inst.src1.split("\\("); //25(R1) -> 25 , R1)
			int offset = Integer.parseInt(data[0]); // 25 , offsets are integers

			int baseIdx = Integer.parseInt(data[1].substring(1,data[1].length()-1)); //a value for R1 , or randomized one?0->511
			int base = integerRegisters[baseIdx];
			A = base+offset;
			
			if(op.equals("SD")) {
				//it has vj and qj(may be waiting for a value to be computed)
				//TODO check whether "dest" is available or yet to be computed by another instruction
				int idx=Integer.parseInt(dest.substring(1));
				if(rf.file[idx].qi.equals("0")) {
					vj=rf.file[idx].content;
					jReady=0;
					
				}else {
					qj=rf.file[idx].qi;
				}
				kReady=0;//edited
			}
			address=offset+"+"+data[1].substring(0,data[1].length()-1)+"="+A;
			
		} else {
			int index = Integer.parseInt(inst.src1.substring(1)+"");
			int index2 = Integer.parseInt(inst.src2.substring(1)+"");
			//System.out.println("index: " + index + " index2: " + index2);

			//check for src1 operand if it has a value or a reference
			if(rf.file[index].qi.equals("0")) {
				
				vj=rf.file[index].content;
				jReady=0;
			}else {
				qj=rf.file[index].qi;
			}
			//check for src2 operand if it has a value or a reference
			if(rf.file[index2].qi.equals("0")) {
				vk=rf.file[index2].content;
				kReady=0;
			}else {
				qk=rf.file[index2].qi;
			}

			//	if RegisterFile of index !null --> V else --> Q

			/*A value of 0 indicates that the source operand is 
			already available in Vj or Vk, or is unnecessary. */

			//  ===> an empty string would suffice , but will leave it as it is 

			//			 vj=0, vk=0;
			//			 qj="0", qk="0";
		}
//		if(!op.equals("SD")) {
//			int idx = Integer.parseInt(dest.substring(1));
//			System.out.println("inst: " + inst);
//			System.out.println("idx: " + idx);
//
//			rf.file[idx].qi = inst.reservationTag;//writing in reg file
//		}
		return new ResEntry(op, vj, vk, qj, qk, A, jReady, kReady,address);		
	}

	public String toString() {
		String s = "";

		for(int i =0; i<resEntries.length ;i++) {
			
			char prefix=type.equals("SUB.D")?'A':type.equals("DIV.D")?'M':type.charAt(0);
			String tag = prefix+""+(i+1);

			s += tag +" " + resEntries[i] + "\n";
		}

		return s;
	}
}
