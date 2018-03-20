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
				new TestCase("Test 2", "Correct method header for the doSomething method", "Completion", 1),
				new TestCase("Test 3", "Correct Output", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			/*Parser lpar = new Parser();
			if (lpar.parse("MyException.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getDeclarationAsString(false, false, false).matches(".*public class MyException extends Exception.*")) {
						tests[1].setResult(true);
					}
				}
				tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s).*CS.*Too short!.*"));
			}*/



			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getName().asString().equals("doSomething")) {
						//tests[2].setResult(true);
						for (Modifier mo : m.getModifiers()) {
							if (mo.asString().equals("static")) {
								tests[1].setResult(true);
							}
						}
					}
				}
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*CS.*Too short!.*"));
			}




 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
