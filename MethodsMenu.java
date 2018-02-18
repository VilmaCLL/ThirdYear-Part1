import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * 
 * @author Catalina Hernández Morales - 2015295
 * @author Vilma López Lemoine - 2015210
 * 
 * This class is the implementation of the Double Linked List, it generates the current list by
 * creating a new 'MigrationLinkedList' with the functionality we designed for it.
 * This is also the part of the system that interacts with the user.
 */


public class MethodsMenu {

	//Global variables initialized.
	String 	option 		= "";
	String 	first_name 	= "";
	String 	last_name 	= "";
	Date 	date 		= new Date() ;
	String 	passport	="";
	int 	priority 	= 0;
	int 	data		= 0;
	
	//BufferedReader will be used by two methods to improve validations.
	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	
	//Instantiation of the double linked list created and of the objects to be added to it.
	Individual thisOne = null; 
	MigrationLinkedList todaysList;

	// Constructor calls the MigrationLinkedList class and starts the Main Menu
	public MethodsMenu(){  

		todaysList = new MigrationLinkedList();

		showMainMenu();

	}

		//Main menu to show options to user and get initial input.
		public void showMainMenu(){ 		

				System.out.println("��.�.�����.�.�Immigration Office�.�.�����.�.��");
				System.out.println("");
				System.out.println("");
				System.out.println("[1] Add New Applicant to Queue");  
				System.out.println("[2] See Current Queue ");
				System.out.println("[3] Find Applicant by Id"); 				
				System.out.println("[4] Delete Applicant from Queue");	
				System.out.println("[5] Update Applicant Details");		
				System.out.println("[6] Delete Last Places of the Queue");
				System.out.println("[7] Insert Applicant in Specified Position on Queue ");
				System.out.println("[8] Exit Program ");
				System.out.println("");
				System.out.println("Please select one option:");

				//methodReadOption(int) is called to read input and validate options (1-8 allowed only).
				option = methodReadOption(8);
	
		
						if (option.equals("1")){
							
								addingDetails ();
		
						}else if (option.equals("2")){
							
							System.out.println(todaysList.fullQueue());
							System.out.println("");
							showMainMenu();
		
						}else if (option.equals("3")){
							
							findIndividualOnList();
							
						}else if (option.equals("4")){
							
							deleteOneApplicantById ();
							
						}else if (option.equals("5")){
							
							updateApplicant ();
							
							
						}else if (option.equals("6")){
							
							deleteNPositionsBackOfList();
							
						}else if (option.equals("7")){
							
							addInSpecifiedPosition ();
									
						}else if (option.equals("8")){
							
							exit();
						}

		}

		
		//Gets the input from user to search the queue based on id. 
		//Set the path for using the method for searching, catching also exceptions 
		//to prevent the program to crash.
		public void findIndividualOnList(){		
			
			int IdNumber = 0;
			
			System.out.println("Please Enter Applicant Id:");
			
			IdNumber = Integer.parseInt(methodReadInput());
			
				try {
					
					todaysList.findIndividualById(IdNumber);
					
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			
				
				System.out.println("");
				showMainMenu();
			
			
		}
		
		//Gets the input from user (position to be added to) and adds a new applicant based it.
		//Set the path for using the method for adding in specified position, catching also exceptions 
		//to prevent the program to crash.	
		public void addInSpecifiedPosition (){
			
				if (todaysList.isEmpty()){
					
					System.out.println("This Will Be the First Applicant Added on Queue");
					System.out.println("");
					addingDetails();
					
				}else {
					int thisPosition;
					System.out.println("Please Enter Position to Insert");
					thisPosition = Integer.parseInt(methodReadInput());
					
						if (thisPosition > todaysList.size + 1 ){
					
							System.out.println("The Position You Are Trying to Reach Is Greater than the Queue");
							System.out.println("");
							showMainMenu();
				
						}else
							
							try {
									System.out.println("");
									System.out.println("Please Enter Applicant's Name:");
									first_name = methodReadInput();
									System.out.println("");
						
									System.out.println("Please Enter Applicant's Last Name:");
									last_name = methodReadInput();
									System.out.println("");
						
									System.out.println("Registration Date Is " + " " + date.toString());
								    System.out.println("");
						
								    System.out.println("Please Enter Applicant's Passport Number:");
									passport = methodReadInput();
									System.out.println("");
									
								thisOne = new Individual (first_name, last_name, passport, 0);
								todaysList.addInSpecifiedPosition(thisOne, thisPosition);
									
								System.out.println("New Applicant Added in Position:" + thisPosition);
									
								showMainMenu();
									
							}catch (Exception e) {
									
									System.out.println(e.getMessage());
									System.out.println("");
									showMainMenu();
							}
						
				
			    }
		}
		
		//Deletes an specified applicant from the list based on ID.
		//Calls the searching by ID method of the double linked list and the calls the method 
		//to delete this individual.
		//Catches the exceptions thrown by each of those methods.
		public void deleteOneApplicantById (){
	
			if (todaysList.isEmpty()){
				
				System.out.println("There Are No Applicants Currently on Queue");
				System.out.println("");
				showMainMenu();
				
			}else if (todaysList.size == 1){
				System.out.println("You Cannot Delete the Full Queue");
				System.out.println("");
				showMainMenu();
			
			}else
				
				System.out.println("Please enter Applicant Id");
					
				data = Integer.parseInt(methodReadInput());
					
					try {
						todaysList.findIndividualById(data);
						System.out.println("");
						System.out.println("Delete this Applicant from the list? (Press 1 to Confirm / Press 2 for MAIN MENU)?");
					
						option = methodReadOption(2);
				 	 
							if (option.equals("1")){
								todaysList.deletePositionOnList(data);
								System.out.println("Applicant Deleted from Queue");
								System.out.println("");
								showMainMenu();
				 		
							}else if (option.equals("2")){
								showMainMenu();
							} 
							
						}catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("");
						showMainMenu();
						}
				
			}
		
