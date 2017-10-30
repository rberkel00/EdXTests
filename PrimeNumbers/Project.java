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
				new TestCase("Test 1", "Prime.java exists", "Completion", 1),
				new TestCase("Test 2", "Prime.java compiles", "Completion", 1),
				new TestCase("Test 3", "Scanner object is created in main method (only create one!)", "Completion", 1),
				new TestCase("Test 4", "Main method signature is written correctly", "Completion", 1),
				new TestCase("Test 5", "isPrime method signature is written correctly", "Completion", 1),
				new TestCase("Test 6", "Main method uses isPrime method call", "Completion", 1),
				new TestCase("Test 7", "isPrime uses a selection construct (if or switch statement) to identify factor", "Completion", 1),
				new TestCase("Test 8", "isPrime uses a repetition construct (while, for, do-while, etc) to identify factor", "Completion", 1),
				new TestCase("Test 9", "Main method prints the orignal number with the answer", "Correctness", 1),
				new TestCase("Test 10", "output is correct case 1 (input is 0, which is not prime)", "Correctness", 2),
				new TestCase("Test 11", "output is correct case 2 (input is a large prime number)", "Correctness", 2),
				new TestCase("Test 12", "output is correct case 3 (input is a large non-prime number)", "Correctness", 2)
			};

			File studentwork = new File("Prime.java");
			if (!studentwork.exists()) {
				System.out.println("Prime.java was not found. Did you name your file correctly?");
				return;
			}

			tests[0].setResult(true);

			if (!TestCase.compile(new File("Prime.java"))) {
				System.out.println("Prime.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("Prime.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			String[] methods = {"void main(String[])", "boolean isPrime(int)"};
			File[] files = parser.replace(methods, "asnlib/Prime.java");
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
					if (mce.getName().asString().equals("isPrime")) {
						tests[5].setResult(true);
					}
				}
			}

			List<Node> ex = parser.findPieces("boolean isPrime(int)");
			for (Node es : ex) {
				if (es instanceof ConditionalExpr) {
					tests[6].setResult(true);
				}
			}

			List<Node> st = parser.findPieces("boolean isPrime(int)");
			for (Node s : st) {
				if (s instanceof IfStmt) {
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
				tests[8].setResult(TestCase.runMain(files[0].getParent(), "Prime", new File("asnlib/input1.txt"), "(?s).*0.*"));
			}

			if (files[1] != null) {
				tests[9].setResult(TestCase.runMain(files[1].getParent(), "Prime", new File("asnlib/input1.txt"), "(?s).*not prime.*"));
				tests[10].setResult(TestCase.runMain(files[1].getParent(), "Prime", new File("asnlib/input2.txt"), "(?s).*is prime.*"));
				tests[11].setResult(TestCase.runMain(files[1].getParent(), "Prime", new File("asnlib/input3.txt"), "(?s).*not prime.*"));
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
		}
	}
}
