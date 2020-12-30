
public class MemoryUnit {
	double[] mem;

	public MemoryUnit(int size) {
		mem = new double[size];
	}

	double get(int index) {
		if(index>= mem.length) {
			System.out.println("Sorry, index greater than memory size");
			return mem[mem.length-1];
		}
		return mem[index];
	}
}
