public class InstructionUnit {
	Instruction [] instQ;
	int numberOfinputs;

	public InstructionUnit(int n) {
		instQ = new Instruction[n];	
		numberOfinputs = 0;
	}	
	
	void add(String s) {
		Instruction i = parse(s); 
		if(numberOfinputs < instQ.length)
			instQ[numberOfinputs++] = i;
		else
			System.out.println("Sorry, instruction register is full");
	}
	
	Instruction get(int index) {
		return instQ[index];
	}

	Instruction parse(String s) {
		//ta5od el instruction k string .. to convert it to instance of instruction 
		
		String[] tokens = s.split(" "); 
		String type = tokens[0]; //type of instruction
		String[] reg = tokens[1].split(",");
		String dest = reg[0];
		String src1 = reg[1];
		String src2 = type.equals("LD") || type.equals("SD") ? "" : reg[2];
		
		return new Instruction(type, dest, src1, src2);
		}	
}
