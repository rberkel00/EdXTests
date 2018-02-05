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
				new TestCase("Test 2", "Correct array list output", "Completion", 1),
				new TestCase("Test 3", "Correct array list size", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[1].setResult(TestCase.runMain(".", "Practice", null, "(?s).*LaKisha\nMicheal\nGrace\nMicheal.*"));
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*4.*"));
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
