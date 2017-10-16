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
				new TestCase("Test 1", "GRAVITY is assigned the value 9.81", "Completion", 1),
				new TestCase("Test 2", "GRAVITY is declared with final", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(TestCase.runMain(".", "Practice", null, "(?s).*9.81.*"));
			}

			Parser parser = new Parser();
			if (parser.parse("Practice.java")) {
				List<Expression> exs = parser.findExpressionsOfType("void main(String[])");
				if (exs != null) {
					for (Expression ex : exs) {
						if (ex instanceof VariableDeclarationExpr) {
							VariableDeclarationExpr v = (VariableDeclarationExpr)ex;
							for (VariableDeclarator vd : v.getVariables()) {
								if (vd.getName().asString().equals("GRAVITY")) {
									for (Modifier m : v.getModifiers()) {
										if (m.asString().toLowerCase().equals("final")) {
											tests[1].setResult(true);
										}
									}
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
