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
				new TestCase("Test 1", "OnlineOrder.java exists", "Completion", 1),
				new TestCase("Test 2", "OnlineOrder.java compiles", "Completion", 1),
				new TestCase("Test 3", "Instance variables are correct", "Completion", 1),
				new TestCase("Test 4", "Method signatures are correct", "Completion", 1),
				new TestCase("Test 5", "Constructor signature is correct", "Completion", 1),
				new TestCase("Test 6", "Constructor assigns correct values to instance variables", "Completion", 1),
				new TestCase("Test 7", "changeOrder assigns correct values to instance variables", "Completion", 1),
				new TestCase("Test 8", "Accessor methods return current value of corresponding instance variables", "Completion", 1),
				new TestCase("Test 9", "toString method returns correct value", "Completion", 1),
				new TestCase("Test 10", "TAX_RATE is a constant value", "Completion", 1),
				new TestCase("Test 11", "changeOrder checks input values (negative input)", "Completion", 1)
			};
			File lot = new File("OnlineOrder.java");
			if (!lot.exists()) {
				System.out.println("OnlineOrder.java was not found. Did you name your file correctly?");
			}
			else tests[0].setResult(true);

			if (!TestCase.compile(new File("OnlineOrder.java"))) {
				System.out.println("OnlineOrder.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser parser = new Parser();
			if (parser.parse("OnlineOrder.java")) {
				String[] methods = {"OnlineOrder(String, int, int, int, int)","void changeOrder(int, int, int, int)","double getTotalCost()", "double getTax()", "String toString()"};
				File[] files = parser.replace(methods, "asnlib/OnlineOrder.java");
				boolean[] acc = new boolean[4];
				boolean[] acc2 = new boolean[2];

				if (files[0] != null) {
					tests[4].setResult(true);
					tests[5].setResult(TestCase.runMain(files[0].getParent(), "OnlineOrder", null, "(?s).*tc1: 33.93.*"));
				}
				if (files[1] != null) {
					acc[0] = true;
					tests[6].setResult(TestCase.runMain(files[1].getParent(), "OnlineOrder", null, "(?s).*tc2: 42.91.*"));
					tests[10].setResult(TestCase.runMain(files[1].getParent(), "OnlineOrder", null, "(?s).*tc3: 9.98.*"));
				}
				if (files[2] != null) {
					acc[1] = true;
					acc2[0] = TestCase.runMain(files[2].getParent(), "OnlineOrder", null, "(?s).*tc1: 33.93.*");
 				}
				if (files[3] != null) {
					acc[2] = true;
					acc2[1] = TestCase.runMain(files[3].getParent(), "OnlineOrder", null, "(?s).*t1: 2.3.*");
				}
				if (files[4] != null) {
					acc[3] = true;
					tests[8].setResult(TestCase.runMain(files[4].getParent(), "OnlineOrder", null, "(?s).*ts1: Spongebob.*\\$10.6.*"));
				}

				tests[3].setResult(true);
				for (int i = 0; i < 4; i++) {
					if (!acc[i]) {
						tests[3].setResult(false);
					}
				}

				tests[7].setResult(true);
				for (int i = 0; i < 2; i++) {
					if (!acc2[i]) {
						tests[7].setResult(false);
					}
				}

				int countfields = 0;
				for (FieldDeclaration f : parser.fields) {
					boolean constant = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("final")) {
							constant = true;
						}
					}
					for (VariableDeclarator v : f.getVariables()) {
						if (v.getName().asString().equals("orderName") && v.getType().asString().equals("String") && !constant ||
								v.getName().asString().equals("totalCost") && v.getType().asString().equals("double") && !constant ||
								v.getName().asString().equals("tax") && v.getType().asString().equals("double") && !constant ||
								v.getName().asString().equals("numTurkey") && v.getType().asString().equals("int") && !constant ||
								v.getName().asString().equals("numItalian") && v.getType().asString().equals("int") && !constant ||
								v.getName().asString().equals("numVeggie") && v.getType().asString().equals("int") && !constant ||
								v.getName().asString().equals("numBLT") && v.getType().asString().equals("int") && !constant) {
									countfields++;
								}
						if (v.getName().asString().equals("TAX_RATE") && v.getType().asString().equals("double") && constant) {
							tests[9].setResult(true);
						}
					}
				}
				if (countfields >= 7) {
					tests[2].setResult(true);
				}

			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
