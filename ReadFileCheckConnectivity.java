
/*KeeJoh O'Hearon
 * Data Structures
 * Project 3 Bonus Task
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadFileCheckConnectivity {

	public static void main(String[] args) {
		// create counter to keep track of the number of verticies
		int numberOfVerticies = 0;
		// Accept a file path as input
		Scanner input1 = new Scanner(System.in);
		// Displays welcome message and required input to make the program run
		// correctly.
		System.out.println("Welcome and thank you for using our program. \n "
				+ " this program will prompt you to enter a file. \n The file must be in this java project folder, two tests files have been provided in the project \n "
				+ "TestEdgesConnected.txt and TestEdgesNotConnected.txt these file names must be entered exactly as displayed."
				+ " \n The filename must have the extension of '.txt'");
		System.out.print("Please enter a file : ");
		String filename = input1.nextLine();
		// create a file object from the filename
		File file = new File(filename);
		// create an instance of TestConnected from the created file the input filename
		// and the initial numberOfVerticies
		TestConnected testGraph = new TestConnected(file, filename, numberOfVerticies);
		// tries to run the testIfConnected method if the method can be run validates
		// whether or not the graph from the text file is connected or not
		try {
			testGraph.testIfConnected();
		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " does not exist");
			e.printStackTrace();
		}

	}

}