		//Calls the method to delete N positions at the back of the queue.
		//Gets the input from user (number of positions to be deleted).
		//Catches the exceptions.
		public void deleteNPositionsBackOfList(){
			
			if (todaysList.isEmpty()){
				System.out.println("There Are No Applicants Currently on Queue");
				System.out.println("");
				showMainMenu();
			
			}else
				
				System.out.println("Please Enter Number of Positions to Delete:");
				data = Integer.parseInt(methodReadInput());	
				
				System.out.println("");
				System.out.println("Please Review the Current Queue Before Proceeding");
				System.out.println("");
				System.out.println(todaysList.fullQueue());
				
				System.out.println("");
				System.out.println("Are You Sure You Want to Delete the Last " +(data)+ " Positions from this Queue? (Press 1 to Confirm / Press 2 for MAIN MENU)?");
			
				option = methodReadOption(2);
		 	 
						if (option.equals("1")){
							
								try {
									todaysList.deleteNLastPositions(data);
					
									System.out.println("Positions Deleted");
									System.out.println("");
									showMainMenu();
								
								}catch (Exception e) {
									System.out.println(e.getMessage());
									System.out.println("");
									showMainMenu();
								}
						
						}else if(option.equals("2")){
								showMainMenu();
						}
						
					
		} 

	
		//Shows to the used the details of the applicant that has just been added.
		//Only used with the method to add applicants. In order to check details, a person can
		//be found by ID or checked by printing the full list.
		public void showingInfo (){
			
				System.out.println("");
				System.out.println("Date: " + date.toString());
				System.out.println("Candidate ID: " + thisOne.getId());
				System.out.println("Name: " + thisOne.getFirst_name());
				System.out.println("Last Name: " + thisOne.getLast_name());
				System.out.println("Last Name: " + thisOne.getPassport());
				System.out.println("Number of Applicants Currently on Queue: " + todaysList.size);
	
			}

