package Project1.src;

//Andre Barajas
//CS 444 
//Fall 2018
//Grammer Class to represent A4 Lexcon
//Grammer key word and ID provided by professor Siska

public class Grammar 
{
	//A4 ID lexcon */
    private int id;
    //A4 Keyword
    private String keyword;

    public Grammar(int id, String keyword)
    {
        this.id = id;
        this.keyword = keyword;

    }//Ending Grammer Constructor
    //Setters and Getters for instance variables
    public int getId() 
    {
        return id;
    }// Ending getId function
    public void setId(int id) 
    {
        this.id = id;
    }//Ending setId method
    public String getKeyword() 
    {
        return keyword;
    }//Ending getKeyword method
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }//ending setKeyword method
}
