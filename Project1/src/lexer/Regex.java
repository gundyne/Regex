package lexer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author Dat Doan
 * @email dathuydoan@gmail.com
 * This class is the main class of the project and runs first
 */
public class Regex {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creating tokenizer to handle tokens found from the file
        Tokenizer tokTest = new Tokenizer(args[0]);
        // Determine where the file being opened is stored at
        String fileLocation = args[0].substring(0, args[0].lastIndexOf("\\")+1);
        ArrayList<Token> tokenList = tokTest.printTokens();
        try {
            try (Writer fileWriter = new FileWriter(new File(fileLocation+"output.txt"),false)) {
                //Writing tokens to output.txt
                for(Token t: tokenList)
                    fileWriter.write(t.toString()+'\n');
            }
        } catch (IOException ex) {
            System.err.println("IOException has occured");
        }
        
    }
}
