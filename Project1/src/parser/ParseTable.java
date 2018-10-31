//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a6 LEXCON language provided by professor siska 

//loading needed libraries
package parser;

import java.util.HashMap;

public class ParseTable
{
	 //A6 Lexicon grammer rules associated to rule id's
    private RuleList rules;
	//map used for non terminals and index which represent the y axis labels from the ll parse table
    private HashMap<String, Integer> nMapper;
    //container to hold LL Parse table ID's to rules 
    private int[][] table;
    //map used for terminals and index which represent the X axis of the labels of the LL parse table. 
    private HashMap<String, Integer> tMapper;
   

    public ParseTable()
    {
        fillMappers();
        fillTable();
        rules = new RuleList();
    }//ending parsetable default constructor

    //Function to get associated rule from the parse table
    //where index [x, y] is equal to x = stacktop and y = frontinput
    public Rule get(String topofMap, String frontOfMap) 
    {
        int lId = nMapper.get(topofMap);
        int rId = tMapper.get(frontOfMap);

        if (lId == -1 || rId == -1) 
        {
            return null;
        }//ending if condition statement

        int currentRuleId = lookup(lId, rId);
        if (currentRuleId == 0) 
        {
            return null;
        } else 
        {
            return rules.get(currentRuleId);
        }//ending if else condition statement
    }//ending get function

    //function to get associated rule id form rule 
    private int lookup(int currentLeftId, int currentRightId)
    {
        if (currentLeftId > 29 || currentLeftId < 0 || currentRightId > 30 || currentRightId < 0) 
        {
            return -1;
        }//ending if condition statement

        return table[currentLeftId][currentRightId];
    }//ending function
    //function to fill table
    private void fillTable()
    {
        table = new int[29][30];
        table[0][0] = 1;
        table[1][1] = 2;
        table[2][2] = 4;
        table[2][3] = 3;
        table[2][7] = 4;
        table[2][9] = 4;
        table[2][10] = 4;
        table[3][4] = 5;
        table[4][5] = 7;
        table[4][15] = 6;
        table[4][16] = 6;
        table[4][17] = 6;
        table[5][15] = 8;
        table[5][16] = 8;
        table[5][17] = 8;
        table[6][15] = 9;
        table[6][16] = 10;
        table[6][17] = 11;
        table[7][7] = 12;
        table[8][2] = 14;
        table[8][7] = 13;
        table[8][9] = 13;
        table[8][10] = 13;
        table[9][7] = 15;
        table[9][9] = 16;
        table[9][10] = 17;
        table[10][7] = 18;
        table[11][9] = 19;
        table[12][10] = 20;
        table[13][4] = 21;
        table[14][4] = 22;
        table[15][4] = 23;
        table[15][7] = 23;
        table[15][12] = 23;
        table[15][13] = 23;
        table[15][14] = 23;
        table[16][5] = 25;
        table[16][11] = 24;
        table[17][4] = 28;
        table[17][7] = 28;
        table[17][12] = 28;
        table[17][13] = 28;
        table[17][14] = 28;
        table[18][5] = 27;
        table[18][6] = 27;
        table[18][11] = 27;
        table[18][18] = 26;
        table[18][19] = 26;
        table[18][20] = 26;
        table[18][21] = 26;
        table[18][22] = 26;
        table[18][23] = 26;
        table[19][4] = 31;
        table[19][7] = 31;
        table[19][12] = 31;
        table[19][13] = 31;
        table[19][14] = 31;
        table[20][5] = 30;
        table[20][6] = 30;
        table[20][11] = 30;
        table[20][18] = 30;
        table[20][19] = 30;
        table[20][20] = 30;
        table[20][21] = 30;
        table[20][22] = 30;
        table[20][23] = 30;
        table[20][24] = 29;
        table[20][25] = 29;
        table[21][4] = 34;
        table[21][7] = 34;
        table[21][12] = 34;
        table[21][13] = 34;
        table[21][14] = 34;
        table[22][5] = 33;
        table[22][6] = 33;
        table[22][11] = 33;
        table[22][18] = 33;
        table[22][19] = 33;
        table[22][20] = 33;
        table[22][21] = 33;
        table[22][22] = 33;
        table[22][23] = 33;
        table[22][24] = 33;
        table[22][25] = 33;
        table[22][26] = 32;
        table[22][27] = 32;
        table[22][28] = 32;
        table[23][4] = 39;
        table[23][7] = 38;
        table[23][12] = 35;
        table[23][13] = 36;
        table[23][14] = 37;
        table[24][18] = 40;
        table[24][19] = 41;
        table[24][20] = 43;
        table[24][21] = 44;
        table[24][22] = 42;
        table[24][23] = 45;
        table[25][22] = 46;
        table[26][23] = 47;
        table[27][24] = 48;
        table[27][25] = 49;
        table[28][26] = 50;
        table[28][27] = 51;
        table[28][28] = 52;
    }

    private void fillMappers()
    {
        nMapper = new HashMap<>();
        nMapper.put("Pgm", 0);
        nMapper.put("BBlock", 1);
        nMapper.put("Vargroup", 2);
        nMapper.put("PPVarlist", 3);
        nMapper.put("Varlist", 4);
        nMapper.put("Vardecl", 5);
        nMapper.put("Basekind", 6);
        nMapper.put("Varid", 7);
        nMapper.put("Stmts", 8);
        nMapper.put("Stmt", 9);
        nMapper.put("Stasgn", 10);
        nMapper.put("Stprint", 11);
        nMapper.put("Stwhile", 12);
        nMapper.put("PPexprs", 13);
        nMapper.put("PPexpr1", 14);
        nMapper.put("Exprlist", 15);
        nMapper.put("Moreexprs", 16);
        nMapper.put("Expr", 17);
        nMapper.put("S", 18);
        nMapper.put("Rterm", 19);
        nMapper.put("R", 20);
        nMapper.put("Term", 21);
        nMapper.put("Q", 22);
        nMapper.put("Fact", 23);
        nMapper.put("Oprel", 24);
        nMapper.put("Lthan", 25);
        nMapper.put("Gthan", 26);
        nMapper.put("Opadd", 27);
        nMapper.put("Opmul", 28);

        // tmapper = terminal symbol mapper to int for array lookup
        tMapper = new HashMap<>();
        tMapper.put("kprog", 0);
        tMapper.put("brace1", 1);
        tMapper.put("brace2", 2);
        tMapper.put("kwdvars", 3);
        tMapper.put("parens1", 4);
        tMapper.put("parens2", 5);
        tMapper.put("semi", 6);
        tMapper.put("id", 7);
        tMapper.put("equal", 8);
        tMapper.put("kprint", 9);
        tMapper.put("kwhile", 10);
        tMapper.put("comma", 11);
        tMapper.put("int", 12);
        tMapper.put("float", 13);
        tMapper.put("string", 14);
        tMapper.put("kint", 15);
        tMapper.put("kfloat", 16);
        tMapper.put("kstring", 17);
        tMapper.put("opeq", 18);
        tMapper.put("opne", 19);
        tMapper.put("ople", 20);
        tMapper.put("opge", 21);
        tMapper.put("angle1", 22);
        tMapper.put("angle2", 23);
        tMapper.put("plus", 24);
        tMapper.put("minus", 25);
        tMapper.put("aster", 26);
        tMapper.put("slash", 27);
        tMapper.put("caret", 28);
        tMapper.put("eps", 29);
    }
}//ending parse table class
