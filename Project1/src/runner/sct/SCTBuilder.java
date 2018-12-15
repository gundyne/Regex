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
	/**
	 * create a scope tree from any given astnode
	 * @param aRootNode
	 * @return
	 */
    public static SCTNode buildScopeTree(Node aRootNode) 
    {
        SCTNode sctNode = new SCTNode();
        if (aRootNode == null)
            return sctNode;
        //ending if condition statement
        return buildHelper(sctNode, aRootNode);
    }//ending SCTnode build scope tree methodS

    /**
     * 
     * @param sNode
     * @param aNode
     * @return
     */
    private static SCTNode handleBlock(SCTNode sNode, Node aNode) 
    {
        SCTNode sCTChild = new SCTNode(sNode);
        aNode.setSctNode(sCTChild);
        ArrayList<Node> children = aNode.getChildren();
        for (Node child : children) 
        {
            buildHelper(sCTChild, child);
        }//Ending for loop statement

        return sCTChild;
    }
  
    /**
     * function used recursively to walk tree and edit declaration (add) any new sctnodes needed
     * @param sNode
     * @param aNode
     * @return
     */
    private static SCTNode buildHelper(SCTNode sNode, Node aNode) 
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
    }
    
    /**
     * check if given node is a block or not
     * @param aNode
     * @return
     */
    protected static boolean isBlock(Node aNode)
    {
        return aNode != null && aNode.getKeyword().equals("brace1");
    }
    
    /**
     * cjeck if an ast node is use
     * @param aNode
     * @return
     */
    protected static boolean isUse(Node aNode) 
    {
        return aNode != null &&
        		aNode.getKeyword().equals("id") &&
                !aNode.getParent().getKeyword().equals("equal");
    }
    
    /**
     * check if given ast node is a declaration
     * @param aNode
     * @return
     */
    protected static boolean isDeclaration(Node aNode) 
    {
        	return aNode != null &&
        			aNode.getKeyword().equals("equal") &&
        			aNode.getChildren().get(0).getKeyword().equals("id");
    }

    /**
     * create new entries in a scope of a tree for a given astNode at a level 
     * @param sNode
     * @param aNode
     */
    private static void handleDeclaration(SCTNode sNode, Node aNode) 
    {
        // This will be a decl, so grab the acc
        TableEntry entry = new TableEntry(sNode, aNode);
        sNode.addSymbol(entry);
    }

    /**
     * locate scope of tree active
     * @param sNode
     * @param aNode
     */
    private static void handleUse(SCTNode sNode, Node aNode) 
    {
        if (sNode == null || aNode == null)
            return;
        //ending if condition statement
        sNode.findEntry(aNode);
    }//ending handleUse function 
   
}//ending SCTBuilder function
