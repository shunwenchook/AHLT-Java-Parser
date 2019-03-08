import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexicon {

	// ArrayList of Strings with all the different lexicons
	ArrayList<String> NN, NNS, VB, VBZ, JJ, DT;
	
	String[] sentence;

	/**
	 * Constructor to create Lexicon object. Initializes input sentence from user and reads in the lexicon.txt text file.
	 *  
	 * @param sentence - Reads in input sentence from user from the main program
	 * @throws IOException - To read in the files
	 */
	public Lexicon(String sentence[]) throws IOException {
		this.sentence = sentence;

		NN = new ArrayList<String>();
		NNS = new ArrayList<String>();
		VB = new ArrayList<String>();
		VBZ = new ArrayList<String>();
		JJ = new ArrayList<String>();
		DT = new ArrayList<String>();

		// Reads in lexicon.txt text file
		FileReader fr = null;
		try {
			fr = new FileReader("lexicon.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);

		// Reads in each sentence and adds each line of lexicon type into the class
		String line;
		int counter = 1;
		while ((line = br.readLine()) != null) {
			String[] lineSplit = line.split("\\s");

			// Switch case to look for the lexicon type and stores the lexicons in the arraylists
			switch (lineSplit[0]) {
			case "NN":
				while (counter < lineSplit.length) {
					NN.add(lineSplit[counter]);
					counter++;
				}
				break;
			case "NNS":
				while (counter < lineSplit.length) {
					NNS.add(lineSplit[counter]);
					counter++;
				}
				break;
			case "VB":
				while (counter < lineSplit.length) {
					VB.add(lineSplit[counter]);
					counter++;

				}
				break;
			case "VBZ":
				while (counter < lineSplit.length) {
					VBZ.add(lineSplit[counter]);
					counter++;

				}
				break;
			case "JJ":
				while (counter < lineSplit.length) {
					JJ.add(lineSplit[counter]);
					counter++;

				}
				break;
			case "DT":
				while (counter < lineSplit.length) {
					DT.add(lineSplit[counter]);
					counter++;

				}
				break;

			default:
				break;
			}
			counter = 1;
		}
	}

	/**
	 * A method to parse the input sentence and parses each word and adds its lexicon type to the words
	 * @return parsedSentence - ArrayList that contains each word and its lexicon accordingly
	 */
	public ArrayList<String> parseSentence() {
		boolean lexiconExist;
		ArrayList<String> parsedSentence = new ArrayList<String>();
		
		// Loops though each word for the input
		for (int i = 0; i < sentence.length; i++) {
			lexiconExist = false;
			
			// For loops to loop though each lexicon type and check with the text files provided
			for (int j = 0; j < NN.size(); j++) {
				if (sentence[i].equals(NN.get(j))) {
					System.out.println(sentence[i] + " = Noun Singular");
					parsedSentence.add("NN|" + sentence[i]);

					lexiconExist = true;
				}
			}

			for (int j = 0; j < NNS.size(); j++) {
				if (sentence[i].equals(NNS.get(j))) {
					System.out.println(sentence[i] + " = Noun Plural");
					parsedSentence.add("NNS|" + sentence[i]);
					lexiconExist = true;

				}
			}

			for (int j = 0; j < VB.size(); j++) {
				if (sentence[i].equals(VB.get(j))) {
					System.out.println(sentence[i] + " = Verb");
					parsedSentence.add("VB|" + sentence[i]);
					lexiconExist = true;

				}
			}

			for (int j = 0; j < VBZ.size(); j++) {
				if (sentence[i].equals(VBZ.get(j))) {
					System.out.println(sentence[i] + " = Verb 3rd person perspective");
					parsedSentence.add("VBZ|" + sentence[i]);
					lexiconExist = true;

				}
			}

			for (int j = 0; j < JJ.size(); j++) {
				if (sentence[i].equals(JJ.get(j))) {
					System.out.println(sentence[i] + " = Adjective");
					parsedSentence.add("JJ|" + sentence[i]);
					lexiconExist = true;

				}
			}

			for (int j = 0; j < DT.size(); j++) {
				if (sentence[i].equals(DT.get(j))) {
					System.out.println(sentence[i] + " = Determiner");
					parsedSentence.add("DT|" + sentence[i]);
					lexiconExist = true;

				}
			}

			// If none of the lexicon type is found, adds an Error to the current word
			if (!lexiconExist) {
				parsedSentence.add("ERR|" + sentence[i]);
			}
		}

		return parsedSentence;
	}
}
