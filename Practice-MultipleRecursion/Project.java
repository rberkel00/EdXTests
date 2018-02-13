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
				new TestCase("Test 1", "File compiles", "Completion", 1),
				new TestCase("Test 2", "Correct recurisive method call", "Completion", 1),
				new TestCase("Test 3", "Correct ouput", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				List<Node> nodes = lpar.findPieces("int mathy(int)");
				for (Node n : nodes) {
					if (n instanceof MethodCallExpr) {
						MethodCallExpr m = (MethodCallExpr)n;
						if (m.getName().asString().equals("mathy")) {
							tests[1].setResult(true);
						}
					}
				}
				System.out.println(TestCase.runMain(".", "Practice", null));
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*7.*"));

			}
 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
