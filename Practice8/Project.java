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
				new TestCase("Test 1", "Created two Thingy objects", "Completion", 1),
				new TestCase("Test 2", "Output is correct in first print statement", "Completion", 1),
				new TestCase("Test 3", "Output is correct in second print statement", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else if (!TestCase.compile(new File("Thingy.java"))) {
				System.out.println("Thingy.java does not compile.");
			} else {
				tests[1].setResult(TestCase.runMain(".", "Practice", null, "(?s).*input.4.gives.4.*"));
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*input.3.gives.11.*"));
			}

			Parser parser = new Parser();

			boolean objectone = false;

			if (parser.parse("Practice.java")) {
				List<Expression> exps = parser.findExpressionsOfType("void main(String[])");
				for (Expression e : exps) {
					if (e instanceof ObjectCreationExpr) {
						ObjectCreationExpr ae = (ObjectCreationExpr)e;
						if (objectone) {
							tests[0].setResult(true);
						} else {
							objectone = true;
						}
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
