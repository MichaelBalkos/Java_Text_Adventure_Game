
public class Game {

	// This main method executes the entire program.
	public static void main(String[] args) {
		
		Gui g01 = new Gui();
		
		// Launches the GUI.
		g01.openGui();
		
		// Initialize game player and enemies
		Combat.loadSettings();
		
		Dialog.Opening();
		
		Locations.gameLocations();
		
	}
	
	

}
