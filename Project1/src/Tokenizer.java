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
    private static final char eol = System.lineSeparator().charAt(0);
    //HashMap container for A4 Lexcon ID & Definitions
    private static final HashMap<String, Grammer> lexcon = allocGramm2Lexcon();

    
    // Overloaded string(filename) constructor to create a tokenizer to read input 
    //from a file
    public Tokenizer(String fileName) 
    {
    	bankOftokens = new ArrayList<Token>();
        token = "";
        lineCount = 1;

        try 
        {
            file = new Buffer(new FileReader(fileName));
        } catch (FileNotFoundException e) 
        {
            System.out.println("Error: File Not Found.");

            Token currentToken = new Token(lineCount, "");
            currentToken.setGrammer(new Grammer(99, "error")); //reporting unknown token to A4 Lexcon
            bankOftokens.add(currentToken);
        }//Ending try catch statement 
    }//Ending Tokenzier overloaded constructor
//Function to allocate grammer rules with respective id's into a hashmap container
    //A4 Lexicon Rules grammer requirements were provided by prof. Siska
    private static HashMap<String, Grammer> allocGramm2Lexcon()
    {
        HashMap<String, Grammer> a4lexcon = new HashMap<String, Grammer>();

        // A4 Lexcon declarations 
        lexcon.put("id", new Grammer(2, "id"));
        lexcon.put("int", new Grammer(3, "int"));
        lexcon.put("float", new Grammer(4, "float"));
        lexcon.put("string", new Grammer(5, "string"));
        // A4 Lexcon Unpaired delimiters
        lexcon.put(",", new Grammer(6, "comma"));
        lexcon.put(";", new Grammer(7, "semi"));

        // A4 Lexcon Keywords
        lexcon.put("prog", new Grammer(10, "kprog"));
        lexcon.put("main", new Grammer(11, "kmain"));
        lexcon.put("fcn", new Grammer(12, "kfcn"));
        lexcon.put("class", new Grammer(13, "kclass"));
        lexcon.put("float", new Grammer(15, "kfloat"));
        lexcon.put("int", new Grammer(16, "kint"));
        lexcon.put("string", new Grammer(17, "kstring"));
        lexcon.put("if", new Grammer(18, "kif"));
        lexcon.put("elseif", new Grammer(19, "kelseif"));
        lexcon.put("else", new Grammer(20, "kelse"));
        lexcon.put("while", new Grammer(21, "kwhile"));
        lexcon.put("input", new Grammer(22, "kinput"));
        lexcon.put("print", new Grammer(23, "kprint"));
        lexcon.put("new", new Grammer(24, "knew"));
        lexcon.put("return", new Grammer(25, "kreturn"));
        lexcon.put("var", new Grammer(25, "kvar"));

        // A4 Lexcon paired delimiters
        lexcon.put("<", new Grammer(31, "angle1"));
        lexcon.put(">", new Grammer(32, "angle2"));
        lexcon.put("{", new Grammer(33, "brace1"));
        lexcon.put("}", new Grammer(34, "brace2"));
        lexcon.put("[", new Grammer(35, "bracket1"));
        lexcon.put("]", new Grammer(36, "bracket2"));
        lexcon.put("(", new Grammer(37, "parens1"));
        lexcon.put(")", new Grammer(38, "parens2"));

        // A4 Lexcon other punctuation tokens 
        lexcon.put("*", new Grammer(41, "aster"));
        lexcon.put("^", new Grammer(42, "caret"));
        lexcon.put(":", new Grammer(43, "colon"));
        lexcon.put(".", new Grammer(44, "dot"));
        lexcon.put("=", new Grammer(45, "equal"));
        lexcon.put("-", new Grammer(46, "minus"));
        lexcon.put("+", new Grammer(47, "plus"));
        lexcon.put("/", new Grammer(48, "slash"));

        // A4 Lexcon multi-char operators
        lexcon.put("->", new Grammer(51, "oparrow"));
        lexcon.put("==", new Grammer(52, "opeq"));
        lexcon.put("!=", new Grammer(53, "opne"));
        lexcon.put("<=", new Grammer(54, "ople"));
        lexcon.put(">=", new Grammer(55, "opge"));
        lexcon.put("<<", new Grammer(56, "opshl"));
        lexcon.put(">>", new Grammer(57, "opshr"));

        //A4 Lexcon Misc
        lexcon.put("", new Grammer(99, "error")); // error
        lexcon.put(" ", new Grammer(0, "eol")); // end of line input

        return a4lexcon;
    }//Ending allocGramm2Lexcon Funtion
}//Ending Tokenizer Class