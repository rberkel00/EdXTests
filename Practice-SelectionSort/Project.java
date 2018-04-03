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
				new TestCase("Test 2", "Funciton call is made in main", "Completion", 1),
				new TestCase("Test 3", "Conditional statement is created", "Completion", 1),
				new TestCase("Test 4", "Output is correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s).*0, 10, 10, 10, 15, 21, 33, 33, 34, 43, 44, 50, 55, 67, 72, 73, 78, 87, 95, 96.*"));
			}

			Parser p = new Parser();
			if (p.parse("Practice.java")) {
				List<Node> mnodes = p.findPieces("void main(String[])");
				List<Node> snodes = p.findPieces("long selection(ArrayList<Comparable>)");
				for (Node n : mnodes) {
					if (n instanceof MethodCallExpr) {
						MethodCallExpr m = (MethodCallExpr)n;
						if (m.getName().asString().equals("selection")) {
							tests[1].setResult(true);
						}
					}
				}
				for (Node n : snodes) {
					if (n instanceof ConditionalExpr || n instanceof IfStmt || n instanceof SwitchStmt) {
						tests[2].setResult(true);
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
