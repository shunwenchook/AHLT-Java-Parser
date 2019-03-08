import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class parserProgram {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);
		System.out.println("The/a man/men/woman/women bite(s)/like(s) the green dog");
		System.out.println("Please input a sentence from the available lexicons above:");

		// Takes in input from user and stores it in a String array called words
		String sentence = scan.nextLine();

		String[] words = sentence.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");
		}

		// Calls Lexicon class and passes the user input to the class
		Lexicon lexiconParser = new Lexicon(words);
		ArrayList<String> parsedSentence = lexiconParser.parseSentence();

		// Prints the output from the Lexicon class
		for (int i = 0; i < parsedSentence.size(); i++) {
			System.out.print(parsedSentence.get(i) + " ");
		}
		
		System.out.println();
		
		// Passes the sentence with the Lexicon types to the Parser class
		Parser parser = new Parser(parsedSentence);
		System.out.println(parser.parse());
	}

}
