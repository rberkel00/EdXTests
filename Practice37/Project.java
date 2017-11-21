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
				new TestCase("Test 2", "Created a static method", "Completion", 1),
				new TestCase("Test 3", "Method has correct variable names and int types (a, b, and c)", "Completion", 1),
				new TestCase("Test 4", "Correct output", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s).*12.*101.*"));
			}

			Parser parser = new Parser();

			if (parser.parse("Practice.java")) {
				for (MethodDeclaration m : parser.methods) {
					if (m.getDeclarationAsString(true, false, false).matches(".*static.*")) {
						tests[1].setResult(true);
					}
					if (m.getDeclarationAsString(false, false, true).matches(".*addMe\\(int a, int b, int c\\).*")) {
						tests[2].setResult(true);
					}
				}
			}




 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
