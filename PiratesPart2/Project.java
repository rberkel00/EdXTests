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
				new TestCase("Test 2", "Method signature written properly (with parameter p)", "Completion", 1)
			};

			if (!TestCase.compile(new File("Pirate.java"))) {
				System.out.println("Pirate.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();

			if (parser.parse("Pirate.java")) {
				for (MethodDeclaration m : parser.methods) {
					if (m.getDeclarationAsString(false, false, true).equals("String swashbuckle(Pirate p)")) {
						tests[1].setResult(true);
					}
				}

			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
