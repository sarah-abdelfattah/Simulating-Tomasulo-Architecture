
public class Main {
	public static void main(String[]args) {
		Buffer LDBuffer = new Buffer("LD", 5);
		Buffer SDBuffer = new Buffer("SD", 5);

		ReservationStation addResStation = new ReservationStation("ADD", 3);
		ReservationStation mulResStation = new ReservationStation("MUL", 2);

		InstructionUnit instructionUnit = new InstructionUnit(7);
		//TODO: fill instruction unit
		MemoryUnit memoryUnit = new MemoryUnit(1024);
		//TODO: fill memory unit

		RegisterFile registerFile = new RegisterFile(10);
		//TODO: fill register file with RegEntry
	}
}