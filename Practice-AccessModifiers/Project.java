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
				new TestCase("Test 2", "Correct field declarations", "Completion", 1),
				new TestCase("Test 3", "Correct accessor method header", "Completion", 1),
				new TestCase("Test 4", "Correct output", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java")) || !TestCase.compile(new File("Runner.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[3].setResult(TestCase.runMain(".", "Runner", null, "(?s).*1.*"));
			}

			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getDeclarationAsString(true, false, false).equals("public int getA()")) {
						tests[2].setResult(true);
					}
				}
				boolean[] vars = new boolean[3];
				for (FieldDeclaration f : lpar.fields) {
					for (Modifier mo : f.getModifiers()) {
						if (mo.asString().equals("private")) {
							for (VariableDeclarator v : f.getVariables()) {
								if (v.getName().asString().equals("a")) {
									vars[0] = true;
								} else if (v.getName().asString().equals("b")) {
									vars[1] = true;
								} else if (v.getName().asString().equals("c")) {
									vars[2] = true;
								}
							}
						}
					}
				}
				if (vars[0] && vars[1] && vars[2]) tests[1].setResult(true);
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
