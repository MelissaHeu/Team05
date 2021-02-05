package de.tu_bs.cs.isf.e4cf.compare.data_structures.io.reader;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

/**
 * This file extends the nodes types and attributes for the generic data structure generated by {@link JavaVisitor}. 
 * 
 * @author Serkan Acar
 * @author Pascal Blum
 * @author Paulo Haas
 * @author Hassan Smaoui
 */
public enum JavaNodeTypes {
	/**
	 * Node type for arguments and parameters
	 * 
	 * @see Expression
	 * @see Parameter
	 */
	Argument,
	/**
	 * Node type for assignments 
	 * 
	 * @see AssignExpr
	 */
	Assignment,
	/**
	 * Node type for block comments
	 * 
	 * @see BlockComment
	 */
	BlockComment,
	/**
	 * Node type for a body or a block
	 */
	Body,
	/**
	 * Node type for a bound of a type parameter
	 * 
	 * @see TypeParameter
	 */
	Bound,
	/**
	 * Node type for a break statement
	 * 
	 * @see BreakStmt
	 */
	Break,
	/**
	 * Node type for a cast expr
	 * 
	 * @see CastExpr
	 */
	Cast,
	/**
	 * Node type for a class
	 * 
	 * @see ClassOrInterfaceDeclaration
	 */
	Class,
	/**
	 * Node type for a continue stmt
	 * 
	 * @see ContinueStmt
	 */
	Continue, 
	/**
	 * Node type for the last else branch of a if stmt
	 * 
	 * @see IfStmt
	 */
	Else,
	/**
	 * Node type for an import
	 * 
	 * @see ImportDeclaration
	 */
	Import, 
	/**
	 * Node type for an interface
	 * 
	 * @see ClassOrInterfaceDeclaration
	 */
	Interface,
	/**
	 * Node for Javadoc comments
	 * 
	 * @see JavadocComment
	 */
	JavadocComment,
	/**
	 * Node for a inline comment
	 * 
	 * @see LineComment
	 */
	LineComment,
	/**
	 * Node for synchronized stmt
	 * 
	 * @see SynchronizedStmt
	 */
	Synchronized,
	/**
	 * Node types for <code>if</code> or <code>else if</code>.
	 * 
	 * @see IfStmt
	 */
	Then,
}
