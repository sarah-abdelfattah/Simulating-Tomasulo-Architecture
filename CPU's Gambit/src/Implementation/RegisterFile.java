package Implementation;

public class RegisterFile {

	public static class RegEntry{
		public String qi;
		public double content;

		RegEntry(String qi, double content) {
			this.qi = qi;
			this.content = content;
		}

		@Override
		public String toString() {
			return "RegEntry:" 
					+ "\t" + qi 
					+ "\t" + content 
					+ "\n";
		}
	}

	public RegEntry[] file;


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


	@Override
	public String toString() {
		
		String s ="";
		for(int i=0; i<file.length;i++) {
			s += file[i] + "\t F" + i + "\n" ; 
		}
		return s;
	}

}
