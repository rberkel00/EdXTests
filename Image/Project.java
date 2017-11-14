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
				new TestCase("Test 2", "Repetition construct (loop) is created in filterRed()", "Completion", 1),
				new TestCase("Test 3", "pixels is initialized with the correct lengths", "Correctness", 1),
				new TestCase("Test 4", "Output is correct (there are no \"majority red\" pixels)", "Correctness", 1)
			};

			if (!TestCase.compile(new File("Image.java"))) {
				System.out.println("Image.java does not compile.");
			} else {
				tests[0].setResult(true);
				String output = TestCase.runMain(".", "Image", null);
				int count = 0;
				for (int i = 0; i < output.length(); i++) {
					i = output.indexOf("td style=", i);
					if (i == -1) i = output.length();
					else count++;
				}
				if (count == 240) tests[2].setResult(true);
			}
			Parser parser = new Parser();
			if (parser.parse("Image.java")) {

			String[] methods = {"void filterRed()"};
			File[] files = parser.replace(methods, "asnlib/Image.java");

			if (files[0] != null) {
				tests[3].setResult(!TestCase.runMain(files[0].getParent(), "Image", null, "(?s).*failed.*"));
			}

				List<Node> exps = parser.findPieces("void filterRed()");
				for (Node e : exps) {
					//System.out.println("Expression: " + e.toString() + " Type: " + e.getClass());
					if (e instanceof ForStmt) {
						tests[1].setResult(true);
					} else if (e instanceof DoStmt) {
						tests[1].setResult(true);
					} else if (e instanceof WhileStmt) {
						tests[1].setResult(true);
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
