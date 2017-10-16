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
				new TestCase("Test 1", "Output is correct", "Completion", 1),
				new TestCase("Test 2", "Used s1 to get values for length, c1, c5, and s2 (did not hard code values)", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(TestCase.runMain(".", "Practice", null, "(?s).*West Lafayette, Indiana, USA.*is 28 characters long.*ter 1 of s1 is .e. and character 5 is .L.*string s2 is .eL.*"));
			}

			Parser parser = new Parser();

			if (parser.parse("Practice.java")) {
				tests[1].setResult(true);
				List<Expression> exps = parser.findExpressionsOfType("void main(String[])");
				for (Expression e : exps) {
					if (e instanceof AssignExpr) {
						AssignExpr ae = (AssignExpr)e;
						Expression s = ae.getTarget();
						if (s instanceof NameExpr) {
							NameExpr n = (NameExpr)s;
							if (n.getName().asString().equals("length") || n.getName().asString().equals("c1") || n.getName().asString().equals("c5") || n.getName().asString().equals("s2")) {
								if(ae.getValue() instanceof LiteralExpr) {
									tests[1].setResult(false);
								}
							}
						}
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
