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

    public SCTNode(SCTNode sNode)
    {
        this.children = new ArrayList<>();
        this.symbolTable = new HashSet<>();
        this.parent = sNode;
        sNode.addChild(this);
    }//Ending copy constructor
    
    
    //Locates value which is associated to the declaration
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
        //ending if else statement 
    }
    //Method to add any new symbols to a SCTNode.(i.e. if they're not there. 
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
            }//Ending if condition statement
        }//ending if condition statement

        Node nodeToTest = tEntry.getAstNode().getChildren().get(0);
        if ((Operations.isOperation(nodeToTest) || nodeToTest.getKeyword().equals("id"))
                && findEntry(tEntry.getAstNode().getChildren().get(1)).equals(""))
        {
        	tEntry.setValue(Operations.doOperation(tEntry.getSctNode(), nodeToTest) + "");
        	tEntry.setType("float");
            symbolTable.add(tEntry);
        }//ending if condition statement
    }//ending addSysmbol method

    //Updates after finding a current extant table entry in SCTNode or that of a parent
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
            }//Ending nested if condition statement
        }//ending for loop statement

        if (this.parent != null)
            return this.parent.findAndUpdateEntry(aNode, data);
        else
        {
            return false;
        }
        //Ending if else statement 
    }//ending  find and update entry function

    @Override 
    public String toString()
    {
        String value = "Sct Node: " +
                "ID:  " + System.identityHashCode(this) +
                ", Symbol Table:" + symbolTable +
                '}';
        return value;
    }
  //Setters and getters for class

    public ArrayList<SCTNode> getChildren()
    {
        return children;
    }

    public void addChild(SCTNode currentNode)
    {
        this.children.add(currentNode);
    }
    public SCTNode getParent()
    {
        return parent;
    }

    public void setParent(SCTNode currentParent) 
    {
        this.parent = currentParent;
    }
    public HashSet<TableEntry> getSymbolTable()
    {
        return symbolTable;
    }

    public void setSymbolTable(HashSet<TableEntry> symTable) 
    {
        this.symbolTable = symTable;
    }

}//Ending SCTNode class
