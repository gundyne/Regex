//Andre Barajas
//CS 444
//Fall 2018
//Parser extensions 

//loading needed libraries

package runner;

import parser.pst.Node;
import runner.sct.SCTNode;

import java.util.ArrayList;

public class Operations 
{
	/**
	 * 
	 * @param aNode
	 * @return
	 */
	public static boolean isOperation(Node aNode)
	{
	    if (aNode == null)
	        return false;
	    //Ending if condition statement
            String keyword = aNode.getKeyword();

            return keyword.equals("slash") ||
				keyword.equals("aster") ||
				keyword.equals("minus") ||
				keyword.equals("caret") ||
				keyword.equals("plus");		
	}
	
	/**
	 * 
	 * @param aNode
	 * @return
	 */
    private static boolean isIdentifier(Node aNode) 
    {
	    return aNode.getKeyword().equals("id");
    }//Ending isIdentifier method

    /**
     * 
     * @param aNode
     * @return
     */
    private static boolean isNumber(Node aNode) 
    {
	    String keyword = aNode.getKeyword();
	    return keyword.equals("int") ||
                keyword.equals("float");
    }

    /**
     * 
     * @param sNode
     * @param aNode
     * @return
     */
	public static double doOperation(SCTNode sNode, Node aNode) 
	{
	    if (sNode == null || aNode == null)
	        return 0;
	    //ending if condition statement
        if (isIdentifier(aNode))
        {
            String value = sNode.findEntry(aNode);
            if (!value.isEmpty())
                return Double.parseDouble(value);
            //ending nested if condition statement
        } //ending if condition statement
        if (isNumber(aNode))
	        return Double.parseDouble(aNode.getValue());
        //ending if condition statement
        if (aNode.getKeyword().equals("parens1"))
        	aNode = aNode.getChildren().get(1);

		if (!isOperation(aNode))
			return 0;

		String keyword = aNode.getKeyword();
		ArrayList<Node> bankOfChildren = aNode.getChildren();
	        double returnValue = 0;
	
	        switch(keyword) 
	        {
	        		case "caret":
			            double base = doOperation(sNode, bankOfChildren.get(1));
			            double exponent = doOperation(sNode, bankOfChildren.get(0));
			            returnValue = Math.pow(base, exponent);
		            	break;
		            case "minus":
		                for (Node child : bankOfChildren)
		                	returnValue = doOperation(sNode, child) - returnValue;
		                break;
		            case "plus":
					    for (Node child : bankOfChildren)
					    	returnValue += doOperation(sNode, child);
					    break;
		            case "aster" :
		            	returnValue = 1;
		                for (Node child: bankOfChildren)
		                	returnValue *= doOperation(sNode, child);
		                break;
		            case "slash":
		            	returnValue = 1;
		                for (Node child : bankOfChildren)
		                	returnValue = doOperation(sNode, child) / returnValue;
		                break;
			}

			return returnValue;
	}//Ending doOperation function	
}//EndingOperations class
