//Andre Barajas
//CS 444 
//FALL 2018
//Parser for compiler program for a5 LEXCON language

//loading needed libraries and classes
package parser;

public class RuleList 
{

    //A6 Grammer rules provided by profesor Siska
    private Rule[] rules;
    private final int NUM_RULES = 110;

   
    public RuleList() 
    {
        fillList();
    }//ending default constructor 

    //Function to get rule and RHS 
    public Rule get(int currentRuleId) 
    {
        if (currentRuleId > NUM_RULES + 1 || currentRuleId < 1) 
        {
            return null;
        }//ending if condition statement
        return rules[currentRuleId - 1];
    }//ending get function

    //Function to fill structure with rules provided for the A6 Grammer
    private void fillList() 
    {
        rules = new Rule[NUM_RULES];

        rules[0] = new Rule(1, "Pgm", "kwdprog Vargroup Fcndefs Main");
        
        rules[1] = new Rule(2, "Main", "kwdmain BBlock");
        
        rules[2] = new Rule(3, "BBlock", "brace1 Vargroup Stmts brace2");
        
        rules[3] = new Rule(4, "Vargroup", "kwdvars PPvarlist");
        rules[4] = new Rule(5, "Vargroup", "eps");
        
        rules[5] = new Rule(6, "PPvarlist", "parens1 Varlist, parens2");
        
        rules[6] = new Rule(7, "Varlist", "Varitem semi Varlist");
        rules[7] = new Rule(8, "Varlist", "eps");
        
        rules[8] = new Rule(9, "Varitem", "Vardecl Varitem_tail");
        rules[9] = new Rule(10, "Varitem", "Classdecl Classdef");
        
        rules[10] = new Rule(11, "Varitem_tail", "equal Varinit");
        rules[11] = new Rule(12, "Varitem_tail", "eps");
        
        rules[12] = new Rule(13, "Vardecl", "Simplekind Varspec");
        
        rules[13] = new Rule(14, "Simplekind", "Kind");
        rules[14] = new Rule(15, "Simplekind", "Classid");
       
        rules[15] = new Rule(16, "Basekind", "int");
        rules[16] = new Rule(17, "Basekind", "float");
        rules[17] = new Rule(18, "Basekind", "string");
        
        rules[18] = new Rule(19, "Kind", "kwdint");
        rules[19] = new Rule(20, "Kind", "kwdfloat");
        rules[20] = new Rule(21, "Kind", "kwdstring");
        
        rules[21] = new Rule(22, "Classid", "id");
        
        rules[22] = new Rule(23, "Varspec", "Varid");
        rules[23] = new Rule(24, "Varspec", "Deref_id");
        
        rules[24] = new Rule(25, "Varid", "id Varid_tail");
        
        rules[25] = new Rule(26, "Varid_tail", "bracket1 Varid_item bracket2");
        rules[26] = new Rule(27, "Varid_tail", "PPexpr");
        rules[27] = new Rule(28, "Varid_tail", "eps");
        
        rules[28] = new Rule(29, "Varid_item", "Expr");
        
        rules[29] = new Rule(30, "Deref_id", "Deref id");
        
        rules[30] = new Rule(31, "Deref", "aster");
        
        rules[31] = new Rule(32, "Varinit", "Expr");
        rules[32] = new Rule(33, "Varinit", "BBexprs");
        
        rules[33] = new Rule(34, "BBexprs", "brace1 Exprlist brace2");
        
        rules[34] = new Rule(35, "Exprlist", "Expr Moreexprs");
        rules[35] = new Rule(36, "Exprlist", "eps");
        
        rules[36] = new Rule(37, "Moreexprs", "comma Expr Moreexprs");
        rules[37] = new Rule(38, "Moreexprs", "eps");
        
        rules[38] = new Rule(39, "Classdecl", "kwdclass Classid");
        
        rules[39] = new Rule(40, "Classdef", "Interfaces brace1 Classitems brace2");
        rules[40] = new Rule(41, "Classdef", "eps");
        
        rules[41] = new Rule(42, "Classitems", "Classgroup Classitems");
        rules[42] = new Rule(43, "Classitems", "eps");
        
        rules[43] = new Rule(44, "Classgroup", "Class_ctrl");
        rules[44] = new Rule(45, "Classgroup", "Varlist");
        rules[45] = new Rule(46, "Classgroup", "Mddecls");
        
        rules[46] = new Rule(47, "Class_ctrl", "colon id");
        
        rules[47] = new Rule(48, "Interfaces", "colon Classid Interfaces");
        rules[48] = new Rule(49, "Interfaces", "eps");
        
        rules[49] = new Rule(50, "Mddecls", "Mdheader Mddecls");
        rules[50] = new Rule(51, "Mddecls", "eps");
        
        rules[51] = new Rule(52, "Mdheader", "kwdfcn Md_id PParmlist Retkind");
        
        rules[52] = new Rule(53, "Md_id", "Classid colon Fcnid");
       
        rules[53] = new Rule(54, "Fcndefs", "Fcndef");
        rules[54] = new Rule(55, "Fcndefs", "eps");
        
        rules[55] = new Rule(56, "Fcndef", "Fcnheader BBlock");
        
        rules[56] = new Rule(57, "Fcnheader", "kwdfcn Fcnid PParmlist Retkind");
        
        rules[57] = new Rule(58, "Retkind", "Kind");
        
        rules[58] = new Rule(59, "Fcnid", "id");
        
        rules[59] = new Rule(60, "PParmlist", "parens1 Varspecs parens2");
        
        rules[60] = new Rule(61, "Varspecs", "Varspec More_varspecs");
        rules[61] = new Rule(62, "Varspecs", "eps");
        
        rules[62] = new Rule(63, "More_varspecs", "comma Varspec More_varspecs");
        rules[63] = new Rule(64, "More_varspecs", "eps");
        
        rules[64] = new Rule(65, "Stmts", "Stmt semi Stmts");
        rules[65] = new Rule(66, "Stmts", "eps");
        
        rules[66] = new Rule(67, "Stmt", "Stasgn");
        rules[67] = new Rule(68, "Stmt", "Stif");
        rules[68] = new Rule(69, "Stmt", "Stwhile");
        rules[69] = new Rule(70, "Stmt", "Stprint");
        rules[70] = new Rule(71, "Stmt", "Strtn");
        
        rules[71] = new Rule(72, "Stasgn", "Varspec equal Expr");
        
        rules[72] = new Rule(73, "PPexprs", "parens1 Exprlist parens2");
        
        rules[73] = new Rule(74, "Stif", "kwdif PPexpr BBlock Elsepart");
        
        rules[74] = new Rule(75, "Elsepart", "kwdelseif PPexpr BBlock Elsepart");
        rules[75] = new Rule(76, "Elsepart", "kwdelse BBlock");
        rules[76] = new Rule(77, "Elsepart", "eps");
        
        rules[77] = new Rule(78, "Stwhile", "kwdwhile PPexpr BBlock");
        
        rules[78] = new Rule(79, "Stprint", "kprint PPexprs");
        
        rules[79] = new Rule(80, "Strtn", "kwdreturn Strtn_tail");
        
        rules[80] = new Rule(81, "Strtn_tail", "Expr");
        rules[81] = new Rule(82, "Strtn_tail", "eps");
        
        rules[82] = new Rule(83, "PPexpr", "parens1 Expr parens2");
        
        rules[83] = new Rule(84, "Expr", "Rterm Expr_rec");
        
        rules[84] = new Rule(85, "Expr_rec", "Oprel Rterm Expr_rec");
        rules[85] = new Rule(86, "Expr_rec", "eps");
        
        rules[86] = new Rule(87, "Rterm", "Term Rterm_rec");
        
        rules[87] = new Rule(88, "Rterm_rec", "Opadd Term Rterm_rec");
        rules[88] = new Rule(89, "Rterm_rec", "eps");
        
        rules[89] = new Rule(90, "Term", "Fact Term_rec");
        
        rules[90] = new Rule(91, "Term_rec", "Opmul Fact Term_rec");
        rules[91] = new Rule(92, "Term_rec", "eps");
       
        rules[92] = new Rule(93, "Fact", "Basekind");
        rules[93] = new Rule(94, "Fact", "Varspec");
        rules[94] = new Rule(95, "Fact", "Addrof_id");
        rules[95] = new Rule(96, "Fact", "PPexpr");
        
        rules[96] = new Rule(97, "Addrof_id", "ampersand id");
        
        rules[97] = new Rule(98, "Oprel", "opeq");
        rules[98] = new Rule(99, "Oprel", "opne");
        rules[99] = new Rule(100, "Oprel", "Lthan");
        rules[100] = new Rule(101, "Oprel", "ople");
        rules[101] = new Rule(102, "Oprel", "opge");
        rules[102] = new Rule(103, "Oprel", "Gthan");
        
        rules[103] = new Rule(104, "Lthan", "angle1");
        
        rules[104] = new Rule(105, "Gthan", "angle2");
        
        rules[105] = new Rule(106, "Opadd", "plus");
        rules[106] = new Rule(107, "Opadd", "minus");
        
        rules[107] = new Rule(108, "Opmul", "aster");
        rules[108] = new Rule(109, "Opmul", "slask");
        rules[109] = new Rule(110, "Opmul", "caret");
        
        
//        rules[0] = new Rule(1, "Pgm", "kprog BBlock");
//        rules[1] = new Rule(2, "BBlock", "brace1 Vargroup Stmts brace2");
//        rules[2] = new Rule(3, "Vargroup", "kwdvars PPvarlist");
//        rules[3] = new Rule(4, "Vargroup", "eps");
//        rules[4] = new Rule(5, "PPvarlist", "parens1 Varlist, parens2");
//        rules[5] = new Rule(6, "Varlist", "Vardecl semi Varlist");
//        rules[6] = new Rule(7, "Varlist", "eps");
//        rules[7] = new Rule(8, "Vardecl", "Basekind Varid");
//        rules[8] = new Rule(9, "Basekind", "kint");
//        rules[9] = new Rule(10, "Basekind", "kfloat");
//        rules[10] = new Rule(11, "Basekind", "kstring");
//        rules[11] = new Rule(12, "Varid", "id");
//        rules[12] = new Rule(13, "Stmts", "Stmt semi Stmts");
//        rules[13] = new Rule(14, "Stmts", "eps");
//        rules[14] = new Rule(15, "Stmt", "Stasgn");
//        rules[15] = new Rule(16, "Stmt", "Stprint");
//        rules[16] = new Rule(17, "Stmt", "Stwhile");
//        rules[17] = new Rule(18, "Stasgn", "Varid equal Expr");
//        rules[18] = new Rule(19, "Stprint", "kprint PPexprs");
//        rules[19] = new Rule(20, "Stwhile", "kwhile PPexpr1 BBlock");
//        rules[20] = new Rule(21, "PPexprs", "parens1 Exprlist parens2");
//        rules[21] = new Rule(22, "PPexpr1", "parens1 Expr parens2");
//        rules[22] = new Rule(23, "Exprlist","Expr Moreexprs");
//        rules[23] = new Rule(24, "Moreexprs", "comma Exprlist");
//        rules[24] = new Rule(25, "Moreexprs", "eps");
//        rules[25] = new Rule(26, "S", "Oprel Rterm S");
//        rules[26] = new Rule(27, "S", "eps");
//        rules[27] = new Rule(28, "Expr", "Rterm S");
//        rules[28] = new Rule(29, "R", "Opadd Term R");
//        rules[29] = new Rule(30, "R", "eps");
//        rules[30] = new Rule(31, "Rterm", "Term R");
//        rules[31] = new Rule(32, "Q", "Opmul Fact Q");
//        rules[32] = new Rule(33, "Q", "eps");
//        rules[33] = new Rule(34, "Term", "Fact Q");
//        rules[34] = new Rule(35, "Fact", "int");
//        rules[35] = new Rule(36, "Fact", "float");
//        rules[36] = new Rule(37, "Fact", "string");
//        rules[37] = new Rule(38, "Fact", "Varid");
//        rules[38] = new Rule(39, "Fact", "PPexpr1");
//        rules[39] = new Rule(40, "Oprel", "opeq");
//        rules[40] = new Rule(41, "Oprel", "opne");
//        rules[41] = new Rule(42, "Oprel", "Lthan");
//        rules[42] = new Rule(43, "Oprel", "ople");
//        rules[43] = new Rule(44, "Oprel", "opge");
//        rules[44] = new Rule(45, "Oprel", "Gthan");
//        rules[45] = new Rule(46, "Lthan", "angle1");
//        rules[46] = new Rule(47, "Gthan", "angle2");
//        rules[47] = new Rule(48, "Opadd", "plus");
//        rules[48] = new Rule(49, "Opadd", "minus");
//        rules[49] = new Rule(50, "Opmul", "aster");
//        rules[50] = new Rule(51, "Opmul", "slash");
//        rules[51] = new Rule(52, "Opmul", "caret");
    }//ending fill list function
}//Ending RuleList class 
