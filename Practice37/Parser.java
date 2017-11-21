/**
* @author Robyn Berkel
* @verison 1.0
*/


import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.body.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.metamodel.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

public class Parser {

	/**
	* All comments found in file parsed by parse method
	*/

	public List<Comment> comments;

	/**
	* All constructors found in file parsed by parse method
	*/

	public List<ConstructorDeclaration> constructors;

	/**
	* All methods found in file parsed by parse method
	*/

	public List<MethodDeclaration> methods;

	/**
	* All fields found in file parsed by parse method
	*/

	public List<FieldDeclaration> fields;

	/**
	* All problems (compile-time errors) found in file parsed by parse method
	*/

	public List<Problem> problems;

	/*
	* Initializes list fields
	*/

	public Parser() {
		comments = new ArrayList<Comment>();
		constructors = new ArrayList<ConstructorDeclaration>();
		methods = new ArrayList<MethodDeclaration>();
		fields = new ArrayList<FieldDeclaration>();
		problems = new ArrayList<Problem>();
	}


	/**
	* Parses the file given and adds file data to the class fields to be used with other Parser methods.
	* <p>
	* Not static to avoid needing to parse file data multiple times
	* @param file the file to parse, data from file will be added to class fields
	* @return true if at least partial parse was successful
	* @throws Exception any exception
	*/

	public boolean parse(String file) throws Exception {
			File f = new File(file);
			JavaParser parser = new JavaParser();
			ParseResult<CompilationUnit> result = parser.parse(ParseStart.COMPILATION_UNIT, Providers.provider(f));

			//to get beginning of problem: p.getLocation().get().toRange().begin
			problems = result.getProblems();

			//try to retrieve nodes
			if (!result.getResult().isPresent()) return false;
			CompilationUnit cu = result.getResult().get();

			//get comments, methods, fields, and constructors
			comments = cu.getComments();

			Optional<ClassOrInterfaceDeclaration> child = cu.getClassByName(file.substring(0, file.length()-5));
			if (!child.isPresent()) {
				return false;
			}
			ClassOrInterfaceDeclaration childNodes = child.get();
			for (Node n : childNodes.getChildNodes()) {
				if (n instanceof ConstructorDeclaration) constructors.add((ConstructorDeclaration)n);
				else if (n instanceof MethodDeclaration) {
					methods.add((MethodDeclaration)n);
				}
				else if (n instanceof FieldDeclaration) fields.add((FieldDeclaration)n);
			}
			return true;
	}

	/**
	* Returns a String reporting compile-time errors found.
	* <p>
	* These "problems" include problems that make code ungradable and problems that can be worked around.
	* @return Returns a String starting with "Compile-time errors were found at these locations: " then lists problem locations by (line, column)
	*/

	public String reportProblems() {
		System.out.println("check3");
		String result = "Compile-time errors were found at these locations:";
		for (Problem p : problems) {
			if (p.getLocation().isPresent()) {
				result += "\n" + p.getLocation().get().toRange().begin;
			}
		}
		return result;
	}

	/**
	* Checks if given method exists in the parsed file. Only checks return type, name, and parameter list
	* @param signature method signature to look for. Should be in format returnType MethodName (paramType, paramType, paramType, ... )
	* @return true if signature is found, false if signature not found
	*/

	public boolean checkMethodSignature(String signature) {
		for (MethodDeclaration m : methods) {
			if (m.toString().equals(signature)) {
				return true;
			}
		}
		return false;
	}

	/**
	* Calls replaceMethod for each signature listed in sigs
	* @param sigs array of method signatures each in the format returnType MethodName (paramType, paramType, paramType, ... )
	* @param work path of file to replace with
	* @return array of new files with replacements, possibly contains null values
	* @throws Exception any exception
	*/

	public File[] replace(String[] sigs, String work) throws Exception {
		File[] files = new File[sigs.length];
		for (int i = 0; i < sigs.length; i++) {
			files[i] = replaceMethod(sigs[i], work);
		}
		return files;
	}

	/**
	* Looks for method signature in "methods" class field. If found, then looks for method signature in "file" (String parameter).
	* If found, it takes the method body of method in "methods" class field and replaces method body in "file" with it.
	* <p>
	* Ending result is a new file with the contents of "file", except now the matching method body is the body from the original parsed file (student submission)
	* @param signature method signature each in the format returnType MethodName (paramType, paramType, paramType, ... )
	* @param file path of file to replace with
	* @return array of new files with replacements, possibly contains null values
	*/

