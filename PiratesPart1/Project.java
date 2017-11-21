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
				new TestCase("Test 2", "Created all necessary instance variables (names matter!)", "Completion", 1),
				new TestCase("Test 3", "Created static variable (name matters!)", "Completion", 1)
			};

			if (!TestCase.compile(new File("Pirate.java"))) {
				System.out.println("Pirate.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();
			boolean[] vars = new boolean[7];

			if (parser.parse("Pirate.java")) {
				boolean check;
				for (FieldDeclaration f : parser.fields) {
					check = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("static")) {
							check = true;
						}
					}
					for (VariableDeclarator vd : f.getVariables()) {
						if (!check && vd.getType().asString().equals("String") && vd.getName().asString().equals("name")) {
							vars[0] = true;
						}
						if (!check && vd.getType().asString().equals("int") && vd.getName().asString().equals("health")) vars[1] = true;
						if (!check && vd.getType().asString().equals("int") && vd.getName().asString().equals("swordsmanship")) vars[2] = true;
						if (!check && vd.getType().asString().equals("int") && vd.getName().asString().equals("agility")) vars[3] = true;
						if (!check && vd.getType().asString().equals("boolean") && vd.getName().asString().equals("hasParrot")) vars[4] = true;
						if (!check && vd.getType().asString().equals("boolean") && vd.getName().asString().equals("hasPegLeg")) vars[5] = true;
						if (!check && vd.getType().asString().equals("int") && vd.getName().asString().equals("doubloons")) vars[6] = true;
						if (check && vd.getType().asString().equals("int") && vd.getName().asString().equals("numberOfPirates")) tests[2].setResult(true);
					}
				}
				tests[1].setResult(true);
				for (int i = 0; i < vars.length; i++) {
					if (!vars[i]) tests[1].setResult(false);
				}

			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
