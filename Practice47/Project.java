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
				new TestCase("Test 2", "scrambler is recurisive (makes a call to itself)", "Completion", 1),
				new TestCase("Test 3", "Base cases: 0, 1, 2", "Completion", 1),
				new TestCase("Test 4", "scrambler works correctly", "Completion", 1)
			};

			if (!TestCase.compile(new File("Practice.java"))) {
				System.out.println("Practice.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser lpar = new Parser();
			if (lpar.parse("Practice.java")) {
				List<Node> nodes = lpar.findPieces("String scrambler(String)");
				for (Node n : nodes) {
					if (n instanceof MethodCallExpr) {
						MethodCallExpr m = (MethodCallExpr)n;
						if (m.getName().asString().equals("scrambler")) {
							tests[1].setResult(true);
						}
					}
				}
				File f = lpar.replaceMethod("String scrambler(String)", "asnlib/Practice.java");
				if (f != null) {
					tests[2].setResult((TestCase.runMain(f.getParent(), "Practice", null, "(?s).*12.*")
											&& TestCase.runMain(f.getParent(), "Practice", null, "(?s).* a .*")
											&& TestCase.runMain(f.getParent(), "Practice", null, "(?s).* cb .*")));
					tests[3].setResult(TestCase.runMain(f.getParent(), "Practice", null, "(?s).* loertyctdp .*"));
				}
			}
 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
