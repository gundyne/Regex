//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a5 LEXCON language
package parser.pst;

import java.util.ArrayList;

public class ASTConverter 
{


    public static Node convert(Node rootNode) 
    {
        if (rootNode != null) 
        {
            ArrayList<Node> bankOfChildren = rootNode.getChildren();
            for (int i = 0; i < bankOfChildren.size(); i++) 
            {
                Node fixedChild = convert(bankOfChildren.get(i));
                // remove unnecessary rules that may exist after.
                fixedChild.getChildren().removeIf(Node::isEpsilonRule);
                fixedChild = fixExtra(fixedChild);
                bankOfChildren.set(i, fixedChild);
            }//ending nested for loop statement
        }//ending if condition statement
        return fix(rootNode);
    }//ending convert method
    //function to hoist the child according to the type of node.
    private static Node fix(Node pNode) 
    {
        Node hoistOp = pNode;
        if (pNode != null) 
        {
            String nodeParadigm = pNode.getKeyword();
            ArrayList<Node> bankOfChildren = pNode.getChildren();
            boolean integrate = true;

            if (!bankOfChildren.isEmpty()) 
            {
                switch (nodeParadigm) 
                {
                    case "Pgm":
                    	hoistOp = bankOfChildren.remove(3);
                        break;
                    case "Main":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "BBlock":
                        bankOfChildren.remove(0);
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "Vargroup":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "PPvarlist":
                        bankOfChildren.remove(0);
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "Varlist":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "Vardecl":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "Basekind":
                    	hoistOp = bankOfChildren.remove(0);
                        break;
                    case "Varid":
                    	hoistOp = bankOfChildren.remove(0);
                        break;
                    case "Stmts":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "Stmt":
                    	hoistOp = bankOfChildren.remove(0);
                        break;
                    case "Stasgn":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "Stprint":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "Stwhile":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "PPexprs":
                    	hoistOp = bankOfChildren.remove(0);
                        break;
                    case "PPexpr1":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "Exprlist":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "Moreexprs":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "S":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "Expr":
                    	hoistOp = bankOfChildren.remove(1);
                        break;
                    case "R":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "Rterm":
                    	hoistOp = bankOfChildren.remove(0);
                        break;
                    case "Q":
                    	hoistOp = bankOfChildren.remove(2);
                        break;
                    case "Rterm_rec":
                    	hoistOp = bankOfChildren.remove(0);
                        System.out.println(hoistOp);
                        integrate = true;
                        break;
                    case "Term_rec":
                    	hoistOp = bankOfChildren.remove(0);
                        break;
                    case "Term":
                    	hoistOp = bankOfChildren.remove(0);
                    	integrate = true;
                        break;
                    default:
                    	hoistOp = bankOfChildren.remove(0);
                } //Ending switch case statement
            }//ending if condition statement

            if (integrate)
            	hoistOp.addChildren(bankOfChildren);
            else
            	hoistOp.replaceChildren(bankOfChildren);
            //ending nested if else condition statement
            hoistOp.setParent(pNode.getParent());
        }//ending if condition statement
        return hoistOp;
    }//ending fix method

    private static Node fixExtra(Node curretNode) 
    {
        Node hoistOp = curretNode;

        if (hoistOp.getKeyword().equals("Q")) 
        {
            ArrayList<Node> children = hoistOp.getChildren();

            if (children.size() >= 1)
            {
            	hoistOp = children.remove(0);;
            	hoistOp.replaceChildren(children);
            }//ending nested if condition
        }//ending if condition statement

        if (hoistOp.getKeyword().equals("R")) 
        {
            ArrayList<Node> bankOfChildren = hoistOp.getChildren();

            if (bankOfChildren.size() == 1) 
            {
                Node nodeToTest = bankOfChildren.get(0);

                if (nodeToTest.getKeyword().equals("caret") ||
                        nodeToTest.getKeyword().equals("aster") ||
                        nodeToTest.getKeyword().equals("slash")) {
                	hoistOp = nodeToTest;
                } else 
                {
                	hoistOp = bankOfChildren.remove(0);;
                	hoistOp.replaceChildren(bankOfChildren);
                }//ending nested if else condition statement
            }//ending nested if condition statement
        }//ending if condition statement

        hoistOp.setParent(curretNode.getParent());
        return hoistOp;
    }//ending fixExtra function
}//ending ASTConverter class
