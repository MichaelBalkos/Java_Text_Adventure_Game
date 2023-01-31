
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.util.Date;


//GUI Console created for clearing, colour and emphasis support on text, that the default Eclipse console does not support.
public class Gui extends JFrame {
	
	// Declarations/Initializations for referencing purposes.
	private JPanel contentPane;
	private static JTextPane Pannel = new JTextPane();
	private final JPanel panel = new JPanel();
	private static JScrollPane scrollPane = new JScrollPane();
	private final JTextField textField = new JTextField();
	private final Component verticalStrut = Box.createVerticalStrut(5);
	private final JPanel panel_1 = new JPanel();
	
	//The time text was obtained by 'JTextField, stored into 'getUserInput()'.
	public static long textTime = 0;
	public static long gameTime;
	
	// Object used for suspending threads and thread Synchronization. Used by 'substituteScanner();'.
	static Object lock = new Object();
	
	// Holds the last String entered into 'textField'.
	private static String userInput = "Initialize";

	// userInput Setter.
	public void setUserInput(String userInput) {
		Gui.userInput = userInput; 
	}
	
	// userInput Getter.
	public static String getUserInput() {
		return userInput;
	}
	
	// Launches the GUI.
	public void openGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Creates the frame object
					Gui frame = new Gui();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Pause the game (thread suspension) until an answer was provided to the most recent question.
	// This method is essentially a homemade DIY java Scanner with complex functionality for graphical interface elements.
	public static String substituteScanner() {
		
		// Obtains the current time the above question was sent.
		Date date = new Date();
		gameTime = date.getTime();
		// Obtains the last received text's current time.
		textTime = Gui.textTime;
		
		// Intrinsic lock/monitor lock tied to 'lock' object.
        synchronized (lock) {
            while (textTime <= gameTime) {
                try {
                	// Thread suspension by invoking 'wait()'.
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // If text was detected, obtain it and continue the game by ending the 'while' loop.
                userInput = Gui.getUserInput();
                break;
            } 
        }
        return userInput;
	}
	
	
	/* This method directs text to output and display on the JEditorPane.
	 * Appends text to the document of JEditorPane, as .setText does not keep previous text.
	 * Also controls the theme of text, E.g. color and weight of text such as green and bold.
	*/
	public static void text(String text, String color, String emphasis) {
		   try {

			   	//Using StyledDocument to append text, instead of '.setText()' as '.setText()' clears the previous text entries.
				StyledDocument doc = Pannel.getStyledDocument();
				Style style = Pannel.addStyle("style", null);
				
		
				// Determines what color/colour to style the text.
				String color_clean =  color.toLowerCase(); 
				switch(color_clean) {
				case "black":
				case "":
					 StyleConstants.setForeground(style, Color.black);
					break;
				case "grey":
				case "gray":	
					 StyleConstants.setForeground(style, Color.gray);
					break;	
				case "green":
				case "g":
					StyleConstants.setForeground(style, Color.green);
					break;
				case "darkgreen":
				case "dgreen":
				case "dg":
					//Custom Green with RGB values
					final Color DARK_GREEN = new Color(0,204,0);
					StyleConstants.setForeground(style, DARK_GREEN);
					break;
				case "blue":
				case "b":
					StyleConstants.setForeground(style, Color.blue);
					break;
				case "cyan":
				case "c":
					StyleConstants.setForeground(style, Color.CYAN);
					break;	
				case "red":
				case "r":
					StyleConstants.setForeground(style, Color.red);
					break;
				case "orange":
				case "o":
					StyleConstants.setForeground(style, Color.orange);
					break;
				case "purple":
				case "p":
				case "magenta":	
					StyleConstants.setForeground(style, Color.magenta);;
					break;
				case "pink":
					StyleConstants.setForeground(style, Color.pink);
					break;
				case "yellow":
				case "y":
					StyleConstants.setForeground(style, Color.yellow);
					break;
					
					// Colors for gradual gradient health bars
				case "h01":
					final Color h01 = new Color(64, 255, 0);
					StyleConstants.setForeground(style, h01);
					break;	
				case "h02":
					final Color h02 = new Color(191, 255, 0);
					StyleConstants.setForeground(style, h02);
					break;	
				case "h03":
					final Color h03 = new Color(255, 191, 0);
					StyleConstants.setForeground(style, h03);
					break;	
				case "h04":
					final Color h04 = new Color(255, 128, 0);
					StyleConstants.setForeground(style, h04);
					break;	
				case "h05":
					final Color h05 = new Color(242, 13, 0);
					StyleConstants.setForeground(style, h05);
					break;	
					
				default:
					StyleConstants.setForeground(style, Color.black);
					break;
				}
				
				/* If 'n' is appended to the String 'emphasis', such as "nb" or "nitallic", append displayed text to pre-existing line.
				 * Used for styling a specific portion of words in a single line.
				 * The reason it was made this way was to avoid creating infrequently used arguments that create confusion when calling the 'text()' method.
				 */
				if (emphasis != "") {
					char newLine;
					newLine = emphasis.charAt(0);
					
					if (newLine != 'n') {
						//Appends to a new line
						text = text + "\n";
					}
				} else {
					text = text + "\n";
				}
				   
				//Controls emphasis on text such as bold, italics and underline.
				String emphasis_clean =  emphasis.toLowerCase(); 
				switch(emphasis_clean) {
				case "b":
				case "nb":
				case "bold":
				case "nbold":
					StyleConstants.setBold(style, true);
					break;
				case "i":
				case "ni":
				case "italic":
				case "nitalic":
					StyleConstants.setItalic(style, true);
					break;
				case "u":
				case "nu":
				case "underline":
				case "nunderline":
					StyleConstants.setUnderline(style, true);
					break;
				default:
					break;
				}
				
				//Future reference for changing font size if considered.
				//StyleConstants.setFontSize(fontSize, 25);
				
		    // Append text to output
		    doc.insertString(doc.getLength(), text, style);
		    
			// Scrolls the scrollPane down to show appended text.
		    scroll();
		   } catch(BadLocationException exc) {
		      exc.printStackTrace();
		   }
		}
	
	// Scrolls the scrollPane down
	static void scroll() {
		 Pannel.setCaretPosition(Pannel.getDocument().getLength());
	}
	
	
	// Default Constructor.
	public Gui() {
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setAlignmentY(Component.TOP_ALIGNMENT);
		textField.setColumns(10);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		
		contentPane.add(panel_1, BorderLayout.NORTH);
		contentPane.add(scrollPane);
		Pannel.setEditable(false);
		Pannel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Pannel.setForeground(Color.BLACK);
		scrollPane.setViewportView(Pannel);
		
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(verticalStrut);
		panel.add(textField);
		
		/* This section of code below essentially mimics Java's default scanner, 
		 * but instead of obtaining input though default console, it obtains input though a JTextField.
		 * This was achieved by suspending threads until a user entered 
		 * text or an answer that was after the time a question was provided to the user.
		 */
		
		//Event listener on 'textField' that looks for 'Enter' key press.
		textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// Obtain then store the text entered into the 'textField'.
            	String userInput = textField.getText();
            	setUserInput(userInput);
            	
            	/* Obtain date in milliseconds then store.
            	 * Used for comparisons between when a question was asked, and an answer was given.
            	 */
            	Date date02 = new Date();
            	textTime = date02.getTime();
            	
            	// Intrinsic lock/monitor lock tied to 'lock' object.
            	messageSynchronize(Gui.lock);
            	textField.setText("");
            }
		});
	}
	
	// Used to unpause the game, when an answer was provided to a question.
	public void messageSynchronize(Object lock) {
		synchronized (lock) {
			// notify() - When invoked, wakes up a synchronized suspended thread.
			// Unpauses the game or wakes up the thread after an answer was submitted.
            lock.notify();
        }
	}
	
	
	
}
