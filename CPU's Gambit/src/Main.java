import java.util.Scanner;

public class Main {
	static ReservationStation LDResStation, SDResStation, addResStation, mulResStation;
	static InstructionUnit instructionUnit;
	static MemoryUnit memoryUnit;
	static RegisterFile registerFile;

	static void init(){
		LDResStation = new ReservationStation("LD", 5);
		SDResStation = new ReservationStation("SD", 5);

		addResStation = new ReservationStation("ADD", 4);
		mulResStation = new ReservationStation("MUL", 3);

		instructionUnit = new InstructionUnit(20);
		//TODO: fill instruction unit
		memoryUnit = new MemoryUnit(1024);
		//TODO: fill memory unit
		registerFile = new RegisterFile(10);
		//TODO: fill register file with RegEntry
		
		System.out.println("Please enter # when finished");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

//		Thread.sleep(3000);
		for(int i = 0 ; i<instructionUnit.instArr.length && !s.equals("#") ; i++) {
			instructionUnit.add(s);
			s = sc.nextLine();
		}
		
		System.out.println(instructionUnit);
	}


	public static void main(String[]args) {
		init();
	}
}