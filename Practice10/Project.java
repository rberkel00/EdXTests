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
				new TestCase("Test 1", "s2 output is correct", "Completion", 1),
				new TestCase("Test 2", "s3 output is correct", "Completion", 1),
				new TestCase("Test 3", "Used substring method of String class to get values for s2 and s3", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(TestCase.runMain(".", "Practice", null, "(?s).*s2: model\n.*"));
				tests[1].setResult(TestCase.runMain(".", "Practice", null, "(?s).*s2: modern major\n.*"));
			}

			Parser parser = new Parser();

			boolean subone = false;

			if (parser.parse("Practice.java")) {
				tests[1].setResult(true);
				List<Expression> exps = parser.findExpressionsOfType("void main(String[])");
				for (Expression e : exps) {
					if (e instanceof MethodCallExpr) {
						MethodCallExpr ae = (MethodCallExpr)e;
						if (ae.getName().asString().equals("substring")) {
							if (subone) tests[2].setResult(true);
							else subone = true;
						}
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
