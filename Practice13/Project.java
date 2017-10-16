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
				new TestCase("Test 2", "if statement, two else-if statements, and an else clause is created", "Completion", 1),
				new TestCase("Test 3", "output is correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*30 or fewer letters in .How many letters do I have?.*"));
			}

			Parser parser = new Parser();


			if (parser.parse("Practice.java")) {
				List<Statement> exps = parser.findStatements("void main(String[])");
				for (Statement e : exps) {
					if (e instanceof IfStmt) {
							IfStmt i = (IfStmt)e;
							if (i.getElseStmt().isPresent()) {
								if (i.getElseStmt().get() instanceof IfStmt) {
									IfStmt ia = (IfStmt)i;
									if (ia.getElseStmt().isPresent() && ia.getElseStmt().get() instanceof IfStmt) {
										IfStmt ib = (IfStmt)i;
										if (ib.getElseStmt().isPresent()) {
											tests[1].setResult(true);
										}
									}
								}
						}
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
