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
   
    /**
     * 
     * @param sNode
     * @param aNode
     */
    public TableEntry(SCTNode sNode, Node aNode) 
    {
    	this.sctNode = sNode;
        this.astNode = aNode;
        
        ArrayList<Node> children = aNode.getChildren();
        this.type = children.get(1).getKeyword();

        if (this.type.equals("id")) 
        {
            this.value = sNode.findEntry(aNode);
        } else 
        {
            this.value = children.get(1).getValue();
        }

        this.id = children.get(0).getValue();
    }

    /**
     * 
     * @return
     */
    public String getId() 
    {
        return id;
    }

    /**
     * 
     * @param iD
     */
    public void setId(String iD) 
    {
        this.id = iD;
    }
    
    /**
     * 
     * @return
     */
    public Node getAstNode() 
    {
        return astNode;
    }

    /**
     * 
     * @param aNode
     */
    public void setAstNode(Node aNode) 
    {
        this.astNode = aNode;
    }
    
    /**
     * 
     * @return
     */
    public SCTNode getSctNode() 
    {
        return sctNode;
    }

    /**
     * 
     * @param sNode
     */
    public void setSctNode(SCTNode sNode) 
    {
        this.sctNode = sNode;
    }

    /**
     * 
     * @return
     */
    public String getType() 
    {
        return type;
    }

    /**
     * 
     * @param paradigm
     */
    public void setType(String paradigm) 
    {
        this.type = paradigm;
    }

    /**
     * 
     * @return
     */
    public String getValue()
    {
        return value;
    }

    /**
     * 
     * @param data
     */
    public void setValue(String data) 
    {
        this.value = data;
    }
    
    /**
     * 
     */
    @Override
    public String toString() 
    {
        return "Table Entry at {" +
                "ID: '" + id + '\'' +
                ", Type: '" + type + '\'' +
                ", Value: '" + value + '\'' +
                '}';
    }

    /**
     * 
     */
    @Override
    public boolean equals(Object target) 
    {
        if (this == target) 
        	return true;

        if (target == null || getClass() != target.getClass()) 
        	return false;


        TableEntry that = (TableEntry) target;

        if (id != null ? !id.equals(that.id) : that.id != null) 
        	return false;
        
        return type != null ? type.equals(that.type) : that.type == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() 
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}//Ending Table entry class
