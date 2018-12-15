//Andre Barajas
//CS 444
// Fall 2018
// A6 Parser program SCTNod eclass 
package runner.sct;
//loading needed libraries 
import parser.pst.Node;
import runner.Operations;

import java.util.ArrayList;
import java.util.HashSet;

public class SCTNode 
{

    private ArrayList<SCTNode> children;
    private SCTNode parent;
    private HashSet<TableEntry> symbolTable;//SCTNode entries

    public SCTNode() 
    {
        this.children = new ArrayList<>();
        this.symbolTable = new HashSet<>();
    }//ending SCTNode default constructor

    /**
     * 
     * @param sNode
     */
    public SCTNode(SCTNode sNode)
    {
        this.children = new ArrayList<>();
        this.symbolTable = new HashSet<>();
        this.parent = sNode;
        sNode.addChild(this);
    }//Ending copy constructor
    
    
    /**
     * Locates value which is associated to the declaration
     * @param aNode
     * @return
     */
    public String findEntry(Node aNode)
    {
        if (aNode == null)
            return "";

        // if they're passing in a number already, why find it...
        if (aNode.getKeyword().equals("int") || aNode.getKeyword().equals("float"))
            return aNode.getValue();

        for (TableEntry entry : symbolTable) 
        {
            // Find the matching value
            if (entry.getId().equals(aNode.getValue())) 
            {
                return entry.getValue();
            }//Ending nested if condition statement
        }//ending for loop statement

        // if we can't find it here, try to find it in the parent
        if (parent != null)
            return this.parent.findEntry(aNode);
        else
            return "";
    }
   
    
    /**
     * Method to add any new symbols to a SCTNode.(i.e. if they're not there. 
     * @param tEntry
     */
    public void addSymbol(TableEntry tEntry) 
    {
        if (tEntry == null)
            return;

        // straight up declaration w/ no operations
        if (tEntry.getType().equals("int") ||
        		tEntry.getType().equals("float") ||
        		tEntry.getType().equals("string")) 
        {
            if (!symbolTable.contains(tEntry)) 
            {
                symbolTable.add(tEntry);
            }
        }

        Node nodeToTest = tEntry.getAstNode().getChildren().get(0);
        if ((Operations.isOperation(nodeToTest) || nodeToTest.getKeyword().equals("id"))
                && findEntry(tEntry.getAstNode().getChildren().get(1)).equals(""))
        {
        	tEntry.setValue(Operations.doOperation(tEntry.getSctNode(), nodeToTest) + "");
        	tEntry.setType("float");
            symbolTable.add(tEntry);
        }
    }

   
    /**
     * Updates after finding a current extant table entry in SCTNode or that of a parent
     * @param aNode
     * @param data
     * @return
     */
    public boolean findAndUpdateEntry(Node aNode, String data) 
    {
        if (aNode == null)
            return false;
        //ending if condition statement

        for (TableEntry entry : symbolTable)
        {
            if (entry.getId().equals(aNode.getValue())) 
            {
                entry.setValue(data);
                return true;
            }
        }

        if (this.parent != null)
            return this.parent.findAndUpdateEntry(aNode, data);
        else
        {
            return false;
        }
    }

    /**
     * 
     */
    @Override 
    public String toString()
    {
        String value = "Sct Node: " +
                "ID:  " + System.identityHashCode(this) +
                ", Symbol Table:" + symbolTable +
                '}';
        return value;
    }

    /**
     * 
     * @return
     */
    public ArrayList<SCTNode> getChildren()
    {
        return children;
    }

    /**
     * 
     * @param currentNode
     */
    public void addChild(SCTNode currentNode)
    {
        this.children.add(currentNode);
    }
    
    /**
     * 
     * @return
     */
    public SCTNode getParent()
    {
        return parent;
    }
    
    /**
     * 
     * @param currentParent
     */
    public void setParent(SCTNode currentParent) 
    {
        this.parent = currentParent;
    }
    
    /**
     * 
     * @return
     */
    public HashSet<TableEntry> getSymbolTable()
    {
        return symbolTable;
    }

    /**
     * 
     * @param symTable
     */
    public void setSymbolTable(HashSet<TableEntry> symTable) 
    {
        this.symbolTable = symTable;
    }

}//Ending SCTNode class
