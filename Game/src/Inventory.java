

public class Inventory {
	static String [] items = new String[10];
	static int count = 0;
	static int arrayIndex = 0;
	
	
	// Initialize first item into inventory.
	static void inventoryInitialisation() {
		items[arrayIndex] = "Rusty Short Sword";
		arrayIndex++;
		count++;
		
	}
	
	static void addItem(String item) {
		items[arrayIndex] = item;
		arrayIndex++;
		count++;
	}
	
	
	static void displayInventory() {
		Gui.text("─────────────────── Inventory ──────────────────","","b");
		for(int i = 0; i < items.length;i++) {
			if(items[i] != null) {
				
				Gui.text("Item slot ","","nb");
				Gui.text(""+(i+ 1) + ":","","nb");
				Gui.text(" " + items[i],"","");
			}
			
		}
		Gui.text("─────────────────────────────────────────────","","b");
	}
	
	
}
