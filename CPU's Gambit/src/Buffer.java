
public class Buffer {
	String type;	// LD, SD

	BufferEntry[] bufferEntries;


	public Buffer(String type, int size) {
		this.type = type;
		bufferEntries = new BufferEntry[size];
	}	

	boolean add(Instruction inst){
		int idx= hasPlace();
		if(idx==-1)
			return false;

		BufferEntry bf = parse(inst);
		bufferEntries[idx] = bf;

		return true;
	}

	BufferEntry parse(Instruction inst) {
		//LD F0,32(R2)

		String op = inst.type;
		String dest = inst.dest;

		String[] data = inst.src1.split("("); //25(R1) -> 25 , R1)
		int offset = Integer.parseInt(data[0]);
		int base = (int)(512*Math.random()); //a value for R1 , or randomized one?0->511

		return new BufferEntry(op, dest, offset + base);
	}

	private int hasPlace() {
		for(int i= 0; i< bufferEntries.length; i++) {
			if(bufferEntries[i] == null) {
				return i;
			}
		}
		return -1;
	}

}
