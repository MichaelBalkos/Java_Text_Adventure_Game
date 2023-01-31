import java.util.Arrays;

public class Locations {
	
	// Declarations/Initializations.
	static String location = "Campsite";
	private static String pastLocation;
	// Declaration for an array containing adjacent locations of a location.
	static String adjLocations[];
	
	// Initialization used for 'substituteScanner()'.
	public static String userInput = Gui.getUserInput();

	// Used to store the previous location a user has been too. 
	// Storing past locations allows combat to be object oriented.
	public static void setPastLocation(String pastLocation) {
		Locations.pastLocation = pastLocation;
	}
	
	// Used to obtain the previous location a user has been too.
	public static String getPastLocation() {
		return pastLocation;
	}
	
	/* Location map of the game built into a 2 dimensional array that is used to
	 * automatically adjusts dialog and user options based off adjacent locations.
	 * The game resides in a 5x5 game board, inside the 7x7 array. 
	 * The extra sized array acts as a border when calculating adjacent locations.
	 */ 
	static String[][] gameMap = new String[][]{
    	
		/* 0, 			 1, 				2,	  		 		 3, 				4, 						5,				6*/		
	/*0*/{ "",			 "",				"",					"",					"",						"",				""},
	/*1*/{ "",	 		 "",  			 "Crypt",  				"",					"",						"",				""},
	/*2*/{ "",		 	 "",		  "Crypt Gate",			"Forest",	     "Mountain Crossroads",	  "Mountain Village",	""},
	/*3*/{ "",			 "",				"",			     "Cliff",				"",						"",				""},
	/*4*/{ "",			 "",				"",			"Foggy Bushland",	"Rangers Watchtower",  		"Grasslands",		""},
	/*5*/{ "",	   "Mercinaries Den",		"Bridge",			"Campsite",				"",					  "Farm",			""},
	/*6*/{ "",			 "",				"",					"",					"",						"",				""}
	
	};
	

	/* Returns the surrounding adjacent locations based off the 2 dimensional String Array 'gameMap'.
	 * Used to automatically adjust and adapt dialog and user options based of the 'gameMap'.
	 */
	public static String[] map(String location) {
		
		// Initialize the variables that store the current location's column and row position.
		int locationRow = 0;
		int locationColumn = 0;
		
		// Increment array's rows with for loop.
        for (int row = 0; row < gameMap.length; row++) {
        	// Increment array's columns with for loop.
            for (int column = 0; column < gameMap[row].length; column++) {
            	
            	// When the current location is found, store its position.
                if (gameMap[row][column] == location) {
                	locationRow = row;
                	locationColumn = column;

                }
            }
        }
		
	    // Create an array to return multiple values.
		String adjacent[] = new String[4];
		
		// Increment the rows and columns of the current location to obtain adjacent locations.
		adjacent[0] = gameMap[locationRow-1][locationColumn]; // North
		adjacent[1] = gameMap[locationRow+1][locationColumn]; // South
		adjacent[2] = gameMap[locationRow][locationColumn+1]; // East
		adjacent[3] = gameMap[locationRow][locationColumn-1]; // West
		
		// Return adjacent locations
        return adjacent;
	}
	
