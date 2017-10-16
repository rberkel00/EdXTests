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
				new TestCase("Test 1", "z is set to x divided by y", "Completion", 1),
				new TestCase("Test 2", "z does not lose precision", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(TestCase.runMain(".", "Practice", null, "(?s).*6.*10.*0.*"));
				tests[1].setResult(TestCase.runMain(".", "Practice", null, "(?s).*6.*10.*0.6.*"));
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
