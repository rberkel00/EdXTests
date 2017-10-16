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
				new TestCase("Test 2", "switch statement is created", "Completion", 1),
				new TestCase("Test 3", "output is correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[2].setResult(TestCase.runMain(".", "Practice", null, "(?s).*I have 1 pizza\\..*I have 10 pizzas.*I have 0 pizza\\..*"));
			}

			Parser parser = new Parser();


			if (parser.parse("Practice.java")) {
				List<Expression> exps = parser.findExpressionsOfType("void howManyPizzas(int)");
				for (Expression e : exps) {
					if (e instanceof ConditionalExpr) {
							tests[1].setResult(true);
					}
				}
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
