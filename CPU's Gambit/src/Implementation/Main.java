package Implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//offset in LD AND SD should be from -1024->527 (negative base+offset accesses memory from the bottom )->reverse access 
//Mapping of negative memory addresses -1 -> last pos (1023) , -2 ->1022 .. (0 indexed)

import View.GUI;

//register file and memory are initially filled with zeroes , method fillDummy in line 33, fills reg file and memory with dummy data to test properly
//we assume for any block of code entered that all the operands are ready / no hazards stopping us ,(no depending on a previous snippet of code not mentioned)
//if 2 inst try to write on the bus at the same time , FIFO scheme will be done 
//to test load and store problem , try this
public class Main {
	public static ReservationStation LDResStation, SDResStation, addResStation, mulResStation;
	public static InstructionUnit instructionUnit;
	static MemoryUnit memoryUnit;
	public static RegisterFile registerFile;
	static int loadCycles,storeCycles,addCycles,subCycles,mulCycles,divCycles;
	static int lastIssued=-1;
	static int curCycle=1;
	public static String[]insts;
	public static String s;
	public static void init() throws IOException{
		instructionUnit = new InstructionUnit(32);
		// fill instruction unit .. done --> at run TODO: text file
		memoryUnit = new MemoryUnit(1024);
		// fill memory unit .. done --> filldummy()
		registerFile = new RegisterFile(32);//32 fp regs
		// fill register file with RegEntry  .. done --> filldummy()
		insts=new String[32];
//		System.out.println("Please enter # when finished");
//		Scanner sc = new Scanner(System.in);
//		String s = sc.nextLine();
//	    sc.useDelimiter("#"); 

		
		 File file = new File("Tests/test2.txt"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  String st; 
		  int i=0;
		  while (!(st = br.readLine()).equals("#")) {
			  instructionUnit.add(st);
			  insts[i++]=st;
		  }
		  
		  LDResStation = new ReservationStation("LD", Integer.parseInt(br.readLine().split(" ")[4]));
		  SDResStation = new ReservationStation("SD",  Integer.parseInt(br.readLine().split(" ")[4]));

		  addResStation = new ReservationStation("ADD",  Integer.parseInt(br.readLine().split(" ")[4]));
		  mulResStation = new ReservationStation("MUL",  Integer.parseInt(br.readLine().split(" ")[4]));

		  loadCycles=Integer.parseInt(br.readLine().split(" ")[3]);
		  storeCycles=Integer.parseInt(br.readLine().split(" ")[3]);
          addCycles=Integer.parseInt(br.readLine().split(" ")[3]);
		  subCycles=Integer.parseInt(br.readLine().split(" ")[3]);
		  mulCycles=Integer.parseInt(br.readLine().split(" ")[3]);
		  divCycles=Integer.parseInt(br.readLine().split(" ")[3]);

//		//fill data
		fillDummy();
		//new GUI();

		//		System.out.println(instructionUnit);
	}

	private static void fillDummy() {
		for(int i =0 ;i<registerFile.file.length;i++) {
			double value = ((int) (Math.random()*2300))/100.0;
			registerFile.file[i].content = value;
		}
		//		System.out.println(registerFile);

		for(int i =0 ;i<memoryUnit.mem.length;i++) {
			double value =  ((int)(Math.random()*9000))/100.0;
			memoryUnit.mem[i] = value;
		}
	}


	public static void main(String[]args) throws IOException {
		//init();
		//while(nextCycle()!=-1);
		//System.out.println(instructionUnit);
	}
	public static int nextCycle() throws IOException {
		s="";
		//File filex = new File("Tests/Comments"); 
		//Path fileToDeletePath = Paths.get("Tests/Comments");
		//Files.delete(fileToDeletePath);
		//filex.delete();
		//File file = new File("Tests/Comments"); 
		//PrintWriter out=new PrintWriter(System.out);
		if(curCycle==1) {
			init();
		}
		//
		//out.append("hai\n");
		//out.flush();
	
		//BufferedReader br = new BufferedReader(new FileReader(file)); 
		//System.out.println(br.readLine());
	
		//index of the last issued instruction(in instruction unit)
		int numOfInstructions=instructionUnit.numberOfinputs;
			//System.out.println("cycle: " + curCycle);
			Instruction current=lastIssued<numOfInstructions-1?instructionUnit.instArr[lastIssued+1]:null;
			//current is null if we already issued all instructions

			if(current!=null ) {
				int idx=canBeIssued(current);
				//if can be issued is true , it will be added automatically
				//if alu, does it have a place to reside in?
				//if load,does it have a place to reside in? is it preceeded by a store of same effective address?
				//if store,does it have a place to reside in? is it preceeded by a load/store of same effective address?
				//				char prefix=current.type.equals("SUB")?'A':current.type.equals("DIV")?'M':current.type.charAt(0);
				//				current.reservationTag=prefix+""+(idx+1);
				//				System.out.println("current.reservationTag: " + current.reservationTag);
				if(idx!=-1){
					s+="Instruction "+(lastIssued+2)+" is issued\n";
					current.issueCycle=curCycle;
					lastIssued++;
				}else {
					s+="Cannot issue , wait for reservation station to be emptied\n";
				}
				//issue it , put it in corresponding reservation station
			}
			//traverse the instruction unit until before the last issued to update the fields of execution and trying to write results in the bus

			//add an instruction to it's corresponding reservation
			boolean cdbIsBusy=false;
			for(int i=0;i<=lastIssued;i++) { //TODO: to be checked .. less than or less than or equal
				Instruction cur=instructionUnit.instArr[i];
				int executionCycle=cur.executionCycle;
				int finishCycle=cur.finishCycle;
				//TODO: needs to check eno el WB of what is needed is writted in a cycle previous
				if(cur.issueCycle>0 && cur.issueCycle<=curCycle-1&&executionCycle==0 ) {//for issued instructions that did not begin execution yet
					if(canExecute(cur, curCycle)) {
						s+="Instruction "+(i+1)+" has started execution\n";
						cur.executionCycle=curCycle;
						//set initial execution cycle in the corresponding res entry (for gui purpose only)
						setInitialExecutionCycle(cur,curCycle);
						//is there a logic upon beginning execution?
					}
				}

				//following condition is for an instruction to write a result in cdb , it checks if it already executed and finished execution and the common cdb is not busy

				//ex>0 to ensure it's already executing , finish==0 to ensure it's still executing and did not finish yet,3rd condition to check if it's eligible for
				//writing result(done executing in the reservation) , last condition to check if it can publish the result or not(cdb is not busy)
				if(executionCycle!=0&&curCycle-executionCycle==getPromisedCycles(cur)-1) {
					cur.finishExecCycle=curCycle;
					s+="Instruction "+(i+1)+" has finished execution\n";
				}else if(executionCycle!=0&&executionCycle!=curCycle&&curCycle-executionCycle<getPromisedCycles(cur)) {
					s+="Instruction "+(i+1)+" is currently executing\n";
				}
				if(executionCycle>0
						&&finishCycle==0
						&&curCycle-executionCycle>=getPromisedCycles(cur)
						) {
					if(cdbIsBusy) {
						s+="Instruction "+(i+1)+" cannot write as common data bus is busy\n";
						continue;
					}
					s+="Instruction "+(i+1)+" writes result and removed from reservation tag\n";
					cur.finishCycle=curCycle;
					//simulation only,as we do it here in 1 step
					//QUESTION: what shall the below line return if it's a store, maybe value to be stored only for generality?
					//					System.out.println("cur: " + cur);
					double value = execute(cur);
					//publish the result with the tag of current instruction
					//it should be used by any other res station that needs it and also updated in reg file
					publishResult(value,cur.reservationTag, curCycle);
					//remove the instruction from the reservation station(removed with tag)
					deleteInstruction(cur);
					cdbIsBusy=true;
				}
			}
			//			System.out.println(instructionUnit);
			//						System.out.println(registerFile);
			//			System.out.println(instructionUnit);

			//			System.out.println(mulResStation);


		//System.out.println(s);
			if(done()) {
				//out.println("All Done YAY!!");
				//out.flush();
				//out.close();
				s+="\n\n\n\n\nAll done YAAAY!";
				return -1;
			}
		//out.println(s);
		//out.flush();
		//out.close();
		curCycle++;
		return 1;
	}
	private static void setInitialExecutionCycle(Instruction cur,int cycle) {
		int idx=Integer.parseInt(cur.reservationTag.substring(1))-1;//extracts index of it
		if(cur.type.equals("LD")) {
			LDResStation.resEntries[idx].initialExecutionCycle=cycle;
		}else if(cur.type.equals("SD")) {
			SDResStation.resEntries[idx].initialExecutionCycle=cycle;
		}else if(cur.type.equals("MUL.D")||cur.type.equals("DIV.D")) {
			mulResStation.resEntries[idx].initialExecutionCycle=cycle;
		}else {//ADD OR SUB
			addResStation.resEntries[idx].initialExecutionCycle=cycle;
		}
	}

	private static void deleteInstruction(Instruction cur) {
		int idx=Integer.parseInt(cur.reservationTag.substring(1))-1;//extracts index of it
		if(cur.type.equals("LD")) {
			LDResStation.resEntries[idx]=null;
		}else if(cur.type.equals("SD")) {
			SDResStation.resEntries[idx]=null;
		}else if(cur.type.equals("MUL.D") ||cur.type.equals("DIV.D")) {
			mulResStation.resEntries[idx]=null;
		}else {//ADD OR SUB
			addResStation.resEntries[idx]=null;
		}
		//is there any other appropriate logic to be done?
	}

	private static void publishResult(double value, String reservationTag, int currentCycle) {
		//functionality: inst finish f b-publish 

		/*
		 * 1- if inst = SD --> memory only
		 * 2- a) else update regfile 
		 * 	  b) go through all reservations except LD
		 * 	  c)if waited for .. update l v/q="0"
		 *  */

		if(reservationTag.charAt(0) == 'S') {
			int index = -1;
			int tagIndex = Integer.parseInt(reservationTag.substring(1)); //S1
			for(int i = 0; i<SDResStation.resEntries.length ; i++) {
				//System.out.println("tag: "+tagIndex);
				if(tagIndex==i+1 && SDResStation.resEntries[i]!=null) {
					index = SDResStation.resEntries[i].A;
					memoryUnit.set(index, value);
					//SDResStation.resEntries[i].jReady=0;
					//SDResStation.resEntries[i].vj = value;
				}
			}
		
		} else {
			//loop through RegFile
			for(int i = 0; i < registerFile.file.length ; i++ ) {
				if(registerFile.file[i].qi.equals(reservationTag)) {
					registerFile.file[i].content = value;
					registerFile.file[i].qi = "0";
					s+="Tag "+reservationTag+" is updated in register file at F"+i+"\n";
				}
			}

			//loop through ADD reservation stations
			for(int i = 0; i< addResStation.resEntries.length ; i++ ) {
				if(addResStation.resEntries[i] != null && addResStation.resEntries[i].qj.equals(reservationTag)) {
					addResStation.resEntries[i].vj = value;
					addResStation.resEntries[i].qj = "0";
					addResStation.resEntries[i].jReady = currentCycle;	
					s+="Qj in A"+(i+1)+" is updated \n";
				}
				if(addResStation.resEntries[i] != null && addResStation.resEntries[i].qk.equals(reservationTag)) {
					addResStation.resEntries[i].vk = value;
					addResStation.resEntries[i].qk = "0";
					addResStation.resEntries[i].kReady = currentCycle;
					s+="Qk in A"+(i+1)+" is updated \n";
				}
			}

			//loop through MUL reservation stations
			for(int i = 0; i< mulResStation.resEntries.length ; i++ ) {
				if(mulResStation.resEntries[i] != null &&  mulResStation.resEntries[i].qj.equals(reservationTag)) {
					mulResStation.resEntries[i].vj = value;
					mulResStation.resEntries[i].qj = "0";
					mulResStation.resEntries[i].jReady = currentCycle;
					s+="Qj in M"+(i+1)+" is updated \n";
				}
				if(mulResStation.resEntries[i] != null && mulResStation.resEntries[i].qk.equals(reservationTag)) {
					mulResStation.resEntries[i].vk = value;
					mulResStation.resEntries[i].qk = "0";
					mulResStation.resEntries[i].kReady = currentCycle;
					s+="Qk in M"+(i+1)+" is updated \n";
				}
			}
			for(int i = 0; i< SDResStation.resEntries.length ; i++ ) {
				if(SDResStation.resEntries[i] != null &&  SDResStation.resEntries[i].qj.equals(reservationTag)) {
					SDResStation.resEntries[i].vj = value;
					SDResStation.resEntries[i].qj = "0";
					SDResStation.resEntries[i].jReady = currentCycle;
					s+="Qi in S"+(i+1)+" is updated \n";
				}
				
			}
		}
	}

	private static double execute(Instruction cur) {
		double ans=0;
		//execute from reservation station, tagIndex is index within the reservation station
		int tagIndex=Integer.parseInt(cur.reservationTag.substring(1))-1;//if M1 ,tagIndex=0
		ResEntry entry=null;

		if(cur.type.equals("LD")) {
			entry=LDResStation.resEntries[tagIndex];
			ans=memoryUnit.get(entry.A);
		}else if(cur.type.equals("SD")) {
			entry=SDResStation.resEntries[tagIndex];
			ans=entry.vj;
		}else if(cur.type.equals("DIV.D")) {
			entry=mulResStation.resEntries[tagIndex];
			ans=(double)entry.vj/entry.vk;
		}else if(cur.type.equals("MUL.D")){
			entry=mulResStation.resEntries[tagIndex];
			ans=(double)entry.vj*entry.vk;
		}else if(cur.type.equals("ADD.D")){
			entry=addResStation.resEntries[tagIndex];
			ans=entry.vj+entry.vk;
		}else {//SUB
			entry=addResStation.resEntries[tagIndex];
			ans=entry.vj-entry.vk;
		}
		return ans;
	}


	private static int getPromisedCycles(Instruction cur) {
		String op=cur.type;
		if(op.equals("LD") )
			return loadCycles;
		else if(op.equals("SD")) 
			return storeCycles;
		else if(op.equals("ADD.D"))
			return addCycles;
		else if( op.equals("SUB.D"))
			return subCycles;
		else if(op.equals("MUL.D"))
			return mulCycles;
		else 
			return divCycles; //DIV		
	}


	private static boolean canExecute(Instruction cur, int currentCycle) {
		int idx=Integer.parseInt(cur.reservationTag.substring(1))-1;//extracts index of it
		//load is not needed to be checked as it's not waiting for something
		ResEntry res=null;
		if(cur.type.equals("LD")) {
			return true;
		}
		if(cur.type.equals("SD")) {
			res=SDResStation.resEntries[idx] ;
		} else if(cur.type.equals("MUL.D")||cur.type.equals("DIV.D")) {
			res=mulResStation.resEntries[idx];

		} else{//ADD OR SUB
			res=addResStation.resEntries[idx];
		}
				//System.out.println("res: " + res);
		return res.qj.equals("0") 
				&& res.qk.equals("0") 
				&& res.jReady != -1
				&& res.kReady != -1
				&& currentCycle > res.jReady 
				&& currentCycle > res.kReady;
				//operands are ready
	}


	private static int canBeIssued(Instruction current) {
		String op=current.type;
		if(op.equals("LD") )
			return LDResStation.add(current,registerFile,LDResStation,SDResStation);
		else if(op.equals("SD"))
			return SDResStation.add(current,registerFile,LDResStation,SDResStation);
		else if(op.equals("ADD.D") || op.equals("SUB.D"))
			return addResStation.add(current,registerFile,LDResStation,SDResStation);
		else //MUL AND DIV
			return mulResStation.add(current,registerFile,LDResStation,SDResStation);
	}


	private static boolean done() {
		for(int i=0;i<instructionUnit.numberOfinputs;i++) {
			Instruction cur=instructionUnit.instArr[i];
			if(cur.finishCycle==0)//means that a single instruction didn't finish execution
				return false;
		}
		return true;
	}
}