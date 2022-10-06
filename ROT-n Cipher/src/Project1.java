/* Name: Ethan Glenn
* File Name: Project1.java
* Date: 7 October, 2022
* Program Description: The point of this project is to create a program that encrypts user input or a file using a ROT-n algorithm.
*/
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Project1 {

	/* This defines the dialog box messages and key variables used. */
	static final String WELCOME_MSG = "Welcome to the ROT-n Cipher!\n"
				+ "This program will encrypt any given plain text using the ROT-n Cipher.\n"
				+ "How would you like to input the plain text?\n"
				+ "Enter the number of your selection:\n\n"
				+ "1: Enter plain text manually.\n"
				+ "2: Read plain text from file.";
	static final String ENTER_VALUE = "Enter a value for n from 1 to 25:";
	static final String OUTPUT_MSG = "How would you like to output the cipher text?\n"
				+ "Enter the number of your selection:\n"
				+ "1: Output cipher text to screen.\n"
				+ "2: Output cipher text to file.";
	static String plainText = "";
	static String cipherText = "";
	static int n = 0;

	/* This function asks the user where to get the data from, a string or a file. */
	public static void queryPlainText() throws IOException {
		boolean validInput = false;
		do {
			String option = JOptionPane.showInputDialog(null, WELCOME_MSG, "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
			switch (option) {
				case "1":
					plainText = JOptionPane.showInputDialog(null, "Enter the plain text:", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
					getNumberOfRotations();
					validInput = true;
					break;
				case "2":
					/* Gets the filename from the user. */
					String fileName = JOptionPane.showInputDialog(null, "Enter the name of the file: ", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
					/* Attempts to open the file. */
					FileReader reader = null;
					try {
						reader = new FileReader(fileName);
					} catch (FileNotFoundException exception) {
						JOptionPane.showMessageDialog(null, "File not found: \"" + fileName + "\"\nPlease try again.");
						continue;
					}
					/* Reads the file to the plain text string. */
					BufferedReader in = new BufferedReader(reader);
					while (in.ready()) {
						plainText += in.readLine() + "\n";
					}
					in.close();
					reader.close();
					getNumberOfRotations();
					validInput = true;
					break;
				default:
					/* Displays an error message if choice is not 1 or 2. */
					JOptionPane.showMessageDialog(null, "Invalid choice: " + option + "\nMust be between 1 and 2.\nPlease try again.");
					validInput = false;
			}
		} while (!validInput);  
	}
	/* Acquires and validates n value for ROT-n calculations. */
	public static void getNumberOfRotations() {
		boolean valid = true;
		do {
			/* Retrieves the number of rotations from the user. */
			String value = JOptionPane.showInputDialog(null, ENTER_VALUE);
			/* Attempts to convert the string to an integer. */
			try {
				n = Integer.parseInt(value);
			} catch(NumberFormatException Exception) {
				/* Displays an error if this string could not be converted to an integer. */
				String notNumeric = String.format("Value is not numeric: %s\nTry again.", value);
				JOptionPane.showMessageDialog(null, notNumeric);
				continue;
			}
			/* Tests if n is within range. */
			if (n >=1  && n <= 25) {
				break;
			} else {
				/* Displays an error if n is out of range. */
				String outOfRange = String.format("Invalid number: %d\nMust be between 1 and 25.\nPlease try again.", n);
				JOptionPane.showMessageDialog(null, outOfRange);
				valid = false;
			}
		} while(!valid);
	}
	/* Main method, contains basic instructions for entire program */
	public static void queryOutput() throws IOException {
		boolean valid = false;
		do 	 {
			String outputOption = JOptionPane.showInputDialog(null, OUTPUT_MSG);
			/* Checks output decision from user */
			switch (outputOption) {
				case "1":
					/* Prints cipherText to screen. */
					JOptionPane.showMessageDialog(null, cipherText);
					valid = true;
					break;
				case "2":
					/* Writes cipherText to file.  */
					BufferedWriter out = new BufferedWriter(new FileWriter("riddles-encrypted.txt"));
					out.write(cipherText);
					out.close();
					valid = true;
					break;
				default:
					/* Displays an error message if choice is not 1 or 2.*/
					JOptionPane.showMessageDialog(null, "Invalid choice: " + outputOption + "\nMust be between 1 and 2.\nPlease try again.");
					break;
			}
		} while(!valid);
	}
	/* Runs entire program and loops if user wishes to run again. */
	public static void main(String[] args) throws Exception {
		boolean runAgain = true;
		int counter = 0;
		do {
			queryPlainText();
			plainText = plainText.toUpperCase();
			/* Runs the encoder. */
			for (int i = 0; i < plainText.length(); i++) {
				char c = plainText.charAt(i);
				if (Character.isAlphabetic(c)) {
					c = (char)((((char)(c - 65) + n) % 26) + 65);
					cipherText += c;
					counter++;
				} else {
					cipherText += c;
				}
			}
			queryOutput();
			JOptionPane.showMessageDialog(null, "You have successfully encrypted " + counter + " characters.");
			/* Takes user decision to run again or not. */
			int yesOrNo = JOptionPane.showConfirmDialog(null, "Encrypt another plain text?");
			if (yesOrNo == JOptionPane.NO_OPTION || yesOrNo == JOptionPane.CANCEL_OPTION)
				runAgain = false;
		} while(runAgain);		
	}
}