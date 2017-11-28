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
				new TestCase("Test 2", "Two getUser methods exist", "Completion", 1),
				new TestCase("Test 3", "Second getUser method signature is correct", "Completion", 1)
			};

			TestCase.compile(new File("User.java"));
			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			int count = 0;
			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getDeclarationAsString(false, false, false).matches(".*getUser.*")) {
						count++;
					}
					if (m.getDeclarationAsString(false, false, false).equals("int getUser(int)")) {
						tests[2].setResult(true);
					}
				}
				if (count > 1) tests[1].setResult(true);
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
