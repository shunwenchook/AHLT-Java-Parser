# AHLT-Java-Parser
A java program that takes in a string and parses the sentence into its lexicon type
1.	Program design
The program consists of 3 java classes and 2 text files. The java classes are the Lexicon, Parser and the main program with the main method, parserProgram. The text files are the lexicon.txt and the rules.txt.
Text Files
•	Lexicon.txt
 
Figure 1. lexicon.txt containing all the lexicons
The lexicon.txt contains all the possible lexicon for the input sentence provided in the input requirements:
The/a man/men/woman/women bite(s)/like(s) the green dog
The first word of a line is the lexicon type, while the rest of the line are the possible words that can be input to the program. The lexicon type are as follows:
NN = Noun Singular
NNS = Noun Plural
VB = Verb
VBZ = Verb 3rd Perspective (s)
DT = Determiner
JJ = Adjective

•	Rules.txt
 
Figure 2. rules.txt containing the POS and rules
The rules.txt contains the parts of speech in the first line and all the rules for the sentence based on the inputs provided. The part of speech contains as follows:
NN = Noun Singular
NNS = Noun Plural
VB = Verb
VBZ = Verb 3rd Perspective (s)
DT = Determiner
JJ = Adjective
S = Sentence
NP = Noun Phrase
VP = Verb Phrase
AP = Adjective Phrase

 
2.	Implementations
Java classes
•	Lexicon.java
The Lexicon.java consists of a constructor and a method called parseSentence(). The constructor takes in the user input from the main program and initializes ArrayLists with String types to store all the different lexicons.
 
Figure 3. Lexicon constructor
The constructor then reads in the textfile, lexicon.txt, to take in all the lexicon type and lexicons and stores it in the class using the different ArrayLists. The constructor does this by looping through all the lines in the textfile and checking for the lexicon type using a switch case. Each line is split using the split() method that separates all the different words with spaces between them. The switch case checks the first word of the sentence for the lexicon type and stores all the words after the first word.
 
Figure 4. ParseSentence() Method
The parseMethod() in the lexicon.java checks the user input with the lexicon.txt file and determines if the lexicon is accepted or rejected. The user input is looped though each word and checks with the lexicon ArrayLists if the lexicon exists from the text file. If the lexicon exists, the lexicon type and the word will be added to a new array, parsedSentence, that will be passed back to the main method. The new processed sentence will look as follows: 
Input: 	The man likes the green dog
Output: DT|the NN|man VBZ|likes DT|the JJ|green NN|dog
 
•	Parser.java
The Parser.java class takes in the parsed sentence from the Lexicon class and applies the parts of speech rules from the rules.txt files. 
 
Figure 5. Constructor for Parser
The constructor for parser takes in the parsed sentence from the lexicon class and reads in the rules.txt text file. The first line of the rules.txt is stored as the parts of speech rules containing all the possible lexicons and rules. While all the other lines are stored as rules for the sentence. 
 
Figure 6. Parse method in Parser class
When the parse method is called, the method processes the sentence formed from the Lexicon object. A for loop loops though the sentence and the word category are then compared with the parts of speech rules, returning either true or false. If the rules all exist, the program then continues to form a bracketed sentence. The first set of if statements decides if the current lexicon is a Noun or Verb. In the Noun if statement, another if statement is then presented to check if the previous lexicon is a determiner or a adjective. If the previous lexicon is a determiner a bracket structure is formed,
[NP[DT][NN]]
If an adjective was detected before the noun, a new variable will be introduced to find the previous of the previous’ lexicon and the program will assume it’s a determiner. The bracket structure will be as follows,
[NP[AP[DT][JJ]][NP[NN]]]
Once the whole sentence is processed, the bracket structure will be sent back to the main program to be printed to the console.
When the current lexicon is a verb, the bracket structure will output,
[VP[VB][NP]]
•	ParserProgram.java
 
Figure 7. ParserProgram contains the main method
The main method uses a Scanner class to read in the input from the user. The split method splits the sentence into an array of words. The array is then passed to the Lexicon class to parse the sentence into its lexicon types. Once the Lexicon class returns the parsedSentence, the main method passes the sentence to the Parser class to process the bracket structure.
 
3.	Problems faced and solutions
i.	Problem: Reading in the lexicons from the lexicon.txt
In the Lexicon class, the program needed to read in the text file, lexicon.txt. Each line of the lexicon text file is divided to 2 main parts, the lexicon type and the words of the lexicon type. A FileReader and BufferedReader was used to read in the text file. A while loop is then used to loop through every line in the text file. Next, a switch case is then used to store each lexicon type to an array list.
ii.	Problem: Checking user input with the Lexicon class
The method in the Lexicon class is used to compare and determine what lexicon based on the user input. A for loop is used to loop through the sentence from the use input. If statements for each lexicon type is used to detect the word type. Once the word type is found, it inserts the Lexicon type and the word in the parsedSentence Array list such as follows:
DT|the
The for loop eventually ends and the parsed sentence is sent back to the main method.
iii.	Problem: Reading in the POS and the rules for the sentences
To read in the POS and the rules for the sentences, a FileReader and BufferedReader was used to read in the rules.txt. The program reads the first line which is the parts of speech and stores it in an ArrayList. A while loop is then used to loop through the rest of the file to read in the rules for the sentence.
iv.	Problem: Applying the rules to the user input sentence – Partially Solved
Unfortunately, I was unable to apply all the rules in the rules.txt and had to use if and else statements to determine between the Noun and Verb phrases, the method then checks for the previous lexicon for an adjective or a determiner to apply additional rules such as AP -> DT JJ and NP -> DT NP. The program can read in the Parts of speech rules and check with the user input.
 
4.	Outcome and Results
 
Figure 8. Screenshot of outcome
When the program runs, it prompts the user with available lexicons and requests the user to type in an input for the program. In this case, the sentence “the green man likes the green dog” was used. The program receives the input and passes it to the Lexicon class and determines its word category and adds the word type to the sentence as follows,
DT|the JJ|green NN|man VBZ|likes DT|the JJ|green NN|dog
The main method then takes the result from the lexicon method to pass parsed sentence to the Parser class. In the parser class, the method loops through each lexicon and checks each word. The 2 main if statements determines if the current lexicon is a Noun or a Verb. If the current lexicon is a verb, the program then presents 2 more if statements determining if the previous lexicon is an adjective or a verb. The program then prints the bracketed structure of the sentence accordingly.
