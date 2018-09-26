package Project1.src;

//Andre Barajas
//CS 444 
//Fall 2018
 //Buffer Class to look ahead in  A4 Lexcon grammer input
//Buffer holds a limit of 1000 
//Libraries needed
import java.io.IOException;
import java.io.Reader;

public class Buffer extends java.io.BufferedReader
{
	//Constant buffer size of 1000 utilized in mark() function
	 private final int readAhead = 1000;
	 
	     //Creates a new BufferedReader using Reader instance
	    //parameter of java.io.Reader
	    public Buffer(Reader reader)
	    {
	        super(reader);
	    }//ending Buffer constructor
    
	    //Returns the next address and reads the next character     
	    public int peek() throws IOException 
	    {
	        mark(readAhead);
	        int input = read();
	        reset();

	        return input;
	    }//Ending peek function
}//Ending Buffer Class
