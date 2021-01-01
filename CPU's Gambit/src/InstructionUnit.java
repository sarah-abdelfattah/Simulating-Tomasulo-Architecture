public class InstructionUnit {
	Instruction[] instArr;
	int numberOfinputs;

	public InstructionUnit(int n) {
		instArr = new Instruction[n];	
		numberOfinputs = 0;
	}	

	void add(String s) {
		Instruction i = parse(s); 
		if(numberOfinputs < instArr.length)
			instArr[numberOfinputs++] = i;
		else
			System.out.println("Sorry, instruction register is full");
	}

	Instruction get(int index) {
		return instArr[index];
	}

	Instruction parse(String s) {
		//ta5od el instruction k string .. to convert it to instance of instruction 

		String[] tokens = s.split(" "); 
		String type = tokens[0]; //type of instruction

		//F0,F1,F2 or F0,32(R2)
		String[] reg = tokens[1].split(",");
		String dest = reg[0];
		String src1 = reg[1];
		String src2 = type.equals("LD") || type.equals("SD") ? "" : reg[2];

		return new Instruction(type, dest, src1, src2);
	}	

	public String toString() {
		String s = "";
		
		for(int i =0; i<numberOfinputs ;i++)
			s += instArr[i];
		
		return s;
	}
}
