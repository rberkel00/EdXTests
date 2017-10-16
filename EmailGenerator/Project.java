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
				new TestCase("Test 1", "EmailGenerator.java exists", "Completion", 1),
				new TestCase("Test 2", "EmailGenerator.java compiles", "Completion", 1),
				new TestCase("Test 3", "EmailGenerator class header is written correctly", "Completion", 1),
				new TestCase("Test 4", "Scanner object is created in main method (only create one!)", "Completion", 1),
				new TestCase("Test 5", "Main method stores user input from scanner object", "Completion", 1),
				new TestCase("Test 6", "Main method uses makeUserName and makeEmail method calls", "Completion", 1),
				new TestCase("Test 7", "Main method signature is written correctly", "Completion", 1),
				new TestCase("Test 8", "makeUserName method signature is written correctly", "Completion", 1),
				new TestCase("Test 9", "makeEmail method signature is written correctly", "Completion", 1),
				new TestCase("Test 10", "makeUserName runs correctly", "Correctness", 1),
				new TestCase("Test 11", "makeEmail runs correctly", "Correctness", 1),
				new TestCase("Test 12", "Email is in lowercase", "Correctness", 1)
			};

			File studentwork = new File("EmailGenerator.java");
			if (!studentwork.exists()) {
				System.out.println("EmailGenerator.java was not found. Did you name your file correctly?");
				return;
			}

			tests[0].setResult(true);

			if (!TestCase.compile(new File("EmailGenerator.java"))) {
				System.out.println("EmailGenerator.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("EmailGenerator.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			tests[2].setResult(true);

			String[] methods = {"void main(String[])", "String makeUserName(String, String)", "String makeEmail(String, String)"};
			File[] files = parser.replace(methods, "asnlib/EmailGenerator.java");
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null) {
					tests[i + 6].setResult(true);
				}
			}

			boolean username = false;
			boolean email = false;

			List<Expression> exps = parser.findExpressionsOfType("void main(String[])");
			for (Expression e : exps) {
				if (e instanceof ObjectCreationExpr) {
					ObjectCreationExpr oce = (ObjectCreationExpr)e;
					if (oce.getType().asString().equals("Scanner")) {
						tests[3].setResult(true);
					}
				} if (e instanceof MethodCallExpr) {
					MethodCallExpr mce = (MethodCallExpr)e;
					if (mce.getName().asString().equals("makeUserName")) {
						username = true;
					} else if (mce.getName().asString().equals("makeEmail")) {
						email = true;
					} else if (mce.getName().asString().substring(0, 4).equals("next")) {
						tests[4].setResult(true);
					}
				}
			}

			if (username && email) tests[5].setResult(true);

			if (files[1] != null) {
				tests[9].setResult(TestCase.runMain(files[1].getParent(), "EmailGenerator", new File("asnlib/input.txt"), "(?s).*(r|R)(b|B)erkel@purdue.edu.*"));
				tests[11].setResult(TestCase.runMain(files[1].getParent(), "EmailGenerator", new File("asnlib/input.txt"), "(?s).*rberkel@purdue.edu.*"));
			}
			if (files[2] != null) {
				tests[10].setResult(TestCase.runMain(files[2].getParent(), "EmailGenerator", new File("asnlib/input.txt"), "(?s).*(r|R)(b|B)erkel@purdue.edu.*"));
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {

		}
	}
}
