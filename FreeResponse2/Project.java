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
		TestCase[] tests = {
			new TestCase("Test 1", "File compiles", "Completion", 1),
			new TestCase("Test 2", "Conditional statements are used in showRewards: switch or if", "Completion", 1),
			new TestCase("Test 3", "Conditional statements are used in getReward: switch or if", "Completion", 1),
			new TestCase("Test 4", "showRewards correct if at least 50 points", "Completion", 1),
			new TestCase("Test 5", "showRewards correct if at least 100 points", "Completion", 1),
			new TestCase("Test 6", "showRewards correct if at least 200 points", "Completion", 1),
			new TestCase("Test 7", "showRewards correct if at least 5000 points", "Completion", 1),
			new TestCase("Test 9", "getReward correct if choice is 1 (includes case where not enough points)", "Completion", 1),
			new TestCase("Test 10", "getReward correct if choice is 2 (includes case where not enough points)", "Completion", 1),
			new TestCase("Test 11", "getReward correct if choice is 3 (includes case where not enough points)", "Completion", 1),
			new TestCase("Test 12", "getReward correct if choice is 4 (includes case where not enough points)", "Completion", 1),
		};
		try {

			if (!TestCase.compile(new File("Rewards.java"))) {
				System.out.println("Rewards.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();
			boolean parsed = parser.parse("Rewards.java");
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			List<Statement> statements = parser.findStatements("String showRewards()");
			for (Statement s : statements) {
				if (s instanceof IfStmt || s instanceof SwitchStmt || s instanceof SwitchEntryStmt) {
					tests[1].setResult(true);
				}
			}

			List<Statement> sa = parser.findStatements("String getReward(int)");
			for (Statement s : sa) {
				if (s instanceof IfStmt || s instanceof SwitchStmt || s instanceof SwitchEntryStmt) {
					tests[2].setResult(true);
				}
			}

			List<Expression> expressions = parser.findExpressionsOfType("String showRewards()");
			for (Expression e : expressions) {
				if (e instanceof ConditionalExpr) {
					tests[1].setResult(true);
				}
			}

			List<Expression> ea = parser.findExpressionsOfType("String getReward(int)");
			for (Expression e : ea) {
				if (e instanceof ConditionalExpr) {
					tests[2].setResult(true);
				}
			}

			String[] strings = {"String showRewards()", "String getReward(int)"};
			File[] files = {null, null};
				files = parser.replace(strings, "asnlib/Rewards.java");
			for (int i = 0; i < files.length; i++) {
				if (files[i] == null) {
					System.out.println("Can't find method or constructor: " + strings[i] + ". If you changed these signatures, please reset your assignment.");
				}
			}

			if (files[0] != null) {
				tests[3].setResult(
				TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input1.txt"), "(?s).*free.coffee.*50.pts.*") &&
				TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input1.txt"), "(?s)((?!baked good|100 pts|specialty coffee|200 pts|store ownership|5000 pts).)*"));
			}
			if (files[0] != null) {
				tests[4].setResult(
				TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input2.txt"), "(?s).*free.baked.good.*100.pts.*") &&
				TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input2.txt"), "(?s)((?!specialty coffee|200 pts|store ownership|5000 pts).)*"));
			}
			if (files[0] != null) {
				tests[5].setResult(
				TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input3.txt"), "(?s).*free.specialty.coffee.*200.pts.*") &&
				TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input3.txt"), "(?s)((?!store ownership|5000 pts).)*"));
			}
			if (files[0] != null) {
				tests[6].setResult(TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input4.txt"), "(?s).*store.ownership.*5000.pts.*"));
				//tests[6].setResult(TestCase.runMain(files[0].getParent(), "Rewards", new File("asnlib/input4.txt"), "(?s)((?!baked good|100 pts|specialty coffee|200 pts|free coffee|50 pts).)*"));
			}

			if (files[1] != null) {
				tests[7].setResult(TestCase.runMain(files[1].getParent(), "Rewards", new File("asnlib/input5.txt"), "(?s).*free coffee.*"));
				tests[8].setResult(TestCase.runMain(files[1].getParent(), "Rewards", new File("asnlib/input6.txt"), "(?s).*free baked good.*"));
				tests[9].setResult(TestCase.runMain(files[1].getParent(), "Rewards", new File("asnlib/input7.txt"), "(?s).*free specialty coffee.*"));
				tests[10].setResult(TestCase.runMain(files[1].getParent(), "Rewards", new File("asnlib/input8.txt"), "(?s).*store ownership.*"));
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {
			try {
				TestCase.pushAll(tests);
			} catch (Exception f) {

			}
		}
	}
}
