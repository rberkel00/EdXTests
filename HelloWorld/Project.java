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

		TestCase[] tests = {
				new TestCase("Test 1", "Prints \"Hello World!\"", "Completion", 1)
			};

		try {
			
			String work = "HelloWorld.java";
		
			//Report problems
			Parser parser = new Parser();
			boolean parsed = parser.parse(work);
			if (parser.problems.size() != 0) {
				System.out.println(parser.reportProblems());
			}
			if (!parsed) {
				System.out.println("You need to fix the errors listed above before any grading can be done.");
				TestCase.pushAll(tests);
				return;
			}

			List<Expression> exs = parser.findExpressionsOfType("void main(String[])");
			for (Expression ex : exs) {
				if (ex instanceof MethodCallExpr) {
					MethodCallExpr mx = (MethodCallExpr)ex;
					String name = mx.getName().asString();
					if (name.equals("println") || name.equals("printf") || name.equals("print") || name.equals("write"))
						tests[0].setResult(true);
				}
			}

			TestCase.pushAll(tests);

		} catch (Exception e) {
			try {
				TestCase.pushAll(tests);
			} catch (Exception t) {
				
			}
		}
	}
}