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
				new TestCase("Test 2", "A loop is created to iterate through the letters of word", "Completion", 1),
				new TestCase("Test 3", "output is correct", "Completion", 5)
			};

			if (!TestCase.compile(new File("WordScore.java"))) {
				System.out.println("WordScore.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();

			int forcount = 0;
			int ifcount = 0;
			if (parser.parse("WordScore.java")) {
				List<Node> st = parser.findPieces("int getScrabbleScore()");
				for (Node s : st) {
					if (s instanceof DoStmt) {
						tests[1].setResult(true);
					} else if (s instanceof WhileStmt) {
						tests[1].setResult(true);
					} else if (s instanceof ForStmt) {
						tests[1].setResult(true);
					}
				}
			}

			String[] methods = {"int getScrabbleScore()"};
			File[] files = parser.replace(methods, "asnlib/WordScore.java");
			if (files[0] != null) {
				tests[2].setResult(TestCase.runMain(".", "WordScore", new File("asnlib/input1.txt"), "(?s).*8.*"));
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
