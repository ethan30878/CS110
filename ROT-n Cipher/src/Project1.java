/* Name: Ethan Glenn
* File Name: Project1.java
* Date: 7 October, 2022
* Program Description: Beep boop bop
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
	static String _plainText = "";
	static String _cipherText = "";
	static int _n = 0;

	/* This function asks the user where to get the data from, a string or a file. */
	public static void queryPlainText() throws IOException {
		boolean validInput = false;
		
		do {
			String option = JOptionPane.showInputDialog(null, WELCOME_MSG, "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
			
			switch (option) {

				/* Option '1' is from user text. */
				case "1":
					_plainText = JOptionPane.showInputDialog(null, "Enter the plain text:", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);
					getNumberOfRotations();
					validInput = true;
					break;

				/* Option '2' is from a file path. */
				case "2":
					/* Get the filename from the user. */
					String fileName = JOptionPane.showInputDialog(null, "Enter the name of the file: ", "ROT-n Cipher", JOptionPane.INFORMATION_MESSAGE);

					/* Attempt to open the file. */
					FileReader reader = null;
					try {
						reader = new FileReader(fileName);
					} catch (FileNotFoundException exception) {
						JOptionPane.showMessageDialog(null, "File not found: \"" + fileName + "\"\nPlease try again.");
						continue;
					}

					/* Read the file to the plain text string. */
					BufferedReader in = new BufferedReader(reader);
					while (in.ready()) {
						_plainText += in.readLine() + "\n";
					}
					in.close();
					reader.close();
	
					getNumberOfRotations();
					validInput = true;
				break;

				default:
					JOptionPane.showMessageDialog(null, "Invalid choice: " + option + "\nMust be between 1 and 2.\nPlease try again.");
					validInput = false;
			}
		} while (!validInput);  
	}

	// /* This function encodes the data using the ROT-N algorithm. */
	// public static String encryptRotN(String str) { 	
	// 	/* The user string will be in upper case to keep algorithm incomplex. */	
	// 	str = str.toUpperCase();
		
	// 	/* Rotate the ASCII english characters and nothing more. */
	// 	String cipherText = "";

	// 	/* Run the encoder. */
	// 	for (int i = 0; i < str.length(); i++) {
	// 		char c = str.charAt(i);

	// 		if (Character.isAlphabetic(c)) {
	// 			c = (char)((((char)(c - 65) + _n) % 26) + 65);
	// 			cipherText += c;
	// 		} else {
	// 			cipherText += c;
	// 		}
	// 	}

	// 	/* Return the newly encrypted string. */
	// 	return cipherText;
	// }

	/*  */
	public static void getNumberOfRotations() {
		/* If the text that was inserted was incorrect do this process again. */
		boolean valid = false;
		do {
			/* Retrieve the number of rotations from the user. */
			String value = JOptionPane.showInputDialog(null, ENTER_VALUE);
			
			/* Attempt to convert the string to an integer. */
			try {
				_n = Integer.parseInt(value);
			} catch(NumberFormatException Exception) {
				/* Displays an error if this string could not be converted to an integer. */
				String notNumeric = String.format("Value is not numeric: %d\nTry again.", value);
				JOptionPane.showMessageDialog(null, notNumeric);
				continue;
			}
			/* Tests if n is within range. */
			if (_n >=1  && _n <= 25) {
				break;
			} else {
				String outOfRange = String.format("Invalid number: %d\nMust be between 1 and 25.\nPlease try again.", _n);
				JOptionPane.showMessageDialog(null, outOfRange);
				valid = true;
			}
		} while(valid);
	}
	/* Main method, contains basic instructions for entire program */
	public static void queryOutput() throws IOException {
		
		String outputOption = JOptionPane.showInputDialog(null, OUTPUT_MSG);
		boolean valid = false;
		do 	 {
			switch (outputOption) {
				case "1":
					JOptionPane.showMessageDialog(null, _cipherText);
					valid = true;
					break;
				case "2":
					BufferedWriter out = new BufferedWriter(new FileWriter("riddles-encrypted.txt"));
					out.write(_cipherText);
					out.close();
					valid = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid choice: " + outputOption + "\nMust be between 1 and 2.\nPlease try again.");
			}
		} while(!valid);
	}
	public static void main(String[] args) throws Exception {
		boolean runAgain = true;
		int counter = 0;
		do {
			queryPlainText();
			// _cipherText = encryptRotN(_plainText);

			/* The user string will be in upper case to keep algorithm incomplex. */	
			_plainText = _plainText.toUpperCase();

			/* Run the encoder. */
			for (int i = 0; i < _plainText.length(); i++) {
				char c = _plainText.charAt(i);
				if (Character.isAlphabetic(c)) {
					c = (char)((((char)(c - 65) + _n) % 26) + 65);
					_cipherText += c;
					counter++;
				} else {
					_cipherText += c;
				}
			}
			queryOutput();
			JOptionPane.showMessageDialog(null, "You have successfully encrypted " + counter + " characters.");
		
			int yesOrNo = JOptionPane.showConfirmDialog(null, "Encrypt another plain text?");
			
			if (yesOrNo == JOptionPane.NO_OPTION || yesOrNo == JOptionPane.CANCEL_OPTION)
				runAgain = false;
		} while(runAgain);		
	}
}