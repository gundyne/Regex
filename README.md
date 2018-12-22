Class Number: CECS 444 - Compiler Construction (Tu,Th 6:30pm - 7:20pm)

Project Number and Name: Project #3 Interpreter

Team Name and Members: 
	(Team FAQ)
	Dat Doan,
	Long Truong,
	Andre Barajas,
	Trisha Echual,

Intro (including algorithm used): 
The parser use the token stream output from the Lexer (or hand-built) to create a Parse Tree(PST) and convert it to an AST.  The AST will be traversed to create a tree of nested scopes (SCT) where each scope will contain the local symbol table.  The AST will be traversed again to run statements.

Content: 
 - Parser.java
 - ParseTable.java
 - Rule.java
 - RuleList.java
 - ASTConverter.java
 - Node.java
 - ASTRunner.java
 - Operation.java
 - SCTBuilder.java
 - SCTNode.java
 - TableEntry.java

External Requirements: None

Setup and Installation (if any): 
Start and run the program.

Features added: None

Bugs(if any): The major bug in the program is that the algorithm we implemented (in hindsight) does not hoist the nodes properly to "fit" the SCT creation structure.  As this is a major bug, and we do not want to compromise showing the symbol tables, we have manually created the tree to create the tables from (in main method).
