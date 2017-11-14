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
				new TestCase("Test 2", "findTopDonor() works correctly", "Completion", 2),
				new TestCase("Test 3", "findTopState() works correctly", "Correctness", 2),
				new TestCase("Test 4", "makeDonation() works correctly", "Correctness", 2)
			};

			if (!TestCase.compile(new File("BloodBank.java"))) {
				System.out.println("BloodBank.java does not compile.");
			} else {
				tests[0].setResult(true);
			}
			Parser parser = new Parser();
			if (parser.parse("BloodBank.java")) {

				String[] methods = {"int findTopDonor()", "int findTopState()", "void makeDonation(double, int, int)"};
				File[] files = parser.replace(methods, "asnlib/BloodBank.java");

				if (files[0] != null) {
					tests[1].setResult(TestCase.runMain(files[0].getParent(), "BloodBank", null, "(?s).*The top donor is 6.*"));
				}

				if (files[1] != null) {
					tests[2].setResult(TestCase.runMain(files[1].getParent(), "BloodBank", null, "(?s).*The top state is 2.*"));
				}

				if (files[2] != null) {
					tests[3].setResult(TestCase.runMain(files[2].getParent(), "BloodBank", null, "(?s).*New value is 7.0.*"));
				}

			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
