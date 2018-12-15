//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a6 LEXCON language

//loading needed libraries and classes
package parser;

import lexer.Token;
import lexer.Tokenizer;
import parser.pst.Node;
import runner.ASTRunner;
import parser.pst.ASTConverter;
import runner.sct.SCTBuilder;

import java.util.ArrayList;
import java.util.Stack;
import runner.ASTRunner;
import runner.sct.SCTNode;

public class Parser
{

    private static Stack<Node> inputStack;
    private static ArrayList<Token> tokens;
    private static Node root;
    
    
    public static void main(String args[]) 
    {
        //Node astRoot = getASTRoot(false,args[0]);
        
        Node astRoot = new Node("kwdprog");
        Node main = new Node("kwdmain");
        Node block = new Node("brace1");
        Node line1 = new Node("equal");
        Node line2 = new Node("equal");
        Node meth = new Node("aster");
        Node val1 = new Node("id","test");
        Node val2 = new Node("int","500");
        Node val3 = new Node("int","100");
        Node val4 = new Node("id","test");
        Node val5 = new Node("int","100");
        astRoot.addChild(main);
        main.addChild(block);
        block.addChild(line1);
        block.addChild(line2);
        line1.addChild(val1);
        line1.addChild(meth);
        meth.addChild(val2);
        meth.addChild(val3);
        line2.addChild(val4);
        line2.addChild(val5);
        
        printTree(astRoot, 0);
        
        System.out.println("\n");
        ASTRunner runner = new ASTRunner(astRoot);
        System.out.println("\n\n");
        runner.run();
        
    }

    /**
     * 
     * @param printThisPST
     * @param location
     * @return
     */
    public static Node getASTRoot(boolean printThisPST,String location) 
    {
        ParseTable table = init(location);
        if (table == null) 
        {
            System.out.println("Error: Tokenizer failed to initiate");
            System.exit(1);
        }//ending if condition statement for error

        System.out.println("Creating tree...\n");
        
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
                } 
            }
        } 

        
        if (printThisPST)
            printTree(root, 0);
       
        return ASTConverter.convert(root);
    } 

   
    /**
     * start the ll parse table to read from 
     * @param location
     * @return
     */
    private static ParseTable init(String location)
    {
        //Tokenizer tokenizer = new Tokenizer("program.txt");
    	Tokenizer tokenizer = new Tokenizer(location);

        tokens = tokenizer.printTokens();
 
        if (tokens == null || tokens.isEmpty())
            return null;

        inputStack = new Stack<Node>();
        root = new Node("Pgm");
        inputStack.push(root);

        return new ParseTable();
    }

    /**
     * pop any node from the top of the stack and adds value to front of the input stream
     */
    private static void m1() 
    {
        Node poppedNode = inputStack.pop();
        Token poppedToken = tokens.remove(0);

        poppedNode.setValue(poppedToken.getValue());
    }


    /**
     * return i to top of stacj is terminal symb
     * @param currentTop
     * @return
     */
    private static boolean m2(String currentTop)
    {
        return !currentTop.isEmpty() && Character.isLowerCase(currentTop.charAt(0));
    }

    /**
     * 
     * @param currentRule
     * @return
     */
    private static boolean m3(Rule currentRule) 
    {
        return currentRule == null;
    }

    /**
     * pops if epsilon rule is present or for matching rule
     * @param currentRule
     */
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
            }
        }
    }

    /**
     * 
     * @param currentError
     */
    private static void error(String currentError)
    {
        System.out.println(currentError + " ERROR.");
        System.exit(1);
    }

  
    /**
     * pre order print of the tree
     * @param rootNode
     * @param nodeLevel
     */
    public static void printTree(Node rootNode, int nodeLevel) 
    {
        if (rootNode == null)
            return;

        String tab = getSpacing(nodeLevel);
        System.out.println(tab + "(" + rootNode + ")");

        nodeLevel++;
        ArrayList<Node> children = rootNode.getChildren();
        for (Node child : children)
        {
            printTree(child, nodeLevel);
        }
    }

    /**
     * function used in tree printing
     * @param nodeLevel
     * @return
     */
    private static String getSpacing(int nodeLevel)
    {
        String data = "";
        for (int i = 0; i < nodeLevel; i++)
        	data = data + "  ";


        return data;
    }
} //Ending Parser class
