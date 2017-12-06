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
				new TestCase("Test 2", "Payment class fields are set to private", "Completion", 1),
				new TestCase("Test 3", "Accessor method is correct with standard method name", "Completion", 1),
				new TestCase("Test 4", "Mutator method is correct with standard method and parameter names", "Completion", 1)
			};

			if (!TestCase.compile(new File("Payment.java"))) {
				System.out.println("Payment.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser lpar = new Parser();
			if (lpar.parse("Payment.java")) {
				for (MethodDeclaration m : lpar.methods) {
					if (m.getDeclarationAsString(true, false, false).equals("public double getAmount()")) {
						tests[2].setResult(true);
					}
					if (m.getDeclarationAsString(true, false, false).equals("public void setAmount(double)")) {
						tests[3].setResult(true);
					}
				}
				boolean[] vars = new boolean[3];
				for (FieldDeclaration f : lpar.fields) {
					for (Modifier mo : f.getModifiers()) {
						if (mo.asString().equals("private")) {
							for (VariableDeclarator v : f.getVariables()) {
								if (v.getName().asString().equals("payer")) {
									vars[0] = true;
								} else if (v.getName().asString().equals("payee")) {
									vars[1] = true;
								} else if (v.getName().asString().equals("amount")) {
									vars[2] = true;
								}
							}
						}
					}
				}
				if (vars[0] && vars[1] && vars[2]) tests[1].setResult(true);
			}

 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
