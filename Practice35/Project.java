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
				new TestCase("Test 2", "Declared Strings \"first\", \"last\"", "Completion", 1),
				new TestCase("Test 3", "Declared integer \"position\"", "Completion", 1),
				new TestCase("Test 4", "Declared double \"battingAverage\"", "Completion", 1)
			};

			if (!TestCase.compile(new File("BaseballPlayer.java"))) {
				System.out.println("BaseballPlayer.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();

			boolean first = false;
			boolean last = false;

			if (parser.parse("BaseballPlayer.java")) {
				List<FieldDeclaration> fields = parser.fields;
				for(FieldDeclaration f : fields) {
					NodeList<VariableDeclarator> nvd = f.getVariables();
					for (VariableDeclarator vd : nvd) {
						if (vd.getName().asString().equals("first") && vd.getType().asString().equals("String")) {
							first = true;
						} else if (vd.getName().asString().equals("last") && vd.getType().asString().equals("String")) {
							last = true;
						}  else if (vd.getName().asString().equals("position") && vd.getType().asString().equals("int")) {
							tests[2].setResult(true);
						} else if (vd.getName().asString().equals("battingAverage") && vd.getType().asString().equals("double")) {
							tests[3].setResult(true);
						}
					}
				}
				if (last && first) tests[1].setResult(true);
			}


 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}
	}
}
