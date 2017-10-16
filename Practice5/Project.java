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
				new TestCase("Test 1", "b is declared as a boolean", "Completion", 1),
				new TestCase("Test 2", "b is initialized to the value of 8>=3", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[1].setResult(TestCase.runMain(".", "Practice", null, "(?s).*ever.true.*"));
			}

			Parser parser = new Parser();
			if (parser.parse("Practice.java")) {
				List<Expression> exs = parser.findExpressionsOfType("void main(String[])");
				if (exs != null) {
					for (Expression ex : exs) {
						if (ex instanceof VariableDeclarationExpr) {
							VariableDeclarationExpr v = (VariableDeclarationExpr)ex;
							for (VariableDeclarator vd : v.getVariables()) {
								if (vd.getName().asString().equals("b") && vd.getType().asString().equals("boolean")) {
									tests[0].setResult(true);
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
