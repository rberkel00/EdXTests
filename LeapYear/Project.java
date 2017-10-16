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
				new TestCase("Test 1", "LeapYear.java exists", "Completion", 1),
				new TestCase("Test 2", "LeapYear.java compiles", "Completion", 1),
				new TestCase("Test 3", "Scanner object is created in main method (only create one!)", "Completion", 1),
				new TestCase("Test 4", "Main method signature is written correctly", "Completion", 1),
				new TestCase("Test 5", "isLeapYear method signature is written correctly", "Completion", 1),
				new TestCase("Test 6", "output is correct case 1 (year is only divisible by 4)", "Correctness", 2),
				new TestCase("Test 7", "output is correct case 2 (year is divisible by 100 but not 400)", "Correctness", 2),
				new TestCase("Test 8", "output is correct case 3 (year is divisible by 400)", "Correctness", 2),
				new TestCase("Test 9", "output is correct case 4 (year is not divisible by 4)", "Correctness", 2)
			};

			File studentwork = new File("LeapYear.java");
			if (!studentwork.exists()) {
				System.out.println("LeapYear.java was not found. Did you name your file correctly?");
				return;
			}

			tests[0].setResult(true);

			if (!TestCase.compile(new File("LeapYear.java"))) {
				System.out.println("LeapYear.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("LeapYear.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			String[] methods = {"void main(String[])", "boolean isLeapYear(int)"};
			File[] files = parser.replace(methods, "asnlib/LeapYear.java");
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null) {
					tests[i + 3].setResult(true);
				}
			}

			boolean username = false;
			boolean email = false;

			List<Expression> exps = parser.findExpressionsOfType("void main(String[])");
			for (Expression e : exps) {
				if (e instanceof ObjectCreationExpr) {
					ObjectCreationExpr oce = (ObjectCreationExpr)e;
					if (oce.getType().asString().equals("Scanner")) {
						tests[2].setResult(true);
					}
				}
			}

			if (files[1] != null) {
				tests[5].setResult(TestCase.runMain(files[1].getParent(), "LeapYear", new File("asnlib/input1.txt"), "(?s).*The year 96 is a leap year.*"));
				tests[6].setResult(TestCase.runMain(files[1].getParent(), "LeapYear", new File("asnlib/input2.txt"), "(?s).*The year 300 is not a leap year.*"));
				tests[7].setResult(TestCase.runMain(files[1].getParent(), "LeapYear", new File("asnlib/input3.txt"), "(?s).*The year 1600 is a leap year.*"));
				tests[8].setResult(TestCase.runMain(files[1].getParent(), "LeapYear", new File("asnlib/input4.txt"), "(?s).*The year 77 is not a leap year.*"));
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
		}
	}
}
