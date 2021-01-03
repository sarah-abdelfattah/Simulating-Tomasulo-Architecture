import java.util.Scanner;
//offset in LD AND SD should be from -1024->527 (negative base+offset accesses memory from the bottom )->reverse access 
//Mapping of negative memory addresses -1 -> last pos (1023) , -2 ->1022 .. (0 indexed)

//register file and memory are initially filled with zeroes , method fillDummy in line 33, fills reg file and memory with dummy data to test properly
//we assume for any block of code entered that all the operands are ready / no hazards stopping us ,(no depending on a previous snippet of code not mentioned)
//if 2 inst try to write on the bus at the same time , FIFO scheme will be done 
//to test load and store problem , try this
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

		instructionUnit = new InstructionUnit(32);
		//TODO: fill instruction unit
		memoryUnit = new MemoryUnit(1024);
		//TODO: fill memory unit
		registerFile = new RegisterFile(32);//32 fp regs
		//TODO: fill register file with RegEntry
		
		System.out.println("Please enter # when finished");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

//		Thread.sleep(3000);
		for(int i = 0 ; i<instructionUnit.instArr.length && !s.equals("#") ; i++) {
			instructionUnit.add(s);
			s = sc.nextLine();
		}
		//fill data
		fillDummy();
		
		System.out.println(instructionUnit);
	}


	private static void fillDummy() {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[]args) {
		init();
		int lastIssued=-1;//index of the last issued instruction(in instruction unit)
		int numOfInstructions=instructionUnit.numberOfinputs;
		for(int curCycle=1;;curCycle++) {
			Instruction current=lastIssued<numOfInstructions-1?instructionUnit.instArr[lastIssued+1]:null;
			//current is null if we already issued all instructions
			if(current!=null&&canBeIssued(current)) {//if can be issued is true , it will be added automatically
				//if alu, does it have a place to reside in?
				//if load,does it have a place to reside in? is it preceeded by a store of same effective address?
				//if store,does it have a place to reside in? is it preceeded by a load/store of same effective address?
				current.issueCycle=curCycle;
				lastIssued++;
				//issue it , put it in corresponding reservation station
			}
			//traverse the instruction unit until before the last issued to update the fields of execution and trying to write results in the bus
			
			//add an instruction to it's corresponding reservation
			boolean cdbIsBusy=false;
			for(int i=0;i<=lastIssued;i++) {
				Instruction cur=instructionUnit.instArr[i];
				int executionCycle=cur.executionCycle;
				int finishCycle=cur.finishCycle;
				if(cur.issueCycle<=curCycle-1&&executionCycle==0) {//for issued instructions that did not begin execution yet
					if(canExecute(cur)) {
						cur.executionCycle=curCycle;
						//set initial execution cycle in the corresponding res entry (for gui purpose only)
						setInitialExecutionCycle(cur,curCycle);
						//is there a logic upon beginning execution?
					}
				}
				
				//following condition is for an instruction to write a result in cdb , it checks if it already executed and finished execution and the common cdb is not busy
				
				//ex>0 to ensure it's already executing , finish==0 to ensure it's still executing and did not finish yet,3rd condition to check if it's eligible for
				//writing result(done executing in the reservation) , last condition to check if it can publish the result or not(cdb is not busy)
				if(executionCycle>0&&finishCycle==0&&curCycle-executionCycle>=getPromisedCycles(cur)&&!cdbIsBusy) {
					cur.finishCycle=curCycle;
					//simulation only,as we do it here in 1 step
					//QUESTION: what shall the below line return if it's a store, maybe value to be stored only for generality?
					double value=execute(cur);
					//publish the result with the tag of current instruction
					//it should be used by any other res station that needs it and also updated in reg file
					publishResult(value,cur.reservationTag);
					//remove the instruction from the reservation station(removed with tag)
					deleteInstruction(cur);
					cdbIsBusy=true;
				}
			}
			
			if(done()) {
				System.out.println("All is done");
				break;
			}
		}
		
	}


	private static void setInitialExecutionCycle(Instruction cur,int cycle) {
		int idx=Integer.parseInt(cur.reservationTag.substring(1))-1;//extracts index of it
		if(cur.type=="LD") {
			LDResStation.resEntries[idx].initialExecutionCycle=cycle;
		}else if(cur.type=="SD") {
			SDResStation.resEntries[idx].initialExecutionCycle=cycle;
		}else if(cur.type=="MUL"||cur.type=="DIV") {
			addResStation.resEntries[idx].initialExecutionCycle=cycle;
		}else {//ADD OR SUB
			mulResStation.resEntries[idx].initialExecutionCycle=cycle;
		}
	}


	private static void deleteInstruction(Instruction cur) {
		int idx=Integer.parseInt(cur.reservationTag.substring(1))-1;//extracts index of it
		if(cur.type=="LD") {
			LDResStation.resEntries[idx]=null;
		}else if(cur.type=="SD") {
			SDResStation.resEntries[idx]=null;
		}else if(cur.type=="MUL"||cur.type=="DIV") {
			addResStation.resEntries[idx]=null;
		}else {//ADD OR SUB
			mulResStation.resEntries[idx]=null;
		}
		//is there any other appropriate logic to be done?
	}


	private static void publishResult(double value, String reservationTag) {
		// TODO Auto-generated method stub
		
	}


	private static double execute(Instruction cur) {
		double ans=0;
		//execute from reservation station, tagIndex is index within the reservation station
		int tagIndex=Integer.parseInt(cur.reservationTag.substring(1))-1;//if M1 ,tagIndex=0
		ResEntry entry=null;
		
		if(cur.type=="LD") {
			entry=LDResStation.resEntries[tagIndex];
			ans=memoryUnit.get(entry.A);
		}else if(cur.type=="SD") {
			entry=LDResStation.resEntries[tagIndex];
			ans=entry.vj;
		}else if(cur.type=="DIV") {
			entry=mulResStation.resEntries[tagIndex];
			ans=(double)entry.vj/entry.vk;
		}else if(cur.type=="MUL"){
			entry=mulResStation.resEntries[tagIndex];
			ans=entry.vj*entry.vk;
		}else if(cur.type=="ADD"){
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
		if(op.equals("LD") || op.equals("SD"))
			return 2;
		else if(op.equals("ADD") || op.equals("SUB"))
			return 3;
		else if(op.equals("MUL"))
			return 5;
		else 
			return 7;		
	}


	private static boolean canExecute(Instruction cur) {
		int idx=Integer.parseInt(cur.reservationTag.substring(1))-1;//extracts index of it
		//load is not needed to be checked as it's not waiting for something
		ResEntry res=null;
		 if(cur.type=="LD") {
			 return true;
		 }
		 if(cur.type=="SD") {
			 res=SDResStation.resEntries[idx];
		}else if(cur.type=="MUL"||cur.type=="DIV") {
			 res=addResStation.resEntries[idx];
		}else{//ADD OR SUB
			 res=mulResStation.resEntries[idx];
		}
		return res.qj.equals("0")&&res.qk.equals("0");//operands are ready
	}


	private static boolean canBeIssued(Instruction current) {
		String op=current.type;
		if(op.equals("LD") )
			return LDResStation.add(current,registerFile,LDResStation,SDResStation);
		else if(op.equals("SD"))
			return SDResStation.add(current,registerFile,LDResStation,SDResStation);
		else if(op.equals("ADD") || op.equals("SUB"))
			return addResStation.add(current,registerFile,LDResStation,SDResStation);
		else //MUL AND DIV
			return mulResStation.add(current,registerFile,LDResStation,SDResStation);
		
	}


	private static boolean done() {
		for(int i=0;i<instructionUnit.instArr.length;i++) {
			Instruction cur=instructionUnit.instArr[i];
			if(cur.finishCycle==0)//means that a single instruction didn't finish execution
				return false;
		}
		return true;
	}
}