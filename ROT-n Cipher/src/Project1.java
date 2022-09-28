/* Name: Ethan Glenn
* File Name: Project1.java
* Date: 7 October, 2022
Program Description: Beep boop bop
*/
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Project1 {

	public static void main(String[] args) throws Exception {
		
		final String WELCOME_MSG = "Welcome to the ROT-n Cipher!\n"
				+ "This program will encrypt any given plain text using the ROT-n Cipher.\n"
				+ "How would you like to input the plain text?\n"
				+ "Enter the number of your selection:\n\n"
				+ "1: Enter plain text manually.\n"
				+ "2: Read plain text from file.";
		final String ENTER_VALUE = "Enter a vlaue for n from 1 to 25:";
		
		String value = "";
		
		int n = 0;
		
		String unencrypted = "";
		
		String encrypted = "";
		
		String fileName = "";
		
		Boolean validInput = true;
		
		Boolean runAgain = false;
		
		do {
			
			String option = JOptionPane.showInputDialog(null, WELCOME_MSG, "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
			
			switch (option) {
				case "1":
				
					unencrypted = JOptionPane.showInputDialog(null, "Enter the plain text:", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
					value = JOptionPane.showInputDialog(null, ENTER_VALUE);
					n = Integer.parseInt(value);
					validInput = true;
				
					break;
				case "2":
		
					fileName = JOptionPane.showInputDialog(null, "Enter the name of the file: ", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
					value = JOptionPane.showInputDialog(null, ENTER_VALUE);
					BufferedReader in = new BufferedReader(new FileReader(fileName));
					while (in.ready()) {
						unencrypted += in.readLine();
					}
					in.close();
					validInput = true;
					// Insert file reading code here
				
					break;
				default:
				
					JOptionPane.showMessageDialog(null, "Invalid choice: " + option + "\nMust be between 1 and 2.\nPlease try again.");
					validInput = false;
			
			}
		} while (!validInput);  
		
		// JOptionPane.showMessageDialog(null, n);
		
		for (int i = 0; i < unencrypted.length(); i++) {
			encrypted += unencrypted.charAt(i);
		}
		JOptionPane.showMessageDialog(null, encrypted);
		
		int yesOrNo = JOptionPane.showConfirmDialog(null, "Run again?");
		
		if (yesOrNo == JOptionPane.YES_OPTION)
			runAgain = true;
		else
			runAgain = false;
		}
			
	}
