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
				new TestCase("Test 2", "studentPair method signature is correct", "Completion", 1),
				new TestCase("Test 3", "studentPair returns the two Strings separated by a comma", "Completion", 1),
				new TestCase("Test 4", "canPair method signature is correct", "Completion", 1),
				new TestCase("Test 5", "canPair returns the correct result", "Completion", 1),
				new TestCase("Test 6", "classPairs method signature is correct", "Completion", 1),
				new TestCase("Test 7", "classPairs returns the correct pairings", "Completion", 1)
			};

			if (!TestCase.compile(new File("ClassroomPairs.java"))) {
				System.out.println("ClassroomPairs.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser p = new Parser();
			if (p.parse("ClassroomPairs.java")) {
				String[] methods = {"String studentPair(String, String)","boolean canPair(String[][])","String[] classPairs(String[][])"};
				File[] files = p.replace(methods, "asnlib/ClassroomPairs.java");
				if (files[0] != null) {
					tests[1].setResult(true);
					tests[2].setResult(TestCase.runMain(files[0].getParent(), "ClassroomPairs", null, "(?s).*test01: Chris, Taylor.*"));
				}
				if (files[1] != null) {
					tests[3].setResult(true);
					tests[4].setResult(TestCase.runMain(files[1].getParent(), "ClassroomPairs", null, "(?s).*test02: true.*test03: false.*"));
				}
				if (files[2] != null) {
					tests[5].setResult(true);
					tests[6].setResult(TestCase.runMain(files[2].getParent(), "ClassroomPairs", null, "(?s).*test04:.*Robyn, Chris.*Brenda, Taylor.*Ruger, Bandit.*Ken, Vicki.*Jess, Hank.*Robin, Marshall.*"));
 				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
