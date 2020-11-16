package de.tu_bs.cs.isf.e4cf.compare.data_structures.io.reader;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.impl.NodeImpl;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.impl.TreeImpl;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.AbstractArtifactReader;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Node;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Tree;
import de.tu_bs.cs.isf.e4cf.core.file_structure.FileTreeElement;
import de.tu_bs.cs.isf.e4cf.core.util.file.FileStreamUtil;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.expr.SimpleName;
/***
 * 
 * @author Serkan Acar
 * @author Hassan Smaoui
 * @author Pascal Blum
 * @author Paulo Haas
 *
 */

public class JavaReader extends AbstractArtifactReader {
	public final static String[] SUPPORTED_FILE_ENDINGS = { "java" };

	private Node recursivelyTreeBuilder(com.github.javaparser.ast.Node node) {
		// create a node
		Node newNode = new NodeImpl(String.valueOf(node.getClass()));
		
		// counters
		int modifierCtr = 0;

		// search for imports, packages (only for compilation unit)
		if (node.getClass().equals(CompilationUnit.class)) {
			newNode.addAttribute("" + JavaNodeTypes.Package, ((CompilationUnit) node).getPackageDeclaration().toString());
		}
		
		// set Attributes (Type, SimpleName, Modifier)
		newNode.addAttribute("Type", node.getClass().toString());
		newNode.addAttribute("SimpleName", node.toString());
		if (node.getClass().equals(ClassOrInterfaceDeclaration.class)) {
			for (Modifier modifier : ((ClassOrInterfaceDeclaration) node).getModifiers()) {
				newNode.addAttribute("Modifier" + modifierCtr, modifier.toString());
				modifierCtr++;
			}
		}
		
				

		// Recursive depth search behavior
		for (com.github.javaparser.ast.Node child : node.getChildNodes()) {	
			
			if (child.getClass().equals(Modifier.class)) {
				node.remove(child);
			}
			else if (child.getClass().equals(SimpleName.class)) {
				node.remove(child);
			}
			/*else if (child.getClass().equals()) {
				
			}
			else if (child.getClass().equals()) {
				
			}
			else if (child.getClass().equals()) {
				
			}*/
			else {
				Node newChildNode = recursivelyTreeBuilder(child);
				newNode.addChild(newChildNode);	
			}		
		}
		
		newNode.addAttribute("columnBegin",
				String.valueOf(node.getTokenRange().get().getBegin().getRange().get().begin.column));
		newNode.addAttribute("columnEnd",
				String.valueOf(node.getTokenRange().get().getBegin().getRange().get().end.column));
		newNode.addAttribute("lineBegin",
				String.valueOf(node.getTokenRange().get().getBegin().getRange().get().begin.line));
		newNode.addAttribute("lineEnd",
				String.valueOf(node.getTokenRange().get().getBegin().getRange().get().end.line));

		newNode.addAttribute("ModifierCount", "" + modifierCtr);
		
		return newNode;
	}

	public JavaReader() {
		super(SUPPORTED_FILE_ENDINGS);
	}

	@Override
	public Tree readArtifact(FileTreeElement element) {
		Tree tree = null;

		if (isFileSupported(element)) {
			String s = FileStreamUtil.readLineByLine(Paths.get(element.getAbsolutePath()));
			String fileName = Paths.get(element.getAbsolutePath()).getFileName().toString();
			CompilationUnit cu = StaticJavaParser.parse(s);
			// do stuff

			String sps = cu.toString();

			// tree = new TreeImpl("", new NodeImpl(cu.toString()));

		}

		return tree;
	}
}