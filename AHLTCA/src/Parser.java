import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	
	String[] prevLexicon = {"null", "null"}; 
	
	ArrayList<String> sentence, posRules;
	ArrayList<String[]> rules;

	/**
	 * Constructor to takes in the arraylist from main method and reads in the rules.txt to 
	 * @param sentence - ArrayList<String>
	 * @throws IOException
	 */
	public Parser(ArrayList<String> sentence) throws IOException {
		this.sentence = sentence;
		
		posRules = new ArrayList<String>();
		rules = new ArrayList<String[]>();

		// Reads in the POS and rules from the textfile
		FileReader fr = null;
		try {
			fr = new FileReader("rules.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		
		//Reads first line for POS rules and set it in the variable the ArrayList posRules
		String line = br.readLine();
		String[] lineSplit = line.split("\\s");
		for (int i = 0; i < lineSplit.length; i++) {
			posRules.add(lineSplit[i]);
		}
		
		// Adds each rules in an ArrayList rules
		while ((line = br.readLine()) != null) {
			lineSplit = line.split("\\s");
			rules.add(lineSplit);
		}
		
	}

	/**
	 * Parses in sentence and builds a bracket sentence
	 * @return string - Bracket Sentence
	 */
	public String parse() {
		boolean ruleExist;
		String string = "[S";

		// Loops through the sentence provided
		for (int i = 0; i < sentence.size(); i++) {
			ruleExist = false;
			
			// Splits the rules and the word
			String[] lexicon = sentence.get(i).split("\\|");

			// Loops through the POSRules and checks for each word
			for (int j = 0; j < posRules.size(); j++) {
				if (lexicon[0].equals(posRules.get(j))) {
					ruleExist = true;
				} 
			}
			
			if (!ruleExist) {
				return "Word does not exist: "+ lexicon[1];
			}
			
			if (lexicon[0].equals("NN") || lexicon[0].equals("NNS")) { // if current lexicon is a Noun
				
				if (i > 0) {
					if (!sentence.get(i-1).equals(null) )  {
						prevLexicon = sentence.get(i - 1).split("\\|");
					} 
				} else {
					prevLexicon = null;
				}
				
				if (prevLexicon[0].equals("DT")) { // if previous was a Determiner

					string = string + "[NP[" + prevLexicon[0] + " " + prevLexicon[1] + "]"
							+ "[" + lexicon[0] + " "+ lexicon[1] + "]]";
				} else if (prevLexicon[0].equals("JJ")){ // if previous was Adjective
					String[] prevPrevLexicon = sentence.get(i - 2).split("\\|");

					string = string + "[NP[AP[" + prevPrevLexicon[0] + " " + prevPrevLexicon[1] + "]"
							+ "[" + prevLexicon[0] + " " + prevLexicon[1] + "]]"
							+ "[NP[" + lexicon[0] + " "+ lexicon[1] + "]]]";
				} else { // if there is no previous
					string = string + "[NP[" + lexicon[0] + " "+ lexicon[1] + "]]";
				}
			} else if (lexicon[0].equals("VB")) { // if current lexicon is a Verb
				string = string + "[VP[VB "+ lexicon[1] + "]";
			} else if (lexicon[0].equals("VBZ")) {
				string = string + "[VP[VBZ "+ lexicon[1] + "]";
			}
		}

		string = string + "]]";

		return string;
	}
}
