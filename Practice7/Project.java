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
				new TestCase("Test 1", "b output is correct", "Completion", 1),
				new TestCase("Test 2", "c output is correct", "Completion", 1),
				new TestCase("Test 3", "d output is correct", "Completion", 1),
				new TestCase("Test 4", "f output is correct", "Completion", 1),
				new TestCase("Test 5", "b assignment operator uses ++", "Completion", 1),
				new TestCase("Test 6", "c assignment operator uses +=", "Completion", 1),
				new TestCase("Test 7", "d assignment operator uses -=", "Completion", 1),
				new TestCase("Test 8", "f assignment operator uses *=", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(TestCase.runMain(".", "Practice", null, "(?s).*b.is.2.*"));
				tests[1].setResult(TestCase.runMain(".", "Practice", null, "(?s).*c.is.9.*"));
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*d.is.0.*"));
				tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s).*f.is.40.*"));
			}

			Parser parser = new Parser();

			if (parser.parse("Practice.java")) {
				List<Expression> exps = parser.findExpressionsOfType("void main(String[])");
				for (Expression e : exps) {
					if (e instanceof AssignExpr) {
						AssignExpr ae = (AssignExpr)e;
						String s = ae.getOperator().toString();
						if (s.equals("PLUS")) {
							tests[5].setResult(true);
						} else if (s.equals("MINUS")) {
							tests[6].setResult(true);
						} else if (s.equals("MULTIPLY")) {
							tests[7].setResult(true);
						}
					} else if (e instanceof UnaryExpr) {
						UnaryExpr ue = (UnaryExpr)e;
						if (ue.getOperator().toString().equals("PREFIX_INCREMENT") || ue.getOperator().toString().equals("POSTFIX_INCREMENT")) {
							tests[4].setResult(true);
						}
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
