//Andre Barajas
//CS 444
//Fall 2018
//Parser extensions 
//loading needed libraries
package runner.sct;

import parser.pst.Node;

import java.util.ArrayList;

public class SCTBuilder 
{

	//method to create a scope tree from any given ASTNode
    public SCTNode buildScopeTree(Node aRootNode) 
    {
        SCTNode sctNode = new SCTNode();
        if (aRootNode == null)
            return sctNode;
        //ending if condition statement
        return buildHelper(sctNode, aRootNode);
    }//ending SCTnode build scope tree methodS
  //method to link and create a new sct Node/s
    private SCTNode handleBlock(SCTNode sNode, Node aNode) 
    {
        SCTNode sCTChild = new SCTNode(sNode);
        aNode.setSctNode(sCTChild);
        ArrayList<Node> children = aNode.getChildren();
        for (Node child : children) 
        {
            buildHelper(sCTChild, child);
        }//Ending for loop statement

        return sCTChild;
    }//Ending handleBlock statment
  
    //Function used recursively to walk tree and edit declaration(add) any new sctNodes needed
    private SCTNode buildHelper(SCTNode sNode, Node aNode) 
    {
        if (aNode == null)
            return sNode;

        if (isBlock(aNode))
        {
        	sNode = handleBlock(sNode, aNode);
        } else if (isDeclaration(aNode))
        {
            handleDeclaration(sNode, aNode);
        } else if (isUse(aNode)) 
        {
            handleUse(sNode, aNode);
        } else 
        {
            ArrayList<Node> children = aNode.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) 
            {
                buildHelper(sNode, children.get(i));
            }//ending nested for loop statement
        }//ending if else condition statement
        return sNode;
    }//ending buildHelper method 
    //function to check if a given node is a block or not 
    protected boolean isBlock(Node aNode)
    {
        return aNode != null && aNode.getKeyword().equals("brace1");
    }//ending mis Blockm method 

    
    //function to check if an ast node  is of any use 
    protected boolean isUse(Node aNode) 
    {
        return aNode != null &&
        		aNode.getKeyword().equals("id") &&
                !aNode.getParent().getKeyword().equals("equal");
    }//ending inUse method 
    //method to check if a given ast node is a declaration 
    protected boolean isDeclaration(Node aNode) 
    {
        	return aNode != null &&
        			aNode.getKeyword().equals("equal") &&
        			aNode.getChildren().get(1).getKeyword().equals("id");
    }//ending isDeclaration method
    //function to create new entries in a scope of a tree for a given astNode at a level 
    private void handleDeclaration(SCTNode sNode, Node aNode) 
    {
        // This will be a decl, so grab the acc
        TableEntry entry = new TableEntry(sNode, aNode);
        sNode.addSymbol(entry);
    }//Ending handleDeclaration
    //function to locate a scope of a tree active 
    private void handleUse(SCTNode sNode, Node aNode) 
    {
        if (sNode == null || aNode == null)
            return;
        //ending if condition statement
        sNode.findEntry(aNode);
    }//ending handleUse function 
   
}//ending SCTBuilder function