		//Closes the program.
		public void exit() {
				
			System.out.println("Do you really want to exit the program? (Press 1 for EXIT / Press 2 For MAIN MENU)?");
			option = methodReadOption(2);
			 	 
					if (option.equals("1")){
						
				 		 System.out.println("��.�.��Thank you!��.�.��");
				 		 System.exit(0);
				 	
					}else if(option.equals("2")){
				 		showMainMenu();
				 	
					}
		}

		//Reads input from user depending on numbers allowed by the selection.
		//It validates inputs when numerical options must be entered by user. The integer passed
		//in its signature represents the highest option number available. It also does not allow numbers < 1.
		public String methodReadOption(int topNumber){
			
			boolean rightOption = false;

			int num = 0;
				 
				do {
					try{
					    option = br.readLine();
					    
					    num = Integer.parseInt(option);

					    	if (num >0 && num <= topNumber ){
					    		rightOption = true;
					    	}
						}catch (IOException e)  {System.out.println(e);
						
						}catch (NumberFormatException e) {System.out.println("Please Introduce Only Valid Numbers");}
				
				} while (rightOption!= true);
			
				  return option;

			}
			
		//Reads string inputs from user to store object Individual attributes.
		public String methodReadInput() {
				
					try {
						option = br.readLine();

						} catch (Exception e) {}

				return option;
		}

		//This method requests full information from users in order to create a new object individual
		//and then add it to the queue in the right place, depending on priority.
		public void addingDetails(){

			boolean addOneMore = false;

				do{
					
						System.out.println("");
						System.out.println("Please Enter Applicant's Name:");
						first_name = methodReadInput();
						System.out.println("");
			
						System.out.println("Please Enter Applicant's Last Name:");
						last_name = methodReadInput();
						System.out.println("");
			
						System.out.println("Registration Date Is " + " " + date.toString());
					    System.out.println("");
			
					    System.out.println("Please Enter Applicant's Passport Number:");
						passport = methodReadInput();
						System.out.println("");
			
						System.out.println("Please Select Applicant's Priority:");
						System.out.println("1 - First Priority - Emergency doc is mandatory ");
						System.out.println("2 - Applicant with kids under 3 years old");
						System.out.println("3 - Normal applicant");
						priority = Integer.parseInt(methodReadOption(3));
		
					thisOne = new Individual(first_name, last_name, passport, priority);
					todaysList.addArrival(thisOne);
					
					showingInfo ();
		
						System.out.println("");
						System.out.println("Do you want to add another candidate?");
						System.out.println("1. Yes");
						System.out.println("2. No");
		
					option = methodReadOption(2);
		
						if(option.equals("1")){
							
							addOneMore = true;
		
						}else if (option.equals("2")){
							
						addOneMore = false;
						showMainMenu();
		
						}
		
				}while (addOneMore);
				
		}

		//Retrieves an applicant from the queue and updates main details (Name, Last Name and Passport).
		//It keeps place on queue, ID and priority.
		//Catches exceptions.
		public void updateApplicant (){

			if (todaysList.isEmpty()){
				
				System.out.println("There Are No Applicants Currently on Queue");
				System.out.println("");
				showMainMenu();
				
			}else 
				System.out.println("Please enter Id of Applicant:");
				int thisId = 0;
				thisId = Integer.parseInt(methodReadInput());
			
			try {
				
				todaysList.findIndividualById(thisId);
				
			  		 int thisPriority = thisOne.getPriority();

			  		 		System.out.println("");
			  		 		System.out.println("Please Enter New Details:");
			  		 		System.out.println("");
			  		 		
							System.out.println("Name:");
							first_name = methodReadInput();
							thisOne.setFirst_name(first_name);
							
							System.out.println("Last Name");
							last_name = methodReadInput();
							thisOne.setLast_name(last_name);
							
							System.out.println("Passport Number");
							passport = methodReadInput();
							thisOne.setPassport(passport);
							
							thisOne.setPriority(thisPriority);
						
							showingInfo ();
			 	
			 	showMainMenu();

		
			}catch (Exception e) {
				
				System.out.println(e.getMessage());
				System.out.println("");
				showMainMenu();
			}			
		}	
			
	//Starts the program.
	public static void main(String[] args) {

		new MethodsMenu();

	}



}
