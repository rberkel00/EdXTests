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
				new TestCase("Test 2", "Correct constructor signatures (with correct parameter names)", "Completion", 1),
				new TestCase("Test 3", "Assign instance variables with \"this\"", "Completion", 1),
				new TestCase("Test 4", "Output is correct (increment numberOfPirates in constructor)", "Completion", 1)
			};

			if (!TestCase.compile(new File("Pirate.java"))) {
				System.out.println("Pirate.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();

			if (parser.parse("Pirate.java")) {
				String[] methods = {"Pirate(String, int, int, boolean, boolean)"};
				File[] files = parser.replace(methods, "asnlib/Pirate.java");
				if (files[0] != null) {
					tests[3].setResult(TestCase.runMain(files[0].getParent(), "Pirate", null, "(?s).*2.*"));
				}
				int count = 0;
				List<Node> nodes = parser.findPieces("Pirate(String, int, int, boolean, boolean)");
				for (Node n : nodes) {
						if (n instanceof ThisExpr) count++;
				}
				if (count >= 5) {
					tests[2].setResult(true);
				}
				for (ConstructorDeclaration c : parser.constructors) {
					if (c.getDeclarationAsString(false, false, true).equals("Pirate(String name, int swordsmanship, int agility, boolean hasParrot, boolean hasPegLeg)")) {
						tests[1].setResult(true);
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
