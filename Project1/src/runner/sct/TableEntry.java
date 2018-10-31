//Andre Barajas
//CS 444
//Fall 2018
//Parser program 
package runner.sct;
//Loading needed libraries
import parser.pst.Node;

import java.util.ArrayList;

public class TableEntry
{

	 private String id;
	 private String value;
	 private String type;
    // SCTNode link
    private SCTNode sctNode;
    // ASTNode entry link
    private Node astNode;
   
    
    public TableEntry(SCTNode sNode, Node aNode) 
    {
    	this.sctNode = sNode;
        this.astNode = aNode;
        
        ArrayList<Node> children = aNode.getChildren();
        this.type = children.get(0).getKeyword();

        if (this.type.equals("id")) 
        {
            this.value = sNode.findEntry(aNode);
        } else 
        {
            this.value = children.get(0).getValue();
        }//Ending if else condition statement

        this.id = children.get(1).getValue();
    }//Ending Table Entry overloaded constructor

    //Getters and Setter declerations 
    public String getId() 
    {
        return id;
    }

    public void setId(String iD) 
    {
        this.id = iD;
    }
    public Node getAstNode() 
    {
        return astNode;
    }

    public void setAstNode(Node aNode) 
    {
        this.astNode = aNode;
    }
    public SCTNode getSctNode() 
    {
        return sctNode;
    }

    public void setSctNode(SCTNode sNode) 
    {
        this.sctNode = sNode;
    }

    

    public String getType() 
    {
        return type;
    }

    public void setType(String paradigm) 
    {
        this.type = paradigm;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String data) 
    {
        this.value = data;
    }
//------------------------------ --------------- -----------------

    @Override
    public String toString() 
    {
        return "Table Entry at {" +
                "ID: '" + id + '\'' +
                ", Type: '" + type + '\'' +
                ", Value: '" + value + '\'' +
                '}';
    }//ending toString method


    @Override
    public boolean equals(Object target) 
    {
        if (this == target) return true;
        //ending if condition statement
        if (target == null || getClass() != target.getClass()) return false;
        //ending if condition statement

        TableEntry that = (TableEntry) target;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        //ending teritary condition statement
        return type != null ? type.equals(that.type) : that.type == null;
    }//Ending equals method 

    @Override
    public int hashCode() 
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }//ending hashCode function
}//Ending Table entry class
