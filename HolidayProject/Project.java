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
				new TestCase("Test 1", "Delievered at least 1 present before running out of carrots", "Completion", 1),
				new TestCase("Test 2", "Delievered all of the presents", "Completion", 1),
				new TestCase("Test 3", "Delievered all of the presents in under 100 steps", "Completion", 1)
			};
			File lot = new File("ScannerClaus.java");
			if (!lot.exists()) {
				System.out.println("ScannerClaus.java was not found. Did you name your file correctly?");
			}

			if (!TestCase.compile(new File("ScannerClaus.java"))) {
				System.out.println("ScannerClaus.java does not compile.");
			}

			String output = TestCase.runMain(".", "HolidayRunner", new File("asnlib/input1.txt"));
			if (output != null) {
				tests[0].setResult(!output.matches("(?s).*6 presents left to deliver.*") && output.matches("(?s).*presents left to deliver.*"));
				tests[1].setResult(output.matches("(?s).*You delivered all of the presents.*"));
				tests[2].setResult(tests[1].getResult() && output.matches("(?s).*Steps: \\d?\\d? .*"));
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