	// Creates the compass automatically based of if adjacent locations exist. E.g (N/S/E/W).
	public static String compassDirection(String[] adjLocations) {
		String compass = "(";
		int length = 0;
		
		// Concatenate Characters if the adjacent location exists.
		if (adjLocations[0] != "") {
			compass = compass + "N";
		}
		
		// Concatenate Characters if the adjacent location exists.
		if (adjLocations[1] != "") {
			length = compass.length();
			if (length <= 1) {
				compass = compass + "S";
			} else {
				compass = compass + "/S";
			}
		}
		
		// Concatenate Characters if the adjacent location exists.
		if (adjLocations[2] != "") {
			length = compass.length();
			if (length <= 1) {
				compass = compass + "E";
			} else {
				compass = compass + "/E";
			}
		}
		
		// Concatenate Characters if the adjacent location exists.
		if (adjLocations[3] != "") {
			length = compass.length();
			if (length <= 1) {
				compass = compass + "W";
			} else {
				compass = compass + "/W";
			}
		}
		
		compass = compass + ")";
		
		return compass;
	}
	
	
	// When a user enters input to a question, this method determines what location or event to do next.
	public static void selectOption(String userInput) {
		
		// Change the location determined by user's input.
		switch(userInput.toLowerCase()) {
		case "n":
		case "north":	
		case "up":
			// If the location exists, change it, else display an error message.
			if (adjLocations[0] != "") {
				setPastLocation(location);
				location = adjLocations[0];
			} else {
				Gui.text("\nInvalid direction. Please try again.","","b");
			}	
			break;
		
		case "s":
		case "south":
		case "down":
			// If the location exists, change it, else display an error message.
			if (adjLocations[1] != "") {
				setPastLocation(location);
				location = adjLocations[1];
			} else {
				Gui.text("\nInvalid direction. Please try again.","","b");
			}
			break;
			
		case "e":
		case "east":
		case "right":
			// If the location exists, change it, else display an error message.
			if (adjLocations[2] != "") {
				setPastLocation(location);
				location = adjLocations[2];
			} else {
				Gui.text("\nInvalid direction. Please try again.","","b");
			}
			break;
			
		case "w":
		case "west":
		case "left":
			// If the location exists, change it, else display an error message.
			if (adjLocations[3] != "") {
				setPastLocation(location);
				location = adjLocations[3];
			} else {
				Gui.text("\nInvalid direction. Please try again.","","b");
			}
			break;
		
		default:
			// Display an error message if the user entered anything apart from the predefined switch cases.
			Gui.text("\nInvalid direction. Please try again.","","b");
			break;
		
		}
	}
	

	// Start of game location selection.
	static void gameLocations() {
		
		// Changes the current location method being executed if the location variable changes correspondingly.
		while(true)
		{
			if(location.equalsIgnoreCase("Campsite")) {
				campSite();
			}
			
			else if(location.equalsIgnoreCase("Bridge")) {
				bridge();
			}
			
			else if(location.equalsIgnoreCase("Foggy Bushland")) {
				foggyBushland();
			}
			
			else if(location.equalsIgnoreCase("Rangers Watchtower")) {
				rangersWatchtower();
			}
			
			else if(location.equalsIgnoreCase("Grasslands")) {
				grassLands();
			}
			
			else if(location.equalsIgnoreCase("Farm")) {
				farm();
			}

			else if(location.equalsIgnoreCase("Dragon's Den")) {
				dragonsDen();
			}
			
			else if(location.equalsIgnoreCase("Forest")) {
				forest();
			}
			
			else if(location.equalsIgnoreCase("Cliff")) {
				cliff();
			}
			
			else if(location.equalsIgnoreCase("Mountain Crossroads")) {
				mountainCrossroads();
			}
			
			else if(location.equalsIgnoreCase("Mountain Village")) {
				mountainVillage();
			}
			
			else if(location.equalsIgnoreCase("Crypt Gate")) {
				cryptGate();
			}
			
			else if(location.equalsIgnoreCase("Crypt")) {
				crypt();
			}
			
			else if(location.equalsIgnoreCase("Mercinaries Den")) {
				mercinariesDen();
			}

		}
	}
	
	static void campSite() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
		
