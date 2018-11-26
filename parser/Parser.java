//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a6 LEXCON language

//loading needed libraries and classes
package parser;

import lexer.Token;
import lexer.Tokenizer;
import parser.pst.Node;
import parser.pst.ASTConverter;

import java.util.ArrayList;
import java.util.Stack;

public class Parser
{

    private static Stack<Node> inputStack;
    private static ArrayList<Token> tokens;
    private static Node root;

    public static void main(String args[]) 
    {
        Node astRoot = getASTRoot(true);
        printTree(astRoot, 0);
    }//ending main method

    public static Node getASTRoot(boolean printThisPST) 
    {
        ParseTable table = init();
        if (table == null) 
        {
            System.out.println("Error: Tokenizer failed to initiate");
            System.exit(1);
        }//ending if condition statement for error

        while(!inputStack.empty())
        {
            String currentStackTop = inputStack.peek().getKeyword();
            String currentInputFront = tokens.get(0).getGrammar().getKeyword();

            if (currentStackTop.equals(currentInputFront) || currentStackTop.equals("$")) 
            {
                m1();
            } else if (m2(currentStackTop)) 
            {
                error("M2");
            } else 
            {
                Rule currentRule = table.get(currentStackTop, currentInputFront);
                if (m3(currentRule)) 
                {
                    error("M3");
                } else 
                {
                    m4(currentRule);
                } //Ending nested if else condition statement
            } // Ending nested if else condition statement
        } // Ending while loop condition statement

        if (printThisPST)
            printTree(root, 0);
        //Ending if condition statement
        return ASTConverter.convert(root);
    } // Ending getASTRoon function 

   
    //function to start the ll parse table to read from 
    private static ParseTable init()
    {
        Tokenizer tokenizer = new Tokenizer("program.txt");

        tokens = tokenizer.printTokens();
        //ending if condition statement
        if (tokens == null || tokens.isEmpty())
            return null;
        //ending if condition statement
        inputStack = new Stack<Node>();
        root = new Node("Pgm");
        inputStack.push(root);

        return new ParseTable();
    }//ending init function
    
    //function to pop any node from the top of the stack and adds value to front of the input stream
    private static void m1() 
    {
        Node poppedNode = inputStack.pop();
        Token poppedToken = tokens.remove(0);

        poppedNode.setValue(poppedToken.getValue());
    }

    //function to return i the top stack is terminal symb.
    private static boolean m2(String currentTop)
    {
        return !currentTop.isEmpty() && Character.isLowerCase(currentTop.charAt(0));
    }

    private static boolean m3(Rule currentRule) 
    {
        return currentRule == null;
    }

  
    
    //pops if epsilon rule is present or for matching rule
    private static void m4(Rule currentRule) 
    {
        if (currentRule.isEpsilonRule()) 
        {
            inputStack.pop();
        } else 
        {
            Node parent = inputStack.pop();

            String[] reversed = currentRule.getReversedRhsArray();
            for (String keyword : reversed) 
            {
                Node child = new Node(keyword);
                parent.addChild(child);
                inputStack.push(child);
            }//ending for loop statement
        }//ending if else condition statement
    }

    private static void error(String currentError)
    {
        System.out.println(currentError + " ERROR.");
        System.exit(1);
    }

  
    //pre order print of the tree 
    public static void printTree(Node rootNode, int nodeLevel) 
    {
        if (rootNode == null)
            return;
        //ending if condition statement
        String tab = getSpacing(nodeLevel);
        System.out.println(tab + "(" + rootNode + ")");

        nodeLevel++;
        ArrayList<Node> children = rootNode.getChildren();
        for (Node child : children)
        {
            printTree(child, nodeLevel);
        }//ending enhanced for loop statement
    }
    //function used in tree printing
    private static String getSpacing(int nodeLevel)
    {
        String data = "";
        for (int i = 0; i < nodeLevel; i++)
        	data = data + "  ";
        //ending for loop statement

        return data;
    }
} //Ending Parser class
