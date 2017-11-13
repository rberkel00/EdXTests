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
				new TestCase("Test 1", "WordList.java exists", "Completion", 1),
				new TestCase("Test 2", "WordList.java compiles", "Completion", 1),
				new TestCase("Test 3", "addWord case 2", "Correctness", 1),
				new TestCase("Test 4", "addWord case 3", "Correctness", 1),
				new TestCase("Test 5", "addWord case 1", "Correctness", 1),
				new TestCase("Test 6", "findWord when word is present", "Correctness", 1),
				new TestCase("Test 7", "findWord when word is not present", "Correctness", 1),
				new TestCase("Test 8", "removeWord when word is present", "Correctness", 1),
				new TestCase("Test 9", "removeWord when word is not present", "Correctness", 1)
			};

			File studentwork = new File("WordList.java");
			if (!studentwork.exists()) {
				System.out.println("WordList.java was not found. Did you name your file correctly?");
				return;
			}

			tests[0].setResult(true);

			if (!TestCase.compile(new File("WordList.java"))) {
				System.out.println("WordList.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("WordList.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			String[] methods = {"int addWord(String)", "int findWord(String)", "void removeWord(String)"};
			File[] files = parser.replace(methods, "asnlib/WordList.java");
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null) {
					tests[i + 3].setResult(true);
				}
			}

			if (files[0] != null) {
				tests[2].setResult(TestCase.runMain(files[0].getParent(), "WordList", new File("asnlib/input1.txt"), "(?s).*1.0.*"));
				tests[3].setResult(TestCase.runMain(files[0].getParent(), "WordList", new File("asnlib/input2.txt"), "(?s).*1.2.3.2.*"));
				tests[4].setResult(TestCase.runMain(files[0].getParent(), "WordList", new File("asnlib/input3.txt"), "(?s).*1.2.2.*"));
			}

			if (files[1] != null) {
				tests[5].setResult(TestCase.runMain(files[1].getParent(), "WordList", new File("asnlib/input4.txt"), "(?s).*1.2.1.*"));
				tests[6].setResult(TestCase.runMain(files[1].getParent(), "WordList", new File("asnlib/input5.txt"), "(?s).*1.2.-1.*"));
			}

			if (files[2] != null) {
				tests[7].setResult(TestCase.runMain(files[2].getParent(), "WordList", new File("asnlib/input6.txt"), "(?s).*1.2.3.-1.*"));
				tests[8].setResult(TestCase.runMain(files[2].getParent(), "WordList", new File("asnlib/input7.txt"), "(?s).*1.2.*"));
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
		}
	}
}
