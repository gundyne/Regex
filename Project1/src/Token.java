package Project1.src;

//Andre Barajas
//CS 444 
//Fall 2018
 //Token Class to represent A4 Lexcon single token
//Token will be utilized in Tokenizer Class for further processing

public class Token 
{
	 //Value associated with token in A4 Lexicon
    private String value;
    //Grammar which holds ID & keyword 
    private Grammar grammar;
    // line # where the token is located
    private int lineNumber;
   
    public Token(int lineNum, String value) 
    {
        this.lineNumber = lineNum;
        this.value = value;
        this.grammar = new Grammar(0, "");
    }//Ending Token Overloaded Constructor

    @Override
    public String toString() 
    {
        String valueGenerated = getValue();
        String numberGenerated = "";
        if (grammar.getId() == 3 || grammar.getId() == 4)
        	numberGenerated = " " + grammar.getKeyword() + "= " + valueGenerated;
        else if (grammar.getKeyword().equals("string"))
            valueGenerated = value.substring(1, value.length() - 1); // chop off the quotes
        //ending if else statement
        return String.format("(Tok:%3d line=%3d str= \"%s\"%s)", grammar.getId(), lineNumber, valueGenerated, numberGenerated);
    }//Ending toString method 
    //Setters and Getters for Instance Variables
    public Grammar getGrammar() 
    {
        return grammar;
    }//Ending GetGrammr Function

    public void setGrammar(Grammar grammar) 
    {
        this.grammar = grammar;
    }//Ending setGrammar method

    public String getValue() 
    {
        return value;
    }//Ending getGrammar method

    public void setValue(String value) 
    {
        this.value = value;
    }//Ending SetValue method

    public int getLineNum() 
    {
        return lineNumber;
    }//Ending get line number method

    public void setLineNum(int lineNum) 
    {
        this.lineNumber = lineNum;
    }//Ending set LineNumber method
}//Ending Token Class
