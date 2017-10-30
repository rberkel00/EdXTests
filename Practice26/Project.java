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
				new TestCase("Test 2", "Two for loops are created", "Completion", 1),
				new TestCase("Test 3", "An if statement is created", "Completion", 1),
				new TestCase("Test 4", "output is correct", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
				tests[3].setResult(TestCase.runMain(".", "Practice", null, "(?s)#         . #        .  #       .   #      .    #     .     #    .      #   .       #  .        # .         #."));
			}

			Parser parser = new Parser();

			int forcount = 0;
			int ifcount = 0;
			if (parser.parse("Practice.java")) {
				List<Node> exps = parser.findPieces("void main(String[])");
				for (Node e : exps) {
					//System.out.println("Expression: " + e.toString() + " Type: " + e.getClass());
					if (e instanceof ForStmt) {
						forcount++;
					} else if (e instanceof IfStmt) {
						ifcount++;
					}
				}
			}

			if (forcount > 1) tests[1].setResult(true);
			if (ifcount > 0) tests[2].setResult(true);

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