	public File replaceMethod(String signature, String file) {
		try {
			File f = new File(file);
			JavaParser parser = new JavaParser();
			ParseResult<CompilationUnit> result = parser.parse(ParseStart.COMPILATION_UNIT, Providers.provider(f));

			//try to retrieve nodes
			if (!result.getResult().isPresent()) return null;
			CompilationUnit cu = result.getResult().get();

			//get methods
			int start = file.lastIndexOf("/") + 1;
			if (start == -1) start = 0;
			ClassOrInterfaceDeclaration childNodes = cu.getClassByName(file.substring(start, file.length()-5)).get();
			for (Node n : childNodes.getChildNodes()) {
				if (n instanceof MethodDeclaration) {
					MethodDeclaration toSet = (MethodDeclaration)n;
					if (toSet.getDeclarationAsString(false, false, false).equals(signature)) {
						for (MethodDeclaration m : methods) {
							if (m.getDeclarationAsString(false, false, false).equals(signature)) {

								//make modification
								toSet.setBody(m.getBody().get());
								toSet.setParameters(m.getParameters());
								Random r = new Random();
								String temp = "temp" + r.nextInt(10000);
								File directory = new File(temp);
								directory.mkdir();
								File newfile = new File(temp + "/" + f.getName());
								PrintWriter pw = new PrintWriter(newfile);
								pw.write(cu.toString());
								pw.close();
								return newfile;
							}
						}
					}
				} else if (n instanceof ConstructorDeclaration) {
					ConstructorDeclaration toSet = (ConstructorDeclaration)n;
					if (toSet.getDeclarationAsString(false, false, false).equals(signature)) {
						for (ConstructorDeclaration m : constructors) {
							if (m.getDeclarationAsString(false, false, false).equals(signature)) {

								//make modification
								toSet.setBody(m.getBody());
								toSet.setParameters(m.getParameters());
								Random r = new Random();
								String temp = "temp" + r.nextInt(10000);
								File directory = new File(temp);
								directory.mkdir();
								File newfile = new File(temp + "/" + f.getName());
								PrintWriter pw = new PrintWriter(newfile);
								pw.write(cu.toString());
								pw.close();
								return newfile;
							}
						}
					}
				}
			}

			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	* Returns list of small expressions or statements extracted from all statements found in the method corresponding to "method" parameter.
	* <p>
	* Expression types are defined in JavaParser project: com.github.javaparser.ast.expr.*
	* Statement types are definded in JavaParser project: com.github.javaparser.ast.stmt.*
	* @param method method signature to find statements from, matches format returnType MethodName (paramType, paramType, paramType, ... )
	* @return list of nodes (expressions or statements) that are instances of the class defined by exprclass found in the indicated method
	* @throws Exception any exception
	*/

	public List<Node> findPieces(String method) throws Exception {
		List<Node> expressions = new ArrayList<Node>();
		for (MethodDeclaration m : methods) {
			if (m.getDeclarationAsString(false, false, false).equals(method)) {
				NodeList<Statement> statements = m.getBody().get().getStatements();
				for (Statement s : statements) {
					expressions.addAll(getAllPieces(s));
				}
			}
		}
		return expressions;
	}

	/**
	* Helper method (made public for possible future cases) for findPieces. Recursive method to find all pieces of an expression or statement.
	* @param ex root node from which to derive all smaller expressions or statements. Node should be an expression or statement
	* @return list of all found expressions and statements, including all nodes not just leaf nodes
	* @throws Exception any exception
	*/

	public List<Node> getAllPieces(Node ex) throws Exception {
		List<Node> e = new ArrayList<Node>();
		e.add(ex);
		if (ex instanceof ArrayAccessExpr) {
			ArrayAccessExpr ae = (ArrayAccessExpr)ex;
			e.addAll(getAllPieces(ae.getName()));
			e.addAll(getAllPieces(ae.getIndex()));
		}
		else if (ex instanceof ArrayInitializerExpr) {
			ArrayInitializerExpr ae = (ArrayInitializerExpr)ex;
			NodeList<Expression> exs = ae.getValues();
			for (Expression s : exs) {
				e.addAll(getAllPieces(s));
			}
		}
		else if (ex instanceof AssignExpr) {
			AssignExpr ae = (AssignExpr)ex;
			e.addAll(getAllPieces(ae.getTarget()));
			e.addAll(getAllPieces(ae.getValue()));
		}
		else if (ex instanceof BinaryExpr) {
			BinaryExpr be = (BinaryExpr)ex;
			e.addAll(getAllPieces(be.getLeft()));
			e.addAll(getAllPieces(be.getRight()));
		}
		else if (ex instanceof CastExpr) {
			CastExpr be = (CastExpr)ex;
			e.addAll(getAllPieces(be.getExpression()));
		}
		else if (ex instanceof ConditionalExpr) {
			ConditionalExpr be = (ConditionalExpr)ex;
			e.addAll(getAllPieces(be.getCondition()));
			e.addAll(getAllPieces(be.getElseExpr()));
			e.addAll(getAllPieces(be.getThenExpr()));
		}
		else if (ex instanceof EnclosedExpr) {
			EnclosedExpr be = (EnclosedExpr)ex;
			if (be.getInner().isPresent()) {
				e.addAll(getAllPieces(be.getInner().get()));
			}
		}
		else if (ex instanceof FieldAccessExpr) {
			FieldAccessExpr be = (FieldAccessExpr)ex;
			e.addAll(getAllPieces(be.getScope()));
		}
		else if (ex instanceof InstanceOfExpr) {
			InstanceOfExpr be = (InstanceOfExpr)ex;
			e.addAll(getAllPieces(be.getExpression()));
		}
		else if (ex instanceof InstanceOfExpr) {
			InstanceOfExpr be = (InstanceOfExpr)ex;
			e.addAll(getAllPieces(be.getExpression()));
		}
		else if (ex instanceof LambdaExpr) {
			LambdaExpr ae = (LambdaExpr)ex;
			if (ae.getExpressionBody().isPresent()) {
				e.addAll(getAllPieces(ae.getExpressionBody().get()));
			}
			e.addAll(getAllPieces(ae.getBody()));
		}
		else if (ex instanceof MethodCallExpr) {
			MethodCallExpr ae = (MethodCallExpr)ex;
			NodeList<Expression> exs = ae.getArguments();
			for (Expression s : exs) {
				e.addAll(getAllPieces(s));
			}
			if (ae.getScope().isPresent()) {
				e.addAll(getAllPieces(ae.getScope().get()));
			}
		}
		else if (ex instanceof MethodReferenceExpr) {
			MethodReferenceExpr be = (MethodReferenceExpr)ex;
			e.addAll(getAllPieces(be.getScope()));
		}
		else if (ex instanceof ObjectCreationExpr) {
			ObjectCreationExpr ae = (ObjectCreationExpr)ex;
			NodeList<Expression> exs = ae.getArguments();
			for (Expression s : exs) {
				e.addAll(getAllPieces(s));
			}
			if (ae.getScope().isPresent()) {
				e.addAll(getAllPieces(ae.getScope().get()));
			}
		}
		else if (ex instanceof SuperExpr) {
			SuperExpr ae = (SuperExpr)ex;
			if (ae.getClassExpr().isPresent()) {
				e.addAll(getAllPieces(ae.getClassExpr().get()));
			}
		}
		else if (ex instanceof ThisExpr) {
			ThisExpr ae = (ThisExpr)ex;
			if (ae.getClassExpr().isPresent()) {
				e.addAll(getAllPieces(ae.getClassExpr().get()));
			}
		}
		else if (ex instanceof UnaryExpr) {
			UnaryExpr be = (UnaryExpr)ex;
			e.addAll(getAllPieces(be.getExpression()));
		}
		else if (ex instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr ae = (VariableDeclarationExpr)ex;
			List<NodeList<?>> node = ae.getNodeLists();
			for (NodeList<?> n : node) {
				if (n.size() > 0) {
					if (n.get(0) instanceof VariableDeclarator) {
						VariableDeclarator vd = (VariableDeclarator)n.get(0);
						if (vd.getInitializer().isPresent()) {
							e.addAll(getAllPieces(vd.getInitializer().get()));
						}
					}
				}
			}
			NodeList<AnnotationExpr> exs = ae.getAnnotations();
			for (AnnotationExpr s : exs) {
				e.addAll(getAllPieces(s));
			}
		}
		else if (ex instanceof AssertStmt) {
			AssertStmt as = (AssertStmt)ex;
			e.addAll(getAllPieces(as.getCheck()));
			if (as.getMessage().isPresent()) {
				e.addAll(getAllPieces(as.getMessage().get()));
			}
		}
		else if (ex instanceof BlockStmt) {
			BlockStmt bs = (BlockStmt)ex;
			for (Statement s : bs.getStatements()) {
				e.addAll(getAllPieces(s));
			}
		}
		else if (ex instanceof BreakStmt) {
			BreakStmt bs = (BreakStmt)ex;
		}
		else if (ex instanceof ContinueStmt) {
			ContinueStmt cs = (ContinueStmt)ex;

		}
		else if (ex instanceof DoStmt) {
			DoStmt ds = (DoStmt)ex;
			e.addAll(getAllPieces(ds.getBody()));
			e.addAll(getAllPieces(ds.getCondition()));
		}
		else if (ex instanceof EmptyStmt) {
			EmptyStmt es = (EmptyStmt)ex;
		}
		else if (ex instanceof ExplicitConstructorInvocationStmt) {
			ExplicitConstructorInvocationStmt es = (ExplicitConstructorInvocationStmt)ex;
			for (Expression ee : es.getArguments()) {
				e.addAll(getAllPieces(ee));
			}
			if (es.getExpression().isPresent()) {
				e.addAll(getAllPieces(es.getExpression().get()));
			}
		}
		else if (ex instanceof ExpressionStmt) {
			ExpressionStmt es = (ExpressionStmt)ex;
			e.addAll(getAllPieces(es.getExpression()));
		}
		else if (ex instanceof ForeachStmt) {
			ForeachStmt fs = (ForeachStmt)ex;
			e.addAll(getAllPieces(fs.getBody()));
			e.addAll(getAllPieces(fs.getIterable()));
			e.addAll(getAllPieces(fs.getVariable()));
		}
		else if (ex instanceof ForStmt) {
			ForStmt fs = (ForStmt)ex;
			e.addAll(getAllPieces(fs.getBody()));
			if (fs.getCompare().isPresent()) {
				e.addAll(getAllPieces(fs.getCompare().get()));
			}
			for (Expression ee : fs.getInitialization()) {
				e.addAll(getAllPieces(ee));
			}
			for (Expression ee : fs.getUpdate()) {
				e.addAll(getAllPieces(ee));
			}
		}
		else if (ex instanceof IfStmt) {
			IfStmt is = (IfStmt)ex;
			e.addAll(getAllPieces(is.getCondition()));
			if (is.getElseStmt().isPresent()) {
				e.addAll(getAllPieces(is.getElseStmt().get()));
			}
			e.addAll(getAllPieces(is.getThenStmt()));
		}
		else if (ex instanceof LabeledStmt) {
			LabeledStmt ls = (LabeledStmt)ex;
			e.addAll(getAllPieces(ls.getStatement()));
		}
		else if (ex instanceof LocalClassDeclarationStmt) {
			LocalClassDeclarationStmt ls = (LocalClassDeclarationStmt)ex;
		}
		else if (ex instanceof ReturnStmt) {
			ReturnStmt rs = (ReturnStmt)ex;
			if (rs.getExpression().isPresent()) {
				e.addAll(getAllPieces(rs.getExpression().get()));
			}
		}
		else if (ex instanceof SwitchEntryStmt) {
			SwitchEntryStmt ss = (SwitchEntryStmt)ex;
			if (ss.getLabel().isPresent()) {
				e.addAll(getAllPieces(ss.getLabel().get()));
			}
			for (Statement s : ss.getStatements()) {
				e.addAll(getAllPieces(s));
			}
		}
		else if (ex instanceof SwitchStmt) {
			SwitchStmt ss = (SwitchStmt)ex;
			for (Statement s : ss.getEntries()) {
				e.addAll(getAllPieces(s));
			}
			e.addAll(getAllPieces(ss.getSelector()));
		}
		else if (ex instanceof SynchronizedStmt) {
			SynchronizedStmt ss = (SynchronizedStmt)ex;
			e.addAll(getAllPieces(ss.getBody()));
			e.addAll(getAllPieces(ss.getExpression()));
		}
		else if (ex instanceof ThrowStmt) {
			ThrowStmt ts = (ThrowStmt)ex;
			e.addAll(getAllPieces(ts.getExpression()));
		}
		else if (ex instanceof TryStmt) {
			TryStmt ts = (TryStmt)ex;
			for (CatchClause cc : ts.getCatchClauses()) {
				e.addAll(getAllPieces(cc.getBody()));
			}
			if (ts.getFinallyBlock().isPresent()) {
				e.addAll(getAllPieces(ts.getFinallyBlock().get()));
			}
			for (Expression ee : ts.getResources()) {
				e.addAll(getAllPieces(ee));
			}
			if (ts.getTryBlock().isPresent()) {
				e.addAll(getAllPieces(ts.getTryBlock().get()));
			}
		}
		else if (ex instanceof UnparsableStmt) {
			UnparsableStmt us = (UnparsableStmt)ex;
		}
		else if (ex instanceof WhileStmt) {
			WhileStmt ws = (WhileStmt)ex;
			e.addAll(getAllPieces(ws.getBody()));
			e.addAll(getAllPieces(ws.getCondition()));
		}
		return e;
	}
}
