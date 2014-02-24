import java.util.Scanner;

public class AssignmentTwo{
	public static String[][] ChipsSetUpNumbers = new String[25][25];
	static int dice1st;
	static int dice2nd;  
	static int validmovechecking=0;
	static int n=0;
	public static void Initialization(){
		//Initializing values
		ChipsSetUpNumbers[1][0]="O"; ChipsSetUpNumbers[1][1]="2";
		ChipsSetUpNumbers[6][0]="X"; ChipsSetUpNumbers[6][1]="5";
		ChipsSetUpNumbers[8][0]="X"; ChipsSetUpNumbers[8][1]="3";
		ChipsSetUpNumbers[12][0]="O"; ChipsSetUpNumbers[12][1]="5";
		
		ChipsSetUpNumbers[13][0]="X"; ChipsSetUpNumbers[13][1]="5";
		ChipsSetUpNumbers[17][0]="O"; ChipsSetUpNumbers[17][1]="3";
		ChipsSetUpNumbers[19][0]="O"; ChipsSetUpNumbers[19][1]="5";
		ChipsSetUpNumbers[24][0]="X"; ChipsSetUpNumbers[24][1]="2";
	}
	
	//Quit, pass & continue functions{
	public static void quitorpassorcontinuefunction(){
		Scanner quitornot = new Scanner(System.in);
		String thisisstringquit="q";
		String thisisstringcontinue="c";
		String thisisstringpass="pass";
		
		System.out.println("Enter q to quit, c to continue, pass to pass:");
		String takingchoice = quitornot.nextLine();

		if (takingchoice.equalsIgnoreCase(thisisstringcontinue)){
			System.out.println("*Continue function occurred! Backgammon: Go ahead!*\n");
			RollDice();
			System.out.printf("Please enter your move(s) - ");
			if (dice1st!=dice2nd){
				System.out.printf("[Note: Available valid moves %d %d]:\n",dice1st,dice2nd);
			}
			else {
				System.out.printf("[Note: Available valid moves %d %d %d %d]:\n",dice1st,dice2nd,dice1st,dice2nd);
			}
		}
		else if (takingchoice.equalsIgnoreCase(thisisstringpass)){
			System.out.println("*Pass function occurred! Backgammon: Player gives up!*\n");
			settinguptheboard();
			quitorpassorcontinuefunction();
		}
		else if (takingchoice.equalsIgnoreCase(thisisstringquit)){
			System.out.println("*Quit function occurred! Backgammon: GoodBye!*\n");
		}
		else {
			System.out.println("*Invalid Inputs! Backgammon: Re-enter!*\n");
			quitorpassorcontinuefunction();
		}
	}
	//Quit, pass & continue functions here}
	
	public static void RollDice() {
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		dice1st=dice1.show();
		dice2nd=dice2.show();	
		System.out.println("\t    Dice One: " +dice1st+  "; Dice Two: "+dice2nd +"\n");	
	}
	
	public static void makingmoves(int startposition, int endposition){
		if (startposition>=0 && endposition>=0){
			//Check invalid moves such as you cannot move X onto O or vice versa
			if (ChipsSetUpNumbers[endposition][0]!=ChipsSetUpNumbers[startposition][0] && ChipsSetUpNumbers[endposition][0]!="|"){
				validmovechecking++;
			}
			else if (ChipsSetUpNumbers[startposition][0]!="X" && ChipsSetUpNumbers[startposition][0]!="O"){
				validmovechecking++;
			}
			else if (ChipsSetUpNumbers[startposition][0]=="O" && startposition>endposition){
				//O cannot move from right to left
				validmovechecking++;
			}
			else if (ChipsSetUpNumbers[startposition][0]=="X" && endposition>startposition){
				//X cannot move from left to right
				validmovechecking++;
			}
			//If moves are valid, then:
			else {
				//If we move to a new place which means no chips at all,  then:
				if (ChipsSetUpNumbers[endposition][0]!="X" && ChipsSetUpNumbers[endposition][0]!="O"){
					ChipsSetUpNumbers[endposition][0]=ChipsSetUpNumbers[startposition][0];
					ChipsSetUpNumbers[endposition][1]="1";
				}
				//Otherwise: basically, increase the number of chips by one at the new position
				else {
					int checkinginexistence=Integer.parseInt(ChipsSetUpNumbers[endposition][1]);
					checkinginexistence++;
					String movingchipsin = Integer.toString(checkinginexistence);
					ChipsSetUpNumbers[endposition][1]=movingchipsin;
					ChipsSetUpNumbers[endposition][0]=ChipsSetUpNumbers[startposition][0];
				}
				//No matter what moves we make, we will always need to decrease the number of chips by one at where we start
				int converttointeger=Integer.parseInt(ChipsSetUpNumbers[startposition][1]);
				converttointeger--;
				//If one position has been moved to have no chips, then we replace it by "|"
				if(converttointeger>0){
					String convertbacktostring = Integer.toString(converttointeger);
					ChipsSetUpNumbers[startposition][1]=convertbacktostring;
				}
				else{
					ChipsSetUpNumbers[startposition][0]=ChipsSetUpNumbers[startposition][1]=null;
				}
			}
		}
	}
	
