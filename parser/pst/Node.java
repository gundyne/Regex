//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a6 LEXCON language
package parser.pst;
//loading needed libraries 
import runner.sct.SCTNode;

import java.util.ArrayList;

public class Node 
{
    //SCTNode Link
    private SCTNode sctNode;
    private Node parent;
    private String keyword;
    private int hashId;
    private String value;
   
    private ArrayList<Node> children;
  

    public Node(String key) 
    {
        this(key, "");
    }//Ending Node overloaded constructor 

    public Node(String key, String data) 
    {
        this.keyword = key;
        this.value = data;
        children = new ArrayList<Node>();
        this.hashId = System.identityHashCode(this);
    }//Ending Node overloaded constructor

    
    public void addChild(Node childNode) 
    {
        children.add(childNode);
        childNode.setParent(this);
    }//Ending add child function 

    
    public void addChildren(ArrayList<Node> bankOfChildrenNodes) 
    {
        if (bankOfChildrenNodes == null)
            return;
        for (Node child : bankOfChildrenNodes)
            addChild(child);
    }//Ending add children function 

    //method to remove existing childs that the node has and replaces them with another child where possible
    public void replaceChildren(ArrayList<Node> bankOfChildrenNodes) {
        if (bankOfChildrenNodes == null)
            return;

        children.clear();
        for (Node child : bankOfChildrenNodes)
            addChild(child);
    }

   
    //Epsilon rule method to figure out if the rule in node is in epsilon rule
    public boolean isEpsilonRule()
    {
        return Character.isUpperCase(this.keyword.charAt(0)) && children.isEmpty();
    }//ending epsilonRule

   
    //Method used i toString to check value in node and print it if necesary
    //i.e.  values such as ints,strings floats an id's are valid false otherswise
    private boolean isValueOkayToPrint()
    {
        return !value.isEmpty() &&
                keyword.equals("id") || keyword.equals("int") ||
                keyword.equals("float") || keyword.equals("string");
    }
    //----Getters and Setters ----------------------
    public SCTNode getSctNode()
    {
        return sctNode;
    }

    public void setSctNode(SCTNode sNode) 
    {
        this.sctNode = sNode;
    }

    public String getKeyword() 
    {
        return keyword;
    }

    public String getValue() 
    {
        return value;
    }

    //function to set value of node but technically if the associated keyword of node is int float str or id we end up keeping 
    //values of other values
    public void setValue(String data) 
    {
        this.value = data;
    }

    public Node getParent() 
    {
        return parent;
    }

    public void setParent(Node currentParentNode) 
    {
        this.parent = currentParentNode;
    }

    public ArrayList<Node> getChildren()
    {
        return children;
    }


    @Override
    public String toString() 
    {
        String data = "Node: " +
                "id: '" + hashId + '\'' +
                ", keyword: '" + keyword + '\'';

        if (isValueOkayToPrint()) 
        {
        	data = data + ", value: '" + value + '\'';
        }

        data = data + "}";
        return data;
    }

}