		// Initial story, NPC, health and Item introduction.
		Dialog.campsite01();
		
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}
	
	
	static void rangersWatchtower() {
		
		Dialog.watchtower01();
		
		// Initiate option to fight a predetermined enemy. 
		// If enemy is not dead, but player died or chose to run, prompt to engage in combat again.
		Combat.combat(getPastLocation(),Combat.archer);
		
		// If user died or ran, skip this locations dialog, reset health of both player and enemy, and go back to previous location. 
		if (Combat.archer.getCombatStatus().equals("run") || Combat.archer.getCombatStatus().equals("lost")) {
			Combat.pl.setPlayerHealth(100);
			Combat.archer.setCreatureHealth(100);
			
		// If the player defeated the enemy, reset player health and allow access to this locations content.
		} else if (Combat.archer.getCreatureIsDead() || Combat.archer.getCombatStatus().equals("won")) {
			
			//Obtain adjacent Locations.
			adjLocations = map(location);
			
			Combat.pl.setPlayerHealth(100);
			
			Gui.text("","","");
			
			Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
			
			Gui.text("There is a decomposed " + Combat.archer.getCreatureRace(),"","");

			
			if (adjLocations[0] != "") {
				Gui.text("To the North is " + adjLocations[0],"","");
			}
			
			if (adjLocations[1] != "") {
				Gui.text("To the South is " + adjLocations[1],"","");
			}
			
			if (adjLocations[2] != "") {
				Gui.text("To the East is " + adjLocations[2],"","");
			}
			
			if (adjLocations[3] != "") {
				Gui.text("To the West is " + adjLocations[3],"","");
			}
			
			Gui.text("───────────────────────────────","","");
			Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
			Gui.text("","","");
			
			//Obtains a user's input from the graphical interface.
			userInput = Gui.substituteScanner();
			
			// Determines what location or event to occur based on user's input.
			selectOption(userInput);
		}
	}
	
	
	static void bridge() {
		
		if(Arrays.asList(Inventory.items).contains("Cobalt Shield")) {
			Combat.pl.setPlayerHealth(300);
			Combat.pl.setPlayerDamage(70);
		}
		
		
		Gui.text("","","");
		Dialog.bridge01();
		Gui.text("","","");
		
		// Initiate option to fight a predetermined enemy. 
		// If enemy is not dead, but player died or chose to run, prompt to engage in combat again.
		Combat.combat(getPastLocation(),Combat.tankWarlord);
		
		// If user died or ran, skip this locations dialog, reset health of both player and enemy, and go back to previous location. 
		if (Combat.tankWarlord.getCombatStatus().equals("run") || Combat.tankWarlord.getCombatStatus().equals("lost")) {
			Combat.pl.setPlayerHealth(100);
			Combat.tankWarlord.setCreatureHealth(100);
			
		// If the player defeated the enemy, reset player health and allow access to this locations content.
		} else if (Combat.tankWarlord.getCreatureIsDead() || Combat.tankWarlord.getCombatStatus().equals("won")) {
			
			//Obtain adjacent Locations.
			adjLocations = map(location);
			
			Combat.pl.setPlayerHealth(100);
			
			Gui.text("","","");
			
			Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
			
			Gui.text("There is a decomposed " + Combat.tankWarlord.getCreatureRace(),"","");

			
			if (adjLocations[0] != "") {
				Gui.text("To the North is " + adjLocations[0],"","");
			}
			
			if (adjLocations[1] != "") {
				Gui.text("To the South is " + adjLocations[1],"","");
			}
			
			if (adjLocations[2] != "") {
				Gui.text("To the East is " + adjLocations[2],"","");
			}
			
			if (adjLocations[3] != "") {
				Gui.text("To the West is " + adjLocations[3],"","");
			}
			
			Gui.text("───────────────────────────────","","");
			Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
			Gui.text("","","");
			
			//Obtains a user's input from the graphical interface.
			userInput = Gui.substituteScanner();
			
			// Determines what location or event to occur based on user's input.
			selectOption(userInput);
		}
	}
	
	
	static void cliff() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		Dialog.cliff01();
		
		// If player has the ROPE item obtained, allow them to access the cliff
		if(Arrays.asList(Inventory.items).contains("Bundle of Rope")){
			
			Gui.text("You are currently at a ","","n"); Gui.text(location.toUpperCase(),"black","b");
			
			if (adjLocations[0] != "") {
				Gui.text("To the North is " + adjLocations[0],"","");
			}
			
			if (adjLocations[1] != "") {
				Gui.text("To the South is " + adjLocations[1],"","");
			}
			
			if (adjLocations[2] != "") {
				Gui.text("To the East is " + adjLocations[2],"","");
			}
			
			if (adjLocations[3] != "") {
				Gui.text("To the West is " + adjLocations[3],"","");
			}
			
			Gui.text("───────────────────────────────","","");
			Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
			Gui.text("","","");
			
			//Obtains a user's input from the graphical interface.
			userInput = Gui.substituteScanner();
			
			// Determines what location or event to occur based on user's input.
			selectOption(userInput);
			
		}
	}
	
	
	static void foggyBushland() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}

	
	static void grassLands() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}
	
	
	static void farm() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		Dialog.farm01();
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}
	
	
	static void dragonsDen() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}

	
	static void forest() {
		
		
		
		// Initiate option to fight a predetermined enemy. 
		// If enemy is not dead, but player died or chose to run, prompt to engage in combat again.
		Combat.combat(getPastLocation(),Combat.burglar);
		
		// If user died or ran, skip this locations dialog, reset health of both player and enemy, and go back to previous location. 
		if (Combat.burglar.getCombatStatus().equals("run") || Combat.burglar.getCombatStatus().equals("lost")) {
			Combat.pl.setPlayerHealth(100);
			Combat.burglar.setCreatureHealth(100);
			
		// If the player defeated the enemy, reset player health and allow access to this locations content.
		} else if (Combat.burglar.getCreatureIsDead() || Combat.burglar.getCombatStatus().equals("won")) {
			
			//Obtain adjacent Locations.
			adjLocations = map(location);
			
			Combat.pl.setPlayerHealth(100);
			
			Gui.text("","","");
			
			Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
			
			Gui.text("There is a decomposed " + Combat.burglar.getCreatureRace(),"","");

			
			if (adjLocations[0] != "") {
				Gui.text("To the North is " + adjLocations[0],"","");
			}
			
			if (adjLocations[1] != "") {
				Gui.text("To the South is " + adjLocations[1],"","");
			}
			
			if (adjLocations[2] != "") {
				Gui.text("To the East is " + adjLocations[2],"","");
			}
			
			if (adjLocations[3] != "") {
				Gui.text("To the West is " + adjLocations[3],"","");
			}
			
			Gui.text("───────────────────────────────","","");
			Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
			Gui.text("","","");
			
			//Obtains a user's input from the graphical interface.
			userInput = Gui.substituteScanner();
			
			// Determines what location or event to occur based on user's input.
			selectOption(userInput);
		}
	}

	
	static void mountainCrossroads() {
		
		Dialog.mountainCrossroads01();
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}


	static void mountainVillage() {
		
		Dialog.village01();
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}

	
	static void cryptGate() {
		
		Dialog.castleGate01();
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}

	
	static void crypt() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		
		Dialog.crypt01();
		
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}

	
	static void mercinariesDen() {
		
		//Obtain adjacent Locations.
		adjLocations = map(location);
	
		Gui.text("","","");
		

		
		Gui.text("You are currently in ","","n"); Gui.text(location.toUpperCase(),"black","b");
		Dialog.sleep(3000);
		Gui.text("You find a burnt out campfire. Looks like everyone left!","","");
		Dialog.sleep(3000);
		Gui.text("You find your stolen good, amongst the tents! ","","");
		Dialog.sleep(3000);
		Gui.text("* They must be out by the river! *","gray","itallic");
		Dialog.sleep(3000);
		Gui.text("You steal a horse and ride away with all your rightful belongings. ","","b");
		Dialog.sleep(3000);
		Gui.text("CONGRATULATIONS! YOU WON!","dg","b");
		Dialog.sleep(3000);
		Gui.text("THE END","dg","b");
		Dialog.sleep(30000);
		
		if (adjLocations[0] != "") {
			Gui.text("To the North is " + adjLocations[0],"","");
		}
		
		if (adjLocations[1] != "") {
			Gui.text("To the South is " + adjLocations[1],"","");
		}
		
		if (adjLocations[2] != "") {
			Gui.text("To the East is " + adjLocations[2],"","");
		}
		
		if (adjLocations[3] != "") {
			Gui.text("To the West is " + adjLocations[3],"","");
		}
		
		Gui.text("───────────────────────────────","","");
		Gui.text("Which way do you want to go ","","n"); Gui.text(compassDirection(adjLocations),"","nb"); Gui.text("?: ","",""); 
		Gui.text("","","");
		
		//Obtains a user's input from the graphical interface.
		userInput = Gui.substituteScanner();
		
		// Determines what location or event to occur based on user's input.
		selectOption(userInput);
	}

	
	
	
	
	
}












