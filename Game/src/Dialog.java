import java.util.Arrays;

// Contains story dialog between sections of the game
public class Dialog {
	
	// Declarations
	private boolean completed;
	
	// Constructor
	public Dialog(boolean completed) {
		this.completed = completed;
	}
	
	
	// completed Setter method
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	// completed Getter method
	public boolean getCompleted() {
		return completed;
	}
	
	
	// Objects that keep track of what dialog has been already displayed.
	static Dialog campsite01 = new Dialog(false);
	static Dialog bridge01 = new Dialog(false);
	static Dialog bridge02 = new Dialog(false);
	static Dialog cliff01 = new Dialog(false);
	static Dialog watchtower01 = new Dialog(false);
	static Dialog watchtower02 = new Dialog(false);
	static Dialog grasslands01 = new Dialog(false);
	static Dialog farm01 = new Dialog(false);
	static Dialog crossroads01 = new Dialog(false);
	static Dialog village01 = new Dialog(false);
	static Dialog castlegate01 = new Dialog(false);
	static Dialog crypt01 = new Dialog(false);
	
	
	
	
	// Declarations/Initializations
	public static String userInput = Gui.getUserInput();

	
	// Sleeps a thread. Used to delay text being displayed.
	static void sleep(int Duration) {
		try {
			Thread.sleep(Duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	// Sleeps a thread. Used to delay text being displayed. Provides less time to dialog already viewed.
	static void sleepDialog(int Duration, boolean blockade) {
		
		try {
			if (blockade == true) {
				Thread.sleep(250);
			} else {
				Thread.sleep(Duration);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	static void Opening() {
		
		Gui.text("Welcome to Attacked!","","b");
		sleep(2000);
		
		Gui.text("To answer a question, type your response in the text box below, and press enter!","","");
		sleep(2000);
		
		Gui.text("Some responses WILL impact your future actions and or open up additional dialog!","","");
		sleep(2000);
		
		Gui.text("Enter ","","nb"); Gui.text("START","dg","nb"); Gui.text(" to continue!","","b");
		
		
		while(true) {
			userInput = Gui.substituteScanner();
			userInput = userInput.toLowerCase();
			
			if (userInput.equals("start")) {
				break;
			} else {
				Gui.text("Please try again.","","b");	
			}
		}
	}

	
	// Initial story, NPC, health and Item introduction.
	static void campsite01() {
		if (!campsite01.getCompleted()) {
			
			//Conditions to be met to advance in the dialog
			boolean blockade01 = false;
			boolean blockade02 = false;
			boolean blockade03 = false;
			
			sleep(2000);
			Gui.text("#@^*&J#$!H!","","b");
			sleep(2000);
			Gui.text("HEY#%Z*T@#$%(!","","b");
			sleep(2000);
			Gui.text("*The ringing in your ears starts to fade away* ","grey","i");
			sleep(2000);
			Gui.text("--Hey! ","","");	 	
			sleep(2000);
			Gui.text("*You start to make out a faint shadow over you* ","grey","i");
			sleep(2000);
			Gui.text("Villager: ","b","nb"); Gui.text("Are you alright? ","","");	
			sleep(2000);
			Gui.text("───────────────────────────────","","");
			Gui.text("Select an answer: ","","n"); Gui.text("(Y/N)","","b"); 
			

			while(true) {
				
				userInput = Gui.substituteScanner();
				userInput = userInput.toLowerCase();
				
				if (userInput.equals("n") || userInput.equals("no")) {
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("Let me help you stop the bleeding. ","","");	
					Gui.text("Player health: ","","nb"); Gui.text(Integer.toString(Combat.pl.getPlayerHealth()),Combat.healthBar(Combat.pl.getPlayerHealth()),"b");
					break;
					
				} else if (userInput.equals("y") || userInput.equals("yes")) {
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("Let me help you up then. ","","");
					Combat.pl.setPlayerHealth(90);
					Gui.text("Player health: ","","nb"); Gui.text(Integer.toString(Combat.pl.getPlayerHealth()),Combat.healthBar(Combat.pl.getPlayerHealth()),"b");
					break;
					
				} else {
					Gui.text("Invalid option. Please try again.","","b");	
					
				}
			}
			
			// Pause to let user read dialog
			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	
	
			Gui.text("*What is going on?* ","grey","i");
			Gui.text("","","");
			sleep(2000);
			
			while(true) {
				
				Gui.text("Ask the villager questions: ","","b");
				
					// Make options already chosen a grey colour.
					if (blockade01 == false) {
						Gui.text("1. Where am I?","","");
					} else if (blockade01 == true) {
						Gui.text("1. Where am I?","grey","");
					}
				
					// Make options already chosen a grey colour.
					if (blockade02 == false) {
						Gui.text("2. What happened?","","");
					} else if (blockade02 == true) {
						Gui.text("2. What happened?","grey","");
					}
				
				// Open up additional options once initial ones have been completed.
				if (blockade01 == true && blockade02 == true) {
					
					// Make options already chosen a grey colour.
					if (blockade03 == false) {
						Gui.text("3. Where did they go?","","");
					} else if (blockade03 == true) {
						Gui.text("3. Where did they go?","grey","");
					}
				}
				
				// Open up additional options once initial ones have been completed.
				if (blockade01 == true && blockade02 == true && blockade03 == true) {
					Gui.text("4. Exit","","");
					
				}
				
				Gui.text("───────────────────────────────","","");
				
				
				if (blockade01 ==  false || blockade02 ==  false) {
					Gui.text("Select an option: ","","n"); Gui.text("(1/2)","","b"); 
				} else if (blockade01 == true && blockade02 == true && blockade03 == false) {
					Gui.text("Select an option: ","","n"); Gui.text("(1/2/3)","","b"); 
				} else if (blockade01 == true && blockade02 == true && blockade03 == true) {
					Gui.text("Select an option: ","","n"); Gui.text("(1/2/3/4)","","b"); 
				}
				
				userInput = Gui.substituteScanner();
				userInput = userInput.toLowerCase();
				
				if (userInput.equals("1")) {
	
					Gui.text("","","");	
					Gui.text("You: ","","nb"); Gui.text("Where am I?","","");
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("Your currently at the ","","n");  Gui.text("CAMPSITE. ","","B");
					sleepDialog(2000,blockade01);
					Gui.text("Villager: ","b","nb"); Gui.text("We brought you here from your horse cart.","","");
						
						// Loop back to ask another question
						blockade01 = true;
						Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
						userInput = Gui.substituteScanner();
						
					
				} else if (userInput.equals("2")) {
	
					Gui.text("","","");	
					Gui.text("You: ","","nb"); Gui.text("What happened?","","");
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("You were attacked while traveling.","","");
					sleepDialog(2000,blockade02);
					Gui.text("Villager: ","b","nb"); Gui.text("We found you knocked out in your empty cart a few miles from here.","","");
					sleepDialog(4000,blockade02);
					Gui.text("Villager: ","b","nb"); Gui.text("A couple of mercenaries passed us carrying bags full of goods before we got to you.","","");
					sleepDialog(4000,blockade02);
					Gui.text("*if this is what I think happened..* ","grey","i");
					sleepDialog(2000,blockade02);
					Gui.text("Villager: ","b","nb"); Gui.text("I think they attacked you and stole all your goods you were transporting.","","");
					sleepDialog(4000,blockade02);
					Gui.text("*ARGGH! How dare they! They wont get away with this!* ","grey","i");
					sleepDialog(3000,blockade02);
					Gui.text("You: ","","nb"); Gui.text("They wont get away with this!","","");
					sleepDialog(3000,blockade02);
						
						// Pause to let user read dialog
						Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
						userInput = Gui.substituteScanner();
						Gui.text("","","");	
						
					Gui.text("Villager: ","b","nb"); Gui.text("Be careful traveler, they had royal embroidery on their chain armour.","","");
					sleepDialog(3000,blockade02);
					Gui.text("*This cant be true! Guards from the Royal Castle stealing from a traveling merchant?* ","grey","i");
					sleepDialog(4000,blockade02);
					Gui.text("You: ","","nb"); Gui.text("Guards from the Royal Castle, why would they do such a thing?","","");
					
						// Pause to let user read dialog
						Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
						userInput = Gui.substituteScanner();
						
	
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("I dont know all the answers my friend, but I dont think they were Guards from the Royal Castle.","","");
					sleepDialog(4000,blockade02);
					Gui.text("Villager: ","b","nb"); Gui.text("It looked like they stole the armour they were wearing too.","","");
					sleepDialog(3000,blockade02);
					Gui.text("Villager: ","b","nb"); Gui.text("I'd be very careful if you wish to seek vengeance, its unheard of Royal Castle Guards being defeated these days.","","");
					sleepDialog(4500,blockade02);;	
					Gui.text("*Why cant I remember them attacking me.. * ","grey","i");
					sleepDialog(2500,blockade02);
					
						// Loop back to ask another question
						blockade02 = true;
						Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
						userInput = Gui.substituteScanner();
						Gui.text("","","");	
						
				} else if (userInput.equals("3") && (blockade01 == true && blockade02 == true)) {
					
					Gui.text("","","");	
					Gui.text("You: ","","nb"); Gui.text("Where did they go?","","");
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("I dont know for certain, but a few of the other villagers saw them head ","","n"); Gui.text("WEST.","","b");
					sleepDialog(3000,blockade03);
					Gui.text("Villager: ","b","nb"); Gui.text("That of course, means they passed the ","","n"); Gui.text("BRIDGE","","nb"); 
					Gui.text(", which is guarded by one of most skilled swordsman, the ","","n"); Gui.text("Tank Warlord.","","b"); 
					sleepDialog(4000,blockade03);
					
					// Pause to let user read dialog
					Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
					userInput = Gui.substituteScanner();
					Gui.text("","","");	
					
					Gui.text("* I've heard stories about him before, not someone to bat an eye too.* ","grey","i");
					sleepDialog(3500,blockade03);
					Gui.text("You: ","","nb"); Gui.text("Did they leave any of my things behind?","","");
					sleepDialog(3000,blockade03);
					Gui.text("Villager: ","b","nb"); Gui.text("All we recovered from your cart was this ","","n"); Gui.text("Rusty Short Sword.","pink","b");
					sleepDialog(3000,blockade03);
					Gui.text("","","");	
					Gui.text("You obtained a: ","c","nb"); Gui.text("Rusty Short Sword!","pink","b");
					Gui.text("","","");	
					sleepDialog(4000,blockade03);
					
					// Initialize only once
					if (blockade03 == false) {
						// Initialize the first item to player's inventory.
						Inventory.inventoryInitialisation();
					}
					Inventory.displayInventory();
					
					// Pause to let user read dialog
					Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
					userInput = Gui.substituteScanner();
					Gui.text("","","");	
					
					Gui.text("Villager: ","b","nb"); Gui.text("It wont be enough to get defeat ","","n"); Gui.text("Tank Warlord","","nb"); 
					Gui.text(", you'd need at least a ","","n");  Gui.text("Shield","","nb");
					Gui.text(", but it should help you start somewhere. ","","");
					sleepDialog(4000,blockade03);
					
					// Loop back to ask another question
					blockade03 = true;
					Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
					userInput = Gui.substituteScanner();
					Gui.text("","","");	
				
				} else if (userInput.equals("4") && (blockade01 == true && blockade02 == true && blockade03 == true)) {	
					Gui.text("","","");	
					Gui.text("Villager: ","b","nb"); Gui.text("I'll be at the ","","n"); 
					Gui.text("Village","","nb"); 
					Gui.text(" if you wish to speak with me again.","",""); 
					sleep(2000);
					Gui.text("Villager: ","b","nb"); Gui.text("Safe travels.","",""); 
					sleep(2000);
					Gui.text("You: ","","nb"); Gui.text("Good Bye!","","");
					sleep(2000);
					Gui.text("* The villager heads off ","grey","ni"); Gui.text(" NORTH","grey","nb"); Gui.text(" towards the ","grey","ni"); Gui.text("BUSHLANDS.","grey","b");
					Gui.text("","","");	
					sleep(3000);
					
					Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
					userInput = Gui.substituteScanner();
					Gui.text("","","");	
					
					//End the loop, go back to locations.
					break;
				
				// If incorrect input was entered
				} else {
					Gui.text("","","");	
					Gui.text("Invalid option. Please try again.","","b");	
					Gui.text("","","");	
				}
			
				// Change Completed to 'true', revoking dialog from prompting again in the future.
				campsite01.setCompleted(true);
			}
		}
	} // static void campsite01()
	
	
	// Bridge warning to turn back if shield item is not obtained
	static void bridge01() {
		if (!bridge01.getCompleted()) {
			
			// If user has the shield, do not display warning.
			if(!Arrays.asList(Inventory.items).contains("Cobalt Shield")){
			Gui.text("*I dont think this is a good idea, I wont be able to defeat Tank Warlord with my current gear* ","grey","i");
			}
			
			// If user has the shield, set as completed.
			if(Arrays.asList(Inventory.items).contains("Cobalt Shield")) {
				Gui.text("*With this new gear, I'm certain I can win this fight now! * ","grey","i");
				//bridge01.setCompleted(false);
			}
		}
	}
	
	
	// Obtain ROPE section
	static void cliff01() {
		if (!cliff01.getCompleted()) {
			
			// If user has rope, do not display dialog
			if(!Arrays.asList(Inventory.items).contains("Bundle of Rope")) {
				Gui.text("You approach a ","","n"); Gui.text(Locations.location.toUpperCase(),"black","b");
			Gui.text("*The cliffside spans far in both directions, and is very steep* ","grey","i");
			sleep(3000);
			Gui.text("*There must be something I can obtain to climb up here* ","grey","i");
			
			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	
			
			//Go back to Bush Lands
			Locations.location = Locations.getPastLocation();
			
			}
			
			// If user has the shield, set as completed.
			if(Arrays.asList(Inventory.items).contains("Bundle of Rope")){
				// Change Completed to 'true', revoking dialog from prompting again in the future.
				cliff01.setCompleted(true);
			}
		}
	}
	

	// Initial Watch Tower
	static void watchtower01() {
		if (!watchtower01.getCompleted()) {
			
			if (!Combat.archer.getCreatureIsDead() || !Combat.archer.getCombatStatus().equals("won")) {
				Gui.text("------PHWWWWWHHHTT!!","purple","b");
				sleep(1000);
				Gui.text("An arrow narrowly misses and whistles past you","","");
				sleep(3000);
				Gui.text("* You see a dark mysterious figure lurking leap out from the Tower. ","grey","i");
				sleep(3000);
				Gui.text("* You take cover, and approach the tower cautiously. ","grey","i");
				
				Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
				userInput = Gui.substituteScanner();
				Gui.text("","","");	
				
			}
			
			if (Combat.archer.getCreatureIsDead() || Combat.archer.getCombatStatus().equals("won")) {
				// Change Completed to 'true', revoking dialog from prompting again in the future.
				watchtower01.setCompleted(true);
			}
		}
	}
	
	
	// Watch Tower additional combat dialog
	static void watchtower02() {
		if (!watchtower02.getCompleted()) {
			
			if (!Combat.archer.getCreatureIsDead() || !Combat.archer.getCombatStatus().equals("won")) {
				
				Gui.text("","","");
				Gui.text("------PHWWWWWHHHTT!!","purple","b");
				Gui.text("","","");
				sleep(1000);
				Gui.text("The archer misses another arrow.","","");
				sleep(2000);
				Gui.text("You sprint to the base of the tower and manage to climb up it","","");
				sleep(3000);
				Gui.text("The archer is shocked, drops his bow and pulls out a small dagger out of his belt","","");
				sleep(3000);
				Gui.text("* The tables have turned * ","grey","i");

				Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
				userInput = Gui.substituteScanner();
				Gui.text("","","");	
				
			}
			
			if (Combat.archer.getCreatureIsDead() || Combat.archer.getCombatStatus().equals("won")) {
				// Change Completed to 'true', revoking dialog from prompting again in the future.
				watchtower02.setCompleted(true);
			}
		}
	}

	
	// Farm Rope
	static void farm01() {
		if (!farm01.getCompleted()) {
			
			Gui.text("An old barn looms in the distance. ","","");
			sleep(2000);
			Gui.text("* It seems abandoned, there must be something here to help me over that cliff * ","grey","i");
			sleep(3000);
			Gui.text("You spot some rope bundled up around a fence post. ","","");
			sleep(3000);
			Gui.text("* It could just be long enough to scale the cliff! * ","grey","i");
			sleep(3000);
			Gui.text(" ","grey","i");
			
			Gui.text("You obtained a: ","c","nb"); Gui.text("Bundle of Rope","pink","b");
			Gui.text("","","");	
			sleep(3000);

			Inventory.addItem("Bundle of Rope");
			Inventory.displayInventory();
			Gui.text("","","");	
			
			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	
			
			// Stops this dialog from occurring in the future.
			farm01.setCompleted(true);
			
		}
	}
	
	
	// Watch Tower additional combat dialog
	static void mountainCrossroads01() {
		if (!crossroads01.getCompleted()) {
			
			Gui.text("The Cross Roads are thin and the path is barly noticeable.","","");
			sleep(2000);
			Gui.text("You proceed to make it all the way though.","","");

			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	
			
			crossroads01.setCompleted(true);
		}
	}
	
	// Initial story, NPC, health and Item introduction.
	static void village01() {
		if (!village01.getCompleted()) {
			
			Gui.text("You are currently in ","","n"); Gui.text(Locations.location.toUpperCase(),"black","b");
			sleep(2000);
			Gui.text("Villager: ","b","nb"); Gui.text("Welcome to our village Traveler! ","","");	
			sleep(2000);
			Gui.text("Villager: ","b","nb"); Gui.text("We meet again. ","","");	
			sleep(2000);
			Gui.text("","","");	
			
			Gui.text("You: ","","nb"); Gui.text("Any more information on the shield you mensoned to me previously?","","");
			sleep(3000);
			Gui.text("Villager: ","b","nb"); Gui.text("Ah of course, you can probably find one in the Crypts.","","");	
			sleep(2000);
			Gui.text("Villager: ","b","nb"); Gui.text("However, there is a gate blocking access to it. ","","");	
			sleep(2000);
			Gui.text("Villager: ","b","nb"); Gui.text("The crypt hasn't been opened in decades. ","","");	
			sleep(2000);
			
			// Pause to let user read dialog
			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	
			
			Gui.text("Villager: ","b","nb"); Gui.text("However, some of the village kids have been trying to open it recently. ","","");	
			sleep(4000);
			Gui.text("Villager: ","b","nb"); Gui.text("They found it \"fun\" scrolling though hundreds of codes ","","");	
			sleep(3000);
			Gui.text("Villager: ","b","nb"); Gui.text("I think I heard one of them saying it was ","","n");	Gui.text("4321.","b","b");	
			sleep(4000);
			Gui.text("You: ","","nb"); Gui.text("Thanks! I'll give it a try.","","");
			sleep(3000);
			Gui.text("Villager: ","b","nb"); Gui.text("Safe travels.","","");
			sleep(1000);
			
			// Pause to let user read dialog
			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	
			castlegate01.setCompleted(true);
		}
	}

	
	// Initial Watch Tower
	static void castleGate01() {
		if (!castlegate01.getCompleted()) {
			
			if (!castlegate01.getCompleted()) {
				Gui.text("There seems to be a combination lock on the gate","","");
				sleep(3000);
				Gui.text("\"ENTER CODE\" the lock says, prompting a 4 digit keyword.","","");
				sleep(3000);
				Gui.text("* Might as well give it a shot.  *","grey","i");
				sleep(2000);
				Gui.text("Enter the keyword to continue: ","","");
				
				userInput = Gui.substituteScanner();
				userInput = userInput.toLowerCase();
				
				if (userInput.equals("4321")) {
					Gui.text("Clank! ","magenta","b");
					sleep(2000);
					Gui.text("The gate swiftly opened up. ","","");
					
					Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
					userInput = Gui.substituteScanner();
					Gui.text("","","");	
					castlegate01.setCompleted(true);

				} else {
					Gui.text("The lock didn't budge.","","b");	
					sleep(2000);
					Gui.text("* Maybe the villager knows a way to get in. *","gray","");
					sleep(2000);
					Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
					userInput = Gui.substituteScanner();
					Gui.text("","","");	
					Locations.location = Locations.getPastLocation();
					castlegate01.setCompleted(false);
				}

			}
		}
	}
	
	
	// Crypt
	static void crypt01() {
		if (!crypt01.getCompleted()) {
			
			Gui.text("You enter the crypts and hear eerie sounds come out of the cavern. ","","");
			sleep(3000);
			Gui.text("You progress further and further into the crypts. ","","");
			sleep(3000);
			Gui.text("Something shines on the corner of your eye from a sky opening's light rays. ","","");
			sleep(3000);
			Gui.text("* It looks like the Shield! *","gray","itallic");
			sleep(3000);
			Gui.text("* Theres also a Steel Longsword! *","gray","itallic");
			sleep(3000);
			Gui.text("","","");
			Gui.text("You obtained a: ","c","nb"); Gui.text("Cobalt Shield","pink","b");
			Gui.text("You obtained a: ","c","nb"); Gui.text("Steel Longsword","pink","b");
			sleep(3000);
			Gui.text("","","");
			Gui.text("* I wont be needing this rusty sword anymore. *","gray","itallic");
			sleep(3000);
			Inventory.addItem("Cobalt Shield");
			Inventory.addItem("Steel Longsword");
			sleep(3000);
			Gui.text("","","");	
			Inventory.items[0] = null;
			Inventory.displayInventory();
			Gui.text("","","");	
			
			Gui.text("Press ","","n"); Gui.text("ENTER ","","nb");  Gui.text("to continue:","","");
			userInput = Gui.substituteScanner();
			Gui.text("","","");	

			
		}
	}
	

} // Class Dialog






















