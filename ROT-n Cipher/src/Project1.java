/* Name: Ethan Glenn
* File Name: Project1.java
* Date: 7 October, 2022
Program Description: Beep boop bop
*/
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Project1 {

	public static void main(String[] args) throws Exception {
		
		final String WELCOME_MSG = "Welcome to the ROT-n Cipher!\n"
				+ "This program will encrypt any given plain text using the ROT-n Cipher.\n"
				+ "How would you like to input the plain text?\n"
				+ "Enter the number of your selection:\n\n"
				+ "1: Enter plain text manually.\n"
				+ "2: Read plain text from file.";
		final String ENTER_VALUE = "Enter a value for n from 1 to 25:";
		final String OUTPUT_MSG = "How would you like to output the cipher text?\n"
				+ "Enter the number of your selection:\n"
				+ "1: Output cipher text to screen.\n"
				+ "2: Output cipher text to file.";
		String value = "";
		String unencrypted = "";
		String encrypted = "";
		String fileName = "";
		Boolean validInput = true;
		Boolean runAgain = false;
		Boolean nIsValid = false;
		String outputOption = "";
		int n = 0;
		
		do {
			do {
				String option = JOptionPane.showInputDialog(null, WELCOME_MSG, "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
				
				switch (option) {
					case "1":
						unencrypted = JOptionPane.showInputDialog(null, "Enter the plain text:", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
						do {
							value = JOptionPane.showInputDialog(null, ENTER_VALUE);
							n = Integer.parseInt(value);
							if (n > 0 && n < 25) {
								nIsValid = true;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid number: " + n + "\nMust be between 1 and 25.\nPlease try again.");
							}
						} while(!nIsValid);
						validInput = true;
					break;
					case "2":
						fileName = JOptionPane.showInputDialog(null, "Enter the name of the file: ", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
						do {
							value = JOptionPane.showInputDialog(null, ENTER_VALUE);
							n = Integer.parseInt(value);
							if (n >= 1 && n <= 25) {
								nIsValid = true;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid number: " + n + "\nMust be between 1 and 25.\nPlease try again.");
							}
						} while(!nIsValid);
						BufferedReader in = new BufferedReader(new FileReader(fileName));
						while (in.ready()) {
							unencrypted += in.readLine() + "\n";
						}
							in.close();
							validInput = true;
					break;
					default:
						JOptionPane.showMessageDialog(null, "Invalid choice: " + option + "\nMust be between 1 and 2.\nPlease try again.");
						validInput = false;
				}
			} while (!validInput);  
			
			int counter = 0;
			
			unencrypted = unencrypted.toUpperCase();
			
			for (int i = 0; i < unencrypted.length(); i++) {
				
				char c = unencrypted.charAt(i);
				
				if (Character.isAlphabetic(c)) {
					c = (char)((((char)(c - 65) + n) % 26) + 65);
					encrypted += c;
					counter++;
				} else {
					encrypted += c;
				}
			}
			
			outputOption = JOptionPane.showInputDialog(null, OUTPUT_MSG);
			
			switch (outputOption) {
				case "1":
					JOptionPane.showMessageDialog(null, encrypted);
					break;
				case "2":
					BufferedWriter out = new BufferedWriter(new FileWriter("riddles-encrypted.txt"));
					out.write(encrypted);
					out.close();
					break;
			}
			
			JOptionPane.showMessageDialog(null, "You have successfully encrypted " + counter + " characters.");
		
			int yesOrNo = JOptionPane.showConfirmDialog(null, "Encrypt another plain text?");
			
			if (yesOrNo == JOptionPane.YES_OPTION)
				runAgain = true;
			else
				runAgain = false;
		} while(runAgain);		
	}
}