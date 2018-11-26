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
        if (currentLeftId > 64 || currentLeftId < 0 || currentRightId > 41 || currentRightId < 0) 
        {
            return -1;
        }//ending if condition statement

        return table[currentLeftId][currentRightId];
    }//ending function
    //function to fill table
    private void fillTable()
    {
        table = new int[65][42];
        
        // Pgm
        table[0][0] = 1; // kwdprog
        
        // Main
        table[1][1] = 2; // kwdmain
        
        // BBlock
        table[2][2] = 3; // brace1
        
        // Vargroup
        table[3][4] = 4; // kwdvars
        table[3][15] = 5; // id
        table[3][1] = 5; // kwdmain
        table[3][22] = 5; // kwdfcn
        table[3][23] = 5; // kwdif
        table[3][26] = 5; // kwdwhile
        table[3][27] = 5; // kprint
        table[3][28] = 5; // kwdreturn
        table[3][3] = 5; // brace2
        table[3][18] = 5; // aster
        table[3][29] = 5; // ampersand
        
        // PPvarlist 
        table[4][5] = 6; // parens1 
        
        // Varlist
        table[5][15] = 7; // id
        table[5][9] = 7; // int
        table[5][10] = 7; // float
        table[5][11] = 7; // string
        table[5][20] = 7; // kwdclass
        table[5][6] = 8; // parens2
        
        // Varitem
        table[6][15] = 9; // id
        table[6][9] = 9; // int
        table[6][10] = 9; // float
        table[6][11] = 9; // string
        table[6][20] = 10; // kwdclass
        
        // Varitem_tail
        table[7][8] = 11; // equal
        table[7][7] = 12; // semi
        
        // Vardecl
        table[8][15] = 13; // id
        table[8][13] = 13; // kwdfloat
        table[8][12] = 13; // kwdint
        table[8][14] = 13; // kwdstring
        
        // Simplekind
        table[9][13] = 14; // kwdfloat
        table[9][12] = 14; // kwdint
        table[9][14] = 14; // kwdstring
        table[9][15] = 15; // int
        
        // Basekind
        table[10][9] = 16; // int
        table[10][10] = 17; // float
        table[10][11] = 18; // string
        
        // Kind
        table[11][12] = 19; // kwdint
        table[11][13] = 20; // kwdfloat
        table[11][14] = 21; // kwdstring
        
        // Classid
        table[12][15] = 22; // id
        
        // Varspec
        table[13][15] = 23; // id
        table[13][18] = 24; // aster
        
        // Varid
        table[14][15] = 25; // id
        
        // Varid_tail
        table[15][2] = 26; // brace1
        table[15][5] = 27 ; // parens1
        table[15][6] = 28; // comma
        table[15][19] = 28; // comma
        table[15][7] = 28; // semi
        table[15][8] = 28; // equal
        table[15][18] = 28; // equal
        table[15][36] = 28; // equal

        // Varid_item
        table[16][15] = 29; // id
        table[16][9] = 29; // int
        table[16][10] = 29; // float
        table[16][11] = 29; // string
        table[16][5] = 29; // parens1
        table[16][18] = 29; // aster
        table[16][29] = 29; // ampersand
        
        // Deref_id
        table[17][18] = 30; // aster
        
        // Deref
        table[18][18] = 31; // aster
        
        // Varinit
        table[19][15] = 32; // id
        table[19][9] = 32; // int
        table[19][10] = 32; // float
        table[19][11] = 32; // string
        table[19][5] = 32; // parens1
        table[19][18] = 32; // aster
        table[19][29] = 32; // ampersand
        table[19][2] = 33; // brace1
        
        // BBexprs
        table[20][2] = 34; // brace1
        
        // Exprlist
        table[21][15] = 35; // id
        table[21][9] = 35; // int
        table[21][10] = 35; // float
        table[21][11] = 35; // string
        table[21][5] = 35; // parens1
        table[21][18] = 35; // aster
        table[21][29] = 35; // ampersand
        table[21][3] = 36; // brace2
        table[21][17] = 36; // bracket2
        
        // Moreexprs
        table[22][19] = 37; // comma
        table[22][3] = 38; // brace2
        table[22][17] = 38; // bracket2
        table[22][6] = 38; // parens2
        table[22][5] = 38; // parens1
        
        // Classdecl
        table[23][20] = 39; // kwdclass
        
        // Classdef
        table[24][2] = 40; // brace1
        table[24][21] = 40; // colon
        table[24][7] = 41; // semi
        
        // Classitems
        table[25][15] = 42; // id
        table[25][9] = 42; // int
        table[25][10] = 42; // float
        table[25][11] = 42; // string
        table[25][19] = 42; // comma
        table[25][22] = 42; // kwdfcn
        table[25][21] = 42; // colon
        table[25][3] = 43; // brace2
        
        // Classgroup
        table[26][21] = 44; // colon
        table[26][15] = 45; // id
        table[26][9] = 45; // int
        table[26][10] = 45; // float
        table[26][11] = 45; // string
        table[26][20] = 45; // kwdclass
        table[26][22] = 46; // kwdfcn
       
        // Class_ctrl
        table[27][21] = 47; // colon
        
        // Interfaces
        table[28][21] = 48; // colon
        table[28][2] = 49; // brace1
        
        // Mddecls
        table[29][22] = 50; // kwdfcn
        table[29][3] = 51; // brace2
        
        // Mdheader
        table[30][22] = 52; // kwdfcn
        
        // Md_id
        table[31][15] = 53; // id
        
        // Fcndefs
        table[32][22] = 54; // kwdfcn
        table[32][1] = 55; // kwdmain
        
        // Fcndef
        table[33][22] = 56; // kwdfcn
        
        // Fcnheader
        table[34][22] = 57; // kwdfcn
        
        // Retkind
        table[35][13] = 58; // kwdfloat
        table[35][12] = 58; // kwdint
        table[35][14] = 58; // kwdstring
        
        // Fcnid
        table[36][15] = 59; // id
        
        // PParmlist
        table[37][5] = 60; // parens1
        
        // Varspecs
        table[38][15] = 61; // id
        table[38][18] = 61; // aster
        table[38][6] = 62; // parens2
        
        // More_varspecs
        table[39][19] = 63; // comma
        table[39][6] = 64; // parens2
        
        // Stmts
        table[40][15] = 65; // id
        table[40][23] = 65; // kwdif
        table[40][26] = 65; // kwdwhile
        table[40][27] = 65; // kprint
        table[40][28] = 65; // kwdreturn
        table[40][18] = 65; // aster
        table[40][29] = 65; // ampersand
        table[40][3] = 66; // brace2
        
        // Stmt
        table[41][15] = 67; // id
        table[41][18] = 67; // aster
        table[41][23] = 68; // kwdif
        table[41][26] = 69; // kwdwhile
        table[41][27] = 70; // kprint
        table[41][28] = 71; // kwdreturn
        
        // Stasgn
        table[42][15] = 72; // id
        table[42][18] = 72; // aster
        
        // PPexprs
        table[43][5] = 73; // parens1
        
        // Stif
        table[44][23] = 74; // kwdif
        
        // BBlock
        table[2][24] = 75; // kwdelseif
        table[2][25] = 76; // kwdelse
        
        // Elsepart
        table[45][24] = 75; // kwdelseif
        table[45][25] = 76; // kwdelse
        table[45][7] = 77; // semi
        
        // Stwhile
        table[46][26] = 78; // kwdwhile
        
        // Stprint
        table[47][27] = 79; // kprint
        
        // Strtn
        table[48][28] = 80; // kwdreturn
        
        // Strtn_tail
        table[49][15] = 81; // id
        table[49][9] = 81; // int
        table[49][10] = 81; // float
        table[49][11] = 81; // string
        table[49][5] = 81; // parens1
        table[49][18] = 81; // aster
        table[49][29] = 81; // ampersand
        table[49][7] = 82; // semi
        
        // PPexpr
        table[50][5] = 83; // parens1
        
        // Expr
        table[51][15] = 84; // id
        table[51][9] = 84; // int
        table[51][10] = 84; // float
        table[51][11] = 84; // string
        table[51][5] = 84; // parens1
        table[51][18] = 84; // aster
        
        // Expr_rec
        table[52][34] = 85; // angle1
        table[52][35] = 85; // angle2
        table[52][30] = 85; // opeq
        table[52][31] = 85; // opne
        table[52][32] = 85; // ople
        table[52][33] = 85; // opge
        table[52][19] = 86; // comma
        table[52][17] = 86; // bracket2
        table[52][5] = 86; // parens2
        table[52][6] = 86; // parens2
        
        // Rterm
        table[53][15] = 87; // id
        table[53][9] = 87; // int
        table[53][10] = 87; // float
        table[53][11] = 87; // string
        table[53][5] = 87; // parens1
        table[53][18] = 87; // aster
        table[53][29] = 87; // ampersand
        
        // Rterm_rec
        table[54][19] = 89; // comma
        table[54][6] = 89; // parens2
        table[54][37] = 88; // minus
        table[54][36] = 88; // plus
        table[54][34] = 89; // angle1
        table[54][35] = 89; // angle2
        table[54][30] = 89; // opeq
        table[54][31] = 89; // opne
        table[54][32] = 89; // ople
        table[54][33] = 89; //opge
        
        // Term
        table[55][15] = 90; // id
        table[55][9] = 90; // int
        table[55][10] = 90; // float
        table[55][11] = 90; // string
        table[55][5] = 90; // parens1
        table[55][18] = 90; // aster
        table[55][29] = 90; // ampersand
        
        // Term_rec
        table[56][18] = 91; // aster
        table[56][39] = 91; // caret
        table[56][38] = 91; // slash
        table[56][37] = 92; // minus
        table[56][36] = 92; // plus
        table[56][19] = 92; // parens1
        table[56][6] = 92; // parens2
        table[56][9] = 93; // int
        table[56][10] = 93; // float
        table[56][11] = 93; // string
        
        // Fact
        table[57][15] = 94; // id
        table[57][9] = 93; // int - added
        table[57][10] = 93; // float - added
        table[57][11] = 93; // string - added
        table[57][18] = 94; // aster
        table[57][29] = 95; // ampersand
        table[57][5] = 96; // parens1
        
        // Addrof_id
        table[58][29] = 97; // ampersand
        
        // Oprel
        table[59][30] = 98; // opeq
        table[59][31] = 99; // opne
        table[59][34] = 100; // angle1
        table[59][32] = 101; // ople
        table[59][33] = 102; // opge
        table[59][35] = 103; // angle2
        
        // Lthan
        table[60][34] = 104; // angle1
        
        // Gthan
        table[61][35] = 105; // angle2
        
        // Opadd
        table[62][36] = 106; // plus
        table[62][37] = 107; // minus
        
        // Opmul
        table[63][18] = 108; // aster
        table[63][38] = 109; // slash
        table[63][39] = 110; // caret
        
        table[64][9] = 111; //int
        table[64][10] = 111; //float
        table[64][11] = 111; //string
        table[64][15] = 111; //id
        table[64][18] = 111; //aster
        table[64][29] = 111; //ampersand
        table[64][5] = 111; //parens1
        table[64][41] = 112;
        
//        table[0][0] = 1;
//        table[1][1] = 2;
//        table[2][2] = 4;
//        table[2][3] = 3;
//        table[2][7] = 4;
//        table[2][9] = 4;
//        table[2][10] = 4;
//        table[3][4] = 5;
//        table[4][5] = 7;
//        table[4][15] = 6;
//        table[4][16] = 6;
//        table[4][17] = 6;
//        table[5][15] = 8;
//        table[5][16] = 8;
//        table[5][17] = 8;
//        table[6][15] = 9;
//        table[6][16] = 10;
//        table[6][17] = 11;
//        table[7][7] = 12;
//        table[8][2] = 14;
//        table[8][7] = 13;
//        table[8][9] = 13;
//        table[8][10] = 13;
//        table[9][7] = 15;
//        table[9][9] = 16;
//        table[9][10] = 17;
//        table[10][7] = 18;
//        table[11][9] = 19;
//        table[12][10] = 20;
//        table[13][4] = 21;
//        table[14][4] = 22;
//        table[15][4] = 23;
//        table[15][7] = 23;
//        table[15][12] = 23;
//        table[15][13] = 23;
//        table[15][14] = 23;
//        table[16][5] = 25;
//        table[16][11] = 24;
//        table[17][4] = 28;
//        table[17][7] = 28;
//        table[17][12] = 28;
//        table[17][13] = 28;
//        table[17][14] = 28;
//        table[18][5] = 27;
//        table[18][6] = 27;
//        table[18][11] = 27;
//        table[18][18] = 26;
//        table[18][19] = 26;
//        table[18][20] = 26;
//        table[18][21] = 26;
//        table[18][22] = 26;
//        table[18][23] = 26;
//        table[19][4] = 31;
//        table[19][7] = 31;
//        table[19][12] = 31;
//        table[19][13] = 31;
//        table[19][14] = 31;
//        table[20][5] = 30;
//        table[20][6] = 30;
//        table[20][11] = 30;
//        table[20][18] = 30;
//        table[20][19] = 30;
//        table[20][20] = 30;
//        table[20][21] = 30;
//        table[20][22] = 30;
//        table[20][23] = 30;
//        table[20][24] = 29;
//        table[20][25] = 29;
//        table[21][4] = 34;
//        table[21][7] = 34;
//        table[21][12] = 34;
//        table[21][13] = 34;
//        table[21][14] = 34;
//        table[22][5] = 33;
//        table[22][6] = 33;
//        table[22][11] = 33;
//        table[22][18] = 33;
//        table[22][19] = 33;
//        table[22][20] = 33;
//        table[22][21] = 33;
//        table[22][22] = 33;
//        table[22][23] = 33;
//        table[22][24] = 33;
//        table[22][25] = 33;
//        table[22][26] = 32;
//        table[22][27] = 32;
//        table[22][28] = 32;
//        table[23][4] = 39;
//        table[23][7] = 38;
//        table[23][12] = 35;
//        table[23][13] = 36;
//        table[23][14] = 37;
//        table[24][18] = 40;
//        table[24][19] = 41;
//        table[24][20] = 43;
//        table[24][21] = 44;
//        table[24][22] = 42;
//        table[24][23] = 45;
//        table[25][22] = 46;
//        table[26][23] = 47;
//        table[27][24] = 48;
//        table[27][25] = 49;
//        table[28][26] = 50;
//        table[28][27] = 51;
//        table[28][28] = 52;
    }

    private void fillMappers()
    {
    	nMapper = new HashMap<>();
        nMapper.put("Pgm", 0);
        nMapper.put("Main", 1);
        nMapper.put("BBlock", 2);
        nMapper.put("Vargroup", 3);
        nMapper.put("PPvarlist", 4);
        nMapper.put("Varlist", 5);
        nMapper.put("Varitem", 6);
        nMapper.put("Varitem_tail", 7);
        nMapper.put("Vardecl", 8);
        nMapper.put("Simplekind", 9);
        nMapper.put("Basekind", 10);
        nMapper.put("Kind", 11);
        nMapper.put("Classid", 12);
        nMapper.put("Varspec", 13);
        nMapper.put("Varid", 14);
        nMapper.put("Varid_tail", 15);
        nMapper.put("Varid_item", 16);
        nMapper.put("Deref_id", 17);
        nMapper.put("Deref", 18);
        nMapper.put("Varinit", 19);
        nMapper.put("BBexprs", 20);
        nMapper.put("Exprlist", 21);
        nMapper.put("Moreexprs", 22);
        nMapper.put("Classdecl", 23);
        nMapper.put("Classdef", 24);
        nMapper.put("Classitems", 25);
        nMapper.put("Classgroup", 26);
        nMapper.put("Class_ctrl", 27);
        nMapper.put("Interfaces", 28);
        nMapper.put("Mddecls", 29);
        nMapper.put("Mdheader", 30);
        nMapper.put("Md_id", 31);
        nMapper.put("Fcndefs", 32);
        nMapper.put("Fcndef", 33);
        nMapper.put("Fcnheader", 34);
        nMapper.put("Retkind", 35);
        nMapper.put("Fcnid", 36);
        nMapper.put("PParmlist", 37);
        nMapper.put("Varspecs", 38);
        nMapper.put("More_varspecs", 39);
        nMapper.put("Stmts", 40);
        nMapper.put("Stmt", 41);
        nMapper.put("Stasgn", 42);
        nMapper.put("PPexprs", 43);
        nMapper.put("Stif", 44);
        nMapper.put("Elsepart", 45);
        nMapper.put("Stwhile", 46);
        nMapper.put("Stprint", 47);
        nMapper.put("Strtn", 48);
        nMapper.put("Strtn_tail", 49);
        nMapper.put("PPexpr", 50);
        nMapper.put("Expr", 51);
        nMapper.put("Expr_rec", 52);
        nMapper.put("Rterm", 53);
        nMapper.put("Rterm_rec", 54);
        nMapper.put("Term", 55);
        nMapper.put("Term_rec", 56);
        nMapper.put("Fact", 57);
        nMapper.put("Addrof_id", 58);
        nMapper.put("Oprel", 59);
        nMapper.put("Lthan", 60);
        nMapper.put("Gthan", 61);
        nMapper.put("Opadd", 62);
        nMapper.put("Opmul", 63);
        nMapper.put("Stasgn_tail", 64);
        
//        nMapper = new HashMap<>();
//        nMapper.put("Pgm", 0);
//        nMapper.put("BBlock", 1);
//        nMapper.put("Vargroup", 2);
//        nMapper.put("PPVarlist", 3);
//        nMapper.put("Varlist", 4);
//        nMapper.put("Vardecl", 5);
//        nMapper.put("Basekind", 6);
//        nMapper.put("Varid", 7);
//        nMapper.put("Stmts", 8);
//        nMapper.put("Stmt", 9);
//        nMapper.put("Stasgn", 10);
//        nMapper.put("Stprint", 11);
//        nMapper.put("Stwhile", 12);
//        nMapper.put("PPexprs", 13);
//        nMapper.put("PPexpr1", 14);
//        nMapper.put("Exprlist", 15);
//        nMapper.put("Moreexprs", 16);
//        nMapper.put("Expr", 17);
//        nMapper.put("S", 18);
//        nMapper.put("Rterm", 19);
//        nMapper.put("R", 20);
//        nMapper.put("Term", 21);
//        nMapper.put("Q", 22);
//        nMapper.put("Fact", 23);
//        nMapper.put("Oprel", 24);
//        nMapper.put("Lthan", 25);
//        nMapper.put("Gthan", 26);
//        nMapper.put("Opadd", 27);
//        nMapper.put("Opmul", 28);

        // tmapper = terminal symbol mapper to int for array lookup
        tMapper = new HashMap<>();
        tMapper.put("kwdprog", 0);
        tMapper.put("kwdmain", 1);
        tMapper.put("brace1", 2);
        tMapper.put("brace2", 3);
        tMapper.put("kwdvars", 4);
        tMapper.put("parens1", 5);
        tMapper.put("parens2", 6);
        tMapper.put("semi", 7);
        tMapper.put("equal", 8);
        tMapper.put("int", 9);
        tMapper.put("float", 10);
        tMapper.put("string", 11);
        tMapper.put("kwdint", 12);
        tMapper.put("kwdfloat", 13);
        tMapper.put("kwdstring", 14);
        tMapper.put("id", 15);
        tMapper.put("bracket1", 16);
        tMapper.put("bracket2", 17);
        tMapper.put("aster", 18);
        tMapper.put("comma", 19);
        tMapper.put("kwdclass", 20);
        tMapper.put("colon", 21);
        tMapper.put("kwdfcn", 22);
        tMapper.put("kwdif", 23);
        tMapper.put("kwdelseif", 24);
        tMapper.put("kwdelse", 25);
        tMapper.put("kwdwhile", 26);
        tMapper.put("kprint", 27);
        tMapper.put("kwdreturn", 28);
        tMapper.put("ampersand", 29);
        tMapper.put("opeq", 30);
        tMapper.put("opne", 31);
        tMapper.put("ople", 32);
        tMapper.put("opge", 33);
        tMapper.put("angle1", 34);
        tMapper.put("angle2", 35);
        tMapper.put("plus", 36);
        tMapper.put("minus", 37);
        tMapper.put("slash", 38);
        tMapper.put("caret", 39);
        tMapper.put("eps", 40);
        tMapper.put("kwdinput", 41);
        
        
        
//        tMapper = new HashMap<>();
//        tMapper.put("kprog", 0);
//        tMapper.put("brace1", 1);
//        tMapper.put("brace2", 2);
//        tMapper.put("kwdvars", 3);
//        tMapper.put("parens1", 4);
//        tMapper.put("parens2", 5);
//        tMapper.put("semi", 6);
//        tMapper.put("id", 7);
//        tMapper.put("equal", 8);
//        tMapper.put("kprint", 9);
//        tMapper.put("kwhile", 10);
//        tMapper.put("comma", 11);
//        tMapper.put("int", 12);
//        tMapper.put("float", 13);
//        tMapper.put("string", 14);
//        tMapper.put("kint", 15);
//        tMapper.put("kfloat", 16);
//        tMapper.put("kstring", 17);
//        tMapper.put("opeq", 18);
//        tMapper.put("opne", 19);
//        tMapper.put("ople", 20);
//        tMapper.put("opge", 21);
//        tMapper.put("angle1", 22);
//        tMapper.put("angle2", 23);
//        tMapper.put("plus", 24);
//        tMapper.put("minus", 25);
//        tMapper.put("aster", 26);
//        tMapper.put("slash", 27);
//        tMapper.put("caret", 28);
//        tMapper.put("eps", 29);
    }
}//ending parse table class
