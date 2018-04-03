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
				new TestCase("Test 2", "Two loops are created", "Completion", 1),
				new TestCase("Test 3", "Funciton call is made in main", "Completion", 1),
				new TestCase("Test 4", "Conditional statement is created", "Completion", 1),
				new TestCase("Test 5", "Output is correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[4].setResult(TestCase.runMain(".", "Practice", null, "(?s).*0, 10, 10, 10, 15, 21, 33, 33, 34, 43, 44, 50, 55, 67, 72, 73, 78, 87, 95, 96.*"));
			}

			Parser p = new Parser();
			if (p.parse("Practice.java")) {
				List<Node> mnodes = p.findPieces("void main(String[])");
				List<Node> snodes = p.findPieces("long bubble(ArrayList<Comparable>)");
				for (Node n : mnodes) {
					if (n instanceof MethodCallExpr) {
						MethodCallExpr m = (MethodCallExpr)n;
						if (m.getName().asString().equals("bubble")) {
							tests[2].setResult(true);
						}
					}
				}
				int count = 0;
				for (Node n : snodes) {
					if (n instanceof ForeachStmt || n instanceof ForStmt || n instanceof WhileStmt || n instanceof DoStmt) {
						count++;
					} else if (n instanceof ConditionalExpr || n instanceof IfStmt || n instanceof SwitchStmt) {
						tests[3].setResult(true);
					}
				}
				if (count > 1) tests[1].setResult(true);
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
