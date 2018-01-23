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
				new TestCase("Test 2", "doItAgain method signature exists", "Completion", 1),
				new TestCase("Test 3", "doItAgain method is static", "Completion", 1),
				new TestCase("Test 4", "doItAgain method parameters and return value are correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getName().asString().equals("doItAgain")) {
						tests[1].setResult(true);
						for (Modifier mo : m.getModifiers()) {
							if (mo.asString().equals("static")) {
								tests[2].setResult(true);
							}
						}
						if (m.getDeclarationAsString(false, false, false).equals("int doItAgain(int)")) {
							tests[3].setResult(true);
						}
					}
				}
			}
 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
