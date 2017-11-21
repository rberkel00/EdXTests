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
				new TestCase("Test 2", "Created constructor signature correctly (parameter names must be hours and minutes)", "Completion", 1),
				new TestCase("Test 3", "used \"this\" keyword to set instance variables in constructer body", "Completion", 1),
				new TestCase("Test 4", "Correct output", "Completion", 1)
			};

			if (!TestCase.compile(new File("DigitalClock.java"))) {
				System.out.println("DigitalClock.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[3].setResult(TestCase.runMain(".", "DigitalClock", null, "(?s).*3.45.*"));
			}

			Parser parser = new Parser();

			if (parser.parse("DigitalClock.java")) {
				for (ConstructorDeclaration c : parser.constructors) {
					if (c.getDeclarationAsString(false, false, true).matches("DigitalClock\\(int hours, int minutes\\)")) {
						tests[1].setResult(true);
					}
				}
				List<Node> nodes = parser.findPieces("DigitalClock(int, int)");
				for (Node n : nodes) {
					if (n instanceof ThisExpr) {
						tests[2].setResult(true);
					}
				}
			}




 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
