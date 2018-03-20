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
				new TestCase("Test 2", "Correct header for the try-catch block and block that catches the
									 ArithmeticException", "Completion", 1)
				//new TestCase("Test 3", "Correct header for the block that catches theArithmeticException", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				//File compiles test
				tests[0].setResult(true);

			}


			Parser parser = new Parser();

			if (parser.parse("Practice.java")) {
				List<Node> exps = parser.findPieces("void main(String[])");
				for (Node e : exps) {
					//System.out.println("Expression: " + e.toString() + " Type: " + e.getClass());
					if (e instanceof TryStmt) {
						tests[1].setResult(true);
					}
				}
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
