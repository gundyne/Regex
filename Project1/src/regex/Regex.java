package regex;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Reader;
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
        String fileName = "D:\\Download\\Temp\\A4-sample-1.acod";
        Tokenizer tokTest = new Tokenizer(fileName);
        tokTest.printTokens();
    }
    
    public static void lineBreak(String text)
    {
        String[] lines = text.split("\n");
        for(int i = 0; i < lines.length; i++)
            lineRead(lines[i],i+1);
    }
    
    public static void lineRead(String line, int lineNumber)
    {
        String scannedWord = "";
        
    }
    
    public static int keywordCheck(String word)
    {
        String[] keywords = {"prog","main","fcn","class","float","int","string","if","elseif","else","while","input","print","new","return","var"};
        int[] tokenNum = {10,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26};
        for(int k=0;k<16;k++)
            if(word.equals(keywords[k]))
                return tokenNum[k];
        return 0;
    }
    
    public static int delimCheck(char scannedChar)
    {
        char[] delim = {',',';','<','>','{','}','[',']','(',')'};
        int[] tokenNum = {6,7,31,32,33,34,35,36,37,38};
        for(int d=0; d<10; d++)
            if(scannedChar == delim[d])
                return tokenNum[d];
        return 0;
    }
}
