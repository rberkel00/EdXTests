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
		try {
			TestCase[] tests = {
				new TestCase("Test 1", "Alphabet.java exists", "Completion", 1),
				new TestCase("Test 2", "Alphabet.java compiles", "Completion", 1),
				new TestCase("Test 3", "Scanner object is created in main method (only create one!)", "Completion", 1),
				new TestCase("Test 4", "Main method signature is written correctly", "Completion", 1),
				new TestCase("Test 5", "whatsMissing method signature is written correctly", "Completion", 1),
				new TestCase("Test 6", "Main method uses whatsMissing method call", "Completion", 1),
				new TestCase("Test 7", "whatsMissing uses a selection construct (if or switch statement) to identify if the letter can be found", "Completion", 1),
				new TestCase("Test 8", "whatsMissing uses a repetition construct (while, for, do-while, etc) to loop through the alphabet", "Completion", 1),
				new TestCase("Test 9", "main method prints output", "Correctness", 2),
				new TestCase("Test 10", "output of whatsMissing is correct", "Correctness", 8)
			};

			File studentwork = new File("Alphabet.java");
			if (!studentwork.exists()) {
				System.out.println("Alphabet.java was not found. Did you name your file correctly?");
				return;
			}

			tests[0].setResult(true);

			if (!TestCase.compile(new File("Alphabet.java"))) {
				System.out.println("Alphabet.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("Alphabet.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			String[] methods = {"void main(String[])", "String whatsMissing(String)"};
			File[] files = parser.replace(methods, "asnlib/Alphabet.java");
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null) {
					tests[i + 3].setResult(true);
				}
			}

			boolean username = false;
			boolean email = false;

			List<Node> exps = parser.findPieces("void main(String[])");
			for (Node e : exps) {
				if (e instanceof ObjectCreationExpr) {
					ObjectCreationExpr oce = (ObjectCreationExpr)e;
					if (oce.getType().asString().equals("Scanner")) {
						tests[2].setResult(true);
					}
				} else if (e instanceof MethodCallExpr) {
					MethodCallExpr mce = (MethodCallExpr)e;
					if (mce.getName().asString().equals("whatsMissing")) {
						tests[5].setResult(true);
					}
				}
			}

			List<Node> st = parser.findPieces("String whatsMissing(String)");
			for (Node s : st) {
				if (s instanceof ConditionalExpr) {
					tests[6].setResult(true);
				} else if (s instanceof IfStmt) {
					tests[6].setResult(true);
				} else if (s instanceof SwitchStmt) {
					tests[6].setResult(true);
				} else if (s instanceof DoStmt) {
					tests[7].setResult(true);
				} else if (s instanceof WhileStmt) {
					tests[7].setResult(true);
				} else if (s instanceof ForStmt) {
					tests[7].setResult(true);
				}
			}

			if (files[0] != null) {
				tests[8].setResult(TestCase.runMain(files[0].getParent(), "Alphabet", new File("asnlib/input1.txt"), "(?s).*letters.*abdijklqtuvxyz.*"));
			}

			if (files[1] != null) {
				tests[9].setResult(TestCase.runMain(files[1].getParent(), "Alphabet", new File("asnlib/input1.txt"), "(?s).*abdijklqtuvxyz.*"));
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
