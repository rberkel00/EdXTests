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
				new TestCase("Test 1", "Files compile", "Completion", 1),
				new TestCase("Test 2", "Created static method signature in Counter", "Completion", 1),
				new TestCase("Test 3", "made static method call in Practice", "Completion", 1),
				new TestCase("Test 4", "Correct output", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				if (!TestCase.compile(new File("Counter.java"))) {
					System.out.println("Counter.java does not compile.");
				} else {
					tests[0].setResult(true);
					tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s).*are 3 positive.*"));
				}
			}

			Parser parser = new Parser();

			if (parser.parse("Counter.java")) {
				for (MethodDeclaration c : parser.methods) {
					if (c.getDeclarationAsString(true, false, false).matches(".*static int positiveCounter\\(int\\[\\]\\)")) {
						tests[1].setResult(true);
					}
				}
			}

			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				List<Node> p = lpar.findPieces("void main(String[])");
				for (Node n : p) {
					if (n instanceof MethodCallExpr) {
						MethodCallExpr m = (MethodCallExpr)n;
						if (m.getName().asString().equals("positiveCounter")) {
							tests[2].setResult(true);
						}
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
