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
				new TestCase("Test 1", "getQuarters implementation", "Completion", 1),
				new TestCase("Test 2", "getDimes implemenation", "Completion", 1),
				new TestCase("Test 3", "getNickels implementation", "Completion", 1),
				new TestCase("Test 4", "getPennies implementation", "Completion", 1),
				new TestCase("Test 5", "GiveChange compiles", "Completion", 1)
			};

			if (!TestCase.compile(new File("GiveChange.java"))) {
				System.out.println("EmailGenerator.java does not compile.");
			} else {
				tests[4].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("GiveChange.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			String[] methods = {"int getQuarters(int)", "int getDimes(int, int)", "int getNickels(int, int, int)", "int getPennies(int, int, int, int)"};
			File[] files = parser.replace(methods, "asnlib/GiveChange.java");

			if (files[0] != null) {
				tests[0].setResult(TestCase.runMain(files[0].getParent(), "GiveChange", null, "(?s).* 7 quarters.*"));
			}
			if (files[1] != null) {
				tests[1].setResult(TestCase.runMain(files[1].getParent(), "GiveChange", null, "(?s).* 2 dimes.*"));
			}
			if (files[2] != null) {
				tests[2].setResult(TestCase.runMain(files[2].getParent(), "GiveChange", null, "(?s).* 0 nickels.*"));
			}
			if (files[3] != null) {
				tests[3].setResult(TestCase.runMain(files[3].getParent(), "GiveChange", null, "(?s).* 2 pennies.*"));
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {

		}
	}
}
