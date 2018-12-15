//Andre Barajas
//CS 444
//Fall 2018
//Parser extensions 

//loading needed libraries

package runner;

import parser.pst.Node;
import runner.sct.SCTBuilder;
import runner.sct.SCTNode;

import java.util.ArrayList;
import runner.sct.TableEntry;

public class ASTRunner extends SCTBuilder 
{

    private Node astRoot;
    private SCTNode sctRoot;

    
    public ASTRunner(Node aRootNode)
    {
        this.astRoot = aRootNode;
        this.sctRoot = buildScopeTree(aRootNode);
    }//Ending overloaded constructor 

   //run function for use 
    public void run() 
    {
        run(sctRoot, astRoot);
        printScopeTree();
    }//Ending run function

   
    //Function to Access AST and run recursively 
    private void run(SCTNode sRootNode, Node aRootNode) 
    {
        if (isBlock(aRootNode))
        	sRootNode = aRootNode.getSctNode();
        //ending if condition statement
        if (isUse(aRootNode)) 
        {
        	 String value = sRootNode.findEntry(aRootNode);
        	 sRootNode.findAndUpdateEntry(aRootNode, value);
            
        } else if (isDeclaration(aRootNode)) 
        {
           
         // Observe case that may find the entry and update 
            double value = Operations.doOperation(sRootNode, aRootNode.getChildren().get(1));
            if(sRootNode.findEntry(aRootNode).equals(""))
                sRootNode.addSymbol(new TableEntry(sRootNode,aRootNode));
            else
                sRootNode.findAndUpdateEntry(aRootNode.getChildren().get(0), value + "");
        } else 
        {
            if (isPrint(aRootNode))
                handlePrint(sRootNode, aRootNode);
            if (isWhile(aRootNode)) 
            {
                handleWhile(sRootNode, aRootNode);
                return; // don't handle the kids
            }//ending nested if condition statement
        }//ending if else condition statement
        ArrayList<Node> children = aRootNode.getChildren();
        for (int i = children.size() - 1; i >= 0; --i)
            run(sRootNode, children.get(i));
    }//Ending run function 

    private boolean isPrint(Node aNode)
    {
        if (aNode == null)
            return false;
        //Ending if condition statement
        return aNode.getKeyword().equals("kprint");
    }//Ending isPring method

    private void handlePrint(SCTNode sNode, Node aRootNode)
    {
        ArrayList<Node> children = aRootNode.getChildren();
        Node nodeToPrint = children.get(0);
        System.out.print(nodeToPrint.getValue().replaceAll("^\"|\"$", ""));
        printArgs(sNode, nodeToPrint);
        System.out.println(""); 
    }//Ending handlePrint method 
    //employed in recursions where scope of a tree is present
    private void printArgs(SCTNode sNode, Node aNode)
    {
        ArrayList<Node> children = aNode.getChildren();
        if (children == null || children.isEmpty() || !children.get(0).getKeyword().equals("comma"))
            return;
        //Ending if condition statement
        Node nodeToOperate = children.get(0).getChildren().get(0);
        if (nodeToOperate.getKeyword().equals("string"))
            System.out.print(nodeToOperate.getValue().replaceAll("^\"|\"$", ""));
        else
            System.out.print(Operations.doOperation(sNode, nodeToOperate));
        //ending if else condition statement
        printArgs(sNode, nodeToOperate);
    }//Ending printArgs method

    private boolean isWhile(Node aNode) 
    {
        if (aNode == null)
            return false;
        //ending if condition statement
        return aNode.getKeyword().equals("kwhile");
    }//ending isWhile method
    private void handleWhile(SCTNode sNode, Node aNode) 
    {
        Node condition = aNode.getChildren().get(1);
        Node body = aNode.getChildren().get(0);
        while (evaluate(sNode, condition))
        {
            run(sNode, body);
        }//ending while loop statement
    }//Ending handlWhile function

    private boolean evaluate(SCTNode sNode, Node aNode)
    {
        String left = sNode.findEntry(aNode.getChildren().get(1));
        Node condition = aNode.getChildren().get(1).getChildren().get(0);
        String right = sNode.findEntry(condition.getChildren().get(0));

        double leftArg = Double.parseDouble(left); // get the numbers
        double rightArg = Double.parseDouble(right);

        String operator = condition.getKeyword();
        switch(operator) 
        {
            
            case "opne":
                return leftArg != rightArg;
            case "opeq":
                return leftArg == rightArg;
            case "ople":
                return leftArg <= rightArg;
            case "angle1":
                return leftArg < rightArg;
            case "opge":
                return leftArg >= rightArg;
            case "angle2":
                return leftArg > rightArg;
            default:
                return false;
        }//ending switch case statement
    }//ending evaluate function
    //Pre order approach to printing tree recursively
    public void printScopeTree() 
    {
        printTree(sctRoot, 0);
    }//ending printScopeTree method

    //Pre order print: recursive
    public static void printTree(SCTNode rootNode, int currentLevel) 
    {
        if (rootNode == null)
            return;
        //ending  if condition statement
        String tabbing = getSpacing(currentLevel);
        System.out.println(tabbing + "(" + rootNode + ")");

        currentLevel++;
        ArrayList<SCTNode> children = rootNode.getChildren();
        for (SCTNode child : children)
        {
            printTree(child, currentLevel);
        }//ending for loop statement
    }//ending printTree function
    private static String getSpacing(int currentLevel)
    {
        String data = "";
        for (int i = 0; i < currentLevel; i++)
        	data = data + "  ";
        return data;
    }//ending getSpacing method
}//ending ASTRunner function
