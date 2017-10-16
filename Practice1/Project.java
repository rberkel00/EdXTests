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
				new TestCase("Test 1", "Addition and assignment to x", "Completion", 1)
			};

			tests[0].setResult(TestCase.runMain(".", "Practice", new File("asnlib/input.txt"), "(?s).*5.*"));

 			TestCase.pushAll(tests);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
