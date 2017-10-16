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

public class Project {
	public static void main(String[] args) {

		//define tests

		TestCase[] tests = {
				new TestCase("Test 1", "Associate.java exists in student directory", "Completion", 1),
				new TestCase("Test 2", "Associate.java compiles", "Correctness", 1),
				new TestCase("Test 3", "Class header written correctly", "Completion", 1),
				new TestCase("Test 4", "Main method signature written correctly", "Completion", 1),
				new TestCase("Test 5", "firstTwo method signature written correctly", "Completion", 1),
				new TestCase("Test 6", "lastTwo method signature written correctly", "Completion", 1),
				new TestCase("Test 7", "Main method creates a Scanner object", "Completion", 1),
				new TestCase("Test 8", "Main method creates an Associate object", "Completion", 1),
				new TestCase("Test 9", "Main method uses firstTwo and lastTwo method calls", "Completion", 1),
				new TestCase("Test 10", "Created three seperate instance variables", "Completion", 1),
				new TestCase("Test 11", "Class constructor signature written correctly", "Completion", 1),
				new TestCase("Test 12", "Class constructor makes assignments to class variables", "Completion", 1)

			};

		//Almost every method throws an exception, just put it all under here

		try {
			String work = "Associative.java";
			String solution = "asnlib/Associative.java";

			File studentwork = new File(work);
			if (!studentwork.exists()) {
				System.out.println("Associative.java was not found. Did you name your file correctly?");
				return;
			}

			tests[0].setResult(true);

			//Report problems
			Parser parser = new Parser();
			boolean parsed = parser.parse(work);
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			tests[1].setResult(TestCase.compile(new File(work)));
			tests[2].setResult(true);

			//replace methods and create temporary files
			String[] sigs = {"void main(String[])", "int firstTwo()", "int lastTwo()"};
			File[] files = parser.replace(sigs, solution);

			//check files and give points for methods found
			for (int i = 0; i < files.length; i++) {
				if (files[i] == null) {
					System.out.println("Method not found: " + sigs[i]);
				} else {
					tests[i + 3].setResult(true);
				}
			}

			List<Expression> exs = parser.findExpressionsOfType("void main(String[])");
			if (exs != null) {
				boolean firstTwo = false;
				boolean lastTwo = false;

				for (Expression ex : exs) {
					if (ex instanceof MethodCallExpr) {
						MethodCallExpr mx = (MethodCallExpr)ex;
						String name = mx.getName().asString();
						if (name.equals("firstTwo")) firstTwo = true;
						else if (name.equals("lastTwo")) lastTwo = true;
					} else if (ex instanceof ObjectCreationExpr) {
						ObjectCreationExpr o = (ObjectCreationExpr)ex;
						if (o.getType().getName().asString().equals("Scanner")) {
							tests[6].setResult(true);
						} else if (o.getType().getName().asString().equals("Associative")) {
							tests[7].setResult(true);
						}
					}
				}

				tests[8].setResult(firstTwo && lastTwo);
			}

			int count = 0;
			for (FieldDeclaration f : parser.fields) {
				for (VariableDeclarator v : f.getVariables()) {
					if (v.getType().asString().equals("int")) {
						count++;
					}
				}
			}
			if (count == 3) tests[9].setResult(true);

			for (ConstructorDeclaration c : parser.constructors) {
				if (c.getDeclarationAsString(false, false, false).equals("Associative(int, int, int)")) {
					tests[10].setResult(true);
				}
			}

			count = 0;
			List<Expression> cex = parser.findExpressionsOfType("Associative(int, int, int)");
			if (cex != null) {
				for (Expression ex : cex) {
					if (ex instanceof AssignExpr) {
						count++;
					}
				}
			}
			if (count == 3) tests[11].setResult(true);

			TestCase.pushAll(tests);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
