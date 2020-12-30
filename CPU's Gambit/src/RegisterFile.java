
public class RegisterFile {

	static class RegEntry{
		String qi;
		double content;

		RegEntry(String qi, double content) {
			this.qi = qi;
			this.content = content;
		}

	}

	static RegEntry[] file;


	public RegisterFile(int size) {
		file = new RegEntry[size];

		for(int i = 0; i<size; i++) {
			file[i] = new RegEntry("0", 0);
		}
	}


	public RegEntry getFile(int i) {
		return file[i];
	}


	public void setFile(int i, RegEntry r) {
		file[i] = r;
	}


}
