

public class Combat {
	
	// Used to return results to parent method.
	static String combatResult = "";
	
	// Initialization used for 'substituteScanner()'.
	public static String userInput = Gui.getUserInput();
	
	// Returns a different colour depending on the entities health. 
	static String healthBar(int health) {
		String healthReturn = "green";
		if (health > 80) {
			healthReturn = "h01";
		} else if (health >= 60 && health < 80) {
			healthReturn = "h02";
		} else if (health >= 40 && health < 60) {
			healthReturn = "h03";
		} else if (health >= 25 && health < 40) {
			healthReturn = "h04";
		} else if (health >= 0 && health < 25) {
			healthReturn = "h05";
		} else if (health <= 0) {
			healthReturn = "magenta";
		}
		return healthReturn;
	}
	
	static Player pl;
	static Enemies vampire;
	static Enemies tankWarlord;
	static Enemies archer;
	static Enemies burglar;
	
	static void loadSettings()
	{
		pl = new Player("You","Human",100,20);
		
		vampire = new Enemies("Dracula","Vampire", 100, 20, "active");
		tankWarlord = new Enemies("Tank Warlord","Warlord", 200, 50,"active");
		archer = new Enemies("Watchtower's Archer","Bandit", 100, 8,"active");
		burglar = new Enemies("Mugger","Burglar", 100, 14,"active");
		 
	}
	

	static void combat(String pastLocation, Enemies cr) {
		do {
			// If the enemy is not dead
			if(!cr.getCreatureIsDead()) {
				Gui.text("You enter " + Locations.location + " and come across ","","n"); Gui.text(cr.getCreatureName() + " the " + cr.getCreatureRace(),"b","nb"); Gui.text(".","b","");
				Gui.text("They look aggressive and are blocking you for proceeding any further. ","","");
				Gui.text("Do you want to fight?","","n"); Gui.text(" (Y/N)","","nb"); Gui.text("?","","");	
						
				//Obtains a user's input from the graphical interface.
				userInput = Gui.substituteScanner();
				
				
				switch(userInput.toLowerCase()) {
				case "y":
				case "yes":	
					fightCreature(pastLocation, cr);
					break;
				
				case "n":
				case "no":	
					Gui.text("","","b");
					Gui.text("You decided to not fight " + cr.getCreatureName(),"","");
					Gui.text("Going back to " + pastLocation,"","");
					Gui.text("───────────────────────────────","","b");
					Locations.location = pastLocation;
					cr.setCombatStatus("run");
					break;
				
				default:
					// Display an error message if the user entered anything apart from the predefined switch cases.
					Gui.text("\nInvalid input. Please try again.","","b");
					break;
				}
				
				
				if (cr.getCombatStatus() != "active") {
					break;
				}
			}

		}
		
		// Loop back if enemy is not dead.
		while (cr.getCombatStatus() == "active");
	}
	
	

	static void fightCreature(String pastLocation, Enemies cr) {
		
		if (cr == archer) {
			Dialog.watchtower02();
		}
		
		Gui.text("","","b");
		Gui.text("───────────────────────────────","","b");
		Gui.text("Player health: ","","n"); Gui.text(Integer.toString(pl.getPlayerHealth()),healthBar(pl.getPlayerHealth()),"b");
		Gui.text("Creature health: ","","n"); Gui.text(Integer.toString(cr.getCreatureHealth()),healthBar(cr.getCreatureHealth()),"b");
		
		while (pl.getPlayerHealth() > 0 && cr.getCreatureHealth() > 0) {

			Gui.text("","","");
			
			int playerDamage = pl.getPlayerDamage();
			int creatureDamage =  cr.getCreatureDamage();
			
			int playerHealth = pl.getPlayerHealth() - creatureDamage;
			int creatureHealth = cr.getCreatureHealth() - playerDamage;
			
			
			Gui.text(cr.getCreatureName() + " the " + cr.getCreatureRace() + " inflicts " + creatureDamage + " % damage","","");
			Gui.text(pl.getPlayerName() + " inflict " + playerDamage + " % damage","","");
			
			pl.setPlayerHealth(playerHealth);
			cr.setCreatureHealth(creatureHealth);
			
			Gui.text("Player health: ","","n"); Gui.text(Integer.toString(pl.getPlayerHealth()),healthBar(pl.getPlayerHealth()),"b");
			Gui.text("Creature health: ","","n"); Gui.text(Integer.toString(cr.getCreatureHealth()),healthBar(cr.getCreatureHealth()),"b");
			
			// Pauses the game between combat iterations until player presses enter.
			Gui.substituteScanner();
			
			// If the player won the combat.
			if(pl.getPlayerHealth() > 0 && cr.getCreatureHealth() <= 0) {
				Gui.text("You killed " + cr.getCreatureName(),"","");
				cr.setCreatureIsDead(true);
				Gui.text("▀▀▀▀▀▀▀▀▀▀▀▀ OBJECTIVE COMPLETE ▀▀▀▀▀▀▀▀▀▀▀▀","dg","b");
				
				Gui.text("You spend some time recovering","","n");
				 for (int i = 0; i < 3; i++) {
					 Dialog.sleep(1000);
					 Gui.text("..","","n");
				    }
				 Gui.text("","","");
				 
				 Gui.text("Press ","","nb"); Gui.text("ENTER","dg","nb"); Gui.text(" to continue:","","b");
				Gui.substituteScanner();
				cr.setCombatStatus("won");
				

			// If the enemy won the combat.
			} else if (pl.getPlayerHealth() <= 0 && cr.getCreatureHealth() > 0) {
				Gui.text(cr.getCreatureName() + " killed you!!!!","","");
				Gui.text("▀▀▀▀▀▀▀▀▀▀▀▀ GAME OVER ▀▀▀▀▀▀▀▀▀▀▀▀","r","b");
				Gui.text("You respawned at your last location.","","");
				Gui.text("Press ","","nb"); Gui.text("ENTER","dg","nb"); Gui.text(" to continue:","","b");
				Gui.substituteScanner();
				Locations.location = pastLocation;
				cr.setCombatStatus("lost");

			// If both the player and enemy died.
			} else if (pl.getPlayerHealth() <= 0 && cr.getCreatureHealth() <= 0) {
				Gui.text("You and the " + cr.getCreatureName() + " both died!!!!","","");
				Gui.text("▀▀▀▀▀▀▀▀▀▀▀▀ GAME OVER ▀▀▀▀▀▀▀▀▀▀▀▀","r","b");
				Gui.text("You respawned at your last location.","","");
				Gui.text("Press ","","nb"); Gui.text("ENTER","dg","nb"); Gui.text(" to continue:","","b");
				Gui.substituteScanner();
				Locations.location = pastLocation;
				cr.setCombatStatus("lost");

			}
		}
	}
}
