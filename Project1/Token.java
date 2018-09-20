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
    private Grammer grammer;
    // line # where the token is located
    private int lineNumber;
   
    public Token(int lineNum, String value) 
    {
        this.lineNumber = lineNum;
        this.value = value;
        this.grammer = new Grammer(0, "");
    }//Ending Token Overloaded Constructor

    @Override
    public String toString() 
    {
        String valueGenerated = getValue();
        String numberGenerated = "";
        if (grammer.getId() == 3 || grammer.getId() == 4)
        	numberGenerated = " " + grammer.getKeyword() + "= " + valueGenerated;
        else if (grammer.getKeyword().equals("string"))
            valueGenerated = value.substring(1, value.length() - 1); // chop off the quotes
        //ending if else statement
        return String.format("(Tok:%3d line=%3d str= \"%s\"%s)", grammer.getId(), lineNumber, valueGenerated, numberGenerated);
    }//Ending toString method 
    //Setters and Getters for Instance Variables
    public Grammer getGrammer() 
    {
        return grammer;
    }//Ending GetGrammer Function

    public void setGrammer(Grammer grammer) 
    {
        this.grammer = grammer;
    }//Ending setGrammer method

    public String getValue() 
    {
        return value;
    }//Ending getGrammer method

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
