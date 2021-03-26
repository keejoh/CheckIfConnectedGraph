import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestConnected {
	//create data fields for TestConnected class
	private File file;
	private int numberOfVerticies;
	private String filename;
	//create a map to be able to traverse the input text file
	private Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
	//constructor for TestConnected class taking a file filename and numberOFVerticies as parameters
	public TestConnected(File graphFile, String filename, int numberOfVerticies) {
		//sets the parameters equal to the this. instances of the class
		this.file = graphFile;
		this.filename = filename;
		this.numberOfVerticies = numberOfVerticies;

	}
	//method to test if the graph from the text file is connected
	public void testIfConnected() throws FileNotFoundException {
		//test if the file exists
		if (file.exists()) {
			Scanner input = new Scanner(file);
			TreeMap<String, ArrayList<String>> map = new TreeMap<>();
			//iterates through each line of the file
			while (input.hasNextLine()) {
				//takes the next line of the files and turns it into a string array.
				String text = input.nextLine();
				String[] lines = text.split("[\\s]");
				//take first character from the line as the vertices key 
				String key = lines[0].toLowerCase();
				//validates that that the input is a vertices or if it has any edges
				if (lines.length >= 2) {
					//if map does not contain the vertices add to map as key
					if (!map.containsKey(key)) {
						//increase the number of vertices
						numberOfVerticies++;
						//add vertices to map as key and instantiate value as ArrayList of type String
						map.put(key, new ArrayList<String>());
						//iterate over the line for the given vertices and add corresponding edges
						for (int j = 1; j < lines.length; j++) {
							map.get(key).add(lines[j]);
						}

					}

				}
			}
			//checking whether or not the graph is connected or not
			//mark a starting place for grabbing the first key and corresponding values from the map
			String startCheck = map.firstKey();
			//create an array list that holds the initial vertices to check
			ArrayList<String> initialVerticies = map.get(startCheck);
			//make an array list that holds the verticies to check instantiated with a clone of intialVerticies otherwise the array list will 
			//point back to the first key value of map and change the map incorrectly
			ArrayList<String> verticiesToCheck = (ArrayList<String>) initialVerticies.clone();
			//make a set that will check connectivity by holding all the vertices that have been visited from the starting point
			Set<String> checkConnectivity = new HashSet<>();
			//create ArrayList that holds vertices to remove from the verticiesToCheck array list other wise check will go on infinitely
			ArrayList<String> removeVerticies = new ArrayList<>();
			//create a string that will hold the vertices to remove that will be added to removeVerticies Array List
			String verticiesToRemove;

			int i = 0;
			//run while verticiesToCheck is not empty
			while (!verticiesToCheck.isEmpty()) {
			//validates that there is not an out of bounds access occuring
				if (i < verticiesToCheck.size()) {
					//validates that the map contains the key being accesed
					if (map.containsKey(verticiesToCheck.get(i))) {
						//starts with the initial values in verticiesToCheck and adds values to checkConnectivity as 
						//we traverse from an initial vertices and its corresponding edges
						verticiesToCheck.addAll(map.get(verticiesToCheck.get(i)));
						checkConnectivity.addAll(verticiesToCheck);
						//placeholder for the vertices that needs to be removed
						verticiesToRemove = verticiesToCheck.get(i);
						//adds the vertices that has been traversed to the removeVerticies Array List
						removeVerticies.add(verticiesToRemove);
						//remove all the vertices in verticiesToCheck that are in removeVerticies so traveled verticies 
						//are not revisited ensuring that an infinite loop does not occur
						verticiesToCheck.removeAll(removeVerticies);

					}

				}
			}
			//validates whether or not the graph is connected
			//if the number of vertices in the set checkConnectivity equal the total number of Verticies 
			//we were able to traverse the entire graph and reach all verticies from the intial starting 
			//point meaning the graph is connected. if they are not equal then the graph is not connected
			if (checkConnectivity.size() == numberOfVerticies) {
				System.out.println("The graph is connected!");

			} else {
				System.out.println("The graph is not connected!");
			}

		}

		// prints out each word and the number of times it appears in the file
		else {
			System.out.println("File " + filename + " does not exist");

		}
	}
}