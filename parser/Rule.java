//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a5 LEXCON language
package parser;

//loading needed libraries
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Rule 
{

    private int id;
    private String lhs;
    private String rhs;

    public Rule(int iD, String leftHandSide, String rihtHandSide) 
    {
        this.id = iD;
        this.lhs = leftHandSide;
        this.rhs = rihtHandSide;
    }//Ending Rule class overloaded constructor 

    //Setters and getters for the instance variables
    

    public String[] getReversedRhsArray() 
    {
        List<String> bankOfElements = Arrays.asList(rhs.trim().split(" "));
        Collections.reverse(bankOfElements);

        return (String[]) bankOfElements.toArray();
    }
    public String getLhs() 
    {
        return lhs;
    }

    public void setLhs(String leftHandSide) 
    {
        this.lhs = leftHandSide;
    }
    public int getId() 
    {
        return id;
    }

    public void setId(int iD) 
    {
        this.id = iD;
    }

   
    public boolean isEpsilonRule() 
    {
        return rhs.equals("eps");
    }
    public String getRhs()
    {
        return rhs;
    }

    public void setRhs(String rightHandSide) 
    {
        this.rhs = rightHandSide;
    }
}//Ending Rule Class
