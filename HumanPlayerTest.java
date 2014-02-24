import java.util.Scanner;


public class HumanPlayerTest extends AssignmentTwo{
	
	public static void main(String[] args){
		System.out.println("\n\t **Welcome to \"3in1\" Backgammon Game!**\n");
		Initialization();
		gameimplementation();
	}
	
	public static void gameimplementation(){
		Scanner a = new Scanner(System.in);
		
		String userinputs;
		int positionfirstselection=-1;
		int positionsecondselection=-1;
		int positionsecondselectionforO=-1;
		int positionsecondselectionforX=-1;
		
		String[] SepareteInputs;	
		int numberofmovescanbemade;
		
		do{
			settinguptheboard();
			quitorpassorcontinuefunction();
			
			userinputs=a.nextLine();
			String[] TakingInputs = userinputs.split("-?\\s+");
			
			if (dice1st==dice2nd){
				numberofmovescanbemade=4;
			}
			else {
				numberofmovescanbemade=2;
			}
			
			//Syntax error checking: pattern: "12-3" not any others else
			int checkingvalidinputs=0;
			for(int number=0;number<numberofmovescanbemade && number<TakingInputs.length;number++){
				
				if (TakingInputs[number].matches("[0-9]+-+[0-9]")){
					checkingvalidinputs++;
				}
				else if (checkingvalidinputs!=TakingInputs.length){
					System.out.println("*Syntax error: Backgammon: inputs must be in forms like: 12-3! - Re-enter*");
					gameimplementation();
				}
			}
			//Syntax error checking: pattern: "12-3" not any others else
			
			//bear off checking: e.g. "6-6 4-6" is considered as a syntax error{ 
			String[] checkinginvalidinputsOne=TakingInputs[0].split("-");
			String[] checkinginvalidinputsTwo=TakingInputs[1].split("-");
			if (checkinginvalidinputsOne[1].equals(checkinginvalidinputsTwo[1]) && numberofmovescanbemade!=4){
				System.out.println("*Syntax error: Backgammon: Invalid Inputs - Ren-enter:*\n");
				gameimplementation();
			}
			//bear off checking}
			
			for (int counter=0;counter<numberofmovescanbemade && counter<TakingInputs.length;counter++){
				SepareteInputs = TakingInputs[counter].split("-");
				
				positionfirstselection=Integer.parseInt(SepareteInputs[0]);
				positionsecondselection=Integer.parseInt(SepareteInputs[1]);
				
				if (ChipsSetUpNumbers[positionfirstselection][0]=="O"){
					positionsecondselectionforO=positionfirstselection+positionsecondselection;
					positionsecondselection=positionsecondselectionforO;
				}
				else if (ChipsSetUpNumbers[positionfirstselection][0]=="X"){
					positionsecondselectionforX=positionfirstselection-positionsecondselection;
					positionsecondselection=positionsecondselectionforX;
				}			
				makingmoves(positionfirstselection,positionsecondselection);
			}
		}while(n<100);
	}
	//Human Player Execution}
}
