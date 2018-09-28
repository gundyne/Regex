package regex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dat
 */
public class Regex {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tokenizer tokTest = new Tokenizer(args[0]);
        String fileLocation = args[0].substring(0, args[0].lastIndexOf("\\")+1);
        ArrayList<Token> tokenList = tokTest.printTokens();
        try {
            try (Writer fileWriter = new FileWriter(new File(fileLocation+"output.txt"),false)) {
                for(Token t: tokenList)
                    fileWriter.write(t.toString()+'\n');
            }
        } catch (IOException ex) {
            System.err.println("IOException has occured");
        }
        
    }
}
