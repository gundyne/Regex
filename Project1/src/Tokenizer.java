package Project1.src;

//Andre Barajas
//CS 444 
//Fall 2018
 //Tokenizer Class to hold data associated with A4 Lexcon language
//Tokenizer specs was provided by professor Siska via "444-p1-lexer.pdf" assignment
//This class holds operations to process tokens for the A4 language
//I.e the class may read a file that has characters and creates tokens for end user

//Importing Libraries needed. 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public class Tokenizer 
{
//Declaring instance variables
	 //Token string used in order to produce tokens 
    private String token;
    //Buffered object created in order to parse the file in some functions
    private Buffer file;
    //Int counter for  lines 
    private int lineCount;
    //container for tokens in system
    private ArrayList<Token> bankOftokens;
    //End of line char that is specific  to java 
    private static final char eof = System.lineSeparator().charAt(0);
    //HashMap container for A4 Lexcon ID & Definitions
    private static final HashMap<String, Grammar> lexcon = allocGramm2Lexcon();

    
    // Overloaded string(filename) constructor to create a tokenizer to read input 
    //from a file
    public Tokenizer(String fileName) 
    {
    	bankOftokens = new ArrayList<>();
        token = "";
        lineCount = 1;

        try 
        {
            file = new Buffer(new FileReader(fileName));
        } catch (FileNotFoundException e) 
        {
            System.out.println("Error: File Not Found.");

            Token currentToken = new Token(lineCount, "");
            currentToken.setGrammar(new Grammar(99, "error")); //reporting unknown token to A4 Lexcon
            bankOftokens.add(currentToken);
        }//Ending try catch statement 
    }//Ending Tokenzier overloaded constructor
//Function to allocate grammer rules with respective id's into a hashmap container
    //A4 Lexicon Rules grammer requirements were provided by prof. Siska
    private static HashMap<String, Grammar> allocGramm2Lexcon()
    {
        HashMap<String, Grammar> a4lexcon = new HashMap<>();

        // A4 Lexcon declarations 
        a4lexcon.put("^[A-Za-z_][A-Za-z0-9_]*$", new Grammar(2, "id"));
        a4lexcon.put("^[0-9]*$", new Grammar(3, "int"));
        a4lexcon.put("^[+-]?([0-9]*[.])?[0-9]+*$", new Grammar(4, "float"));
        a4lexcon.put("\".*\"", new Grammar(5, "string")); //"\\s\"(.*?)\"\\s"
        
        // A4 Lexcon Unpaired delimiters
        a4lexcon.put(",", new Grammar(6, "comma"));
        a4lexcon.put(";", new Grammar(7, "semi"));

        // A4 Lexcon Keywords
        a4lexcon.put("prog", new Grammar(10, "kprog"));
        a4lexcon.put("main", new Grammar(11, "kmain"));
        a4lexcon.put("fcn", new Grammar(12, "kfcn"));
        a4lexcon.put("class", new Grammar(13, "kclass"));
        a4lexcon.put("float", new Grammar(15, "kfloat"));
        a4lexcon.put("int", new Grammar(16, "kint"));
        a4lexcon.put("string", new Grammar(17, "kstring"));
        a4lexcon.put("if", new Grammar(18, "kif"));
        a4lexcon.put("elseif", new Grammar(19, "kelseif"));
        a4lexcon.put("else", new Grammar(20, "kelse"));
        a4lexcon.put("while", new Grammar(21, "kwhile"));
        a4lexcon.put("input", new Grammar(22, "kinput"));
        a4lexcon.put("print", new Grammar(23, "kprint"));
        a4lexcon.put("new", new Grammar(24, "knew"));
        a4lexcon.put("return", new Grammar(25, "kreturn"));
        a4lexcon.put("var", new Grammar(25, "kvar"));

        // A4 Lexcon paired delimiters
        a4lexcon.put("<", new Grammar(31, "angle1"));
        a4lexcon.put(">", new Grammar(32, "angle2"));
        a4lexcon.put("{", new Grammar(33, "brace1"));
        a4lexcon.put("}", new Grammar(34, "brace2"));
        a4lexcon.put("[", new Grammar(35, "bracket1"));
        a4lexcon.put("]", new Grammar(36, "bracket2"));
        a4lexcon.put("(", new Grammar(37, "parens1"));
        a4lexcon.put(")", new Grammar(38, "parens2"));

        // A4 Lexcon other punctuation tokens 
        a4lexcon.put("*", new Grammar(41, "aster"));
        a4lexcon.put("^", new Grammar(42, "caret"));
        a4lexcon.put(":", new Grammar(43, "colon"));
        a4lexcon.put(".", new Grammar(44, "dot"));
        a4lexcon.put("=", new Grammar(45, "equal"));
        a4lexcon.put("-", new Grammar(46, "minus"));
        a4lexcon.put("+", new Grammar(47, "plus"));
        a4lexcon.put("/", new Grammar(48, "slash"));

        // A4 Lexcon multi-char operators
        a4lexcon.put("->", new Grammar(51, "oparrow"));
        a4lexcon.put("==", new Grammar(52, "opeq"));
        a4lexcon.put("!=", new Grammar(53, "opne"));
        a4lexcon.put("<=", new Grammar(54, "ople"));
        a4lexcon.put(">=", new Grammar(55, "opge"));
        a4lexcon.put("<<", new Grammar(56, "opshl"));
        a4lexcon.put(">>", new Grammar(57, "opshr"));

        //A4 Lexcon Misc
        a4lexcon.put("", new Grammar(99, "error")); // error
        a4lexcon.put(" ", new Grammar(0, "eof")); // end of file input

        return a4lexcon;
    }//Ending allocGramm2Lexcon Funtion
}//Ending Tokenizer Class