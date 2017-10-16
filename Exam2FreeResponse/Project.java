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
				new TestCase("Test 1", "Precedent.java compiles", "Completion", 5),
				new TestCase("Test 2", "whatsBefore method signature is correct", "Completion", 5),
				new TestCase("Test 3", "whatsBefore functions as expected", "Completion", 5)
			};

			if (!TestCase.compile(new File("Precedent.java"))) {
				System.out.println("Precedent.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("Precedent.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any more grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			String[] methods = {"String whatsBefore(String, String)"};
			File[] files = parser.replace(methods, "asnlib/Precedent.java");
			if (files[0] != null) {
					tests[1].setResult(true);
			}

			if (files[0] != null) {
				tests[2].setResult(TestCase.runMain(files[0].getParent(), "Precedent", null, "(?s).*hello g.*"));
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {

		}
	}
}