	public static void settinguptheboard(){
		//Top Frame
		for (int TopLine =13; TopLine<25; TopLine++){
			if (TopLine == 13 | TopLine == 18 | TopLine == 19 | TopLine == 24){
				System.out.print(TopLine);
				if (TopLine == 13 | TopLine == 19)
					System.out.print("--");
				else if (TopLine == 24)
					System.out.print(" off");
				else
					System.out.print(" Bar ");
			}
			else{ 
				System.out.print("+");
				for (int PrintDash=0;PrintDash<3;PrintDash++){
					System.out.print("-");
				}
			}
		}
				
				for (int InitialValuesNumbers=0;InitialValuesNumbers<24;InitialValuesNumbers++){
					if (ChipsSetUpNumbers[InitialValuesNumbers][0]==null | ChipsSetUpNumbers[InitialValuesNumbers][1]==null){
						ChipsSetUpNumbers[InitialValuesNumbers][0]="|";
						ChipsSetUpNumbers[InitialValuesNumbers][1]=" ";
						//Its "|"+" ", so that to make it like "X" or "O"+ "Numbers" such as "X5"
					}
				}
				System.out.println();
				
				//Place Chips
				for (int PlaceChips=13;PlaceChips<25;PlaceChips++){
					System.out.print(ChipsSetUpNumbers[PlaceChips][0]);
					System.out.print(ChipsSetUpNumbers[PlaceChips][1]);
					if (PlaceChips==18){
						for (int PrintEmptySpace=0;PrintEmptySpace<3;PrintEmptySpace++){
							System.out.print(" ");
						}
					}
					for (int PrintEmptySpace=0;PrintEmptySpace<2;PrintEmptySpace++){
						System.out.print(" ");
					}
				}
						
				System.out.println();
				
				for (int PrintSpace=0;PrintSpace<21;PrintSpace++){
					System.out.print(" ");
				}
				System.out.println();
						
				//Place Chips
				for (int PlaceChips=12;PlaceChips>0;PlaceChips--){
					System.out.print(ChipsSetUpNumbers[PlaceChips][0]);
					System.out.print(ChipsSetUpNumbers[PlaceChips][1]);
					if (PlaceChips==7){
						for (int PrintEmptySpace=0;PrintEmptySpace<3;PrintEmptySpace++){
							System.out.print(" ");
						}
					}
				    for (int PrintEmptySpace=0;PrintEmptySpace<2;PrintEmptySpace++){
						System.out.print(" ");
					}
				}
						
				System.out.println();
				
		//The board: Bottom frame
				for (int BottomLine =12; BottomLine>0; BottomLine--){
					if (BottomLine == 12 | BottomLine == 7 | BottomLine == 6 | BottomLine == 1){
						if(BottomLine ==7 | BottomLine ==6 | BottomLine ==1 ){
							System.out.print("0"+BottomLine);
						}
						else {
							System.out.print(BottomLine);
						}
						if (BottomLine == 12 | BottomLine == 6)
							System.out.print("--");
						else if (BottomLine == 1)
							System.out.print(" off");
						else
							System.out.print(" Bar ");
						
					}
					else{ 
						System.out.print("+");
						for (int PrintDash=0;PrintDash<3;PrintDash++){
							System.out.print("-");
						}
					}
				}
				//Invalid moves checking
				if (validmovechecking>=1){
					System.out.print("\nInvalid Move: " +
							"1. Cannot move X onto O or vice versa!"+
							" or 2. Cannot move from an empty place.");
				}
				System.out.println("\n");
	}
}
