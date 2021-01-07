package Implementation;

public class MemoryUnit {
	double[] mem;

	public MemoryUnit(int size) {
		mem = new double[size];
	}

	double get(int index) {
		if(index>= mem.length||index<-1023) {
			System.out.println("Sorry, index not valid");
			return mem[mem.length-1];
		}
		if(index<0) {
			index=index+1024;
		}
		return mem[index];
	}

	void set(int index, double value) {
		if(index>= mem.length||index<-1023) {
			System.out.println("Sorry, index not valid");
			return;
		}

		if(index<0) {
			index=index+1024;
		}

		mem[index] = value;
	}
}
