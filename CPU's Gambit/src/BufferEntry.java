
public class BufferEntry {
	//LD f0, 32(R2)

	String type; //LD or SD

	String dest;

	int address; 

	int cyclesLeft;

	public BufferEntry(String type, String dest, int address) {
		this.type = type;
		this.dest = dest;
		this.address = address;
		cyclesLeft = 3;
	}
}
