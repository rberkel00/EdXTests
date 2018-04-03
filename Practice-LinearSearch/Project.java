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
				new TestCase("Test 2", "Loop is created", "Completion", 1),
				new TestCase("Test 3", "Conditional statement is created", "Completion", 1),
				new TestCase("Test 4", "Output is correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s).*position 304 of our list.*"));
			}

			Parser p = new Parser();
			if (p.parse("Practice.java")) {
				List<Node> snodes = p.findPieces("int linear(ArrayList<Comparable>, Comparable)");
				for (Node n : snodes) {
					if (n instanceof ForeachStmt || n instanceof ForStmt || n instanceof WhileStmt || n instanceof DoStmt) {
						tests[1].setResult(true);
					} else if (n instanceof ConditionalExpr || n instanceof IfStmt || n instanceof SwitchStmt) {
						tests[2].setResult(true);
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
