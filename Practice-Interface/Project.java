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
				new TestCase("Test 2", "Correct method header", "Completion", 1),
				new TestCase("Test 3", "Correct output", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				//File compiles test
				tests[0].setResult(true);

			}

			Parser lpar2 =

			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getDeclarationAsString(false, false, false).matches(".*includeMe.*")) {
						//Correct method header test
						tests[1].setResult(true);
					}
				}
				System.out.println(TestCase.runMain(".", "Practice", null));
				//Correct output test
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*A practical method.*"));
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
